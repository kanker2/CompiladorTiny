package alexTiny0;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import alex.UnidadLexica;
import alex.ECaracterInesperado;

public class DomJudge {

    public static void main(String[] args) throws FileNotFoundException, IOException {
		Reader input = new InputStreamReader(System.in);
		AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
		UnidadLexica unidad = null;
		boolean error;
		do {
		  error = false;  
		  try {  
		    unidad = al.sigToken();
		    System.out.println(unidad);
		  }
		  catch(ECaracterInesperado e) {
		         error = true;
		         System.out.println("ERROR");
		  }
		}
		while (error || unidad.clase() != ClaseLexica.EOF);
    }        

} 
