package asintTiny0;

import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;

import alexEval.AnalizadorLexicoEval;
import alexEval.ClaseLexica;
import alexEval.UnidadLexica;
import errorsEval.GestionErroresEval;

public class AnalizadorSintacticoTiny0 {
	private UnidadLexica anticipo;       // token adelantado
	   private AnalizadorLexicoEval alex;   // analizador léxico 
	   private GestionErroresEval errores;  // gestor de errores
	   private Set<ClaseLexica> esperados;  // clases léxicas esperadas
	   
	   public AnalizadorSintacticoTiny0(Reader input) throws IOException {
	        // se crea el gestor de errores
	      errores = new GestionErroresEval();
	        // se crea el analizador léxico
	      alex = new AnalizadorLexicoEval(input,errores);
	      esperados = EnumSet.noneOf(ClaseLexica.class);
	        // Se lee el primer token adelantado
	      sigToken();                      
	   }
	   public void analiza() {
	      //programa();
	      empareja(ClaseLexica.EOF);
	   }   
	   
	   private void empareja(ClaseLexica claseEsperada) {
	      if (anticipo.clase() == claseEsperada) {
	          traza_emparejamiento(anticipo);
	          sigToken();
	      }    
	      else {
	          esperados(claseEsperada);
	          error();
	      }
	   }
	   private void sigToken() {
	      try {
	        anticipo = alex.sigToken();
	        esperados.clear();
	      }
	      catch(IOException e) {
	        errores.errorFatal(e);
	      }
	   }
	   
	    private void error() {
	        errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), esperados);
	    }
	    
	    protected void traza_emparejamiento(UnidadLexica anticipo) {}  
	
}

