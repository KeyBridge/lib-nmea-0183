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

/**
 * Set and drift, true/magnetic direction and speed of current.
 * <p>
 * Example:<br>
 * <code></code>
 *
 * @author Kimmo Tuukkanen
 */
public interface VDRSentence extends Sentence {

  /**
   * Returns the magnetic current direction.
   *
   * @return Direction in degrees
   * @see #getTrueDirection()
   */
  double getMagneticDirection();

  /**
   * Returns the current flow speed.
   *
   * @return Speed in knots
   */
  double getSpeed();

  /**
   * Returns the true direction of current.
   *
   * @return Direction in degrees
   * @see #getMagneticDirection()
   */
  double getTrueDirection();

  /**
   * Sets the magnetic direction of current.
   *
   * @param direction
   */
  void setMagneticDirection(double direction);

  /**
   * Sets the current flow speed.
   *
   * @param speed Speed in knots
   */
  void setSpeed(double speed);

  /**
   * Sets the true direction of current.
   *
   * @param direction Direction in degrees
   * @see #setMagneticDirection(double)
   */
  void setTrueDirection(double direction);

}
