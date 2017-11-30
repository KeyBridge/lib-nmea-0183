/*
 * GSAParser.java
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

import org.nmea.sentence.GSASentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.FaaModeType;
import org.nmea.type.GpsFixStatusType;
import java.util.ArrayList;
import java.util.List;

/**
 * GSA sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class GSAParser extends SentenceParser implements GSASentence {

  // field indices
  private static final int GPS_MODE = 0;
  private static final int FIX_MODE = 1;
  private static final int FIRST_SV = 2;
  private static final int LAST_SV = 13;
  private static final int POSITION_DOP = 14;
  private static final int HORIZONTAL_DOP = 15;
  private static final int VERTICAL_DOP = 16;

  /**
   * Creates a new instance of GSA parser.
   *
   * @param nmea GSA sentence String
   * @throws IllegalArgumentException If specified sentence is invalid.
   */
  public GSAParser(String nmea) {
    super(nmea, SentenceType.GSA);
  }

  /**
   * Creates GSA parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public GSAParser(TalkerType talker) {
    super(talker, SentenceType.GSA, 17);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#getFixStatus()
   */
  public GpsFixStatusType getFixStatus() {
    return GpsFixStatusType.valueOf(getIntValue(FIX_MODE));
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#getHorizontalDOP()
   */
  public double getHorizontalDOP() {
    return getDoubleValue(HORIZONTAL_DOP);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.GSASentence#getMode()
   */
  public FaaModeType getMode() {
    return FaaModeType.valueOf(getCharValue(GPS_MODE));
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#getPositionDOP()
   */
  public double getPositionDOP() {
    return getDoubleValue(POSITION_DOP);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#getSatelliteIds()
   */
  public String[] getSatelliteIds() {
    List<String> result = new ArrayList<>();
    for (int i = FIRST_SV; i <= LAST_SV; i++) {
      if (hasValue(i)) {
        result.add(getStringValue(i));
      }
    }
    return result.toArray(new String[result.size()]);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#getVerticalDOP()
   */
  public double getVerticalDOP() {
    return getDoubleValue(VERTICAL_DOP);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#setFixStatus(net.sf.marineapi
   * .nmea.util.GpsFixStatus)
   */
  public void setFixStatus(GpsFixStatusType status) {
    setIntValue(FIX_MODE, status.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#setHorizontalPrecision(double)
   */
  public void setHorizontalDOP(double hdop) {
    setDoubleValue(HORIZONTAL_DOP, hdop, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#setFaaMode(net.sf.marineapi
   * .nmea.util.FaaMode)
   */
  public void setMode(FaaModeType mode) {
    setCharValue(GPS_MODE, mode.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#setPositionDOP(double)
   */
  public void setPositionDOP(double pdop) {
    setDoubleValue(POSITION_DOP, pdop, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#setSatelliteIds(java.lang.
   * String[])
   */
  public void setSatelliteIds(String[] ids) {
    if (ids.length > (LAST_SV - FIRST_SV + 1)) {
      throw new IllegalArgumentException("List length exceeded (12)");
    }
    int j = 0;
    for (int i = FIRST_SV; i <= LAST_SV; i++) {
      String id = (j < ids.length) ? ids[j++] : "";
      setStringValue(i, id);
    }
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSASentence#setVerticalDOP(double)
   */
  public void setVerticalDOP(double vdop) {
    setDoubleValue(VERTICAL_DOP, vdop, 1, 1);
  }

}
