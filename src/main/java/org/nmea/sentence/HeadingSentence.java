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
 * Interface for sentences that provide vessel's true or magnetic heading.
 *
 * @author Kimmo Tuukkanen
 */
public interface HeadingSentence extends Sentence {

  /**
   * Returns the vessel's current heading.
   *
   * @return Heading in degrees.
   * @see #isTrue()
   */
  double getHeading();

  /**
   * Tells if the heading returned and set by {@link #getHeading()} and
   * {@link #setHeading(double)} methods is <em>true</em> or <em>magnetic</em>
   *
   * @return <code>true</code> if true heading, otherwise <code>false</code> for
   *         magnetic heading.
   */
  boolean isTrue();

  /**
   * Sets the heading value.
   *
   * @param hdt Heading in degrees
   * @see #isTrue()
   * @throws IllegalArgumentException If heading value out of range [0..360]
   */
  void setHeading(double hdt);
}
