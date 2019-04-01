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
 * Interface for sentences containing the depth of water.
 *
 * @author Kimmo Tuukkanen
 */
public interface DepthSentence extends Sentence {

  /**
   * Get depth of water, in meters.
   *
   * @return Depth value
   */
  double getDepth();

  /**
   * Set depth of water, in meters.
   *
   * @param depth Depth value
   */
  void setDepth(double depth);
}
