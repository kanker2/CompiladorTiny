package alex;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {

   private Reader input;
   private StringBuffer lex;
   private int sigCar;
   private int filaInicio;
   private int columnaInicio;
   private int filaActual;
   private int columnaActual;
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
	   RecExp, MenosExp, MasExp, Rec0Exp, RealExp, Real0Exp 
	   
   }

   private Estado estado;

   public AnalizadorLexicoTiny(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual=1;
    columnaActual=1;
   }
   
   public UnidadLexica sigToken() throws IOException {
     estado = Estado.Inicio;
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     lex.delete(0,lex.length());
     while (true) {
    	 switch (estado) {
	    	 case RECAMPERSAND: 
	    		 if (hayAmpersand()) transita(Estado.FINDECLARACIONES);
	    		 else error();
	    		 break;
	    	 case PUNTOYCOMA: return unidadPUNTOYCOMA();
	    	 case FINBLOQUE: return unidadFINBLOQUE();
	    	 case INIBLOQUE: return unidadINIBLOQUE();
	    	 case FINPAR: return unidadFINPAR();
	    	 case INIPAR: return unidadINIPAR();
	    	 case ARROBA: return unidadARROBA();
	    	 case Identificador:
	    		 if (hayGuionBajo() || hayGuionMedio() || hayDigito() || hayLetra()) transita(Estado.Identificador);
	    		 else return unidadIdentificador();
	    		 break; 
	    	 case RecCom0:
	    		 if (hayHashtag()) transita(Estado.RecCom1);
	    		 else error();
	    		 break;
	    	 case RecCom1:
	    		 if (haySaltoLinea()) transita(Estado.Inicio);
	    		 else error();
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
	    		 else error();
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
	    		 else if (hayeE()) transita(Estado.RecExp);
	    		 else return unidadEntero();
	    		 break;
	    	 case RecIDec:
	    		 if (hayDigito()) transita(Estado.RealDec);
	    		 else error();
	    		 break;
	    	 case RealDec:
	    		 if (hayCero()) transita(Estado.Rec0Dec);
	    		 else if (hayeE()) transita(Estado.RecExp);
	    		 else return unidadReal();
	    		 break;
	    	 case Rec0Dec:
	    		 if (hayCero()) transita(Estado.Rec0Dec);
	    		 else if (hayDigitoPositivo()) transita(Estado.RealDec);
	    		 else error();
	    		 break;
	    	 case RecExp:
	    		 if (hayCero()) transita(Estado.Rec0Exp);
	    		 else if (hayDigitoPositivo()) transita(Estado.RealExp);
	    		 else if (hayMENOS()) transita(Estado.MenosExp);
	    		 else if (hayMAS()) transita(Estado.MasExp);
	    		 else error();
	    		 break;
	    	 case MenosExp:
	    		 if (hayDigitoPositivo()) transita(Estado.RealExp);
	    		 else if (hayCero()) transita(Estado.Rec0Exp);
	    		 else error();
	    		 break;
	    	 case MasExp:
	    		 if (hayDigitoPositivo()) transita(Estado.RealExp);
	    		 else if (hayCero()) transita(Estado.Rec0Exp);
	    		 else error();
	    		 break;
	    	 case Rec0Exp:
	    		 if (hayDigitoPositivo()) transita(Estado.RealExp);
	    		 else return unidadReal();
	    		 break;
	    	 case RealExp:
	    		 if (hayCero()) transita(Estado.Real0Exp);
	    		 else return unidadReal();
	    		 break;
	    	 case Real0Exp:
	    		 if (hayCero()) transita(Estado.Real0Exp);
	    		 else if (hayDigitoPositivo()) transita(Estado.RealExp);
	    		 else error();
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
	    		 else if (hayHashtag()) transita(Estado.RecCom0);
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
	    		 else if (haySeparador()) transita(Estado.Inicio);
	    		 else error();
	    		 break;
    		 default:
    			 error();
    			 
     	 }
     }
//     while(true) {
//         switch(estado) {
//           case INICIO: 
//              if(hayLetra())  transita(Estado.REC_ID);
//              else if (hayDigitoPos()) transita(Estado.REC_ENT);
//              else if (hayCero()) transita(Estado.REC_0);
//              else if (haySuma()) transita(Estado.REC_MAS);
//              else if (hayResta()) transita(Estado.REC_MENOS);
//              else if (hayMul()) transita(Estado.REC_POR);
//              else if (hayDiv()) transita(Estado.REC_DIV);
//              else if (hayPAp()) transita(Estado.REC_PAP);
//              else if (hayPCierre()) transita(Estado.REC_PCIERR);
//              else if (hayIgual()) transita(Estado.REC_IGUAL);
//              else if (hayComa()) transita(Estado.REC_COMA);
//              else if (hayAlmohadilla()) transitaIgnorando(Estado.REC_COM);
//              else if (haySep()) transitaIgnorando(Estado.INICIO);
//              else if (hayEOF()) transita(Estado.REC_EOF);
//              else error();
//              break;
//           case REC_ID: 
//              if (hayLetra() || hayDigito()) transita(Estado.REC_ID);
//              else return unidadId();               
//              break;
//           case REC_ENT:
//               if (hayDigito()) transita(Estado.REC_ENT);
//               else if (hayPunto()) transita(Estado.REC_IDEC);
//               else return unidadEnt();
//               break;
//           case REC_0:
//               if (hayPunto()) transita(Estado.REC_IDEC);
//               else return unidadEnt();
//               break;
//           case REC_MAS:
//               if (hayDigitoPos()) transita(Estado.REC_ENT);
//               else if(hayCero()) transita(Estado.REC_0);
//               else return unidadMas();
//               break;
//           case REC_MENOS: 
//               if (hayDigitoPos()) transita(Estado.REC_ENT);
//               else if(hayCero()) transita(Estado.REC_0);
//               else return unidadMenos();
//               break;
//           case REC_POR: return unidadPor();
//           case REC_DIV: return unidadDiv();              
//           case REC_PAP: return unidadPAp();
//           case REC_PCIERR: return unidadPCierre();
//           case REC_IGUAL: return unidadIgual();
//           case REC_COMA: return unidadComa();
//           case REC_COM: 
//               if (hayNL()) transitaIgnorando(Estado.INICIO);
//               else if (hayEOF()) transita(Estado.REC_EOF);
//               else transitaIgnorando(Estado.REC_COM);
//               break;
//           case REC_EOF: return unidadEof();
//           case REC_IDEC:
//               if (hayDigitoPos()) transita(Estado.REC_DEC);
//               else if (hayCero()) transita(Estado.REC_IDEC);
//               else error();
//               break;
//           case REC_DEC: 
//               if (hayDigitoPos()) transita(Estado.REC_DEC);
//               else if (hayCero()) transita(Estado.REC_IDEC);
//               else return unidadReal();
//               break;
//         }
//     }    
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
        columnaActual=0;
     }
     else {
       columnaActual++;  
     }
   }
   private void saltaFinDeLinea() throws IOException {
      for (int i=1; i < NL.length(); i++) {
          sigCar = input.read();
          if (sigCar != NL.charAt(i)) error();
      }
      sigCar = '\n';
   }
   
   private boolean hayLetra() {return sigCar >= 'a' && sigCar <= 'z' ||
                                      sigCar >= 'A' && sigCar <= 'z';}
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
   private boolean hayComa() {return sigCar == ',';}
   private boolean hayPunto() {return sigCar == '.';}
   private boolean hayAlmohadilla() {return sigCar == '#';}
   private boolean haySeparador() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n' || sigCar=='\b' || sigCar=='\r';}
   private boolean haySaltoLinea() {return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';}
   private boolean hayEOF() {return sigCar == -1;}
   private boolean hayExclamacion() {return sigCar=='!';}
   private boolean hayMENOR() {return sigCar=='<';}
   private boolean hayMAYOR() {return sigCar=='>';}
   private boolean hayHashtag() {return sigCar=='#';}
   private boolean hayGuionBajo() {return sigCar=='_';}
   private boolean hayGuionMedio() {return sigCar=='-';}
   private boolean hayARROBA() {return sigCar=='@';}
   private boolean hayPUNTOYCOMA() {return sigCar==';';}
   private boolean hayAmpersand() {return sigCar=='&';}
   private boolean hayeE() {return sigCar=='e' || sigCar=='E';}
   private UnidadLexica unidadIdentificador() {
     switch(lex.toString().toLowerCase()) {
         case "int": 
         	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.INT);
         case "real":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.REAL);
         case "bool":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BOOL);
         case "string":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.STRING);
         case "and":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.AND);
         case "or":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.OR);
         case "not":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NOT);
         case "null":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NULL);
         case "true":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.TRUE);
         case "false":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.FALSE);
         case "proc":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PROC);
         case "if":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.IF);
         case "else":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.ELSE);
         case "while":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.WHILE);
         case "struct":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.STRUCT);
         case "new":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NEW);
         case "delete":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DELETE);
         case "read":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.READ);
         case "write":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.WRITE);
         case "nl":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NL);
         case "type":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.TYPE);
         case "call":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.CALL);
         default:    
            return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.Identificador,lex.toString());     
      }
   }  
   
   private UnidadLexica unidadMENOSUNARIO() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOSUNARIO);
	}

	private UnidadLexica unidadNOT() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NOT);
	}

	private UnidadLexica unidadPOR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.POR);
	}

	private UnidadLexica unidadENTRE() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ENTRE);
	}

	private UnidadLexica unidadAND() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AND);
	}

	private UnidadLexica unidadOR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OR);
	}

	private UnidadLexica unidadMAS() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAS);
	}

	private UnidadLexica unidadMENOS() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOS);
	}

	private UnidadLexica unidadMENOR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR);
	}

	private UnidadLexica unidadEntero() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.Entero);
	}

	private UnidadLexica unidadReal() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.Real);
	}

	private UnidadLexica unidadBooleano() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.Booleano);
	}

	private UnidadLexica unidadMAYOR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR);
	}

	private UnidadLexica unidadMENORIGUAL() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENORIGUAL);
	}

	private UnidadLexica unidadMAYORIGUAL() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYORIGUAL);
	}

	private UnidadLexica unidadCOMPARACION() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.COMPARACION);
	}

	private UnidadLexica unidadDISTINTO() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DISTINTO);
	}

	private UnidadLexica unidadASIGNACION() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ASIGNACION);
	}

	private UnidadLexica unidadINIPAR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INIPAR);
	}

	private UnidadLexica unidadFINPAR() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FINPAR);
	}

	private UnidadLexica unidadINIBLOQUE() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INIBLOQUE);
	}

	private UnidadLexica unidadFINBLOQUE() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FINBLOQUE);
	}

	private UnidadLexica unidadARROBA() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.ARROBA);
	}

	private UnidadLexica unidadFINDECLARACIONES() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FINDECLARACIONES);
	}

	private UnidadLexica unidadPUNTOYCOMA() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PUNTOYCOMA);
	}

	private UnidadLexica unidadEOF() {
	    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EOF);
	}
   
//   private UnidadLexica unidadEnt() {
//     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.ENT,lex.toString());     
//   }    
//   private UnidadLexica unidadReal() {
//     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.REAL,lex.toString());     
//   }    
//   private UnidadLexica unidadMas() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAS);     
//   }    
//   private UnidadLexica unidadMenos() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENOS);     
//   }    
//   private UnidadLexica unidadPor() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.POR);     
//   }    
//   private UnidadLexica unidadDiv() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DIV);     
//   }    
//   private UnidadLexica unidadPAp() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PAP);     
//   }    
//   private UnidadLexica unidadPCierre() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCIERRE);     
//   }    
//   private UnidadLexica unidadIgual() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.IGUAL);     
//   }    
//   private UnidadLexica unidadComa() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.COMA);     
//   }    
//   private UnidadLexica unidadEof() {
//     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
//   }    
   private void error() {
     System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado");  
     System.exit(1);
   }

   public static void main(String arg[]) throws IOException {
     Reader input = new InputStreamReader(new FileInputStream("input.txt"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad;
     do {
       unidad = al.sigToken();
       System.out.println(unidad);
     }
     while (unidad.clase() != ClaseLexica.EOF);
    } 
}