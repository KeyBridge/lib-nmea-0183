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
