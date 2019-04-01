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
package ch.keybridge.lib.nmea.provider.event;

import java.util.EventObject;

/**
 * Abstract base class for provider events.
 *
 * @author Kimmo Tuukkanen
 */
public abstract class ProviderEvent extends EventObject {

  private static final long serialVersionUID = -5207967682036248721L;

  /**
   * Constructor
   *
   * @param source
   */
  public ProviderEvent(Object source) {
    super(source);
  }
}
