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

import org.nmea.sentence.MHUSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;

/**
 * MHUParser - Humidity & dew point.
 * <p>
 * $--MHU,x.x,x.x,x.x,C*hh<CR><LF>
 *
 * @author Kimmo Tuukkanen
 */
class MHUParser extends SentenceParser implements MHUSentence {

  private static final int RELATIVE_HUMIDITY = 0;
  private static final int ABSOLUTE_HUMIDITY = 1;
  private static final int DEW_POINT = 2;
  private static final int DEW_POINT_UNIT = 3;

  /**
   * Constructor for parsing MHU sentence.
   *
   * @param nmea MHU sentence String
   */
  public MHUParser(String nmea) {
    super(nmea, SentenceType.MHU);
  }

  /**
   * Constructor for fresh MHU sentence.
   *
   * @param tid Talker ID to be used.
   */
  public MHUParser(TalkerType tid) {
    super(tid, SentenceType.MHU, 4);
    setDewPointUnit('C');
  }

  @Override
  public double getRelativeHumidity() {
    return getDoubleValue(RELATIVE_HUMIDITY);
  }

  @Override
  public double getAbsoluteHumidity() {
    return getDoubleValue(ABSOLUTE_HUMIDITY);
  }

  @Override
  public double getDewPoint() {
    return getDoubleValue(DEW_POINT);
  }

  @Override
  public char getDewPointUnit() {
    return getCharValue(DEW_POINT_UNIT);
  }

  @Override
  public void setRelativeHumidity(double humidity) {
    setDoubleValue(RELATIVE_HUMIDITY, humidity, 1, 1);
  }

  @Override
  public void setAbsoluteHumidity(double humidity) {
    setDoubleValue(ABSOLUTE_HUMIDITY, humidity, 1, 1);
  }

  @Override
  public void setDewPoint(double dewPoint) {
    setDoubleValue(DEW_POINT, dewPoint, 1, 1);
  }

  @Override
  public void setDewPointUnit(char unit) {
    setCharValue(DEW_POINT_UNIT, unit);
  }
}
