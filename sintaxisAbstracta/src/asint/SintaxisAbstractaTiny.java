package asint;

public class SintaxisAbstractaTiny {
	public SintaxisAbstractaTiny() {};
	
	
	private static abstract class ProgT {
		protected Blq bloque;
		
		public ProgT(Blq bloque) {
			this.bloque = bloque;
		}
	}
	
	private static abstract class Blq{
		protected LOptDecs lista_opt_declaraciones;
		protected LOptInst lista_opt_instrucciones;
		
		public Blq(LOptDecs lista_opt_declaraciones, LOptInst lista_opt_instrucciones) {
			this.lista_opt_declaraciones = lista_opt_declaraciones;
			this.lista_opt_instrucciones = lista_opt_instrucciones;
		}
	}
	
	private static abstract class LOptDecs{
		protected LDecs lista_declaraciones;
		
		public LOptDecs(LDecs lista_declaraciones) {
			this.lista_declaraciones = lista_declaraciones;
		}
		
		public LOptDecs() {};
	}
	
	private static abstract class LDecs{
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
	
	private static abstract class Dec{
		protected TpArray tipo_array;
		protected CabProc cabecera_proc;
		protected Blq bloque;
		protected String cadena;
		
		public Dec(TpArray tipo_array, String cadena) {
			this.tipo_array = tipo_array;
			this.cadena = cadena;
		}
		
		public Dec(CabProc cabecera_proc, Blq bloque) {
			this.cabecera_proc = cabecera_proc;
			this.bloque = bloque;
		}
	}
	
	private static abstract class CabProc{
		protected String cadena;
		protected LOptParamForm lista_opt_parametros_formales;
		
		public CabProc(String cadena, LOptParamForm lista_opt_parametros_formales) {
			this.cadena = cadena;
			this.lista_opt_parametros_formales = lista_opt_parametros_formales;
		}
	}
	
	private static abstract class LOptParamForm{
		protected LParamForm lista_parametros_formales;
	
		public LOptParamForm(LParamForm lista_parametros_formales) {
			this.lista_parametros_formales = lista_parametros_formales;
		}
		
		public LOptParamForm() {};
	}
	
	private static abstract class LParamForm{
		protected LParamForm lista_parametros_formales;
		protected ParamForm parametro_formal;
		
		public LParamForm(LParamForm lista_parametros_formales, ParamForm parametro_formal) {
			this.lista_parametros_formales = lista_parametros_formales;
			this.parametro_formal = parametro_formal;
		}
	}
	
}
