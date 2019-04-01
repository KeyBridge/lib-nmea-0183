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
package org.nmea.ais.util;

/**
 * Class holding information about a violation against an AIS rule.
 *
 * @author Lázár József
 */
public class AISRuleViolation implements Violation {

  private final String fPlaceOfViolation;
  private final Object fCurrentValue;
  private final String fValidRange;

  public AISRuleViolation(String where, Object value, String range) {
    fPlaceOfViolation = where;
    fCurrentValue = value;
    fValidRange = range;
  }

  public String toString() {
    return "Violation: Value " + fCurrentValue.toString()
      + " in " + fPlaceOfViolation
      + " is outside the allowed range (" + fValidRange + ")";
  }
}
