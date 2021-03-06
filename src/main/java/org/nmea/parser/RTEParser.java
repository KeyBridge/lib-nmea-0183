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

import org.nmea.sentence.RTESentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.RouteType;
import java.util.ArrayList;
import java.util.List;

/**
 * RTE sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class RTEParser extends SentenceParser implements RTESentence {

  // fields indices
  private static final int NUMBER_OF_SENTENCES = 0;
  private static final int SENTENCE_NUMBER = 1;
  private static final int STATUS = 2;
  private static final int ROUTE_ID = 3;
  private static final int FIRST_WPT = 4;

  /**
   * Creates a new instance of RTE parser.
   *
   * @param nmea RTE sentence string.
   */
  public RTEParser(String nmea) {
    super(nmea, SentenceType.RTE);
  }

  /**
   * Creates RTE parser with empty sentence. The created RTE sentence contains
   * none waypoint ID fields.
   *
   * @param talker TalkerId to set
   */
  public RTEParser(TalkerType talker) {
    super(talker, SentenceType.RTE, 4);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#addWaypointId(java.lang.String )
   */
  public int addWaypointId(String id) {

    String[] ids = getWaypointIds();
    String[] newIds = new String[ids.length + 1];

    System.arraycopy(ids, 0, newIds, 0, ids.length);
    newIds[newIds.length - 1] = id;

    setStringValues(FIRST_WPT, newIds);
    return newIds.length;
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RTESentence#getRouteId()
   */
  public String getRouteId() {
    return getStringValue(ROUTE_ID);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#getSentenceCount()
   */
  public int getSentenceCount() {
    return getIntValue(NUMBER_OF_SENTENCES);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#getSentenceIndex()
   */
  public int getSentenceIndex() {
    return getIntValue(SENTENCE_NUMBER);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#getWaypointCount()
   */
  public int getWaypointCount() {
    return getWaypointIds().length;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#getWaypointIds()
   */
  public String[] getWaypointIds() {

    List<String> temp = new ArrayList<>();

    for (int i = FIRST_WPT; i < getFieldCount(); i++) {
      try {
        temp.add(getStringValue(i));
      } catch (DataNotAvailableException e) {
        // nevermind empty fields
      }
    }

    return temp.toArray(new String[temp.size()]);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#isActiveRoute()
   */
  public boolean isActiveRoute() {
    return getCharValue(STATUS) == RouteType.ACTIVE.getCode();
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RTESentence#isFirst()
   */
  public boolean isFirst() {
    return (getSentenceIndex() == 1);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RTESentence#isLast()
   */
  public boolean isLast() {
    return (getSentenceIndex() == getSentenceCount());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#isWorkingRoute()
   */
  public boolean isWorkingRoute() {
    return getCharValue(STATUS) == RouteType.WORKING.getCode();
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#setRouteId(java.lang.String)
   */
  public void setRouteId(String id) {
    setStringValue(ROUTE_ID, id);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#setRouteType(net.sf.marineapi
   * .nmea.util.RouteType)
   */
  public void setRouteType(RouteType type) {
    setCharValue(STATUS, type.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#setSentenceCount(int)
   */
  public void setSentenceCount(int count) {
    if (count < 0) {
      throw new IllegalArgumentException("Count cannot be negative");
    }
    setIntValue(NUMBER_OF_SENTENCES, count);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#setSentenceIndex(int)
   */
  public void setSentenceIndex(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("Index cannot be negative");
    }
    setIntValue(SENTENCE_NUMBER, index);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RTESentence#setWaypointIds(java.lang.String
   * [])
   */
  public void setWaypointIds(String[] ids) {
    setStringValues(FIRST_WPT, ids);
  }

}
