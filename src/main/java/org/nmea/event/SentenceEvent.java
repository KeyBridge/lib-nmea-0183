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
package org.nmea.event;

import org.nmea.sentence.Sentence;
import java.util.EventObject;

/**
 * Sentence events occur when a valid NMEA 0183 sentence has been read from the
 * data source.
 *
 * @author Kimmo Tuukkanen
 * @see SentenceListener
 * @see org.nmea.io.SentenceReader
 */
public class SentenceEvent extends EventObject {

  private static final long serialVersionUID = -2756954014186470514L;
  private final long timestamp = System.currentTimeMillis();
  private final Sentence sentence;

  /**
   * Creates a new SentenceEvent object.
   *
   * @param src Object that fired the event
   * @param s   Sentence that triggered the event
   * @throws IllegalArgumentException If specified sentence is <code>null</code>
   */
  public SentenceEvent(Object src, Sentence s) {
    super(src);
    if (s == null) {
      throw new IllegalArgumentException("Sentence cannot be null");
    }
    this.sentence = s;
  }

  /**
   * Gets the Sentence object that triggered the event.
   *
   * @return Sentence object
   */
  public Sentence getSentence() {
    return sentence;
  }

  /**
   * Get system time when this event was created.
   *
   * @return Milliseconds timestamp
   */
  public long getTimeStamp() {
    return timestamp;
  }
}
