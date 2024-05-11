package asint;

import visitante.Procesamiento;

public class SintaxisAbstractaTiny {
	public SintaxisAbstractaTiny() {
	};

	public interface ImpresionInterprete {
		public abstract void imprime();
	}

	public static String getPosDJ(Integer f, Integer c) {
		return "$f:" + f.toString() + ",c:" + c.toString() + "$";
	}

	public static abstract class Nodo {

		public Nodo vinculo;
		public boolean tipoCorrecto;
		public Tipo tipo;
		public int nivel;
		public int dir;
		public int tam;
		public int prim;
		public int sig;
		
		public Nodo() {
			fila = col = -1;
		}

		private int fila;
		private int col;

		public Nodo ponFila(int fila) {
			this.fila = fila;
			return this;
		}

		public Nodo ponCol(int col) {
			this.col = col;
			return this;
		}

		public int leeFila() {
			return fila;
		}

		public int leeCol() {
			return col;
		}

		public abstract void imprime();

		public abstract void procesa(Procesamiento p);
	}

	// Clases abstractas (Generos)
	public static abstract class ProgT extends Nodo implements ImpresionInterprete {
		protected Blq bloque;

		public ProgT(Blq bloque) {
			this.bloque = bloque;
		}

		public Blq bloque() {
			throw new UnsupportedOperationException();
		}

		public abstract void procesa(Procesamiento p);
	}

	public static abstract class Blq extends Nodo implements ImpresionInterprete {
		protected Blq bloque;
		protected LOptDecs lista_opt_declaraciones;
		protected LOptInst lista_opt_instrucciones;

		public Blq() {
		}

		public Blq(Blq bloque) {
			this.bloque = bloque;
		}

		public Blq(LOptDecs lista_opt_declaraciones, LOptInst lista_opt_instrucciones) {
			this.lista_opt_declaraciones = lista_opt_declaraciones;
			this.lista_opt_instrucciones = lista_opt_instrucciones;
		}

		public LOptDecs lista_opt_declaraciones() {
			throw new UnsupportedOperationException();
		}

		public LOptInst lista_opt_instrucciones() {
			throw new UnsupportedOperationException();
		}

		public abstract void procesa(Procesamiento p);

	}

	public static abstract class LOptDecs extends Nodo implements ImpresionInterprete {
		protected LDecs lista_declaraciones;

		public LOptDecs(LDecs lista_declaraciones) {
			this.lista_declaraciones = lista_declaraciones;
		}

		public LOptDecs() {
		};

		public LDecs lista_declaraciones() {
			throw new UnsupportedOperationException();
		}

		public abstract void procesa(Procesamiento p);
	}

	public static abstract class LDecs extends Nodo implements ImpresionInterprete {
		protected LDecs lista_declaraciones;
		protected Dec declaracion;

		public LDecs(LDecs lista_declaraciones, Dec declaracion) {
			this.lista_declaraciones = lista_declaraciones;
			this.declaracion = declaracion;
		}

		public LDecs(Dec declaracion) {
			this.declaracion = declaracion;
		}

		public LDecs lista_declaraciones() {
			throw new UnsupportedOperationException();
		}

