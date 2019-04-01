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
 * Data Acquisition types.
 *
 * @author Johan Bergkvist
 * @see org.nmea.sentence.TTMSentence
 */
public enum AcquisitionType {

  AUTO('A'),
  MANUAL('M'),
  REPORTED('R');

  private final char code;

  private AcquisitionType(char ch) {
    this.code = ch;
  }

  /**
   * Returns the corresponding char constant.
   *
   * @return Char indicator for AcquisitionType
   */
  public char getCode() {
    return code;
  }

  /**
   * Get the enum corresponding to specified char.
   *
   * @param code Char indicator for AcquisitionType
   * @return AcquisitionType
   */
  public static AcquisitionType valueOf(char code) {
    for (AcquisitionType d : values()) {
      if (d.getCode() == code) {
        return d;
      }
    }
    throw new IllegalArgumentException("Unrecognized code " + code);
  }
}
