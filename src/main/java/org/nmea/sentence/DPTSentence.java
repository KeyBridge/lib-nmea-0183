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
 * Depth of water, measured in meters. Includes offset to transducer, positive
 * values for distance from transducer to water line and negative values for
 * distance from transducer to keel. The maximum value is included since NMEA
 * v3.0 and may thus be missing.
 * <p>
 * Example:<br><code>$SDDPT,2.4,,*7F</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface DPTSentence extends DepthSentence {

  /**
   * Get offset to transducer.
   *
   * @return Offset in meters.
   */
  double getOffset();

  /**
   * Set offset to transducer.
   *
   * @param offset Offset in meters
   */
  void setOffset(double offset);

  /**
   * Get maximum depth value the sounder can detect.
   *
   * @return Maximum depth, in meters.
   */
  double getMaximum();

  /**
   * Set maximum depth value the sounder can detect.
   *
   * @param max Maximum depth, in meters.
   */
  void setMaximum(double max);
}
