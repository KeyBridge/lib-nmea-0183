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
 * Water depth below transducer, in meters, feet and fathoms.
 * <p>
 * Example:<br><code>$SDDBT,8.1,f,2.4,M,1.3,F*0B</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface DBTSentence extends DepthSentence {

  /**
   * Get depth in fathoms.
   *
   * @return Depth value
   */
  double getFathoms();

  /**
   * Get depth in feet.
   *
   * @return Depth value
   */
  double getFeet();

  /**
   * Set depth value, in fathoms.
   *
   * @param depth Depth to set
   */
  void setFathoms(double depth);

  /**
   * Set depth value, in feet.
   *
   * @param depth Depth to set
   */
  void setFeet(double depth);

}
