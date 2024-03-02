package asint;

import java.io.Reader;


public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {
    private void imprime(Token t) {
        switch(t.kind) {
            case donde: System.out.println("<donde>"); break;
            case evalua: System.out.println("<evalua>"); break;
            case EOF: System.out.println("<EOF>"); break;
            default: System.out.println(t.image);
        }
    }
    
    public AnalizadorSintacticoTinyDJ(Reader r) {
        super(r);
        disable_tracing();
    }
    final protected void trace_token(Token t, String where) {
        imprime(t);
    }
}
