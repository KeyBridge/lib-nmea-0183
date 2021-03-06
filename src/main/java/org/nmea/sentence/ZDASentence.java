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

import java.util.Date;
import org.nmea.parser.DataNotAvailableException;
import org.nmea.parser.ParseException;
import org.nmea.type.Time;

/**
 * UTC time and date with local time zone offset.
 * <p>
 * Example: <br>
 * <code>$GPZDA,032915,07,08,2004,00,00*4D</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface ZDASentence extends TimeSentence, DateSentence {

  /**
   * Get offset to local time zone in hours, from 0 to +/- 13 hours.
   *
   * @return Time zone offset
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  int getLocalZoneHours();

  /**
   * Get offset to local time zone in minutes, from 0 to +/- 59.
   *
   * @return Time zone offset
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  int getLocalZoneMinutes();

  /**
   * Set offset to local time zone in hours.
   *
   * @param hours Offset, from 0 to +/- 13 hours.
   */
  void setLocalZoneHours(int hours);

  /**
   * Set offset to local time zone in minutes.
   *
   * @param minutes Offset, from 0 to +/- 59 minutes.
   */
  void setLocalZoneMinutes(int minutes);

  /**
   * Set time and local time zone hours and minutes.
   *
   * @param t Time to be inserted in sentence.
   */
  void setTimeAndLocalZone(Time t);

  /**
   * Get date and time as {@link java.util.Date}.
   *
   * @return {@link java.util.Date}
   * @throws DataNotAvailableException If any of the date/time values is not
   *                                   available.
   * @throws ParseException            If the any of the date/time fields
   *                                   contains invalid value.
   */
  Date toDate();
}
