package c_ast_descendente;

import java.io.FileReader;

import c_ast_descendente.asint.ConstructorASTTinyDesc;

public class Main {
	public static void main(String[] args) throws Exception {
		ConstructorASTTinyDesc asint = new ConstructorASTTinyDesc(new FileReader(args[0]));
		asint.disable_tracing();
		System.out.println(asint.analiza());
	}
}