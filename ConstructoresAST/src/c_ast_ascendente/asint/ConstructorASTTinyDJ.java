package c_ast_ascendente.asint;

import c_ast_ascendente.alex.UnidadLexica.StringLocalizado;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class ConstructorASTTinyDJ extends ConstructorASTTiny {
	public void debug_message(String msg) {
	}

	public void debug_shift(Symbol token) {
		System.out.println(((StringLocalizado) token.value).str());
	}

	public ConstructorASTTinyDJ(Scanner alex) {
		super(alex);
	}
}
