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
package org.nmea.dev;

/**
 * Class to manipulate latitude and longitude
 */
public class LatLon {

  private double latitude;
  private double longitude;
  public static final double EARTH_RADIUS_MILES = 3963.1676;
  public static final double EARTH_RADIUS_KM = 6371.009;

  public LatLon() {
  }

  public LatLon(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public LatLon(String latitude, String longitude) {
    this(Double.valueOf(latitude), Double.valueOf(longitude));
  }

  public LatLon(int latDegrees, int latMinutes, double latSeconds, String latDir,
                int lonDegrees, int lonMinutes, double lonSeconds, String lonDir) {
    this.latitude = latDegrees + (latMinutes + (latSeconds / 60)) / 60;
    if ("S".equals(latDir) || "s".equals(latDir)) {
      this.latitude *= -1;
    }
    this.longitude = lonDegrees + (lonMinutes + (lonSeconds / 60)) / 60;
    if ("W".equals(lonDir) || "w".equals(lonDir)) {
      this.longitude *= -1;
    }
  }

  public int getLatDegrees() {
    return (int) Math.abs(Math.floor(latitude));
  }

  public int getLatMinutes() {
    return (int) Math.floor((latitude - Math.floor(latitude)) * 60);
  }

  public double getLatSeconds() {
    double min = (latitude - Math.floor(latitude)) * 60;
    int min_int = (int) Math.floor(min);
    return Math.floor((min - min_int) * 60);
  }

  public int getLonDegrees() {
    return (int) Math.abs(Math.floor(longitude));
  }

  public int getLonMinutes() {
    return (int) Math.floor((longitude - Math.floor(longitude)) * 60);
  }

  public double getLonSeconds() {
    double min = (longitude - Math.floor(longitude)) * 60;
    int min_int = (int) Math.floor(min);
    return Math.floor((min - min_int) * 60);
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getLatRadian() {
    return getLatitude() * Math.PI;
  }

  public double getLonRadian() {
    return getLongitude() * Math.PI;
  }

  public boolean latitudeIsNegative() {
    return this.latitude < 0;
  }

  public boolean longitudeIsNegative() {
    return this.longitude < 0;
  }

  public String getLatHeading() {
    if (this.latitudeIsNegative()) {
      return "S";
    } else {
      return "N";
    }
  }

  public String getLonHeading() {
    if (this.longitudeIsNegative()) {
      return "W";
    } else {
      return "E";
    }
  }

  public double getLatitudeMiles() {
    return 69.047;
  }

  public double getLongitudeMiles() {
    return (Math.PI / 180) * EARTH_RADIUS_MILES * Math.cos(getLatRadian());
  }

  public double getLatitudeKm() {
    return 110.567;
  }

  public double getLongitudeKm() {
    return (Math.PI / 180) * EARTH_RADIUS_KM * Math.cos(getLatRadian());
  }

  @Override
  public String toString() {
    return "DEC: (" + getLatitude() + "), (" + getLongitude() + ") DMS: (" + getLatDegrees() + " " + getLatHeading() + " " + getLatMinutes() + " " + getLatSeconds() + ", " + getLonDegrees() + " " + getLonHeading() + " " + getLonMinutes() + " " + getLonSeconds() + ")";
  }
}
