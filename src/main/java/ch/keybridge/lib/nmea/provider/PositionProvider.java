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
package ch.keybridge.lib.nmea.provider;

import org.nmea.type.SentenceType;
import org.nmea.sentence.Sentence;
import org.nmea.sentence.GGASentence;
import org.nmea.sentence.VTGSentence;
import org.nmea.sentence.GLLSentence;
import org.nmea.sentence.RMCSentence;
import org.nmea.type.DataStatusType;
import org.nmea.type.GpsFixQualityType;
import org.nmea.type.FaaModeType;
import org.nmea.type.Position;
import org.nmea.type.Date;
import org.nmea.type.Time;
import org.nmea.io.SentenceReader;
import org.nmea.parser.DataNotAvailableException;
import ch.keybridge.lib.nmea.provider.event.PositionEvent;

/**
 * Provides Time, Position and Velocity reports from GPS. Data is captured from
 * RMC, GGA and GLL sentences. RMC is used for date/time, speed and course. GGA
 * is used as primary source for position as it contains also the altitude. When
 * GGA is not available, position may be taken from GLL or RMC. If this is the
 * case, there is no altitude included in the
 * {@link ch.keybridge.lib.nmea.util.Position}. GPS data statuses are also
 * captured and events are dispatched only when sentences report
 * {@link org.nmea.util.DataStatusType#ACTIVE}. FAA mode transmitted in
 * RMC is also checked and captured when available, but may be <code>null</code>
 * depending on used NMEA version.
 *
 * @author Kimmo Tuukkanen
 * @see ch.keybridge.lib.nmea.provider.event.PositionListener
 * @see ch.keybridge.lib.nmea.provider.event.PositionEvent
 * @see org.nmea.io.SentenceReader
 */
public class PositionProvider extends AbstractProvider<PositionEvent> {

  /**
   * Creates a new instance of PositionProvider.
   *
   * @param reader SentenceReader that provides the required sentences.
   */
  public PositionProvider(SentenceReader reader) {
    super(reader, SentenceType.RMC, SentenceType.GGA, SentenceType.GLL, SentenceType.VTG);
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.provider.AbstractProvider#createProviderEvent()
   */
  @Override
  protected PositionEvent createProviderEvent() {
    Position p = null;
    Double sog = null;
    Double cog = null;
    Date d = null;
    Time t = null;
    FaaModeType mode = null;
    GpsFixQualityType fix = null;

    for (Sentence s : getSentences()) {
      if (s instanceof RMCSentence) {
        RMCSentence rmc = (RMCSentence) s;
        sog = rmc.getSpeed();
        try {
          cog = rmc.getCourse();
        } catch (DataNotAvailableException e) {
          // If we are not moving, cource can be undefined. Leave null in that case.
        }
        d = rmc.getDate();
        t = rmc.getTime();
        if (p == null) {
          p = rmc.getPosition();
          if (rmc.getFieldCount() > 11) {
            mode = rmc.getMode();
          }
        }
      } else if (s instanceof VTGSentence) {
        VTGSentence vtg = (VTGSentence) s;
        sog = vtg.getSpeedKnots();
        try {
          cog = vtg.getTrueCourse();
        } catch (DataNotAvailableException e) {
          // If we are not moving, cource can be undefined. Leave null in that case.
        }
      } else if (s instanceof GGASentence) {
        // Using GGA as primary position source as it contains both
        // position and altitude
        GGASentence gga = (GGASentence) s;
        p = gga.getPosition();
        fix = gga.getFixQuality();

        // Some receivers do not provide RMC message
        if (t == null) {
          t = gga.getTime();
        }
      } else if (s instanceof GLLSentence && p == null) {
        GLLSentence gll = (GLLSentence) s;
        p = gll.getPosition();
      }
    }

    // Ag-Star reciever does not provide RMC sentence. So we have to guess what date it is
    if (d == null) {
      d = new Date();
    }

    return new PositionEvent(this, p, sog, cog, d, t, mode, fix);
  }

  /*
   * (non-Javadoc) @see org.nmea.provider.AbstractProvider#isReady()
   */
  @Override
  protected boolean isReady() {
    return hasOne("RMC", "VTG") && hasOne("GGA", "GLL");
  }

  /*
   * (non-Javadoc) @see org.nmea.provider.AbstractProvider#isValid()
   */
  @Override
  protected boolean isValid() {

    for (Sentence s : getSentences()) {

      if (s instanceof RMCSentence) {
        RMCSentence rmc = (RMCSentence) s;
        DataStatusType ds = rmc.getStatus();
        if (DataStatusType.VOID.equals(ds)
          || (rmc.getFieldCount() > 11 && FaaModeType.NONE.equals(rmc.getMode()))) {
          return false;
        }
      } else if (s instanceof GGASentence) {
        GpsFixQualityType fq = ((GGASentence) s).getFixQuality();
        if (GpsFixQualityType.INVALID.equals(fq)) {
          return false;
        }
      } else if (s instanceof GLLSentence) {
        DataStatusType ds = ((GLLSentence) s).getStatus();
        if (DataStatusType.VOID.equals(ds)) {
          return false;
        }
      }
    }

    return true;
  }
}
