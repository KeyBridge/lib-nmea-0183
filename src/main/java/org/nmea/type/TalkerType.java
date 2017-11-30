/*
 * TalkerId.java
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
 * An enumerated collection of recognized Talker IDs.
 * <p>
 * Talker type is typically encoded in the first two characters in the sentence
 * address field. For example, <code>GP</code> in <code>$GPGGA</code>. Notice
 * that proprietary sentences are identified by single character {@link #P}.
 * <p>
 * Note: This enum contains also non-NMEA IDs to enable parsing AIS messages
 * such as {@link #AI}, {@link #AB} and {@link #BS}. However, the correct
 * meaning of these are still unconfirmed.
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.sentence.SentenceId
 */
public enum TalkerType {

  /**
   * AIS, NMEA 4.0 Base AIS station
   */
  AB("AIS, NMEA 4.0 Base AIS station"),
  /**
   * AIS, NMEA 4.0 Dependent AIS Base Station
   */
  AD("AIS, NMEA 4.0 Dependent AIS Base Station"),
  /**
   * Autopilot, General
   */
  AG("Autopilot, General"),
  /**
   * AIS, Mobile AIS station
   */
  AI("AIS, Mobile AIS station"),
  /**
   * AIS, NMEA 4.0 Aid to Navigation AIS station
   */
  AN("AIS, NMEA 4.0 Aid to Navigation AIS station"),
  /**
   * Autopilot, Magnetic
   */
  AP("Autopilot, Magnetic"),
  /**
   * AIS, NMEA 4.0 AIS Receiving Station
   */
  AR("AIS, NMEA 4.0 AIS Receiving Station"),
  /**
   * AIS, NMEA 4.0 Limited Base Station
   */
  AS("AIS, NMEA 4.0 Limited Base Station"),
  /**
   * AIS, NMEA 4.0 AIS Transmitting Station
   */
  AT("AIS, NMEA 4.0 AIS Transmitting Station"),
  /**
   * AIS, NMEA 4.0 Repeater AIS station
   */
  AX("AIS, NMEA 4.0 Repeater AIS station"),
  /**
   * Beidou satellite navigation system (Chinese)
   */
  BD("Beidou satellite navigation system (Chinese)"),
  /**
   * AIS, Base AIS station (Deprecated in NMEA 4.0)
   */
  BS("AIS, Base AIS station (Deprecated in NMEA 4.0)"),
  /**
   * Communications, Digital Selective Calling (DSC)
   */
  CD("Communications, Digital Selective Calling (DSC)"),
  /**
   * Channel Pilot (Navicom Dynamics proprietary)
   */
  CP("Channel Pilot (Navicom Dynamics proprietary)"),
  /**
   * Communications, Satellite
   */
  CS("Communications, Satellite"),
  /**
   * Communications, Radio-Telephone (MF/HF)
   */
  CT("Communications, Radio-Telephone (MF/HF)"),
  /**
   * Communications, Radio-Telephone (VHF)
   */
  CV("Communications, Radio-Telephone (VHF)"),
  /**
   * Communications, Scanning Receiver
   */
  CX("Communications, Scanning Receiver"),
  /**
   * Direction Finder
   */
  DF("Direction Finder"),
  /**
   * Velocity Sensor, Speed Log, Water, Magnetic
   */
  DM("Velocity Sensor, Speed Log, Water, Magnetic"),
  /**
   * Electronic Chart Display &lt; Information System (ECDIS)
   */
  EC("Electronic Chart Display &lt; Information System (ECDIS)"),
  /**
   * Emergency Position Indicating Beacon (EPIRB)
   */
  EP("Emergency Position Indicating Beacon (EPIRB)"),
  /**
   * Engine Room Monitoring Systems
   */
  ER("Engine Room Monitoring Systems"),
  /**
   * Galileo satellite navigation system (European)
   */
  GA("Galileo satellite navigation system (European)"),
  /**
   * Beidou satellite navigation system (Chinese)
   */
  GB("Beidou satellite navigation system (Chinese)"),
  /**
   * Indian Regional Navigation Satellite System (IRNSS)
   */
  GI("Indian Regional Navigation Satellite System (IRNSS)"),
  /**
   * GLONASS (according to IEIC 61162-1)
   */
  GL("GLONASS (according to IEIC 61162-1)"),
  /**
   * Mixed GLONASS and GPS data (according to IEIC 61162-1)
   */
  GN("Mixed GLONASS and GPS data (according to IEIC 61162-1)"),
  /**
   * Global Positioning System (GPS)
   */
  GP("Global Positioning System (GPS)"),
  /**
   * Quasi Zenith Satellite System (QXSS, Japanese)
   */
  GQ("Quasi Zenith Satellite System (QXSS, Japanese)"),
  /**
   * Heading, Magnetic Compass
   */
  HC("Heading, Magnetic Compass"),
  /**
   * Heading, North Seeking Gyro
   */
  HE("Heading, North Seeking Gyro"),
  /**
   * Heading, Non North Seeking Gyro
   */
  HN("Heading, Non North Seeking Gyro"),
  /**
   * Integrated Instrumentation
   */
  II("Integrated Instrumentation"),
  /**
   * Integrated Navigation
   */
  IN("Integrated Navigation"),
  /**
   * Indian Regional Navigation Satellite System (IRNSS)
   */
  IR("Indian Regional Navigation Satellite System (IRNSS)"),
  /**
   * OpenPlotter calculated
   */
  OC("OpenPlotter calculated"),
  /**
   * QZSS regional GPS augmentation system (Japan)
   */
  QZ("QZSS regional GPS augmentation system (Japan)"),
  /**
   * RADAR and/or ARPA
   */
  RA("RADAR and/or ARPA"),
  /**
   * Propulsion Remote Control System
   */
  RC("Propulsion Remote Control System"),
  /**
   * AIS, NMEA 4.0 Physical Shore AIS Station
   */
  SA("AIS, NMEA 4.0 Physical Shore AIS Station"),
  /**
   * Depth Sounder
   */
  SD("Sounder, Depth"),
  /**
   * Electronic Positioning System, other/general
   */
  SN("Electronic Positioning System, other/general"),
  /**
   * Scanning Sounder
   */
  SS("Sounder, Scanning"),
  /**
   * Raymarine SeaTalk ($STALK)
   */
  ST("Raymarine SeaTalk ($STALK)"),
  /**
   * Turn Rate Indicator
   */
  TI("Turn Rate Indicator"),
  /**
   * TRANSIT Navigation System
   */
  TR("TRANSIT Navigation System"),
  /**
   * Velocity Sensor, Doppler, other/general
   */
  VD("Velocity Sensor, Doppler, other/general"),
  /**
   * Velocity Sensor, Speed Log, Water, Magnetic
   */
  VM("Velocity Sensor, Speed Log, Water, Magnetic"),
  /**
   * Velocity Sensor, Speed Log, Water, Mechanical
   */
  VW("Velocity Sensor, Speed Log, Water, Mechanical"),
  /**
   * Weather Instruments
   */
  WI("Weather Instruments"),
  /**
   * Transducer
   */
  YX("Transducer"),
  /**
   * Timekeeper, Atomic Clock
   */
  ZA("Timekeeper, Atomic Clock"),
  /**
   * Timekeeper, Chronometer
   */
  ZC("Timekeeper, Chronometer"),
  /**
   * Timekeeper, Quartz
   */
  ZQ("Timekeeper, Quartz"),
  /**
   * Timekeeper, Radio Update, WWV or WWVH
   */
  ZV("Timekeeper, Radio Update, WWV or WWVH"),

