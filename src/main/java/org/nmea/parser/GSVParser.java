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

import org.nmea.sentence.GSVSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.Satellite;
import java.util.ArrayList;
import java.util.List;

/**
 * GSV sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class GSVParser extends SentenceParser implements GSVSentence {

  // field indices
  private static final int NUMBER_OF_SENTENCES = 0;
  private static final int SENTENCE_NUMBER = 1;
  private static final int SATELLITES_IN_VIEW = 2;

  // satellite id fields
  private static final int[] ID_FIELDS = {3, 7, 11, 15};

  // satellite data fields, relative to each id field
  private static final int ELEVATION = 1;
  private static final int AZIMUTH = 2;
  private static final int NOISE = 3;

  /**
   * Constructor.
   *
   * @param nmea GSV Sentence
   */
  public GSVParser(String nmea) {
    super(nmea, SentenceType.GSV);
  }

  /**
   * Creates an GSV parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public GSVParser(TalkerType talker) {
    super(talker, SentenceType.GSV, 19);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSVSentence#getSatelliteCount()
   */
  public int getSatelliteCount() {
    return getIntValue(SATELLITES_IN_VIEW);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSVSentence#getSatelliteInfo()
   */
  public List<Satellite> getSatelliteInfo() {

    List<Satellite> satellites = new ArrayList<>(4);

    for (int idf : ID_FIELDS) {
      try {
        String id = getStringValue(idf);
        int elev = getIntValue(idf + ELEVATION);
        int azm = getIntValue(idf + AZIMUTH);
        int snr = getIntValue(idf + NOISE);
        satellites.add(new Satellite(id, elev, azm, snr));
      } catch (DataNotAvailableException e) {
        // nevermind missing satellite info
      } catch (IndexOutOfBoundsException e) {
        // less than four satellites, give up
        break;
      }
    }

    return satellites;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSVSentence#getSentenceCount()
   */
  public int getSentenceCount() {
    return getIntValue(NUMBER_OF_SENTENCES);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSVSentence#getSentenceIndex()
   */
  public int getSentenceIndex() {
    return getIntValue(SENTENCE_NUMBER);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.GSVSentence#isFirst()
   */
  public boolean isFirst() {
    return (getSentenceIndex() == 1);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.GSVSentence#isLast()
   */
  public boolean isLast() {
    return (getSentenceIndex() == getSentenceCount());
  }

  public void setSatelliteCount(int count) {
    if (count < 0) {
      throw new IllegalArgumentException(
        "Satellite count cannot be negative");
    }
    setIntValue(SATELLITES_IN_VIEW, count);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSVSentence#setSatelliteInfo(java.util
   * .List)
   */
  public void setSatelliteInfo(List<Satellite> info) {
    if (info.size() > 4) {
      throw new IllegalArgumentException("Maximum list size is 4");
    }
    int i = 0;
    for (int id : ID_FIELDS) {
      if (i < info.size()) {
        Satellite si = info.get(i++);
        setStringValue(id, si.getId());
        setIntValue(id + ELEVATION, si.getElevation());
        setIntValue(id + AZIMUTH, si.getAzimuth(), 3);
        setIntValue(id + NOISE, si.getNoise());
      } else {
        setStringValue(id, "");
        setStringValue(id + ELEVATION, "");
        setStringValue(id + AZIMUTH, "");
        setStringValue(id + NOISE, "");
      }
    }
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSVSentence#setSentenceCount(int)
   */
  public void setSentenceCount(int count) {
    if (count < 1) {
      throw new IllegalArgumentException(
        "Number of sentences cannot be negative");
    }
    setIntValue(NUMBER_OF_SENTENCES, count);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.GSVSentence#setSentenceIndex(int)
   */
  public void setSentenceIndex(int index) {
    if (index < 0) {
      throw new IllegalArgumentException(
        "Sentence index cannot be negative");
    }
    setIntValue(SENTENCE_NUMBER, index);
  }

}
