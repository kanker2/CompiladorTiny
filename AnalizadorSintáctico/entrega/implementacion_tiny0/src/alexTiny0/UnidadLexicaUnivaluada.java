package alexTiny0;

public class UnidadLexicaUnivaluada extends UnidadLexica {
	private String simbolo;
   public String lexema() {throw new UnsupportedOperationException();}   
   public UnidadLexicaUnivaluada(int fila, int columna, ClaseLexica clase, String simbolo) {
     super(fila,columna,clase);
     this.simbolo=simbolo;
   }
   
   
  public String toString() {
    return simbolo;  
  }   
}
