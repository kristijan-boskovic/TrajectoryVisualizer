package com.trajectoryvisualizer.hermes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * // TO DO
 */
public class Hermes {
    private static final String SID = "orcl";
    private static final String USERNAME = "HERMES";
    private static final String PASSWORD = "HERMES";

    public static void loadStudy(long id) throws Exception{
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:" + SID, USERNAME,
                PASSWORD)) {

            // When this class first attempts to establish a connection, it automatically
            // loads any JDBC 4.0 drivers found within
            // the class path. Note that your application must manually load any JDBC
            // drivers prior to version 4.0.

//			System.out.println("Connected to Oracle database!" + "\n");
//			if(!bulkData(connection, id)) {
//				throw new Exception();
//			}


        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }

    }

    private static boolean bulkData(Connection connection, long id) {
        try {
            CallableStatement statement = connection.prepareCall(
                    "{call hermes.raw_trajectories_loader.bulkload_raw_trajectories(82087,'Raw_Studies', 'MPoints_Studies')}");

            System.out.println("Executing bulking procedure...");
            statement.execute();

            System.out.println("Done bulking..");
            System.out.println();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Bulking procedure failure.");
            return false;
        }

    }

    private static boolean loadRawData(Connection connection, long id) {
        try {
            String sqlldrCmd = "SQLLDR HERMES/HERMES control=./src/main/resources/TRAJECTORIES_" + id + "_CL.ctl";

            System.out.println("SQLLDR Started ....... ");
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(sqlldrCmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while (input.readLine() != null);


            proc.waitFor();
            System.out.println("SQLLDR Ended.");

            return true;
        }catch(InterruptedException | IOException e) {
            System.out.println("Error loading data into Oracle database. Quit loading.");
            return false;
        }

    }
}