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
 * Water speed and heading in respect to true and magnetic north.
 * <p>
 * Example:<br><code>$IIVHW,,,213,M,0.00,N,,K*2F</code>
 *
 * @author Warren Zahra, Kimmo Tuukkanen
 */
public interface VHWSentence extends HeadingSentence {

  /**
   * Returns the current magnetic heading.
   *
   * @return Heading in degrees magnetic.
   */
  double getMagneticHeading();

  /**
   * Returns the current water speed.
   *
   * @return Speed in km/h (kilmetres per hour)
   */
  double getSpeedKmh();

  /**
   * Returns the current water speed.
   *
   * @return Speed in knots (nautical miles per hour)
   */
  double getSpeedKnots();

  /**
   * Sets the magnetic heading.
   *
   * @param hdg Heading in degrees magnetic.
   * @throws IllegalArgumentException If value is out of bounds [0..360]
   */
  void setMagneticHeading(double hdg);

  /**
   * Sets the water speed in km/h.
   *
   * @param kmh Speed in kilmetres per hour.
   */
  void setSpeedKmh(double kmh);

  /**
   * Sets the water speed in knots.
   *
   * @param knots Speed in knots (nautical miles per hour)
   */
  void setSpeedKnots(double knots);
}
