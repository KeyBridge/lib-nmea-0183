/* 
 * Copyright 2017 Key Bridge.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nmea.sentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enumeration of NMEA codes. This is used to identify which parsing object
 * should be applied to a received NMEA sentence.
 *
 * @author jesse
 */
public enum NMEACode {

  /**
   * GPAAM: Waypoint Arrival Alarm
   */
  GPAAM("Waypoint Arrival Alarm"),
  /**
   * GPALM: GPS Almanac Data
   */
  GPALM("GPS Almanac Data"),
  /**
   * GPAPA: Autopilot Sentence A
   */
  GPAPA("Autopilot Sentence A"),
  /**
   * GPAPB: Autopilot Sentence B
   */
  GPAPB("Autopilot Sentence B"),
  /**
   * GPASD: Autopilot System Data
   */
  GPASD("Autopilot System Data"),
  /**
   * GPBEC: Bearing & Distance to Waypoint Dead Reckoning
   */
  GPBEC("Bearing & Distance to Waypoint Dead Reckoning"),
  /**
   * GPBOD: Bearing Origin to Destination
   */
  GPBOD("Bearing Origin to Destination"),
  /**
   * GPBWC: Bearing & Distance to Waypoint Great Circle
   */
  GPBWC("Bearing & Distance to Waypoint Great Circle"),
  /**
   * GPBWR: Bearing & Distance to Waypoint Rhumb Line
   */
  GPBWR("Bearing & Distance to Waypoint Rhumb Line"),
  /**
   * GPBWW: Bearing Waypoint to Waypoint
   */
  GPBWW("Bearing Waypoint to Waypoint"),
  /**
   * GPDBT: Depth Below Transducer
   */
  GPDBT("Depth Below Transducer"),
  /**
   * GPDCN: Decca Position
   */
  GPDCN("Decca Position"),
  /**
   * GPDPT: Depth
   */
  GPDPT("Depth"),
  /**
   * GPFSI: Frequency Set Information
   */
  GPFSI("Frequency Set Information"),
  /**
   * GPGGA: Global Positioning System Fix Data
   */
  GPGGA("Global Positioning System Fix Data"),
  /**
   * GPGLC: Geographic Position Loran-C
   */
  GPGLC("Geographic Position Loran-C"),
  /**
   * GPGLL: Geographic Position Latitude/Longitude
   */
  GPGLL("Geographic Position Latitude/Longitude"),
  /**
   * GPGSA: GPS DOP and Active Satellites
   */
  GPGSA("GPS DOP and Active Satellites"),
  /**
   * GPGSV: GPS Satellites in View
   */
  GPGSV("GPS Satellites in View"),
  /**
   * GPGXA: TRANSIT Position
   */
  GPGXA("TRANSIT Position"),
  /**
   * GPHDG: Heading Deviation & Variation
   */
  GPHDG("Heading Deviation & Variation"),
  /**
   * GPHDT: Heading True
   */
  GPHDT("Heading True"),
  /**
   * GPHSC: Heading Steering Command
   */
  GPHSC("Heading Steering Command"),
  /**
   * GPLCD: Loran-C Signal Data
   */
  GPLCD("Loran-C Signal Data"),
  /**
   * GPMTA: Air Temperature (to be phased out
   */
  GPMTA("Air Temperature (to be phased out"),
  /**
   * GPMTW: Water Temperature
   */
  GPMTW("Water Temperature"),
  /**
   * GPMWD: Wind Direction
   */
  GPMWD("Wind Direction"),
  /**
   * GPMWV: Wind Speed and Angle
   */
  GPMWV("Wind Speed and Angle"),
  /**
   * GPOLN: Omega Lane Numbers
   */
  GPOLN("Omega Lane Numbers"),
  /**
   * GPOSD: Own Ship Data
   */
  GPOSD("Own Ship Data"),
  /**
   * GPR00: Waypoint active route (not standard
   */
  GPR00("Waypoint active route (not standard"),
  /**
   * GPRMA: Recommended Minimum Specific Loran-C Data
   */
  GPRMA("Recommended Minimum Specific Loran-C Data"),
  /**
   * GPRMB: Recommended Minimum Navigation Information
   */
  GPRMB("Recommended Minimum Navigation Information"),
  /**
   * GPRMC: Recommended Minimum Specific GPS/TRANSIT Data
   */
  GPRMC("Recommended Minimum Specific GPS/TRANSIT Data"),
  /**
   * GPROT: Rate of Turn
   */
  GPROT("Rate of Turn"),
  /**
   * GPRPM: Revolutions
   */
  GPRPM("Revolutions"),
  /**
   * GPRSA: Rudder Sensor Angle
   */
  GPRSA("Rudder Sensor Angle"),
  /**
   * GPRSD: RADAR System Data
   */
  GPRSD("RADAR System Data"),
  /**
   * GPRTE: Routes
   */
  GPRTE("Routes"),
  /**
   * GPSFI: Scanning Frequency Information
   */
  GPSFI("Scanning Frequency Information"),
  /**
   * GPSTN: Multiple Data ID
   */
  GPSTN("Multiple Data ID"),
  /**
   * GPTRF: Transit Fix Data
   */
  GPTRF("Transit Fix Data"),
  /**
   * GPTTM: Tracked Target Message
   */
  GPTTM("Tracked Target Message"),
  /**
   * GPVBW: Dual Ground/Water Speed
   */
  GPVBW("Dual Ground/Water Speed"),
  /**
   * GPVDR: Set and Drift
   */
  GPVDR("Set and Drift"),
  /**
   * GPVHW: Water Speed and Heading
   */
  GPVHW("Water Speed and Heading"),
  /**
   * GPVLW: Distance Traveled through the Water
   */
  GPVLW("Distance Traveled through the Water"),
  /**
   * GPVPW: Speed Measured Parallel to Wind
   */
  GPVPW("Speed Measured Parallel to Wind"),
  /**
   * GPVTG: Track Made Good and Ground Speed
   */
  GPVTG("Track Made Good and Ground Speed"),
  /**
   * GPWCV: Waypoint Closure Velocity
   */
  GPWCV("Waypoint Closure Velocity"),
  /**
   * GPWNC: Distance Waypoint to Waypoint
   */
  GPWNC("Distance Waypoint to Waypoint"),
  /**
   * GPWPL: Waypoint Location
   */
  GPWPL("Waypoint Location"),
  /**
   * GPXDR: Transducer Measurements
   */
  GPXDR("Transducer Measurements"),
  /**
   * GPXTE: Cross-Track Error Measured
   */
  GPXTE("Cross-Track Error Measured"),
  /**
   * GPXTR: Cross-Track Error Dead Reckoning
   */
  GPXTR("Cross-Track Error Dead Reckoning"),
  /**
   * GPZDA: Time & Date
   */
  GPZDA("Time & Date"),
  /**
   * GPZFO: UTC & Time from Origin Waypoint
   */
  GPZFO("UTC & Time from Origin Waypoint"),
  /**
   * GPZTG: UTC & Time to Destination Waypoint
   */
  GPZTG("UTC & Time to Destination Waypoint"),
  /**
   * PGRME: GARMIN Estimated Position Error
   */
  PGRME("GARMIN Estimated Position Error"),
  /**
   * PGRMF: GARMIN Position Fix Sentence
   */
  PGRMF("GARMIN Position Fix Sentence"),
  /**
   * PGRMM: GARMIN Map Datum
   */
  PGRMM("GARMIN Map Datum"),
  /**
   * PGRMV: GARMIN Velocity Sentence
   */
  PGRMV("GARMIN Velocity Sentence"),
  /**
   * PGRMZ: GARMIN Altitude Information
   */
  PGRMZ("GARMIN Altitude Information"),
  /**
   * PSLIB: GARMIN Differential Control
   */
  PSLIB("GARMIN Differential Control");

  /**
   * a human readable description
   */
  private final String description;

  private NMEACode(String description) {
    this.description = description;
  }

  /**
   * Get a human readable description.
   *
   * @return a human readable description. e.g. "Waypoint Location"
   */
  public String getDescription() {
    return description;
  }

  /**
   * Find the matching GPSd code for a NMEA sentence. Expected sentences are
   * formatted as $[CODE][DATA]
   *
   * @param sentence the sentence
   * @return the NMEA code
   */
  public static NMEACode parse(String sentence) {
    Pattern p = Pattern.compile("^\\$(\\w{3,5}),");
    Matcher m = p.matcher(sentence);
    if (m.find()) {
      return NMEACode.valueOf(m.group(1).trim());
    }
    throw new IllegalArgumentException("Not recognized");
  }

}
