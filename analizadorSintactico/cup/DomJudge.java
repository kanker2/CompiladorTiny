
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTinyDJ;
import errors.GestionErroresEval.ErrorLexico;
import errors.GestionErroresEval.ErrorSintactico;

public class DomJudge {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(System.in);
     AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
     AnalizadorSintacticoTinyDJ asint = new AnalizadorSintacticoTinyDJ(alex);
	 //asint.setScanner(alex);
     try {    
        asint.debug_parse();
     }
     catch(ErrorLexico e) {
        System.out.println("ERROR_LEXICO"); 
     }
     catch(ErrorSintactico e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
 }

}
