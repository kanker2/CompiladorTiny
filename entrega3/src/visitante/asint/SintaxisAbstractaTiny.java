package visitante.asint;

public class SintaxisAbstractaTiny {
	public SintaxisAbstractaTiny() {
	};

	public static abstract class Nodo {
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
		
		public abstract void procesa(Procesamiento p);
	}

	// Clases abstractas (Generos)
	public static abstract class ProgT extends Nodo {
		public Blq bloque;

		public ProgT(Blq bloque) {
			this.bloque = bloque;
		}
		
		public void procesa(Procesamiento p)
		{
			p.procesa(this);
		}
		
		
	}

	public static abstract class Blq extends Nodo{
		public LOptDecs lista_opt_declaraciones;
		public LOptInst lista_opt_instrucciones;

		public Blq(LOptDecs lista_opt_declaraciones, LOptInst lista_opt_instrucciones) {
			this.lista_opt_declaraciones = lista_opt_declaraciones;
			this.lista_opt_instrucciones = lista_opt_instrucciones;
		}
		
		
	}

	public static abstract class LOptDecs extends Nodo {
		public LDecs lista_declaraciones;

		public LOptDecs(LDecs lista_declaraciones) {
			this.lista_declaraciones = lista_declaraciones;
		}

		public LOptDecs() {
		};
	}

	public static abstract class LDecs extends Nodo{
		public LDecs lista_declaraciones;
		public Dec declaracion;

		public LDecs(LDecs lista_declaraciones, Dec declaracion) {
			this.lista_declaraciones = lista_declaraciones;
			this.declaracion = declaracion;
		}

		public LDecs(Dec declaracion) {
			this.declaracion = declaracion;
		}
	}

	public static abstract class Dec extends Nodo {
		public Tipo tipo;
		public String cadena;
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
		
		 
	}

	public static abstract class LOptParamForm extends Nodo{
		protected LParamForm lista_parametros_formales;

		public LOptParamForm(LParamForm lista_parametros_formales) {
			this.lista_parametros_formales = lista_parametros_formales;
		}

		public LOptParamForm() {
		};
	}

	public static abstract class LParamForm extends Nodo{
		protected LParamForm lista_parametros_formales;
		protected ParamForm parametro_formal;

		public LParamForm(LParamForm lista_parametros_formales, ParamForm parametro_formal) {
			this.lista_parametros_formales = lista_parametros_formales;
			this.parametro_formal = parametro_formal;
		}

		public LParamForm(ParamForm parametro_formal) {
			this.parametro_formal = parametro_formal;
		}
	}

	public static abstract class ParamForm extends Nodo{
		protected Tipo tipo;
		protected String cadena;

		public ParamForm() {
		};

		public ParamForm(Tipo tipo, String cadena) {
			this.tipo = tipo;
			this.cadena = cadena;
		}
	}

	public static abstract class Tipo extends Nodo {
		protected Tipo tipo;
		protected String cadena;
		protected LParamReg lista_parametros_registro;

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
	}

	public static abstract class LParamReg extends Nodo{
		protected LParamReg lista_parametros_registro;
		protected ParamReg parametro_registro;

		public LParamReg(LParamReg lista_parametros_registro, ParamReg parametro_registro) {
			this.lista_parametros_registro = lista_parametros_registro;
			this.parametro_registro = parametro_registro;
		}

		public LParamReg(ParamReg parametro_registro) {
			this.parametro_registro = parametro_registro;
		}
	}

	public static abstract class ParamReg extends Nodo{
		protected Tipo tipo;
		protected String cadena;

		public ParamReg(Tipo tipo, String cadena) {
			this.tipo = tipo;
			this.cadena = cadena;
		}
	}

	public static abstract class LOptInst extends Nodo {
		public LInst lista_instrucciones;

		public LOptInst() {
		};

		public LOptInst(LInst lista_instrucciones) {
			this.lista_instrucciones = lista_instrucciones;
		}

	}

	public static abstract class LInst extends Nodo {
		public LInst lista_instrucciones;
		public Inst instruccion;

		public LInst(LInst lista_instrucciones, Inst instruccion) {
			this.lista_instrucciones = lista_instrucciones;
			this.instruccion = instruccion;
		}

		public LInst(Inst instruccion) {
			this.instruccion = instruccion;
		}
	}

	public static abstract class Inst extends Nodo {
		public Exp expresion;
		public Blq bloque1;
		public Blq bloque2;
		public String cadena;
		public LOptParam parametro;

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
	}

