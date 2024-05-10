import java.io.Reader;

import asint.SintaxisAbstractaTiny.ProgT;
import c_ast_ascendente.alex.AnalizadorLexicoTiny;
import c_ast_ascendente.asint.ConstructorASTTiny;
import c_ast_ascendente.errors.GestionErroresTiny.ErrorLexico;
import c_ast_ascendente.errors.GestionErroresTiny.ErrorSintactico;
import c_ast_descendente.asint.ConstructorASTTinyDesc;
import c_ast_descendente.asint.ConstructorASTTinyDescDJ;
import c_ast_descendente.asint.ParseException;
import c_ast_descendente.asint.TokenMgrError;

public class DomJudge {
	private static ProgT construye_ast(Reader input, char constructor) throws Exception {
		if (constructor == 'a') {
			try {
				AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
				// en esta fase no necesitamos volcar los distintos tokens leídos: utilizamos
				// directamente la clase ConstructorASTTiny, en lugar de su especialización
				// ConstructorASTTinyDJ, e invocamos a parse, en lugar de debug_parse.
				ConstructorASTTiny asint = new ConstructorASTTiny(alex);
				ProgT p = (ProgT) asint.parse().value;
				return p;
			} catch (ErrorLexico e) {
				System.out.println("ERROR_LEXICO");
			} catch (ErrorSintactico e) {
				System.out.println("ERROR_SINTACTICO");
				System.exit(0);
			}
		} else if (constructor == 'd') {
			try {
				// no necesitamos volcar los tokens: usamos directamente la clase generada
				// por javacc, y deshabilitamos la traza (por si estuviera activo DEBUG_PARSER.
				ConstructorASTTinyDesc asint = new ConstructorASTTinyDesc(input);
				asint.disable_tracing();
				return asint.programa_tiny();
			} catch (TokenMgrError e) {
				System.out.println("ERROR_LEXICO");
			} catch (ParseException e) {
				System.out.println("ERROR_SINTACTICO");
				System.exit(0);
			}
		} else {
			System.err.println("Metodo de construccion no soportado:" + constructor);
		}
		return null;
	}

	public static void procesa(Prog p, Reader datos) throws Exception {
		Errores errores = new Errores();
		new Vinculacion(errores).procesa(p);
		if (!errores.hayError()) {
			new Pretipado(errores).procesa(p);
		}
		if (!errores.hayError()) {
			new Tipado(errores).procesa(p);
		}
		if (!errores.hayError()) {
			new AsignacionEspacio().procesa(p);
			new Etiquetado().procesa(p);
			MaquinaP m = new MaquinaP(datos, 500, 5000, 5000, 10);
			new GeneracionCodigo(m).procesa(p);
			m.ejecuta();
		}
	}

	public static void main(String[] args) throws Exception {
		char constructor = (char) System.in.read();
		Reader r = new BISReader(System.in);
		Prog prog = construye_ast(r, constructor);
		if (prog != null) {
			procesa(prog, r);
		}
	}
}
