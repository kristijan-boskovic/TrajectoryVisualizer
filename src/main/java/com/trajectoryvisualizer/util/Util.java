package com.trajectoryvisualizer.util;


import com.trajectoryvisualizer.latlong.LatLongLocation;
import com.trajectoryvisualizer.latlong.UTMCoordinate;
import com.trajectoryvisualizer.user.User;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static int row = 0;

    /**
     * @param id
     *            Movebank study id
     * @return URL of particular movebank study
     */
    public static String getStudyURL(Long id) {
        return "https://www.movebank.org/movebank/service/direct-read?entity_type=event&study_id=" + id
                + "&attributes=individual_id,timestamp,location_long,location_lat";
    }

    /**
     *
     * @params urlToRead, user URL of particular movebank study User object for
     *         authorization
     * @throws Exception
     *             Throws exception if data cannot be accessed
     */

    public static void writeToFile(String urlToRead, User user) throws Exception {
        System.out.println("Reading URL...");

        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("C:\\Users\\Komp\\Desktop\\output.txt")));
        URL url = new URL(urlToRead);

        URLConnection uc = url.openConnection();
        uc.setRequestProperty("Authorization", user.getBasicAuth());

        InputStream is = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
//        int row = 0;

        while ((line = br.readLine()) != null) {

            String[] splitLine = line.split(",");
            List<String> listToBuildLine = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            LatLongLocation latLong;
            UTMCoordinate utm;

            if (row != 0) {
                // individual_id
//				listToBuildLine.add(splitLine[0]);
                listToBuildLine.add("1");

                // trajectory_id
                listToBuildLine.add(String.valueOf(row / 1000));
//                listToBuildLine.add("1");

                double longitude = Double.parseDouble(splitLine[2]);
                double lattitude = Double.parseDouble(splitLine[3]);
                latLong = new LatLongLocation(longitude, lattitude);
                utm = Converter.convertToUTM(latLong);

                double x = utm.getX();
                double y = utm.getY();

                String easting = Double.toString(x).replace(".", ",");

                String northing = Double.toString(y).replace(".", ",");


                listToBuildLine.add(easting);

                listToBuildLine.add(northing);

                // ovdje razdvajam datum na godinu,mjesec i dan i vrijeme na sat,minuta i
                // sekunda
                String[] timestamp = splitLine[1].split(" ");

                String date = timestamp[0];
                String time = timestamp[1];

                String[] dateSplit = date.split("-");

                String year = dateSplit[0];
                listToBuildLine.add(year);

                String month = dateSplit[1];
                listToBuildLine.add(month);

                String day = dateSplit[2];
                listToBuildLine.add(day);

                String[] timeSplit = time.split(":");

                String hours = timeSplit[0];
                listToBuildLine.add(hours);

                String minutes = timeSplit[1];
                listToBuildLine.add(minutes);

                String seconds = timeSplit[2];
                listToBuildLine.add(seconds.substring(0,2));

                for (String tren : listToBuildLine) {
                    sb.append(tren + "|");
                }
                sb.deleteCharAt(sb.length() - 1);
                bfw.write(sb.toString());
                bfw.write("\n");

            } else {
                row++;
                continue;
            }

            row++;
        }

        br.close();
        bfw.close();
        System.out.println("URL successfully read and trajectory_ids successfully added");
    }
}