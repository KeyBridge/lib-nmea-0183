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

import org.nmea.dev.NMEAReader;

/**
 * $PGRME - Estimated Position Error
 * <p>
 * Garmin proprietary sentences with 4 interpreted fields
 * <pre>
 * eg. $PGRME,15.0,M,45.0,M,25.0,M*22
 *
 * 15.0,M       Estimated horizontal position error in metres (HPE)
 * 45.0,M       Estimated vertical error (VPE) in metres
 * 25.0,M       Overall spherical equivalent position error
 * </pre>
 */
public class PGRME {

  /**
   * The NMEA sentence.
   */
  private final String nmeaSentence;
  /**
   * Estimated horizontal position error in metres (HPE)
   */
  private final double errorHorizontal;
  /**
   * Estimated vertical error (VPE) in metres
   */
  private final double errorVertical;
  /**
   * Overall spherical equivalent position error
   */
  private final double errorSpherical;
  /**
   * The unit. Default is meters.
   */
  private final String unit;

  public PGRME(String nmeaSentence) {
    // Set the sentence
    this.nmeaSentence = nmeaSentence;
    // Tokenize
    String[] tokens = NMEAReader.tokenize(nmeaSentence);
    // Set values
    errorHorizontal = Double.valueOf(tokens[1]);
    errorVertical = Double.valueOf(tokens[3]);
    errorSpherical = Double.valueOf(tokens[5]);
    unit = tokens[2];
  }

  public double getErrorHorizontal() {
    return errorHorizontal;
  }

  public double getErrorSpherical() {
    return errorSpherical;
  }

  public double getErrorVertical() {
    return errorVertical;
  }

  public String getNmeaSentence() {
    return nmeaSentence;
  }

  public String getUnit() {
    return unit;
  }

  public String getDescription() {
    return "PGRME: " + " Estimated horizontal position [" + errorHorizontal + "] Estimated vertical error [" + errorVertical + "] Overall spherical position error [" + errorSpherical + "] Units [" + unit + "]";
  }

  @Override
  public String toString() {
    return nmeaSentence;
  }

}
