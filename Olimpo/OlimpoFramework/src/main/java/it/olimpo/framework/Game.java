package it.olimpo.framework;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

class Input {
    private String name;
    private String value;

    public Input() {
    }

    public Input(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @XmlElement(name = "value")
    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
}

class Output {
    private String value;

    public Output() {
    }

    public Output(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @XmlElement(name = "value")
    public void setValue(String value) {
        this.value = value;
    }
}

class Test {
    private Input input;
    private Output output;

    public Test() {
    }

    public Test(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public Input getInput() {
        return input;
    }

    @XmlElement(name = "input")
    public void setInput(Input input) {
        this.input = input;
    }

    public Output getOutput() {
        return output;
    }

    @XmlElement(name = "output")
    public void setOutput(Output output) {
        this.output = output;
    }
}
@XmlRootElement(name = "game")
public class Game {
    private String name;
    private String hasExpectedOutput;
    private String logicClass;
    private List<Test> test;

    public Game() {
    }

    public Game(String name, String hasExpectedOutput, String logicClass, List<Test> test) {
        this.name = name;
        this.hasExpectedOutput = hasExpectedOutput;
        this.logicClass = logicClass;
        this.test = test;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public boolean hasExpectedOutput() {
        return hasExpectedOutput == null || hasExpectedOutput.equalsIgnoreCase("Y");
    }

    @XmlAttribute (name = "has_expected")
    public void setHasExpectedOutput(String hasExpectedOutput) {
        this.hasExpectedOutput = hasExpectedOutput;
    }

    public String getLogicClass() {
        return logicClass;
    }

    @XmlAttribute (name = "class")
    public void setLogicClass(String logicClass) {
        this.logicClass = logicClass;
    }

    public List<Test> getTest() {
        return test;
    }
    @XmlElement(name = "test")
    public void setTest(List<Test> test) {
        this.test = test;
    }
}



