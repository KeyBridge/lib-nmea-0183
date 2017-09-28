# NMEA 0183 GPS data reader and writer.

NMEA 0183 is a combined electrical and data specification for communication between marine electronics such as
echo sounder, sonars, anemometer, gyrocompass, autopilot, GPS receivers and many other types of instruments.

The NMEA 0183 standard uses a simple ASCII, serial communications protocol that defines how data are
transmitted in a "sentence" from one "talker" to multiple "listeners" at a time. Through the use of intermediate
expanders, a talker can have a unidirectional conversation with a nearly unlimited number of listeners, and using
multiplexers, multiple sensors can talk to a single computer port.

This project reads and writes NMEA GPS sentences.

https://www.nmea.org/

## LICENSING

This is free software: you can redistribute it and/or modify it
under the terms of the GNU Lesser General Public License as published by the
Free Software Foundation, either version 3 of the License, or (at your
option) any later version.

This software is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
for more details.

You should have received a copy of the GNU Lesser General Public License
along with this software. If not, see <http://www.gnu.org/licenses/>.

## DISCLAIMER

This is not official NMEA software. Further, it is not related to
National Marine Electronics Association <http://www.nmea.org/>. The decoding of
NMEA-0183 sentences is based entirely on publicly available resources in the
Internet. Thus, it is NOT guaranteed that the library would follow and implement
the standard accurately and correctly.

## SOURCE CODE

The source code is maintained in Github <http://www.github.com>.

Browse code at Github.com: <https://github.com/keybridge/lib-nmea-0183>

If you wish to contribute new code or bug fixes, please fork and send pull requests in Github.

## DEVELOPERS

 * Key Bridge
 * Some classes are based on code imported from the **Marine API** (GPL) project by Kimmo Tuukkanen <kimmo.tuukkanen@gmail.com>

# Marine Automatic Identification System (AIS)

AIS is composed of small bursts of data sent over normal marine VHF but using GMSK modulation instead of FM to identify vessels, their position, and telemetry. The data is encoded into a bit pattern (bit vector) to make it as small as possible. The NMEA standard uses two primary sentences to for AIS data !AIVDM (Received Data from other vessels) and !AIVDO (Your own vessels information)

See ITU M.1371-2 for a complete definition of the AIS Data Format.

# REFERENCES

The NMEA 0183 information has been acquired from many publicly available documents including various of the following:
 * [NMEA 0183 article in Wikipedia](http://en.wikipedia.org/wiki/NMEA_0183)
 * [NMEA Revealed by Eric S. Raymond](http://catb.org/gpsd/NMEA.html)
 * [SiRF NMEA Reference Manual (Rev. 2.1, Dec 2007) by SiRF Technology, Inc.](https://www.sparkfun.com/datasheetsGPS/NMEA%20Reference%20Manual-Rev2.1-Dec07.pdf)
 * [The NMEA Information Sheet (issue 3, 25th Feb 2011) by Actisense](http://www.actisense.com/products/nmea-0183/opto-4/downloads-opto-4.html)
 * [PB100 WeatherStation Technical Manual by Airmar](http://www.airmartechnology.com/uploads/installguide/PB100TechnicalManual_rev1.007.pdf)
 * [RT Intertial+ NMEA Description (rev. 100720) by Oxford Technical Solutions](http://www.oxts.com/Downloads/Products/Inertial2/nmeaman.pdf)
 * [BD9xx GNSS Receivers Help by Trimble Navigation Limited](http://www.trimble.com/OEM_ReceiverHelp/V4.44/en/)
 * [NMEA-0183 Messages - Guide for AgGPS Receivers by Trimble Navigation Limited](http://trl.trimble.com/docushare/dsweb/Get/Document-159714/NMEA_Messages_RevA_Guide_ENG.pdf)
 * [Eye4Software GPS Toolkit - Description of supported NMEA0183 sentences](http://www.eye4software.com/products/gpstoolkit/nmea/)
 * [NMEA International Conference & Expo - Standards Update October 2014 by Steve Spitzer](http://www.nmea.org/Assets/20141004%20nmea%20standards%20update%20for%202014%20conference.pdf)
 * [NMEA 0183 Sentences Not Recommended for New Designs by NMEA](http://www.nmea.org/Assets/100108_nmea_0183_sentences_not_recommended_for_new_designs.pdf)
 * [NMEA Sentence Information by Glenn Baddeley](http://home.mira.net/~gnb/gps/nmea.html)
 * [NMEA Data by Dale DePriest](http://www.gpsinformation.org/dale/nmea.htm)
 * [The NMEA FAQ by Peter Bennett](http://www.eoss.org/pubs/nmeafaq.htm)
 * [Automatic Identification System Overview by Navigation Center of U.S. Department of Homeland Security](http://www.navcen.uscg.gov/?pageName=AISMessages)
 * [SeaTalk Technical Reference (revision 3.22) by Thomas Knauf](http://www.thomasknauf.de/seatalk.htm)
 * [RS232 SeaTalk (-) NMEA Converter installation and operation manual by gadgetPool](http://www.gadgetpool.de/nuke/downloads/ManualRS232.pdf)

All warnings concerning the accuracy of information in above documents apply equally to this software.

