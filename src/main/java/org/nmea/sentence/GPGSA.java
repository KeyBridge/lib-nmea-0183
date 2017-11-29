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

import java.util.ArrayList;
import java.util.List;
import org.nmea.NMEAReader;

/**
 * $GPGSA - GPS DOP and active satellites
 * <pre>
 *
 * eg1. $GPGSA,A,3,,,,,,16,18,,22,24,,,3.6,2.1,2.2*3C
 * eg2. $GPGSA,A,3,19,28,14,18,27,22,31,39,,,,,1.7,1.0,1.3*35
 *
 * 1    = GPSMode:
 *        M=Manual, forced to operate in 2D or 3D
 *        A=Automatic, 3D/2D
 * 2    = GPSMode:
 *        1=Fix not available
 *        2=2D
 *        3=3D
 * 3-14 = IDs of SVs used in position fix (null for unused fields)
 * 15   = PDOP
 * 16   = HDOP
 * 17   = VDOP
 */
public class GPGSA {

  /**
   * The NMEA sentence.
   */
  private final String nmeaSentence;
  /**
   * Automatic GPS mode indicator.
   */
  private final boolean automatic;
  /**
   * GPSMode: M=Manual, forced to operate in 2D or 3D A=Automatic, 3D/2D
   */
  private final NMEAMode mode;
  /**
   * IDs of SVs used in position fix (null for unused fields)
   */
  private final List<Integer> satellites = new ArrayList<>();
  /**
   * dilution of precision (PDOP) for position
   */
  private final double pdop;
  /**
   * the horizontal dilution Of precision (HDOP)
   */
  private final double hdop;
  /**
   * the vertical dilution Of precision (VDOP)
   */
  private final double vdop;

  public GPGSA(String nmeaSentence) {
    // Set the sentence
    this.nmeaSentence = nmeaSentence;
    // Tokenize
    String[] tokens = NMEAReader.tokenize(nmeaSentence);
    // Set values
    automatic = tokens[1].contains("A");
    mode = NMEAMode.getByMode(Integer.valueOf(tokens[2]));
    pdop = Double.valueOf(tokens[15]);
    hdop = Double.valueOf(tokens[16]);
    vdop = Double.valueOf(tokens[17]);
    // Set the satellite values
    for (int i = 3; i < 14; i++) {
      if (tokens[i] != null && !tokens[i].trim().isEmpty()) {
        satellites.add(Integer.valueOf(tokens[i]));
      }
    }
  }

  public boolean isAutomatic() {
    return automatic;
  }

  public double getHdop() {
    return hdop;
  }

  public String getMode() {
    return mode.label;
  }

  public String getNmeaSentence() {
    return nmeaSentence;
  }

  public double getPdop() {
    return pdop;
  }

  /**
   * Returns the satellite PRN identifiers
   *
   * @return
   */
  public List<Integer> getSatellites() {
    return satellites;
  }

  public double getVdop() {
    return vdop;
  }

  public String getDescription() {
    return "GPGSA: automatic [" + automatic + "] mode [" + mode.label + "] pdop [" + pdop + "] hdop [" + hdop + "] vdop [" + vdop + "] satellites [" + satellites.toString() + "] ";
  }

  @Override
  public String toString() {
    return nmeaSentence;
  }

  public static enum NMEAMode {
    NOT_AVAILABLE(1, "Fix not available"),
    TWO_D(2, "2D"),
    THREE_D(3, "3D");

    private final int mode;
    private final String label;

    private NMEAMode(int mode, String label) {
      this.mode = mode;
      this.label = label;
    }

    @SuppressWarnings("unused")
    public String getLabel() {
      return label;
    }

    public int getMode() {
      return mode;
    }

    public static NMEAMode getByMode(int mode) {
      for (NMEAMode v : NMEAMode.values()) {
        if (v.getMode() == mode) {
          return v;
        }
      }
      throw new IllegalArgumentException("Unrecognized mode " + mode);
    }
  }
}
