package it.olimpo;

/**
 * Hello world!
 *
 * Il framework OlimpoFramework deve essere implementato all'interno di un progetto OlimpoJedi/
 *
 * La struttura di un xml deve essere cosi composta:

 <game name="game_name" has_expected="N" class="ClassName">
 </game>

 * In questo esempio abbiamo un gioco senza un expected output, verrà quindi mostrato a terminale il risultato
 * del metodo start(SimpleFile sf).
 *
 * Se si ha un input e un expected output, il file sarà cosi formato:

 <game name="game_name" has_expected="Y" class="ClassName">
    <test>
        <input name="input_01">
            <value>
                1234 456
                789 10
            </value>
        </input>
        <output name="output_01">
            <value>
                7
            </value>
        </output>
    </test>
    <test>
        <input name="input_02">
            <value>
                14
            </value>
        </input>
        <output name="output_02">
            <value>
                -1
            </value>
        </output>
    </test>
 </game>
 */
public class OlimpoJedi {

    public static void main(String[] args) {
        System.out.println("Hello world!\n");

        OlimpoStarter.go(true);
    }
}

