package it.olimpo.framework;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static void findFileList (String name, File searchDir, List<File> inOut) {
        for( File f : searchDir.listFiles()) {
            if (f.isDirectory()) {
                findFileList(name, f, inOut);
            } else {
                if (f.getName().equalsIgnoreCase(name)) {
                    inOut.add(f);
                }
            }
        }
    }

    public static File searchFile (String name) {
        List<File> lFiles = new ArrayList<>();
        findFileList(name, new File("OlimpoJedi/"), lFiles);

        return lFiles.size() == 0 ? null : lFiles.get(0);
    }

    // TODO
    public static List<TestResult> parseXml (String runningType) {
        List<TestResult> lOut = new ArrayList<>();

        return lOut;
    }

}
