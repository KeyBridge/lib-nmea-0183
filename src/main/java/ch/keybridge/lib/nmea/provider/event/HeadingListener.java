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

/**
 * Listener interface for {@link org.nmea.provider.HeadingProvider}.
 *
 * @author Kimmo Tuukkanen
 */
public interface HeadingListener extends ProviderListener<HeadingEvent> {

  /*
   * (non-Javadoc) @see
   * org.nmea.provider.event.ProviderListener#providerUpdate(net.sf
   * .marineapi.provider.event.ProviderEvent)
   */
  public void providerUpdate(HeadingEvent evt);
}
