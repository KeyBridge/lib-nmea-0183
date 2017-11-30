/*
 * HeadingProvider.java
 * Copyright (C) 2012 Kimmo Tuukkanen
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
package ch.keybridge.lib.nmea.provider;

import org.nmea.io.SentenceReader;
import org.nmea.sentence.HeadingSentence;
import org.nmea.sentence.Sentence;
import org.nmea.type.SentenceType;
import ch.keybridge.lib.nmea.provider.event.HeadingEvent;

/**
 * Heading provider reports the vessel's current heading. Data is captured from
 * HDT, HDM or HDG sentences.
 *
 * @author Kimmo Tuukkanen
 * @see ch.keybridge.lib.nmea.provider.event.HeadingEvent
 * @see ch.keybridge.lib.nmea.provider.event.HeadingListener
 */
public class HeadingProvider extends AbstractProvider<HeadingEvent> {

  /**
   * Creates a new intance of HeadingProvider.
   *
   * @param reader Reader for capturing heading sentences.
   */
  public HeadingProvider(SentenceReader reader) {
    super(reader, SentenceType.HDT, SentenceType.HDM, SentenceType.HDG);
  }

  /*
   * (non-Javadoc) @see org.nmea.provider.AbstractProvider#createEvent()
   */
  @Override
  protected HeadingEvent createProviderEvent() {
    for (Sentence s : getSentences()) {
      if (s instanceof HeadingSentence) {
        return new HeadingEvent(this, (HeadingSentence) s);
      }
    }
    return null;
  }

  /*
   * (non-Javadoc) @see org.nmea.provider.AbstractProvider#isReady()
   */
  @Override
  protected boolean isReady() {
    return hasOne("HDT", "HDM", "HDG");
  }

  /*
   * (non-Javadoc) @see org.nmea.provider.AbstractProvider#isValid()
   */
  @Override
  protected boolean isValid() {
    return true;
  }

}
