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

import org.nmea.type.FaaModeType;
import org.nmea.type.GpsFixStatusType;

/**
 * Precision of GPS fix and list of active satellites. Dilution of precision
 * (DOP) is an indication of the effect of satellite geometry on the accuracy of
 * the fix. It is a unitless number where smaller is better.
 * <p>
 * Example:<br>
 * <code>$GPGSA,A,3,02,,,07,,09,24,26,,,,,1.6,1.6,1.0*3D</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface GSASentence extends Sentence {

  /**
   * Get the GPS fix mode; 2D, 3D or no fix.
   *
   * @return GpsFixStatus enum
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  GpsFixStatusType getFixStatus();

  /**
   * Get the horizontal dilution Of precision (HDOP).
   *
   * @return double
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getHorizontalDOP();

  /**
   * Get the FAA operation mode of GPS.
   *
   * @return FaaMode enum
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  FaaModeType getMode();

  /**
   * Get the dilution of precision (PDOP) for position.
   *
   * @return double
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getPositionDOP();

  /**
   * Get list of satellites used for acquiring the GPS fix.
   *
   * @return String array containing satellite IDs.
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  String[] getSatelliteIds();

  /**
   * Get the vertical dilution of precision (VDOP).
   *
   * @return double
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getVerticalDOP();

  /**
   * Set the GPS fix mode; 2D, 3D or no fix.
   *
   * @param status Status to set
   */
  void setFixStatus(GpsFixStatusType status);

  /**
   * Set the horizontal dilution of precision (HDOP).
   *
   * @param hdop Precision value to set
   */
  void setHorizontalDOP(double hdop);

  /**
   * Set the FAA operation mode of GPS.
   *
   * @param mode Mode to set
   */
  void setMode(FaaModeType mode);

  /**
   * Set the dilution of precision for position.
   *
   * @param pdop Precision value to set
   */
  void setPositionDOP(double pdop);

  /**
   * Set list of satellites used for acquiring the GPS fix.
   *
   * @param ids List of satellite IDs, maximum length of array is 12.
   */
  void setSatelliteIds(String[] ids);

  /**
   * Set the vertical dilution of precision (VDOP).
   *
   * @param vdop Precision value to set
   */
  void setVerticalDOP(double vdop);
}
