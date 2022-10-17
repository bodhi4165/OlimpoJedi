package it.olimpo.games.solutions;

import it.olimpo.framework.OlimpoRunner;
import it.olimpo.framework.SimpleFile;

public class Turni extends OlimpoRunner {
    @Override
    public SimpleFile start(SimpleFile fIn) {
        return new SimpleFile ("" + turniLogic(fIn)) ;
    }

    private int turniLogic (SimpleFile sf) {

        int numbersOperator = sf.getIntArray(sf.get(0))[0];

        int[] orari = new int[96];

        int j = 0;
        while (j < 96) {
            orari[j] = -1;
            j++;
        }

        int i = 1;
        while (i <= numbersOperator) {
            int[] turno = sf.getIntArray(sf.get(i));

            int start = turno[0];
            int end = turno[1];
            int over = 0;

            if (end < start) {
                over = end;
                end = 96;
            }

            while (start < end) {
                orari[start] = 1;
                start++;
            }

            int k = 0;
            while (k < over) {
                orari[k] = 1;
                k++;
            }

            i++;
        }

        i = 0;
        while (i < 96) {
            if (orari[i] == -1) {
                return i;
            }
            i++;
        }

        return -1;
    }
}
