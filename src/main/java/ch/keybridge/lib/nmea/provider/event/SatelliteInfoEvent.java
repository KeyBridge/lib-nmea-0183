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
package ch.keybridge.lib.nmea.provider.event;

import org.nmea.sentence.GSASentence;
import org.nmea.type.FaaModeType;
import org.nmea.type.GpsFixStatusType;
import org.nmea.type.Satellite;
import java.util.List;

/**
 * SatelliteInfoEvent contains the satellite information collected by
 * {@link org.nmea.provider.SatelliteInfoProvider}.
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.sentence.GSASentence
 * @see org.nmea.sentence.GSVSentence
 * @see org.nmea.type.Satellite
 */
public class SatelliteInfoEvent extends ProviderEvent {

  private static final long serialVersionUID = -5243047395130051907L;

  private final GSASentence gsa;
  private final List<Satellite> info;

  /**
   * @param source
   */
  public SatelliteInfoEvent(
    Object source, GSASentence gsa, List<Satellite> info) {
    super(source);
    this.gsa = gsa;
    this.info = info;
  }

  /**
   * Returns the list of GPS satellites used for GPS fix.
   *
   * @return Satellite ids list as reported by GSA sentence.
   */
  public String[] getSatelliteIds() {
    return gsa.getSatelliteIds();
  }

  /**
   * Returns the current detailed satellite information.
   *
   * @return List of SatelliteInfo objects from latest GSV sequence.
   */
  public List<Satellite> getSatelliteInfo() {
    return this.info;
  }

  /**
   * Returns the horizontal precision of GPS fix.
   *
   * @return HDOP value as reported by GSA sentence.
   */
  public double getHorizontalPrecision() {
    return gsa.getHorizontalDOP();
  }

  /**
   * Returns the vertical precision of GPS fix.
   *
   * @return VDOP as reported by GSA sentence.
   */
  public double getVerticalPrecision() {
    return gsa.getVerticalDOP();
  }

  /**
   * Returns the overall precision of GPS fix.
   *
   * @return PDOP as reported by GSA sentence.
   */
  public double getPositionPrecision() {
    return gsa.getPositionDOP();
  }

  /**
   * Returns the GPS mode of operation as reported in GSA sentence.
   *
   * @return FaaMode enum value
   */
  public FaaModeType getGpsMode() {
    return gsa.getMode();
  }

  /**
   * Returns the GPS fix status as reported by GSA sentence.
   *
   * @return GpsFixStatus enum value
   */
  public GpsFixStatusType getGpsFixStatus() {
    return gsa.getFixStatus();
  }
}
