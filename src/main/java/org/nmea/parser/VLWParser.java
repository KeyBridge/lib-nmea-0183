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

import org.nmea.type.TalkerType;
import org.nmea.sentence.VLWSentence;

/**
 * VLW sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class VLWParser extends SentenceParser implements VLWSentence {

  private static final int TOTAL = 0;
  private static final int TOTAL_UNITS = 1;
  private static final int TRIP = 2;
  private static final int TRIP_UNITS = 3;

  /**
   * Creates a new instance of VLWParser.
   *
   * @param nmea NMEA sentence STring.
   */
  public VLWParser(String nmea) {
    super(nmea);
  }

  /**
   * Creates a new empty instance of VLWParser.
   *
   * @param talker TalkerId to set.
   */
  public VLWParser(TalkerType talker) {
    super(talker, "VLW", 4);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.VLWSentence#getTotal()
   */
  @Override
  public double getTotal() {
    return getDoubleValue(TOTAL);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.VLWSentence#getTotalUnits()
   */
  @Override
  public char getTotalUnits() {
    return getCharValue(TOTAL_UNITS);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.VLWSentence#getTrip()
   */
  @Override
  public double getTrip() {
    return getDoubleValue(TRIP);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.VLWSentence#getTripUnits()
   */
  @Override
  public char getTripUnits() {
    return getCharValue(TRIP_UNITS);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.VLWSentence#setTotal(double)
   */
  @Override
  public void setTotal(double distance) {
    setDoubleValue(TOTAL, distance, 1, 1);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.VLWSentence#setTotalUnits(char)
   */
  @Override
  public void setTotalUnits(char unit) {
    setUnit(TOTAL_UNITS, unit);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.VLWSentence#setTrip(double)
   */
  @Override
  public void setTrip(double distance) {
    setDoubleValue(TRIP, distance, 1, 1);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.VLWSentence#setTripUnits(char)
   */
  @Override
  public void setTripUnits(char unit) {
    setUnit(TRIP_UNITS, unit);
  }

  /**
   * Set and validate unit char.
   *
   * @param index Field index
   * @param unit  Unit char
   */
  private void setUnit(int index, char unit) {
    if (unit != VLWSentence.KM && unit != VLWSentence.NM) {
      throw new IllegalArgumentException(
        "Invalid distance unit, expected 'N' or 'K'");
    }
    setCharValue(index, unit);
  }

}
