package alex;

import java.util.HashMap;

public class UnidadLexicaUnivaluada extends UnidadLexica {
	
	static HashMap<ClaseLexica, String> lexemas;
	
	static {
		lexemas = new HashMap<>();
		lexemas.put(ClaseLexica.PUNTO, ".");
		lexemas.put(ClaseLexica.NOT, "<not>");
		lexemas.put(ClaseLexica.POR, "*");
		lexemas.put(ClaseLexica.ENTRE, "/");
		lexemas.put(ClaseLexica.AND, "<and>");
		lexemas.put(ClaseLexica.OR, "<or>");
		lexemas.put(ClaseLexica.MAS, "+");
		lexemas.put(ClaseLexica.MENOS, "-");
		lexemas.put(ClaseLexica.MENOR, "<");
		lexemas.put(ClaseLexica.MAYOR, ">");
		lexemas.put(ClaseLexica.MENORIGUAL, "<=");
		lexemas.put(ClaseLexica.MAYORIGUAL, ">=");
		lexemas.put(ClaseLexica.COMPARACION, "==");
		lexemas.put(ClaseLexica.DISTINTO, "!=");
		lexemas.put(ClaseLexica.ASIGNACION, "=");
		lexemas.put(ClaseLexica.INIPAR, "(");
		lexemas.put(ClaseLexica.FINPAR, ")");
		lexemas.put(ClaseLexica.INIBLOQUE, "{");
		lexemas.put(ClaseLexica.FINBLOQUE, "}");
		lexemas.put(ClaseLexica.ARROBA, "@");
		lexemas.put(ClaseLexica.FINDECLARACIONES, "&&");
		lexemas.put(ClaseLexica.PUNTOYCOMA, ";");
		lexemas.put(ClaseLexica.EOF, "EOF");
		lexemas.put(ClaseLexica.INTP, "<int>");
		lexemas.put(ClaseLexica.REALP, "<real>");
		lexemas.put(ClaseLexica.BOOLP, "<bool>");
		lexemas.put(ClaseLexica.STRINGP, "<string>");
		lexemas.put(ClaseLexica.NULL, "<null>");
		lexemas.put(ClaseLexica.TRUE, "<true>");
		lexemas.put(ClaseLexica.FALSE, "<false>");
		lexemas.put(ClaseLexica.PROC, "<proc>");
		lexemas.put(ClaseLexica.IF, "<if>");
		lexemas.put(ClaseLexica.ELSE, "<else>");
		lexemas.put(ClaseLexica.WHILE, "<while>");
		lexemas.put(ClaseLexica.STRUCT, "<struct>");
		lexemas.put(ClaseLexica.NEW, "<new>");
		lexemas.put(ClaseLexica.DELETE, "<delete>");
		lexemas.put(ClaseLexica.READ, "<read>");
		lexemas.put(ClaseLexica.WRITE, "<write>");
		lexemas.put(ClaseLexica.NL, "<nl>");
		lexemas.put(ClaseLexica.TYPE, "<nl>");
		lexemas.put(ClaseLexica.CALL, "<call>");
		lexemas.put(ClaseLexica.INIARRAY, "[");
		lexemas.put(ClaseLexica.FINARRAY, "]");
		lexemas.put(ClaseLexica.PUNTERO, "^");
		lexemas.put(ClaseLexica.MODULO, "%");
		lexemas.put(ClaseLexica.COMA, ",");
		lexemas.put(ClaseLexica.REFERENCIA, "&");
	}
	
   public String lexema() { return lexemas.get(clase); }
   public UnidadLexicaUnivaluada(int fila, int columna, ClaseLexica clase) {
     super(fila,columna,clase);  
   }
  public String toString() {
    return "[clase:"+clase()+",fila:"+fila()+",col:"+columna()+"]";  
  }   
}
