package com.trajectoryvisualizer.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key for RawStudies and TraclusStudies entities.
 */
class CompositeKey implements Serializable {

    private double longitude;
    private double latitude;
    private double x;
    private double y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeKey)) return false;
        CompositeKey that = (CompositeKey) o;
        return Double.compare(that.longitude, longitude) == 0 &&
                Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, x, y);
    }
}
