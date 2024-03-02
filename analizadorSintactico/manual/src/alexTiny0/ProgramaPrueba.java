package alexTiny0;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import alex.ECaracterInesperado;
import alex.UnidadLexica;

public class ProgramaPrueba {

	public static void main(String[] args)  throws FileNotFoundException, IOException {
		if (args.length < 1) {
			System.err.println("Se debe proporcionar la ruta al archivo a procesar");
			System.exit(1);
		}
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
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
		         System.out.println(e);
		  }
		}
		while (error || unidad.clase() != ClaseLexica.EOF);
	}

}
