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

import org.nmea.type.DataStatusType;
import org.nmea.type.FaaModeType;
import org.nmea.type.CompassPointType;
import org.nmea.type.Position;
import org.nmea.type.Date;
import org.nmea.type.Time;
import org.nmea.sentence.RMCSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;

/**
 * RMC sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class RMCParser extends PositionParser implements RMCSentence {

  private static final int UTC_TIME = 0;
  private static final int DATA_STATUS = 1;
  private static final int LATITUDE = 2;
  private static final int LAT_HEMISPHERE = 3;
  private static final int LONGITUDE = 4;
  private static final int LON_HEMISPHERE = 5;
  private static final int SPEED = 6;
  private static final int COURSE = 7;
  private static final int UTC_DATE = 8;
  private static final int MAG_VARIATION = 9;
  private static final int VAR_HEMISPHERE = 10;
  private static final int MODE = 11;

  /**
   * Creates a new instance of RMCParser.
   *
   * @param nmea RMC sentence String.
   * @throws IllegalArgumentException If specified sentence is invalid.
   */
  public RMCParser(String nmea) {
    super(nmea, SentenceType.RMC);
  }

  /**
   * Creates a ZDA parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public RMCParser(TalkerType talker) {
    super(talker, SentenceType.RMC, 12);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#getCorrectedCourse()
   */
  public double getCorrectedCourse() {
    return getCourse() + getVariation();
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMCSentence#getCourse()
   */
  public double getCourse() {
    return getDoubleValue(COURSE);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.DateSentence#getDate()
   */
  public Date getDate() {
    return new Date(getStringValue(UTC_DATE));
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#getDirectionOfVariation()
   */
  public CompassPointType getDirectionOfVariation() {
    return CompassPointType.valueOf(getCharValue(VAR_HEMISPHERE));
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMCSentence#getFaaMode()
   */
  public FaaModeType getMode() {
    return FaaModeType.valueOf(getCharValue(MODE));
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.PositionSentence#getPosition()
   */
  public Position getPosition() {
    return parsePosition(LATITUDE, LAT_HEMISPHERE, LONGITUDE, LON_HEMISPHERE);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMCSentence#getSpeed()
   */
  public double getSpeed() {
    return getDoubleValue(SPEED);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#getDataStatus()
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
   * org.nmea.sentence.RMCSentence#getVariation()
   */
  public double getVariation() {
    double variation = getDoubleValue(MAG_VARIATION);
    if (CompassPointType.EAST == getDirectionOfVariation() && variation > 0) {
      variation = -(variation);
    }
    return variation;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#setCourse(double)
   */
  public void setCourse(double cog) {
    setDegreesValue(COURSE, cog);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.DateSentence#setDate(org.
   * nmea.util.Date)
   */
  public void setDate(Date date) {
    setStringValue(UTC_DATE, date.toString());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#setDirectionOfVariation(net
   * .sf.marineapi.nmea.util.Direction)
   */
  public void setDirectionOfVariation(CompassPointType dir) {
    if (dir != CompassPointType.EAST && dir != CompassPointType.WEST) {
      throw new IllegalArgumentException(
        "Invalid variation direction, expected EAST or WEST.");
    }
    setCharValue(VAR_HEMISPHERE, dir.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#setFaaMode(net.sf.marineapi
   * .nmea.util.FaaMode)
   */
  public void setMode(FaaModeType mode) {
    setFieldCount(12);
    setCharValue(MODE, mode.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.PositionSentence#setPosition(net.sf.marineapi
   * .nmea.util.Position)
   */
  public void setPosition(Position pos) {
    setPositionValues(pos, LATITUDE, LAT_HEMISPHERE, LONGITUDE, LON_HEMISPHERE);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#setSpeed(double)
   */
  public void setSpeed(double sog) {
    setDoubleValue(SPEED, sog, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#setDataStatus(net.sf.marineapi
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

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMCSentence#setVariation(double)
   */
  public void setVariation(double var) {
    setDegreesValue(MAG_VARIATION, var);
  }
}
