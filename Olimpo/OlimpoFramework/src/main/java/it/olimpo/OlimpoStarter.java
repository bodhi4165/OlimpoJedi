package it.olimpo;

import it.olimpo.framework.FileUtils;
import it.olimpo.framework.Game;
import it.olimpo.framework.OlimpoRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OlimpoStarter {
    private OlimpoStarter() {
    }

    public static Map<String, File> checkForFiles() {
        List<File> inputFiles = FileUtils.searchByExtension(FileUtils.XML_FORMAT);
        Map<String, File> classNames = new HashMap<>();
        for (File f : inputFiles) {
            Game game = FileUtils.getGameObject(f);
            String className = game.getLogicClass();
            classNames.put(className, f);
        }
        return classNames;
    }
    public static void go (boolean canPrint) {
        Map<String, File> names = checkForFiles();

        for (String sIn : names.keySet()) {
            try {
                OlimpoRunner t = (OlimpoRunner) OlimpoRunner.getRunningInstance(sIn, names.get(sIn));
                t.run();
                if (canPrint) print(t.getRunnerName(), "" + t.getResult());
                if (canPrint && t.getSingleReturn() != null) print(t.getRunnerName(), (String) t.getSingleReturn());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                if (canPrint) print (sIn, ex.getMessage());
            }
        }
    }

    public static void print (String name, String line) {
        System.out.println("# " + name + " > " + line);
    }
}
