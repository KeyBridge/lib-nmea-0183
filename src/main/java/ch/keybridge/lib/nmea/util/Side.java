/*
 * Side.java
 * Copyright (C) 2014 Lázár József
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
package ch.keybridge.lib.nmea.util;

/**
 * Defines the sides of a boat, i.e. "port" and "starboard".
 *
 * @author Lázár József
 */
public enum Side {

  /**
   * Port
   */
  PORT('P'),
  /**
   * Right
   */
  STARBOARD('S');

  private char ch;

  private Side(char c) {
    ch = c;
  }

  /**
   * Returns the corresponding char constant.
   *
   * @return Char indicator for Direction
   */
  public char toChar() {
    return ch;
  }

  /**
   * Get the enum corresponding to specified char.
   *
   * @param c Char indicator for Side
   * @return Side
   */
  public static Side valueOf(char c) {
    for (Side d : values()) {
      if (d.toChar() == c) {
        return d;
      }
    }
    return valueOf(String.valueOf(c));
  }
}
