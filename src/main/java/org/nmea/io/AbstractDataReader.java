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

import org.nmea.parser.SentenceFactory;
import org.nmea.sentence.Sentence;
import org.nmea.sentence.SentenceValidator;

/**
 * Base class for data readers; common methods and run-loop.
 *
 * @author Kimmo Tuukkanen
 */
abstract class AbstractDataReader implements DataReader {

  // Sleep time between failed read attempts to prevent busy-looping
  private static final int SLEEP_TIME = 100;

  private final SentenceReader parent;
  private volatile boolean isRunning = true;

  /**
   * Creates a new instance.
   *
   * @param parent {@link SentenceReader} that owns this reader
   */
  public AbstractDataReader(SentenceReader parent) {
    this.parent = parent;
  }

  /**
   * Returns the parent SentenceReader.
   */
  protected SentenceReader getParent() {
    return this.parent;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.io.DataReader#isRunning()
   */
  public boolean isRunning() {
    return isRunning;
  }

  /**
   * Read one line from the data source.
   *
   * @return String or <code>null</code> if nothing was read.
   */
  public abstract String read() throws Exception;

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Runnable#run()
   */
  public void run() {

    ActivityMonitor monitor = new ActivityMonitor(parent);
    SentenceFactory factory = SentenceFactory.getInstance();

    while (isRunning) {
      try {
        String data = read();
        if (data == null) {
          Thread.sleep(SLEEP_TIME);
        } else if (SentenceValidator.isValidSentence(data)) {
          monitor.refresh();
          Sentence s = factory.createParser(data);
          parent.fireSentenceEvent(s);
        } else if (!SentenceValidator.isSentence(data)) {
          parent.fireDataEvent(data);
        }
      } catch (Exception e) {
        parent.handleException("Data read failed", e);
        try {
          Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException interruptException) {
        }
      } finally {
        monitor.tick();
      }
    }
    monitor.reset();
    parent.fireReadingStopped();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.nmea.io.DataReader#stop()
   */
  public void stop() {
    isRunning = false;
  }
}
