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

import org.nmea.sentence.GNSSentence;
import org.nmea.type.Position;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.Time;

/**
 * GNS sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class GNSParser extends PositionParser implements GNSSentence {

  // NMEA field indices
  private static final int UTC_TIME = 0;
  private static final int LATITUDE = 1;
  private static final int LAT_DIRECTION = 2;
  private static final int LONGITUDE = 3;
  private static final int LON_DIRECTION = 4;
  private static final int MODE = 5;
  private static final int SATELLITE_COUNT = 6;
  private static final int HDOP = 7;
  private static final int ORTHOMETRIC_HEIGHT = 8;
  private static final int GEOIDAL_SEPARATION = 9;
  private static final int DGPS_AGE = 10;
  private static final int DGPS_STATION = 11;

  // MODE string character indices
  private static final int GPS_MODE = 0;
  private static final int GNS_MODE = 1;
  private static final int VAR_MODE = 2;

  /**
   * Constructor for parsing GNS.
   *
   * @param nmea GNS sentence String
   */
  public GNSParser(String nmea) {
    super(nmea, SentenceType.GNS);
  }

  /**
   * Constructor for empty GNS sentence.
   *
   * @param tid Talker ID to set
   */
  public GNSParser(TalkerType tid) {
    super(tid, SentenceType.GNS, 12);
    setTime(new Time());
    setStringValue(MODE, "NN");
  }

  @Override
  public Time getTime() {
    return new Time(getStringValue(UTC_TIME));
  }

  @Override
  public void setTime(Time t) {
    setStringValue(UTC_TIME, t.toString());
  }

  @Override
  public Position getPosition() {
    return parsePosition(LATITUDE, LAT_DIRECTION, LONGITUDE, LON_DIRECTION);
  }

  @Override
  public void setPosition(Position pos) {
    setPositionValues(pos, LATITUDE, LAT_DIRECTION, LONGITUDE, LON_DIRECTION);
  }

  @Override
  public ModeType getGpsMode() {
    String modes = getStringValue(MODE);
    return ModeType.valueOf(modes.charAt(GPS_MODE));
  }

  @Override
  public void setGpsMode(ModeType gps) {
    String modes = getStringValue(MODE);
    setStringValue(MODE, gps.getCode() + modes.substring(GNS_MODE));
  }

  @Override
  public ModeType getGlonassMode() {
    String modes = getStringValue(MODE);
    return ModeType.valueOf(modes.charAt(GNS_MODE));
  }

  @Override
  public void setGlonassMode(ModeType gns) {

    String modes = getStringValue(MODE);

    StringBuffer sb = new StringBuffer(modes.length());
    sb.append(modes.charAt(GPS_MODE));
    sb.append(gns.getCode());

    if (modes.length() > 2) {
      sb.append(modes.substring(VAR_MODE));
    }

    setStringValue(MODE, sb.toString());
  }

  @Override
  public ModeType[] getAdditionalModes() {
    String mode = getStringValue(MODE);
    if (mode.length() == 2) {
      return new ModeType[0];
    }
    String additional = mode.substring(VAR_MODE);
    ModeType[] modes = new ModeType[additional.length()];
    for (int i = 0; i < additional.length(); i++) {
      modes[i] = ModeType.valueOf(additional.charAt(i));
    }
    return modes;
  }

  @Override
  public void setAdditionalModes(ModeType... modes) {
    String current = getStringValue(MODE);
    StringBuffer sb = new StringBuffer(modes.length + 2);
    sb.append(current.substring(0, VAR_MODE));
    for (ModeType m : modes) {
      sb.append(m.getCode());
    }
    setStringValue(MODE, sb.toString());
  }

  @Override
  public int getSatelliteCount() {
    return getIntValue(SATELLITE_COUNT);
  }

  @Override
  public void setSatelliteCount(int count) {
    setIntValue(SATELLITE_COUNT, count, 2);
  }

  @Override
  public double getHorizontalDOP() {
    return getDoubleValue(HDOP);
  }

  @Override
  public void setHorizontalDOP(double hdop) {
    setDoubleValue(HDOP, hdop, 1, 2);
  }

  @Override
  public double getOrthometricHeight() {
    return getDoubleValue(ORTHOMETRIC_HEIGHT);
  }

  @Override
  public void setOrthometricHeight(double height) {
    setDoubleValue(ORTHOMETRIC_HEIGHT, height, 1, 2);
  }

  @Override
  public double getGeoidalSeparation() {
    return getDoubleValue(GEOIDAL_SEPARATION);
  }

  @Override
  public void setGeoidalSeparation(double separation) {
    setDoubleValue(GEOIDAL_SEPARATION, separation, 1, 2);
  }

  @Override
  public double getDgpsAge() {
    return getDoubleValue(DGPS_AGE);
  }

  @Override
  public void setDgpsAge(double age) {
    setDoubleValue(DGPS_AGE, age, 1, 1);
  }

  @Override
  public String getDgpsStationId() {
    return getStringValue(DGPS_STATION);
  }

  @Override
  public void setDgpsStationId(String stationId) {
    setStringValue(DGPS_STATION, stationId);
  }
}
