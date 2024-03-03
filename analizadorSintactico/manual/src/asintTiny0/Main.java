package asintTiny0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import errorsTiny0.GestionErroresTiny0.ErrorLexico;
import errorsTiny0.GestionErroresTiny0.ErrorSintactico;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
	     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	     AnalizadorSintacticoTiny0DJ asint = new AnalizadorSintacticoTiny0DJ(input);
	     try {
	    	 asint.analiza();
	     } catch(ErrorSintactico e) {
	    	 System.out.println("ERROR_SINTACTICO");
	     } catch(ErrorLexico e) {
	    	 System.out.println("ERROR_LEXICO");
	     }
	 }

}
