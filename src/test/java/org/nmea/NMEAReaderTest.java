/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmea;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
}
