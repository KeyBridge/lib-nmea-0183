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

import java.util.EventListener;

/**
 * Base interface for provider listeners.
 *
 * @author Kimmo Tuukkanen
 */
public abstract interface ProviderListener<T extends ProviderEvent> extends
  EventListener {

  /**
   * Invoked when provider has new data available.
   *
   * @param evt ProviderEvent object
   */
  void providerUpdate(T evt);

}
