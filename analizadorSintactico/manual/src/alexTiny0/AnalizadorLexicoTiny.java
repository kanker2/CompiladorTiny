package alexTiny0;

import java.io.Reader;
import java.io.IOException;

public class AnalizadorLexicoTiny {

   private Reader input;
   private StringBuffer lex;
   private int sigCar;
   private int filaInicio;
   private int columnaInicio;
   private int filaActual;
   private int columnaActual;
   private boolean huboError;
   private static String NL = System.getProperty("line.separator");
   
   private static enum Estado {
	   Inicio,
	   RECAMPERSAND, FINDECLARACIONES,
	   PUNTOYCOMA,
	   FINBLOQUE, INIBLOQUE,
	   FINPAR, INIPAR,
	   ARROBA,
	   Identificador,
	   RecCom0, RecCom1,
	   EOF,
	   POR, ENTRE,
	   MAYOR, MAYORIGUAL,
	   MENOR, MENORIGUAL,
	   ASIGNACION, COMPARACION,
	   RecExcl, DISTINTO,
	   MENOS, MAS, REC0,
	   Entero,
	   RecIDec, Rec0Dec, RealDec,
	   RecExp, MenosExp, MasExp, Rec0Exp, RealExp, 
	   
   }

   private Estado estado;

   public AnalizadorLexicoTiny(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual=1;
    columnaActual=1;
    huboError = false;
   }
   
