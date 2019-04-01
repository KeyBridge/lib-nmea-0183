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
 * GpsFixQuality defines the supported fix quality types.
 *
 * @author Kimmo Tuukkanen
 * @see FaaMode
 * @see GpsFixStatus
 * @see DataStatus
 */
public enum GpsFixQualityType {

  /**
   * No GPS fix acquired.
   */
  INVALID(0),
  /**
   * Normal GPS fix, Standard Position Service (SPS).
   */
  NORMAL(1),
  /**
   * Differential GPS fix.
   */
  DGPS(2),
  /**
   * Precise Positioning Service fix.
   */
  PPS(3),
  /**
   * Real Time Kinematic
   */
  RTK(4),
  /**
   * Float RTK
   */
  FRTK(5),
  /**
   * Estimated, dead reckoning (2.3 feature)
   */
  ESTIMATED(6),
  /**
   * Manual input mode
   */
  MANUAL(7),
  /**
   * Simulation mode
   */
  SIMULATED(8);

  private final int code;

  GpsFixQualityType(int code) {
    this.code = code;
  }

  /**
   * Returns the corresponding int indicator for fix quality.
   *
   * @return Fix quality indicator value as indicated in sentences.
   */
  public int getCode() {
    return code;
  }

  /**
   * Get GpsFixQuality enum that corresponds the actual integer identifier used
   * in the sentences.
   *
   * @param code Status identifier value
   * @return GpsFixQuality enum
   */
  public static GpsFixQualityType valueOf(int code) {
    for (GpsFixQualityType gfq : values()) {
      if (gfq.getCode() == code) {
        return gfq;
      }
    }
    throw new IllegalArgumentException("Unrecognized code " + code);
  }
}
