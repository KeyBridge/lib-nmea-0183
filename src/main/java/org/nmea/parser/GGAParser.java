/*
 * GGAParser.java
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

import org.nmea.sentence.GGASentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.GpsFixQualityType;
import org.nmea.type.Position;
import org.nmea.type.Time;
import org.nmea.type.UnitType;

/**
 * GGA sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class GGAParser extends PositionParser implements GGASentence {

  // GGA field indices
  private static final int UTC_TIME = 0;
  private static final int LATITUDE = 1;
  private static final int LAT_HEMISPHERE = 2;
  private static final int LONGITUDE = 3;
  private static final int LON_HEMISPHERE = 4;
  private static final int FIX_QUALITY = 5;
  private static final int SATELLITES_IN_USE = 6;
  private static final int HORIZONTAL_DILUTION = 7;
  private static final int ALTITUDE = 8;
  private static final int ALTITUDE_UNITS = 9;
  private static final int GEOIDAL_HEIGHT = 10;
  private static final int HEIGHT_UNITS = 11;
  private static final int DGPS_AGE = 12;
  private static final int DGPS_STATION_ID = 13;

  /**
   * Creates a new instance of GGA parser.
   *
   * @param nmea GGA sentence String.
   * @throws IllegalArgumentException If the specified sentence is invalid or
   *                                  not a GGA sentence.
   */
  public GGAParser(String nmea) {
    super(nmea, SentenceType.GGA);
  }

  /**
   * Creates GSA parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public GGAParser(TalkerType talker) {
    super(talker, SentenceType.GGA, 14);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.GGASentence#getAltitude()
   */
  public double getAltitude() {
    return getDoubleValue(ALTITUDE);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#getAltitudeUnits()
   */
  public UnitType getAltitudeUnits() {
    char ch = getCharValue(ALTITUDE_UNITS);
    if (ch != ALT_UNIT_METERS && ch != ALT_UNIT_FEET) {
      String msg = "Invalid altitude unit indicator: %s";
      throw new ParseException(String.format(msg, ch));
    }
    return UnitType.valueOf(ch);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.GGASentence#getDgpsAge()
   */
  public double getDgpsAge() {
    return getDoubleValue(DGPS_AGE);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#getDgpsStationId()
   */
  public String getDgpsStationId() {
    return getStringValue(DGPS_STATION_ID);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#getFixQuality()
   */
  public GpsFixQualityType getFixQuality() {
    return GpsFixQualityType.valueOf(getIntValue(FIX_QUALITY));
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#getGeoidalHeight()
   */
  public double getGeoidalHeight() {
    return getDoubleValue(GEOIDAL_HEIGHT);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#getGeoidalHeightUnits()
   */
  public UnitType getGeoidalHeightUnits() {
    return UnitType.valueOf(getCharValue(HEIGHT_UNITS));
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#getHorizontalDOP()
   */
  public double getHorizontalDOP() {
    return getDoubleValue(HORIZONTAL_DILUTION);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.PositionSentence#getPosition()
   */
  public Position getPosition() {

    Position pos = parsePosition(
      LATITUDE, LAT_HEMISPHERE, LONGITUDE, LON_HEMISPHERE);

    if (hasValue(ALTITUDE) && hasValue(ALTITUDE_UNITS)) {
      double alt = getAltitude();
      if (getAltitudeUnits().equals(UnitType.FEET)) {
        alt = (alt / 0.3048);
      }
      pos.setAltitude(alt);
    }

    return pos;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#getSatelliteCount()
   */
  public int getSatelliteCount() {
    return getIntValue(SATELLITES_IN_USE);
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
   * org.nmea.sentence.GGASentence#setAltitude(double)
   */
  public void setAltitude(double alt) {
    setDoubleValue(ALTITUDE, alt, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#setAltitudeUnits(net.sf.marineapi
   * .nmea.util.Units)
   */
  public void setAltitudeUnits(UnitType unit) {
    setCharValue(ALTITUDE_UNITS, unit.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#setDgpsAge(int)
   */
  public void setDgpsAge(double age) {
    setDoubleValue(DGPS_AGE, age, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#setDgpsStationId(java.lang
   * .String)
   */
  public void setDgpsStationId(String id) {
    setStringValue(DGPS_STATION_ID, id);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#setFixQuality(net.sf.marineapi
   * .nmea.util.GpsFixQuality)
   */
  public void setFixQuality(GpsFixQualityType quality) {
    setIntValue(FIX_QUALITY, quality.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#setGeoidalHeight(double)
   */
  public void setGeoidalHeight(double height) {
    setDoubleValue(GEOIDAL_HEIGHT, height, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#setGeoidalHeightUnits(net.
   * sf.marineapi.nmea.util.Units)
   */
  public void setGeoidalHeightUnits(UnitType unit) {
    setCharValue(HEIGHT_UNITS, unit.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#setHorizontalDOP(double)
   */
  public void setHorizontalDOP(double hdop) {
    setDoubleValue(HORIZONTAL_DILUTION, hdop, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.PositionSentence#setPosition(net.sf.marineapi
   * .nmea.util.Position)
   */
  public void setPosition(Position pos) {
    setPositionValues(
      pos, LATITUDE, LAT_HEMISPHERE, LONGITUDE, LON_HEMISPHERE);

    setAltitude(pos.getAltitude());
    setAltitudeUnits(UnitType.METER);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GGASentence#setSatelliteCount(int)
   */
  @Override
  public void setSatelliteCount(int count) {
    if (count < 0) {
      throw new IllegalArgumentException("Satelite count cannot be negative");
    }
    setIntValue(SATELLITES_IN_USE, count, 2);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.TimeSentence#setTime(org.
   * nmea.util.Time)
   */
  public void setTime(Time t) {
    setStringValue(UTC_TIME, t.toString());
  }

}
