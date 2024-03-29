package cup.alex;
import java_cup.runtime.Symbol;
import cup.asint.ClaseLexica;

public class UnidadLexica extends Symbol{
   protected ClaseLexica clase;
   private int fila;
   private int columna;
   public UnidadLexica(int fila, int columna, int clase, String lexema) {
	 super(clase, lexema);
     this.fila = fila;
     this.columna = columna;
   }
   public int clase () {return sym;}
   public String lexema() { return (String) value; }
   public int fila() {return fila;}
   public int columna() {return columna;}
}
