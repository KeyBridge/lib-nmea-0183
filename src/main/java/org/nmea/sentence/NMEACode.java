/**
 * <p>
 * Copyright 2010 Key Bridge Global LLC.
 * </p>
 * <p>
 * http://keybridgeglobal.com
 * </p>
 * <p>
 * This file is part of the Java package "GPSd Java Client: (GPSdClient)".
 * </p>
 * <p>
 * GPSdClient is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * </p>
 * <p>
 * GPSdClient is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * </p>
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * GPSdClient. If not, see <http://www.gnu.org/licenses/>.
 * </p>
 */
package org.nmea.sentence;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enumeration of NMEA codes. This is used to identify which parsing object
 * should be applied to a received NMEA sentence.
 * 
 * @author jesse
 */
public enum NMEACode {
  GPAAM("GPAAM", "Waypoint Arrival Alarm"),
  GPALM("GPALM", "GPS Almanac Data"),
  GPAPA("GPAPA", "Autopilot Sentence A"),
  GPAPB("GPAPB", "Autopilot Sentence B"),
  GPASD("GPASD", "Autopilot System Data"),
  GPBEC("GPBEC", "Bearing & Distance to Waypoint Dead Reckoning"),
  GPBOD("GPBOD", "Bearing Origin to Destination"),
  GPBWC("GPBWC", "Bearing & Distance to Waypoint Great Circle"),
  GPBWR("GPBWR", "Bearing & Distance to Waypoint Rhumb Line"),
  GPBWW("GPBWW", "Bearing Waypoint to Waypoint"),
  GPDBT("GPDBT", "Depth Below Transducer"),
  GPDCN("GPDCN", "Decca Position"),
  GPDPT("GPDPT", "Depth"),
  GPFSI("GPFSI", "Frequency Set Information"),
  GPGGA("GPGGA", "Global Positioning System Fix Data"),
  GPGLC("GPGLC", "Geographic Position Loran-C"),
  GPGLL("GPGLL", "Geographic Position Latitude/Longitude"),
  GPGSA("GPGSA", "GPS DOP and Active Satellites"),
  GPGSV("GPGSV", "GPS Satellites in View"),
  GPGXA("GPGXA", "TRANSIT Position"),
  GPHDG("GPHDG", "Heading Deviation & Variation"),
  GPHDT("GPHDT", "Heading True"),
  GPHSC("GPHSC", "Heading Steering Command"),
  GPLCD("GPLCD", "Loran-C Signal Data"),
  GPMTA("GPMTA", "Air Temperature (to be phased out"),
  GPMTW("GPMTW", "Water Temperature"),
  GPMWD("GPMWD", "Wind Direction"),
  GPMWV("GPMWV", "Wind Speed and Angle"),
  GPOLN("GPOLN", "Omega Lane Numbers"),
  GPOSD("GPOSD", "Own Ship Data"),
  GPR00("GPR00", "Waypoint active route (not standard"),
  GPRMA("GPRMA", "Recommended Minimum Specific Loran-C Data"),
  GPRMB("GPRMB", "Recommended Minimum Navigation Information"),
  GPRMC("GPRMC", "Recommended Minimum Specific GPS/TRANSIT Data"),
  GPROT("GPROT", "Rate of Turn"),
  GPRPM("GPRPM", "Revolutions"),
  GPRSA("GPRSA", "Rudder Sensor Angle"),
  GPRSD("GPRSD", "RADAR System Data"),
  GPRTE("GPRTE", "Routes"),
  GPSFI("GPSFI", "Scanning Frequency Information"),
  GPSTN("GPSTN", "Multiple Data ID"),
  GPTRF("GPTRF", "Transit Fix Data"),
  GPTTM("GPTTM", "Tracked Target Message"),
  GPVBW("GPVBW", "Dual Ground/Water Speed"),
  GPVDR("GPVDR", "Set and Drift"),
  GPVHW("GPVHW", "Water Speed and Heading"),
  GPVLW("GPVLW", "Distance Traveled through the Water"),
  GPVPW("GPVPW", "Speed Measured Parallel to Wind"),
  GPVTG("GPVTG", "Track Made Good and Ground Speed"),
  GPWCV("GPWCV", "Waypoint Closure Velocity"),
  GPWNC("GPWNC", "Distance Waypoint to Waypoint"),
  GPWPL("GPWPL", "Waypoint Location"),
  GPXDR("GPXDR", "Transducer Measurements"),
  GPXTE("GPXTE", "Cross-Track Error Measured"),
  GPXTR("GPXTR", "Cross-Track Error Dead Reckoning"),
  GPZDA("GPZDA", "Time & Date"),
  GPZFO("GPZFO", "UTC & Time from Origin Waypoint"),
  GPZTG("GPZTG", "UTC & Time to Destination Waypoint"),
  PGRME("PGRME", "GARMIN Estimated Position Error"),
  PGRMF("PGRMF", "GARMIN Position Fix Sentence"),
  PGRMM("PGRMM", "GARMIN Map Datum"),
  PGRMV("PGRMV", "GARMIN Velocity Sentence"),
  PGRMZ("PGRMZ", "GARMIN Altitude Information"),
  PSLIB("PSLIB", "GARMIN Differential Control");

  private String                             code;
  private String                             description;
  private static final Map<String, NMEACode> lookup = new HashMap<String, NMEACode>();

  private NMEACode(String code, String description) {
    this.code = code;
    this.description = description;
  }

  static {
    for (NMEACode p : EnumSet.allOf(NMEACode.class)) {
      lookup.put(p.getCode(), p);
    }
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static NMEACode getByCode(String code) {
    return lookup.get(code);
  }

  /**
   * Find the matching GPSd code for a NMEA sentence
   * 
   * @param sentence
   * @return
   */
  public static NMEACode lookup(String sentence) {
    Pattern p = Pattern.compile("(\\w+),");
    Matcher m = p.matcher(sentence);
    String code = null;
    if (m.find()) {
      code = m.group(1);
    }
    return lookup.get(code);
  }

  /**
   * test harness
   * 
   * @param args
   */
  public static void main(String[] args) {
    String s = "$GPGGA,085756,3856.6512,N,07714.3095,W,2,06,1.40,110.62,M,-33.942,M,,*7D";
    System.out.println("found " + NMEACode.lookup(s) + " " + NMEACode.lookup(s).description);
  }
}
