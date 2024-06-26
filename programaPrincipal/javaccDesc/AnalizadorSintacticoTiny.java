/* AnalizadorSintacticoTiny.java */
/* Generated By:JavaCC: Do not edit this line. AnalizadorSintacticoTiny.java */
package javaccDesc;

public class AnalizadorSintacticoTiny implements AnalizadorSintacticoTinyConstants {
    protected void newToken(Token t) {}

  final public void programa_tiny() throws ParseException {
    trace_call("programa_tiny");
    try {

      bloque();
      jj_consume_token(0);
    } finally {
      trace_return("programa_tiny");
    }
}

  final public void bloque() throws ParseException {
    trace_call("bloque");
    try {

      jj_consume_token(INIBLOQUE);
      lista_opt_declaraciones();
      lista_opt_instrucciones();
      jj_consume_token(FINBLOQUE);
    } finally {
      trace_return("bloque");
    }
}

  final public void lista_opt_declaraciones() throws ParseException {
    trace_call("lista_opt_declaraciones");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case REAL:
      case BOOL:
      case STRING:
      case PROC:
      case STRUCT:
      case TYPE:
      case PUNTERO:
      case Identificador:{
        lista_declaraciones();
        jj_consume_token(FINDECLARACIONES);
        break;
        }
      default:
        jj_la1[0] = jj_gen;

      }
    } finally {
      trace_return("lista_opt_declaraciones");
    }
}

  final public void lista_declaraciones() throws ParseException {
    trace_call("lista_declaraciones");
    try {

      declaracion();
      lista_decs();
    } finally {
      trace_return("lista_declaraciones");
    }
}

  final public void lista_decs() throws ParseException {
    trace_call("lista_decs");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PUNTOYCOMA:{
        jj_consume_token(PUNTOYCOMA);
        declaracion();
        lista_decs();
        break;
        }
      default:
        jj_la1[1] = jj_gen;

      }
    } finally {
      trace_return("lista_decs");
    }
}

  final public void declaracion() throws ParseException {
    trace_call("declaracion");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case REAL:
      case BOOL:
      case STRING:
      case STRUCT:
      case PUNTERO:
      case Identificador:{
        declaracion_variable();
        break;
        }
      case TYPE:{
        declaracion_tipo();
        break;
        }
      case PROC:{
        declaracion_proc();
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("declaracion");
    }
}

  final public void declaracion_variable() throws ParseException {
    trace_call("declaracion_variable");
    try {

      tipo();
      jj_consume_token(Identificador);
    } finally {
      trace_return("declaracion_variable");
    }
}

  final public void declaracion_tipo() throws ParseException {
    trace_call("declaracion_tipo");
    try {

      jj_consume_token(TYPE);
      tipo();
      jj_consume_token(Identificador);
    } finally {
      trace_return("declaracion_tipo");
    }
}

  final public void declaracion_proc() throws ParseException {
    trace_call("declaracion_proc");
    try {

      jj_consume_token(PROC);
      jj_consume_token(Identificador);
      jj_consume_token(INIPAR);
      lista_opt_parametros_formales();
      jj_consume_token(FINPAR);
      bloque();
    } finally {
      trace_return("declaracion_proc");
    }
}

  final public void lista_opt_parametros_formales() throws ParseException {
    trace_call("lista_opt_parametros_formales");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case REAL:
      case BOOL:
      case STRING:
      case STRUCT:
      case PUNTERO:
      case Identificador:{
        lista_parametros_formales();
        break;
        }
      default:
        jj_la1[3] = jj_gen;

      }
    } finally {
      trace_return("lista_opt_parametros_formales");
    }
}

  final public void lista_parametros_formales() throws ParseException {
    trace_call("lista_parametros_formales");
    try {

      parametro_formal();
      RECP();
    } finally {
      trace_return("lista_parametros_formales");
    }
}

  final public void RECP() throws ParseException {
    trace_call("RECP");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMA:{
        jj_consume_token(COMA);
        parametro_formal();
        RECP();
        break;
        }
      default:
        jj_la1[4] = jj_gen;

      }
    } finally {
      trace_return("RECP");
    }
}

  final public void parametro_formal() throws ParseException {
    trace_call("parametro_formal");
    try {

      tipo();
      RECpf();
    } finally {
      trace_return("parametro_formal");
    }
}

  final public void RECpf() throws ParseException {
    trace_call("RECpf");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case REFERENCIA:{
        jj_consume_token(REFERENCIA);
        jj_consume_token(Identificador);
        break;
        }
      case Identificador:{
        jj_consume_token(Identificador);
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("RECpf");
    }
}

  final public void tipo() throws ParseException {
    trace_call("tipo");
    try {

      tipo_array();
    } finally {
      trace_return("tipo");
    }
}

  final public void tipo_array() throws ParseException {
    trace_call("tipo_array");
    try {

      tipo_puntero();
      RETA();
    } finally {
      trace_return("tipo_array");
    }
}

  final public void RETA() throws ParseException {
    trace_call("RETA");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INIARRAY:{
        jj_consume_token(INIARRAY);
        jj_consume_token(Entero);
        jj_consume_token(FINARRAY);
        RETA();
        break;
        }
      default:
        jj_la1[6] = jj_gen;

      }
    } finally {
      trace_return("RETA");
    }
}

  final public void tipo_puntero() throws ParseException {
    trace_call("tipo_puntero");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PUNTERO:{
        jj_consume_token(PUNTERO);
        tipo_puntero();
        break;
        }
      case INT:
      case REAL:
      case BOOL:
      case STRING:
      case STRUCT:
      case Identificador:{
        T2();
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("tipo_puntero");
    }
}

  final public void T2() throws ParseException {
    trace_call("T2");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case REAL:
      case BOOL:
      case STRING:{
        tipo_basico();
        break;
        }
      case STRUCT:{
        tipo_registro();
        break;
        }
      case Identificador:{
        tipo_definido();
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("T2");
    }
}

  final public void tipo_basico() throws ParseException {
    trace_call("tipo_basico");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:{
        jj_consume_token(INT);
        break;
        }
      case REAL:{
        jj_consume_token(REAL);
        break;
        }
      case BOOL:{
        jj_consume_token(BOOL);
        break;
        }
      case STRING:{
        jj_consume_token(STRING);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("tipo_basico");
    }
}

  final public void tipo_registro() throws ParseException {
    trace_call("tipo_registro");
    try {

      jj_consume_token(STRUCT);
      jj_consume_token(INIBLOQUE);
      lista_opt_parametros_registro();
      jj_consume_token(FINBLOQUE);
    } finally {
      trace_return("tipo_registro");
    }
}

  final public void tipo_definido() throws ParseException {
    trace_call("tipo_definido");
    try {

      jj_consume_token(Identificador);
    } finally {
      trace_return("tipo_definido");
    }
}

  final public void lista_opt_parametros_registro() throws ParseException {
    trace_call("lista_opt_parametros_registro");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case REAL:
      case BOOL:
      case STRING:
      case STRUCT:
      case PUNTERO:
      case Identificador:{
        lista_parametros_registro();
        break;
        }
      default:
        jj_la1[10] = jj_gen;

      }
    } finally {
      trace_return("lista_opt_parametros_registro");
    }
}

  final public void lista_parametros_registro() throws ParseException {
    trace_call("lista_parametros_registro");
    try {

      parametro_registro();
      REClpr();
    } finally {
      trace_return("lista_parametros_registro");
    }
}

  final public void REClpr() throws ParseException {
    trace_call("REClpr");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMA:{
        jj_consume_token(COMA);
        parametro_registro();
        REClpr();
        break;
        }
      default:
        jj_la1[11] = jj_gen;

      }
    } finally {
      trace_return("REClpr");
    }
}

  final public void parametro_registro() throws ParseException {
    trace_call("parametro_registro");
    try {

      tipo();
      jj_consume_token(Identificador);
    } finally {
      trace_return("parametro_registro");
    }
}

  final public void lista_opt_instrucciones() throws ParseException {
    trace_call("lista_opt_instrucciones");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IF:
      case WHILE:
      case NEW:
      case DELETE:
      case READ:
      case WRITE:
      case NL:
      case CALL:
      case INIBLOQUE:
      case ARROBA:{
        lista_instrucciones();
        break;
        }
      default:
        jj_la1[12] = jj_gen;

      }
    } finally {
      trace_return("lista_opt_instrucciones");
    }
}

  final public void lista_instrucciones() throws ParseException {
    trace_call("lista_instrucciones");
    try {

      instruccion();
      RECI();
    } finally {
      trace_return("lista_instrucciones");
    }
}

  final public void RECI() throws ParseException {
    trace_call("RECI");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PUNTOYCOMA:{
        jj_consume_token(PUNTOYCOMA);
        instruccion();
        RECI();
        break;
        }
      default:
        jj_la1[13] = jj_gen;

      }
    } finally {
      trace_return("RECI");
    }
}

  final public void instruccion() throws ParseException {
    trace_call("instruccion");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ARROBA:{
        instruccion_eval();
        break;
        }
      case IF:{
        instruccion_if();
        break;
        }
      case WHILE:{
        instruccion_while();
        break;
        }
      case READ:{
        instruccion_read();
        break;
        }
      case WRITE:{
        instruccion_write();
        break;
        }
      case NL:{
        instruccion_nl();
        break;
        }
      case NEW:{
        instruccion_new();
        break;
        }
      case DELETE:{
        instruccion_delete();
        break;
        }
      case CALL:{
        instruccion_call();
        break;
        }
      case INIBLOQUE:{
        instruccion_compuesta();
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("instruccion");
    }
}

  final public void instruccion_eval() throws ParseException {
    trace_call("instruccion_eval");
    try {

      jj_consume_token(ARROBA);
      expresion();
    } finally {
      trace_return("instruccion_eval");
    }
}

  final public void expresion() throws ParseException {
    trace_call("expresion");
    try {

      E0();
    } finally {
      trace_return("expresion");
    }
}

  final public void E0() throws ParseException {
    trace_call("E0");
    try {

      E1();
      FE0();
    } finally {
      trace_return("E0");
    }
}

  final public void FE0() throws ParseException {
    trace_call("FE0");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASIGNACION:{
        jj_consume_token(ASIGNACION);
        E0();
        break;
        }
      default:
        jj_la1[15] = jj_gen;

      }
    } finally {
      trace_return("FE0");
    }
}

  final public void E1() throws ParseException {
    trace_call("E1");
    try {

      E2();
      RE1();
    } finally {
      trace_return("E1");
    }
}

  final public void RE1() throws ParseException {
    trace_call("RE1");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MENOR:
      case MENORIGUAL:
      case MAYOR:
      case MAYORIGUAL:
      case COMPARACION:
      case DISTINTO:{
        OP1();
        E2();
        RE1();
        break;
        }
      default:
        jj_la1[16] = jj_gen;

      }
    } finally {
      trace_return("RE1");
    }
}

  final public void E2() throws ParseException {
    trace_call("E2");
    try {

      E3();
      FE2();
      RE2();
    } finally {
      trace_return("E2");
    }
}

  final public void RE2() throws ParseException {
    trace_call("RE2");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MAS:{
        jj_consume_token(MAS);
        E3();
        RE2();
        break;
        }
      default:
        jj_la1[17] = jj_gen;

      }
    } finally {
      trace_return("RE2");
    }
}

  final public void FE2() throws ParseException {
    trace_call("FE2");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MENOS:{
        jj_consume_token(MENOS);
        E3();
        break;
        }
      default:
        jj_la1[18] = jj_gen;

      }
    } finally {
      trace_return("FE2");
    }
}

  final public void E3() throws ParseException {
    trace_call("E3");
    try {

      E4();
      FE3();
    } finally {
      trace_return("E3");
    }
}

  final public void FE3() throws ParseException {
    trace_call("FE3");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        jj_consume_token(AND);
        E3();
        break;
        }
      case OR:{
        jj_consume_token(OR);
        E4();
        break;
        }
      default:
        jj_la1[19] = jj_gen;

      }
    } finally {
      trace_return("FE3");
    }
}

  final public void E4() throws ParseException {
    trace_call("E4");
    try {

      E5();
      RE4();
    } finally {
      trace_return("E4");
    }
}

  final public void RE4() throws ParseException {
    trace_call("RE4");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case POR:
      case ENTRE:
      case MODULO:{
        OP4();
        E5();
        RE4();
        break;
        }
      default:
        jj_la1[20] = jj_gen;

      }
    } finally {
      trace_return("RE4");
    }
}

  final public void E5() throws ParseException {
    trace_call("E5");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NOT:
      case MENOS:{
        OP5();
        E5();
        break;
        }
      case NULL:
      case TRUE:
      case FALSE:
      case INIPAR:
      case Identificador:
      case Entero:
      case Real:
      case Cadena:{
        E6();
        break;
        }
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("E5");
    }
}

  final public void E6() throws ParseException {
    trace_call("E6");
    try {

      E7();
      RE6();
    } finally {
      trace_return("E6");
    }
}

  final public void RE6() throws ParseException {
    trace_call("RE6");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INIARRAY:
      case PUNTERO:
      case PUNTO:{
        OP6();
        RE6();
        break;
        }
      default:
        jj_la1[22] = jj_gen;

      }
    } finally {
      trace_return("RE6");
    }
}

  final public void E7() throws ParseException {
    trace_call("E7");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case Entero:{
        jj_consume_token(Entero);
        break;
        }
      case Real:{
        jj_consume_token(Real);
        break;
        }
      case TRUE:{
        jj_consume_token(TRUE);
        break;
        }
      case FALSE:{
        jj_consume_token(FALSE);
        break;
        }
      case NULL:{
        jj_consume_token(NULL);
        break;
        }
      case Cadena:{
        jj_consume_token(Cadena);
        break;
        }
      case Identificador:{
        jj_consume_token(Identificador);
        break;
        }
      case INIPAR:{
        jj_consume_token(INIPAR);
        E0();
        jj_consume_token(FINPAR);
        break;
        }
      default:
        jj_la1[23] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("E7");
    }
}

  final public void OP1() throws ParseException {
    trace_call("OP1");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MAYOR:{
        jj_consume_token(MAYOR);
        break;
        }
      case MAYORIGUAL:{
        jj_consume_token(MAYORIGUAL);
        break;
        }
      case MENOR:{
        jj_consume_token(MENOR);
        break;
        }
      case MENORIGUAL:{
        jj_consume_token(MENORIGUAL);
        break;
        }
      case COMPARACION:{
        jj_consume_token(COMPARACION);
        break;
        }
      case DISTINTO:{
        jj_consume_token(DISTINTO);
        break;
        }
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("OP1");
    }
}

  final public void OP4() throws ParseException {
    trace_call("OP4");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case POR:{
        jj_consume_token(POR);
        break;
        }
      case ENTRE:{
        jj_consume_token(ENTRE);
        break;
        }
      case MODULO:{
        jj_consume_token(MODULO);
        break;
        }
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("OP4");
    }
}

  final public void OP5() throws ParseException {
    trace_call("OP5");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NOT:{
        jj_consume_token(NOT);
        break;
        }
      case MENOS:{
        jj_consume_token(MENOS);
        break;
        }
      default:
        jj_la1[26] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("OP5");
    }
}

  final public void OP6() throws ParseException {
    trace_call("OP6");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INIARRAY:{
        jj_consume_token(INIARRAY);
        E0();
        jj_consume_token(FINARRAY);
        break;
        }
      case PUNTO:{
        jj_consume_token(PUNTO);
        jj_consume_token(Identificador);
        break;
        }
      case PUNTERO:{
        jj_consume_token(PUNTERO);
        break;
        }
      default:
        jj_la1[27] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
      trace_return("OP6");
    }
}

  final public void instruccion_if() throws ParseException {
    trace_call("instruccion_if");
    try {

      jj_consume_token(IF);
      expresion();
      bloque();
      instruccion_else();
    } finally {
      trace_return("instruccion_if");
    }
}

  final public void instruccion_else() throws ParseException {
    trace_call("instruccion_else");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ELSE:{
        jj_consume_token(ELSE);
        bloque();
        break;
        }
      default:
        jj_la1[28] = jj_gen;

      }
    } finally {
      trace_return("instruccion_else");
    }
}

  final public void instruccion_while() throws ParseException {
    trace_call("instruccion_while");
    try {

      jj_consume_token(WHILE);
      expresion();
      bloque();
    } finally {
      trace_return("instruccion_while");
    }
}

  final public void instruccion_read() throws ParseException {
    trace_call("instruccion_read");
    try {

      jj_consume_token(READ);
      expresion();
    } finally {
      trace_return("instruccion_read");
    }
}

  final public void instruccion_write() throws ParseException {
    trace_call("instruccion_write");
    try {

      jj_consume_token(WRITE);
      expresion();
    } finally {
      trace_return("instruccion_write");
    }
}

  final public void instruccion_nl() throws ParseException {
    trace_call("instruccion_nl");
    try {

      jj_consume_token(NL);
    } finally {
      trace_return("instruccion_nl");
    }
}

  final public void instruccion_new() throws ParseException {
    trace_call("instruccion_new");
    try {

      jj_consume_token(NEW);
      expresion();
    } finally {
      trace_return("instruccion_new");
    }
}

  final public void instruccion_delete() throws ParseException {
    trace_call("instruccion_delete");
    try {

      jj_consume_token(DELETE);
      expresion();
    } finally {
      trace_return("instruccion_delete");
    }
}

  final public void instruccion_call() throws ParseException {
    trace_call("instruccion_call");
    try {

      jj_consume_token(CALL);
      jj_consume_token(Identificador);
      parametros();
    } finally {
      trace_return("instruccion_call");
    }
}

  final public void parametros() throws ParseException {
    trace_call("parametros");
    try {

      jj_consume_token(INIPAR);
      lista_opt_parametros();
      jj_consume_token(FINPAR);
    } finally {
      trace_return("parametros");
    }
}

  final public void lista_opt_parametros() throws ParseException {
    trace_call("lista_opt_parametros");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NOT:
      case NULL:
      case TRUE:
      case FALSE:
      case MENOS:
      case INIPAR:
      case Identificador:
      case Entero:
      case Real:
      case Cadena:{
        lista_parametros();
        break;
        }
      default:
        jj_la1[29] = jj_gen;

      }
    } finally {
      trace_return("lista_opt_parametros");
    }
}

  final public void lista_parametros() throws ParseException {
    trace_call("lista_parametros");
    try {

      expresion();
      REClp();
    } finally {
      trace_return("lista_parametros");
    }
}

  final public void REClp() throws ParseException {
    trace_call("REClp");
    try {

      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMA:{
        jj_consume_token(COMA);
        expresion();
        REClp();
        break;
        }
      default:
        jj_la1[30] = jj_gen;

      }
    } finally {
      trace_return("REClp");
    }
}

  final public void instruccion_compuesta() throws ParseException {
    trace_call("instruccion_compuesta");
    try {

      bloque();
    } finally {
      trace_return("instruccion_compuesta");
    }
}

  /** Generated Token Manager. */
  public AnalizadorSintacticoTinyTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[31];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x20881e00,0x0,0x20881e00,0x801e00,0x0,0x0,0x0,0x801e00,0x801e00,0x1e00,0x801e00,0x0,0x5f500000,0x0,0x5f500000,0x0,0x0,0x0,0x0,0x6000,0x80000000,0x78000,0x0,0x70000,0x0,0x80000000,0x8000,0x0,0x200000,0x78000,0x0,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x1080000,0x10000,0x1080000,0x1080000,0x200000,0x1400000,0x20000,0x1080000,0x1000000,0x0,0x1080000,0x200000,0x5000,0x10000,0x5000,0x200,0x1f8,0x2,0x4,0x0,0x100001,0xf000404,0x8a0000,0xf000400,0x1f8,0x100001,0x4,0x8a0000,0x0,0xf000404,0x200000,};
	}

  {
      enable_tracing();
  }
  /** Constructor with InputStream. */
  public AnalizadorSintacticoTiny(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public AnalizadorSintacticoTiny(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public AnalizadorSintacticoTiny(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new AnalizadorSintacticoTinyTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public AnalizadorSintacticoTiny(AnalizadorSintacticoTinyTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AnalizadorSintacticoTinyTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   trace_token(token, "");
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	   trace_token(token, " (in getNextToken)");
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[60];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 31; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 60; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  private int trace_indent = 0;
/** Enable tracing. */
  final public void enable_tracing() {
	 trace_enabled = true;
  }

/** Disable tracing. */
  final public void disable_tracing() {
	 trace_enabled = false;
  }

  protected void trace_call(String s) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.println("Call:	" + s);
	 }
	 trace_indent = trace_indent + 2;
  }

  protected void trace_return(String s) {
	 trace_indent = trace_indent - 2;
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.println("Return: " + s);
	 }
  }

  protected void trace_token(Token t, String where) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.print("Consumed token: <" + tokenImage[t.kind]);
	   if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
		 System.out.print(": \"" + TokenMgrError.addEscapes(t.image) + "\"");
	   }
	   System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
	 }
  }

  protected void trace_scan(Token t1, int t2) {
	 if (trace_enabled) {
	   for (int i = 0; i < trace_indent; i++) { System.out.print(" "); }
	   System.out.print("Visited token: <" + tokenImage[t1.kind]);
	   if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
		 System.out.print(": \"" + TokenMgrError.addEscapes(t1.image) + "\"");
	   }
	   System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
	 }
  }

}