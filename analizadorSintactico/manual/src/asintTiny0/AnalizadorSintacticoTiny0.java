package asintTiny0;

import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;

import alexTiny0.ClaseLexica;
import alexTiny0.UnidadLexica;
import alexTiny0.AnalizadorLexicoTiny;
import errorsTiny0.GestionErroresTiny0;

public class AnalizadorSintacticoTiny0 {
	private UnidadLexica anticipo;       // token adelantado
	   private AnalizadorLexicoTiny alex;   // analizador léxico 
	   private GestionErroresTiny0 errores;  // gestor de errores
	   private Set<ClaseLexica> esperados;  // clases léxicas esperadas
	   
	   public AnalizadorSintacticoTiny0(Reader input) throws IOException {
	        // se crea el gestor de errores
	      errores = new GestionErroresTiny0();
	        // se crea el analizador léxico
	      alex = new AnalizadorLexicoTiny(input,errores);
	      esperados = EnumSet.noneOf(ClaseLexica.class);
	        // Se lee el primer token adelantado
	      sigToken();                      
	   }
	   public void analiza() {
		   programa_tiny();
		   empareja(ClaseLexica.EOF);
	   }
	   
	   private void programa_tiny() {
		   bloque();
	   }
	   
	   private void bloque() {
		   switch(anticipo.clase()) {
		   case INIBLOQUE:
			   empareja(ClaseLexica.INIBLOQUE);
			   lista_opt_declaraciones();
			   lista_opt_instrucciones();
			   empareja(ClaseLexica.FINBLOQUE);
			   break;
		   default: esperados(ClaseLexica.INIBLOQUE); error();
		   }
	   }
	   
	   private void lista_opt_declaraciones() {
		   switch (anticipo.clase()) {
		   case INT: case REAL: case BOOL:
			   lista_declaraciones();
			   empareja(ClaseLexica.FINDECLARACIONES);
		   default:
			   esperados(ClaseLexica.INT, ClaseLexica.REAL, ClaseLexica.BOOL);
			   break;
		   }
	   }
	   
	   private void lista_declaraciones() {
		   switch (anticipo.clase()) {
		   case INT: case REAL: case BOOL:
			   declaracion();
			   lista_decs();
			   break;
		   default:
			   esperados(ClaseLexica.INT, ClaseLexica.REAL, ClaseLexica.BOOL);
			   error();
			   break;
		   }
	   }
	   
	   private void lista_decs() {
		   switch (anticipo.clase()) {
		   case PUNTOYCOMA:
			   empareja(ClaseLexica.PUNTOYCOMA);
			   declaracion();
			   lista_decs();
		   default:
			   esperados(ClaseLexica.PUNTOYCOMA);
			   break;
		   }
	   }
	   
	   private void declaracion() {
		   switch (anticipo.clase()) {
		   case INT: case REAL: case BOOL:
			   tipo();
			   empareja(ClaseLexica.Identificador);
			   break;
		   default:
			   esperados(ClaseLexica.INT, ClaseLexica.REAL, ClaseLexica.BOOL);
			   error();
			   break;
		   }
	   }
	   
	   private void tipo() {
		   switch (anticipo.clase()) {
		   case INT:
			   empareja(ClaseLexica.INT);
			   break;
		   case REAL:
			   empareja(ClaseLexica.REAL);
			   break;
		   case BOOL:
			   empareja(ClaseLexica.BOOL);
			   break;
		   default:
			   esperados(ClaseLexica.INT, ClaseLexica.REAL, ClaseLexica.BOOL);
			   error();
			   break;
		   }
	   }
	   
	   private void lista_opt_instrucciones() {
		   switch (anticipo.clase()) {
		   case ARROBA:
			   lista_instrucciones();
			   break;
		   default:
			   esperados(ClaseLexica.ARROBA);
			   break;
		   }
	   }
	   
	   private void lista_instrucciones() {
		   switch (anticipo.clase()) {
		   case ARROBA:
			   instruccion();
			   lista_ins();
			   break;
		   default:
			   esperados(ClaseLexica.ARROBA);
			   error();
			   break;
		   }
	   }
	   
	   private void lista_ins() {
		   switch (anticipo.clase()) {
		   case PUNTOYCOMA:
			   empareja(ClaseLexica.PUNTOYCOMA);
			   instruccion();
			   lista_ins();
			   break;
		   default:
			   esperados(ClaseLexica.PUNTOYCOMA);
			   break;
		   }
	   }

