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

import org.nmea.sentence.STALKSentence;
import org.nmea.type.SentenceType;
import org.nmea.type.TalkerType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SeaTalk $STALK sentence parser.
 * <p>
 * $STALK,cc,p1,p2..,pn*xx
 */
class STALKParser extends SentenceParser implements STALKSentence {

  private static int COMMAND = 0;
  private static int FIRST_PARAM = 1;

  /**
   * Construct with sentence.
   *
   * @param nmea <code>$STALK</code> sentence String.
   */
  public STALKParser(String nmea) {
    super(nmea, SentenceType.ALK);
  }

  /**
   * Constructor with TalkerId, mostly for compatibility with SentenceFactory.
   * Does not set given talker id, but uses the STALK default 'ST'. Creates a
   * sentence with two fields, command and one parameter.
   *
   * @param tid Any TalkerId may given, but does not affect the resulting
   *            "talker id" as sentence identifier is always
   *            <code>$STALK</code>.
   */
  public STALKParser(TalkerType tid) {
    super(TalkerType.ST, SentenceType.ALK, 2);
    if (!tid.equals(TalkerType.ST)) {
      throw new IllegalArgumentException("$STALK talker id 'ST' is mandatory (got " + tid + ")");
    }
  }

  @Override
  public String getCommand() {
    return getStringValue(COMMAND);
  }

  @Override
  public void setCommand(String cmd) {
    setStringValue(COMMAND, cmd);
  }

  @Override
  public String[] getParameters() {
    return getStringValues(FIRST_PARAM);
  }

  @Override
  public void setParameters(String... params) {
    setStringValues(FIRST_PARAM, params);
  }

  @Override
  public void addParameter(String param) {
    List<String> params = new ArrayList<>(Arrays.asList(getParameters()));
    params.add(param);
    setParameters(params.toArray(new String[params.size()]));
  }
}
