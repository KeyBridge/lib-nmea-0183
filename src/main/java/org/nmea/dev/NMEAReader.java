/*
 * Copyright 2017 Key Bridge.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nmea.dev;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * NMEA 0183 Message.
 * <p>
 * NMEA 0183 is a combined electrical and data specification for communication
 * between marine electronics such as echo sounder, sonars, anemometer,
 * gyrocompass, autopilot, GPS receivers and many other types of instruments. It
 * has been defined by, and is controlled by, the National Marine Electronics
 * Association. It replaces the earlier NMEA 0180 and NMEA 0182 standards.[1] In
 * marine applications, it is slowly being phased out in favor of the newer NMEA
 * 2000 standard.
 * <p>
 * The electrical standard that is used is EIA-422, although most hardware with
 * NMEA-0183 outputs are also able to drive a single EIA-232 port. Although the
 * standard calls for isolated inputs and outputs, there are various series of
 * hardware that do not adhere to this requirement.
 * <p>
 * The NMEA 0183 standard uses a simple ASCII, serial communications protocol
 * that defines how data are transmitted in a "sentence" from one "talker" to
 * multiple "listeners" at a time. Through the use of intermediate expanders, a
 * talker can have a unidirectional conversation with a nearly unlimited number
 * of listeners, and using multiplexers, multiple sensors can talk to a single
 * computer port.
 * <p>
 * At the application layer, the standard also defines the contents of each
 * sentence (message) type, so that all listeners can parse messages accurately.
 * <p>
 * Decodes the following NMEA sentence types:
 * <ul><li>GGA - Fix information (full GPS data)</li><li>GLL - Geographic
 * Latitude and Longitude (Loran)</li><li>RMC - Recommended minimum data for gps
 * (limited GPS)</li><li>ZDA - Date and Time</li></ul>
 * <p>
 * Encodes: <ul><li>ZDA - Date and Time</li><li>GLL - Latitude /
 * Longitude</li></ul>
 * <p>
 * @author Jesse Caulfield <jesse@caulfield.org>
 * @see <a href="http://www.gpsinformation.org/dale/nmea.htm#nmea">NMEA Data</a>
 */
public class NMEAReader {

  /**
   * Alternative sentence begin character used in VDO and VDM sentences.
   */
  private static final String ALTERNATIVE_BEGIN_CHAR = "!";

  /**
   * Sentence begin character
   */
  private static final String BEGIN_CHAR = "$";
  /**
   * Sentence data fields delimiter char
   */
  private static final String FIELD_DELIMITER = ",";
  /**
   * Checksum field delimiter char
   */
  private static final String CHECKSUM_DELIMITER = "*";

  private Calendar cal;
  private double latitude;
  private double longitude;
  private String ZDA;
  private String GLL;
  private final DecimalFormat df2 = new DecimalFormat("00");
  private final DecimalFormat df2dot2 = new DecimalFormat("00.00");
  private final DecimalFormat df3 = new DecimalFormat("000");
  private final DecimalFormat df3dot2 = new DecimalFormat("000.00");

  /**
   * Creates an NMEA object<br>
   * Sets the Calendar and ZDA string to the system timestamp
   */
  public NMEAReader() {
    this.cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    this.ZDA = encodeZDA(cal);
  }

  /**
   * Creates an NMEA object May be created with any acceptable NMEA sentence
   * <p>
   * Sets the Calendar and ZDA string to the fix timestamp
   * <p>
   * Decodes the input GGA/GLL string (lat/lon) and sets the Lat/Lon values
   * <p>
   * Encodes the date/time and sets ZDA string
   * <p>
   * @param message - encoded GPS location
   */
  public NMEAReader(String message) {
    this.cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    this.ZDA = encodeZDA(cal);

    if (message.startsWith("$GPGGA")) {
      decodeGGA(message);
    } else if (message.startsWith("$GPGLL")) {
      decodeGLL(message);
    } else if (message.startsWith("$GPRMC")) {
      decodeRMC(message);
    } else if (message.startsWith("$GPZDA")) {
      this.cal = decodeZDA(message);
      this.ZDA = message;
    }

    this.GLL = encodeGLL(this.latitude, this.longitude);
  }

