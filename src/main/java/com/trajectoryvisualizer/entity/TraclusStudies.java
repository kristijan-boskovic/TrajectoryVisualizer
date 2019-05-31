package com.trajectoryvisualizer.entity;

import javax.persistence.*;

@Entity
@IdClass(CompositeKey.class)
@Table(name = "TRACLUS_STUDIES")
public class TraclusStudies {
    @Column(name = "STUDYID", nullable = true, length = 38)
    private Integer studyid;

    @Column(name = "CLUSTERID", nullable = true, length = 38)
    @Id
    private Integer clusterid;

    @Column(name = "LONGITUDE", nullable = true, length = 22)
    @Id
    private double longitude;

    @Column(name = "LATITUDE", nullable = true, length = 22)
    @Id
    private double latitude;

    @Column(name = "X", nullable = true, length = 22)
    @Id
    private double x;

    @Column(name = "Y", nullable = true, length = 22)
    @Id
    private double y;

    public TraclusStudies(Integer studyid, Integer clusterid, double longitude, double latitude, double x, double y) {
        this.studyid = studyid;
        this.clusterid = clusterid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.x = x;
        this.y = y;
    }

    public TraclusStudies() {
        super();
    }

    public Integer getStudyid() {
        return studyid;
    }

    public void setStudyid(Integer studyid) {
        this.studyid = studyid;
    }

    public Integer getClusterid() {
        return clusterid;
    }

    public void setClusterid(Integer clusterid) {
        this.clusterid = clusterid;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}