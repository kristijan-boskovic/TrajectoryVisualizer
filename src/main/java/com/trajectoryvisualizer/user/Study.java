package com.trajectoryvisualizer.user;

import com.trajectoryvisualizer.util.Util;

/**
 * // TO DO
 */
public class Study {

    private String name;
    private String URL;
    private long ID;
    private String description;
    private int zoneNumber;
    private char zoneLetter;
    private int zoom;
    private Integer otherZone;
    private Double valueOther;

    public Study(String name, long ID, String description, int zoneNumber, char letter, int zoom, Integer otherZone, Double valueOther) {
        this.name = name;
        this.ID = ID;
        this.description = description;
        URL = Util.getStudyURL(ID);

        this.zoneNumber = zoneNumber;
        this.zoneLetter = letter;
        this.zoom = zoom;
        this.otherZone = otherZone;
        this.valueOther = valueOther;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(int zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public char getZoneLetter() {
        return zoneLetter;
    }

    public void setZoneLetter(char zoneLetter) {
        this.zoneLetter = zoneLetter;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public Integer getOtherZone() {
        return otherZone;
    }

    public void setOtherZone(Integer otherZone) {
        this.otherZone = otherZone;
    }

    public Double getValueOther() {
        return valueOther;
    }

    public void setValueOther(Double valueOther) {
        this.valueOther = valueOther;
    }
}