  /**
   * Construct a new NMEA GLL message with the Calendar and ZDA string to the
   * system time This also encodes and sets this GLL string (lat/lon).
   * </pre>
   * <p>
   * @param latitude  the latitude
   * @param longitude the longitude
   */
  public NMEAReader(double latitude, double longitude) {
    this.cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    this.GLL = encodeGLL(latitude, longitude);
  }

  /**
   * Construct a new NMEA message. Sets the Calendar and ZDA string to the
   * system time Encodes and sets this GLL string (lat/lon)
   * <p>
   * @param latitude  the latitude
   * @param longitude the longitude
   */
  public NMEAReader(String latitude, String longitude) {
    this(Double.valueOf(latitude), Double.valueOf(longitude));
  }

  /**
   * Tokenize the NMEA sentence.
   *
   * @param nmeaSentence
   * @return
   */
  public static String[] tokenize(String nmeaSentence) {
    if (nmeaSentence.startsWith(BEGIN_CHAR) || nmeaSentence.startsWith(ALTERNATIVE_BEGIN_CHAR)) {
      return nmeaSentence.substring(1).split("[,\\*]");
    } else {
      return nmeaSentence.split("[,\\*]");
    }
  }

  /**
   * Returns a calendar object
   * <br>
   * To set the calendar object, either use the object constructor NMEA(ZDA) or
   * setCalendar(ZDA)
   * <p>
   * @return
   */
  public Calendar getCalendar() {
    return cal;
  }

  /**
   * Mirror function for decodeZDA
   * <p>
   * @param ZDA - an NMEA encoded ZDA datestamp: e.g.
   *            $GPZDA,hhmmss.ss,dd,mm,yyyy,xx,yy*CC
   */
  public void setCalendar(String ZDA) {
    this.cal = decodeZDA(ZDA);
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double l) {
    this.latitude = l;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double l) {
    this.longitude = l;
  }

  /**
   * Return an NMEA encoded date time sentence (timestamp)
   * <p>
   * @return
   */
  public String getZDA() {
    return ZDA;
  }

  /**
   * Retun an NMEA encoded location sentence (lat/lon)
   * <p>
   * @return
   */
  public String getGLL() {
    return GLL;
  }

  /**
   * mirror function to getZDA - for convenience
   * <p>
   * @return
   */
  public String getDateTimeSentence() {
    return ZDA;
  }

  /**
   * mirror function to getGLL - for convenience
   * <p>
   * @return
   */
  public String getLocationSentence() {
    return GLL;
  }

  /**
   * Decodes a ZDA (Date and Time) sentence and returns a proper Java Calendar
   * object.
   * <pre>
   * $GPZDA,hhmmss.ss,dd,mm,yyyy,xx,yy*CC
   * $GPZDA,201530.00,04,07,2002,00,00*60
   *
   * where:
   *   hhmmss    HrMinSec(UTC)
   *   dd,mm,yyy Day,Month,Year
   *   xx        local zone hours -13..13
   *   yy        local zone minutes 0..59
   *   *CC       checksum
   * </pre>
   * <p>
   * @param s - ZDA date sentence
   * @return Calendar object
   */
  public static Calendar decodeZDA(String s) {
    if (s.startsWith("$GPZDA")) {
      StringTokenizer st = new StringTokenizer(s, FIELD_DELIMITER);
      // Skip the header
      st.nextToken();
      // Process the time stamp
      String tmp = st.nextToken();
      int hh = Integer.valueOf(tmp.substring(0, 2));
      int mm = Integer.valueOf(tmp.substring(2, 4));
      int ss = Integer.valueOf(tmp.substring(4, 6));
      int dd = Integer.valueOf(st.nextToken());
      int mo = Integer.valueOf(st.nextToken());
      int yyyy = Integer.valueOf(st.nextToken());
      int tzh = Integer.valueOf(st.nextToken());
      int tzm = Integer.valueOf(st.nextToken().substring(0, 2));
      String timeZoneId = "GMT" + ((tzh > 0) ? "+" + String.valueOf(tzh) : String.valueOf(tzh)) + String.valueOf(tzm);
      Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
      calendar.set(yyyy, mo - 1, dd, hh, mm, ss); // Cal months start at zero
      return calendar;
    } else {
      return Calendar.getInstance();
    }
  }

