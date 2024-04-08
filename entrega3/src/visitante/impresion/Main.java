package visitante.impresion;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import asint.SintaxisAbstractaTiny.ProgT;
import c_ast_ascendente.alex.AnalizadorLexicoTiny;
import c_ast_ascendente.asint.ConstructorASTTiny;

public class Main {
	public static void main(String[] args) throws Exception {
		if (args[0].equals("asc")) {
			Reader input = new InputStreamReader(new FileInputStream(args[1]));
			AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			ConstructorASTTiny asint = new ConstructorASTTiny(alex);
			ProgT prog = (ProgT) asint.parse().value;
			prog.procesa(new Impresion());
		} else {
			ConstructorASTTiny asint = new ConstructorASTTiny(new FileReader(args[1]));
			asint.disable_tracing();
			asint.analiza().procesa(new Impresion());
		}
	}
}
