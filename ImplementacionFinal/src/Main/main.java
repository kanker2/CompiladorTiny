package Main;
import java.io.FileInputStream;

import Procesamientos.*;
import asint.SintaxisAbstractaTiny.ProgT;
import c_ast_ascendente.alex.*;
import c_ast_ascendente.asint.*;
import c_ast_ascendente.errors.*;
import c_ast_descendente.asint.*;

public class main {

	
	
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
		VinculacionVisitante p1=new VinculacionVisitante();
		AEspacioVisitante p2=new AEspacioVisitante();
		CTiposVisitante p3= new CTiposVisitante();
		EtiquetadoVisitante p4 = new EtiquetadoVisitante();
		GDCVisitante p5 = new GDCVisitante();
		
		//1º proceso de vinculacion con exito
		p1.vincula(prog);
		//Hay que comprobrar que no hay errores para continuar
		//2º proceso de vinculacion con exito
		p2.asig_espacio(prog);
		//3º proceso de vinculacion con exito
		
		//4º proceso de vinculacion con exito
		
		//5º proceso de vinculacion con exito
	}

}
