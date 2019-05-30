package com.trajectoryvisualizer.util;

import java.io.*;

public class Cmd {
    private static String sqlldrCmd = "SQLLDR HERMES/HERMES control=./MILANO_RAW_TRAJECTORIES_CL.ctl";

    @SuppressWarnings("unused")
    public static void execute() throws IOException {
        System.out.println("Executing SQLLDR");
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(sqlldrCmd);
        BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("C:\\Users\\Komp\\Desktop\\sqlldr.txt")));
        String line;
        while((line = input.readLine()) != null) {
            bfw.write(line);
            bfw.write("\n");
        }

        input.close();
        bfw.close();

        System.out.println("SQLLDR successfully executed!");
    }
}
