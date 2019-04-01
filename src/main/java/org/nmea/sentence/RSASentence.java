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

import org.nmea.type.DataStatusType;
import org.nmea.type.Side;

/**
 * Rudder angle, measured in degrees. Negative value represents port side,
 * positive starboard side turn. May contain value for both port and starboard
 * rudder. {@link Side#PORT} is used for vessels with single rudder.
 * <p>
 * Example:<br>
 * <code>$IIRSA,9,A,,*38</code>
 *
 * @author Lázár József, Kimmo Tuukkanen
 */
public interface RSASentence extends Sentence {

  /**
   * Returns the rudder angle for specified side.
   *
   * @return Rudder angle in degrees.
   */
  double getRudderAngle(Side side);

  /**
   * Sets the rudder's angle for specified side.
   *
   * @param side  Rudder side
   * @param angle Rudder angle in degrees
   */
  void setRudderAngle(Side side, double angle);

  /**
   * Returns the data status (valid/invalid) for specified side.
   *
   * @param side Rudder side
   * @return Data status
   */
  DataStatusType getStatus(Side side);

  /**
   * Set data status for specified side.
   *
   * @param side   Rudder side
   * @param status Data status to set
   */
  void setStatus(Side side, DataStatusType status);
}
