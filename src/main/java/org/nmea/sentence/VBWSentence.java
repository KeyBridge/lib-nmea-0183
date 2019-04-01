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
 * VBW Dual Ground/Water Speed Longitudinal, Transverse and Stern Ground/Water
 * Speed with Status.
 * <p>
 * Example:<br>
 * <code>$IIVBW,11.0,02.0,A,06.0,03.0,A,05.3,A,01.0,A*43</code>
 *
 * @author Jeremy Wilson
 */
public interface VBWSentence extends Sentence {

  /**
   * Returns the Longitudinal Water Speed.
   *
   * @return Longitudinal Water Speed
   */
  double getLongWaterSpeed();

  /**
   * Returns the Longitudinal Ground Speed.
   *
   * @return Longitudinal Ground Speed
   */
  double getLongGroundSpeed();

  /**
   * Returns the Transverse Water Speed.
   *
   * @return Transverse Water Speed
   */
  double getTravWaterSpeed();

  /**
   * Returns the Transverse Ground Speed.
   *
   * @return Transverse Ground Speed
   */
  double getTravGroundSpeed();

  /**
   * Returns the Water Speed Status.
   *
   * @return DataStatus Water Speed Status
   */
  DataStatusType getWaterSpeedStatus();

  /**
   * Returns the Ground Speed Status.
   *
   * @return DataStatus Ground Speed Status
   */
  DataStatusType getGroundSpeedStatus();

  /**
   * Returns the Stern Water Speed.
   *
   * @return Stern Water Speed
   */
  double getSternWaterSpeed();

  /**
   * Returns the Stern Water Speed Status.
   *
   * @return DataStatus Stern Water Speed Status
   */
  DataStatusType getSternWaterSpeedStatus();

  /**
   * Returns the Stern Ground Speed.
   *
   * @return Stern Ground Speed
   * @see #setSternGroundSpeed(double)
   */
  double getSternGroundSpeed();

  /**
   * Returns the Stern Ground Speed Status.
   *
   * @return DataStatus Stern Ground Speed Status
   */
  DataStatusType getSternGroundSpeedStatus();

  /**
   * Sets Longitudinal Water Speed.
   *
   * @param speed Longitudinal Water Speed.
   */
  void setLongWaterSpeed(double speed);

  /**
   * Sets Longitudinal Ground Speed.
   *
   * @param speed Longitudinal Ground Speed.
   */
  void setLongGroundSpeed(double speed);

  /**
   * Sets Transverse Water Speed.
   *
   * @param speed Transverse Water Speed.
   */
  void setTravWaterSpeed(double speed);

  /**
   * Sets Transverse Ground Speed.
   *
   * @param speed Transverse Ground Speed.
   */
  void setTravGroundSpeed(double speed);

  /**
   * Sets Water Speed Status.
   *
   * @param status Water Speed Status
   */
  void setWaterSpeedStatus(DataStatusType status);

  /**
   * Sets Ground Speed Status.
   *
   * @param status Ground Speed Status
   */
  void setGroundSpeedStatus(DataStatusType status);

  /**
   * Sets Stern Water Speed.
   *
   * @param speed Stern Water Speed.
   */
  void setSternWaterSpeed(double speed);

  /**
   * Sets Stern Water Speed Status.
   *
   * @param status Stern Water Speed Status.
   */
  void setSternWaterSpeedStatus(DataStatusType status);

  /**
   * Sets Stern Ground Speed.
   *
   * @param speed Stern Ground Speed.
   */
  void setSternGroundSpeed(double speed);

  /**
   * Sets Stern Ground Speed Status.
   *
   * @param status Stern Ground Speed Status.
   */
  void setSternGroundSpeedStatus(DataStatusType status);

}
