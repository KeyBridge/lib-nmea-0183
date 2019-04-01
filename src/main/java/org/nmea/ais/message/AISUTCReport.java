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
 * Common interface for all UTC time and position providing AIS messages.
 *
 * @author Lázár József
 */
public interface AISUTCReport extends AISMessage {

  /**
   * Returns the UTC year.
   *
   * @return an integer value representing the UTC year (1-9999)
   */
  int getUtcYear();

  /**
   * Returns the UTC month.
   *
   * @return an integer value representing the UTC month (1-12)
   */
  int getUtcMonth();

  /**
   * Returns the UTC day.
   *
   * @return an integer value representing the UTC day (1-31)
   */
  int getUtcDay();

  /**
   * Returns the UTC hour.
   *
   * @return an integer value representing the UTC hour (0-23)
   */
  int getUtcHour();

  /**
   * Returns the UTC minute.
   *
   * @return an integer value representing the UTC minute (0-59)
   */
  int getUtcMinute();

  /**
   * Returns the UTC second.
   *
   * @return an integer value representing the UTC second (0-59)
   */
  int getUtcSecond();

  /**
   * Returns the type of electronic position fixing device.
   *
   * @return an integer value of the position device
   */
  int getTypeOfEPFD();
}
