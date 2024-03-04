package alex;

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
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOT, "<not>");
	}

	UnidadLexica unidadPOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.POR);
	}

	UnidadLexica unidadENTRE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ENTRE);
	}

	UnidadLexica unidadAND() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AND);
	}

	UnidadLexica unidadOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OR);
	}

	UnidadLexica unidadMAS() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAS);
	}

	UnidadLexica unidadMENOS() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOS);
	}

	UnidadLexica unidadMENOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR);
	}

	UnidadLexica unidadEntero() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.Entero, alex.lexema());
	}

	UnidadLexica unidadReal() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.Real, alex.lexema());
	}

	UnidadLexica unidadMAYOR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
	}

	UnidadLexica unidadMENORIGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL);
	}

	UnidadLexica unidadMAYORIGUAL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL);
	}

	UnidadLexica unidadCOMPARACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMPARACION);
	}

	UnidadLexica unidadDISTINTO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DISTINTO);
	}

	UnidadLexica unidadASIGNACION() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION);
	}

	UnidadLexica unidadINIPAR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INIPAR);
	}

	UnidadLexica unidadFINPAR() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FINPAR);
	}

	UnidadLexica unidadINIBLOQUE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INIBLOQUE);
	}

	UnidadLexica unidadFINBLOQUE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FINBLOQUE);
	}

	UnidadLexica unidadARROBA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ARROBA);
	}

	UnidadLexica unidadFINDECLARACIONES() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FINDECLARACIONES);
	}

	UnidadLexica unidadPUNTOYCOMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTOYCOMA);
	}

	UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EOF);
	}

	public UnidadLexica unidadId() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.IDEN, alex.lexema());
	}

	public UnidadLexica unidadCadena() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
	}

	public UnidadLexica unidadINTP() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INTP);
	}

	public UnidadLexica unidadREALP() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REALP);
	}

	public UnidadLexica unidadBOOLP() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.BOOLP);
	}

	public UnidadLexica unidadSTRINGP() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRINGP);
	}

	public UnidadLexica unidadNULL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NULL);
	}

	public UnidadLexica unidadTRUE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TRUE);
	}

	public UnidadLexica unidadFALSE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FALSE);
	}

	public UnidadLexica unidadPROC() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PROC);
	}

	public UnidadLexica unidadIF() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IF);
	}

	public UnidadLexica unidadELSE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ELSE);
	}

	public UnidadLexica unidadWHILE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WHILE);
	}

	public UnidadLexica unidadSTRUCT() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRUCT);
	}

	public UnidadLexica unidadNEW() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NEW);
	}

	public UnidadLexica unidadDELETE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DELETE);
	}

	public UnidadLexica unidadREAD() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.READ);
	}

	public UnidadLexica unidadWRITE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WRITE);
	}

	public UnidadLexica unidadNL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NL);
	}

	public UnidadLexica unidadTYPE() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TYPE);
	}

	public UnidadLexica unidadCALL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CALL);
	}

	public UnidadLexica unidadINIARRAY() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INIARRAY);
	}

	public UnidadLexica unidadFINARRAY() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FINARRAY);
	}

	public UnidadLexica unidadPUNTERO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTERO);
	}

	public UnidadLexica unidadMODULO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MODULO);
	}

	public UnidadLexica unidadCOMA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMA);
	}

	public UnidadLexica unidadREFERENCIA() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REFERENCIA);
	}

	public UnidadLexica unidadPUNTO() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
	}

	public void error() {
		String msgError = "Carácter inesperado (" + alex.fila() + "," + alex.columna() + "); "+alex.yycharat(alex.yylength());
		throw new ECaracterInesperado(msgError);
	}

}