	   private void instruccion() {
		   switch (anticipo.clase()) {
		   case ARROBA:
			   empareja(ClaseLexica.ARROBA);
			   expresion();
			   break;
		   default:
			   esperados(ClaseLexica.ARROBA);
			   error();
			   break;
		   }
	   }
	   private void expresion() {
		   switch (anticipo.clase()) {
		   case Entero: case Real:
		   case TRUE: case FALSE:
		   case Identificador:
		   case INIPAR: case NOT: case MENOS:
			   E0();
			   break;
		   default:
			   esperados(ClaseLexica.Entero, ClaseLexica.Real,
					   ClaseLexica.TRUE, ClaseLexica.FALSE,
					   ClaseLexica.Identificador,
					   ClaseLexica.INIPAR, ClaseLexica.NOT, ClaseLexica.MENOS);
			   error();
			   break;
		   }
	   }
	   
	   private void E0() {
		   switch (anticipo.clase()) {
		   case Entero: case Real:
		   case TRUE: case FALSE:
		   case Identificador:
		   case INIPAR: case NOT: case MENOS:
			   E1();
			   FE0();
			   break;
		   default:
			   esperados(ClaseLexica.Entero, ClaseLexica.Real,
					   ClaseLexica.TRUE, ClaseLexica.FALSE,
					   ClaseLexica.Identificador,
					   ClaseLexica.INIPAR, ClaseLexica.NOT, ClaseLexica.MENOS);
			   error();
			   break;
		   }
	   }
	   
	   private void FE0() {
		   switch (anticipo.clase()) {
		   case ASIGNACION:
			   empareja(ClaseLexica.ASIGNACION);
			   E0();
			   break;
		   default:
			   esperados(ClaseLexica.ASIGNACION);
			   break;
		   }
	   }
	   
	   private void E1() {
		   switch (anticipo.clase()) {
		   case Entero: case Real:
		   case TRUE: case FALSE:
		   case Identificador:
		   case INIPAR: case NOT: case MENOS:
			   E2();
			   RE1();
			   break;
		   default:
			   esperados(ClaseLexica.Entero, ClaseLexica.Real,
					   ClaseLexica.TRUE, ClaseLexica.FALSE,
					   ClaseLexica.Identificador,
					   ClaseLexica.INIPAR, ClaseLexica.NOT, ClaseLexica.MENOS);
			   error();
			   break;
		   }
	   }
	   
	   private void RE1() {
		   switch (anticipo.clase()) {
		   case MENOR: case MENORIGUAL:
		   case MAYOR: case MAYORIGUAL:
		   case COMPARACION: case DISTINTO:
			   OP1();
			   E2();
			   RE1();
			   break;
		   default:
			   esperados(ClaseLexica.MENOR, ClaseLexica.MENORIGUAL,
					   ClaseLexica.MAYOR, ClaseLexica.MAYORIGUAL,
					   ClaseLexica.COMPARACION, ClaseLexica.DISTINTO);
			   break;
		   }
	   }
	   
	   private void E2() {
		   switch (anticipo.clase()) {
		   case Entero: case Real:
		   case TRUE: case FALSE:
		   case Identificador:
		   case INIPAR: case NOT: case MENOS:
			   E3();
			   FE2();
			   RE2();
			   break;
		   default:
			   esperados(ClaseLexica.Entero, ClaseLexica.Real,
					   ClaseLexica.TRUE, ClaseLexica.FALSE,
					   ClaseLexica.Identificador,
					   ClaseLexica.INIPAR, ClaseLexica.NOT, ClaseLexica.MENOS);
			   error();
			   break;
		   }
	   }
	   
	   private void RE2() {
		   switch (anticipo.clase()) {
		   case MAS:
			   empareja(ClaseLexica.MAS);
			   E3();
			   RE2();
			   break;
		   default:
			   esperados(ClaseLexica.MAS);
			   break;
		   }
	   }
	   
	   private void FE2() {
		   switch (anticipo.clase()) {
		   case MENOS:
			   empareja(ClaseLexica.MENOS);
			   break;
		   default:
			   esperados(ClaseLexica.MENOS);
			   break;
		   }
	   }
	   
	   private void E3() {
		   switch (anticipo.clase()) {
		   case Entero: case Real:
		   case TRUE: case FALSE:
		   case Identificador:
		   case INIPAR: case NOT: case MENOS:
			   E4();
			   FE3();
			   break;
		   default:
			   esperados(ClaseLexica.Entero, ClaseLexica.Real,
					   ClaseLexica.TRUE, ClaseLexica.FALSE,
					   ClaseLexica.Identificador,
					   ClaseLexica.INIPAR, ClaseLexica.NOT, ClaseLexica.MENOS);
			   error();
			   break;
		   }
	   }
	   
