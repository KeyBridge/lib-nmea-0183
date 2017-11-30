/*
 * TargetStatus.java
 * Copyright (C) 2014 Johan Bergkvist
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
package org.nmea.type;

/**
 * Defines the status of a target reported in a TTM - (Tracked Target Message)
 * sentence. Target Status is one of Lost, Query, Tracking
 *
 * @author Johan Bergkvist
 * @see org.nmea.sentence.TTMSentence
 */
public enum TargetStatusType {

  LOST('L'),
  QUERY('Q'),
  TRACKING('T');

  private final char code;

  private TargetStatusType(char ch) {
    this.code = ch;
  }

  /**
   * Returns the corresponding char constant.
   *
   * @return Char indicator for Status
   */
  public char getCode() {
    return code;
  }

  /**
   * Get the enum corresponding to specified char.
   *
   * @param code Char indicator for Status
   * @return the TargetStatus instance
   */
  public static TargetStatusType valueOf(char code) {
    for (TargetStatusType d : values()) {
      if (d.getCode() == code) {
        return d;
      }
    }
    throw new IllegalArgumentException("Unrecognized code " + code);
  }
}
