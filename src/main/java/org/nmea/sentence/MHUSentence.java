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
 * Relative and absolute humidity with dew point.
 * <p>
 * <em>Notice: not recommended as of Oct 2008, should use <code>XDR</code>
 * instead.</em>
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.sentence.XDRSentence
 */
public interface MHUSentence extends Sentence {

  /**
   * Returns the humidity relative to temperature of air.
   *
   * @return Relative humitidy, percent.
   */
  double getRelativeHumidity();

  /**
   * Returns the absolute humidity value.
   *
   * @return Absolute humidity, g/m³.
   */
  double getAbsoluteHumidity();

  /**
   * Returns the dew point value.
   *
   * @return Dew point, degrees Celcius.
   * @see #getDewPointUnit()
   */
  double getDewPoint();

  /**
   * Returns the unit of dew point temperature, by default degrees Celsius.
   *
   * @return Temperature unit char, defaults to <code>'c'</code>.
   */
  char getDewPointUnit();

  /**
   * Returns the relative humidity.
   *
   * @param humidity Relative humidity, percent.
   */
  void setRelativeHumidity(double humidity);

  /**
   * Returns the absolute humidity value.
   *
   * @param humidity Absolute humidity, percent.
   */
  void setAbsoluteHumidity(double humidity);

  /**
   * Sets the dew point value.
   *
   * @param dewPoint Dew point in degrees Celcius.
   */
  void setDewPoint(double dewPoint);

  /**
   * Sets the unit of dew point temperature, by default degrees Celsius.
   *
   * @param unit Temperature unit char, defaults to <code>'c'</code>.
   */
  void setDewPointUnit(char unit);
}
