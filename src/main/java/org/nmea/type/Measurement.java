/*
 * Measurement.java
 * Copyright (C) 2013 Robert Huitema, Kimmo Tuukkanen
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
package org.nmea.type;

/**
 * Sensor measurement data delivered by XDR - Transducer Measurement
 * {@link org.nmea.sentence.XDRSentence}. Notice that any of the fields may be
 * empty (<code>null</code>), depending on sentence and sensor that produced it.
 *
 * @author Robert Huitema, Kimmo Tuukkanen
 */
public class Measurement {

  /**
   * the type of transducer
   */
  private String type;
  /**
   * the units of measurement
   */
  private String units;
  /**
   * the measurement value
   */
  private double value;
  /**
   * the name of transducer
   */
  private String name;

  /**
   * Creates a new empty instance of Measurement.
   */
  public Measurement() {
  }

  /**
   * Creates a new instance of Measurement with given values.
   *
   * @param type  the type of transducer
   * @param value the measurement value
   * @param units the units of measurement
   * @param name  the name of transducer
   */
  public Measurement(String type, double value, String units, String name) {
    this.type = type;
    this.value = value;
    this.units = units;
    this.name = name;
  }

  /**
   * Returns the name of transducer.
   *
   * @return Sensor name String
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the type of transducer.
   *
   * @return Type String
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the units of measurement.
   *
   * @return Units String
   */
  public String getUnits() {
    return units;
  }

  /**
   * Returns the measurement value.
   *
   * @return Double value
   */
  public double getValue() {
    return value;
  }

  /**
   * Sets the name of transducer.
   *
   * @param name Transducer name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the type of measurement.
   *
   * @param type Type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Sets the units of measurement.
   *
   * @param units Units to set.
   */
  public void setUnits(String units) {
    this.units = units;
  }

  /**
   * Sets the measurement value.
   *
   * @param value Value to set
   */
  public void setValue(double value) {
    this.value = value;
  }

  /**
   * Tells if all fields in this measurement are empty (null).
   *
   * @return true if empty, otherwise false.
   */
  public boolean isEmpty() {
    return name == null && type == null && units == null;
  }

  /**
   * Indicator that that this measurement is usable.
   *
   * @return TRUE if the type and units are configured.
   */
  public boolean isValid() {
    return type != null && !type.isEmpty() && units != null && !units.isEmpty();
  }

}
