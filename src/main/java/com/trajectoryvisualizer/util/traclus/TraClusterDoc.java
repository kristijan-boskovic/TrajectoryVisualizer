package com.trajectoryvisualizer.util.traclus;


import com.trajectoryvisualizer.point.Geopoint;
import com.trajectoryvisualizer.point.UTMPoint;
import com.trajectoryvisualizer.user.Study;
import com.trajectoryvisualizer.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Derived from: https://github.com/luborliu/TraClusAlgorithm/blob/master/src/boliu/TraClusterDoc.java
 *
 * Adapted to this project by Kristijan Boskovic
 *
 * // TO DO
 */
public class TraClusterDoc {
	
	public int m_nDimensions;
	public int m_nTrajectories;
	public int m_nClusters;
	public double m_clusterRatio;
	public int m_maxNPoints;
	public ArrayList<Trajectory> m_trajectoryList;
	public ArrayList<Cluster> m_clusterList;
	
	public TraClusterDoc() {
			
		m_nTrajectories = 0;
		m_nClusters = 0;
		m_clusterRatio = 0.0;	
		m_trajectoryList = new ArrayList<Trajectory>();
		m_clusterList = new ArrayList<Cluster>();
	}
	
	public class Parameter {
		double epsParam;
		int minLnsParam;
	}
	
	boolean onOpenDocument(HashMap<Integer, List<String>> trajectories) {

		if(trajectories == null || trajectories.isEmpty()){
			throw new IllegalArgumentException();
		}

		int nDimensions = 2;		// default dimension = 2
		int nTotalPoints = 0;
		int nTrajectories = 0;
		int trajectoryId;
		int nPoints;
		double value;

		try {

			nDimensions = 2; // the number of dimensions
			m_nDimensions = nDimensions;

			nTrajectories = trajectories.keySet().size();
			m_nTrajectories = nTrajectories;
			
			m_maxNPoints = -1; // initialize for comparison
			
			// the trajectory Id, the number of points, the coordinate of a point ...
			for (int i = 0; i < nTrajectories; i++) {

				String str = "";
				List<String> points = trajectories.get(i);

				str += i + " " + points.size() / 2 + " ";

				for(String point : points){
					str += point + " ";
				}

				Scanner sc = new Scanner(str); 
				sc.useLocale(Locale.US);
				
				trajectoryId = sc.nextInt(); //trajectoryID
				nPoints = sc.nextInt(); // number of points in the trajectory
				
				if (nPoints > m_maxNPoints) {
					m_maxNPoints = nPoints;
				}
				nTotalPoints += nPoints;
				Trajectory pTrajectoryItem = new Trajectory(trajectoryId, nDimensions);		
				for (int j = 0; j < nPoints; j++) {
					CMDPoint point = new CMDPoint(nDimensions);   // initialize the CMDPoint class for each point
					
					for (int k = 0; k < nDimensions; k++) {						
						value = sc.nextDouble();
						point.setM_coordinate(k, value);						
					}
					pTrajectoryItem.addPointToArray(point);				
				}
				
				m_trajectoryList.add(pTrajectoryItem);
			}					
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
        		
		return true;
	}

	boolean onClusterGenerate(long studyId, double epsParam, int minLnsParam) {

		ClusterGen generator = new ClusterGen(this);

		if(m_nTrajectories == 0) {
			System.out.println("Load a trajectory data set first");
		}

		// FIRST STEP: Trajectory Partitioning
		if (!generator.partitionTrajectory())
		{
			System.out.println("Unable to partition a trajectory\n");
			return false;
		}

		// SECOND STEP: Density-based Clustering
		if (!generator.performDBSCAN(epsParam, minLnsParam))
		{
			System.out.println("Unable to perform the DBSCAN algorithm\n");
			return false;
		}

		// THIRD STEP: Cluster Construction
		if (!generator.constructCluster())
		{
			System.out.println( "Unable to construct a cluster\n");
			return false;
		}

		try(Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:" + "orcl", "HERMES",
				"HERMES")){
			connection.prepareStatement("DELETE FROM Traclus_Studies").execute();
				Study study = Util.getStudy(Long.valueOf(studyId));
				for (int i = 0; i < m_clusterList.size(); i++) {
					int clusterId = m_clusterList.get(i).getM_clusterId();

					for (int j = 0; j < m_clusterList.get(i).getM_PointArray().size(); j++) {

						double x = m_clusterList.get(i).getM_PointArray().get(j).getM_coordinate(0);
						double y = m_clusterList.get(i).getM_PointArray().get(j).getM_coordinate(1);

						Geopoint point = new UTMPoint(study.getZoneNumber(), study.getZoneLetter(), x, y).toLatLong();

						String values = studyId + "," + clusterId + "," + x + "," + y + "," + point.getLongitude() + "," + point.getLatitude();

						Statement statement = connection.createStatement();
						statement.execute("INSERT INTO Traclus_Studies" + " VALUES (" + values + ")");
						statement.close();

					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		return true;
	}
	
	Parameter onEstimateParameter() {
		Parameter p = new Parameter();
		ClusterGen generator = new ClusterGen(this);
		if (!generator.partitionTrajectory()) {
			System.out.println("Unable to partition a trajectory\n");
			return null;
		}
		if (!generator.estimateParameterValue(p)) {
			System.out.println("Unable to calculate the entropy\n");
			return null;
		}
		return p;
	}

}
