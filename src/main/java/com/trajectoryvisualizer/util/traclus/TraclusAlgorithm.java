package com.trajectoryvisualizer.util.traclus;

public class TraclusAlgorithm {

    private static String errorMessage = "Please give me 1 or 3 input parameters! \n "
            + "If you have no idea how to decide eps and minLns, just feed in 1 parameter (outputFilePath):\n"
            + "--e.g. java boliu.Main deer_1995.tra testOut.txt \n"
            + "If you know the two parameters, just feed in all the 3 parameters (outputFilePath, eps, minLns)"
            + "--e.g. java boliu.Main deer_1995.tra testOut.txt 29 8 \n";

    /*
    Executes a TraClus algorithm, using data stored in Oracle database.
     */
    public static void executeTraclus(String clusterFileName) {
        System.out.println("Running TraClus algorithm...");
        TraClusterDoc tcd = new TraClusterDoc();
        try {
            tcd.onOpenDocument(TraclusInput.outputToInput());
            TraClusterDoc.Parameter p = tcd.onEstimateParameter();
            if (p != null) {
                System.out.println("Based on the algorithm, the suggested parameters are:\n" + "eps:" + p.epsParam + "  minLns:" + p.minLnsParam);
            }
            tcd.onClusterGenerate(clusterFileName, p.epsParam, p.minLnsParam);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (clusterFileName.isEmpty()) {
            System.out.println(errorMessage);
        }
    }

//    public static void executeTraclus(String clusterFileName, double epsParam, int minLnsParam) {
//        System.out.println("Running TraClus algorithm...");
//        TraClusterDoc tcd = new TraClusterDoc();
//        try {
//            tcd.onOpenDocument(TraclusInput.outputToInput());
//            tcd.onClusterGenerate(clusterFileName, epsParam, minLnsParam);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (clusterFileName == null || epsParam == 0 || minLnsParam == 0) {
//            System.out.println(errorMessage);
//        }
//    }

/**
 * To use the GUI, Remove the below comment and comment out the above section of code
 * An adjustable GUI is to be added.
 */
/*
		TraClusterDoc tcd = new TraClusterDoc();

		//tcd.onOpenDocument("src/deer_1995.tra");
		//tcd.onClusterGenerate("testDeerResult.txt", 29, 8);

		//tcd.onOpenDocument("src/hurricane1950_2006.tra");
		//tcd.onClusterGenerate("testHurricaneResult.txt", 32, 6);

		tcd.onOpenDocument("src/elk_1993.tra");
		tcd.onClusterGenerate("testElkResult.txt", 25, 5);// 25, 5~7

		MainFrame mf = new MainFrame(tcd.m_trajectoryList, tcd.m_clusterList);


		Parameter p = tcd.onEstimateParameter();
		if (p != null) {
			System.out.println("Based on the algorithm, the suggested parameters are:\n" + "eps:" + p.epsParam + "  minLns:" + p.minLnsParam);
		}
*/
}
