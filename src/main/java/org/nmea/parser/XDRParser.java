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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.sentence.XDRSentence;
import org.nmea.type.Measurement;

/**
 * <p>
 * Transducer measurements.
 * <pre>
 *         1 2   3 4            n
 *         | |   | |            |
 *  $--XDR,a,x.x,a,c--c, ..... *hh<CR><LF>
 * </pre>
 * <p>
 * Where:
 * <ol>
 * <li>Transducer Type
 * <li>Measurement Data
 * <li>Units of measurement
 * <li>Name of transducer
 * </ol>
 * <p>
 * There may be any number of quadruplets like this, each describing a sensor.
 * The last field will be a checksum as usual.
 *
 * @author Robert Huitema, Kimmo Tuukkanen
 */
class XDRParser extends SentenceParser implements XDRSentence {

  // length of each data set is 4 fields
  private static int DATA_SET_LENGTH = 4;

  // data set field indices, relative to first field of each set
  private static int TYPE_INDEX = 0;
  private static int VALUE_INDEX = 1;
  private static int UNITS_INDEX = 2;
  private static int NAME_INDEX = 3;

  /**
   * Creates new instance of XDRParser.
   *
   * @param nmea XDR sentence string
   */
  public XDRParser(String nmea) {
    super(nmea, SentenceType.XDR);
  }

  /**
   * Creates XDR parser with empty sentence.
   *
   * @param talker TalkerId to set
   */
  public XDRParser(TalkerType talker) {
    super(talker, SentenceType.XDR, DATA_SET_LENGTH);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.XDRSentence#addMeasurement(org.nmea.util.Measurement[])
   */
  public void addMeasurement(Measurement... m) {
    List<Measurement> ms = getMeasurements();
    ms.addAll(Arrays.asList(m));
    setMeasurements(ms);
  }

  /*
   * (non-Javadoc) @see org.nmea.sentence.XDRSentence#getMeasurements()
   */
  public List<Measurement> getMeasurements() {
    ArrayList<Measurement> result = new ArrayList<>();
    for (int i = 0; i < getFieldCount(); i += DATA_SET_LENGTH) {
      Measurement measurement = fetchValues(i);
      if (measurement.isValid()) {
        result.add(measurement);
      }
    }
    return result;
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.XDRSentence#setMeasurement(org.nmea.util.Measurement)
   */
  public void setMeasurement(Measurement m) {
    setFieldCount(DATA_SET_LENGTH);
    insertValues(TYPE_INDEX, m);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.XDRSentence#setMeasurements(java.util.List)
   */
  public void setMeasurements(List<Measurement> measurements) {

    setFieldCount(measurements.size() * DATA_SET_LENGTH);

    int i = 0;
    for (Measurement m : measurements) {
      insertValues(i, m);
      i += DATA_SET_LENGTH;
    }
  }

  /**
   * Fetch data set starting at given index.
   *
   * @param i Start position of data set, i.e. index of first data field.
   * @return XDRValue object
   */
  private Measurement fetchValues(int i) {

    Measurement m = new Measurement();

    if (hasValue(i)) {
      m.setType(getStringValue(i));
    }

    if (hasValue(i + VALUE_INDEX)) {
      m.setValue(getDoubleValue(i + VALUE_INDEX));
    }

    if (hasValue(i + UNITS_INDEX)) {
      m.setUnits(getStringValue(i + UNITS_INDEX));
    }

    if (hasValue(i + NAME_INDEX)) {
      m.setName(getStringValue(i + NAME_INDEX));
    }

    return m;
  }

  /**
   * Inserts the given data set beginning at given index. Before inserting, make
   * sure the sentence has enough fields for it.
   *
   * @param i Start position of data set, i.e. index of first data field.
   * @param m XDR data set to insert
   */
  private void insertValues(int i, Measurement m) {

    if (m != null) {
      setStringValue((i), m.getType());
      setDoubleValue((i + VALUE_INDEX), m.getValue());
      setStringValue((i + UNITS_INDEX), m.getUnits());
      setStringValue((i + NAME_INDEX), m.getName());
    }
  }
}
