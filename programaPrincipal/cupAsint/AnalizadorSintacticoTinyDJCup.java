package cupAsint;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class AnalizadorSintacticoTinyDJCup extends AnalizadorSintacticoTinyCup {
    public void debug_message(String msg) {}
    public void debug_shift(Symbol token) {
       System.out.println(token.value);
    }
    public AnalizadorSintacticoTinyDJCup(Scanner alex) {
        super(alex);
    }
}
