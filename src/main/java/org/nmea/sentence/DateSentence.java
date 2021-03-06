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
import org.nmea.type.Date;

/**
 * Sentences that contains date information. Notice that some sentences may
 * contain only time without the date.
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.sentence.TimeSentence
 */
public interface DateSentence extends Sentence {

  /**
   * Parses the date information from sentence fields and returns a
   * {@link Date}.
   *
   * @return Date object
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  Date getDate();

  /**
   * Set date. Depending on the sentence type, the values may be inserted to
   * multiple fields or combined into one. Four-digit year value may also be
   * reduced into two-digit format.
   *
   * @param date {@link Date}
   */
  void setDate(Date date);
}
