package c_ast_ascendente.alex;
import c_ast_ascendente.asint.ClaseLexica;

public class ALexOperations {

	private AnalizadorLexicoTiny alex;
	
	public static class ECaracterInesperado extends RuntimeException {
	      public ECaracterInesperado(String msg) {
	          super(msg);
	      }
	}  

	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	UnidadLexica unidadNOT() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT, "<not>");
	}

	UnidadLexica unidadPOR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR, "*");
	}

	UnidadLexica unidadENTRE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENTRE, "/");
	}

	UnidadLexica unidadAND() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "<and>");
	}

	UnidadLexica unidadOR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "<or>");
	}

	UnidadLexica unidadMAS() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS, "+");
	}

	UnidadLexica unidadMENOS() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS, "-");
	}

	UnidadLexica unidadMENOR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR, "<");
	}

	UnidadLexica unidadEntero() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.Entero, alex.lexema());
	}

	UnidadLexica unidadReal() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.Real, alex.lexema());
	}

	UnidadLexica unidadMAYOR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR, ">");
	}

	UnidadLexica unidadMENORIGUAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL, "<=");
	}

	UnidadLexica unidadMAYORIGUAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL, ">=");
	}

	UnidadLexica unidadCOMPARACION() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMPARACION, "==");
	}

	UnidadLexica unidadDISTINTO() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DISTINTO, "!=");
	}

	UnidadLexica unidadASIGNACION() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION, "=");
	}

	UnidadLexica unidadINIPAR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INIPAR, "(");
	}

	UnidadLexica unidadFINPAR() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FINPAR, ")");
	}

	UnidadLexica unidadINIBLOQUE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INIBLOQUE,"{");
	}

	UnidadLexica unidadFINBLOQUE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FINBLOQUE, "}");
	}

	UnidadLexica unidadARROBA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ARROBA, "@");
	}

	UnidadLexica unidadFINDECLARACIONES() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FINDECLARACIONES, "&&");
	}

	UnidadLexica unidadPUNTOYCOMA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOYCOMA, ";");
	}

	UnidadLexica unidadEof() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF, "<EOF>");
	}

	public UnidadLexica unidadId() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.Identificador, alex.lexema());
	}

	public UnidadLexica unidadCadena() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.Cadena, alex.lexema());
	}

	public UnidadLexica unidadINT() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT, "<int>");
	}

	public UnidadLexica unidadREAL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL, "<real>");
	}

	public UnidadLexica unidadBOOL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL, "<bool>");
	}

	public UnidadLexica unidadSTRING() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRING, "<string>");
	}

	public UnidadLexica unidadNULL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULL, "<null>");
	}

	public UnidadLexica unidadTRUE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE, "<true>");
	}

	public UnidadLexica unidadFALSE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE, "<false>");
	}

	public UnidadLexica unidadPROC() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PROC, "<proc>");
	}

	public UnidadLexica unidadIF() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF, "<if>");
	}

	public UnidadLexica unidadELSE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE, "<else>");
	}

	public UnidadLexica unidadWHILE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE, "<while>");
	}

	public UnidadLexica unidadSTRUCT() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT, "<struct>");
	}

	public UnidadLexica unidadNEW() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW, "<new>");
	}

	public UnidadLexica unidadDELETE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DELETE, "<delete>");
	}

	public UnidadLexica unidadREAD() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.READ, "<read>");
	}

	public UnidadLexica unidadWRITE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WRITE, "<write>");
	}

	public UnidadLexica unidadNL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NL, "<nl>");
	}

	public UnidadLexica unidadTYPE() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TYPE, "<type>");
	}

	public UnidadLexica unidadCALL() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CALL, "<call>");
	}

	public UnidadLexica unidadINIARRAY() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INIARRAY, "[" );
	}

	public UnidadLexica unidadFINARRAY() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FINARRAY, "]");
	}

	public UnidadLexica unidadPUNTERO() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTERO, "^");
	}

	public UnidadLexica unidadMODULO() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MODULO, "%");
	}

	public UnidadLexica unidadCOMA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
	}

	public UnidadLexica unidadREFERENCIA() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REFERENCIA, "&" );
	}

	public UnidadLexica unidadPUNTO() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, ".");
	}

	public void error() {
		//String msgError = "Carácter inesperado (" + alex.fila() + "," + alex.columna() + "); "+alex.yycharat(alex.yylength());
		this.alex.getGestionErrores().errorLexico(alex.fila(), alex.columna(), alex.lexema());
	}

}