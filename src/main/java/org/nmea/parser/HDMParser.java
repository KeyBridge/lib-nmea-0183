/*
 * HDMParser.java
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

import org.nmea.sentence.HDMSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;

/**
 * HDM sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class HDMParser extends SentenceParser implements HDMSentence {

  private static final int HEADING = 0;
  private static final int MAGN_INDICATOR = 1;

  /**
   * Creates a new HDM parser.
   *
   * @param nmea HDM sentence String
   */
  public HDMParser(String nmea) {
    super(nmea, SentenceType.HDM);
  }

  /**
   * Creates a new empty HDM sentence.
   *
   * @param talker Talker id to set
   */
  public HDMParser(TalkerType talker) {
    super(talker, SentenceType.HDM, 2);
    setCharValue(MAGN_INDICATOR, 'M');
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.HDMSentence#getHeading()
   */
  public double getHeading() {
    return getDoubleValue(HEADING);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.HeadingSentence#isTrue()
   */
  public boolean isTrue() {
    return false;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.HDMSentence#setHeading(double)
   */
  public void setHeading(double hdm) {
    setDegreesValue(HEADING, hdm);
  }
}
