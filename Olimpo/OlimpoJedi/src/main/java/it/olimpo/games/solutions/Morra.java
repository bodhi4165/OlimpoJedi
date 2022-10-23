package it.olimpo.games.solutions;

import it.olimpo.framework.OlimpoRunner;
import it.olimpo.framework.SimpleFile;

public class Morra extends OlimpoRunner  {

    @Override
    public SimpleFile start( SimpleFile fIn ) {
        StringBuilder resultString = new StringBuilder();
        for(int i = 1; i < fIn.size(); i++) {
            resultString.append(getIesimaRiga(fIn.get(i)));
        }

        return new SimpleFile(resultString.toString());
    }

    private String getIesimaRiga (String row) {
        int pl1_Number = Integer.parseInt(row.split(" ")[0]);
        int pl2_Number = Integer.parseInt(row.split(" ")[1]);

        String pl1_Binary = getBinaryNotation(pl1_Number);
        String pl2_Binary = getBinaryNotation(pl2_Number);

        pl1_Binary = fillNumberByOther(pl1_Binary, pl2_Binary);
        pl2_Binary = fillNumberByOther(pl2_Binary, pl1_Binary);

        int pl1_Score = 0;
        int pl2_Score = 0;
        for (int i = 0; i < pl1_Binary.length(); i++) {
            char c1 = pl1_Binary.charAt(i);
            char c2 = pl2_Binary.charAt(i);

            pl1_Score += (c1 == '1' && c2 == '0') || (c1 == '0' && c2 == '0')
                            ? 1
                            : 0;

            pl2_Score += (c1 == '0' && c2 == '1') || (c1 == '1' && c2 == '1')
                            ? 1
                            : 0;
        }

        return  (pl1_Score > pl2_Score)
                    ? "1"
                    : (pl1_Score < pl2_Score)
                        ? "0"
                        : "X";
    }

    private String getBinaryNotation (int number) {
        String binary = "";

        while (number > 0) {
            binary += number % 2;
            number = number / 2;
        }

        return binary;
    }

    private String fillNumberByOther (String number, String other) {
        while (number.length() < other.length()) {
            number+="0";
        }
        return number;
    }
}
