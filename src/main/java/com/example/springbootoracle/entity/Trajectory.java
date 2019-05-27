package com.example.springbootoracle.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@IdClass(CompositeKey.class)
@Table(name = "MILANO_RAW")
public class Trajectory {
    @Column(name = "USERID", nullable = true, length = 38)
    private Integer id;

    @Column(name = "TRAJECTORYID", nullable = true, length = 38)
    @Id
    private Integer trajid;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrajid() {
        return trajid;
    }

    public void setTrajid(Integer trajid) {
        this.trajid = trajid;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
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

    public Trajectory(Integer id, Integer trajid, Double x, Double y, Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        super();
        this.id = id;
        this.trajid = trajid;
        this.x = x;
        this.y = y;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Trajectory() {
        super();
    }
}
