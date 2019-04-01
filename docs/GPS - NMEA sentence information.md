# GPS - NMEA sentence information

**Contents**

*  [Interpreted sentences](interp)
*  [Garmin proprietary sentences](#garmin)
*  [All $GPxxx sentence codes and short descriptions](#allgp)
*  [Format of latitudes and longitudes](#latlong)
*  [References](#refs)

---

**Interpreted sentences**

*  [$GPBOD - Bearing, origin to destination](#bod)
*  [$GPBWC - Bearing and distance to waypoint, great circle](#bwc)
*  [$GPGGA - Global Positioning System Fix Data](#gga)
*  [$GPGLL - Geographic position, latitude / longitude](#gll)
*  [$GPGSA - GPS DOP and active satellites](#gsa)
*  [$GPGSV - GPS Satellites in view](#gsa)
*  [$GPHDT - Heading, True](#hdt)
*  [$GPR00 - List of waypoints in currently active route](#r00)
*  [$GPRMA - Recommended minimum specific Loran-C data](#rma)
*  [$GPRMB - Recommended minimum navigation info](#rmb)
*  [$GPRMC - Recommended minimum specific GPS/Transit data](#rmc)
*  [$GPRTE - Routes](#rte)
*  [$GPTRF - Transit Fix Data](#trf)
*  [$GPSTN - Multiple Data ID](#stn)
*  [$GPVBW - Dual Ground / Water Speed](#vbw)
*  [$GPVTG - Track made good and ground speed](#vtg)
*  [$GPWPL - Waypoint location](#wpl)
*  [$GPXTE - Cross-track error, Measured](#xte)
*  [$GPZDA - Date and Time](#zda)


<a name="bod"/>

## $GPBOD

Bearing Origin to Destination


Example  `BOD,045.,T,023.,M,DEST,START`

|---|---|
|  045.,T     |  bearing 045 degrees True from "START" to "DEST" |
|  023.,M     |  breaing 023 degrees Magnetic from "START" to "DEST" |
|  DEST       |  destination waypoint ID |
|  START      |  origin waypoint ID |


Example 1: `$GPBOD,099.3,T,105.6,M,POINTB,*01`

Waypoint ID: "POINTB" Bearing 99.3 True, 105.6 Magnetic

This sentence is transmitted in the GOTO mode, without an active route on your GPS.

WARNING: this is the bearing from the moment you press enter in the GOTO
page to the destination waypoint and is NOT updated dynamically!
To update the information, (current bearing to waypoint), you will have to press enter in the GOTO page again.

Example 2: `$GPBOD,097.0,T,103.2,M,POINTB,POINTA*52`

This sentence is transmitted when a route is active. It contains the
active leg information: origin waypoint "POINTA" and destination
waypoint "POINTB", bearing between the two points 97.0 True, 103.2
Magnetic. It does NOT display the bearing from current location to
destination waypoint!

WARNING Again this information does not change until you are on the next
leg of the route. (The bearing from POINTA to POINTB does not change
during the time you are on this leg.)

<a name="bwc"/>

## $GPBWC

Bearing and distance to waypoint, great circle

Example: `BWC,225444,4917.24,N,12309.57,W,051.9,T,031.6,M,001.3,N,004*29`

|---|---|
|  225444     |  UTC time of fix 22:54:44 |
|  4917.24,N  |  Latitude of waypoint |
|  12309.57,W |  Longitude of waypoint |
|  051.9,T    |  Bearing to waypoint, degrees true |
|  031.6,M    |  Bearing to waypoint, degrees magnetic |
|  001.3,N    |  Distance to waypoint, Nautical miles |
|  004        |  Waypoint ID |

Example: `$GPBWC,220516,5130.02,N,00046.34,W,213.8,T,218.0,M,0004.6,N,EGLM*11`

|---|---|
|  220516  |  timestamp |
|  5130.02 |  Latitude of next waypoint|
|  N       |  North/South|
|  00046.34 |  Longitude of next waypoint|
|  W      |   East/West|
|  213.0  |   True track to waypoint |
|  T      |   True Track |
|  218.0  |   Magnetic track to waypoint |
|  M      |   Magnetic |
|  0004.6 |   range to waypoint |
|  N      |   unit of range to waypoint, N = Nautical miles |
|  EGLM   |   Waypoint name |
|  *11    |   checksum |

<a name="gga"/>

## $GPGGA

Global Positioning System Fix Data


|---|---|---|
|Name |	Example Data |	Description |
|Sentence Identifier |	$GPGGA 	| Global Positioning System Fix Data |
|Time 	| 170834 	| 17:08:34 Z |
|Latitude 	| 4124.8963, N  |	41d 24.8963' N or 41d 24' 54" N |
|Longitude 	| 08151.6838, W |	81d 51.6838' W or 81d 51' 41" W |
|Fix Quality| 1 | Data is from a GPS fix. - 0 = Invalid, - 1 = GPS fix, - 2 = DGPS fix 	 |
|Number of Satellites |	05 |	5 Satellites are in view |
|Horizontal Dilution of Precision (HDOP) |	1.5 |	Relative accuracy of horizontal position |
|Altitude |	280.2, M |	280.2 meters above mean sea level |
|Height of geoid above WGS84 ellipsoid |	-34.0, M 	| -34.0 meters |
|Time since last DGPS update |	blank |	No last update |
|DGPS reference station id |	blank |	No station id |
|Checksum |	*75 |	Used by program to check for transmission errors   |


Global Positioning System Fix Data.

Time, position and fix related data for a GPS receiver.

Example: `$--GGA,hhmmss.ss,llll.ll,a,yyyyy.yy,a,x,xx,x.x,x.x,M,x.x,M,x.x,xxxx*hh`

|---|---|
|hhmmss.ss | UTC of position |
|llll.ll | latitude of position |
|a | N or S |
|yyyyy.yy | Longitude of position |
|a | E or W |
|x | GPS Quality indicator (0=no fix, 1=GPS fix, 2=Diff. GPS fix) |
|xx | Number of satellites in use [not those in view] |
|x.x | Horizontal dilution of precision |
|x.x | Antenna altitude above mean-sea-level |
|M | Units of antenna altitude, meters |
|x.x | Geoidal separation (Diff. between WGS-84 earth ellipsoid and mean sea level |
|M | Units of geoidal separation, meters |
|x.x | Age of Differential GPS data (seconds) |
|xxxx | Differential reference station ID |
|*hh | Checksum |



<a name="gll"/>
## $GPGLL

Geographic Position, Latitude / Longitude and time.

Example:  `$GPGLL,4916.45,N,12311.12,W,225444,A`

|---|---|
|  4916.46,N   | Latitude 49 deg. 16.45 min. North |
|  12311.12,W  | Longitude 123 deg. 11.12 min. West |
|  225444      | Fix taken at 22:54:44 UTC |
|  A           | Data valid |

Example:  `$GPGLL,5133.81,N,00042.25,W*75`

|---|---|
|  5133.81  | Current latitude |
|  N        | North/South |
|  00042.25 | Current longitude |
|  W        | East/West |
|  *75      | checksum |


`$--GLL,lll.ll,a,yyyyy.yy,a,hhmmss.ss,A  llll.ll`

|---|---|
|a | N or S |
|yyyyy.yy | Longitude of position |
|a | E or W |
|hhmmss.ss | UTC of position |
|A | status: A = valid data |

<a name="gsa"/>

## $GPGSA

GPS DOP and active satellites

```nmea
Example:  $GPGSA,A,3,,,,,,16,18,,22,24,,,3.6,2.1,2.2*3C
Example:  $GPGSA,A,3,19,28,14,18,27,22,31,39,,,,,1.7,1.0,1.3*35

1    = Mode:
M=Manual, forced to operate in 2D or 3D
A=Automatic, 3D/2D
2    = Mode:
1=Fix not available
2=2D
3=3D
3-14 = IDs of SVs used in position fix (null for unused fields)
15   = PDOP
16   = HDOP
17   = VDOP
```

<a name="gsv"/>

## $GPGSV

GPS Satellites in view

```nmea
Example: . $GPGSV,3,1,11,03,03,111,00,04,15,270,00,06,01,010,00,13,06,292,00*74
$GPGSV,3,2,11,14,25,170,00,16,57,208,39,18,67,296,40,19,40,246,00*74
$GPGSV,3,3,11,22,42,067,42,24,14,311,43,27,05,244,00,,,,*4D

$GPGSV,1,1,13,02,02,213,,03,-3,000,,11,00,121,,14,13,172,05*67

1    = Total number of messages of this type in this cycle
2    = Message number
3    = Total number of SVs in view
4    = SV PRN number
5    = Elevation in degrees, 90 maximum
6    = Azimuth, degrees from true north, 000 to 359
7    = SNR, 00-99 dB (null when not tracking)
8-11 = Information about second SV, same as field 4-7
12-15= Information about third SV, same as field 4-7
16-19= Information about fourth SV, same as field 4-7
```

<a name="hdt"/>
## $GPHDT

Heading, True.

Actual vessel heading in degrees Ture produced by any device or system producing true heading.

`$--HDT,x.x,T`

x.x = Heading, degrees True

<a name="r00"/>

## $GPR00

List of waypoint IDs in currently active route

Example: 1. `$GPR00,EGLL,EGLM,EGTB,EGUB,EGTK,MBOT,EGTB,,,,,,,*58`

Example: 2. `$GPR00,MINST,CHATN,CHAT1,CHATW,CHATM,CHATE,003,004,005,006,007,,,*05`

List of waypoints. This alternates with $GPWPL cycle which itself cycles waypoints.

<a name="rma"/>

## $GPRMA

Recommended minimum specific Loran-C data

```nmea
Example: . $GPRMA,A,llll.ll,N,lllll.ll,W,,,ss.s,ccc,vv.v,W*hh
1    = Data status
2    = Latitude
3    = N/S
4    = longitude
5    = W/E
6    = not used
7    = not used
8    = Speed over ground in knots
9    = Course over ground
10   = Variation
11   = Direction of variation E/W
12   = Checksum
```

<a name="rmb"/>

## $GPRMB

Recommended minimum navigation information (sent by nav.  receiver when a destination waypoint is active)

Example: `$GPRMB,A,0.66,L,003,004,4917.24,N,12309.57,W,001.3,052.5,000.5,V*0B`

```
A            Data status A = OK, V = warning
0.66,L       Cross-track error (nautical miles, 9.9 max.),
steer Left to correct (or R = right)
003          Origin waypoint ID
004          Destination waypoint ID
4917.24,N    Destination waypoint latitude 49 deg. 17.24 min. N
12309.57,W   Destination waypoint longitude 123 deg. 09.57 min. W
001.3        Range to destination, nautical miles
052.5        True bearing to destination
000.5        Velocity towards destination, knots
V            Arrival alarm  A = arrived, V = not arrived
*0B          mandatory checksum
```

Example: `$GPRMB,A,4.08,L,EGLL,EGLM,5130.02,N,00046.34,W,004.6,213.9,122.9,A*3D`

```
A         validity
4.08      off track
L         Steer Left (L/R)
EGLL      last waypoint
EGLM      next waypoint
5130.02   Latitude of Next waypoint
N         North/South
00046.34  Longitude of next waypoint
W         East/West
004.6     Range
213.9     bearing to waypt.
122.9     closing velocity
A         validity
*3D       checksum
```

Example: `$GPRMB,A,x.x,a,c--c,d--d,llll.ll,e,yyyyy.yy,f,g.g,h.h,i.i,j*kk`

```
1    = Data Status (V=navigation receiver warning)
2    = Crosstrack error in nautical miles
3    = Direction to steer (L or R) to correct error
4    = Origin waypoint ID#
5    = Destination waypoint ID#
6    = Destination waypoint latitude
7    = N or S
8    = Destination waypoint longitude
9    = E or W
10   = Range to destination in nautical miles
11   = Bearing to destination, degrees True
12   = Destination closing velocity in knots
13   = Arrival status; (A=entered or perpendicular passed)
14   = Checksum
```

<a name="rmc"/>

## $GPRMC

Recommended minimum specific GPS/Transit data

Format:  `$GPRMC,hhmmss.ss,A,llll.ll,a,yyyyy.yy,a,x.x,x.x,ddmmyy,x.x,a*hh`

```
1    = UTC of position fix
2    = Data status (V=navigation receiver warning)
3    = Latitude of fix
4    = N or S
5    = Longitude of fix
6    = E or W
7    = Speed over ground in knots
8    = Track made good in degrees True
9    = UT date
10   = Magnetic variation degrees (Easterly var. subtracts from true course)
11   = E or W
12   = Checksum
```

Example:  `$GPRMC,225446,A,4916.45,N,12311.12,W,000.5,054.7,191194,020.3,E*68`

```
225446       Time of fix 22:54:46 UTC
A            Navigation receiver warning A = OK, V = warning
4916.45,N    Latitude 49 deg. 16.45 min North
12311.12,W   Longitude 123 deg. 11.12 min West
000.5        Speed over ground, Knots
054.7        Course Made Good, True
191194       Date of fix  19 November 1994
020.3,E      Magnetic variation 20.3 deg East
*68          mandatory checksum
```

Example:  `$GPRMC,220516,A,5133.82,N,00042.24,W,173.8,231.8,130694,004.2,W*70`

```
220516     Time Stamp
A          validity - A-ok, V-invalid
5133.82    current Latitude
N          North/South
00042.24   current Longitude
W          East/West
173.8      Speed in knots
231.8      True course
130694     Date Stamp
004.2      Variation
W          East/West
*70        checksum
```



<a name="rte"/>

## $GPRTE

Routes

Example: `$GPRTE,2,1,c,0,PBRCPK,PBRTO,PTELGR,PPLAND,PYAMBU,PPFAIR,PWARRN,PMORTL,PLISMR*73`

Example: `$GPRTE,2,2,c,0,PCRESY,GRYRIE,GCORIO,GWERR,GWESTG,7FED*34`

```
1 = Number of sentences in sequence
2 = Sentence number
3 = 'c' = Current active route, 'w' = waypoint list starts with destination waypoint
4 = Name or number of the active route
5 onwards = Names of waypoints in Route
```


<a name="trf"/>

## $GPTRF

Transit Fix Data

Time, date, position, and information related to a TRANSIT Fix.

Format `$--TRF,hhmmss.ss,xxxxxx,llll.ll,a,yyyyy.yy,a,x.x,x.x,x.x,x.x,xxx`

```
hhmmss.ss = UTC of position fix
xxxxxx = Date: dd/mm/yy
llll.ll,a = Latitude of position fix, N/S
yyyyy.yy,a = Longitude of position fix, E/W
x.x = Elevation angle
x.x = Number of iterations
x.x = Number of Doppler intervals
x.x = Update distance, nautical miles
x.x = Satellite ID
```

<a name="stn"/>

## $GPSTN

Multiple Data ID.

This sentence is transmitted before each individual sentence where there is a need for
the Listener to determine the exact source of data in the system. Examples might
include dual-frequency depthsounding equipment or equipment that integrates data
from a number of sources and produces a single output.

Format: `$--STN,xx`

xx = Talker ID number, 00 to 99

<a name="vbw"/>

## $GPVBW

Dual Ground / Water Speed

Water referenced and ground referenced speed data.

Format: `$--VBW,x.x,x.x,A,x.x,x.x,A`

```
x.x = Longitudinal water speed, knots
x.x = Transverse water speed, knots
A = Status: Water speed, A = Data valid
x.x = Longitudinal ground speed, knots
x.x = Transverse ground speed, knots
A = Status: Ground speed, A = Data valid
```

<a name="vtg"/>

## $GPVTG

Track Made Good and Ground Speed.

Format  `$GPVTG,t,T,,,s.ss,N,s.ss,K*hh`

```
1    = Track made good
2    = Fixed text 'T' indicates that track made good is relative to true north
3    = not used
4    = not used
5    = Speed over ground in knots
6    = Fixed text 'N' indicates that speed over ground in in knots
7    = Speed over ground in kilometers/hour
8    = Fixed text 'K' indicates that speed over ground is in kilometers/hour
9    = Checksum
```

The actual track made good and speed relative to the ground.

Format:  `$--VTG,x.x,T,x.x,M,x.x,N,x.x,K`

```
x.x,T = Track, degrees True
x.x,M = Track, degrees Magnetic
x.x,N = Speed, knots
x.x,K = Speed, Km/hr
```

Example:  `$GPVTG,360.0,T,348.7,M,000.0,N,000.0,K*43`
Example:  `$GPVTG,054.7,T,034.4,M,005.5,N,010.2,K`

```
054.7,T      True track made good
034.4,M      Magnetic track made good
005.5,N      Ground speed, knots
010.2,K      Ground speed, Kilometers per hour
```

<a name="wpl"/>

## $GPWPL

Waypoint location

When a route is active, this sentence is sent once for each
waypoint in the route, in sequence. When all waypoints have
been reported, GPR00 is sent in the next data set. In any
group of sentences, only one WPL sentence, or an R00
sentence, will be sent.

Example:   `$GPWPL,5128.62,N,00027.58,W,EGLL*59`

```
1    5128.62   Latitude of nth waypoint on list
2    N         North/South
3    00027.58  Longitude of nth waypoint
4    W         East/West
5    EGLL      Ident of nth waypoint
6    *59       checksum
```

<a name="xte"/>

## $GPXTE

Cross Track Error, Measured

Example:  `$GPXTE,A,A,0.67,L,N`

```
A            General warning flag V = warning (Loran-C Blink or SNR warning)
A            Not used for GPS (Loran-C cycle lock flag)
0.67         cross track error distance
L            Steer left to correct error (or R for right)
N            Distance units - Nautical miles
```

Example:  `$GPXTE,A,A,4.07,L,N*6D`

```
1    A         validity
2    A         cycle lock
3    4.07      distance off track
4    L         steer left (L/R)
5    N         distance units
6    *6D       checksum
```

<a name="zda"/>

## $GPZDA

Date and Time

UTC, day, month, year, and local time zone.

Format: `$--ZDA,hhmmss.ss,xx,xx,xxxx,xx,xx`

```
hhmmss.ss = UTC
xx = Day, 01 to 31
xx = Month, 01 to 12
xxxx = Year
xx = Local zone description, 00 to +/- 13 hours
xx = Local zone minutes description (same sign as hours)
```

---

<a name="garmin"/>

# Garmin proprietary sentences

*  [$PGRME - Estimated Position Error](#rme)
*  $PGRMF - Position Fix Sentence
*  [$PGRMM - Map Datum](#rmm)
*  $PGRMV - Velocity Sentence
*  [$PGRMZ - Altitude Information](#rmz)
*  [$PSLIB - Differential Control](pslib)

<a name="rme"/>

## $PGRME

Estimated Position Error

Example: `$PGRME,15.0,M,45.0,M,25.0,M*22`

```
15.0,M       Estimated horizontal position error in metres (HPE)
45.0,M       Estimated vertical error (VPE) in metres
25.0,M       Overall spherical equivalent position error
```

<a name="rmm"/>

## $PGRMM

Map datum

Example:  `$PGRMM,Astrln Geod '66*51`

Example:  `$PGRMM,NAD27 Canada*2F`

Currently active horizontal datum

<a name="rmz"/>

## $PGRMZ

Altitude Information

This sentence shows in feet, regardless of units shown on the display.

Example:   `$PGRMZ,201,f,3*18`

```
1  201   GPA Altitude
2  F     Units - f-Feet
3  checksum
```

<a name="pslib"/>

## $PSLIB

Proprietary sentences to control a Starlink differential beacon receiver.

Example:     `$PSLIB,,,J*22`

Example:     `$PSLIB,,,K*23`

These two sentences are normally sent together in each group
of sentences from the GPS.
The three fields are: Frequency, bit Rate, Request Type.  The
value in the third field may be:

*  J = status request
*  K = configuration request
*  blank = tuning message

When the GPS receiver is set to change the DBR frequency or
baud rate, the "J" sentence is replaced (just once) by (for
example): `$PSLIB,320.0,200*59` to set the DBR to 320 KHz, 200 baud.

---

<a name="allgp"/>

# $GPxxx sentence codes and short descriptions

*  $GPAAM - Waypoint Arrival Alarm
*  $GPALM - GPS Almanac Data
*  $GPAPA - Autopilot Sentence "A"
*  $GPAPB - Autopilot Sentence "B"
*  $GPASD - Autopilot System Data
*  $GPBEC - Bearing and Distance to Waypoint, Dead Reckoning
*  $GPBOD - Bearing, Origin to Destination
*  $GPBWC - Bearing and Distance to Waypoint, Great Circle
*  $GPBWR - Bearing and Distance to Waypoint, Rhumb Line
*  $GPBWW - Bearing, Waypoint to Waypoint
*  $GPDBT - Depth Below Transducer
*  $GPDCN - Decca Position
*  $GPDPT - Depth
*  $GPFSI - Frequency Set Information
*  $GPGGA - Global Positioning System Fix Data
*  $GPGLC - Geographic Position, Loran-C
*  $GPGLL - Geographic Position, Latitude/Longitude
*  $GPGSA - GPS DOP and Active Satellites
*  $GPGSV - GPS Satellites in View
*  $GPGXA - TRANSIT Position
*  $GPHDG - Heading, Deviation and Variation
*  $GPHDT - Heading, True
*  $GPHSC - Heading Steering Command
*  $GPLCD - Loran-C Signal Data
*  $GPMTA - Air Temperature (to be phased out)
*  $GPMTW - Water Temperature
*  $GPMWD - Wind Direction
*  $GPMWV - Wind Speed and Angle
*  $GPOLN - Omega Lane Numbers
*  $GPOSD - Own Ship Data
*  $GPR00 - Waypoint active route (not standard)
*  $GPRMA - Recommended Minimum Specific Loran-C Data
*  $GPRMB - Recommended Minimum Navigation Information
*  $GPRMC - Recommended Minimum Specific GPS/TRANSIT Data
*  $GPROT - Rate of Turn
*  $GPRPM - Revolutions
*  $GPRSA - Rudder Sensor Angle
*  $GPRSD - RADAR System Data
*  $GPRTE - Routes
*  $GPSFI - Scanning Frequency Information
*  $GPSTN - Multiple Data ID
*  $GPTRF - Transit Fix Data
*  $GPTTM - Tracked Target Message
*  $GPVBW - Dual Ground/Water Speed
*  $GPVDR - Set and Drift
*  $GPVHW - Water Speed and Heading
*  $GPVLW - Distance Traveled through the Water
*  $GPVPW - Speed, Measured Parallel to Wind
*  $GPVTG - Track Made Good and Ground Speed
*  $GPWCV - Waypoint Closure Velocity
*  $GPWNC - Distance, Waypoint to Waypoint
*  $GPWPL - Waypoint Location
*  $GPXDR - Transducer Measurements
*  $GPXTE - Cross-Track Error, Measured
*  $GPXTR - Cross-Track Error, Dead Reckoning
*  $GPZDA - Time and Date
*  $GPZFO - UTC and Time from Origin Waypoint
*  $GPZTG - UTC and Time to Destination Waypoint

---

<a name="latlong"/>

# Format of latitudes and longitudes

Where a numeric latitude or longitude is given, the two digits immediately to the left
of the decimal point are whole minutes, to the right are decimals of minutes, and the remaining
digits to the left of the whole minutes are whole degrees.

eg. 4533.35 is 45 degrees and 33.35 minutes.  ".35" of a minute is exactly 21 seconds.

eg. 16708.033 is 167 degrees and 8.033 minutes.  ".033" of a minute is about 2 seconds.

---

<a name="refs"/>

# References

*  [National Marine Electronics Association](http://www.nmea.org)



