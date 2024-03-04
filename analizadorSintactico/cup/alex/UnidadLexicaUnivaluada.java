package alex;

public class UnidadLexicaUnivaluada extends UnidadLexica {
	
   public UnidadLexicaUnivaluada(int fila, int columna, int clase, String lexema) {
     super(fila,columna,clase, lexema);
   }
  public String toString() {
    return "[clase:"+clase()+",fila:"+fila()+",col:"+columna()+"]";  
  }   
}
