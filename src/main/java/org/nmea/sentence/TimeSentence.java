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

import org.nmea.type.Time;

/**
 * Interface for sentences that provide UTC time. Notice that some sentences
 * contain only UTC time, while others may provide also date.
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.sentence.DateSentence
 */
public interface TimeSentence extends Sentence {

  /**
   * Get the time of day.
   *
   * @return Time
   */
  Time getTime();

  /**
   * Set the time of day.
   *
   * @param t Time to set
   */
  void setTime(Time t);
}
