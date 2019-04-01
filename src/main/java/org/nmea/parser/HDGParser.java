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

import org.nmea.sentence.HDGSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.CompassPointType;

/**
 * HDG sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class HDGParser extends SentenceParser implements HDGSentence {

  private static final int HEADING = 0;
  private static final int DEVIATION = 1;
  private static final int DEV_DIRECTION = 2;
  private static final int VARIATION = 3;
  private static final int VAR_DIRECTION = 4;

  /**
   * Creates a new HDG parser.
   *
   * @param nmea HDG sentence String
   */
  public HDGParser(String nmea) {
    super(nmea, SentenceType.HDG);
  }

  /**
   * Creates a new empty HDG parser.
   *
   * @param talker Talker id to set
   */
  public HDGParser(TalkerType talker) {
    super(talker, SentenceType.HDG, 5);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.HDGSentence#getDeviation()
   */
  public double getDeviation() {
    double dev = getDoubleValue(DEVIATION);
    if (dev == 0) {
      return dev;
    }
    CompassPointType dir = CompassPointType.valueOf(getCharValue(DEV_DIRECTION));
    return dir == CompassPointType.WEST ? -dev : dev;
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.HDGSentence#getHeading()
   */
  public double getHeading() {
    return getDoubleValue(HEADING);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.HDGSentence#getVariation()
   */
  public double getVariation() {
    double var = getDoubleValue(VARIATION);
    if (var == 0) {
      return var;
    }
    CompassPointType dir = CompassPointType.valueOf(getCharValue(VAR_DIRECTION));
    return dir == CompassPointType.WEST ? -var : var;
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.HeadingSentence#isTrue()
   */
  public boolean isTrue() {
    return false;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.HDGSentence#setDeviation(double)
   */
  public void setDeviation(double deviation) {
    if (deviation < -180 || deviation > 180) {
      throw new IllegalArgumentException("Value out of range [-180..180]");
    }
    if (deviation > 0) {
      setCharValue(DEV_DIRECTION, CompassPointType.EAST.getCode());
    } else if (deviation < 0) {
      setCharValue(DEV_DIRECTION, CompassPointType.WEST.getCode());
    } else {
      setStringValue(DEV_DIRECTION, "");
    }
    setDoubleValue(DEVIATION, Math.abs(deviation), 3, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.HDGSentence#setHeading(double)
   */
  public void setHeading(double heading) {
    setDegreesValue(HEADING, heading);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.HDGSentence#setVariation(double)
   */
  public void setVariation(double variation) {
    if (variation < -180 || variation > 180) {
      throw new IllegalArgumentException("Value out of range [-180..180]");
    }
    if (variation > 0) {
      setCharValue(VAR_DIRECTION, CompassPointType.EAST.getCode());
    } else if (variation < 0) {
      setCharValue(VAR_DIRECTION, CompassPointType.WEST.getCode());
    } else {
      setStringValue(VAR_DIRECTION, "");
    }
    setDoubleValue(VARIATION, Math.abs(variation), 3, 1);
  }
}
