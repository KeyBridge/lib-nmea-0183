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
package org.nmea.type;

import java.util.Date;

/**
 * Waypoint represents a named geographic location.
 *
 * @author Kimmo Tuukkanen
 * @see org.nmea.type.Position
 */
public class Waypoint extends Position {

  /**
   * id of Waypoint
   */
  private String id;
  /**
   * the waypoint description/comment
   */
  private String description = "";
  /**
   * the time stamp when <code>Waypoint</code> was created
   */
  private final Date timeStamp = new Date();

  /**
   * Creates a new instance of <code>Waypoint</code> with default WGS84 datum.
   *
   * @param id  Waypoint identifier
   * @param lat Latitude degrees of the waypoint location
   * @param lon Longitude degrees of waypoint location
   */
  public Waypoint(String id, double lat, double lon) {
    super(lat, lon);
    this.id = id;
  }

  /**
   * Creates a new instance of <code>Waypoint</code> with default WGS84 datum.
   *
   * @param id  Waypoint identifier
   * @param lat Latitude degrees of the waypoint location
   * @param lon Longitude degrees of waypoint location
   * @param alt Altitude value, in meters above/below mean sea level
   */
  public Waypoint(String id, double lat, double lon, double alt) {
    super(lat, lon, alt);
    this.id = id;
  }

  /**
   * Creates a new instance of Waypoint with explicitly specified datum.
   *
   * @param id    Waypoint identifier
   * @param lat   Latitude degrees of the waypoint location
   * @param lon   Longitude degrees of waypoint location
   * @param datum Position datum, i.e. the coordinate system.
   */
  public Waypoint(String id, double lat, double lon, DatumType datum) {
    super(lat, lon, datum);
    this.id = id;
  }

  /**
   * Creates a new instance of <code>Waypoint</code> with explicitly specified
   * datum.
   *
   * @param id    Waypoint identifier/name
   * @param lat   Latitude degrees of the waypoint location
   * @param lon   Longitude degrees of waypoint location
   * @param alt   Altitude value, in meters above/below mean sea level
   * @param datum Position datum, i.e. the coordinate system.
   */
  public Waypoint(String id, double lat, double lon, double alt, DatumType datum) {
    super(lat, lon, alt, datum);
    this.id = id;
  }

  /**
   * Gets the waypoint description/comment.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get id of Waypoint
   *
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * Returns the time stamp when <code>Waypoint</code> was created.
   *
   * @return Date
   */
  public Date getTimeStamp() {
    return timeStamp;
  }

  /**
   * Sets the waypoint description.
   *
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Set the id of Waypoint
   *
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }
}
