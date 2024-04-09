package c_ast_descendente;

import java.io.FileReader;

import c_ast_descendente.asint.ConstructorASTTinyDesc;
import c_ast_descendente.asint.ParseException;
import c_ast_descendente.asint.TokenMgrError;

public class Main {
	public static void main(String[] args) throws Exception {
		ConstructorASTTinyDesc asint = new ConstructorASTTinyDesc(new FileReader(args[0]));
		asint.disable_tracing();
		System.out.println("CONSTRUCCION AST DESCENDENTE:\n");
		try {
			System.out.println(asint.analiza());
		} catch (ParseException e) {
			System.out.println("ERROR_SINTACTICO");
		} catch (TokenMgrError e) {
			System.out.println("ERROR_LEXICO");
		}
	}
}