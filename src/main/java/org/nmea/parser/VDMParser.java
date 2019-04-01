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

import org.nmea.sentence.AISSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;

/**
 * AIS VDM sentence parser, contains only the NMEA layer. The actual payload
 * message is parsed by AIS message parsers.
 *
 * @author Lázár József
 * @see AISSentence
 * @see AISParser
 */
class VDMParser extends AISParser {

  /**
   * Creates a new instance of VDMParser.
   *
   * @param nmea NMEA sentence String.
   */
  public VDMParser(String nmea) {
    super(nmea, SentenceType.VDM);
  }

  /**
   * Creates a new empty VDMParser.
   *
   * @param talker TalkerId to set
   */
  public VDMParser(TalkerType talker) {
    super(talker, SentenceType.VDM);
  }
}
