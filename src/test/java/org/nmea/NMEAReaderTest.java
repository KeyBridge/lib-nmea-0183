/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmea;

import org.nmea.dev.sentence.PGRME;
import org.nmea.dev.sentence.NMEACode;
import org.nmea.dev.sentence.GPGSA;
import org.nmea.dev.sentence.GPRMC;
import org.nmea.dev.sentence.GPGGA;
import org.nmea.dev.NMEAReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jesse
 */
public class NMEAReaderTest {

  public NMEAReaderTest() {
  }

  @Before
  public void setUp() {
  }

  @Test
  public void testNMEAReader() {

    // Test messages
    String zda = "$GPZDA,201530.00,04,07,2002,00,00*60";
    String gll = "$GPGLL,4916.45,N,12311.12,W,225444,A,*1D";
    String gga = "$GPGGA,123519,4807.038,N,01131.000,E,1,08,0.9,545.4,M,46.9,M,,*47";
    String rmc = "$GPRMC,123519,A,4807.038,N,01131.000,E,022.4,084.4,230394,003.1,W*6A";

    System.out.println(zda);

    NMEAReader nmea = new NMEAReader();
    NMEAReader.decodeZDA(zda);

    System.out.println(gll);
    nmea.decodeGLL(gll);

//    System.out.println("lat: " + nmea.latitude + "  lon: " + nmea.longitude);
    Calendar mycal = new GregorianCalendar();
    nmea.encodeZDA(mycal);
    System.out.println("encodeZDA: " + nmea.getZDA());

    System.out.println(gga);
    nmea.decodeGGA(gga);

    Calendar foocall = nmea.getCalendar();

    System.out.println(foocall.get(Calendar.HOUR_OF_DAY));
    System.out.println(foocall.get(Calendar.MINUTE));
    System.out.println(foocall.get(Calendar.SECOND));

    NMEAReader newnmea = new NMEAReader(gga);
    System.out.println("w/constructor: lat: " + newnmea.getLatitude());

    NMEAReader gllnmea = new NMEAReader(-44.5, -123.34);
    System.out.println("latlon: " + gllnmea.getGLL());

    NMEAReader nmrmc = new NMEAReader(rmc);
    System.out.println(nmrmc.toString());

  }

  @Test
  public void testPGRME() {
    String nmea = "$PGRME,0.00,M,0.00,M,6.73,M*1C";
    PGRME g = new PGRME(nmea);
    System.out.println(g.toString());
  }

  @Test
  public void testNMEACode() {
    String GPGGA = "$GPGGA,085756,3856.6512,N,07714.3095,W,2,06,1.40,110.62,M,-33.942,M,,*7D";
    Assert.assertEquals(NMEACode.parse(GPGGA), NMEACode.GPGGA);

    String GPBWC = "$GPBWC,220516,5130.02,N,00046.34,W,213.8,T,218.0,M,0004.6,N,EGLM*11";
    Assert.assertEquals(NMEACode.parse(GPBWC), NMEACode.GPBWC);

    String GPGLL = "$GPGLL,5133.81,N,00042.25,W*75    ";
    Assert.assertEquals(NMEACode.parse(GPGLL), NMEACode.GPGLL);

    String GPGSV = "$GPGSV,1,1,13,02,02,213,,03,-3,000,,11,00,121,,14,13,172,05*67";
    Assert.assertEquals(NMEACode.parse(GPGSV), NMEACode.GPGSV);

    System.out.println("NMEA parse ok");
  }

  @Test
  public void testGPRMC() {
    String nmea = "$GPRMC,085756,A,3856.6512,N,07714.3095,W,0.5052,174.938,030510,,*31";
    GPRMC g = new GPRMC(nmea);
    System.out.println(g.getDescription());
  }

  @Test
  public void testGPGGA() {
    String gga = "$GPGGA,085756,3856.6512,N,07714.3095,W,2,06,1.40,110.62,M,-33.942,M,,*7D";
    GPGGA g = new GPGGA(gga);
    System.out.println(g.getDescription());
  }

  @Test
  public void testGPGSA() {
    String nmea = "$GPGSA,A,3,02,17,05,12,04,10,,,,,,,2.9,1.4,2.6*3F";
    GPGSA g = new GPGSA(nmea);
    System.out.println(g.getDescription());
  }
}
