import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import alex.UnidadLexica;
import alex.ALexOperations.ECaracterInesperado;
import alex.ClaseLexica;

public class DomJudge {

	public static void main(String[] args) {
		Reader input  = new InputStreamReader(System.in);
	     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
	     UnidadLexica unidad = null;
	     do {
	       try {  
	         unidad = al.yylex();
	         System.out.println("unidad.lexema: "+unidad.lexema());
	         System.out.println("al.yytext: "+al.yytext());
	       }
	       catch(ECaracterInesperado | IOException e) {
	               System.out.println("ERROR");
	       }
	     }
	     while (unidad.clase() != ClaseLexica.EOF);
	}

}
