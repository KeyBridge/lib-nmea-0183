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
 * Thrown to indicate that requested data is not available. For example, when
 * invoking a getter for sentence data field that contains no value.
 *
 * @author Kimmo Tuukkanen
 */
public class DataNotAvailableException extends RuntimeException {

  private static final long serialVersionUID = -3672061046826633631L;

  /**
   * Constructor
   *
   * @param msg Exception message
   */
  public DataNotAvailableException(String msg) {
    super(msg);
  }

  /**
   * Constructor
   *
   * @param msg   Exception message
   * @param cause Throwable that caused the exception
   */
  public DataNotAvailableException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
