import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import c_ast_ascendente.alex.AnalizadorLexicoTiny;
import c_ast_ascendente.asint.ConstructorASTTinyDJ;
import c_ast_ascendente.errors.GestionErroresTiny.ErrorLexico;
import c_ast_ascendente.errors.GestionErroresTiny.ErrorSintactico;
import c_ast_descendente.asint.ConstructorASTTinyDesc;
import c_ast_descendente.asint.ConstructorASTTinyDescDJ;
import c_ast_descendente.asint.ParseException;
import c_ast_descendente.asint.TokenMgrError;

public class Main {

	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));

		char primerCaracter = ' ';
		try {
			primerCaracter = (char) input.read();
		} catch (IOException e1) {
			System.out.println("Error al leer el primer caracter");
		}

		if (primerCaracter == 'a') {
			// Impresion de las trazas
			try {
				AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
				ConstructorASTTinyDJ asint = new ConstructorASTTinyDJ(alex);
				// asint.setScanner(alex);
				System.out.println("CONSTRUCCION AST ASCENDENTE");
				asint.debug_parse();
			} catch (ErrorLexico e) {
				System.out.println("ERROR_LEXICO");
			} catch (ErrorSintactico e) {
				System.out.println("ERROR_SINTACTICO");
			}

			// Impresion del procesamiento recursivo
			// System.out.println(asint.parse().value);

			// Impresion del procesamiento interprete

			// Impresion del procesamiento visitante
		} else {

			// Impresion de las trazas
			try {
				ConstructorASTTinyDescDJ asint = new ConstructorASTTinyDescDJ(input);
				System.out.println("CONSTRUCCION AST DESCENDENTE");
				asint.analiza();
			} catch (ParseException e) {
				System.out.println("ERROR_SINTACTICO");
			} catch (TokenMgrError e) {
				System.out.println("ERROR_LEXICO");
			}

			// Impresion del procesamiento recursivo
			// System.out.println(asint.analiza());

			// Impresion del procesamiento interprete

			// Impresion del procesamiento visitante

		}

	}

}
