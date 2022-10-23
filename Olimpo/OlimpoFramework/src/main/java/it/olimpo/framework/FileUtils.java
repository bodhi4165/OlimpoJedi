package it.olimpo.framework;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileUtils {
    public static final String JAVA_FORMAT = ".java";
    public static final String XML_FORMAT = ".xml";

    public static String getRelativePath (File file) {
        return file.getPath()
                        .substring(file.getPath().indexOf("it/"))
                        .replace("/", ".")
                        .substring(0, file.getPath()
                                            .substring(file.getPath().indexOf("it/"))
                                            .replace("/", ".").length()-5 );

    }
    private static void findFileList (String name, File searchDir, List<File> inOut) {
        for( File f : Objects.requireNonNull(searchDir.listFiles())) {
            if (f.isDirectory()) {
                findFileList(name, f, inOut);
            } else {
                if (f.getName().equalsIgnoreCase(name)) {
                    inOut.add(f);
                }
            }
        }
    }

    private static void findFileByExtension (String name, File searchDir, List<File> inOut) {
        for( File f : Objects.requireNonNull(searchDir.listFiles())) {
            if (f.isDirectory()) {
                findFileByExtension(name, f, inOut);
            } else {
                String extension = "." + f.getName().split("\\.")[f.getName().split("\\.").length-1];
                if (extension.equalsIgnoreCase(name)) {
                    if (!inOut.contains(f) && !f.getName().equalsIgnoreCase("pom.xml")) {
                        inOut.add(f);
                    }
                }
            }
        }
    }

    public static File searchFile (String name, String format) {
        List<File> lFiles = new ArrayList<>();
        findFileList(name + format, new File("OlimpoJedi/"), lFiles);

        return lFiles.size() == 0 ? null : lFiles.get(0);
    }

    public static List<File> searchByExtension (String format) {
        List<File> lFiles = new ArrayList<>();
        findFileByExtension(format, new File("OlimpoJedi/"), lFiles);

        return lFiles;
    }

    public static Game getGameObject (File file) {
        Game gameOut = new Game();
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Game.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            gameOut = (Game) unmarshaller.unmarshal(file);
        } catch (JAXBException jaxbException ) {
            jaxbException.printStackTrace();
        }

        return gameOut;
    }

    private static SimpleFile parseSimpleFile (String[] text) {
        return new SimpleFile (Arrays.stream(text)
                                        .map(String::trim)
                                        .filter(s -> !s.equals("")).
                                        collect(Collectors.toList()));
    }

    public static List<TestResult> parseXml (File f)  {
        Game game = getGameObject(f);

        List<TestResult> lOut = new ArrayList<>();
        if (game.hasExpectedOutput()) {
            for (Test t: game.getTest()) {
                SimpleFile sfInput = parseSimpleFile(t.getInput().getValue().split("\n"));
                SimpleFile sfOutput = parseSimpleFile(t.getOutput().getValue().split("\n"));
                sfOutput.setHasExpected(true);

                lOut.add(new TestResult(sfInput, sfOutput));
            }
        } else {
            lOut.add(new TestResult(new SimpleFile(), new SimpleFile(false)));
        }

        return lOut;
    }
}
