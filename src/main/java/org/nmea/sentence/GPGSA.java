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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * $GPGSA - GPS DOP and active satellites
 * <p>
 * 
 * <pre>
 * 
 * eg1. $GPGSA,A,3,,,,,,16,18,,22,24,,,3.6,2.1,2.2*3C
 * eg2. $GPGSA,A,3,19,28,14,18,27,22,31,39,,,,,1.7,1.0,1.3*35
 * 
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

  private String        nmeaSentence;
  private boolean       automatic;
  private NMEAMode      mode;
  private List<Integer> satellites = new ArrayList<Integer>();
  private double        pdop;
  private double        hdop;
  private double        vdop;

  public GPGSA(String nmeaSentence) {
    // Set the sentence
    this.nmeaSentence = nmeaSentence;
    // Tokenize
    String[] tokens = nmeaSentence.split(",");
    // Set values
    automatic = tokens[1].contains("A") ? true : false;
    mode = NMEAMode.getByMode(Integer.valueOf(tokens[2]));
    pdop = Double.valueOf(tokens[15]);
    hdop = Double.valueOf(tokens[16]);
    // Set the satellite values
    for (int i = 3; i < 14; i++) {
      if (tokens[i] == null ? "" != null : !tokens[i].equals("")) {
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

  @Override
  public String toString() {
    return "GPGSA: automatic [" + automatic + "] mode [" + mode.label + "] pdop [" + pdop + "] hdop [" + hdop + "] vdop [" + vdop + "] satellites [" + satellites.toString() + "] ";
  }

  /**
   * Test harness
   * 
   * @param args
   */
  public static void main(String[] args) {
    String nmea = "$GPGSA,A,3,02,17,05,12,04,10,,,,,,,2.9,1.4,2.6*3F";
    GPGSA g = new GPGSA(nmea);
    System.out.println(g.toString());
  }

  private enum NMEAMode {
    NotAvailable(1, "Fix not available"),
    TwoD(2, "2D"),
    ThreeD(3, "3D");

    private int                                 mode;
    private String                              label;
    private static final Map<Integer, NMEAMode> lookup = new HashMap<Integer, NMEAMode>();
    static {
      for (NMEAMode p : EnumSet.allOf(NMEAMode.class)) {
        lookup.put(p.getMode(), p);
      }
    }

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
      return lookup.get(mode);
    }
  }
}
