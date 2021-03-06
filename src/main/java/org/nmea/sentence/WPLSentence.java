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

import org.nmea.parser.DataNotAvailableException;
import org.nmea.parser.ParseException;
import org.nmea.type.Waypoint;

/**
 * Destination waypoint location and ID. This sentence is transmitted by some
 * GPS models in GOTO mode.
 * <p>
 * Example: <br>
 * <code>$GPWPL,5536.200,N,01436.500,E,RUSKI*1F</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface WPLSentence extends Sentence {

  /**
   * Get the destination waypoint.
   *
   * @return Waypoint
   * @throws DataNotAvailableException If any of the waypoint related data is
   *                                   not available.
   * @throws ParseException            If any of the waypoint related fields
   *                                   contain unexpected or illegal value.
   */
  Waypoint getWaypoint();

  /**
   * Set the destination waypoint.
   *
   * @param wp Waypoint to set
   */
  void setWaypoint(Waypoint wp);

}
