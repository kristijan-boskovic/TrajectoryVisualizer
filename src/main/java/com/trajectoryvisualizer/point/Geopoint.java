package com.trajectoryvisualizer.point;

/**
 * Derived from: cgeo/main/src/cgeo/geocaching/location/Geopoint.java
 *
 * Adapted to this project by Filip Karacic
 *
 * Representation of geographic point.
 */
public class Geopoint {
    private double latitude;
    private double longitude;

    /**
     * Creates new Geopoint with given latitude and longitude (both degree).
     *
     * @param latitude
     *            latitude
     * @param longitude
     *            longitude
     */
    public Geopoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Get latitude in degree.
     *
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }


    /**
     * Get longitude in degree.
     *
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }
}