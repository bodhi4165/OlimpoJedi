package it.olimpo.framework;

public class TestResult {
    private SimpleFile input;
    private SimpleFile expectedOutput;
    private SimpleFile genOutput;

    public SimpleFile getInput() {
        return input;
    }

    public void setInput(SimpleFile input) {
        this.input = input;
    }

    public SimpleFile getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(SimpleFile expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public SimpleFile getGenOutput() {
        return genOutput;
    }

    public void setGenOutput(SimpleFile genOutput) {
        this.genOutput = genOutput;
    }

    public boolean evaluate () {
        if (genOutput.size() != expectedOutput.size()) {
            return false;
        }

        for (int i = 0; i < genOutput.size(); i++) {
            String sGen = genOutput.get(i);
            String sExp = expectedOutput.get(i);

            if (!sGen.equals(sExp)) {
                return false;
            }
        }

        return true;
    }
}
