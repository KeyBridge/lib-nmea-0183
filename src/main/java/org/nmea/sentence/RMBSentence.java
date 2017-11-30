/*
 * RMBSentence.java
 * Copyright (C) 2010 Kimmo Tuukkanen
 *
 * This file is part of Java Marine API.
 * <http://ktuukkan.github.io/marine-api/>
 *
 * Java Marine API is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Java Marine API is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java Marine API. If not, see <http://www.gnu.org/licenses/>.
 */
package org.nmea.sentence;

import com.sun.javafx.scene.traversal.Direction;
import org.nmea.parser.DataNotAvailableException;
import org.nmea.parser.ParseException;
import org.nmea.type.DataStatusType;
import org.nmea.type.DirectionType;
import org.nmea.type.Waypoint;

/**
 * Recommended minimum navigation information. This sentence is transmitted by a
 * GPS receiver when a destination waypoint is active (GOTO mode).
 * <p>
 * Example:<br>
 * <code>$GPRMB,A,0.00,R,,RUSKI,5536.200,N,01436.500,E,432.3,234.9,,V*58</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface RMBSentence extends Sentence {

  /**
   * Get the arrival to waypoint status. Status is {@link DataStatusType#VOID}
   * (false) while not arrived at destination, otherwise
   * {@link DataStatusType#ACTIVE} (true).
   *
   * @return {@link DataStatusType#ACTIVE} or {@link DataStatusType#VOID}
   * @see #hasArrived()
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  DataStatusType getArrivalStatus();

  /**
   * Get true bearing to destination.
   *
   * @return True bearing in degrees.
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getBearing();

  /**
   * Get cross track error (XTE).
   *
   * @return Cross track error, in nautical miles.
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getCrossTrackError();

  /**
   * Get the destination waypoint.
   *
   * @return Waypoint
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  Waypoint getDestination();

  /**
   * Get the ID of origin waypoint.
   *
   * @return Id String.
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  String getOriginId();

  /**
   * Get range to destination waypoint.
   *
   * @return Range to destination, in nautical miles.
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getRange();

  /**
   * Get the sentence data status, valid or invalid.
   *
   * @return {@link DataStatusType#ACTIVE} or {@link DataStatusType#VOID}
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  DataStatusType getStatus();

  /**
   * Get the direction to steer to correct error (left/right).
   *
   * @return Direction.LEFT or Direction.RIGHT
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  DirectionType getSteerTo();

  /**
   * Get velocity towards destination. Notice that returned value may also be
   * negative if vehicle is moving away from destination.
   *
   * @return Velocity value, in knots (nautical miles per hour).
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getVelocity();

  /**
   * Tells if the destination waypoint has been reached or not.
   *
   * @return True if has arrived to waypoint, otherwise false.
   * @throws DataNotAvailableException If arrival status is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  boolean hasArrived();

  /**
   * Set the arrival to waypoint status. Set {@link DataStatusType#VOID} if not
   * arrived at destination, otherwise {@link DataStatusType#ACTIVE}.
   *
   * @param status {@link DataStatusType#VOID} or {@link DataStatusType#ACTIVE}.
   * @throws IllegalArgumentException If status is <code>null</code>.
   */
  void setArrivalStatus(DataStatusType status);

  /**
   * Set true bearing to destination, in degrees.
   *
   * @param bearing Bearing value, will be rounded to one decimal.
   * @throws IllegalArgumentException If bearing value is out of bounds 0..360
   *                                  degrees.
   */
  void setBearing(double bearing);

  /**
   * Set cross track error (XTE), in nautical miles. Negative values are
   * translated to positive, set Steer-To to indicate the direction of error.
   *
   * @param xte Cross track error value, will be rounded to one decimal.
   * @see #setSteerTo(Direction)
   */
  void setCrossTrackError(double xte);

  /**
   * Set the destination waypoint.
   *
   * @param dest Waypoint to set
   */
  void setDestination(Waypoint dest);

  /**
   * Set the ID of origin waypoint.
   *
   * @param id ID to set
   */
  void setOriginId(String id);

  /**
   * Set range to destination waypoint.
   *
   * @param range Range value, in nautical miles.
   */
  void setRange(double range);

  /**
   * Set status of sentence data, valid or invalid.
   *
   * @param status {@link DataStatusType#ACTIVE} or {@link DataStatusType#VOID}
   */
  void setStatus(DataStatusType status);

  /**
   * Set the direction to steer to correct error (left/right).
   *
   * @param steerTo {@link DirectionType#LEFT} or {@link DirectionType#RIGHT}
   * @throws IllegalArgumentException If specified direction is any other than
   *                                  defined valid for param
   *                                  <code>steer</code>.
   */
  void setSteerTo(DirectionType steerTo);

  /**
   * Set velocity towards destination. Notice that value may also be negative if
   * vehicle is moving away from the destination.
   *
   * @param velocity Velocity, in knots (nautical miles per hour).
   */
  void setVelocity(double velocity);

}
