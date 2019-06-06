package com.trajectoryvisualizer.entity;

import javax.persistence.*;

/**
 * // TO DO
 */
@Entity
@IdClass(CompositeKey.class)
@Table(name = "RAW_STUDIES")
public class RawStudies {
    @Column(name = "STUDYID", nullable = true, length = 38)
    private long studyid;

    @Column(name = "TRAJECTORYID", nullable = true, length = 38)
    @Id
    private int trajid;

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

    @Column(name = "YEAR", nullable = true, length = 10)
    private Integer year;

    @Column(name = "MONTH", nullable = true, length = 10)
    private Integer month;

    @Column(name = "DAY", nullable = true, length = 10)
    private Integer day;

    @Column(name = "HOUR", nullable = true, length = 10)
    private Integer hour;

    @Column(name = "MINUTE", nullable = true, length = 10)
    private Integer minute;

    @Column(name = "SECOND", nullable = true, length = 38)
    private Integer second;

    public RawStudies(long studyid, int trajid, double longitude, double latitude, double x, double y, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        super();
        this.studyid = studyid;
        this.trajid = trajid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.x = x;
        this.y = y;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public RawStudies() {
        super();
    }

    public long getStudyid() {
        return studyid;
    }

    public void setStudyid(long studyid) {
        this.studyid = studyid;
    }

    public int getTrajid() {
        return trajid;
    }

    public void setTrajid(int trajid) {
        this.trajid = trajid;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }
}