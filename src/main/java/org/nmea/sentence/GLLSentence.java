/*
 * GLLSentence.java
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

import org.nmea.parser.DataNotAvailableException;
import org.nmea.parser.ParseException;
import org.nmea.type.DataStatusType;

/**
 * Current geographic position and time.
 * <p>
 * Example: <br>
 * <code>$GPGLL,6011.552,N,02501.941,E,120045,A*26</code>
 *
 * @author Kimmo Tuukkanen
 */
public interface GLLSentence extends PositionSentence, TimeSentence {

  /**
   * Get the data quality status, valid or invalid.
   *
   * @return {@link DataStatusType#ACTIVE} or {@link DataStatusType#VOID}
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  DataStatusType getStatus();

  /**
   * Set the data quality status, valid or invalid.
   *
   * @param status DataStatus to set
   * @throws DataNotAvailableException If the data is not available.
   * @throws ParseException            If the field contains unexpected or
   *                                   illegal value.
   */
  void setStatus(DataStatusType status);

}
