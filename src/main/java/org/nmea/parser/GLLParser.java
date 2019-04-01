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

import org.nmea.sentence.GLLSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.DataStatusType;
import org.nmea.type.Position;
import org.nmea.type.Time;

/**
 * GLL Sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class GLLParser extends PositionParser implements GLLSentence {

  // field indices
  private static final int LATITUDE = 0;
  private static final int LAT_HEMISPHERE = 1;
  private static final int LONGITUDE = 2;
  private static final int LON_HEMISPHERE = 3;
  private static final int UTC_TIME = 4;
  private static final int DATA_STATUS = 5;

  /**
   * Creates a new instance of GLLParser.
   *
   * @param nmea GLL sentence String.
   * @throws IllegalArgumentException If the given sentence is invalid or does
   *                                  not contain GLL sentence.
   */
  public GLLParser(String nmea) {
    super(nmea, SentenceType.GLL);
  }

  /**
   * Creates GSA parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public GLLParser(TalkerType talker) {
    super(talker, SentenceType.GLL, 6);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.PositionSentence#getPosition()
   */
  public Position getPosition() {
    return parsePosition(LATITUDE, LAT_HEMISPHERE, LONGITUDE, LON_HEMISPHERE);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GLLSentence#getDataStatus()
   */
  public DataStatusType getStatus() {
    return DataStatusType.valueOf(getCharValue(DATA_STATUS));
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.TimeSentence#getTime()
   */
  public Time getTime() {
    String str = getStringValue(UTC_TIME);
    return new Time(str);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.PositionSentence#setPosition(net.sf.marineapi
   * .nmea.util.Position)
   */
  public void setPosition(Position pos) {
    setPositionValues(
      pos, LATITUDE, LAT_HEMISPHERE, LONGITUDE, LON_HEMISPHERE);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GLLSentence#setDataStatus(net.sf.marineapi
   * .nmea.util.DataStatus)
   */
  public void setStatus(DataStatusType status) {
    setCharValue(DATA_STATUS, status.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.TimeSentence#setTime(org.
   * nmea.util.Time)
   */
  public void setTime(Time t) {
    setStringValue(UTC_TIME, t.toString());
  }
}
