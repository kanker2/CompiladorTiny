import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import asint.SintaxisAbstractaTiny.ProgT;
import c_ast_ascendente.alex.AnalizadorLexicoTiny;
import c_ast_ascendente.asint.ConstructorASTTiny;
import c_ast_ascendente.asint.ConstructorASTTinyDJ;
import c_ast_ascendente.errors.GestionErroresTiny.ErrorLexico;
import c_ast_ascendente.errors.GestionErroresTiny.ErrorSintactico;
import c_ast_descendente.asint.ConstructorASTTinyDesc;
import c_ast_descendente.asint.ConstructorASTTinyDescDJ;
import c_ast_descendente.asint.ParseException;
import c_ast_descendente.asint.TokenMgrError;

import procesamiento.*;

public class Main {

	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		String op = args[1], opp = args[2];
			
		ProgT prog;
		
		// Impresion de las trazas
		try {
			if (op == "asc") {
				AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
				ConstructorASTTinyDJ asint = new ConstructorASTTinyDJ(alex);
				System.out.println("CONSTRUCCION AST ASCENDENTE");
				prog = (ProgT) asint.debug_parse().value;
			} else {
				ConstructorASTTinyDescDJ asint = new ConstructorASTTinyDescDJ(input);
				System.out.println("CONSTRUCCION AST DESCENDENTE");
				prog = asint.analiza();
			}
			procesar(prog, opp);

		} catch (ParseException | ErrorSintactico e) {
			System.out.println("ERROR_SINTACTICO");
		} catch (TokenMgrError | ErrorLexico e ) {
			System.out.println("ERROR_LEXICO");
		}
	}
	
	public static void procesar(ProgT prog, String opp) {
		
		ImpresorBonito e;
		switch (opp) {
		case "rec":
			e = new ImpresorBonitoRecursivo();
			e.procesa(prog);
			break;
		case "int":
			e = new ImpresorBonitoInterprete();
			e.procesa(prog);
			break;
		case "vis":
			e = new ImpresorBonitoVisitante();
			e.procesa(prog);
			break;
		}
	}
}
