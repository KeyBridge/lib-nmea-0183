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

import org.nmea.sentence.DTMSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;

/**
 * DTM parser.
 *
 * @author Kimmo Tuukkanen
 */
class DTMParser extends SentenceParser implements DTMSentence {

  private static final int DATUM_CODE = 0;
  private static final int DATUM_SUBCODE = 1;
  private static final int LATITUDE_OFFSET = 2;
  private static final int LAT_OFFSET_HEMISPHERE = 3;
  private static final int LONGITUDE_OFFSET = 4;
  private static final int LON_OFFSET_HEMISPHERE = 5;
  private static final int ALTITUDE_OFFSET = 6;
  private static final int DATUM_NAME = 7;

  /**
   * Creates a new instance of DTMParser.
   */
  public DTMParser(String nmea) {
    super(nmea, SentenceType.DTM);
  }

  public DTMParser(TalkerType talker) {
    super(talker, SentenceType.DTM, 8);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#getAltitudeOffset()
   */
  @Override
  public double getAltitudeOffset() {
    return getDoubleValue(ALTITUDE_OFFSET);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#getDatumCode()
   */
  @Override
  public String getDatumCode() {
    return getStringValue(DATUM_CODE);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#getDatumSubCode()
   */
  @Override
  public String getDatumSubCode() {
    return getStringValue(DATUM_SUBCODE);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#getLatitudeOffset()
   */
  @Override
  public double getLatitudeOffset() {
    return getDoubleValue(LATITUDE_OFFSET);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#getLongitudeOffset()
   */
  @Override
  public double getLongitudeOffset() {
    return getDoubleValue(LONGITUDE_OFFSET);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#getName()
   */
  @Override
  public String getName() {
    return getStringValue(DATUM_NAME);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.nmea.sentence.DTMSentence#setDatumCode(java.lang.String)
   */
  @Override
  public void setDatumCode(String code) {
    setStringValue(DATUM_CODE, code);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#setDatumSubCode(java.lang.
   * String)
   */
  @Override
  public void setDatumSubCode(String code) {
    setStringValue(DATUM_SUBCODE, code);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#setLatitudeOffset(double)
   */
  @Override
  public void setLatitudeOffset(double offset) {
    setDoubleValue(LATITUDE_OFFSET, offset, 1, 4);
    if (offset < 0) {
      setCharValue(LAT_OFFSET_HEMISPHERE, 'S');
    } else {
      setCharValue(LAT_OFFSET_HEMISPHERE, 'N');
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#setLongitudeOffset(double)
   */
  @Override
  public void setLongitudeOffset(double offset) {
    setDoubleValue(LONGITUDE_OFFSET, offset, 1, 4);
    if (offset < 0) {
      setCharValue(LON_OFFSET_HEMISPHERE, 'W');
    } else {
      setCharValue(LON_OFFSET_HEMISPHERE, 'E');
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.sentence.DTMSentence#setName(java.lang.String)
   */
  @Override
  public void setName(String name) {
    setStringValue(DATUM_NAME, name);
  }

}
