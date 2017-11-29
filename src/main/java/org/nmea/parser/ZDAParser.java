/*
 * ZDAParser.java
 * Copyright (C) 2010 Kimmo Tuukkanen
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

import org.nmea.sentence.SentenceId;
import org.nmea.sentence.TalkerId;
import org.nmea.sentence.ZDASentence;
import org.nmea.util.Date;
import org.nmea.util.Time;

/**
 * ZDA sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class ZDAParser extends SentenceParser implements ZDASentence {

  // field indices
  private static final int UTC_TIME = 0;
  private static final int DAY = 1;
  private static final int MONTH = 2;
  private static final int YEAR = 3;
  private static final int LOCAL_ZONE_HOURS = 4;
  private static final int LOCAL_ZONE_MINUTES = 5;

  /**
   * Creates a new instance of ZDAParser.
   *
   * @param nmea ZDA sentence String
   * @throws IllegalArgumentException If specified sentence is invalid.
   */
  public ZDAParser(String nmea) {
    super(nmea, SentenceId.ZDA);
  }

  /**
   * Creates WPL parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public ZDAParser(TalkerId talker) {
    super(talker, SentenceId.ZDA, 6);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.DateSentence#getDate()
   */
  public Date getDate() {
    int y = getIntValue(YEAR);
    int m = getIntValue(MONTH);
    int d = getIntValue(DAY);
    return new Date(y, m, d);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.ZDASentence#getLocalZoneHours()
   */
  public int getLocalZoneHours() {
    return getIntValue(LOCAL_ZONE_HOURS);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.ZDASentence#getLocalZoneMinutes()
   */
  public int getLocalZoneMinutes() {
    return getIntValue(LOCAL_ZONE_MINUTES);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.TimeSentence#getTime()
   */
  public Time getTime() {

    String str = getStringValue(UTC_TIME);
    int tzHrs = getLocalZoneHours();
    int tzMin = getLocalZoneMinutes();

    Time t = new Time(str);
    t.setOffsetHours(tzHrs);
    t.setOffsetMinutes(tzMin);

    return t;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.DateSentence#setDate(org.
   * nmea.util.Date)
   */
  public void setDate(Date date) {
    setIntValue(YEAR, date.getYear());
    setIntValue(MONTH, date.getMonth(), 2);
    setIntValue(DAY, date.getDay(), 2);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.ZDASentence#setLocalZoneHours(int)
   */
  public void setLocalZoneHours(int hours) {
    if (hours < -13 || hours > 13) {
      throw new IllegalArgumentException(
        "Value must be within range -13..13");
    }
    setIntValue(LOCAL_ZONE_HOURS, hours, 2);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.ZDASentence#setLocalZoneMinutes()
   */
  public void setLocalZoneMinutes(int minutes) {
    if (minutes < -59 || minutes > 59) {
      throw new IllegalArgumentException(
        "Value must be within range -59..59");
    }
    setIntValue(LOCAL_ZONE_MINUTES, minutes, 2);
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
   * org.nmea.sentence.TimeSentence#setTimeAndLocalZone(org.
   * nmea.util.Time)
   */
  public void setTimeAndLocalZone(Time t) {
    setTime(t);
    setLocalZoneHours(t.getOffsetHours());
    setLocalZoneMinutes(t.getOffsetMinutes());
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.ZDASentence#toDate()
   */
  public java.util.Date toDate() {
    Date d = getDate();
    Time t = getTime();
    return t.toDate(d.toDate());
  }
}
