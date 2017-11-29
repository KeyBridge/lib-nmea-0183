/*
 * ROTParser.java
 * Copyright (C) 2014 Mike Tamis, Kimmo Tuukkanen
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

import org.nmea.sentence.ROTSentence;
import org.nmea.sentence.SentenceId;
import org.nmea.sentence.TalkerId;
import org.nmea.util.DataStatus;

/**
 * ROT sentence parser.
 *
 * @author Mike Tamis, Kimmo Tuukkanen
 */
class ROTParser extends SentenceParser implements ROTSentence {

  private static final int RATE_OF_TURN = 0;
  private static final int STATUS = 1;

  /**
   * Creates a new ROT parser.
   *
   * @param nmea ROT sentence String to parse.
   */
  public ROTParser(String nmea) {
    super(nmea, SentenceId.ROT);
  }

  /**
   * Creates a new empty ROT sentence.
   *
   * @param talker Talker id to set
   */
  public ROTParser(TalkerId talker) {
    super(talker, SentenceId.ROT, 2);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.parser.RateOfTurnSentance#getRateOfTurn()
   */
  public double getRateOfTurn() {
    return getDoubleValue(RATE_OF_TURN);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.RateOfTurnSentance#getStatus()
   */
  public DataStatus getStatus() {
    return DataStatus.valueOf(getCharValue(STATUS));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.ROTSentence#setRateOfTurn(double)
   */
  public void setRateOfTurn(double rot) {
    setDoubleValue(RATE_OF_TURN, rot, 3, 1);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.ROTSentence#setStatus(net.sf.marineapi
   * .nmea.util.DataStatus)
   */
  public void setStatus(DataStatus status) {
    setCharValue(STATUS, status.toChar());
  }
}
