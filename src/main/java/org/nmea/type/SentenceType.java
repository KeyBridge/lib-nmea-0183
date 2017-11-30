/*
 * SentenceId.java
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
package org.nmea.type;

import org.nmea.sentence.SentenceValidator;

/**
 * Defines the supported NMEA 0831 sentence types. Sentence address field is a
 * combination of talker and sentence IDs, for example GPBOD, GPGGA or GPGGL.
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.sentence.TalkerId
 */
public enum SentenceType {
  /**
   * Raymarine SeaTalk ($STALK).
   */
  ALK("Raymarine SeaTalk ($STALK)"),
  /**
   * Autopilot sentence 'B'; bearings and heading toward destination
   */
  APB("Autopilot sentence 'B' bearings and heading toward destination"),
  /**
   * Bearing Origin to Destination
   */
  BOD("Bearing Origin to Destination"),
  /**
   * Current
   */
  CUR("Current"),
  /**
   * Depth of water below transducer; in meters, feet and fathoms
   */
  DBT("Depth of water below transducer; in meters, feet and fathoms"),
  /**
   * Depth of water below transducer; in meters.
   */
  DPT("Depth of water below transducer; in meters"),
  /**
   * Datum reference.
   */
  DTM("Datum reference"),
  /**
   * Global Positioning System fix data
   */
  GGA("Global Positioning System fix data"),
  /**
   * Geographic position (latitude/longitude)
   */
  GLL("Geographic position (latitude/longitude)"),
  /**
   * GNSS fix data (GPS, GLONASS and future constellations).
   */
  GNS("GNSS fix data (GPS, GLONASS and future constellations)"),
  /**
   * Dilution of precision (DOP) of GPS fix and active satellites
   */
  GSA("Dilution of precision (DOP) of GPS fix and active satellites"),
  /**
   * Detailed satellite data
   */
  GSV("Detailed satellite data"),
  /**
   * Vessel heading in degrees with magnetic variation and deviation.
   */
  HDG("Vessel heading in degrees with magnetic variation and deviation"),
  /**
   * Vessel heading in degrees with respect to true north.
   */
  HDM("Vessel heading in degrees with respect to true north"),
  /**
   * Vessel heading in degrees true
   */
  HDT("Vessel heading in degrees true"),
  /**
   * Meteorological Composite
   */
  MDA("Meteorological Composite"),
  /**
   * Relative and absolute humidity with dew point
   */
  MHU("Relative and absolute humidity with dew point"),
  /**
   * Barometric pressure in inches of mercury and bars.
   */
  MMB("Barometric pressure in inches of mercury and bars"),
  /**
   * Air temperature in degrees centigrade (Celsius).
   */
  MTA("Air temperature in degrees centigrade (Celsius)"),
  /**
   * Water temperature in degrees centigrade (Celsius).
   */
  MTW("Water temperature in degrees centigrade (Celsius)"),
  /**
   * Wind speed and direction
   */
  MWD("Wind speed and direction"),
  /**
   * Wind speed and angle
   */
  MWV("Wind speed and angle"),
  /**
   * Recommended minimum navigation information
   */
  RMB("Recommended minimum navigation information"),
  /**
   * Recommended minimum specific GPS/TRANSIT data
   */
  RMC("Recommended minimum specific GPS/TRANSIT data"),
  /**
   * Rate of Turn
   */
  ROT("Rate of Turn"),
  /**
   * Revolutions measured from engine or shaft.
   */
  RPM("Revolutions measured from engine or shaft"),
  /**
   * Rudder angle, measured in degrees
   */
  RSA("Rudder angle, measured in degrees"),
  /**
   * Route data and waypoint list
   */
  RTE("Route data and waypoint list"),
  /**
   * Tracked target
   */
  TTM("Tracked target"),
  /**
   * Dual ground/water speed and stern ground/water speed.
   */
  VBW("Dual ground/water speed and stern ground/water speed"),
  /**
   * AIS - Received data from other vessels
   */
  VDM("AIS - Received data from other vessels"),
  /**
   * AIS - Own vessel data
   */
  VDO("AIS - Own vessel data"),
  /**
   * Set and drift, direction and speed of current.
   */
  VDR("Set and drift, direction and speed of current"),
  /**
   * Water speed and heading
   */
  VHW("Water speed and heading"),
  /**
   * Distance traveled through water, cumulative and since reset.
   */
  VLW("Distance traveled through water, cumulative and since reset"),
  /**
   * Track made good and ground speed
   */
  VTG("Track made good and ground speed"),
  /**
   * Relative Wind Speed and Angle
   */
  VWR("Relative Wind Speed and Angle"),
  /**
   * True Wind Speed and Angle
   */
  VWT("True Wind Speed and Angle"),
  /**
   * Waypoint location (latitude/longitude)
   */
  WPL("Waypoint location (latitude/longitude)"),
  /**
   * Transducer measurements (sensor data)
   */
  XDR("Transducer measurements (sensor data)"),
  /**
   * Cross-track error, measured
   */
  XTE("Cross-track error, measured"),
  /**
   * UTC time and date with local time zone offset
   */
  ZDA("UTC time and date with local time zone offset");

  private final String label;

  private SentenceType(String label) {
    this.label = label;
  }

  /**
   * Parses the sentence id from specified sentence String and returns a
   * corresponding <code>SentenceId</code> enum (assuming it exists).
   *
   * @param nmea Sentence String
   * @return SentenceId enum
   * @throws IllegalArgumentException If specified String is not valid sentence
   */
  public static SentenceType parse(String nmea) {
    String sid = parseStr(nmea);
    return SentenceType.valueOf(sid);
  }

  /**
   * Get a human readable label for this type.
   *
   * @return the label.
   */
  public String getLabel() {
    return label;
  }

  /**
   * Parses the sentence id from specified sentence String and returns it as
   * String.
   *
   * @param nmea Sentence String
   * @return Sentence Id, e.g. "GGA"or "GLL" * @throws IllegalArgumentException
   *         If specified String is not recognized as NMEA sentence
   */
  public static String parseStr(String nmea) {
    if (!SentenceValidator.isSentence(nmea)) {
      throw new IllegalArgumentException("String is not a sentence");
    }
    return nmea.startsWith("$P")
           ? nmea.substring(2, nmea.indexOf(','))
           : nmea.substring(3, nmea.indexOf(','));
  }
}
