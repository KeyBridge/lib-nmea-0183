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
package org.nmea.sentence;

import org.nmea.NMEAReader;

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