  /**
   * Encode a Java calendar object into a ZDA string ZDA - Data and Time
   * <p>
   * Sets this ZDA string. Retrieve with getZDA();
   * <p>
   * Example sentence: $GPZDA,hhmmss.ss,dd,mm,yyyy,xx,yy*CC
   * <pre>
   * $GPZDA,hhmmss.ss,dd,mm,yyyy,xx,yy*CC
   * $GPZDA,201530.00,04,07,2002,00,00*60
   *
   * where:
   *   hhmmss    HrMinSec(UTC)
   *   dd,mm,yyy Day,Month,Year
   *   xx        local zone hours -13..13
   *   yy        local zone minutes 0..59
   *   *CC       checksum
   * </pre>
   * <p>
   * @param cal the calendar to encode
   * @return Encoded ZDA string (date/time)
   */
  public final String encodeZDA(Calendar cal) {
    StringBuilder s = new StringBuilder("$GPZDA,");
    int hr = cal.get(Calendar.HOUR_OF_DAY);

    s.append(df2.format(cal.get(Calendar.HOUR_OF_DAY)));
    s.append(df2.format(cal.get(Calendar.MINUTE)));
    s.append(df2.format(cal.get(Calendar.SECOND)));
    s.append(",");
    s.append(df2.format(cal.get(Calendar.DAY_OF_MONTH)));
    s.append(",");
    s.append(df2.format(cal.get(Calendar.MONTH)));
    s.append(",");
    s.append(cal.get(Calendar.YEAR));
    s.append(",");
    s.append(df2.format(cal.get(Calendar.ZONE_OFFSET) / 3600000));
    s.append(",");
    s.append("00");
//    this.ZDA = s;
    return s.toString();
  }

  /**
   * Decodes NMEA GLL message
   * <p>
   * GLL - Geographic Latitude and Longitude is a holdover from Loran data and
   * some old units may not send the time and data active information if they
   * are emulating Loran data. If a gps is emulating Loran data they may use the
   * LC Loran prefix instead of GP.
   * <pre>
   * $GPGLL,4916.45,N,12311.12,W,225444,A,*1D
   *
   * Where:
   * GLL          Geographic position, Latitude and Longitude
   * 4916.46,N    Latitude 49 deg. 16.45 min. North
   * 12311.12,W   Longitude 123 deg. 11.12 min. West
   * 225444       Fix taken at 22:54:44 UTC
   * A            Data Active or V (void)
   * *iD          checksum data
   *
   * Note that, as of the 2.3 release of NMEA, there is a new field in the GLL
   * sentence at the end just prior to the checksum. For more information on this
   * field see here.
   * </pre>
   * <p>
   * @param s
   */
  public final void decodeGLL(String s) {
    if (s.contains("GPGLL")) {
      StringTokenizer st = new StringTokenizer(s, FIELD_DELIMITER);
      String tmp;
      // Skip the header
      st.nextToken();
      // Process the location
      tmp = st.nextToken();
      int latdeg = Integer.valueOf(tmp.substring(0, 2));
      double latmin = Double.valueOf(tmp.substring(2, tmp.length()));
      this.latitude = latdeg + latmin / 60;
      if (st.nextToken().equals("S")) {
        this.latitude *= -1;
      }
      tmp = st.nextToken();
      int londeg = Integer.valueOf(tmp.substring(0, 3));
      double lonmin = Double.valueOf(tmp.substring(3, tmp.length()));
      this.longitude = londeg + lonmin / 60;
      if (st.nextToken().equals("W")) {
        this.longitude *= -1;
      }
      // Ignore the client's fix time stamp
      //        String tmp = st.nextToken();
      //        int hh = Integer.valueOf(tmp.substring(0, 2));
      //        int mm = Integer.valueOf(tmp.substring(2, 4));
      //        int ss = Integer.valueOf(tmp.substring(4, 6));
    }
  }

