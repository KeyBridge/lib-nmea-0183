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
 * Vessel heading with magnetic deviation and variation.
 * <p>
 * Example:<br><code>$HCHDG,205.2,,,2.7,W</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface HDGSentence extends HeadingSentence {

  /**
   * Get magnetic deviation.
   *
   * @return Deviation, in degrees.
   */
  double getDeviation();

  /**
   * Get magnetic variation. Returns negative values for easterly variation and
   * positive for westerly.
   *
   * @return Variation, in degrees.
   */
  double getVariation();

  /**
   * Set magnetic deviation. Provide negative values to set easterly deviation
   * and positive to set westerly. Sets also the correct direction indicator
   * according to value (East/West).
   *
   * @param deviation Deviation, in degrees.
   * @throws IllegalArgumentException If value is out of range [-180..180].
   */
  void setDeviation(double deviation);

  /**
   * Set magnetic variation. Provide negative values to set easterly variation
   * and positive to set westerly. Sets also the correct direction indicator
   * according to value (East/West).
   *
   * @param variation Variation, in degrees.
   * @throws IllegalArgumentException If value is out of range [-180..180].
   */
  void setVariation(double variation);
}
