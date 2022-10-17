package it.olimpo.games.solutions;

import it.olimpo.framework.OlimpoRunner;
import it.olimpo.framework.SimpleFile;

public class Stringhe extends OlimpoRunner  {

    @Override
    public SimpleFile start( SimpleFile fIn ) {
        SimpleFile out = new SimpleFile();

        for(int i = 1; i < fIn.size(); i++) {
            out.add(getIesimaRiga(fIn.get(i)));
        }

        return out;
    }

    private String getIesimaRiga (String row) {
        int i = 0;

        while ( i < 6 ) {
            int totalOcc = 1 + getOccByLetter(row, row.charAt(i), i+1);
            if (totalOcc >= 3) {
                return "C";
            }

            int totalAsc = 1 + getAscenderByLetter (row, row.charAt(i), i+1);
            if (totalAsc >= 3) {
                return "C";
            }

            i++;
        }

        return "B";
    }

    private int getOccByLetter (String row, char curr, int start) {
        if (start == 6) {
            return 0;
        }

        char next = row.charAt(start);
        if (next == curr) {
            return 1 + getOccByLetter(row, curr, start + 1);
        } else {
            return 0;
        }
    }


    private int getAscenderByLetter (String row, char curr, int start) {
        if (start == 6) {
            return 0;
        }

        char next = row.charAt(start);
        if (next > curr) {
            return 1 + getAscenderByLetter(row, next, start + 1);
        } else {
            return 0;
        }
    }
}
