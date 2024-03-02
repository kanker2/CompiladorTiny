package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoEval;
import alex.ClaseLexica;
import errors.GestionErroresEval;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintacticoEval {
   private UnidadLexica anticipo;       // token adelantado
   private AnalizadorLexicoEval alex;   // analizador léxico 
   private GestionErroresEval errores;  // gestor de errores
   private Set<ClaseLexica> esperados;  // clases léxicas esperadas
   
   public AnalizadorSintacticoEval(Reader input) throws IOException {
        // se crea el gestor de errores
      errores = new GestionErroresEval();
        // se crea el analizador léxico
      alex = new AnalizadorLexicoEval(input,errores);
      esperados = EnumSet.noneOf(ClaseLexica.class);
        // Se lee el primer token adelantado
      sigToken();                      
   }
   public void analiza() {
      programa();
      empareja(ClaseLexica.EOF);
   }
   private void programa() {
       expresion();
       declaraciones();
   }
   
   private void expresion() {
       empareja(ClaseLexica.EVALUA);
       E0();
   }
   
   private void declaraciones() {
       switch(anticipo.clase()) {
           case DONDE: 
                 empareja(ClaseLexica.DONDE);
                 lista_declaraciones();
                 break;
           default:        
                esperados(ClaseLexica.DONDE);
                break;
       } 
   }
   
   private void lista_declaraciones() {
       declaracion();
       rlista_decs();
   }
   
   private void declaracion() {
       empareja(ClaseLexica.IDEN);
       empareja(ClaseLexica.IGUAL);
       E0();
   }    
   
   private void rlista_decs() {
       switch(anticipo.clase()) {
           case COMA: 
               empareja(ClaseLexica.COMA);
               declaracion();
               rlista_decs();
               break;
           default:
              esperados(ClaseLexica.COMA);
              break;
       }
   }
   
   private void E0() {
       E1();
       RE0();
   }
   
   private void RE0() {
       switch(anticipo.clase()) {
           case MAS: case MENOS: 
               OP0();
               E1();
               RE0();
               break;
           default: 
              esperados(ClaseLexica.MAS,ClaseLexica.MENOS);
              break;
       }    
    }
   

   private void E1() {
       E2();
       RE1();
   }
   
   private void RE1() {
       switch(anticipo.clase()) {
           case POR: case DIV: 
               OP1();
               E2();
               RE1();
               break;
           default: 
              esperados(ClaseLexica.POR,ClaseLexica.DIV);
              break;
       }    
    }
   
   private void E2() {
      switch(anticipo.clase()) {
          case ENT: empareja(ClaseLexica.ENT); break;
          case REAL: empareja(ClaseLexica.REAL); break; 
          case IDEN: empareja(ClaseLexica.IDEN); break;
          case PAP: 
               empareja(ClaseLexica.PAP); 
               E0(); 
               empareja(ClaseLexica.PCIERRE); 
               break;
          default:
              esperados(ClaseLexica.ENT,ClaseLexica.REAL,ClaseLexica.IDEN,ClaseLexica.PAP);
              error();
   }   
   }
   
   private void OP0() {
     switch(anticipo.clase()) {
         case MAS: empareja(ClaseLexica.MAS); break;  
         case MENOS: empareja(ClaseLexica.MENOS); break;  
         default:    
              esperados(ClaseLexica.MAS,ClaseLexica.MENOS);             
              error();
     }  
   }
   private void OP1() {
     switch(anticipo.clase()) {
         case POR: empareja(ClaseLexica.POR); break;  
         case DIV: empareja(ClaseLexica.DIV); break;  
         default:    
             esperados(ClaseLexica.POR,ClaseLexica.DIV);             
             error();
     }  
   }
       

   private void esperados(ClaseLexica ... esperadas) {
       for(ClaseLexica c: esperadas) {
           esperados.add(c);
       }
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
