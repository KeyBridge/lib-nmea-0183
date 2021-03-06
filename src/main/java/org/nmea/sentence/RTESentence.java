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
import org.nmea.type.RouteType;

/**
 * GPS route data and list of waypoints.
 * <p>
 * Example:<br>
 * <code>$GPRTE,1,1,c,0,MELIN,RUSKI,KNUDAN*25</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface RTESentence extends Sentence {

  /**
   * Add a waypoint ID at the end of waypoint list. The number of waypoint id
   * fields is increased by one on each addition.
   *
   * @param id Waypoint ID to add.
   * @return The total number of waypoint IDs after addition.
   */
  int addWaypointId(String id);

  /**
   * Get the number or name of the route.
   *
   * @return Route ID or name as String
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  String getRouteId();

  /**
   * Get the number of sentences in RTE sequence.
   *
   * @return integer
   * @see #getSentenceIndex()
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  int getSentenceCount();

  /**
   * Get the index of sentence in RTE sequence.
   *
   * @return integer
   * @see #getSentenceCount()
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  int getSentenceIndex();

  /**
   * Get the number of waypoints IDs in this sentence.
   *
   * @return Waypoint count
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  int getWaypointCount();

  /**
   * Get the list of route waypoints.
   *
   * @return Waypoint IDs as String array
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  String[] getWaypointIds();

  /**
   * Tells if the sentence holds a current active route data.
   *
   * @return true if active route, otherwise false.
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  boolean isActiveRoute();

  /**
   * Tells if this is the first sentence in RTE sequence.
   *
   * @return true if there's no sentences left, otherwise false.
   * @throws DataNotAvailableException If the sentence index or sentence count
   *                                   is not available.
   * @throws ParseException            If sentence index or count fields contain
   *                                   unexpected or illegal value.
   */
  boolean isFirst();

  /**
   * Tells if this is the last sentence in RTE sequence.
   *
   * @return true if there's no sentences left, otherwise false.
   * @throws DataNotAvailableException If the sentence index or sentence count
   *                                   is not available.
   * @throws ParseException            If sentence index or count fields contain
   *                                   unexpected or illegal value.
   */
  boolean isLast();

  /**
   * Tells if the sentence holds a current working route data.
   *
   * @return true if working route, otherwise false.
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If status field contains illegal value.
   */
  boolean isWorkingRoute();

  /**
   * Set the route name or number.
   *
   * @param id Route ID or name as String
   */
  void setRouteId(String id);

  /**
   * Set the type of route.
   *
   * @param type RouteType to set
   */
  void setRouteType(RouteType type);

  /**
   * Set the number of sentences in RTE sequence.
   *
   * @param count Sentence count in sequence
   * @throws IllegalArgumentException If the specified count is negative.
   */
  void setSentenceCount(int count);

  /**
   * Set the index of sentence in RTE sequence.
   *
   * @param index Sentence index in sequence
   * @throws IllegalArgumentException If specified index is negative.
   */
  void setSentenceIndex(int index);

  /**
   * Set the list of route waypoints.
   *
   * @param ids String array of waypoint IDs
   */
  void setWaypointIds(String[] ids);

}
