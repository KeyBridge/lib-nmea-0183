/**
 * <p>
 * Copyright 2010 Key Bridge Global LLC.
 * </p>
 * <p>
 * http://keybridgeglobal.com
 * </p>
 * <p>
 * This file is part of the Java package "GPSd Java Client: (GPSdClient)".
 * </p>
 * <p>
 * GPSdClient is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * </p>
 * <p>
 * GPSdClient is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * </p>
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * GPSdClient. If not, see <http://www.gnu.org/licenses/>.
 * </p>
 */
package org.nmea.sentence;

/**
 * $PGRME - Estimated Position Error
 * <p>
 * Garmin proprietary sentences with 4 interpreted
 * <p>
 * 
 * <pre>
 * 
 * eg. $PGRME,15.0,M,45.0,M,25.0,M*22
 * 
 * 15.0,M       Estimated horizontal position error in metres (HPE)
 * 45.0,M       Estimated vertical error (VPE) in metres
 * 25.0,M       Overall spherical equivalent position error
 * </pre>
 */
public class PGRME {

  private String nmeaSentence;
  private double errorHorizontal;
  private double errorVertical;
  private double errorSpherical;
  private String unit;

  public PGRME(String nmeaSentence) {
    // Set the sentence
    this.nmeaSentence = nmeaSentence;
    // Tokenize
    String[] tokens = nmeaSentence.split(",");
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

  @Override
  public String toString() {
    return "PGRME: " + " Estimated horizontal position [" + errorHorizontal + "] Estimated vertical error [" + errorVertical + "] Overall spherical position error [" + errorSpherical + "] Units [" + unit + "]";
  }

  /**
   * Test harness
   * 
   * @param args
   */
  public static void main(String[] args) {
    String nmea = "$PGRME,0.00,M,0.00,M,6.73,M*1C";
    PGRME g = new PGRME(nmea);
    System.out.println(g.toString());
  }
}
