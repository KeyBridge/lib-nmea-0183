/*
 * SatelliteInfoProvider.java
 * Copyright (C) 2012 Kimmo Tuukkanen
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
package ch.keybridge.lib.nmea.provider;

import org.nmea.io.SentenceReader;
import org.nmea.sentence.GSASentence;
import org.nmea.sentence.GSVSentence;
import org.nmea.sentence.Sentence;
import org.nmea.type.Satellite;
import ch.keybridge.lib.nmea.provider.event.SatelliteInfoEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * SatelliteInfoProvider collects GPS satellite information from sequence of GSV
 * sentences and reports all the information in a single event.
 *
 * @author Kimmo Tuukkanen
 */
public class SatelliteInfoProvider extends AbstractProvider<SatelliteInfoEvent> {

  /**
   * Creates a new instance of SatelliteInfoProvider with specified reader.
   *
   * @param reader Reader to scan for GSV sentences.
   */
  public SatelliteInfoProvider(SentenceReader reader) {
    super(reader, "GSA", "GSV");
  }

  /*
   * (non-Javadoc) @see
   * org.nmea.provider.AbstractProvider#createProviderEvent()
   */
  @Override
  protected SatelliteInfoEvent createProviderEvent() {

    GSASentence gsa = null;
    List<Satellite> info = new ArrayList<>();

    for (Sentence sentence : getSentences()) {
      if ("GSA".equals(sentence.getSentenceId())) {
        gsa = (GSASentence) sentence;
      } else if ("GSV".equals(sentence.getSentenceId())) {
        GSVSentence gsv = (GSVSentence) sentence;
        info.addAll(gsv.getSatelliteInfo());
      }
    }

    return new SatelliteInfoEvent(this, gsa, info);
  }

  /*
   * (non-Javadoc) @see org.nmea.provider.AbstractProvider#isReady()
   */
  @Override
  protected boolean isReady() {

    boolean hasFirstGSV = false;
    boolean hasLastGSV = false;
    boolean hasAllGSV = false;
    int count = 0;

    for (Sentence s : getSentences()) {
      if ("GSV".equals(s.getSentenceId())) {
        GSVSentence gsv = (GSVSentence) s;
        if (!hasFirstGSV) {
          hasFirstGSV = gsv.isFirst();
        }
        if (!hasLastGSV) {
          hasLastGSV = gsv.isLast();
        }
        hasAllGSV = (gsv.getSentenceCount() == ++count);
      }
    }

    return hasOne("GSA") && hasAllGSV && hasFirstGSV && hasLastGSV;
  }

  /*
   * (non-Javadoc) @see org.nmea.provider.AbstractProvider#isValid()
   */
  @Override
  protected boolean isValid() {
    return true;
  }
}
