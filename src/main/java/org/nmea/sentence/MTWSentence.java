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
package org.nmea.sentence;

/**
 * Water temperature in degrees Celsius.
 * <p>
 * Example:<br><code>$YXMTW,17.75,C*5D</code>
 *
 * @author Warren Zahra, Kimmo Tuukkanen
 */
public interface MTWSentence extends Sentence {

  /**
   * Get the water temperature.
   *
   * @return Temperature in degrees Celsius.
   */
  double getTemperature();

  /**
   * Set the water temperature.
   *
   * @param temp Water temperature in degrees Celsius.
   */
  void setTemperature(double temp);
}
