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

import org.nmea.sentence.MMBSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;

/**
 * MMBParser - Barometer.
 * <p>
 * $--MMB,x.x,I,x.x,B*hh<CR><LF>
 *
 * @author Kimmo Tuukkanen
 */
class MMBParser extends SentenceParser implements MMBSentence {

  private static final int PRESSURE_INHG = 0;
  private static final int UNIT_INHG = 1;
  private static final int PRESSURE_BARS = 2;
  private static final int UNIT_BARS = 3;

  /**
   * Constructor for parsing MMB.
   *
   * @param nmea MMB sentence String.
   */
  public MMBParser(String nmea) {
    super(nmea, SentenceType.MMB);
  }

  /**
   * Constructs a fresh MMB parser.
   *
   * @param tid TalkerId to use in sentence.
   */
  public MMBParser(TalkerType tid) {
    super(tid, SentenceType.MMB, 4);
    setCharValue(UNIT_INHG, 'I');
    setCharValue(UNIT_BARS, 'B');
  }

  @Override
  public double getInchesOfMercury() {
    return getDoubleValue(PRESSURE_INHG);
  }

  @Override
  public double getBars() {
    return getDoubleValue(PRESSURE_BARS);
  }

  @Override
  public void setInchesOfMercury(double inHg) {
    setDoubleValue(PRESSURE_INHG, inHg);
  }

  @Override
  public void setBars(double bars) {
    setDoubleValue(PRESSURE_BARS, bars);
  }
}