  /**
   * Encode the latitude/longitude pairs and set this objects GLL sentence<br>
   * $GPGLL,4916.45,N,12311.12,W,225444,A,*1D
   * <p>
   * @param lat
   * @param lon
   * @return Encoded GLL string (lat/lon)
   */
  public final String encodeGLL(double lat, double lon) {
    LatLon ll = new LatLon(lat, lon);

    String s = "$GPGLL";
    s += ",";
    s += df2.format(ll.getLatDegrees());
    s += df2dot2.format(ll.getLatMinutes() + ll.getLatSeconds() / 60);
    s += ",";
    s += ll.getLatHeading();
    s += ",";
    s += df2.format(ll.getLonDegrees());
    s += df2dot2.format(ll.getLonMinutes() + ll.getLonSeconds() / 60);
    s += ",";
    s += ll.getLonHeading();
    s += ",";
    s += ",";
    s += "*00";
//    this.GLL = s;
    return s;
  }

  public String encodeGLL(String latitude, String longitude) {
    return encodeGLL(Double.valueOf(latitude), Double.valueOf(longitude));
  }

  /**
   * GGA - essential fix data which provide 3D location and accuracy data.
   * <pre>
   *  $GPGGA,123519,4807.038,N,01131.000,E,1,08,0.9,545.4,M,46.9,M,,*47
   *
   * Where:
   * GGA          Global Positioning System Fix Data
   * 123519       Fix taken at 12:35:19 UTC
   * 4807.038,N   Latitude 48 deg 07.038' N
   * 01131.000,E  Longitude 11 deg 31.000' E
   * 1            Fix quality: 0 = invalid
   *                           1 = GPS fix (SPS)
   *                           2 = DGPS fix
   *                           3 = PPS fix
   *                           4 = Real Time Kinematic
   *                           5 = Float RTK
   *                           6 = estimated (dead reckoning) (2.3 feature)
   *                           7 = Manual input mode
   *                           8 = Simulation mode
   * 08           Number of satellites being tracked
   * 0.9          Horizontal dilution of position
   * 545.4,M      Altitude, Meters, above mean sea level
   * 46.9,M       Height of geoid (mean sea level) above WGS84
   * ellipsoid
   * (empty field) time in seconds since last DGPS update
   * (empty field) DGPS station ID number
   *  *47          the checksum data, always begins with *
   *
   * If the height of geoid is missing then the altitude should be suspect.
   * Some non-standard implementations report altitude with respect to the
   * ellipsoid rather than geoid altitude. Some units do not report negative
   * altitudes at all. This is the only sentence that reports altitude.
   * </pre>
   * <p>
   * @param s
   */
  public final void decodeGGA(String s) {
    if (s.contains("GPGGA")) {
      //    System.out.println("decodeGGA: " + s);
      StringTokenizer st = new StringTokenizer(s, FIELD_DELIMITER);
      String tmp;

      // Skip the header
      st.nextToken();
      // The GPS fix sample timestamp
      tmp = st.nextToken();
//      int hh = Integer.valueOf(tmp.substring(0, 2));
//      int mm = Integer.valueOf(tmp.substring(2, 4));
//      int ss = Integer.valueOf(tmp.substring(4, 6));
      this.cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      this.cal.set(Calendar.HOUR, Integer.valueOf(tmp.substring(0, 2)));
      this.cal.set(Calendar.MINUTE, Integer.valueOf(tmp.substring(2, 4)));
      this.cal.set(Calendar.SECOND, Integer.valueOf(tmp.substring(4, 6)));

      // Process the location
      tmp = st.nextToken();
      int latdeg = Integer.valueOf(tmp.substring(0, 2));
      double latmin = Double.valueOf(tmp.substring(2, tmp.length()));
      this.latitude = latdeg + latmin / 60;
      if (st.nextToken().equals("S")) {
        this.latitude *= -1;
      }
      tmp = st.nextToken();
      int londeg = Integer.valueOf(tmp.substring(0, 3));
      double lonmin = Double.valueOf(tmp.substring(3, tmp.length()));
      this.longitude = londeg + lonmin / 60;
      if (st.nextToken().equals("W")) {
        this.longitude *= -1;
      }
      // Fix Quality
      int fixquality = Integer.valueOf(st.nextToken());
      // Number of satellites
      st.nextToken();
      // Horizontal dilution
      st.nextToken();
//        Altitude, Meters, above mean sea level
      double heightamsl = Double.valueOf(st.nextToken());
      st.nextToken();
//        Height of geoid (mean sea level) above WGS84 ellipsoid
      double heightgeoid = Double.valueOf(st.nextToken());
//        Time in seconds since last DGPS update
//        DGPS station ID number
//        The checksum data, always begins with *
    } else {
      this.cal = new GregorianCalendar();
    }
  }

