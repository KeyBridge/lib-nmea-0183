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
package org.nmea.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The default data reader implementation using InputStream as data source.
 *
 * @author Kimmo Tuukkanen
 */
class DefaultDataReader extends AbstractDataReader {

  private final BufferedReader input;

  /**
   * Creates a new instance of DefaultDataReader.
   *
   * @param source InputStream to be used as data source.
   * @param parent SentenceReader dispatching events for this reader.
   */
  public DefaultDataReader(InputStream source, SentenceReader parent) {
    super(parent);
    InputStreamReader isr = new InputStreamReader(source);
    this.input = new BufferedReader(isr);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.io.AbstractDataReader#read()
   */
  @Override
  public String read() throws Exception {
    return input.readLine();
  }
}
