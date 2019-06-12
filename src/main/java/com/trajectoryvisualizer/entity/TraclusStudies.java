package com.trajectoryvisualizer.entity;

import javax.persistence.*;

/**
 * Entity for cluster data.
 */
@Entity
@IdClass(CompositeKey.class)
@Table(name = "TRACLUS_STUDIES")
public class TraclusStudies {

    @Column(name = "STUDYID", nullable = true, length = 38)
    private long studyid;

    @Column(name = "CLUSTERID", nullable = true, length = 38)
    @Id
    private int clusterid;

    @Column(name = "X", nullable = true, length = 22)
    @Id
    private double x;

    @Column(name = "Y", nullable = true, length = 22)
    @Id
    private double y;

    @Column(name = "LONGITUDE", nullable = true, length = 22)
    @Id
    private double longitude;

    @Column(name = "LATITUDE", nullable = true, length = 22)
    @Id
    private double latitude;


    public TraclusStudies(long studyid, int clusterid, double x, double y, double longitude, double latitude) {
        this.studyid = studyid;
        this.clusterid = clusterid;
        this.x = x;
        this.y = y;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public TraclusStudies() {
        super();
    }

    public long getStudyid() {
        return studyid;
    }

    public void setStudyid(long studyid) {
        this.studyid = studyid;
    }

    public int getClusterid() {
        return clusterid;
    }

    public void setClusterid(int clusterid) {
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