  /**
   * Proprietary sentence format (does not define the talker device).
   */
  P("Proprietary sentence format (does not define the talker device)"),

  /**
   * Computer, Programmed Calculator (obsolete)
   */
  @Deprecated
  CC("Computer, Programmed Calculator (obsolete)"),
  /**
   * Computer, Memory Data (obsolete)
   */
  @Deprecated
  CM("Computer, Memory Data (obsolete)"),
  /**
   * DECCA Navigation (obsolete)
   */
  @Deprecated
  DE("DECCA Navigation (obsolete)"),
  /**
   * Loran A (obsolete)
   */
  @Deprecated
  LA("Loran A (obsolete)"),
  /**
   * Loran C (obsolete)
   */
  @Deprecated
  LC("Loran C (obsolete)"),
  /**
   * Microwave Positioning System (obsolete)
   */
  @Deprecated
  MP("Microwave Positioning System (obsolete)"),
  /**
   * OMEGA Navigation System (obsolete)
   */
  @Deprecated
  OM("OMEGA Navigation System (obsolete)"),
  /**
   * Distress Alarm System (obsolete)
   */
  @Deprecated
  OS("Distress Alarm System (obsolete)"),
  /**
   * Transducer, Temperature (obsolete)
   */
  @Deprecated
  YC("Transducer, Temperature (obsolete)"),
  /**
   * Transducer, Displacement, Angular or Linear (obsolete)
   */
  @Deprecated
  YD("Transducer, Displacement, Angular or Linear (obsolete)"),
  /**
   * Transducer, Frequency (obsolete)
   */
  @Deprecated
  YF("Transducer, Frequency (obsolete)"),
  /**
   * Transducer, Level (obsolete)
   */
  @Deprecated
  YL("Transducer, Level (obsolete)"),
  /**
   * Transducer, Pressure (obsolete)
   */
  @Deprecated
  YP("Transducer, Pressure (obsolete)"),
  /**
   * Transducer, Flow Rate (obsolete)
   */
  @Deprecated
  YR("Transducer, Flow Rate (obsolete)"),
  /**
   * Transducer, Tachometer (obsolete)
   */
  @Deprecated
  YT("Transducer, Tachometer (obsolete)"),
  /**
   * Transducer, Volume (obsolete)
   */
  @Deprecated
  YV("Transducer, Volume (obsolete)");

  private final String label;

  private TalkerType(String label) {
    this.label = label;
  }

  /**
   * Parses the talker id from specified sentence String and returns the
   * corresponding TalkerId enum using the {@link #valueOf(String)} method.
   *
   * @param nmea Sentence String
   * @return TalkerId enum
   * @throws IllegalArgumentException If specified String is not recognized as
   *                                  NMEA sentence
   */
  public static TalkerType parse(String nmea) {

    if (!SentenceValidator.isSentence(nmea)) {
      throw new IllegalArgumentException("String is not a sentence");
    }

    String tid = "";
    if (nmea.startsWith("$P")) {
      tid = "P";
    } else {
      tid = nmea.substring(1, 3);
    }
    return TalkerType.valueOf(tid);
  }
}
