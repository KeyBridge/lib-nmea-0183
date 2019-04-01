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

/**
 * Monitor for firing state change events events, i.e. reader started, paused or
 * stopped.
 *
 * @author Kimmo Tuukkanen
 */
class ActivityMonitor {

  private long lastUpdated = -1;
  private final SentenceReader parent;

  public ActivityMonitor(SentenceReader parent) {
    this.parent = parent;
  }

  /**
   * Resets the monitor in initial state.
   */
  public void reset() {
    lastUpdated = -1;
  }

  /**
   * Refreshes the monitor timestamp and fires reading started event if
   * currently paused.
   */
  public void refresh() {
    if (lastUpdated < 0) {
      parent.fireReadingStarted();
    }
    this.lastUpdated = System.currentTimeMillis();
  }

  /**
   * Heartbeat method, checks the time out if not paused.
   */
  public void tick() {
    if (lastUpdated > 0) {
      long elapsed = System.currentTimeMillis() - lastUpdated;
      int timeout = parent.getPauseTimeout();
      if (elapsed >= timeout) {
        parent.fireReadingPaused();
        reset();
      }
    }
  }
}
