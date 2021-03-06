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

import org.nmea.sentence.MTWSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.UnitType;

/**
 * MTW Sentence parser.
 *
 * @author Warren Zahra, Kimmo Tuukkanen
 */
class MTWParser extends SentenceParser implements MTWSentence {

  private static final int TEMPERATURE = 0;
  private static final int UNIT_INDICATOR = 1;

  /**
   * Creates new instance of MTWParser with specified sentence.
   *
   * @param nmea MTW sentence string
   */
  public MTWParser(String nmea) {
    super(nmea);
  }

  /**
   * Creates new MTW parse without data.
   *
   * @param tid TalkerId to set
   */
  public MTWParser(TalkerType tid) {
    super(tid, SentenceType.MTW, 2);
    setCharValue(UNIT_INDICATOR, UnitType.CELSIUS.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MTWSentence#getTemperature()
   */
  public double getTemperature() {
    return getDoubleValue(TEMPERATURE);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MTWSentence#setTemperature(double)
   */
  public void setTemperature(double temp) {
    setDoubleValue(TEMPERATURE, temp, 1, 2);
  }

}
