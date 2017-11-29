/*
 * AbstractAISMessageListener.java
 * Copyright (C) 2015 Kimmo Tuukkanen
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
package org.nmea.ais.event;

import org.nmea.ais.message.AISMessage;
import org.nmea.ais.parser.AISMessageFactory;
import org.nmea.event.SentenceEvent;
import org.nmea.event.SentenceListener;
import org.nmea.sentence.AISSentence;
import org.nmea.sentence.Sentence;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Abstract base listener for AIS messages. Extend this class to create listener
 * for a specific AIS message type. For listening all available AIS sentences,
 * extend <code>AbstractSentenceListener&lt;AISSentence&gt;</code> or implement
 * SentenceListener interface. However, in this case you should also implement
 * AIS message concatenation to combine and parse messages that are deliverd
 * over multiple sentences.
 *
 * @author Kimmo Tuukkanen
 */
public abstract class AbstractAISMessageListener<T extends AISMessage> implements SentenceListener {

  private final Class<? extends AISMessage> expectedMessageType;
  private final Queue<AISSentence> queue = new LinkedList<>();
  private final AISMessageFactory factory = AISMessageFactory.getInstance();

  /**
   * Constructor
   */
  public AbstractAISMessageListener() {
    // TODO: not DRY
    ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
    Type[] superClassTypeArgs = superClass.getActualTypeArguments();
    this.expectedMessageType = (Class<T>) superClassTypeArgs[0];
  }

  @Override
  public void sentenceRead(SentenceEvent event) {
    Sentence s = event.getSentence();
    if (s.isAISSentence()) {
      handleAIS((AISSentence) s);
    }
  }

  /**
   * Concatenate and pre-parse AIS sentences/messages.
   */
  private void handleAIS(AISSentence sentence) {

    if (sentence.isFirstFragment()) {
      queue.clear();
    }

    queue.add(sentence);

    if (sentence.isLastFragment()) {
      AISSentence[] sentences = queue.toArray(new AISSentence[queue.size()]);
      try {
        AISMessage message = factory.create(sentences);
        if (message != null) {
          Class<?>[] interfaces = message.getClass().getInterfaces();
          if (Arrays.asList(interfaces).contains(expectedMessageType)) {
            onMessage((T) message);
          }
        }
      } catch (IllegalArgumentException iae) {
        // nevermind unsupported message types
      }
    }
  }

  /**
   * Invoked when AIS message has been parsed.
   *
   * @param msg AISMessage of subtype <code>T</code>.
   */
  public abstract void onMessage(T msg);

  /**
   * Empty implementation.
   *
   * @see org.nmea.event.SentenceListener
   */
  @Override
  public void readingPaused() {
  }

  /**
   * Empty implementation.
   *
   * @see org.nmea.event.SentenceListener
   */
  @Override
  public void readingStarted() {
  }

  /**
   * Empty implementation.
   *
   * @see org.nmea.event.SentenceListener
   */
  @Override
  public void readingStopped() {
  }
}
