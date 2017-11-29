/*
 * AbstractSentenceListener.java
 * Copyright (C) 2014 Kimmo Tuukkanen
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
package org.nmea.event;

import org.nmea.sentence.Sentence;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * <p>
 * Abstract base class for typed listeners with automatic sentence type
 * resolving and casting. Extend this class to create a listener/handler for a
 * single sentence type and register it in
 * {@link org.nmea.io.SentenceReader}.
 * <p>
 * Methods of {@link SentenceListener} interface implemented by this class are
 * empty, except {@link #sentenceRead(SentenceEvent)} which detects the incoming
 * sentence parsers and casts them to correct interface before calling
 * {@link #sentenceRead(Sentence)} method.
 *
 * @author Kimmo Tuukkanen
 * @param <T> Sentence interface to be listened.
 * @see org.nmea.io.SentenceReader
 */
public abstract class AbstractSentenceListener<T extends Sentence>
  implements SentenceListener {

  private final Type expectedType;

  public AbstractSentenceListener() {

    ParameterizedType superClass
                      = (ParameterizedType) getClass().getGenericSuperclass();

    Type[] superClassTypeArgs = superClass.getActualTypeArguments();

    this.expectedType = superClassTypeArgs[0];
  }

  /**
   * Empty implementation.
   *
   * @see org.nmea.event.SentenceListener#readingPaused()
   */
  public void readingPaused() {
  }

  /**
   * Empty implementation.
   *
   * @see org.nmea.event.SentenceListener#readingStarted()
   */
  public void readingStarted() {
  }

  /**
   * Empty implementation.
   *
   * @see org.nmea.event.SentenceListener#readingStopped()
   */
  public void readingStopped() {
  }

  /**
   * Invoked when sentence of type <code>T</code> has been read and parsed.
   *
   * @param sentence Sentence of type <code>T</code>.
   */
  public abstract void sentenceRead(T sentence);

  /**
   * <p>
   * Resolves the type of each received sentence parser and passes it to
   * <code>sentenceRead(T)</code> if the type matches the expected type
   * <code>T</code>.
   * <p>
   * <p>
   * This method may be overridden, but be sure to call
   * <code>super.sentencerRead(SentenceEvent)</code> before or after your
   * additional event handling. However, for listeners that need to handle all
   * incoming sentences, it's recommended to directly implement the
   * {@link org.nmea.event.SentenceListener} interface.
   *
   * @see
   * org.nmea.event.SentenceListener#sentenceRead(org.nmea.event.SentenceEvent)
   */
  @SuppressWarnings("unchecked")
  public void sentenceRead(SentenceEvent event) {
    Sentence sentence = event.getSentence();
    Class<?>[] interfaces = sentence.getClass().getInterfaces();
    if (Arrays.asList(interfaces).contains(this.expectedType)) {
      sentenceRead((T) sentence);
    }
  }

}