  /**
   * RMC - NMEA has its own version of essential gps pvt (position, velocity,
   * time) data. It is called RMC, The Recommended Minimum, which will look
   * similar to:
   * <pre>
   * $GPRMC,123519,A,4807.038,N,01131.000,E,022.4,084.4,230394,003.1,W*6A
   *
   * Where:
   * RMC          Recommended Minimum sentence C
   * 123519       Fix taken at 12:35:19 UTC
   * A            Status A=active or V=Void.
   * 4807.038,N   Latitude 48 deg 07.038' N
   * 01131.000,E  Longitude 11 deg 31.000' E
   * 022.4        Speed over the ground in knots
   * 084.4        Track angle in degrees True
   * 230394       Date - 23rd of March 1994
   * 003.1,W      Magnetic Variation
   * *6A          The checksum data, always begins with *
   * </pre>
   * <p>
   * @param s
   */
  public final void decodeRMC(String s) {
    if (s.contains("GPRMC")) {
      System.out.println("decodeRMC: " + s);
      StringTokenizer st = new StringTokenizer(s, FIELD_DELIMITER);
      String tmp;

      // Skip the header
      st.nextToken();
      // The GPS fix sample timestamp
      tmp = st.nextToken();
      int hh = Integer.valueOf(tmp.substring(0, 2));
      int mm = Integer.valueOf(tmp.substring(2, 4));
      int ss = Integer.valueOf(tmp.substring(4, 6));

//    System.out.println("setting hh to: " + hh + " and mm to " + mm + " huh " + Calendar.MINUTE);
//    System.out.println("hhmmss: " + hh + " " + mm + " " + ss);
      this.cal.set(Calendar.HOUR, hh);
      this.cal.set(Calendar.MINUTE, mm);
      this.cal.set(Calendar.SECOND, ss);

      //Status [A]ctive / [V]oid
      st.nextToken();

      // Process the location
      tmp = st.nextToken();
      int latdeg = Integer.valueOf(tmp.substring(0, 2));
      double latmin = Double.valueOf(tmp.substring(2, tmp.length()));
      this.latitude = latdeg + latmin / 60;
      if (st.nextToken().equals("S")) {
        this.latitude *= -1;
      }
      tmp = st.nextToken();
      int londeg = Integer.valueOf(tmp.substring(0, 3));
      double lonmin = Double.valueOf(tmp.substring(3, tmp.length()));
      this.longitude = londeg + lonmin / 60;
      if (st.nextToken().equals("W")) {
        this.longitude *= -1;
      }
      // Speed over groung in knots
      //    double speedOverGround = Double.valueOf(st.nextToken());
      st.nextToken();
      // Track angle in degrees True
      st.nextToken();
      // Date
      tmp = st.nextToken();
      int dd = Integer.valueOf(tmp.substring(0, 2));
      int mo = Integer.valueOf(tmp.substring(2, 4));
      int yy = Integer.valueOf(tmp.substring(4, 6));
      this.cal.set(Calendar.DAY_OF_MONTH, dd);
      this.cal.set(Calendar.MONTH, mo);
      this.cal.set(Calendar.YEAR, yy);
      // Magnetic variation
      // Magnetic variation direction (NSEW)
      // The checksum data, always begins with *
    } else {
      this.cal = new GregorianCalendar();
    }
  }

  @Override
  public String toString() {
    return ("NMEA: cal: " + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + " lat: " + latitude + " lon: " + longitude + " \n zda: " + ZDA + " \n gll: " + GLL);
  }

}
