package com.trajectoryvisualizer.main;

import com.trajectoryvisualizer.hermes.Hermes;
import com.trajectoryvisualizer.user.User;
import com.trajectoryvisualizer.util.Util;
import com.trajectoryvisualizer.util.traclus.TraclusAlgorithm;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Initializing...");
        User user = new User();
        String url = Util.getStudyURL((long) 11017705);
        Util.writeToFile(url, user);
        Hermes.loadStudy("orcl", "HERMES", "HERMES");
        TraclusAlgorithm.executeTraclus("traclus_output.txt");
    }
}