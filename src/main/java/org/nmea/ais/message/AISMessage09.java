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
package org.nmea.ais.message;

/**
 * Standard SAR Aircraft Position Report
 * <p>
 * Tracking information for search-and-rescue aircraft.
 * <p>
 * Total number of bits is 168.
 *
 * @author Henri Laurent
 */
public interface AISMessage09 extends AISPositionInfo {

  /**
   * Returns the Altitude of the aircraft Altitude is in meters. The special
   * value 4095 indicates altitude is not available; 4094 indicates 4094 meters
   * or higher.
   */
  int getAltitude();

  /**
   * Returns the speed over ground. Speed over ground is in knots, not deciknots
   * as in the common navigation block; planes go faster. The special value 1023
   * indicates speed not available, 1022 indicates 1022 knots or higher.
   */
  int getSpeedOverGround();

  /**
   * Returns the course over ground.
   */
  int getCourseOverGround();

  /**
   * Returns the UTC second.
   *
   * @return an integer value representing the UTC second (0-59)
   */
  int getTimeStamp();

  /**
   * Returns the DTE
   */
  boolean getDTEFlag();

  /**
   * Returns the Assigned-mode flag
   */
  boolean getAssignedModeFlag();

  /**
   * Returns the RAIM flag
   */
  boolean getRAIMFlag();

  /**
   * Returns the Radio status
   */
  int getRadioStatus();
}
