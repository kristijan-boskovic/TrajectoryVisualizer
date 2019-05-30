package com.trajectoryvisualizer.util.traclus;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TraclusInput {
    /*
    Creates an input file for Traclus algorithm by using previous output file.
     */
    public static HashMap<Integer, List<String>> outputToInput() throws Exception {

        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        int trajID;
        String coordX;
        String coordY;
        HashMap<Integer, List<String>> trajMap = new HashMap<Integer, List<String>>();

        //BufferedReader br = new BufferedReader(new FileReader("./output.csv"));
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "HERMES", "HERMES");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Milano_Raw");

            // iterate through the java resultset
            while (rs.next())
            {
                trajID = rs.getInt("TRAJECTORYID");
                coordX = rs.getString("X").replace(',', '.'); // replacing all commas with dots in coordinates
                coordY = rs.getString("Y").replace(',', '.');

                // grouping coordinates by trajectory ID
                if (!trajMap.containsKey(trajID)) {
                    List<String> list = new ArrayList<>();
                    list.add(coordX);
                    list.add(coordY);
                    trajMap.put(trajID, list);
                }
                else {
                    trajMap.get(trajID).add(coordX);
                    trajMap.get(trajID).add(coordY);
                }
            }
            st.close();
        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return trajMap;
    }
}