	   private void FE3() {
		   switch (anticipo.clase()) {
		   case AND:
			   empareja(ClaseLexica.AND);
			   E3();
			   break;
		   case OR:
			   empareja(ClaseLexica.OR);
			   E4();
			   break;
		   default:
			   esperados(ClaseLexica.AND, ClaseLexica.OR);
			   break;
		   }
	   }
	   
	   private void E4() {
		   switch (anticipo.clase()) {
		   case Entero: case Real:
		   case TRUE: case FALSE:
		   case Identificador:
		   case INIPAR: case NOT: case MENOS:
			   E5();
			   RE4();
			   break;
		   default:
			   esperados(ClaseLexica.Entero, ClaseLexica.Real,
					   ClaseLexica.TRUE, ClaseLexica.FALSE,
					   ClaseLexica.Identificador,
					   ClaseLexica.INIPAR, ClaseLexica.NOT, ClaseLexica.MENOS);
			   error();
			   break;
		   }
	   }
	   
	   private void RE4() {
		   switch (anticipo.clase()) {
		   case POR: case ENTRE:
			   OP4();
			   E5();
			   RE4();
			   break;
		   default:
			   esperados(ClaseLexica.POR, ClaseLexica.ENTRE);
			   break;
		   }
	   }
	   
	   private void E5() {
		   switch (anticipo.clase()) {
		   case NOT: case MENOS:
			   OP5();
			   E5();
			   break;
		   case Entero: case Real:
		   case TRUE: case FALSE:
		   case Identificador:
		   case INIPAR:
			   E6();
			   break;
		   default:
			   esperados(ClaseLexica.Entero, ClaseLexica.Real,
					   ClaseLexica.TRUE, ClaseLexica.FALSE,
					   ClaseLexica.Identificador,
					   ClaseLexica.INIPAR, ClaseLexica.NOT, ClaseLexica.MENOS);
			   error();
			   break;
		   }
	   }
	   
	   private void E6() {
		   switch (anticipo.clase()) {
		   case Entero:
			   empareja(ClaseLexica.Entero);
			   break;
		   case Real:
			   empareja(ClaseLexica.Real);
			   break;
		   case TRUE:
			   empareja(ClaseLexica.TRUE);
			   break;
		   case FALSE:
			   empareja(ClaseLexica.FALSE);
			   break;
		   case Identificador:
			   empareja(ClaseLexica.Identificador);
			   break;
		   case INIPAR:
			   empareja(ClaseLexica.INIPAR);
			   E0();
			   empareja(ClaseLexica.FINPAR);
			   break;
		   default:
			   esperados(ClaseLexica.Entero,ClaseLexica.Real,
					   ClaseLexica.TRUE,ClaseLexica.FALSE,
					   ClaseLexica.Identificador,ClaseLexica.INIPAR);
			   error();
			   break;
		   }
	   }
	   
	   private void OP1() {
		   switch (anticipo.clase()) {
		   case MENOR:
			   empareja(ClaseLexica.MENOR);
			   break;
		   case MENORIGUAL:
			   empareja(ClaseLexica.MENORIGUAL);
			   break;
		   case MAYOR:
			   empareja(ClaseLexica.MAYOR);
			   break;
		   case MAYORIGUAL:
			   empareja(ClaseLexica.MAYORIGUAL);
			   break;
		   case COMPARACION:
			   empareja(ClaseLexica.COMPARACION);
			   break;
		   case DISTINTO:
			   empareja(ClaseLexica.DISTINTO);
			   break;
		   default:
			   esperados(ClaseLexica.MENOR,ClaseLexica.MENORIGUAL,
					   ClaseLexica.MAYOR,ClaseLexica.MAYORIGUAL,
					   ClaseLexica.COMPARACION,ClaseLexica.DISTINTO);
			   error();
			   break;
		   }
	   }
	   
	   private void OP4() {
		   switch (anticipo.clase()) {
		   case POR:
			   empareja(ClaseLexica.POR);
			   break;
		   case ENTRE:
			   empareja(ClaseLexica.ENTRE);
			   break;
		   default:
			   esperados(ClaseLexica.POR, ClaseLexica.ENTRE);
			   error();
			   break;
		   }
	   }
	   
	   private void OP5() {
		   switch (anticipo.clase()) {
		   case NOT:
			   empareja(ClaseLexica.NOT);
			   break;
		   case MENOS:
			   empareja(ClaseLexica.MENOS);
			   break;
		   default:
			   esperados(ClaseLexica.NOT, ClaseLexica.MENOS);
			   error();
			   break;
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

