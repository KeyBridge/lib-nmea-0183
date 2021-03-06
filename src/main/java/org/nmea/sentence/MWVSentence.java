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
import org.nmea.type.UnitType;

/**
 * Wind speed and angle. Speed in km/h, m/s, or knots. Wind angle is given in
 * degrees relative to bow or true north.
 * <p>
 * Example:<br><code>$IIMWV,199,R,5.9,N,A*2E</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface MWVSentence extends Sentence {

  /**
   * Get wind angle.
   *
   * @return Wind angle in degrees.
   */
  double getAngle();

  /**
   * Returns the wind speed.
   *
   * @return Wind speed value
   */
  double getSpeed();

  /**
   * Returns the wind speed unit.
   *
   * @return {@link UnitType#METER} for meters per second, {@link UnitType#KMH}
   *         for kilometers per hour and {@link UnitType#KNOT} for knots.
   */
  UnitType getSpeedUnit();

  /**
   * Get data validity status.
   *
   * @return Data status
   */
  DataStatusType getStatus();

  /**
   * Tells if the angle is relative or true.
   *
   * @return True if relative to true north, otherwise false (relative to bow)
   */
  boolean isTrue();

  /**
   * Set wind angle.
   *
   * @param angle Wind angle in degrees.
   * @see #setTrue(boolean)
   */
  void setAngle(double angle);

  /**
   * Set the wind speed value.
   *
   * @param speed Wind speed to set.
   */
  void setSpeed(double speed);

  /**
   * Set wind speed unit.
   *
   * @param unit {@link UnitType#METER} for meters per second,
   *             {@link UnitType#KMH} for kilometers per hour and
   *             {@link UnitType#KNOT} for knots.
   * @throws IllegalArgumentException If trying to set invalid unit
   */
  void setSpeedUnit(UnitType unit);

  /**
   * Set data validity status.
   *
   * @param status Data status to set.
   */
  void setStatus(DataStatusType status);

  /**
   * Set angle to relative or true.
   *
   * @param isTrue True for true angle, false for relative to bow.
   * @see #setAngle(double)
   */
  void setTrue(boolean isTrue);
}
