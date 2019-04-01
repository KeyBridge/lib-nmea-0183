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

import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.sentence.VBWSentence;
import org.nmea.type.DataStatusType;

/**
 * VBW sentence parser.
 *
 * @author Jeremy Wilson
 */
class VBWParser extends SentenceParser implements VBWSentence {

  public static final int LONG_WATERSPEED = 0;
  public static final int TRAV_WATERSPEED = 1;
  public static final int WATER_SPEED_STATUS = 2;
  public static final int LONG_GROUNDSPEED = 3;
  public static final int TRAV_GROUNDSPEED = 4;
  public static final int GROUND_SPEED_STATUS = 5;
  public static final int STERN_WATERSPEED = 6;
  public static final int STERN_SPEED_STATUS = 7;
  public static final int STERN_GROUNDSPEED = 8;
  public static final int STERN_GROUNDSPEED_STATUS = 9;

  /**
   * Create a new instance of VBWParser.
   *
   * @param nmea VBW sentence String.
   * @throws IllegalArgumentException If specified sentence is invalid.
   */
  public VBWParser(String nmea) {
    super(nmea, SentenceType.VBW);
  }

  /**
   * Create a VBW parser with an empty sentence.
   *
   * @param talker TalkerId to set
   */
  public VBWParser(TalkerType talker) {
    super(talker, SentenceType.VBW, 10);
  }

  public double getLongWaterSpeed() {
    return getDoubleValue(LONG_WATERSPEED);
  }

  public DataStatusType getWaterSpeedStatus() {
    return DataStatusType.valueOf(getCharValue(WATER_SPEED_STATUS));
  }

  public DataStatusType getGroundSpeedStatus() {
    return DataStatusType.valueOf(getCharValue(GROUND_SPEED_STATUS));
  }

  public double getLongGroundSpeed() {
    return getDoubleValue(LONG_GROUNDSPEED);
  }

  public double getTravWaterSpeed() {
    return getDoubleValue(TRAV_WATERSPEED);
  }

  public double getTravGroundSpeed() {
    return getDoubleValue(TRAV_GROUNDSPEED);
  }

  public double getSternWaterSpeed() {
    return getDoubleValue(STERN_WATERSPEED);
  }

  public DataStatusType getSternWaterSpeedStatus() {
    return DataStatusType.valueOf(getCharValue(STERN_SPEED_STATUS));
  }

  public double getSternGroundSpeed() {
    return getDoubleValue(STERN_GROUNDSPEED);
  }

  public DataStatusType getSternGroundSpeedStatus() {
    return DataStatusType.valueOf(getCharValue(STERN_GROUNDSPEED_STATUS));
  }

  public void setLongWaterSpeed(double speed) {
    setDoubleValue(LONG_WATERSPEED, speed, 2, 1);
  }

  public void setLongGroundSpeed(double speed) {
    setDoubleValue(LONG_GROUNDSPEED, speed, 2, 1);
  }

  public void setTravWaterSpeed(double speed) {
    setDoubleValue(TRAV_WATERSPEED, speed, 2, 1);
  }

  public void setTravGroundSpeed(double speed) {
    setDoubleValue(TRAV_GROUNDSPEED, speed, 2, 1);
  }

  public void setWaterSpeedStatus(DataStatusType status) {
    setCharValue(WATER_SPEED_STATUS, status.getCode());
  }

  public void setGroundSpeedStatus(DataStatusType status) {
    setCharValue(GROUND_SPEED_STATUS, status.getCode());
  }

  public void setSternWaterSpeed(double speed) {
    setDoubleValue(STERN_WATERSPEED, speed, 2, 1);
  }

  public void setSternWaterSpeedStatus(DataStatusType status) {
    setCharValue(STERN_SPEED_STATUS, status.getCode());
  }

  public void setSternGroundSpeed(double speed) {
    setDoubleValue(STERN_GROUNDSPEED, speed, 2, 1);
  }

  public void setSternGroundSpeedStatus(DataStatusType status) {
    setCharValue(STERN_GROUNDSPEED_STATUS, status.getCode());
  }
}
