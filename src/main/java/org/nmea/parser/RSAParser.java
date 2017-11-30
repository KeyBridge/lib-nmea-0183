/*
 * RSAParser.java
 * Copyright (C) 2014 Lázár József, Kimmo Tuukkanen
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

import org.nmea.sentence.RSASentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import org.nmea.type.DataStatusType;
import org.nmea.type.Side;

/**
 * RSA sentence parser.
 *
 * @author Lázár József, Kimmo Tuukkanen
 */
class RSAParser extends SentenceParser implements RSASentence {

  private static final int STARBOARD_SENSOR = 0;
  private static final int STARBOARD_STATUS = 1;
  private static final int PORT_SENSOR = 2;
  private static final int PORT_STATUS = 3;

  /**
   * Creates a new instance of RSAParser.
   *
   * @param nmea RSA sentence String
   */
  public RSAParser(String nmea) {
    super(nmea, SentenceType.RSA);
  }

  /**
   * Creates a new instance of RSAParser with empty data fields.
   *
   * @param talker TalkerId to set
   */
  public RSAParser(TalkerType talker) {
    super(talker, SentenceType.RSA, 4);
    setStatus(Side.STARBOARD, DataStatusType.VOID);
    setStatus(Side.PORT, DataStatusType.VOID);
  }

  @Override
  public double getRudderAngle(Side side) {
    if (Side.STARBOARD.equals(side)) {
      return getDoubleValue(STARBOARD_SENSOR);
    }
    return getDoubleValue(PORT_SENSOR);
  }

  @Override
  public void setRudderAngle(Side side, double angle) {
    if (Side.STARBOARD.equals(side)) {
      setDoubleValue(STARBOARD_SENSOR, angle);
    } else {
      setDoubleValue(PORT_SENSOR, angle);
    }
  }

  @Override
  public DataStatusType getStatus(Side side) {
    if (Side.STARBOARD.equals(side)) {
      return DataStatusType.valueOf(getCharValue(STARBOARD_STATUS));
    }
    return DataStatusType.valueOf(getCharValue(PORT_STATUS));
  }

  @Override
  public void setStatus(Side side, DataStatusType status) {
    if (Side.STARBOARD.equals(side)) {
      setCharValue(STARBOARD_STATUS, status.getCode());
    } else {
      setCharValue(PORT_STATUS, status.getCode());
    }
  }
}
