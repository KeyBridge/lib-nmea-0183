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
import org.nmea.type.CompassPointType;
import org.nmea.type.DataStatusType;
import org.nmea.type.FaaModeType;

/**
 * Recommended minimum navigation information type C. Current time and date,
 * position, speed over ground, true course over ground and magnetic variation.
 * <p>
 * Example:<br>
 * <code>$GPRMC,120044,A,6011.552,N,02501.941,E,000.0,360.0,160705,006.1,E*7C</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface RMCSentence extends PositionSentence, TimeSentence,
                                     DateSentence {

  /**
   * Get the corrected course over ground. Correction is done by subtracting or
   * adding the magnetic variation from true course (easterly variation
   * subtracted and westerly added).
   *
   * @return Corrected true course
   * @see #getCourse()
   * @see #getVariation()
   * @throws DataNotAvailableException If course or variation data is not
   *                                   available.
   * @throws ParseException            If course or variation field contains
   *                                   unexpected or illegal value.
   */
  double getCorrectedCourse();

  /**
   * Get true course over ground (COG).
   *
   * @return True course in degrees
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getCourse();

  /**
   * Get the direction of magnetic variation; east or west.
   *
   * @return Direction.EAST or Direction.WEST
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  CompassPointType getDirectionOfVariation();

  /**
   * Get the FAA operating mode for GPS.
   *
   * @return FaaMode enum
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  FaaModeType getMode();

  /**
   * Get current speed over ground (SOG).
   *
   * @return Speed in knots (nautical miles per hour).
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getSpeed();

  /**
   * Gets the data status, valid or invalid.
   *
   * @return {@link DataStatusType#ACTIVE} or {@link DataStatusType#VOID}
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  DataStatusType getStatus();

  /**
   * Get the magnetic variation. Easterly variation subtracts from true course,
   * and is thus returned as negative value. Otherwise, the value is positive.
   *
   * @return Magnetic variation in degrees
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getVariation();

  /**
   * Set true course over ground (COG).
   *
   * @param cog True course in degrees
   */
  void setCourse(double cog);

  /**
   * Set the direction of magnetic variation, east or west.
   *
   * @param dir {@link CompassPointType#EAST} or {@link CompassPointType#WEST}
   * @throws IllegalArgumentException If specified Direction is other than
   *                                  defined as valid for param
   *                                  <code>dir</code>.
   */
  void setDirectionOfVariation(CompassPointType dir);

  /**
   * Set the FAA operation mode of GPS.
   *
   * @param mode Mode to set
   */
  void setMode(FaaModeType mode);

  /**
   * Set current speed over ground (SOG).
   *
   * @param sog Speed in knots (nautical miles per hour).
   */
  void setSpeed(double sog);

  /**
   * Set the data status, valid or invalid.
   *
   * @param status {@link DataStatusType#ACTIVE} or {@link DataStatusType#VOID}
   */
  void setStatus(DataStatusType status);

  /**
   * Set the magnetic variation.
   *
   * @param var Magnetic variation in degrees
   */
  void setVariation(double var);
}