   public UnidadLexica sigToken() throws IOException {
     estado = Estado.Inicio;
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     if (huboError)
    	 huboError = false;
     else
    	 lex.delete(0,lex.length());
     while (true) {
    	 switch (estado) {
	    	 case RECAMPERSAND: 
	    		 if (hayAmpersand()) transita(Estado.FINDECLARACIONES);
	    		 else error(Estado.RECAMPERSAND);
	    		 break;
	    	 case FINDECLARACIONES: return unidadFINDECLARACIONES();
	    	 case PUNTOYCOMA: return unidadPUNTOYCOMA();
	    	 case FINBLOQUE: return unidadFINBLOQUE();
	    	 case INIBLOQUE: return unidadINIBLOQUE();
	    	 case FINPAR: return unidadFINPAR();
	    	 case INIPAR: return unidadINIPAR();
	    	 case ARROBA: return unidadARROBA();
	    	 case Identificador:
	    		 if (hayGuionBajo() || hayDigito() || hayLetra()) transita(Estado.Identificador);
	    		 else return unidadIdentificador();
	    		 break; 
	    	 case RecCom0:
	    		 if (hayHashtag()) transitaIgnorando(Estado.RecCom1);
	    		 else error(Estado.RecCom0);
	    		 break;
	    	 case RecCom1:
	    		 if (haySaltoLinea()) transitaIgnorando(Estado.Inicio);
	    		 else if (hayEOF()) transita(Estado.EOF);
	    		 else transitaIgnorando(Estado.RecCom1);
	    		 break;
	    	 case EOF: return unidadEOF();
	    	 case POR: return unidadPOR();
	    	 case ENTRE: return unidadENTRE();
	    	 case MAYOR:
	    		 if (hayIgual()) transita(Estado.MAYORIGUAL);
	    		 else return unidadMAYOR();
	    		 break;
	    	 case MAYORIGUAL: return unidadMAYORIGUAL();
	    	 case MENOR:
	    		 if (hayIgual()) transita(Estado.MENORIGUAL);
	    		 else return unidadMENOR();
	    		 break;
	    	 case MENORIGUAL: return unidadMENORIGUAL();
	    	 case ASIGNACION:
	    		 if (hayIgual()) transita(Estado.COMPARACION);
	    		 else return unidadASIGNACION();
	    		 break;
	    	 case COMPARACION: return unidadCOMPARACION();
	    	 case RecExcl:
	    		 if (hayIgual()) transita(Estado.DISTINTO);
	    		 else error(Estado.RecExcl);
	    		 break;
	    	 case DISTINTO: return unidadDISTINTO();
	    	 case MENOS:
	    		 if (hayCero()) transita(Estado.REC0);
	    		 else if (hayDigitoPositivo()) transita(Estado.Entero);
	    		 else return unidadMENOS();
	    		 break;
	    	 case MAS:
	    		 if (hayCero()) transita(Estado.REC0);
	    		 else if (hayDigitoPositivo()) transita(Estado.Entero);
	    		 else return unidadMAS();
	    		 break;
	    	 case Entero:
	    		 if (hayDigito()) transita(Estado.Entero);
	    		 else if (hayPunto()) transita(Estado.RecIDec);
	    		 else if (hayE()) transita(Estado.RecExp);
	    		 else return unidadEntero();
	    		 break;
	    	 case RecIDec:
	    		 if (hayDigito()) transita(Estado.RealDec);
	    		 else error(Estado.RecIDec);
	    		 break;
	    	 case RealDec:
	    		 if (hayCero()) transita(Estado.Rec0Dec);
	    		 else if (hayE()) transita(Estado.RecExp);
	    		 else if (hayDigitoPositivo()) transita(Estado.RealDec);
	    		 else return unidadReal();
	    		 break;
	    	 case Rec0Dec:
	    		 if (hayCero()) transita(Estado.Rec0Dec);
	    		 else if (hayDigitoPositivo()) transita(Estado.RealDec);
	    		 else error(Estado.Rec0Dec);
	    		 break;
	    	 case RecExp:
	    		 if (hayCero()) transita(Estado.Rec0Exp);
	    		 else if (hayDigitoPositivo()) transita(Estado.RealExp);
	    		 else if (hayMENOS()) transita(Estado.MenosExp);
	    		 else if (hayMAS()) transita(Estado.MasExp);
	    		 else error(Estado.RecExp);
	    		 break;
	    	 case MenosExp:
	    		 if (hayDigitoPositivo()) transita(Estado.RealExp);
	    		 else if (hayCero()) transita(Estado.Rec0Exp);
	    		 else error(Estado.MenosExp);
	    		 break;
	    	 case MasExp:
	    		 if (hayDigitoPositivo()) transita(Estado.RealExp);
	    		 else if (hayCero()) transita(Estado.Rec0Exp);
	    		 else error(Estado.MasExp);
	    		 break;
	    	 case Rec0Exp:
	    		 return unidadReal();
	    	 case RealExp:
	    		 if(hayDigito()) transita(Estado.RealExp);
	    		 else return unidadReal();
	    		 break;
	    	 case REC0:
	    		 if(hayPunto()) transita(Estado.RecIDec);
	    		 else if(hayE()) transita(Estado.RecExp);
	    		 else return unidadEntero();
	    		 break;
	    	 case Inicio:
	    		 if (hayAmpersand()) transita(Estado.RECAMPERSAND);
	    		 else if (hayPUNTOYCOMA()) transita(Estado.PUNTOYCOMA);
	    		 else if (hayFINBLOQUE()) transita(Estado.FINBLOQUE);
	    		 else if (hayINIBLOQUE()) transita(Estado.INIBLOQUE);
	    		 else if (hayFINPAR()) transita(Estado.FINPAR);
	    		 else if (hayINIPAR()) transita(Estado.INIPAR);
	    		 else if (hayARROBA()) transita(Estado.ARROBA);
	    		 else if (hayLetra() || hayGuionBajo()) transita(Estado.Identificador);
	    		 else if (hayHashtag()) transitaIgnorando(Estado.RecCom0);
	    		 else if (hayEOF()) transita(Estado.EOF);
	    		 else if (hayPOR()) transita(Estado.POR);
	    		 else if (hayENTRE()) transita(Estado.ENTRE);
	    		 else if (hayMAYOR()) transita(Estado.MAYOR);
	    		 else if (hayMENOR()) transita(Estado.MENOR);
	    		 else if (hayIgual()) transita(Estado.ASIGNACION);
	    		 else if (hayExclamacion()) transita(Estado.RecExcl);
	    		 else if (hayMAS()) transita(Estado.MAS);
	    		 else if (hayMENOS()) transita(Estado.MENOS);
	    		 else if (hayCero()) transita(Estado.REC0);
	    		 else if (hayDigitoPositivo()) transita(Estado.Entero);
	    		 else if (haySeparador()) transitaIgnorando(Estado.Inicio);
	    		 else error(Estado.Inicio);
	    		 break;
    		 default:
    			 error(null);
    			 
     	 }
     }
   }
   private void transita(Estado sig) throws IOException {
     lex.append((char)sigCar);
     sigCar();
     estado = sig;
   }
   private void transitaIgnorando(Estado sig) throws IOException {
     sigCar();         
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     estado = sig;
   }
   private void sigCar() throws IOException {
     sigCar = input.read();
     if (sigCar == NL.charAt(0)) saltaFinDeLinea();
     if (sigCar == '\n') {
        filaActual++;
        columnaActual=1;
     }
     else {
       columnaActual++;  
     }
   }
   private void saltaFinDeLinea() throws IOException {
      for (int i=1; i < NL.length(); i++) {
          sigCar = input.read();
          if (sigCar != NL.charAt(i)) error(null);
      }
      sigCar = '\n';
   }
   
