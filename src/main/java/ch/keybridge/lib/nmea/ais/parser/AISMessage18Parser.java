/*
 * AISMessage18Parser.java
 * Copyright (C) 2015 Lázár József
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
package ch.keybridge.lib.nmea.ais.parser;

import ch.keybridge.lib.nmea.ais.message.AISMessage18;
import ch.keybridge.lib.nmea.ais.util.Sixbit;

/**
 *
 * @author Lázár József
 */
class AISMessage18Parser extends AISPositionReportBParser implements AISMessage18 {

  public AISMessage18Parser(Sixbit content) {
    super(content);
  }
}
