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

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * DataReader implementation using DatagramSocket as data source.
 *
 * @author Kimmo Tuukkanen, Ludovic Drouineau
 */
class UDPDataReader extends AbstractDataReader {

  private final DatagramSocket socket;
  private final byte[] buffer = new byte[1024];
  private final Queue<String> queue = new LinkedList<>();

  /**
   * Creates a new instance of StreamReader.
   *
   * @param socket DatagramSocket to be used as data source.
   * @param parent SentenceReader dispatching events for this reader.
   */
  public UDPDataReader(DatagramSocket socket, SentenceReader parent) {
    super(parent);
    this.socket = socket;
  }

  @Override
  public String read() throws Exception {
    while (true) {
      String data = queue.poll();
      if (data != null) {
        return data;
      }

      data = receive();
      String[] lines = data.split("\\r?\\n");
      queue.addAll(Arrays.asList(lines));
    }
  }

  /**
   * Receive UDP packet and return as String
   */
  private String receive() throws Exception {
    DatagramPacket pkg = new DatagramPacket(buffer, buffer.length);
    socket.receive(pkg);
    return new String(pkg.getData(), 0, pkg.getLength());
  }

}