   private boolean hayLetra() {return sigCar >= 'a' && sigCar <= 'z' ||
                                      sigCar >= 'A' && sigCar <= 'Z';}
   private boolean hayDigitoPositivo() {return sigCar >= '1' && sigCar <= '9';}
   private boolean hayCero() {return sigCar == '0';}
   private boolean hayDigito() {return hayDigitoPositivo() || hayCero();}
   private boolean hayMAS() {return sigCar == '+';}
   private boolean hayMENOS() {return sigCar == '-';}
   private boolean hayPOR() {return sigCar == '*';}
   private boolean hayENTRE() {return sigCar == '/';}
   private boolean hayINIPAR() {return sigCar == '(';}
   private boolean hayFINPAR() {return sigCar == ')';}
   private boolean hayINIBLOQUE() {return sigCar == '{';}
   private boolean hayFINBLOQUE() {return sigCar == '}';}
   private boolean hayIgual() {return sigCar == '=';}
   private boolean hayPunto() {return sigCar == '.';}
   private boolean haySeparador() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n';}
   private boolean haySaltoLinea() {return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';}
   private boolean hayEOF() {return sigCar == -1;}
   private boolean hayExclamacion() {return sigCar=='!';}
   private boolean hayMENOR() {return sigCar=='<';}
   private boolean hayMAYOR() {return sigCar=='>';}
   private boolean hayHashtag() {return sigCar=='#';}
   private boolean hayGuionBajo() {return sigCar=='_';}
   private boolean hayARROBA() {return sigCar=='@';}
   private boolean hayPUNTOYCOMA() {return sigCar==';';}
   private boolean hayAmpersand() {return sigCar=='&';}
   private boolean hayE() {return sigCar=='e' || sigCar=='E';}
   
   private UnidadLexica unidadIdentificador() {
     switch(lex.toString().toLowerCase()) {
         case "int": 
         	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.INT, "<int>");
         case "real":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.REAL, "<real>");
         case "bool":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BOOL, "<bool>");
         case "and":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.AND, "<and>");
         case "or":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.OR, "<or>");
         case "not":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NOT, "<not>");
         case "true":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.TRUE, "<true>");
         case "false":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.FALSE, "<false>");
         default:    
            return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.Identificador,lex.toString());     
      }
   }  
   
	private UnidadLexica unidadPOR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.POR, "*");
	}

	private UnidadLexica unidadENTRE() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ENTRE, "/");
	}

	private UnidadLexica unidadMAS() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAS, "+");
	}

	private UnidadLexica unidadMENOS() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOS, "-");
	}

	private UnidadLexica unidadMENOR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR, "<");
	}

	private UnidadLexica unidadEntero() {
	    return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.Entero, lex.toString());
	}

	private UnidadLexica unidadReal() {
	    return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.Real, lex.toString());
	}

	private UnidadLexica unidadMAYOR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR, ">");
	}

	private UnidadLexica unidadMENORIGUAL() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENORIGUAL, "<=");
	}

	private UnidadLexica unidadMAYORIGUAL() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYORIGUAL, ">=");
	}

	private UnidadLexica unidadCOMPARACION() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.COMPARACION, "==");
	}

	private UnidadLexica unidadDISTINTO() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DISTINTO, "!=");
	}

	private UnidadLexica unidadASIGNACION() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ASIGNACION, "=");
	}

	private UnidadLexica unidadINIPAR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INIPAR, "(");
	}

	private UnidadLexica unidadFINPAR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FINPAR, ")");
	}

	private UnidadLexica unidadINIBLOQUE() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INIBLOQUE, "{");
	}

	private UnidadLexica unidadFINBLOQUE() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FINBLOQUE, "}");
	}

	private UnidadLexica unidadARROBA() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ARROBA, "@");
	}

	private UnidadLexica unidadFINDECLARACIONES() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FINDECLARACIONES, "&&");
	}

	private UnidadLexica unidadPUNTOYCOMA() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PUNTOYCOMA, ";");
	}

	private UnidadLexica unidadEOF() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EOF, "EOF");
	}
 
	private void error(Estado e) throws IOException {
		//String msgError = "Carácter inesperado (" + filaActual + "," + columnaActual + "): " + (char)sigCar + "; " + caracterEsperado(e);
		
		sigCar();
		estado = Estado.Inicio;
		throw new ECaracterInesperado("ERROR");
	}
	
	private String caracterEsperado(Estado e) {
		
		String msg = "Se esperaba un ";
		switch (e) {
			case RecCom0:
				return msg+"#";
			case RECAMPERSAND:
				return msg+'&';
			case RecExcl:
				return msg+"=";
			case RecIDec:
			case Rec0Dec:
			case MasExp:
			case MenosExp:
				return msg+"dígito";
			case Rec0Exp:
				return msg+"dígito o signo";
			case RecCom1:
			case Inicio:
				return "No se reconoce el caracter";
			default:
				return "No se reconoce el caracter";
		}
	}
	   
}
