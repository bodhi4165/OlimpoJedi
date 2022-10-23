package it.olimpo.games.solutions;

import it.olimpo.framework.OlimpoRunner;
import it.olimpo.framework.SimpleFile;

public class Multiples35 extends OlimpoRunner {

    @Override
    public SimpleFile start(SimpleFile fIn ) {
        return new SimpleFile(multiple_3_5());
    }

    int multiple_3_5 () {

        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            if ((i % 3 == 0) || (i % 5 == 0)) {
                sum += i;
            }
        }

        return sum;
    }

}

