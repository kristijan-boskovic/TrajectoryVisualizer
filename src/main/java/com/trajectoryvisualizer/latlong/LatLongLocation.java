package com.trajectoryvisualizer.latlong;

public class LatLongLocation {

    private double longitude;
    private double lattitude;

    public LatLongLocation(double longitude, double lattitude) {
        super();
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    @Override
    public String toString() {
        return "LAT: " + lattitude + " | LONG: " + longitude;
    }

}