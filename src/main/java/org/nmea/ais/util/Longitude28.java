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
 * Checks a 28-bit signed integer longitude value for validity.
 *
 * @author Lázár József
 */
public class Longitude28 {

  private static final double TO_DEGREES = 1.0 / (60.0 * 10000.0);

  /**
   * Converts the longitude value (in 1/10000 minutes) to degrees.
   *
   * @return the longitude value in degrees
   */
  public static double toDegrees(int value) {
    return value * TO_DEGREES;
  }
}
