package c_ast_ascendente;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import c_ast_ascendente.alex.AnalizadorLexicoTiny;
import c_ast_ascendente.asint.ConstructorASTTiny;
import c_ast_ascendente.errors.GestionErroresTiny.ErrorLexico;
import c_ast_ascendente.errors.GestionErroresTiny.ErrorSintactico;

public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		ConstructorASTTiny asint = new ConstructorASTTiny(alex);
		// asint.setScanner(alex);
		System.out.println("CONSTRUCCION AST ASCENDENTE:\n");
		try {
			System.out.println(asint.parse().value);
		} catch (ErrorLexico e) {
			System.out.println("ERROR_LEXICO");
		} catch (ErrorSintactico e) {
			System.out.println("ERROR_SINTACTICO");
		}
		
	}
}
