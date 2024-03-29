package sintaxisAbstractaTiny.asint;

public class SintaxisAbstractaTiny {
	public SintaxisAbstractaTiny() {
	};

	private static abstract class ProgT {
		protected Blq bloque;

		public ProgT(Blq bloque) {
			this.bloque = bloque;
		}
	}

	private static abstract class Blq {
		protected LOptDecs lista_opt_declaraciones;
		protected LOptInst lista_opt_instrucciones;

		public Blq(LOptDecs lista_opt_declaraciones, LOptInst lista_opt_instrucciones) {
			this.lista_opt_declaraciones = lista_opt_declaraciones;
			this.lista_opt_instrucciones = lista_opt_instrucciones;
		}
	}

	private static abstract class LOptDecs {
		protected LDecs lista_declaraciones;

		public LOptDecs(LDecs lista_declaraciones) {
			this.lista_declaraciones = lista_declaraciones;
		}

		public LOptDecs() {
		};
	}

	private static abstract class LDecs {
		protected LDecs lista_declaraciones;
		protected Dec declaracion;

		public LDecs(LDecs lista_declaraciones, Dec declaracion) {
			this.lista_declaraciones = lista_declaraciones;
			this.declaracion = declaracion;
		}

		public LDecs(Dec declaracion) {
			this.declaracion = declaracion;
		}
	}

	private static abstract class Dec {
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
	}

	private static abstract class LOptParamForm {
		protected LParamForm lista_parametros_formales;

		public LOptParamForm(LParamForm lista_parametros_formales) {
			this.lista_parametros_formales = lista_parametros_formales;
		}

		public LOptParamForm() {
		};
	}

	private static abstract class LParamForm {
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

	private static abstract class ParamForm {
		protected Tipo tipo;
		protected String cadena;

		public ParamForm() {
		};

		public ParamForm(Tipo tipo, String cadena) {
			this.tipo = tipo;
			this.cadena = cadena;
		}
	}

	private static abstract class Tipo {
		protected Tipo tipo;
		protected String cadena;
		protected LParamReg lista_parametros_registro;

		public Tipo() {
		};

		public Tipo(String cadena, Tipo tipo) {
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

	private static abstract class LParamReg {
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

	private static abstract class ParamReg {
		protected Tipo tipo;
		protected String cadena;

		public ParamReg(Tipo tipo, String cadena) {
			this.tipo = tipo;
			this.cadena = cadena;
		}
	}

	private static abstract class LOptInst {
		protected LInst lista_instrucciones;

		public LOptInst() {
		};

		public LOptInst(LInst lista_instrucciones) {
			this.lista_instrucciones = lista_instrucciones;
		}

	}
	
	private static abstract class LInst{
		protected LInst lista_instrucciones;
		protected Inst instruccion;
		
		public LInst(LInst lista_instrucciones, Inst instruccion) {
			this.lista_instrucciones = lista_instrucciones;
			this.instruccion = instruccion;
		}
		
		public LInst(Inst instruccion) {
			this.instruccion = instruccion;
		}
	}

	private static abstract class Inst{
		protected Exp expresion;
		protected Blq bloque1;
		protected Blq bloque2;
		protected String cadena;
		protected LOptParam parametro;
		
		public Inst() {};
		
		public Inst(Exp expresion ) {
			this.expresion = expresion;
		}
		
		public Inst(Exp expresion , Blq bloque) {
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
	
	private static abstract class Exp{
		protected Exp op1;
		protected Exp op2;
		protected String cadena;
		
		public Exp() {};
		public Exp (Exp op1, Exp op2) {
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
	}
	
	private static abstract class LOptParam{
		protected LParam lista_parametros;
	
		
		public LOptParam() {};
		public LOptParam(LParam lista_parametros) {
			this.lista_parametros = lista_parametros;
		}
	
	}
	
	private static abstract class LParam{
		protected LParam lista_parametros;
		protected Exp expresion;
		
		public LParam(LParam lista_parametros, Exp expresion) {
			this.lista_parametros = lista_parametros;
			this.expresion = expresion;
		}
		
		public LParam(Exp expresion ) {
			this.expresion = expresion;
		}
	}
	
}
