/*
 * DBTParser.java
 * Copyright (C) 2011 Kimmo Tuukkanen
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
package org.nmea.parser;

import org.nmea.sentence.DBTSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.UnitType;

/**
 * DBT sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class DBTParser extends SentenceParser implements DBTSentence {

  // TODO calculate value for all units when setting a value
  private static final int DEPTH_FEET = 0;
  private static final int FEET = 1;
  private static final int DEPTH_METERS = 2;
  private static final int METERS = 3;
  private static final int DEPTH_FATHOMS = 4;
  private static final int FATHOMS = 5;

  /**
   * Creates a new instance of DBTParser.
   *
   * @param nmea
   */
  public DBTParser(String nmea) {
    super(nmea, SentenceType.DBT);
  }

  /**
   * Creates a new instance of DBTParser with empty data fields.
   *
   * @param talker TalkerId to set
   */
  public DBTParser(TalkerType talker) {
    super(talker, SentenceType.DBT, 6);
    setCharValue(FEET, UnitType.FEET.getCode());
    setCharValue(METERS, UnitType.METER.getCode());
    setCharValue(FATHOMS, UnitType.FATHOMS.getCode());
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.DBTSentence#getMeters()
   */
  public double getDepth() {
    return getDoubleValue(DEPTH_METERS);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.DBTSentence#getFathoms()
   */
  public double getFathoms() {
    return getDoubleValue(DEPTH_FATHOMS);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.DBTSentence#getFeet()
   */
  public double getFeet() {
    return getDoubleValue(DEPTH_FEET);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.DBTSentence#setMeters(double)
   */
  public void setDepth(double depth) {
    setDoubleValue(DEPTH_METERS, depth, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.DBTSentence#setFathoms(double)
   */
  public void setFathoms(double depth) {
    setDoubleValue(DEPTH_FATHOMS, depth, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.DBTSentence#setFeet(double)
   */
  public void setFeet(double depth) {
    setDoubleValue(DEPTH_FEET, depth, 1, 1);
  }
}
