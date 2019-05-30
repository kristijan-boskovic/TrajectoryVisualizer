package com.trajectoryvisualizer.entity;

import java.io.Serializable;
import java.util.Objects;

class CompositeKey implements Serializable {
    private Integer trajid;
    private Double x;
    private Double y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return trajid.equals(that.trajid) &&
                x.equals(that.x) &&
                y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trajid, x, y);
    }
}
