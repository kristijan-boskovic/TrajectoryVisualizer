package com.trajectoryvisualizer.hermes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Hermes {

    /**
     *
     * @param SID
     * @param username
     * @param password
     */
    public static void loadStudy(String SID, String username, String password) {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, username, password);
            // When this class first attempts to establish a connection, it automatically
            // loads any JDBC 4.0 drivers found within
            // the class path. Note that your application must manually load any JDBC
            // drivers prior to version 4.0.

            System.out.println("Connected to Oracle database!" + "\n");

            try {
                String sqlldrCmd = "SQLLDR HERMES/HERMES control=./MILANO_RAW_TRAJECTORIES_CL.ctl";

                System.out.println("SQLLDR Started ....... ");
                Runtime rt = Runtime.getRuntime();
                Process proc = rt.exec(sqlldrCmd);
                BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                while (input.readLine() != null);


                proc.waitFor();
                System.out.println("SQLLDR Ended.");
            }catch(InterruptedException e) {
                System.out.println("Error loading data into Oracle database. Quit loading.");
                return;
            }



            try {
                CallableStatement statement = con.prepareCall(
                        "{call hermes.raw_trajectories_loader.bulkload_raw_trajectories(82087,'MILANO_RAW','MILANO_MPOINTS')}");

                System.out.println("Executing bulking procedure...");
                statement.execute();
                System.out.println("Done bulking..");
                System.out.println("Dataset loading finished!");
                System.out.println();

            }catch (SQLException e) {
                System.out.println("Bulking procedure failure.");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}