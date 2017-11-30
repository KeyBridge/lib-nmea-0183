/*
 * MTAParser.java
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

import org.nmea.sentence.MTASentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.UnitType;

/**
 * MTA sentence parser.
 *
 * @author Kimmo Tuukkanen
 */
class MTAParser extends SentenceParser implements MTASentence {

  private static final int TEMPERATURE = 0;
  private static final int UNIT_INDICATOR = 1;

  /**
   * Constructor.
   */
  public MTAParser(String mta) {
    super(mta, SentenceType.MTA);
  }

  /**
   * Constructor.
   */
  public MTAParser(TalkerType talker) {
    super(talker, SentenceType.MTA, 2);
    setCharValue(UNIT_INDICATOR, UnitType.CELSIUS.getCode());
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MTASentence#getTemperature()
   */
  public double getTemperature() {
    return getDoubleValue(TEMPERATURE);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.sentence.MTASentence#setTemperature(double)
   */
  public void setTemperature(double temp) {
    setDoubleValue(TEMPERATURE, temp, 1, 2);
  }

}
