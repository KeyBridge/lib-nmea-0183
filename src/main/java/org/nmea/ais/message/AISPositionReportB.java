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
 * Common interface for all Class B messages providing position reports.
 *
 * @author Lázár József
 */
public interface AISPositionReportB extends AISPositionInfo {

  /**
   * Returns the speed over ground.
   */
  int getSpeedOverGround();

  /**
   * Returns the course over ground.
   */
  int getCourseOverGround();

  /**
   * Returns the true heading.
   */
  int getTrueHeading();

  /**
   * Returns the time stamp contained in the message.
   */
  int getTimeStamp();
}
