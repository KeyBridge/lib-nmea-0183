/*
 * Units.java
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
package org.nmea.type;

/**
 * Enumerated supported units of measure.
 *
 * @author Kimmo Tuukkanen
 * @author Key Bridge
 */
public enum UnitType {

  /**
   * Temperature in degrees Celsius (centigrade)
   */
  CELSIUS('C'),
  /**
   * Depth in fathoms.
   * <p>
   * A fathom is a unit of length equal to 6 feet (1.8288 metres), used
   * especially for measuring the depth of water.
   */
  FATHOMS('F'),
  /**
   * Length in feet
   */
  FEET('f'),
  /**
   * Speed in kilometers per hour
   */
  KMH('K'),
  /**
   * Speed in knots (kn).
   * <p>
   * The knot is a unit of speed equal to one nautical mile (1.852 km) per hour,
   * approximately 1.15078 mph.
   */
  KNOT('N'),
  /**
   * Length in meter
   */
  METER('M');

  private final char code;

  private UnitType(char code) {
    this.code = code;
  }

  /**
   * Returns the corresponding char constant.
   *
   * @return Char indicator of enum
   */
  public char getCode() {
    return code;
  }

  /**
   * Get the enum corresponding to specified char.
   *
   * @param code Character code indicator for unit. e.g. C for Celsius, F for
   *             Fathom, etc.
   * @return the enumerated UnitType
   */
  public static UnitType valueOf(char code) {
    for (UnitType u : values()) {
      if (u.getCode() == code) {
        return u;
      }
    }
    throw new IllegalArgumentException("Unrecognized code " + code);
  }
}
