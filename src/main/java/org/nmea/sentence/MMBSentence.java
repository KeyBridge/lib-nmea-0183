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
 * Barometer - Barometric pressure in bars and inches of mercury.
 * <p>
 * Example:<br>
 * <code>$IIMMB,29.9870,I,1.0154,B*75</code>
 *
 * @author Kimmo Tuukkanen
 * @deprecated Notice: not recommended as of Oct 2008, should use
 * <code>XDR</code> instead.
 */
public interface MMBSentence extends Sentence {

  /**
   * Returns the barometric pressure in inches of mercury.
   *
   * @return Barometric pressure, inHg.
   */
  double getInchesOfMercury();

  /**
   * Returns the barometric pressure in bars.
   *
   * @return Barometric pressure, bars.
   */
  double getBars();

  /**
   * Sets the barometric pressure in inches of mercury.
   *
   * @param inhg Barometric pressure, inHg.
   */
  void setInchesOfMercury(double inhg);

  /**
   * Sets the barometric pressure in bars.
   *
   * @param bars Barometric pressure, bars.
   */
  void setBars(double bars);

}
