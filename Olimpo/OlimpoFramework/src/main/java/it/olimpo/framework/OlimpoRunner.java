package it.olimpo.framework;

import java.io.File;

public abstract class OlimpoRunner {

    private String runnerName;
    private File xmlInput;
    private boolean result;
    private Object singleReturn;

    public OlimpoRunner (String type) {
        runnerName = type;
    }

    public OlimpoRunner () {
        //
    }

    public Object getSingleReturn() {
        return singleReturn;
    }

    public boolean getResult () {
        return this.result;
    }
    public String getRunnerName () {
        return this.runnerName;
    }

    public static Object getRunningInstance (String runningName, File fXml) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        File fClaz = FileUtils.searchFile(runningName, FileUtils.JAVA_FORMAT);
        assert fClaz != null;
        String relative = FileUtils.getRelativePath(fClaz);
        Object z = Class.forName(relative).newInstance();
        ((OlimpoRunner) z).runnerName = runningName;
        ((OlimpoRunner) z).xmlInput = fXml;
        return z;
    }

    public void run () {
        boolean totalResult = true;
        for (TestResult tr : FileUtils.parseXml(xmlInput)) {
            tr.setGenOutput(start(tr.getInput()));
            totalResult &= tr.evaluate();
            if (!tr.getExpectedOutput().hasExpected()) {
                this.singleReturn = tr.getGenOutput().get(0);
            }
        }
        this.result = totalResult;
    }

    public abstract SimpleFile start( SimpleFile fIn );
}
