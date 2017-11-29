package org.nmea.parser;

import org.nmea.sentence.AISSentence;
import org.nmea.sentence.SentenceId;
import org.nmea.sentence.TalkerId;

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
    super(nmea, SentenceId.VDM);
  }

  /**
   * Creates a new empty VDMParser.
   *
   * @param talker TalkerId to set
   */
  public VDMParser(TalkerId talker) {
    super(talker, SentenceId.VDM);
  }
}
