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
 * Common base interface of AIS messages.
 *
 * @author Kimmo Tuukkanen
 */
public interface AISMessage {

  /**
   * Returns the message type.
   *
   * @return Message type in range from 1 to 27.
   */
  int getMessageType();

  /**
   * Tells how many times the message has been repeated.
   *
   * @return the number of repeats
   */
  int getRepeatIndicator();

  /**
   * Returns the unique identifier of the transmitting ship (MMSI, Maritime
   * Mobile Service Identity).
   *
   * @return MMSI identifier
   */
  int getMMSI();
}
