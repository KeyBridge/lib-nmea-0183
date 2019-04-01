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
import org.nmea.sentence.VTGSentence;
import org.nmea.type.FaaModeType;

/**
 * VTG sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class VTGParser extends SentenceParser implements VTGSentence {

  private static final int TRUE_COURSE = 0;
  private static final int TRUE_INDICATOR = 1;
  private static final int MAGNETIC_COURSE = 2;
  private static final int MAGNETIC_INDICATOR = 3;
  private static final int SPEED_KNOTS = 4;
  private static final int KNOTS_INDICATOR = 5;
  private static final int SPEED_KMPH = 6;
  private static final int KMPH_INDICATOR = 7;
  private static final int MODE = 8;

  /**
   * Creates a new instance of VTGParser.
   *
   * @param nmea VTG sentence String
   * @throws IllegalArgumentException If specified sentence is invalid
   */
  public VTGParser(String nmea) {
    super(nmea, SentenceType.VTG);
  }

  /**
   * Creates VTG parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public VTGParser(TalkerType talker) {
    super(talker, SentenceType.VTG, 9);
    setCharValue(TRUE_INDICATOR, VTGSentence.TRUE);
    setCharValue(MAGNETIC_INDICATOR, VTGSentence.MAGNETIC);
    setCharValue(KNOTS_INDICATOR, VTGSentence.KNOT);
    setCharValue(KMPH_INDICATOR, VTGSentence.KMPH);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.VTGSentence#getMagneticCourse()
   */
  public double getMagneticCourse() {
    return getDoubleValue(MAGNETIC_COURSE);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.VTGSentence#getMode()
   */
  public FaaModeType getMode() {
    return FaaModeType.valueOf(getCharValue(MODE));
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.VTGSentence#getSpeedKmh()
   */
  public double getSpeedKmh() {
    return getDoubleValue(SPEED_KMPH);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.VTGSentence#getSpeedKnots()
   */
  public double getSpeedKnots() {
    return getDoubleValue(SPEED_KNOTS);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.VTGSentence#getTrueCourse()
   */
  public double getTrueCourse() {
    return getDoubleValue(TRUE_COURSE);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.VTGSentence#setMagneticCourse(double)
   */
  public void setMagneticCourse(double mcog) {
    setDegreesValue(MAGNETIC_COURSE, mcog);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.VTGSentence#setMode(org.nmea
   * .util.FaaMode)
   */
  public void setMode(FaaModeType mode) {
    setFieldCount(9);
    setCharValue(MODE, mode.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.VTGSentence#setSpeedKmh(double)
   */
  public void setSpeedKmh(double kmh) {
    if (kmh < 0) {
      throw new IllegalArgumentException("Speed cannot be negative");
    }
    setDoubleValue(SPEED_KMPH, kmh, 1, 2);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.VTGSentence#setSpeedKnots(double)
   */
  public void setSpeedKnots(double knots) {
    if (knots < 0) {
      throw new IllegalArgumentException("Speed cannot be negative");
    }
    setDoubleValue(SPEED_KNOTS, knots, 1, 2);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.VTGSentence#setTrueCourse(double)
   */
  public void setTrueCourse(double tcog) {
    setDegreesValue(TRUE_COURSE, tcog);
  }
}
