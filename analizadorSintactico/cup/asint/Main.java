package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;

public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTinyDJ asint = new AnalizadorSintacticoTinyDJ(alex);
		//asint.setScanner(alex);

		try {
			asint.debug_parse();
		} catch (ErrorLexico e) {
			System.out.println("ERROR_LEXICO");
		} catch (ErrorSintactico e) {
			System.out.println("ERROR_SINTACTICO");
		}
	}
}
