package com.trajectoryvisualizer.util;

import com.trajectoryvisualizer.point.UTMPoint;
import com.trajectoryvisualizer.user.Study;
import com.trajectoryvisualizer.user.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Util {
    private static final String SID = "orcl";
    private static final String USERNAME = "HERMES";
    private static final String PASSWORD = "HERMES";

	private static final long myStudyId = 92261778L;
	private static final List<Study> availableStudies;

	static {
	    availableStudies = new ArrayList<>();

	    availableStudies.add(new Study("LifeTrack Whooper Swan Latvia", 92261778L, "Study: LifeTrack Whooper Swan Latvia", 34, 'V'));
        availableStudies.add(new Study("Toucan movement and seed dispersal", 2931895L, "Seed dispersal is critical to " +
                "understanding forest dynamics but is hard to study because tracking seeds is difficult", 17, 'P'));
        availableStudies.add(new Study("MPIO PNIC hurricane frigate tracking", 6770990L, "Understand how birds react to hurricanes", 16, 'Q'));
        availableStudies.add(new Study("Striated Caracara Falkland Islands", 13978569L, "The movement and feeding ecology of the Striated Caracara, " +
                "the world’s most southerly distributed raptor in its Falkland Islands stronghold", 20, 'F'));
        availableStudies.add(new Study("Navigation and migration in European mallards", 11017705L, "movements of the ducks via satellite " +
                "GPS-tracking", 32, 'U'));

    }

    /**
     * @return Array of accessable movebank study ids
     */
    public static List<Study> getAvailableStudies() {
        return availableStudies;
    }

    /**
     * @param id
     *            Movebank study id
     * @return URL of particular movebank study
     */
    public static String getStudyURL(Long id) {
        return "https://www.movebank.org/movebank/service/direct-read?entity_type=event&study_id=" + id
                + "&attributes=individual_id,timestamp,location_long,location_lat";
    }

    public static Study getStudy(long id){
        for(Study study : availableStudies){
            if(study.getID() == id){
                return study;
            }
        }

        return null;
    }

    public static List<Double[]> getPoints(long id){
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:" + SID, USERNAME,
                PASSWORD)) {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM Raw_Studies WHERE studyid = " + id);

            List<Double[]> points = new ArrayList<>();

            double lon, lat;
            while (rs.next())
            {
                lon = rs.getDouble("Longitude"); // replacing all commas with dots in coordinates
                lat = rs.getDouble("Latitude");

                points.add(new Double[]{lon, lat});
            }
            rs.close();
            st.close();

            return points;
        }catch (SQLException e){
            return null;
        }
    }

    public static Map<Integer,List<Double[]>> getClusters(long id){
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:" + SID, USERNAME,
                PASSWORD)) {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM Traclus_Studies WHERE studyid = " + id);

            Map<Integer, List<Double[]>> clusters = new HashMap<>();
            int clusterId;
            double lon, lat;
            while (rs.next())
            {
                clusterId = rs.getInt("clusterId");
                lon = rs.getDouble("longitude");
                lat = rs.getDouble("latitude");

                if (!clusters.containsKey(clusterId)) {
                    List<Double[]> list = new ArrayList<>();
                    list.add(new Double[]{lon, lat});
                    clusters.put(clusterId, list);
                }
                else {
                    clusters.get(clusterId).add(new Double[]{lon, lat});
                }
            }
            rs.close();
            st.close();

            return clusters;
        }catch (SQLException e){
            return null;
        }
    }

    /**
     *
     * @params id
     *            ID of particular movebank study
     * @throws Exception
     *             Throws exception if data cannot be accessed
     */
    public static void insertIntoTable(long id) throws Exception {
        String stringUrl = Util.getStudyURL(id);
        URL url = new URL(stringUrl);

        URLConnection uc = url.openConnection();
        uc.setRequestProperty("Authorization", new User().getBasicAuth());

        InputStream is = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        int row = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:" + SID, USERNAME,
                PASSWORD)) {
            connection.prepareStatement("DELETE FROM RAW_STUDIES").execute();


            while ((line = br.readLine()) != null) {
                if (row != 0) {
                    String[] splitLine = line.split(",");
                    if (splitLine.length != 4 || splitLine[0].isEmpty() || splitLine[2].isEmpty()) continue;

                    int trajectoryId = row / 600;

                     UTMPoint point = UTMPoint.latLong2UTM(Double.parseDouble(splitLine[3].replace(",", ".")), Double.parseDouble(splitLine[2].replace(",", ".")));
                     String x = String.valueOf(point.getEasting());
                     String y = String.valueOf(point.getNorthing());

                    String lon = splitLine[2];
                    String lat = splitLine[3];

                    String[] dateTime = splitLine[1].split(" ");
                    String[] date = dateTime[0].split("-");
                    String[] time = dateTime[1].split(":");

                    String year = date[0];
                    String month = date[1];
                    String day = date[2];
                    String hour = time[0];
                    String minute = time[1];
                    String second = String.valueOf((int) Math.round(Double.valueOf(time[2])));

                    String values = id + "," + trajectoryId + "," + lon + "," + lat + "," + x + "," + y + "," + year + "," + month + "," + day + "," + hour + "," + minute + "," + second;

                    Statement statement = connection.createStatement();
                    statement.execute("INSERT INTO RAW_STUDIES" + " VALUES (" + values + ")");
                    statement.close();
                }

                row++;
            }
        }
        br.close();
    }

    public static HashMap<Integer, List<String>> trajectoriesToTraclusInput(long id) throws Exception {
        int trajID;
        String coordX;
        String coordY;
        HashMap<Integer, List<String>> trajMap = new HashMap<Integer, List<String>>();

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:" + "orcl", "HERMES",
                "HERMES")) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM RAW_STUDIES WHERE STUDYID = " + id);

            // iterate through the resultset
            while (rs.next())
            {

                trajID = rs.getInt("TRAJECTORYID");
                coordX = rs.getString("X").replace(',', '.'); // replacing all commas with dots in coordinates
                coordY = rs.getString("Y").replace(',', '.');

                // grouping coordinates by trajectory ID
                if (!trajMap.containsKey(trajID)) {
                    List<String> list = new ArrayList<String>();
                    list.add(coordX);
                    list.add(coordY);
                    trajMap.put(trajID, list);
                }
                else {
                    trajMap.get(trajID).add(coordX);
                    trajMap.get(trajID).add(coordY);
                }
            }
            rs.close();
            st.close();
        }

        return trajMap;
    }
}