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

/**
 * Vessel's rate of turn given in degrees per minute. Negative values indicate
 * bow turning to port.
 * <p>
 * Example:<br><code>$GPROT,35.6,A*4E</code>
 *
 * @author Mike Tamis, Kimmo Tuukkanen
 */
public interface ROTSentence extends Sentence {

  /**
   * Returns the vessel's rate of turn.
   *
   * @return Rate of Turn value (degrees per minute)
   */
  double getRateOfTurn();

  /**
   * Sets the vessel's rate of turn value.
   *
   * @param rot Rate of Turn value to set (degrees per minute)
   */
  void setRateOfTurn(double rot);

  /**
   * Returns the data status (valid/invalid).
   *
   * @return True means data is valid
   */
  DataStatusType getStatus();

  /**
   * Sets the data status.
   *
   * @param status DataStatus to set.
   */
  void setStatus(DataStatusType status);
}
