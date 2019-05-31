package com.trajectoryvisualizer.util.traclus;

import com.trajectoryvisualizer.util.Util;

public class Main {
		
	public static void main(String[] args) {
		
		if (args.length == 3) {
			TraClusterDoc tcd = new TraClusterDoc();
			try {

				tcd.onOpenDocument(Util.trajectoriesToTraclusInput(args[0]));
				tcd.onClusterGenerate(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));

			}catch(Exception e){
				e.printStackTrace();
			}
		} else if (args.length == 1) {
			TraClusterDoc tcd = new TraClusterDoc();
			try {

				tcd.onOpenDocument(Util.trajectoriesToTraclusInput(args[0]));
				TraClusterDoc.Parameter p = tcd.onEstimateParameter();

				tcd.onClusterGenerate(args[0], p.epsParam, p.minLnsParam);
			}catch(Exception e){
				e.printStackTrace();
			}


		} else {
			System.out.println("Please give me 1 or 3 input parameters! \n "
					+ "If you have no idea how to decide eps and minLns, just feed in 1 parameter (study id):\n"
					+ "--e.g. java boliu.Main 12345 \n"
					+ "If you know the two parameters, just feed in all the 3 parameters (study id, eps, minLns)"
					+ "--e.g. java boliu.Main 12345 29 8 \n");
		}
	}
	
}
