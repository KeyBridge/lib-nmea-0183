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
import org.nmea.type.GpsFixQualityType;
import org.nmea.type.UnitType;

/**
 * Global Positioning System fix data. Current position, time and other fix
 * related data for a GPS receiver.
 * <p>
 * Example:<br>
 * <code>$GPGGA,120044,6011.552,N,02501.941,E,1,00,2.0,28.0,M,19.6,M,,*79</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface GGASentence extends PositionSentence, TimeSentence {

  /**
   * Altitude presented in meters.
   */
  char ALT_UNIT_METERS = 'M';

  /**
   * Altitude presented in feet.
   */
  char ALT_UNIT_FEET = 'f';

  /**
   * Get antenna altitude above mean sea level.
   *
   * @return Altitude value
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getAltitude();

  /**
   * Gets the altitude units, meters or feet.
   *
   * @return Units enum
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  UnitType getAltitudeUnits();

  /**
   * Gets the age of differential GPS data (DGPS).
   *
   * @return Seconds since last valid RTCM transmission
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getDgpsAge();

  /**
   * Gets the ID of DGPS station.
   *
   * @return Station ID (0000-1024)
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  String getDgpsStationId();

  /**
   * Get the GPS fix quality.
   *
   * @return GpsFixQuality enum
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  GpsFixQualityType getFixQuality();

  /**
   * Get height/separation of geoid above WGS84 ellipsoid, i.e. difference
   * between WGS-84 earth ellipsoid and mean sea level. Negative values are
   * below WGS-84 ellipsoid.
   *
   * @return Height value
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getGeoidalHeight();

  /**
   * Get units of height above geoid.
   *
   * @return Units of geoidal height value
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  UnitType getGeoidalHeightUnits();

  /**
   * Get the horizontal dilution of precision (HDOP), i.e. the relative accuracy
   * of horizontal position.
   *
   * @return Horizontal dilution
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  double getHorizontalDOP();

  /**
   * Get the number of active satellites in use.
   *
   * @return Number of satellites
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  int getSatelliteCount();

  /**
   * Set the antenna altitude.
   *
   * @param alt Altitude to set
   */
  void setAltitude(double alt);

  /**
   * Sets the unit of altitude.
   *
   * @param unit Units to set
   */
  void setAltitudeUnits(UnitType unit);

  /**
   * Sets the age of differential GPS data (DGPS).
   *
   * @param age Seconds since last valid RTCM transmission to set.
   */
  void setDgpsAge(double age);

  /**
   * Sets the ID of DGPS station.
   *
   * @param id Station ID to set
   */
  void setDgpsStationId(String id);

  /**
   * Sets the GPS fix quality.
   *
   * @param quality Fix quality to set
   */
  void setFixQuality(GpsFixQualityType quality);

  /**
   * Set height/separation of geoid above WGS84 ellipsoid, i.e. difference
   * between WGS-84 earth ellipsoid and mean sea level. Negative values are
   * below WGS-84 ellipsoid.
   *
   * @param height Height value to set
   */
  void setGeoidalHeight(double height);

  /**
   * Get unit of height above geoid.
   *
   * @param unit Unit to set
   */
  void setGeoidalHeightUnits(UnitType unit);

  /**
   * Set the horizontal dilution of precision (HDOP), i.e. the relative accuracy
   * of horizontal position.
   *
   * @param hdop Horizontal dilution
   */
  void setHorizontalDOP(double hdop);

  /**
   * Sets the number of active satellites in use.
   *
   * @param count Number of satellites to set.
   * @throws IllegalArgumentException If given count is negative.
   */
  void setSatelliteCount(int count);

}
