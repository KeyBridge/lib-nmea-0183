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

import org.nmea.sentence.DPTSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;

/**
 * DPT sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class DPTParser extends SentenceParser implements DPTSentence {

  private static final int DEPTH = 0;
  private static final int OFFSET = 1;
  private static final int MAXIMUM = 2;

  /**
   * Creates a new instance of DPTParser.
   *
   * @param nmea DPT sentence String
   */
  public DPTParser(String nmea) {
    super(nmea, SentenceType.DPT);
  }

  /**
   * Creates a new instance of DPTParser with empty data fields.
   *
   * @param talker TalkerId to set
   */
  public DPTParser(TalkerType talker) {
    super(talker, SentenceType.DPT, 3);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.DepthSentence#getDepth()
   */
  public double getDepth() {
    return getDoubleValue(DEPTH);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.DPTSentence#getOffset()
   */
  public double getOffset() {
    return getDoubleValue(OFFSET);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.DepthSentence#setDepth(double)
   */
  public void setDepth(double depth) {
    setDoubleValue(DEPTH, depth, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.DPTSentence#setOffset(double)
   */
  public void setOffset(double offset) {
    setDoubleValue(OFFSET, offset, 1, 1);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.DPTSentence#getMaximum()
   */
  public double getMaximum() {
    return getDoubleValue(MAXIMUM);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.DPTSentence#setMaximum(int)
   */
  public void setMaximum(double max) {
    setDoubleValue(MAXIMUM, max);
  }

}
