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
package org.nmea.sentence;

/**
 * Raymarine/Autohelm SeaTalk sentence. See
 * <a href="http://www.thomasknauf.de/seatalk.htm">SeaTalk Technical
 * Reference</a>
 * by Thomas Knauf for more information on the protocol.
 * <p>
 * Example:<br><code>$STALK,52,A1,00,00*36</code>
 */
public interface STALKSentence extends Sentence {

  /**
   * Add given parameter in sentence. Sentence field count is increased by one
   * field, notice that adding too many fields may result in invalid sentence.
   *
   * @param param Hex parameter to add
   */
  void addParameter(String param);

  /**
   * Returns the SeaTalk command / datagram type.
   *
   * @return Command String, "00", "01", "02" etc.
   */
  String getCommand();

  /**
   * Sets the SeaTalk command / datagram type.
   *
   * @param cmd Command String, "00", "01", "02" etc.
   */
  void setCommand(String cmd);

  /**
   * Returns the datagram payload.
   *
   * @return Parameters array containing hex values.
   */
  String[] getParameters();

  /**
   * Sets the datagram payload.
   *
   * @param params Parameters array containing hex Strings.
   */
  void setParameters(String... params);
}
