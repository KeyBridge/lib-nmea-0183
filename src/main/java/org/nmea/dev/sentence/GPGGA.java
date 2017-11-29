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
package org.nmea.dev.sentence;

import java.util.Calendar;
import java.util.TimeZone;
import org.nmea.dev.NMEAReader;

/**
 * $GPGGA: Global Positioning System Fix Data
 * <p>
 * Time, position and fix related data for a GPS receiver.
 * <pre>
 * eg2. $--GGA,hhmmss.ss,llll.ll,a,yyyyy.yy,a,x,xx,x.x,x.x,M,x.x,M,x.x,xxxx
 * hhmmss.ss = UTC of position
 * llll.ll = latitude of position
 * a = N or S
 * yyyyy.yy = Longitude of position
 * a = E or W
 * x = GPS Quality indicator (0=no fix, 1=GPS fix, 2=Dif. GPS fix)
 * xx = number of satellites in use
 * x.x = horizontal dilution of precision
 * x.x = Antenna altitude above mean-sea-level
 * M = units of antenna altitude, meters
 * x.x = Geoidal separation
 * M = units of geoidal separation, meters
 * x.x = Age of Differential GPS data (seconds)
 * xxxx = Differential reference station ID
 *
 * eg3. $GPGGA,hhmmss.ss,llll.ll,a,yyyyy.yy,a,x,xx,x.x,x.x,M,x.x,M,x.x,xxxx*hh
 * 1    = UTC of Position
 * 2    = Latitude
 * 3    = N or S
 * 4    = Longitude
 * 5    = E or W
 * 6    = GPS quality indicator (0=invalid; 1=GPS fix; 2=Diff. GPS fix)
 * 7    = Number of satellites in use [not those in view]
 * 8    = Horizontal dilution of position
 * 9    = Antenna altitude above/below mean sea level (geoid)
 * 10   = Meters  (Antenna height unit)
 * 11   = Geoidal separation (Diff. between WGS-84 earth ellipsoid and mean sea level.  Negative number indicates geoid is below WGS-84 ellipsoid)
 * 12   = Meters  (Units of geoidal separation)
 * 13   = Age in seconds since last update from diff. reference station
 * 14   = Diff. reference station ID#
 * 15   = Checksum
 * </pre>
 */
public class GPGGA {

  /**
   * The NMEA sentence.
   */
  private final String nmeaSentence;
  /**
   * UTC of Position
   */
  private final Calendar gpsTimestamp;
  /**
   * Latitude
   */
  private final double latitude;
  /**
   * Longitude
   */
  private final double longitude;
  /**
   * GPS quality indicator (0=invalid; 1=GPS fix; 2=Diff. GPS fix)
   */
  private final int mode;
  /**
   * Number of satellites in use [not those in view]
   */
  private final int satellites;
  /**
   * Horizontal dilution of position
   */
  private final double hdop;
  /**
   * Antenna altitude above/below mean sea level (geoid)
   */
  private final double altitude;
  /**
   * Antenna height unit (e.g. Meters)
   */
  private final String altitudeUnit;
  /**
   * Geoidal separation (Diff. between WGS-84 earth ellipsoid and mean sea
   * level. Negative number indicates geoid is below WGS-84 ellipsoid)
   */
  private final double height_above_WGS84;

  /**
   * Constructor to create a fully populated GPGGA message object
   *
   * @param nmeaSentence
   */
  public GPGGA(String nmeaSentence) {
    // Set the sentence
    this.nmeaSentence = nmeaSentence;
    // Tokenize
    String[] tokens = NMEAReader.tokenize(nmeaSentence);
    // Set the time (requires sub-parsing)
    this.gpsTimestamp = Calendar.getInstance();
    gpsTimestamp.set(Calendar.ZONE_OFFSET, TimeZone.getTimeZone("UTC").getRawOffset());
    gpsTimestamp.set(Calendar.HOUR, Integer.valueOf(tokens[1].substring(0, 2)));
    gpsTimestamp.set(Calendar.MINUTE, Integer.valueOf(tokens[1].substring(2, 4)));
    gpsTimestamp.set(Calendar.SECOND, Integer.valueOf(tokens[1].substring(4, 6)));
    // Set the other values
    latitude = (Double.valueOf(tokens[2].substring(0, 2)) + Double.valueOf(tokens[2].substring(2, tokens[2].length())) / 100) * (tokens[3].contains("N") ? 1 : -1);
    longitude = (Double.valueOf(tokens[4].substring(0, 3)) + Double.valueOf(tokens[4].substring(3, tokens[4].length())) / 100) * (tokens[5].contains("E") ? 1 : -1);
    mode = Integer.valueOf(tokens[6]);
    satellites = Integer.valueOf(tokens[7]);
    hdop = Double.valueOf(tokens[9]);
    altitude = Double.valueOf(tokens[9]);
    altitudeUnit = tokens[10];
    height_above_WGS84 = Double.valueOf(tokens[11]);
  }

  public double getAltitude() {
    return altitude;
  }

  public String getAltitudeUnit() {
    return altitudeUnit;
  }

  public double getHdop() {
    return hdop;
  }

  public double getHeight_above_WGS84() {
    return height_above_WGS84;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public int getMode() {
    return mode;
  }

  public String getNmeaSentence() {
    return nmeaSentence;
  }

  public int getSatellites() {
    return satellites;
  }

  public Calendar getGpsTimestamp() {
    return gpsTimestamp;
  }

  public String getDescription() {
    return "GPGGA: time [" + gpsTimestamp.getTime() + "] latitude [" + latitude + "] longitude [" + longitude + "] mode [" + mode + "] satellites [" + satellites + "] hdop [" + hdop + "] altitude [" + altitude + "] altitudeUnit [" + altitudeUnit + "] height_above_WGS84 [" + height_above_WGS84 + "] ";
  }

  @Override
  public String toString() {
    return nmeaSentence;
  }
}
