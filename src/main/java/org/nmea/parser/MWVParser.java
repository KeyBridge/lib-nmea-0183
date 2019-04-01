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

import org.nmea.sentence.MWVSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.DataStatusType;
import org.nmea.type.UnitType;

/**
 * MWV sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class MWVParser extends SentenceParser implements MWVSentence {

  private static final int WIND_ANGLE = 0;
  private static final int REFERENCE = 1;
  private static final int WIND_SPEED = 2;
  private static final int SPEED_UNITS = 3;
  private static final int DATA_STATUS = 4;

  /**
   * Creates a new instance of MWVParser.
   *
   * @param nmea MWV sentence String
   */
  public MWVParser(String nmea) {
    super(nmea, SentenceType.MWV);
  }

  /**
   * Creates a new empty instance of MWVParser.
   *
   * @param talker Talker id to set
   */
  public MWVParser(TalkerType talker) {
    super(talker, SentenceType.MWV, 5);
    setCharValue(DATA_STATUS, DataStatusType.VOID.getCode());
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.MWVSentence#getAngle()
   */
  public double getAngle() {
    return getDoubleValue(WIND_ANGLE);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.MWVSentence#getSpeed()
   */
  public double getSpeed() {
    return getDoubleValue(WIND_SPEED);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MWVSentence#getSpeedUnit()
   */
  public UnitType getSpeedUnit() {
    return UnitType.valueOf(getCharValue(SPEED_UNITS));
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.MWVSentence#getStatus()
   */
  public DataStatusType getStatus() {
    return DataStatusType.valueOf(getCharValue(DATA_STATUS));
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.MWVSentence#isTrue()
   */
  public boolean isTrue() {
    char ch = getCharValue(REFERENCE);
    return ch == 'T';
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MWVSentence#setAngle(double)
   */
  public void setAngle(double angle) {
    setDegreesValue(WIND_ANGLE, angle);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MWVSentence#setSpeed(double)
   */
  public void setSpeed(double speed) {
    if (speed < 0) {
      throw new IllegalArgumentException("Speed must be positive");
    }
    setDoubleValue(WIND_SPEED, speed, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MWVSentence#setSpeedUnit(net.sf.marineapi
   * .nmea.util.Units)
   */
  public void setSpeedUnit(UnitType unit) {
    if (unit == UnitType.METER || unit == UnitType.KMH || unit == UnitType.KNOT) {
      setCharValue(SPEED_UNITS, unit.getCode());
      return;
    }
    throw new IllegalArgumentException("Invalid unit for speed");
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MWVSentence#setStatus(net.sf.marineapi
   * .nmea.util.DataStatus)
   */
  public void setStatus(DataStatusType status) {
    setCharValue(DATA_STATUS, status.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MWVSentence#setTrue(boolean)
   */
  public void setTrue(boolean isTrue) {
    if (isTrue) {
      setCharValue(REFERENCE, 'T');
    } else {
      setCharValue(REFERENCE, 'R');
    }
  }

}
