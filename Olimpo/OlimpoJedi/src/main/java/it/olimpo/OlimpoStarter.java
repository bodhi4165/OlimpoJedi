package it.olimpo;

import it.olimpo.framework.OlimpoRunner;
import it.olimpo.games.solutions.Turni;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class OlimpoStarter {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("Hello world!\n");

        if (args.length == 0) {
            return;
        }

        for (String sIn : args) {
            OlimpoRunner t = (OlimpoRunner) OlimpoRunner.getRunningInstance(sIn);
            t.run();
        }
    }
}

