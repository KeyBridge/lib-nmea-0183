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

import org.nmea.sentence.ROTSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.DataStatusType;

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
    super(nmea, SentenceType.ROT);
  }

  /**
   * Creates a new empty ROT sentence.
   *
   * @param talker Talker id to set
   */
  public ROTParser(TalkerType talker) {
    super(talker, SentenceType.ROT, 2);
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
  public DataStatusType getStatus() {
    return DataStatusType.valueOf(getCharValue(STATUS));
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
  public void setStatus(DataStatusType status) {
    setCharValue(STATUS, status.getCode());
  }
}
