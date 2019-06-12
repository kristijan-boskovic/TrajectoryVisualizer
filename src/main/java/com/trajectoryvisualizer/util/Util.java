package com.trajectoryvisualizer.util;

import com.trajectoryvisualizer.dao.RawDao;
import com.trajectoryvisualizer.entity.RawStudies;
import com.trajectoryvisualizer.point.UTMPoint;
import com.trajectoryvisualizer.user.Study;
import com.trajectoryvisualizer.user.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gets study data from Movebank and loads study into database.
 */
public class Util {
    private static final String SID = "orcl";
    private static final String USERNAME = "HERMES";
    private static final String PASSWORD = "HERMES";

	private static final long myStudyId = 92261778L;
	private static final List<Study> availableStudies;

	static {
	    availableStudies = new ArrayList<>();

        availableStudies.add(new Study("LifeTrack Whooper Swan Latvia", 92261778L, "Study: LifeTrack Whooper Swan Latvia", 34, 'V', 9, null, null));
        availableStudies.add(new Study("Toucan movement and seed dispersal", 2931895L, "Seed dispersal is critical to " +
                "understanding forest dynamics but is hard to study because tracking seeds is difficult", 17, 'P',15, null, null));
        availableStudies.add(new Study("Striated Caracara Falkland Islands", 13978569L, "The movement and feeding ecology of the Striated Caracara, " +
                "the world’s most southerly distributed raptor in its Falkland Islands stronghold", 20, 'F', 9, null, null));
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

    /**
     *
     * @params id
     *            ID of particular movebank study
     * @throws Exception
     *             Throws exception if data cannot be accessed
     */
    public static Map<String, List<RawStudies>> insertIntoTable(long id, RawDao rawDao, Map<String, List<RawStudies>> rawMap) throws Exception {
        String stringUrl = Util.getStudyURL(id);
        URL url = new URL(stringUrl);

        URLConnection uc = url.openConnection();
        uc.setRequestProperty("Authorization", new User().getBasicAuth());

        InputStream is = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        int row = 0;

        while ((line = br.readLine()) != null) {
            if (row != 0) {
                String[] splitLine = line.split(",");
                if (splitLine.length != 4 || splitLine[0].isEmpty() || splitLine[2].isEmpty()) continue;
                int trajectoryId = 0;

                UTMPoint point = UTMPoint.latLong2UTM(Double.parseDouble(splitLine[3].replace(",", ".")), Double.parseDouble(splitLine[2].replace(",", ".")));
                double x = point.getEasting();
                double y = point.getNorthing();

                double lon = Double.valueOf(splitLine[2]);
                double lat = Double.valueOf(splitLine[3]);


                String[] dateTime = splitLine[1].split(" ");
                String[] date = dateTime[0].split("-");
                String[] time = dateTime[1].split(":");

                Integer year = Integer.valueOf(date[0]);
                Integer month = Integer.valueOf(date[1]);
                Integer day = Integer.valueOf(date[2]);

                Integer hour = Integer.valueOf(time[0]);
                Integer minute = Integer.valueOf(time[1]);
                Integer second = (int) Math.round(Double.valueOf(time[2]));

                RawStudies studyRow = new RawStudies(id, trajectoryId, lon, lat, x, y, year, month, day, hour, minute, second);

                String sl = splitLine[0];
                if (!rawMap.containsKey(sl)) {
                    List<RawStudies> list = new ArrayList<>();
                    list.add(studyRow);
                    rawMap.put(sl, list);
                } else {
                    rawMap.get(sl).add(studyRow);
                }
            }
            row++;
        }
        br.close();

        int i = 0;
        List<RawStudies> studies = new ArrayList<>();
        for (String key : rawMap.keySet()) {
            for (RawStudies study : rawMap.get(key)) {
                study.setTrajid(i);
                studies.add(study);
            }
            i++;
        }

        rawDao.deleteRawStudy();
        rawDao.commit();
        rawDao.saveAll(studies);

        return rawMap;
    }

    public static HashMap<Integer, List<String>> trajectoriesToTraclusInput(long id, RawDao rawDao) throws Exception {
        int trajID;
        String coordX;
        String coordY;
        HashMap<Integer, List<String>> trajMap = new HashMap<Integer, List<String>>();

        try {
            List<RawStudies> studies = rawDao.getPoints();
            for (RawStudies study : studies) {
                trajID = study.getTrajid();
                coordX = String.valueOf(study.getX());
                coordY = String.valueOf(study.getY());

                if (!trajMap.containsKey(trajID)) {
                    List<String> list = new ArrayList<>();
                    list.add(coordX);
                    list.add(coordY);
                    trajMap.put(trajID, list);
                } else {
                    trajMap.get(trajID).add(coordX);
                    trajMap.get(trajID).add(coordY);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trajMap;
    }
}