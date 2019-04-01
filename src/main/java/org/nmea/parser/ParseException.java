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
package org.nmea.parser;

/**
 * Thrown to indicate that parser is unable interpret the contents of requested
 * data field. For example, when a field contains invalid value that cannot be
 * parsed to expected native data type.
 *
 * @author Kimmo Tuukkanen
 */
public class ParseException extends DataNotAvailableException {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 6203761984607273569L;

  /**
   * Constructor with description.
   *
   * @param msg Description of the Exception
   */
  public ParseException(String msg) {
    super(msg);
  }

  /**
   * Constructor with message and cause.
   *
   * @param msg   Description of the Exception
   * @param cause Throwable that caused this exception
   */
  public ParseException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
