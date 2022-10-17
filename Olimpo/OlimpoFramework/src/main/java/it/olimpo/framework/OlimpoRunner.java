package it.olimpo.framework;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class OlimpoRunner {

    private String runnerName;
    private boolean result;

    public OlimpoRunner (String type) {
        runnerName = type;
    }

    public OlimpoRunner () {
        //
    }

    public void setRunnerName (String runnerName) {
        this.runnerName = runnerName;
    }

    public static Object getRunningInstance (String runningName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        File fClaz = FileUtils.searchFile(runningName + ".java");
        assert fClaz != null;
        String relative = fClaz.getPath().substring(fClaz.getPath().indexOf("it/"))
                                .replace("/", ".")
                                .substring(0, fClaz.getPath().substring(fClaz.getPath().indexOf("it/"))
                                                                .replace("/", ".").length()-5 );

        Object z = Class.forName(relative).newInstance();
        ((OlimpoRunner) z).setRunnerName(runningName);
        return z;
    }

    public void run () {
        boolean totalResult = true;
        for (TestResult tr : FileUtils.parseXml(runnerName)) {
            tr.setGenOutput(start(tr.getInput()));
            totalResult &= tr.evaluate();
        }
        this.result = totalResult;
    }

    public abstract SimpleFile start( SimpleFile fIn );

}
