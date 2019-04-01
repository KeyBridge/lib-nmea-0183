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
package org.nmea.type;

/**
 * Defines the supported route types.
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.sentence.RTESentence
 */
public enum RouteType {

  /**
   * Active route: complete, all waypoints in route order.
   */
  ACTIVE('c'),
  /**
   * Working route: the waypoint you just left, the waypoint you're heading to
   * and then all the rest.
   */
  WORKING('w');

  private final char code;

  private RouteType(char c) {
    code = c;
  }

  /**
   * Get the corresponding char indicator of enum.
   *
   * @return Char
   */
  public char getCode() {
    return code;
  }

  /**
   * Get the char indicator corresponding to enum.
   *
   * @param ch Char
   * @return ReturnType corresponding to specified char.
   */
  public RouteType valueOf(char ch) {
    for (RouteType type : values()) {
      if (type.getCode() == ch) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unrecognized code " + code);
  }

}
