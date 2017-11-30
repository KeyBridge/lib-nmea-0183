/*
 * GNSSentence.java
 * Copyright (C) 2016 Kimmo Tuukkanen
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
package org.nmea.sentence;

/**
 * GNSS capable receivers will always output this message with the
 * <code>GN</code> talker ID. GNSS capable receivers will also output this
 * message with the <code>GP</code> and/or <code>GL</code> talker ID when using
 * more than one constellation for the position fix.
 * <p>
 * Example:
 * <pre>
 * $GNGNS,014035.00,4332.69262,S,17235.48549,E,RR,13,0.9,25.63,11.24,,*70
 * $GPGNS,014035.00,,,,,,8,,,,1.0,23*76
 * $GLGNS,014035.00,,,,,,5,,,,1.0,23*67
 * </pre>
 *
 * @author Kimmo Tuukkanen
 */
public interface GNSSentence extends Sentence, PositionSentence, TimeSentence {

  /**
   * GNS operational modes, a mix of {@link org.nmea.util.FaaMode} and
   * {@link org.nmea.util.GpsFixQuality} with some values omitted.
   */
  public static enum ModeType {
    /**
     * Operating in autonomous mode (automatic 2D/3D).
     */
    AUTOMATIC('A'),
    /**
     * Operating in manual mode (forced 2D or 3D).
     */
    MANUAL('M'),
    /**
     * Operating in differential mode (DGPS).
     */
    DGPS('D'),
    /**
     * Operating in estimating mode (dead-reckoning).
     */
    ESTIMATED('E'),
    /**
     * Real Time Kinetic, satellite system used in RTK mode with fixed integers.
     */
    RTK('R'),
    /**
     * Float RTK, satellite system used in real time kinematic mode with
     * floating integers.
     */
    FRTK('F'),
    /**
     * Precise mode, no deliberate degradation like Selective Availability (NMEA
     * 4.00 and later).
     */
    PRECISE('P'),
    /**
     * Simulated data (running in simulator/demo mode)
     */
    SIMULATED('S'),
    /**
     * No valid GPS data available.
     */
    NONE('N');

    private final char code;

    private ModeType(char c) {
      this.code = c;
    }

    public char getCode() {
      return code;
    }

    public static ModeType valueOf(char code) {
      for (ModeType m : values()) {
        if (m.getCode() == code) {
          return m;
        }
      }
      throw new IllegalArgumentException("Unrecognized code " + code);
    }
  }

  /**
   * Returns the current GPS mode.
   *
   * @return GPS operational mode
   */
  ModeType getGpsMode();

  /**
   * Sets the current GPS mode.
   *
   * @param gps GPS operational mode to set.
   */
  void setGpsMode(ModeType gps);

  /**
   * Gets the current GLONASS mode.
   *
   * @return GLONASS operational mode
   */
  ModeType getGlonassMode();

  /**
   * Sets the current GLONASS mode.
   *
   * @param gns GLONASS operational mode to set.
   */
  void setGlonassMode(ModeType gns);

  /**
   * Returns all additional operation modes, excluding GPS and GLONASS.
   *
   * @return Array of additional modes or empty array if no modes are set.
   */
  ModeType[] getAdditionalModes();

  /**
   * Sets the additional operational modes, leaving GPS and GLONASS modes
   * unaffected or setting them both <code>NONE</code> if field is empty.
   *
   * @param modes Array of additional modes to set
   */
  void setAdditionalModes(ModeType... modes);

  /**
   * Get the number of active satellites in use for currect fix.
   *
   * @return Number of satellites 0..99
   */
  int getSatelliteCount();

  /**
   * Sets the number of satellites used for current fix.
   *
   * @param count Number of satellites to set
   * @throws IllegalArgumentException If given count is out of bounds 0..99
   */
  void setSatelliteCount(int count);

  /**
   * Returns the Horizontal Dilution Of Precision, calculated using all
   * available satellites (GPS, GLONASS and any future satellites).
   *
   * @return HDOP value
   */
  double getHorizontalDOP();

  /**
   * Sets the Horizontal Dilution Of Precision value, calculated using all
   * available satellites (GPS, GLONASS and any future satellites).
   *
   * @param hdop HDOP value to set
   */
  void setHorizontalDOP(double hdop);

  /**
   * Returns the orthometric height (MSL reference).
   *
   * @return Height in meters
   */
  double getOrthometricHeight();

  /**
   * Sets the orthometric height (MSL reference).
   *
   * @param height Height to set, in meters.
   */
  void setOrthometricHeight(double height);

  /**
   * Returns geoidal separation, the difference between the earth ellipsoid
   * surface and mean-sea-level (geoid) surface defined by the reference datum
   * used in the position solution. Negative values denote mean-sea-level
   * surface below ellipsoid.
   *
   * @return Geoidal separation in meters.
   */
  double getGeoidalSeparation();

  /**
   * Returns geoidal separation, the difference between the earth ellipsoid
   * surface and mean-sea-level (geoid) surface defined by the reference datum
   * used in the position solution. Negative values denote mean-sea-level
   * surface below ellipsoid.
   *
   * @param separation Geoidal separation in meters.
   */
  void setGeoidalSeparation(double separation);

  /**
   * Returns the age of differential GPS data.
   * <p>
   * <p>
   * Notice: field is <code>null</code> when Talker ID is <code>GN</code>,
   * additional GNS messages follow with <code>GP</code> and/or <code>GL</code>
   * age of differential data.
   *
   * @return Age of differential data.
   */
  double getDgpsAge();

  /**
   * Sets the age of differential GPS data.
   * <p>
   * <p>
   * Notice: field is <code>null</code> when Talker ID is <code>GN</code>,
   * additional GNS messages follow with <code>GP</code> and/or <code>GL</code>
   * age of differential data.
   *
   * @param age Age to set, negative values will reset the field empty.
   */
  void setDgpsAge(double age);

  /**
   * Returns the differential reference station ID.
   *
   * @return Station ID, 0000..4095
   */
  String getDgpsStationId();

  /**
   * Sets the differential reference station ID.
   *
   * @param id Station ID to set, 0-4095
   */
  void setDgpsStationId(String id);

}
