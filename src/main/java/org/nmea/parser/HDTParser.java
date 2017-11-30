/*
 * HDTParser.java
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

import org.nmea.sentence.HDTSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;

/**
 * HDT sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class HDTParser extends SentenceParser implements HDTSentence {

  private static final int HEADING = 0;
  private static final int TRUE_INDICATOR = 1;

  /**
   * Creates a new HDT parser.
   *
   * @param nmea HDT sentence String to parse.
   */
  public HDTParser(String nmea) {
    super(nmea, SentenceType.HDT);
  }

  /**
   * Creates a new empty HDT sentence.
   *
   * @param talker Talker id to set
   */
  public HDTParser(TalkerType talker) {
    super(talker, SentenceType.HDT, 2);
    setCharValue(TRUE_INDICATOR, 'T');
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.parser.HeadingSentence#getHeading()
   */
  public double getHeading() {
    return getDoubleValue(HEADING);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.HeadingSentence#isTrue()
   */
  public boolean isTrue() {
    return true;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.parser.HeadingSentence#setHeading(double)
   */
  public void setHeading(double hdt) {
    setDegreesValue(HEADING, hdt);
  }
}