	public static abstract class Exp extends Nodo {
		protected Exp op1;
		protected Exp op2;
		protected String cadena;

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
		
	      /* public String valor() {throw new UnsupportedOperationException();}
	       public String iden() {throw new UnsupportedOperationException();}
	       public Exp opnd0() {throw new UnsupportedOperationException();}
	       public Exp opnd1() {throw new UnsupportedOperationException();}*/
	       
	       
	       public abstract int prioridad();
		//////¿?¿?¿?¿?¿?¿¿¿?¿
		/*
       */
	}

	public static abstract class LOptParam extends Nodo {
		protected LParam lista_parametros;

		public LOptParam() {
		};

		public LOptParam(LParam lista_parametros) {
			this.lista_parametros = lista_parametros;
		}

	}

	public static abstract class LParam extends Nodo {
		protected LParam lista_parametros;
		protected Exp expresion;

		public LParam(LParam lista_parametros, Exp expresion) {
			this.lista_parametros = lista_parametros;
			this.expresion = expresion;
		}

		public LParam(Exp expresion) {
			this.expresion = expresion;
		}
	}

	// Clases concretas (Constructoras de AST's)

	public static class Prog_tiny extends ProgT {

		public Prog_tiny(Blq bloque) {
			super(bloque);
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);
		}

	}

	public static class Bloque extends Blq {

		public Bloque(LOptDecs lista_opt_declaraciones, LOptInst lista_opt_instrucciones) {
			super(lista_opt_declaraciones, lista_opt_instrucciones);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
			
		}

	}

	public static class Si_lista_opt_decs extends LOptDecs {

		public Si_lista_opt_decs(LDecs lista_declaraciones) {
			super(lista_declaraciones);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class No_lista_opt_decs extends LOptDecs {
		public No_lista_opt_decs() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Muchas_lista_decs extends LDecs {
		public Muchas_lista_decs(LDecs lista_declaraciones, Dec declaracion) {
			super(lista_declaraciones, declaracion);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Una_lista_decs extends LDecs {
		public Una_lista_decs(Dec declaracion) {
			super(declaracion);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Dec_var extends Dec {
		public Dec_var(Tipo tipo, String cadena) {
			super(tipo, cadena);
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);
			
		}
	}

	public static class Dec_tipo extends Dec {
		public Dec_tipo(Tipo tipo, String cadena) {
			super(tipo, cadena);
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);
			
		}
	}

	public static class Dec_proc extends Dec {
		
		public  LOptParamForm lopf;
		public Blq b;
		
		public Dec_proc(String cadena, LOptParamForm lista_opt_parametros_formales, Blq bloque) {
			super(cadena, lista_opt_parametros_formales, bloque);
			lopf = lista_opt_parametros_formales;
			b = bloque;
		}
		
		

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);
		}
	}

	public static class Si_lista_opt_param_form extends LOptParamForm {
		public LParamForm lpf;
		public Si_lista_opt_param_form(LParamForm lista_parametros_formales) {
			super(lista_parametros_formales);
			lpf =  lista_parametros_formales;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class No_lista_opt_param_form extends LOptParamForm {
		public No_lista_opt_param_form() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Muchas_lista_param_form extends LParamForm {
		public LParamForm lpf;
		public ParamForm pf;
		public Muchas_lista_param_form(LParamForm lista_parametros_formales, ParamForm parametro_formal) {
			super(lista_parametros_formales, parametro_formal);
			lpf = lista_parametros_formales;
			pf = parametro_formal;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Una_lista_param_form extends LParamForm {
		public ParamForm pf;
		public Una_lista_param_form(ParamForm parametro_formal) {
			super(parametro_formal);
			pf = parametro_formal;

		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}

	}

	public static class Param_form_ref extends ParamForm {
		public Tipo t;
		public Param_form_ref(Tipo tipo, String cadena) {
			super(tipo, cadena);
			t = tipo;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Param_form extends ParamForm {
		public Tipo t;
		public Param_form(Tipo tipo, String cadena) {
			super(tipo, cadena);
			t = tipo;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Tipo_array extends Tipo {
		public Tipo t;
		public String cad;
		public Tipo_array(Tipo tipo, String cadena) {
			super(tipo, cadena);
			t = tipo;
			cad = cadena;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Tipo_puntero extends Tipo {
		public Tipo t;

		public Tipo_puntero(Tipo tipo) {
			super(tipo);
			t = tipo;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Int_t extends Tipo {
		public Int_t() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Real_t extends Tipo {
		public Real_t() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Bool_t extends Tipo {
		public Bool_t() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class String_t extends Tipo {
		public String_t() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Tipo_registro extends Tipo {
		public LParamReg lpr;
		public Tipo_registro(LParamReg lista_parametros_registro) {
			super(lista_parametros_registro);
			lpr = lista_parametros_registro;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Tipo_definido extends Tipo {
		public String cad;
		public Tipo_definido(String cadena) {
			super(cadena);
			cad = cadena;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Muchas_lista_param_reg extends LParamReg {
		public LParamReg lpr;
		public ParamReg pr;
		public Muchas_lista_param_reg(LParamReg lista_parametros_registro, ParamReg parametro_registro) {
			super(lista_parametros_registro, parametro_registro);
			lpr = lista_parametros_registro;
			pr = parametro_registro;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Una_lista_param_reg extends LParamReg {
		public ParamReg pr;
		public Una_lista_param_reg(ParamReg parametro_registro) {
			super(parametro_registro);
			pr = parametro_registro;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Param_reg extends ParamReg {
		public Tipo t;
		public String cad;
		public Param_reg(Tipo tipo, String cadena) {
			super(tipo, cadena);
			t = tipo;
			cad = cadena;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Si_lista_opt_inst extends LOptInst {
		public LInst li;
		public Si_lista_opt_inst(LInst lista_instrucciones) {
			super(lista_instrucciones);
			li = lista_instrucciones;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class No_lista_opt_inst extends LOptInst {
		public No_lista_opt_inst() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Muchas_lista_inst extends LInst {
		public LInst li;
		public Inst i;

		public Muchas_lista_inst(LInst lista_instrucciones, Inst instruccion) {
			super(lista_instrucciones, instruccion);
			li = lista_instrucciones;
			i = instruccion;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Una_lista_inst extends LInst {
		public Inst i;

		public Una_lista_inst(Inst instruccion) {
			super(instruccion);
			i = instruccion;

		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_eval extends Inst {
		public Exp e;
		public Inst_eval(Exp expresion) {
			super(expresion);
			e = expresion;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_if extends Inst {
		public Exp e;
		public Blq b;
		public Inst_if(Exp expresion, Blq bloque) {
			super(expresion, bloque);
			e = expresion;
			b = bloque;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_if_else extends Inst {
		public Exp e;
		public Blq b1, b2;
		public Inst_if_else(Exp expresion, Blq bloque, Blq bloque2) {
			super(expresion, bloque, bloque2);
			e = expresion;
			b1 = bloque;
			b2 = bloque2;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
			
		}
	}

	public static class Inst_while extends Inst {
		public Exp e;
		public Blq b;
		public Inst_while(Exp expresion, Blq bloque) {
			super(expresion, bloque);
			e = expresion;
			b = bloque;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_read extends Inst {
		public Exp e;

		public Inst_read(Exp expresion) {
			super(expresion);
			e = expresion;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_write extends Inst {
		public Exp e;

		public Inst_write(Exp expresion) {
			super(expresion);
			e = expresion;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_nl extends Inst {

		public Inst_nl() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_new extends Inst {
		public Exp e;

		public Inst_new(Exp expresion) {
			super(expresion);
			e = expresion;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_delete extends Inst {
		public Exp e;

		public Inst_delete(Exp expresion) {
			super(expresion);
			e = expresion;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_call extends Inst {
		public String cad;
		public LOptParam lop;
		public Inst_call(String cadena, LOptParam lista_opt_parametros) {
			super(cadena, lista_opt_parametros);
			cad = cadena;
			lop = lista_opt_parametros;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Inst_comp extends Inst {
		public Blq b;
		public Inst_comp(Blq bloque) {
			super(bloque);
			b = bloque;
		}
		

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}
	
	public static abstract class ExpBin extends Exp {
        protected Exp opnd0;
        protected Exp opnd1;
        public ExpBin(Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }
        public Exp opnd0() {return opnd0;}
        public Exp opnd1() {return opnd1;}

    }

	public static class Asignacion extends ExpBin {
		public Exp op1, op2;
		public Asignacion(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 0;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);
			
		}
	}

	public static class Mayor extends ExpBin {
		public Exp op1, op2;
		public Mayor(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);
		}
	}

	public static class Mayor_igual extends ExpBin {
		public Exp op1, op2;
		public Mayor_igual(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Menor extends ExpBin {
		public Exp op1, op2;
		public Menor(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Menor_igual extends ExpBin {
		public Exp op1, op2;
		public Menor_igual(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Comparacion extends ExpBin {
		public Exp op1, op2;
		public Comparacion(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Distinto extends ExpBin {
		public Exp op1, op2;
		public Distinto(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 1;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Suma extends ExpBin {
		public Exp op1, op2;
		public Suma(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 2;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Resta extends ExpBin {
		public Exp op1, op2;
		public Resta(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 2;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class And extends ExpBin {
		public Exp op1, op2;
		public And(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}
		@Override
		public int prioridad() {
			return 3;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Or extends ExpBin {
		public Exp op1, op2;
		public Or(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 3;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Mult extends ExpBin {
		public Exp op1, op2;
		public Mult(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 4;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Div extends ExpBin {
		public Exp op1, op2;
		public Div(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}
		@Override
		public int prioridad() {
			return 4;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Mod extends ExpBin {
		public Exp op1, op2;
		public Mod(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 4;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Not_unario extends Exp {
		public Exp op1;
		public Not_unario(Exp op1) {
			super(op1);
			op1 = this.op1;
		}

		@Override
		public int prioridad() {
			return 5;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Resta_unario extends Exp {
		public Exp op1;
		public Resta_unario(Exp op1) {
			super(op1);
			op1 = this.op1;
		}
		@Override
		public int prioridad() {
			return 5;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Indexacion extends Exp {
		public Exp op1, op2;
		public Indexacion(Exp op1, Exp op2) {
			super(op1, op2);
			op1 = this.op1;
			op2 = this.op2;
			
		}

		@Override
		public int prioridad() {
			return 6;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Acc_reg extends Exp {
		public Exp op1;
		public String cad;
		public Acc_reg(Exp op1, String cadena) {
			super(op1, cadena);
			op1 = this.op1;
			cad = cadena;
		}

		@Override
		public int prioridad() {
			return 6;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Indireccion extends Exp {
		public Exp op1;
		public Indireccion(Exp op1) {
			super(op1);
			op1 = this.op1;
		}

		@Override
		public int prioridad() {
			return 6;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Lit_ent extends Exp {
        private String valor;
		public Lit_ent(String cadena) {
			super(cadena);
			valor = cadena;
		}
		
        public String valor() {return valor;}

		@Override
		public int prioridad() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}

	}

	public static class Lit_real extends Exp {
        private String valor;
		public Lit_real(String cadena) {
			super(cadena);
			valor = cadena;
		}
        public String valor() {return valor;}
		@Override
		public int prioridad() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}

	}

	public static class True_e extends Exp {
		public True_e() {
			super();

		}

		@Override
		public int prioridad() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class False_e extends Exp {
		public False_e() {
			super();
		}

		@Override
		public int prioridad() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Null_e extends Exp {
		public Null_e() {
			super();
		}

		@Override
		public int prioridad() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Cadena extends Exp {
		
		public Cadena(String cadena) {
			super(cadena);
		}

		 public String valor() {return cadena;}
		@Override
		public int prioridad() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		}
	}

	public static class Iden extends Exp {
		 private String id;
        public Iden(String id) {
            super();
            this.id = id;
        }
        public String iden() {return id;}
        public String toString() {
            return "iden("+id+"["+leeFila()+","+leeCol()+"])";
        }
		@Override
		public int prioridad() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void procesa(Procesamiento p) {
			 p.procesa(this);			
		} 
	}

	public static class Si_lista_opt_param extends LOptParam {
		public LParam lp;
		public Si_lista_opt_param(LParam lista_parametros) {
			super(lista_parametros);
			lp = lista_parametros;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class No_lista_opt_param extends LOptParam {
		public No_lista_opt_param() {
			super();
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Muchas_lista_param extends LParam {
		public LParam lp;
		public Exp e;
		public Muchas_lista_param(LParam lista_parametros, Exp expresion) {
			super(lista_parametros, expresion);
			lp = lista_parametros;
			e = expresion;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			
		}
	}

	public static class Una_lista_param extends LParam {
		public Exp e;
		public Una_lista_param(Exp expresion) {
			super(expresion);
			e = expresion;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);			// TODO Auto-generated method stub
			
		}
	}

	//////////////////////////////////////////////////// Funciones de llama a constructores

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

	public LDecs una_lista_decs(Dec declaracion) {
		return new Una_lista_decs(declaracion);
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

	public Inst inst_if(Exp expresion, Blq bloque) {
		return new Inst_if(expresion, bloque);
	}

	public Inst inst_if_else(Exp expresion, Blq bloque, Blq bloque2) {
		return new Inst_if_else(expresion, bloque, bloque2);
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
