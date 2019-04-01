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
package org.nmea.type;

/**
 * DataStatus defines the validity of data being broadcasted by an NMEA device.
 *
 * @author Kimmo Tuukkanen
 */
public enum DataStatusType {

  /**
   * Valid data available. May also indicate boolean value <code>true</code>.
   */
  ACTIVE('A'),
  /**
   * No valid data available. May also indicate boolean value
   * <code>false</code>.
   */
  VOID('V');

  private final char code;

  private DataStatusType(char ch) {
    code = ch;
  }

  /**
   * Returns the character used in NMEA sentences to indicate the status.
   *
   * @return Char indicator for DataStatus
   */
  public char getCode() {
    return code;
  }

  /**
   * Returns the DataStatus enum for status char used in sentences.
   *
   * @param code Status char
   * @return DataStatus
   */
  public static DataStatusType valueOf(char code) {
    for (DataStatusType ds : values()) {
      if (ds.getCode() == code) {
        return ds;
      }
    }
    throw new IllegalArgumentException("Unrecognized code " + code);
  }
}
