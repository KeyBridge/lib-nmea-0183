/*
 * HeadingEvent.java
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
package ch.keybridge.lib.nmea.provider.event;

import org.nmea.sentence.HeadingSentence;

/**
 * HeadingProvider event, reports the current magnetic/true heading of vessel in
 * degrees.
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.provider.HeadingProvider
 */
public class HeadingEvent extends ProviderEvent {

  private static final long serialVersionUID = 5706774741081575448L;
  private final double heading;
  private final boolean isTrue;

  /**
   * @param source
   */
  public HeadingEvent(Object source, HeadingSentence s) {
    super(source);
    heading = s.getHeading();
    isTrue = s.isTrue();
  }

  /**
   * Returns the current heading.
   *
   * @return Heading in degrees.
   */
  public double getHeading() {
    return heading;
  }

  /**
   * Tells if the heading is relative to true or magnetic north.
   *
   * @return true if true heading, otherwise false (magnetic).
   */
  public boolean isTrue() {
    return isTrue;
  }

  /*
   * (non-Javadoc) @see java.util.EventObject#toString()
   */
  public String toString() {
    return "[" + getHeading() + ", " + (isTrue() ? "T" : "M") + "]";
  }
}
