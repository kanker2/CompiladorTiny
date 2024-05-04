package Main;
import java.io.FileInputStream;

import Procesamientos.*;
import asint.SintaxisAbstractaTiny.ProgT;
import c_ast_ascendente.alex.*;
import c_ast_ascendente.asint.*;
import c_ast_ascendente.errors.*;
import c_ast_descendente.asint.*;

public class main {

	ProcesamientoVinculacion p1;
	ProcesamientoAEspacio p2;
	ProcesamientoCTipos p3;
	ProcesamientoEtiquetado p4;
	ProcesamientoGDC p5;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		String op = args[1];
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
			procesar(prog);

		} catch (ParseException | ErrorSintactico e) {
			System.out.println("ERROR_SINTACTICO");
		} catch (TokenMgrError | ErrorLexico e ) {
			System.out.println("ERROR_LEXICO");
		}
	}
	
	public static void procesar(ProgT prog) {
		//1� proceso de vinculacion con exito
		//Empezar a vincular p1.vincula(prog)????
		//Hay que comprobrar que no hay errores para continuar
		//2� proceso de vinculacion con exito
		
		//3� proceso de vinculacion con exito
		
		//4� proceso de vinculacion con exito
		
		//5� proceso de vinculacion con exito
	}

}
