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

import org.nmea.sentence.RMBSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.DataStatusType;
import org.nmea.type.DirectionType;
import org.nmea.type.Position;
import org.nmea.type.Waypoint;

/**
 * RMB sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class RMBParser extends PositionParser implements RMBSentence {

  // field indexes
  private static final int STATUS = 0;
  private static final int CROSS_TRACK_ERROR = 1;
  private static final int STEER_TO = 2;
  private static final int ORIGIN_WPT = 3;
  private static final int DEST_WPT = 4;
  private static final int DEST_LAT = 5;
  private static final int DEST_LAT_HEM = 6;
  private static final int DEST_LON = 7;
  private static final int DEST_LON_HEM = 8;
  private static final int RANGE_TO_DEST = 9;
  private static final int BEARING_TO_DEST = 10;
  private static final int VELOCITY = 11;
  private static final int ARRIVAL_STATUS = 12;

  /**
   * Constructor.
   *
   * @param nmea RMB sentence string
   */
  public RMBParser(String nmea) {
    super(nmea, SentenceType.RMB);
  }

  /**
   * Creates RMB parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public RMBParser(TalkerType talker) {
    super(talker, SentenceType.RMB, 13);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#getArrivalStatus()
   */
  public DataStatusType getArrivalStatus() {
    return DataStatusType.valueOf(getCharValue(ARRIVAL_STATUS));
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMBSentence#getBearing()
   */
  public double getBearing() {
    return getDoubleValue(BEARING_TO_DEST);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#getCrossTrackError()
   */
  public double getCrossTrackError() {
    return getDoubleValue(CROSS_TRACK_ERROR);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#getDestination()
   */
  public Waypoint getDestination() {
    String id = getStringValue(DEST_WPT);
    Position p = parsePosition(
      DEST_LAT, DEST_LAT_HEM, DEST_LON, DEST_LON_HEM);
    return p.toWaypoint(id);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMBSentence#getOriginId()
   */
  public String getOriginId() {
    return getStringValue(ORIGIN_WPT);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMBSentence#getRange()
   */
  public double getRange() {
    return getDoubleValue(RANGE_TO_DEST);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMBSentence#getStatus()
   */
  public DataStatusType getStatus() {
    return DataStatusType.valueOf(getCharValue(STATUS));
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMBSentence#getSteerTo()
   */
  public DirectionType getSteerTo() {
    return DirectionType.valueOf(getCharValue(STEER_TO));
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMBSentence#getVelocity()
   */
  public double getVelocity() {
    return getDoubleValue(VELOCITY);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.RMBSentence#hasArrived()
   */
  public boolean hasArrived() {
    return DataStatusType.ACTIVE.equals(getArrivalStatus());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setArrivalStatus(net.sf.marineapi
   * .nmea.util.DataStatus)
   */
  public void setArrivalStatus(DataStatusType status) {
    setCharValue(ARRIVAL_STATUS, status.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setBearing(double)
   */
  public void setBearing(double bearing) {
    setDegreesValue(BEARING_TO_DEST, bearing);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setCrossTrackError(double)
   */
  public void setCrossTrackError(double xte) {
    setDoubleValue(CROSS_TRACK_ERROR, xte, 1, 2);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setDestination(net.sf.marineapi
   * .nmea.util.Waypoint)
   */
  public void setDestination(Waypoint dest) {
    setStringValue(DEST_WPT, dest.getId());
    setPositionValues(dest, DEST_LAT, DEST_LAT_HEM, DEST_LON, DEST_LON_HEM);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setOriginId(java.lang.String)
   */
  public void setOriginId(String id) {
    setStringValue(ORIGIN_WPT, id);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setRange(double)
   */
  public void setRange(double range) {
    setDoubleValue(RANGE_TO_DEST, range, 1, 1);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setStatus(net.sf.marineapi
   * .nmea.util.DataStatus)
   */
  public void setStatus(DataStatusType status) {
    setCharValue(STATUS, status.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setSteerTo(net.sf.marineapi
   * .nmea.util.Direction)
   */
  public void setSteerTo(DirectionType steer) {
    if (steer != DirectionType.LEFT && steer != DirectionType.RIGHT) {
      throw new IllegalArgumentException(
        "Expected steer-to is LEFT or RIGHT.");
    }
    setCharValue(STEER_TO, steer.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.RMBSentence#setVelocity(double)
   */
  public void setVelocity(double velocity) {
    setDoubleValue(VELOCITY, velocity, 1, 1);
  }
}
