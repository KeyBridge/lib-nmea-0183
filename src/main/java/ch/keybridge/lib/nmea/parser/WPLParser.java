/* 
 * WPLParser.java
 * Copyright (C) 2010 Kimmo Tuukkanen
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
package ch.keybridge.lib.nmea.parser;

import ch.keybridge.lib.nmea.sentence.SentenceId;
import ch.keybridge.lib.nmea.sentence.TalkerId;
import ch.keybridge.lib.nmea.sentence.WPLSentence;
import ch.keybridge.lib.nmea.util.Position;
import ch.keybridge.lib.nmea.util.Waypoint;

/**
 * WPL sentence parser.
 * 
 * @author Kimmo Tuukkanen
 */
class WPLParser extends PositionParser implements WPLSentence {

	// field ids
	private static final int LATITUDE = 0;
	private static final int LAT_HEMISPHERE = 1;
	private static final int LONGITUDE = 2;
	private static final int LON_HEMISPHERE = 3;
	private static final int WAYPOINT_ID = 4;

	/**
	 * Creates a new instance of WPLParser.
	 * 
	 * @param nmea WPL sentence String.
	 * @throws IllegalArgumentException If specified sentence is invalid.
	 */
	public WPLParser(String nmea) {
		super(nmea, SentenceId.WPL);
	}

	/**
	 * Creates WPL parser with empty sentence.
	 * 
	 * @param talker TalkerId to set
	 */
	public WPLParser(TalkerId talker) {
		super(talker, SentenceId.WPL, 5);
	}

	/*
	 * (non-Javadoc)
	 * @see net.sf.marineapi.nmea.sentence.WPLSentence#getWaypoint()
	 */
	public Waypoint getWaypoint() {
		String id = getStringValue(WAYPOINT_ID);
		Position p = parsePosition(
			LATITUDE, LAT_HEMISPHERE, LONGITUDE, LON_HEMISPHERE);
		
		return p.toWaypoint(id);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * net.sf.marineapi.nmea.sentence.WPLSentence#setWaypoint(net.sf.marineapi
	 * .nmea.util.Waypoint)
	 */
	public void setWaypoint(Waypoint wp) {
		setStringValue(WAYPOINT_ID, wp.getId());
		setPositionValues(wp, LATITUDE, LAT_HEMISPHERE, LONGITUDE, LON_HEMISPHERE);
	}
}