		public Dec declaracion() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class Dec extends Nodo implements ImpresionInterprete {
		protected Tipo tipo;
		protected String cadena;
		protected Blq bloque;
		protected LOptParamForm lista_opt_parametros_formales;

		public Dec(Tipo tipo, String cadena) {
			this.tipo = tipo;
			this.cadena = cadena;
		}

		public Dec(String cadena, LOptParamForm lista_opt_parametros_formales, Blq bloque) {
			this.cadena = cadena;
			this.lista_opt_parametros_formales = lista_opt_parametros_formales;
			this.bloque = bloque;
		}

		public Tipo tipo() {
			throw new UnsupportedOperationException();
		}

		public String cadena() {
			throw new UnsupportedOperationException();
		}

		public Blq bloque() {
			throw new UnsupportedOperationException();
		}

		public LOptParamForm lista_opt_parametros_formales() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class LOptParamForm extends Nodo implements ImpresionInterprete {
		protected LParamForm lista_parametros_formales;

		public LOptParamForm(LParamForm lista_parametros_formales) {
			this.lista_parametros_formales = lista_parametros_formales;
		}

		public LOptParamForm() {
		};

		public LParamForm lista_parametros_formales() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class LParamForm extends Nodo implements ImpresionInterprete {
		protected LParamForm lista_parametros_formales;
		protected ParamForm parametro_formal;

		public LParamForm(LParamForm lista_parametros_formales, ParamForm parametro_formal) {
			this.lista_parametros_formales = lista_parametros_formales;
			this.parametro_formal = parametro_formal;
		}

		public LParamForm(ParamForm parametro_formal) {
			this.parametro_formal = parametro_formal;
		}

		public LParamForm lista_parametros_formales() {
			throw new UnsupportedOperationException();
		}

		public ParamForm parametro_formal() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class ParamForm extends Nodo implements ImpresionInterprete {
		protected Tipo tipo;
		protected String cadena;

		public ParamForm() {
		};

		public ParamForm(Tipo tipo, String cadena) {
			this.tipo = tipo;
			this.cadena = cadena;
		}

		public Tipo tipo() {
			throw new UnsupportedOperationException();
		}

		public String cadena() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class Tipo extends Nodo implements ImpresionInterprete {
		protected Tipo tipo;
		protected String cadena;
		protected LParamReg lista_parametros_registro;
		protected boolean tipo_definido = false;

		public Tipo() {
		};

		public Tipo(Tipo tipo, String cadena) {
			this.cadena = cadena;
			this.tipo = tipo;
		}

		public Tipo(Tipo tipo) {
			this.tipo = tipo;
		}

		public Tipo(String cadena) {
			this.cadena = cadena;
		}

		public Tipo(LParamReg lista_parametros_registro) {
			this.lista_parametros_registro = lista_parametros_registro;
		}

		public Tipo tipo() {
			throw new UnsupportedOperationException();
		}

		public String cadena() {
			throw new UnsupportedOperationException();
		}

		public LParamReg lista_parametros_registro() {
			throw new UnsupportedOperationException();
		}
		
		public boolean tipoDefinido() { return tipo_definido; }
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class LParamReg extends Nodo implements ImpresionInterprete {
		protected LParamReg lista_parametros_registro;
		protected ParamReg parametro_registro;

		public LParamReg(LParamReg lista_parametros_registro, ParamReg parametro_registro) {
			this.lista_parametros_registro = lista_parametros_registro;
			this.parametro_registro = parametro_registro;
		}

		public LParamReg(ParamReg parametro_registro) {
			this.parametro_registro = parametro_registro;
		}

		public LParamReg lista_parametros_registro() {
			throw new UnsupportedOperationException();
		}

		public ParamReg parametro_registro() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class ParamReg extends Nodo implements ImpresionInterprete {
		protected Tipo tipo;
		protected String cadena;

		public ParamReg(Tipo tipo, String cadena) {
			this.tipo = tipo;
			this.cadena = cadena;
		}

		public Tipo tipo() {
			throw new UnsupportedOperationException();
		}

		public String cadena() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class LOptInst extends Nodo implements ImpresionInterprete {
		protected LInst lista_instrucciones;

		public LOptInst() {
		};

		public LOptInst(LInst lista_instrucciones) {
			this.lista_instrucciones = lista_instrucciones;
		}

		public LInst lista_instrucciones() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);

	}

	public static abstract class LInst extends Nodo implements ImpresionInterprete {
		protected LInst lista_instrucciones;
		protected Inst instruccion;

		public LInst(LInst lista_instrucciones, Inst instruccion) {
			this.lista_instrucciones = lista_instrucciones;
			this.instruccion = instruccion;
		}

		public LInst(Inst instruccion) {
			this.instruccion = instruccion;
		}

		public LInst lista_instrucciones() {
			throw new UnsupportedOperationException();
		}

		public Inst instruccion() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class Inst extends Nodo implements ImpresionInterprete {
		protected Exp expresion;
		protected Blq bloque1;
		protected Blq bloque2;
		protected String cadena;
		protected LOptParam parametro;

		public Inst() {
		};

		public Inst(Exp expresion) {
			this.expresion = expresion;
		}

		public Inst(Exp expresion, Blq bloque) {
			this.expresion = expresion;
			this.bloque1 = bloque;
		}

		public Inst(Exp expresion, Blq bloque1, Blq bloque2) {
			this.expresion = expresion;
			this.bloque1 = bloque1;
			this.bloque2 = bloque2;
		}

		public Inst(String cadena, LOptParam parametro) {
			this.cadena = cadena;
			this.parametro = parametro;
		}

		public Inst(Blq bloque) {
			this.bloque1 = bloque;
		}

		public Exp expresion() {
			throw new UnsupportedOperationException();
		}

		public Blq bloque1() {
			throw new UnsupportedOperationException();
		}

		public Blq bloque2() {
			throw new UnsupportedOperationException();
		}

		public String cadena() {
			throw new UnsupportedOperationException();
		}

		public LOptParam parametro() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class Exp extends Nodo implements ImpresionInterprete {
		protected Exp op1;
		protected Exp op2;
		protected String cadena;

		private static int prioridad(Exp e) {
			if (claseDe(e, Asignacion.class)) {
				return 0;
			} else if (claseDe(e, Mayor.class)) {
				return 1;
			} else if (claseDe(e, Mayor_igual.class)) {
				return 1;
			} else if (claseDe(e, Menor.class)) {
				return 1;
			} else if (claseDe(e, Menor_igual.class)) {
				return 1;
			} else if (claseDe(e, Comparacion.class)) {
				return 1;
			} else if (claseDe(e, Distinto.class)) {
				return 1;
			} else if (claseDe(e, Suma.class)) {
				return 2;
			} else if (claseDe(e, Resta.class)) {
				return 2;
			} else if (claseDe(e, And.class)) {
				return 3;
			} else if (claseDe(e, Or.class)) {
				return 3;
			} else if (claseDe(e, Mult.class)) {
				return 4;
			} else if (claseDe(e, Div.class)) {
				return 4;
			} else if (claseDe(e, Mod.class)) {
				return 4;
			} else if (claseDe(e, Not_unario.class)) {
				return 5;
			} else if (claseDe(e, Resta_unario.class)) {
				return 5;
			} else if (claseDe(e, Indexacion.class)) {
				return 6;
			} else if (claseDe(e, Acc_reg.class)) {
				return 6;
			} else if (claseDe(e, Indireccion.class)) {
				return 6;
			}
			return 7;
		}
		
		public abstract int prioridad();;

		protected static String imprimeExpBinInterprete(Exp padre, Exp opnd0, String op, Exp opnd1, int np0, int np1) {
			imprimeOpndInterprete(opnd0, np0);
			System.out.println();
			System.out.println(op + getPosDJ(padre.leeFila(), padre.leeCol()));
			imprimeOpndInterprete(opnd1, np1);
			return "";
		}

		protected static void imprimeOpndInterprete(Exp opnd, int p) {
			if (prioridad(opnd) < p) {
				System.out.println("(");
			}
			opnd.imprime();

			if (prioridad(opnd) < p) {
				System.out.println();
				System.out.print(")");
			}
		}

		protected static String imprimeExpBin(Exp padre,Exp opnd0, String op, Exp opnd1, int np0, int np1) {
			StringBuilder s = new StringBuilder();
			s.append(imprimeOpnd(opnd0, np0));
			s.append("\n");
			s.append(op);
			s.append("$f:" + padre.leeFila() + ",c:" + padre.leeCol() + "$");
			s.append("\n");
			s.append(imprimeOpnd(opnd1, np1));
			return s.toString();
		}

		protected static String imprimeOpnd(Exp opnd, int p) {
			StringBuilder s = new StringBuilder();
			if (prioridad(opnd) < p) {
				s.append("(\n");
			}
			s.append(opnd.toString());

			if (prioridad(opnd) < p) {
				s.append("\n)");
			}
			return s.toString();
		}

		public Exp() {
		};

		public Exp(Exp op1, Exp op2) {
			this.op1 = op1;
			this.op2 = op2;
		}

		public Exp(Exp op) {
			this.op1 = op;
		}

		public Exp(Exp op, String cadena) {
			this.op1 = op;
			this.cadena = cadena;
		}

		public Exp(String cadena) {
			this.cadena = cadena;
		}

		public Exp op1() {
			throw new UnsupportedOperationException();
		}

		public Exp op2() {
			throw new UnsupportedOperationException();
		}

		public String cadena() {
			return cadena;
		}
		
		public abstract void procesa(Procesamiento p);
	}

	public static abstract class LOptParam extends Nodo implements ImpresionInterprete {
		protected LParam lista_parametros;

		public LOptParam() {
		};

		public LOptParam(LParam lista_parametros) {
			this.lista_parametros = lista_parametros;
		}

		public LParam lista_parametros() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);

	}

	public static abstract class LParam extends Nodo implements ImpresionInterprete {
		protected LParam lista_parametros;
		protected Exp expresion;

		public LParam(LParam lista_parametros, Exp expresion) {
			this.lista_parametros = lista_parametros;
			this.expresion = expresion;
		}

		public LParam(Exp expresion) {
			this.expresion = expresion;
		}

		public LParam lista_parametros() {
			throw new UnsupportedOperationException();
		}

		public Exp expresion() {
			throw new UnsupportedOperationException();
		}
		
		public abstract void procesa(Procesamiento p);
	}

	// Clases concretas (Constructoras de AST's)

	public static class Prog_tiny extends ProgT {
		public Prog_tiny(Blq bloque) {
			super(bloque);
			this.bloque = bloque;
		}

		public String toString() {
			return bloque.toString();
		}

		public Blq bloque() {
			return bloque;
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        } 

		@Override
		public void imprime() {
			bloque.imprime();
		}

	}

	public static class Bloque extends Blq {

		public Bloque(LOptDecs lista_opt_declaraciones, LOptInst lista_opt_instrucciones) {
			super(lista_opt_declaraciones, lista_opt_instrucciones);
		}

		public String toString() {
			return "{" + lista_opt_declaraciones + lista_opt_instrucciones + "\n}";
		}

		public LOptDecs lista_opt_declaraciones() {
			return lista_opt_declaraciones;
		}

		public LOptInst lista_opt_instrucciones() {
			return lista_opt_instrucciones;
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

		@Override
		public void imprime() {
			System.out.print("{");
			lista_opt_declaraciones.imprime();
			lista_opt_instrucciones.imprime();
			System.out.println();
			System.out.print("}");
		}	

	}

	public static class Si_lista_opt_decs extends LOptDecs {

		public Si_lista_opt_decs(LDecs lista_declaraciones) {
			super(lista_declaraciones);
		}

		public String toString() {
			return "\n"+lista_declaraciones + "\n&&";
		}

		public LDecs lista_declaraciones() {
			return lista_declaraciones;
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

		@Override
		public void imprime() {
			System.out.println();
			lista_declaraciones.imprime();
			System.out.println();
			System.out.print("&&");
		}
	}

	public static class No_lista_opt_decs extends LOptDecs {
		public No_lista_opt_decs() {
			super();
		}

		public String toString() {
			return "";
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

		@Override
		public void imprime() {

		}
	}

	public static class Muchas_lista_decs extends LDecs {
		public Muchas_lista_decs(LDecs lista_declaraciones, Dec declaracion) {
			super(lista_declaraciones, declaracion);
		}

		public String toString() {
			return lista_declaraciones + "\n;\n" + declaracion;
		}

		public LDecs lista_declaraciones() {
			return lista_declaraciones;
		}

		public Dec declaracion() {
			return declaracion;
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

		@Override
		public void imprime() {
			lista_declaraciones.imprime();
			System.out.println();
			System.out.print(";");
			System.out.println();
			declaracion.imprime();
		}
	}

	public static class Una_lista_dec extends LDecs {
		public Una_lista_dec(Dec declaracion) {
			super(declaracion);
		}

		public String toString() {
			return declaracion.toString();
		}

		public Dec declaracion() {
			return declaracion;
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

		@Override
		public void imprime() {
			declaracion.imprime();
		}
	}

	public static class Dec_var extends Dec {
		public Dec_var(Tipo tipo, String cadena) {
			super(tipo, cadena);
		}

		public String toString() {
			return tipo + "\n" + String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public Tipo tipo() {
			return tipo;
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public void imprime() {
			tipo.imprime();
			System.out.println();
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

	}

	public static class Dec_tipo extends Dec {
		public Dec_tipo(Tipo tipo, String cadena) {
			super(tipo, cadena);
		}

		public String toString() {
			return "<type>\n" + tipo + "\n" + String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public Tipo tipo() {
			return tipo;
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public void imprime() {
			System.out.println("<type>");
			tipo.imprime();
			System.out.println();
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

	}

	public static class Dec_proc extends Dec {
		public Dec_proc(String cadena, LOptParamForm lista_opt_parametros_formales, Blq bloque) {
			super(cadena, lista_opt_parametros_formales, bloque);
		}

		public String toString() {
			return "<proc>\n" + String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol()) + "\n"
					+ lista_opt_parametros_formales + "\n" + bloque;
		}

		public String cadena() {
			return cadena;
		}

		public LOptParamForm lista_opt_parametros_formales() {
			return lista_opt_parametros_formales;
		}

		public Blq bloque() {
			return bloque;
		}

		@Override
		public void imprime() {
			System.out.println("<proc>");
			System.out.println(cadena + getPosDJ(leeFila(), leeCol()));
			lista_opt_parametros_formales.imprime();
			System.out.println();
			bloque.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

	}

	public static class Si_lista_opt_param_form extends LOptParamForm {
		public Si_lista_opt_param_form(LParamForm lista_parametros_formales) {
			super(lista_parametros_formales);
		}

		public String toString() {
			return "(\n" + lista_parametros_formales.toString() + "\n)";
		}

		public LParamForm lista_parametros_formales() {
			return lista_parametros_formales;
		}

		@Override
		public void imprime() {
			System.out.println("(");
			lista_parametros_formales.imprime();
			System.out.println();
			System.out.print(")");
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class No_lista_opt_param_form extends LOptParamForm {
		public No_lista_opt_param_form() {
			super();
		}

		public String toString() {
			return "(\n)";
		}

		@Override
		public void imprime() {
			System.out.println("(");
			System.out.print(")");
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Muchas_lista_param_form extends LParamForm {
		public Muchas_lista_param_form(LParamForm lista_parametros_formales, ParamForm parametro_formal) {
			super(lista_parametros_formales, parametro_formal);
		}

		public String toString() {
			return lista_parametros_formales + "\n,\n" + parametro_formal;
		}

		public LParamForm lista_parametros_formales() {
			return lista_parametros_formales;
		}

		public ParamForm parametro_formal() {
			return parametro_formal;
		}

		@Override
		public void imprime() {
			lista_parametros_formales.imprime();
			System.out.println();
			System.out.println(",");
			parametro_formal.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Una_lista_param_form extends LParamForm {
		public Una_lista_param_form(ParamForm parametro_formal) {
			super(parametro_formal);
		}

		public String toString() {
			return parametro_formal.toString();
		}

		public ParamForm parametro_formal() {
			return parametro_formal;
		}

		@Override
		public void imprime() {
			parametro_formal.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Param_form_ref extends ParamForm {
		public Param_form_ref(Tipo tipo, String cadena) {
			super(tipo, cadena);
		}

		public String toString() {
			return tipo + "\n&\n" + String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public Tipo tipo() {
			return tipo;
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public void imprime() {
			tipo.imprime();
			System.out.println();
			System.out.println("&");
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Param_form extends ParamForm {
		public Param_form(Tipo tipo, String cadena) {
			super(tipo, cadena);
		}

		public String toString() {
			return tipo + "\n" + String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public Tipo tipo() {
			return tipo;
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public void imprime() {
			tipo.imprime();
			System.out.println();
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Tipo_array extends Tipo {
		public Tipo_array(Tipo tipo, String cadena) {
			super(tipo, cadena);
		}

		public String toString() {
			return tipo + "\n[\n" + cadena + "\n]" + String.format("$f:%d,c:%d$", leeFila(), leeCol());
		}

		public Tipo tipo() {
			return tipo;
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public void imprime() {
			tipo.imprime();
			System.out.println();
			System.out.println("[");
			System.out.println(cadena);
			System.out.print("]" + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Tipo_puntero extends Tipo {
		public Tipo_puntero(Tipo tipo) {
			super(tipo);
		}

		public String toString() {
			return "^\n" + tipo.toString();
		}

		public Tipo tipo() {
			return tipo;
		}

		@Override
		public void imprime() {
			System.out.println("^");
			tipo.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Int_t extends Tipo {
		public Int_t() {
			super();
		}

		public String toString() {
			return "<int>";
		}

		@Override
		public void imprime() {
			System.out.print("<int>");
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Real_t extends Tipo {
		public Real_t() {
			super();
		}

		public String toString() {
			return "<real>";
		}

		@Override
		public void imprime() {
			System.out.print("<real>");
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Bool_t extends Tipo {
		public Bool_t() {
			super();
		}

		public String toString() {
			return "<bool>";
		}

		@Override
		public void imprime() {
			System.out.print("<bool>");
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class String_t extends Tipo {
		public String_t() {
			super();
		}

		public String toString() {
			return "<string>";
		}

		@Override
		public void imprime() {
			System.out.print("<string>");
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Tipo_registro extends Tipo {
		public Tipo_registro(LParamReg lista_parametros_registro) {
			super(lista_parametros_registro);
		}

		public String toString() {
			return "<struct>\n" + "{\n" + lista_parametros_registro + "\n}";
		}

		public LParamReg lista_parametros_registro() {
			return lista_parametros_registro;
		}

		@Override
		public void imprime() {
			System.out.println("<struct>");
			System.out.println("{");
			lista_parametros_registro.imprime();
			System.out.println();
			System.out.print("}");
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Tipo_definido extends Tipo {
		public Tipo_definido(String cadena) {
			super(cadena);
			tipo_definido = true;
		}

		public String toString() {
			return String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public void imprime() {
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Muchas_lista_param_reg extends LParamReg {
		public Muchas_lista_param_reg(LParamReg lista_parametros_registro, ParamReg parametro_registro) {
			super(lista_parametros_registro, parametro_registro);
		}

		public String toString() {
			return lista_parametros_registro + "\n,\n" + parametro_registro;
		}

		public LParamReg lista_parametros_registro() {
			return lista_parametros_registro;
		}

		public ParamReg parametro_registro() {
			return parametro_registro;
		}

		@Override
		public void imprime() {
			lista_parametros_registro.imprime();
			System.out.println();
			System.out.println(",");
			parametro_registro.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Una_lista_param_reg extends LParamReg {
		public Una_lista_param_reg(ParamReg parametro_registro) {
			super(parametro_registro);
		}

		public String toString() {
			return parametro_registro.toString();
		}

		public ParamReg parametro_registro() {
			return parametro_registro;
		}

		@Override
		public void imprime() {
			parametro_registro.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Param_reg extends ParamReg {
		public Param_reg(Tipo tipo, String cadena) {
			super(tipo, cadena);
		}

		public String toString() {
			return tipo + "\n" + String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public Tipo tipo() {
			return tipo;
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public void imprime() {
			tipo.imprime();
			System.out.println();
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Si_lista_opt_inst extends LOptInst {
		public Si_lista_opt_inst(LInst lista_instrucciones) {
			super(lista_instrucciones);
		}

		public String toString() {
			return "\n"+lista_instrucciones.toString();
		}

		public LInst lista_instrucciones() {
			return lista_instrucciones;
		}

		@Override
		public void imprime() {
			System.out.println();
			lista_instrucciones.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class No_lista_opt_inst extends LOptInst {
		public No_lista_opt_inst() {
			super();
		}

		public String toString() {
			return "";
		}

		@Override
		public void imprime() {
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Muchas_lista_inst extends LInst {
		public Muchas_lista_inst(LInst lista_instrucciones, Inst instruccion) {
			super(lista_instrucciones, instruccion);
		}

		public String toString() {
			return lista_instrucciones + "\n;\n" + instruccion;
		}

		public LInst lista_instrucciones() {
			return lista_instrucciones;
		}

		public Inst instruccion() {
			return instruccion;
		}

		@Override
		public void imprime() {
			lista_instrucciones.imprime();
			System.out.println();
			System.out.println(";");
			instruccion.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Una_lista_inst extends LInst {
		public Una_lista_inst(Inst instruccion) {
			super(instruccion);
		}

		public String toString() {
			return instruccion.toString();
		}

		public Inst instruccion() {
			return instruccion;
		}

		@Override
		public void imprime() {
			instruccion.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_eval extends Inst {
		public Inst_eval(Exp expresion) {
			super(expresion);
		}

		public String toString() {
			return "@\n" + expresion;
		}

		public Exp expresion() {
			return expresion;
		}

		@Override
		public void imprime() {
			System.out.println("@");
			expresion.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}
	
	public static  class Inst_if extends Inst{
		private Blq bloque;
		
		public Inst_if(Exp expresion, Blq bloque) {
			super(expresion, bloque);
			this.bloque = bloque;
		}
		
		public String toString() {
			return "<if>\n" + expresion + "\n" + bloque;
		}
		
		public Exp expresion() {
			return expresion;
		}

		public Blq bloque1() {
			return bloque1;
		}
		
		@Override
		public void imprime() {
			System.out.println("<if>");
			expresion.imprime();
			System.out.println();
			bloque.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
		
	}

	public static class Inst_if_else extends Inst {
		private Blq bloque, bloque2;

		public Inst_if_else(Exp expresion, Blq bloque, Blq bloque2) {
			super(expresion, bloque, bloque2);
			this.bloque = bloque;
			this.bloque2 = bloque2;
		}

		public String toString() {
			return "<if>\n" + expresion + "\n" + bloque + "\n<else>\n" + bloque2;
		}

		public Exp expresion() {
			return expresion;
		}

		public Blq bloque1() {
			return bloque1;
		}

		public Blq bloque2() {
			return bloque2;
		}

		@Override
		public void imprime() {
			System.out.println("<if>");
			expresion.imprime();
			System.out.println();
			bloque.imprime();
			System.out.println();
			System.out.println("<else>");
			bloque2.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_while extends Inst {
		Blq bloque;

		public Inst_while(Exp expresion, Blq bloque) {
			super(expresion, bloque);
			this.bloque = bloque;
		}

		public String toString() {
			return "<while>\n" + expresion + "\n" + bloque;
		}

		public Exp expresion() {
			return expresion;
		}

		public Blq bloque() {
			return bloque;
		}

		@Override
		public void imprime() {
			System.out.println("<while>");
			expresion.imprime();
			System.out.println();
			bloque.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_read extends Inst {
		public Inst_read(Exp expresion) {
			super(expresion);
		}

		public String toString() {
			return "<read>\n" + expresion;
		}

		public Exp expresion() {
			return expresion;
		}

		@Override
		public void imprime() {
			System.out.println("<read>");
			expresion.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_write extends Inst {
		public Inst_write(Exp expresion) {
			super(expresion);
		}

		public String toString() {
			return "<write>\n" + expresion;
		}

		public Exp expresion() {
			return expresion;
		}

		@Override
		public void imprime() {
			System.out.println("<write>");
			expresion.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_nl extends Inst {
		public Inst_nl() {
			super();
		}

		public String toString() {
			return "<nl>";
		}

		@Override
		public void imprime() {
			System.out.print("<nl>");
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_new extends Inst {
		public Inst_new(Exp expresion) {
			super(expresion);
		}

		public String toString() {
			return "<new>\n" + expresion;
		}

		public Exp expresion() {
			return expresion;
		}

		@Override
		public void imprime() {
			System.out.println("<new>");
			expresion.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_delete extends Inst {
		public Inst_delete(Exp expresion) {
			super(expresion);
		}

		public String toString() {
			return "<delete>\n" + expresion;
		}

		public Exp expresion() {
			return expresion;
		}

		@Override
		public void imprime() {
			System.out.println("<delete>");
			expresion.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_call extends Inst {
		LOptParam lista_opt_parametros;

		public Inst_call(String cadena, LOptParam lista_opt_parametros) {
			super(cadena, lista_opt_parametros);
			this.lista_opt_parametros = lista_opt_parametros;
		}

		public String toString() {
			return "<call>\n" + String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol()) + "\n"
					+ lista_opt_parametros;
		}

		public String cadena() {
			return cadena;
		}

		public LOptParam lista_opt_parametros() {
			return lista_opt_parametros;
		}

		@Override
		public void imprime() {
			System.out.println("<call>");
			System.out.println(cadena + getPosDJ(leeFila(), leeCol()));
			lista_opt_parametros.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Inst_comp extends Inst {
		Blq bloque;

		public Inst_comp(Blq bloque) {
			super(bloque);
			this.bloque = bloque;
		}

		public String toString() {
			return bloque.toString();
		}

		public Blq bloque() {
			return bloque;
		}

		@Override
		public void imprime() {
			bloque.imprime();
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Asignacion extends Exp {
		public Asignacion(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "=", op2, 1, 0);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 0;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "=", op2, 1, 0));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Mayor extends Exp {
		public Mayor(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, ">", op2, 1, 2);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, ">", op2, 1, 2));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Mayor_igual extends Exp {
		public Mayor_igual(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, ">=", op2, 1, 2);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, ">=", op2, 1, 2));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Menor extends Exp {
		public Menor(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "<", op2, 1, 2);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "<", op2, 1, 2));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Menor_igual extends Exp {
		public Menor_igual(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "<=", op2, 1, 2);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "<=", op2, 1, 2));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Comparacion extends Exp {
		public Comparacion(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "==", op2, 1, 2);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "==", op2, 1, 2));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Distinto extends Exp {
		public Distinto(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "!=", op2, 1, 2);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "!=", op2, 1, 2));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Suma extends Exp {
		public Suma(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "+", op2, 2, 3);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 2;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "+", op2, 2, 3));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Resta extends Exp {
		public Resta(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "-", op2, 3, 3);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 2;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "-", op2, 3, 3));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class And extends Exp {
		public And(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "<and>", op2, 4, 3);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 3;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "<and>", op2, 4, 3));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Or extends Exp {
		public Or(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "<or>", op2, 4, 4);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 3;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "<or>", op2, 4, 4));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Mult extends Exp {
		public Mult(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "*", op2, 4, 5);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 4;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "*", op2, 4, 5));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Div extends Exp {
		public Div(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "/", op2, 4, 5);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}
		
		@Override
		public int prioridad() {
			return 4;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "/", op2, 4, 5));

		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Mod extends Exp {
		public Mod(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeExpBin(this, op1, "%", op2, 4, 5);
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 4;
		}

		@Override
		public void imprime() {
			System.out.print(imprimeExpBinInterprete(this, op1, "%", op2, 4, 5));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Not_unario extends Exp {
		public Not_unario(Exp op1) {
			super(op1);
		}

		public String toString() {
			return "<not>" + String.format("$f:%d,c:%d$", leeFila(), leeCol()) + "\n" + imprimeOpnd(op1, 5);
		}

		public Exp op1() {
			return op1;
		}

		@Override
		public int prioridad() {
			return 5;
		}

		@Override
		public void imprime() {
			System.out.println("<not>" + getPosDJ(leeFila(), leeCol()));
			imprimeOpndInterprete(op1, 5);

		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Resta_unario extends Exp {
		public Resta_unario(Exp op1) {
			super(op1);
		}

		public String toString() {
			return "-" + String.format("$f:%d,c:%d$", leeFila(), leeCol()) + "\n" + imprimeOpnd(op1, 5);
		}

		public Exp op1() {
			return op1;
		}

		@Override
		public int prioridad() {
			return 5;
		}

		@Override
		public void imprime() {
			System.out.println("-" + getPosDJ(leeFila(), leeCol()));
			imprimeOpndInterprete(op1, 5);
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }

	}

	public static class Indexacion extends Exp {
		public Indexacion(Exp op1, Exp op2) {
			super(op1, op2);
		}

		public String toString() {
			return imprimeOpnd(op1, 6) + "\n" + String.format("%s$f:%d,c:%d$", "[", leeFila(), leeCol()) + "\n"
					+ imprimeOpnd(op2, 0) + "\n]";
		}

		public Exp op1() {
			return op1;
		}

		public Exp op2() {
			return op2;
		}

		@Override
		public int prioridad() {
			return 6;
		}

		@Override
		public void imprime() {
			imprimeOpndInterprete(op1, 6);
			System.out.println();
			System.out.println("[" + getPosDJ(leeFila(), leeCol()));
			imprimeOpndInterprete(op2, 0);
			System.out.println();
			System.out.print("]");
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Acc_reg extends Exp {
		public Acc_reg(Exp op1, String cadena) {
			super(op1, cadena);
		}

		public String toString() {
			return imprimeOpnd(op1, 6) + "\n.\n" + String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public Exp op1() {
			return op1;
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public int prioridad() {
			return 6;
		}

		@Override
		public void imprime() {
			imprimeOpndInterprete(op1, 6);
			System.out.println();
			System.out.println(".");
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Indireccion extends Exp {
		public Indireccion(Exp op1) {
			super(op1);
		}

		public String toString() {
			return imprimeOpnd(op1, 6) + "\n" + String.format("^$f:%d,c:%d$", leeFila(), leeCol());
		}

		public Exp op1() {
			return op1;
		}

		@Override
		public int prioridad() {
			return 6;
		}

		@Override
		public void imprime() {
			imprimeOpndInterprete(op1, 6);
			System.out.println();
			System.out.print("^" + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Lit_ent extends Exp {
		public Lit_ent(String cadena) {
			super(cadena);
		}

		public String toString() {
			return String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public int prioridad() {
			return 7;
		}

		@Override
		public void imprime() {
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Lit_real extends Exp {
		public Lit_real(String cadena) {
			super(cadena);
		}

		public String toString() {
			return String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public int prioridad() {
			return 7;
		}

		@Override
		public void imprime() {
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class True_e extends Exp {
		public True_e() {
			super();
		}

		public String toString() {
			return String.format("%s$f:%d,c:%d$", "<true>", leeFila(), leeCol());
		}

		@Override
		public int prioridad() {
			return 7;
		}

		@Override
		public void imprime() {
			System.out.print("<true>" + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class False_e extends Exp {
		public False_e() {
			super();
		}

		public String toString() {
			return String.format("%s$f:%d,c:%d$", "<false>", leeFila(), leeCol());
		}

		@Override
		public int prioridad() {
			return 7;
		}

		@Override
		public void imprime() {
			System.out.print("<false>" + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Null_e extends Exp {
		public Null_e() {
			super();
		}

		public String toString() {
			return String.format("%s$f:%d,c:%d$", "<null>", leeFila(), leeCol());
		}

		@Override
		public int prioridad() {
			return 7;
		}

		@Override
		public void imprime() {
			System.out.print("<null>" + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Cadena extends Exp {
		public Cadena(String cadena) {
			super(cadena);
		}

		public String toString() {
			return String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public int prioridad() {
			return 7;
		}

		@Override
		public void imprime() {
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Iden extends Exp {
		public Iden(String cadena) {
			super(cadena);
		}

		public String toString() {
			return String.format("%s$f:%d,c:%d$", cadena, leeFila(), leeCol());
		}

		public String cadena() {
			return cadena;
		}

		@Override
		public int prioridad() {
			return 7;
		}

		@Override
		public void imprime() {
			System.out.print(cadena + getPosDJ(leeFila(), leeCol()));
		}

		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Si_lista_opt_param extends LOptParam {
		public Si_lista_opt_param(LParam lista_parametros) {
			super(lista_parametros);
		}

		public String toString() {
			return "(\n" + lista_parametros.toString() + "\n)";
		}

		public LParam lista_parametros() {
			return lista_parametros;
		}

		@Override
		public void imprime() {
			System.out.println("(");
			lista_parametros.imprime();
			System.out.println();
			System.out.print(")");
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class No_lista_opt_param extends LOptParam {
		public No_lista_opt_param() {
			super();
		}

		public String toString() {
			return "(\n)";
		}

		@Override
		public void imprime() {
			System.out.println("(");
			System.out.print(")");
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Muchas_lista_param extends LParam {
		public Muchas_lista_param(LParam lista_parametros, Exp expresion) {
			super(lista_parametros, expresion);
		}

		public String toString() {
			return lista_parametros + "\n,\n" + expresion;
		}

		public LParam lista_parametros() {
			return lista_parametros;
		}

		public Exp expresion() {
			return expresion;
		}

		@Override
		public void imprime() {
			lista_parametros.imprime();
			System.out.println();
			System.out.println(",");
			expresion.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	public static class Una_lista_param extends LParam {
		public Una_lista_param(Exp expresion) {
			super(expresion);
		}

		public String toString() {
			return expresion.toString();
		}

		public Exp expresion() {
			return expresion;
		}

		@Override
		public void imprime() {
			expresion.imprime();
		}
		
		public void procesa(Procesamiento p) {
            p.procesa(this);
        }
	}

	// Funciones para la impresion de los operandos

	private static boolean claseDe(Object o, Class c) {
		return o.getClass() == c;
	}

	// Funciones de llama a constructores
	public ProgT prog_tiny(Blq bloque) {
		return new Prog_tiny(bloque);
	}

	public Blq bloque(LOptDecs lista_opt_declaraciones, LOptInst lista_opt_instrucciones) {
		return new Bloque(lista_opt_declaraciones, lista_opt_instrucciones);
	}

	public LOptDecs si_lista_opt_decs(LDecs lista_declaraciones) {
		return new Si_lista_opt_decs(lista_declaraciones);
	}

	public LOptDecs no_lista_opt_decs() {
		return new No_lista_opt_decs();
	}

	public LDecs muchas_lista_decs(LDecs lista_declaraciones, Dec declaracion) {
		return new Muchas_lista_decs(lista_declaraciones, declaracion);
	}

	public LDecs una_lista_dec(Dec declaracion) {
		return new Una_lista_dec(declaracion);
	}

	public Dec dec_var(Tipo tipo, String cadena) {
		return new Dec_var(tipo, cadena);
	}

	public Dec dec_tipo(Tipo tipo, String cadena) {
		return new Dec_tipo(tipo, cadena);
	}

	public Dec dec_proc(String cadena, LOptParamForm lista_opt_parametros_formales, Blq bloque) {
		return new Dec_proc(cadena, lista_opt_parametros_formales, bloque);
	}

	public LOptParamForm si_lista_opt_param_form(LParamForm lista_parametros_formales) {
		return new Si_lista_opt_param_form(lista_parametros_formales);
	}

	public LOptParamForm no_lista_opt_param_form() {
		return new No_lista_opt_param_form();
	}

	public LParamForm muchas_lista_param_form(LParamForm lista_parametros_formales, ParamForm parametro_formal) {
		return new Muchas_lista_param_form(lista_parametros_formales, parametro_formal);
	}

	public LParamForm una_lista_param_form(ParamForm parametro_formal) {
		return new Una_lista_param_form(parametro_formal);
	}

	public ParamForm param_form_ref(Tipo tipo, String cadena) {
		return new Param_form_ref(tipo, cadena);
	}

	public ParamForm param_form(Tipo tipo, String cadena) {
		return new Param_form(tipo, cadena);
	}

	public Tipo tipo_array(Tipo tipo, String cadena) {
		return new Tipo_array(tipo, cadena);
	}

	public Tipo tipo_puntero(Tipo tipo) {
		return new Tipo_puntero(tipo);
	}

	public Tipo int_t() {
		return new Int_t();
	}

	public Tipo real_t() {
		return new Real_t();
	}

	public Tipo bool_t() {
		return new Bool_t();
	}

	public Tipo string_t() {
		return new String_t();
	}

	public Tipo tipo_registro(LParamReg lista_parametros_registro) {
		return new Tipo_registro(lista_parametros_registro);
	}

	public Tipo tipo_definido(String cadena) {
		return new Tipo_definido(cadena);
	}

	public LParamReg muchas_lista_param_reg(LParamReg lista_parametros_registro, ParamReg parametro_registro) {
		return new Muchas_lista_param_reg(lista_parametros_registro, parametro_registro);
	}

	public LParamReg una_lista_param_reg(ParamReg parametro_registro) {
		return new Una_lista_param_reg(parametro_registro);
	}

	public ParamReg param_reg(Tipo tipo, String cadena) {
		return new Param_reg(tipo, cadena);
	}

	public LOptInst si_lista_opt_inst(LInst lista_instrucciones) {
		return new Si_lista_opt_inst(lista_instrucciones);
	}

	public LOptInst no_lista_opt_inst() {
		return new No_lista_opt_inst();
	}

	public LInst muchas_lista_inst(LInst lista_instrucciones, Inst instruccion) {
		return new Muchas_lista_inst(lista_instrucciones, instruccion);
	}

	public LInst una_lista_inst(Inst instruccion) {
		return new Una_lista_inst(instruccion);
	}

	public Inst inst_eval(Exp expresion) {
		return new Inst_eval(expresion);
	}

	public Inst inst_if_else(Exp expresion, Blq bloque1, Blq bloque2) {
		return new Inst_if_else(expresion, bloque1, bloque2);
	}

	public Inst inst_if(Exp expresion, Blq bloque) {
		return new Inst_if(expresion, bloque);
	}

	public Inst inst_while(Exp expresion, Blq bloque) {
		return new Inst_while(expresion, bloque);
	}

	public Inst inst_read(Exp expresion) {
		return new Inst_read(expresion);
	}

	public Inst inst_write(Exp expresion) {
		return new Inst_write(expresion);
	}

	public Inst inst_nl() {
		return new Inst_nl();
	}

	public Inst inst_new(Exp expresion) {
		return new Inst_new(expresion);
	}

	public Inst inst_delete(Exp expresion) {
		return new Inst_delete(expresion);
	}

	public Inst inst_call(String cadena, LOptParam lista_opt_parametros) {
		return new Inst_call(cadena, lista_opt_parametros);
	}

	public Inst inst_comp(Blq bloque) {
		return new Inst_comp(bloque);
	}

	public Exp mayor(Exp op1, Exp op2) {
		return new Mayor(op1, op2);
	}

	public Exp mayor_igual(Exp op1, Exp op2) {
		return new Mayor_igual(op1, op2);
	}

	public Exp menor(Exp op1, Exp op2) {
		return new Menor(op1, op2);
	}

	public Exp menor_igual(Exp op1, Exp op2) {
		return new Menor_igual(op1, op2);
	}

	public Exp asignacion(Exp op1, Exp op2) {
		return new Asignacion(op1, op2);
	}

	public Exp comparacion(Exp op1, Exp op2) {
		return new Comparacion(op1, op2);
	}

	public Exp distinto(Exp op1, Exp op2) {
		return new Distinto(op1, op2);
	}

	public Exp suma(Exp op1, Exp op2) {
		return new Suma(op1, op2);
	}

	public Exp resta(Exp op1, Exp op2) {
		return new Resta(op1, op2);
	}

	public Exp and(Exp op1, Exp op2) {
		return new And(op1, op2);
	}

	public Exp or(Exp op1, Exp op2) {
		return new Or(op1, op2);
	}

	public Exp mult(Exp op1, Exp op2) {
		return new Mult(op1, op2);
	}

	public Exp div(Exp op1, Exp op2) {
		return new Div(op1, op2);
	}

	public Exp mod(Exp op1, Exp op2) {
		return new Mod(op1, op2);
	}

	public Exp not_unario(Exp op1) {
		return new Not_unario(op1);
	}

	public Exp resta_unario(Exp op1) {
		return new Resta_unario(op1);
	}

	public Exp indexacion(Exp op1, Exp op2) {
		return new Indexacion(op1, op2);
	}

	public Exp acc_reg(Exp op1, String cadena) {
		return new Acc_reg(op1, cadena);
	}

	public Exp indireccion(Exp op1) {
		return new Indireccion(op1);
	}

	public Exp lit_ent(String cadena) {
		return new Lit_ent(cadena);
	}

	public Exp lit_real(String cadena) {
		return new Lit_real(cadena);
	}

	public Exp true_e() {
		return new True_e();
	}

	public Exp false_e() {
		return new False_e();
	}

	public Exp null_e() {
		return new Null_e();
	}

	public Exp cadena(String cadena) {
		return new Cadena(cadena);
	}

	public Exp iden(String cadena) {
		return new Iden(cadena);
	}

	public LOptParam si_lista_opt_param(LParam lista_parametros) {
		return new Si_lista_opt_param(lista_parametros);
	}

	public LOptParam no_lista_opt_param() {
		return new No_lista_opt_param();
	}

	public LParam muchas_lista_param(LParam lista_parametros, Exp expresion) {
		return new Muchas_lista_param(lista_parametros, expresion);
	}

	public LParam una_lista_param(Exp expresion) {
		return new Una_lista_param(expresion);
	}
}
