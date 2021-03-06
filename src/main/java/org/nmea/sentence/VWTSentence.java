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

import org.nmea.parser.DataNotAvailableException;
import org.nmea.parser.ParseException;
import org.nmea.type.DirectionType;

/**
 * True Wind Speed and Angle True wind angle in relation to the vessel's heading
 * and true wind speed referenced to the water. Speed in Knots, Meters Per
 * Second and Kilometers Per Hour
 * <p>
 * Example: <br>
 * <code>$--VWT,x.x,a,x.x,N,x.x,M,x.x,K*hh-</code>
 *
 * @author Henri Laurent
 */
public interface VWTSentence extends Sentence {

  /**
   * Units indicator for meters per second
   */
  char MPS = 'M';
  /**
   * Units indicator for kilometers per hour
   */
  char KMPH = 'K';
  /**
   * Units indicator for knots (nautical miles per hour)
   */
  char KNOT = 'N';

  /**
   * Get the Wind angle magnitude in degrees
   *
   * @return Wind angle
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getWindAngle();

  /**
   * Get the Wind angle Left/Right of bow
   *
   * @since NMEA 2.3
   * @return {@link DirectionType} enum
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  DirectionType getDirectionLeftRight();

  /**
   * Get relative wind speed, in kilometers per hour.
   *
   * @return Speed in km/h
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getSpeedKmh();

  /**
   * Get relative wind speed in knots.
   *
   * @return Speed in knots (nautical miles per hour)
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getSpeedKnots();

  /**
   * Set the Wind angle magnitude
   *
   * @param mWindAngle Wind angle magnitude in degrees.
   */
  void setWindAngle(double mWindAngle);

  /**
   * Set the Wind angle Left/Right of bow
   *
   * @param direction Direction to set
   * @since NMEA 2.3
   */
  void setDirectionLeftRight(DirectionType direction);

  /**
   * Set the relative wind speed in kmh
   *
   * @param kmh Speed in kilometers per hour (km/h).
   */
  void setSpeedKmh(double kmh);

  /**
   * Set the relative wind speed in knots.
   *
   * @param knots Speed in knots (nautical miles per hour)
   */
  void setSpeedKnots(double knots);
}
