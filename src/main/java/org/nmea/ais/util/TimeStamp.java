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
 * Checks a 6-bit integer time stamp value for validity.
 *
 * @author Lázár József
 */
public class TimeStamp {

  private static final int MINVALUE = 0;
  private static final int MAXVALUE = 59;

  /**
   * Checks if the time stamp value is available.
   *
   * @return true if the time stamp falls within a range
   */
  public static boolean isAvailable(int value) {
    return (value >= MINVALUE && value <= MAXVALUE);
  }

  /**
   * Return a string representing the time stamp value.
   *
   * @return a string representing the time stamp
   */
  public static String toString(int value) {
    switch (value) {
      case 60:
        return "no time stamp";
      case 61:
        return "positioning system manual";
      case 62:
        return "dead reckoning";
      case 63:
        return "positioning system inoperative";
      default:
        return Integer.toString(value);
    }
  }
}
