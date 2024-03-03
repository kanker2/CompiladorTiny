package asint;

import java.io.Reader;


public class AnalizadorSintacticoTinyDJ extends AnalizadorSintacticoTiny {
    private void imprime(Token t) {
        switch(t.kind) {
            case INT: System.out.println("<int>"); break;
            case REAL: System.out.println("<real>"); break;
            case BOOL: System.out.println("<bool>"); break;
            case STRING: System.out.println("<string>"); break;
            case NULL: System.out.println("<null>"); break;
            case PROC: System.out.println("<proc>"); break;
            case IF: System.out.println("<if>"); break;
            case ELSE: System.out.println("<else>"); break;
            case WHILE: System.out.println("<while>"); break;
            case STRUCT: System.out.println("<struct>"); break;
            case NEW: System.out.println("<new>"); break;
            case DELETE: System.out.println("<delete>"); break;
            case READ: System.out.println("<read>"); break;
            case WRITE: System.out.println("<write>"); break;
            case NL: System.out.println("<nl>"); break;
            case TYPE: System.out.println("<type>"); break;
            case CALL: System.out.println("<call>"); break;
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
