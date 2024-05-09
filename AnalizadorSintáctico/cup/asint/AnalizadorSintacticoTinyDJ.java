package asint;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {
    public void debug_message(String msg) {}
    public void debug_shift(Symbol token) {
       System.out.println(token.value);
    }
    public AnalizadorSintacticoTinyDJ(Scanner alex) {
        super(alex);
    }
}
