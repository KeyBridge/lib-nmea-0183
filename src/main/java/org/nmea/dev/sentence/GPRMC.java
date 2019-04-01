/* 
 * Copyright (C) 2019 Key Bridge
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.nmea.dev.sentence;

import java.util.Calendar;
import java.util.TimeZone;
import org.nmea.dev.NMEAReader;

/**
 * $GPRMC - Recommended Minimum Specific GPS/TRANSIT Data
 * <pre>
 * eg1. $GPRMC,081836,A,3751.65,S,14507.36,E,000.0,360.0,130998,011.3,E*62
 * eg2. $GPRMC,225446,A,4916.45,N,12311.12,W,000.5,054.7,191194,020.3,E*68
 *
 * 225446       Time of fix 22:54:46 UTC
 * A            Navigation receiver warning A = OK, V = warning
 * 4916.45,N    Latitude 49 deg. 16.45 min North
 * 12311.12,W   Longitude 123 deg. 11.12 min West
 * 000.5        Speed over ground, Knots
 * 054.7        Course Made Good, True
 * 191194       Date of fix  19 November 1994
 * 020.3,E      Magnetic variation 20.3 deg East
 * 68          mandatory checksum
 *
 * eg3. $GPRMC,220516,A,5133.82,N,00042.24,W,173.8,231.8,130694,004.2,W*70
 *               1    2    3    4    5     6    7    8      9     10  11 12
 *
 * 1   220516     Time Stamp
 * 2   A          validity - A-ok, V-invalid
 * 3   5133.82    current Latitude
 * 4   N          North/South
 * 5   00042.24   current Longitude
 * 6   W          East/West
 * 7   173.8      Speed in knots
 * 8   231.8      True course
 * 9   130694     Date Stamp
 * 10  004.2      Variation
 * 11  W          East/West
 * 12  *70        checksum
 *
 * eg4. $GPRMC,hhmmss.ss,A,llll.ll,a,yyyyy.yy,a,x.x,x.x,ddmmyy,x.x,a*hh
 * 1    = UTC of position fix
 * 2    = Data status (V=navigation receiver warning)
 * 3    = Latitude of fix
 * 4    = N or S
 * 5    = Longitude of fix
 * 6    = E or W
 * 7    = Speed over ground in knots
 * 8    = Track made good in degrees True
 * 9    = UT date
 * 10   = Magnetic variation degrees (Easterly var. subtracts from true course)
 * 11   = E or W
 * 12   = Checksum
 * </pre>
 */
public class GPRMC {

  /**
   * The NMEA sentence.
   */
  private final String nmeaSentence;
  /**
   * Time Stamp
   */
  private final Calendar gpsTimestamp;
  /**
   * Navigation receiver warning A = OK, V = warning validity. A-ok, V-invalid
   */
  private String validity;
  /**
   * current Latitude
   */
  private final double latitude;
  /**
   * current Longitude
   */
  private final double longitude;
  /**
   * Speed in knots
   */
  private final double speed;
  /**
   * True course
   */
  private final double course;
  /**
   * Variation
   */
  protected double variation;

  public GPRMC(String nmeaSentence) {
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
    // Set the date (requires sub-parsing)
    gpsTimestamp.set(Calendar.DAY_OF_YEAR, Integer.valueOf(tokens[9].substring(0, 2)));
    gpsTimestamp.set(Calendar.MONTH, Integer.valueOf(tokens[9].substring(2, 4)));
    gpsTimestamp.set(Calendar.YEAR, Integer.valueOf(tokens[9].substring(4, 6)));
    // Set other values
    // Set the other values
    latitude = (Double.valueOf(tokens[3].substring(0, 2)) + Double.valueOf(tokens[3].substring(2, tokens[3].length())) / 100) * (tokens[4].contains("N") ? 1 : -1);
    longitude = (Double.valueOf(tokens[5].substring(0, 3)) + Double.valueOf(tokens[5].substring(3, tokens[5].length())) / 100) * (tokens[6].contains("E") ? 1 : -1);
    speed = Double.valueOf(tokens[7]);
    course = Double.valueOf(tokens[8]);
    try {
      // variation is not always set. if absent, set to zero
      variation = Double.valueOf(tokens[10]) * (tokens[11].contains("W") ? 1 : -1);
    } catch (NumberFormatException e) {
      variation = 0;
    }
  }

  public double getCourse() {
    return course;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public String getNmeaSentence() {
    return nmeaSentence;
  }

  public double getSpeed() {
    return speed;
  }

  public Calendar getGpsTimestamp() {
    return gpsTimestamp;
  }

  public String getValidity() {
    return validity;
  }

  public double getVariation() {
    return variation;
  }

  public String getDescription() {
    return "GPRMC: time [" + gpsTimestamp.getTime() + "] latitude [" + latitude + "] longitude [" + longitude + "] speed [" + speed + "] course [" + course + "] variation [" + variation + "] ";
  }

  @Override
  public String toString() {
    return nmeaSentence;
  }

}
