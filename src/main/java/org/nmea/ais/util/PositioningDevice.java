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
package org.nmea.ais.util;

/**
 * Checks the positioning device type for validity.
 *
 * @author Lázár József
 */
public class PositioningDevice {

  /**
   * Returns a text string for the EPFD.
   *
   * @return a text string describing the positioning device type
   */
  static public String toString(int deviceType) {
    switch (deviceType) {
      case 0:
        return "undefined device";
      case 1:
        return "GPS";
      case 2:
        return "GLONASS";
      case 3:
        return "combined GPS/GLONASS";
      case 4:
        return "Loran-C";
      case 5:
        return "Chayka";
      case 6:
        return "integrated navigation system";
      case 7:
        return "surveyed";
      case 8:
        return "Galileo";
      case 15:
        return "internal GNSS";
      default:
        return "not used";
    }
  }
}
