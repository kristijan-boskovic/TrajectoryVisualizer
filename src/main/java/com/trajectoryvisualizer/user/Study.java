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

    public Study(String name, long ID, String description, int zoneNumber, char letter) {
        this.name = name;
        this.ID = ID;
        this.description = description;
        URL = Util.getStudyURL(ID);

        this.zoneNumber = zoneNumber;
        this.zoneLetter = letter;
    }

    public String getName() {
        return name;
    }

    public long getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public String getURL() {
        return URL;
    }

    public int getZoneNumber() {
        return zoneNumber;
    }

    public char getZoneLetter() {
        return zoneLetter;
    }
}
