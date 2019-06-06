package com.trajectoryvisualizer.util.traclus;

import com.trajectoryvisualizer.entity.TraclusStudies;
import com.trajectoryvisualizer.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.trajectoryvisualizer.controller.ClusterController.clusterDao;
import static com.trajectoryvisualizer.controller.RawController.rawDao;

/**
 * Derived from: https://github.com/luborliu/TraClusAlgorithm/blob/master/src/boliu/Main.java
 *
 * Adapted to this project by Kristijan Boskovic
 *
 * // TO DO
 */
public class Main {
		
	public static Map<String, List<TraclusStudies>> main(long id, Map<String, List<TraclusStudies>> traclusMap) {
			TraClusterDoc tcd = new TraClusterDoc();
			try {

				tcd.onOpenDocument(Util.trajectoriesToTraclusInput(id, rawDao));
				TraClusterDoc.Parameter p = tcd.onEstimateParameter();

				traclusMap = tcd.onClusterGenerate(id, p.epsParam, p.minLnsParam, traclusMap);
			}catch(Exception e){
				e.printStackTrace();
			}

		List<TraclusStudies> studies = new ArrayList<>();
		for (String key : traclusMap.keySet()) {
			studies.addAll(traclusMap.get(key));
		}
		clusterDao.deleteClusterStudy();
		clusterDao.commit();
		clusterDao.saveAll(studies);

		return traclusMap;
	}
}
