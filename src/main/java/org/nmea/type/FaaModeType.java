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
package org.nmea.type;

/**
 * FAA operating modes reported by APB, BWC, BWR, GLL, RMA, RMB, RMC, VTG, WCV
 * and XTE sentences since NMEA 2.3. Also, the mode field in GGA was extended to
 * contain these statuses.
 * <p>
 * Notice that FAA mode dominates the {@link DataStatus} fields. Status field
 * will be set to {@link DataStatus#ACTIVE} for modes {@link #AUTOMATIC} and
 * {@link #DGPS}, and {@link DataStatus#VOID} for all other modes.
 *
 * @author Kimmo Tuukkanen
 * @see GpsFixQuality
 * @see GpsFixStatus
 * @see DataStatus
 */
public enum FaaModeType {

  /**
   * Operating in autonomous mode (automatic 2D/3D).
   */
  AUTOMATIC('A'),
  /**
   * Operating in manual mode (forced 2D or 3D).
   */
  MANUAL('M'),
  /**
   * Operating in differential mode (DGPS).
   */
  DGPS('D'),
  /**
   * Operating in estimating mode (dead-reckoning).
   */
  ESTIMATED('E'),
  /**
   * Operating in precise mode, no degradation like Selective Availability (NMEA
   * 4.00 and later).
   */
  PRECISE('P'),
  /**
   * Simulated data (running in simulator/demo mode)
   */
  SIMULATED('S'),
  /**
   * No valid GPS data available.
   */
  NONE('N');

  private final char code;

  FaaModeType(char modeCh) {
    code = modeCh;
  }

  /**
   * Returns the corresponding char indicator of GPS mode.
   *
   * @return Mode char used in sentences.
   */
  public char getCode() {
    return code;
  }

  /**
   * Returns the FaaMode enum corresponding the actual char indicator used in
   * the sentencess.
   *
   * @param code Char mode indicator
   * @return FaaMode enum
   */
  public static FaaModeType valueOf(char code) {
    for (FaaModeType gm : values()) {
      if (gm.getCode() == code) {
        return gm;
      }
    }
    throw new IllegalArgumentException("Unrecognized code " + code);
  }
}
