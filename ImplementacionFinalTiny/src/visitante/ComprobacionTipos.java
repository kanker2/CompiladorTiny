package visitante;

import java.util.HashSet;

import asint.SintaxisAbstractaTiny.*;
import errores.Errores;
import utils.Pair;
import utils.Utils;

public class ComprobacionTipos extends ProcesamientoDef {

	private Errores errores;

	ComprobacionTipos(Errores e) {
		errores = e;
	}

	public void procesa(Prog_tiny p) {
		p.bloque().procesa(this);
		p.tipo = p.bloque().tipo;
	}

	public void procesa(Bloque p) {
		p.lista_opt_declaraciones().procesa(this);
		p.lista_opt_instrucciones().procesa(this);
		p.tipo = Utils.ambos_ok(p.lista_opt_declaraciones().tipo, p.lista_opt_instrucciones().tipo);
	}

	public void procesa(Si_lista_opt_decs p) {
		p.lista_declaraciones().procesa(this);
		p.tipo = p.lista_declaraciones().tipo;
	}

	public void procesa(No_lista_opt_decs p) {
		p.tipo = new Ok_t();
	}

	public void procesa(Muchas_lista_decs p) {
		p.lista_declaraciones().procesa(this);
		p.declaracion().procesa(this);
		p.tipo = Utils.ambos_ok(p.lista_declaraciones().tipo, p.declaracion().tipo());
	}

	public void procesa(Una_lista_dec p) {
		p.declaracion().procesa(this);
		
		if(!Utils.esTipoError(p.declaracion().tipo())) {
			p.tipo = new Ok_t();
		}
	}

	public void procesa(Dec_var p) {
		p.tipo().procesa(this);
		p.tipo = p.tipo().tipo();
	}

	public void procesa(Dec_tipo p) {
		p.tipo().procesa(this);
		p.tipo = p.tipo().tipo();
	}

	public void procesa(Dec_proc p) {
		p.lista_opt_parametros_formales().procesa(this);
		p.bloque().procesa(this);
		p.tipo = Utils.ambos_ok(p.lista_opt_parametros_formales().tipo, p.bloque().tipo);
	}

	@Override
	public void procesa(Si_lista_opt_param_form p) {
		p.lista_parametros_formales().procesa(this);
		p.tipo = p.lista_parametros_formales().tipo;
	}

	@Override
	public void procesa(No_lista_opt_param_form p) {
		p.tipo = new Ok_t();
	}

	@Override
	public void procesa(Muchas_lista_param_form p) {
		p.lista_parametros_formales().procesa(this);
		p.parametro_formal().procesa(this);
		p.tipo = Utils.ambos_ok(p.parametro_formal().tipo(), p.parametro_formal().tipo());
	}

	@Override
	public void procesa(Una_lista_param_form p) {
		p.parametro_formal().procesa(this);
		p.tipo = p.parametro_formal().tipo();
	}

	@Override
	public void procesa(Param_form_ref p) {
		p.tipo().procesa(this);
		p.tipo = p.tipo().tipo();
	}

	@Override
	public void procesa(Param_form p) {
		p.tipo().procesa(this);
		p.tipo = p.tipo().tipo();
	}

	public void procesa(Tipo_array p) {
		p.tipo().procesa(this);
		p.tipo = p.tipo().tipo;
	}

	public void procesa(Tipo_puntero p) {
		p.tipo().procesa(this);
		p.tipo = p.tipo().tipo;
	}

	public void procesa(Tipo_registro p) {
		p.lista_parametros_registro().procesa(this);
		p.tipo = p.lista_parametros_registro().tipo;
	}

	public void procesa(Tipo_definido p) {
		// Esto es parte del pre-tipado
		
		if(p.vinculo instanceof Dec_tipo) {
			p.tipo = new Ok_t();
		}
		else {
			p.tipo = new Error_t();
			errores.nuevoError();
		}
	}

	public void procesa(Int_t p) {
		p.tipo = new Ok_t();
	}

	public void procesa(Real_t p) {
		p.tipo = new Ok_t();
	}

	public void procesa(Bool_t p) {
		p.tipo = new Ok_t();
	}

	public void procesa(String_t p) {
		p.tipo = new Ok_t();
	}

	public void procesa(Muchas_lista_param_reg p) {
		p.lista_parametros_registro().procesa(this);
		p.parametro_registro().procesa(this);
		p.tipo = Utils.ambos_ok(p.lista_parametros_registro().tipo, p.parametro_registro().tipo);
	}

	public void procesa(Una_lista_param_reg p) {
		p.parametro_registro().procesa(this);
		
		if(!Utils.esTipoError(p.parametro_registro().tipo())){
			p.tipo = new Ok_t();
		}
	}

	public void procesa(Param_reg p) {
		p.tipo().procesa(this);
		p.tipo = p.tipo().tipo;
	}

	public void procesa(Si_lista_opt_inst p) {
		p.lista_instrucciones().procesa(this);
		p.tipo = p.lista_instrucciones().tipo;
	}

	public void procesa(No_lista_opt_inst p) {
		p.tipo = new Ok_t();
	}

	public void procesa(Muchas_lista_inst p) {
		p.lista_instrucciones().procesa(this);
		p.instruccion().procesa(this);
		p.tipo = Utils.ambos_ok(p.lista_instrucciones().tipo, p.instruccion().tipo);
	}

	public void procesa(Una_lista_inst p) {
		p.instruccion().procesa(this);
		p.tipo = p.instruccion().tipo;
	}

	public void procesa(Inst_eval p) {
		p.expresion().procesa(this);

		
		p.tipo = p.expresion().tipo;
		if (p.tipo instanceof Error_t)
			errores.nuevoError();
	}

	@Override
	public void procesa(Inst_if p) {
		p.expresion().procesa(this);
		p.bloque1().procesa(this);
		
		if(Utils.ref(p.expresion().tipo) instanceof Bool_t && p.bloque1().tipo instanceof Ok_t) {
			p.tipo = new Ok_t();
		}
		else {
			p.tipo = new Error_t();
			errores.nuevoError();
		}
	}

	@Override
	public void procesa(Inst_if_else p) {
		p.expresion().procesa(this);
		p.bloque1().procesa(this);
		p.bloque2().procesa(this);
		
		if(Utils.ref(p.expresion().tipo) instanceof Bool_t && p.bloque1().tipo instanceof Ok_t
				&& p.bloque2().tipo instanceof Ok_t) {
			p.tipo = new Ok_t();
		}
		else {
			p.tipo = new Error_t();
			errores.nuevoError();
		}
	}

	public void procesa(Inst_while p) {

		p.expresion().procesa(this);
		p.bloque().procesa(this);
		
		if(Utils.ref(p.expresion().tipo) instanceof Bool_t && p.bloque1().tipo instanceof Ok_t) {
			p.tipo = new Ok_t();
		}
		else {
			p.tipo = new Error_t();
			errores.nuevoError();
		}
	}

	public void procesa(Inst_read p) {
		p.expresion().procesa(this);
		Tipo tipo_ref = Utils.ref(p.expresion().tipo);
			
		if(Utils.es_designador(p.expresion()) && (Utils.esEntero(tipo_ref) || Utils.esReal(tipo_ref) || Utils.esString(tipo_ref))){
			p.tipo = new Ok_t();
		}
		else {
			p.tipo = new Error_t();
			errores.nuevoError();
		}
	}

	public void procesa(Inst_write p) {
		p.expresion().procesa(this);
		Tipo tipo_ref = Utils.ref(p.expresion().tipo);
		
		if(Utils.es_designador(p.expresion()) && (Utils.esEntero(tipo_ref) || Utils.esReal(tipo_ref)
				|| Utils.esString(tipo_ref) || Utils.esBoolean(tipo_ref))){
			p.tipo = new Ok_t();
		}
		else {
			p.tipo = new Error_t();
			errores.nuevoError();
		}
			
	}

	public void procesa(Inst_nl p) {
		p.tipo = new Ok_t();
	}

	public void procesa(Inst_new p) {
		p.expresion().procesa(this);
		Tipo tipo_ref = Utils.ref(p.expresion().tipo);
		
		if(tipo_ref instanceof Tipo_puntero) {
			p.tipo = new Ok_t();
		}
		else {
			p.tipo = new Error_t();
			errores.nuevoError();
		}
	}

	public void procesa(Inst_delete p) {
		p.expresion().procesa(this);
		Tipo tipo_ref = Utils.ref(p.expresion().tipo);
		if(tipo_ref instanceof Tipo_puntero) {
			p.tipo = new Ok_t();
		}
		else {
			p.tipo = new Error_t();
			errores.nuevoError();
		}
	}

	@Override
	public void procesa(Inst_call p) {
		if (!(p.vinculo instanceof Dec_proc))
			errores.nuevoError(); // Esto nunca se debería dar por que asumimos una vinculacion sin errores
		else {
			LOptParamForm loptpf = ((Dec_proc) p.vinculo).lista_opt_parametros_formales();
			if (loptpf.num_elems != p.lista_opt_parametros().num_elems)
				errores.nuevoError(); // Se esperaban loptpf.num_elems parametros y se recibieron
										// p.lista_opt_parametros.num_elems
			else
				comparar_listas(loptpf.lista_parametros_formales(), p.lista_opt_parametros().lista_parametros());
		}
	}

	// Con las dos funciones siguientes pasamos de un procesamiento recursivo a uno
	// secuencial
	// Con esto terminamos el procesamiento de comprobacion de tipos por completo de
	// una instruccion call
	// Y la comprobacion de los tipos entre los argumentos y los parametros del
	// procedimiento
	private void comparar_listas(LParamForm lpf, LParam lp) {
		ParamForm pf = lpf.parametro_formal();
		Exp exp = lp.expresion();

		tipado(pf, exp);
		if (lpf instanceof Muchas_lista_param_form)
			comparar_listas(lpf.lista_parametros_formales(), lp.lista_parametros());
	}

	private void tipado(ParamForm p, Exp e) {
		e.procesa(this);

		if (p instanceof Param_form)
			if (!compatibles(p.tipo(), e.tipo))
				errores.nuevoError(); // Tipos no compatibles
			else if (p instanceof Param_form_ref) {
				if (Utils.esReal(p.tipo()))
					if (!(Utils.es_designador(e) && Utils.esReal(e.tipo)))
						errores.nuevoError(); // No se cumple la segunda condicion del ultimo parrafo de la pagina 5,
												// interpretalo tu, majo
					else if (!(Utils.es_designador(e) && compatibles(p.tipo(), e.tipo)))
						errores.nuevoError(); // Tipos no compatibles
			}
	}

	public void procesa(Inst_comp p) {
		p.bloque().procesa(this);
		p.tipo = p.bloque().tipo;
	}

	public void procesa(Lit_ent p) {
		p.tipo = new Int_t();
	}

	public void procesa(Lit_real p) {
		p.tipo = new Real_t();
	}

	public void procesa(True_e p) {
		p.tipo = new Bool_t();
	}

	public void procesa(False_e p) {
		p.tipo = new Bool_t();
	}

	public void procesa(Cadena p) {
		p.tipo = new String_t();
	}

	public void procesa(Null_e p) {
		p.tipo = new Null_t();
	}

	public void procesa(Iden p) {
		if (p.vinculo instanceof Dec_var)
			p.tipo = ((Dec_var) p.vinculo).tipo();
		else if (p.vinculo instanceof Param_form)
			p.tipo = ((Param_form) p.vinculo).tipo();
		else if (p.vinculo instanceof Param_form_ref)
			p.tipo = ((Param_form_ref) p.vinculo).tipo();
		else
			errores.nuevoError(); // El vinculo no está bien vinculado, no se debería dar
	}

	public void procesa(Asignacion p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		if (!Utils.es_designador(p.op1()))
			errores.nuevoError(); // El lado de la izquierda no se le puede asignar nada
		else {
			if (compatibles(p.op1().tipo, p.op2().tipo))
				p.tipo = p.op1().tipo;
			else
				errores.nuevoError(); // Tipos no compatibles
		}
	}

	public void procesa(Mayor p) {

		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2)))
				|| (Utils.esBoolean(t1) && Utils.esBoolean(t2)) || (Utils.esString(t1) && Utils.esString(t2)))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	public void procesa(Mayor_igual p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2)))
				|| (Utils.esBoolean(t1) && Utils.esBoolean(t2)) || (Utils.esString(t1) && Utils.esString(t2)))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	public void procesa(Menor p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2)))
				|| (Utils.esBoolean(t1) && Utils.esBoolean(t2)) || (Utils.esString(t1) && Utils.esString(t2)))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	public void procesa(Menor_igual p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2)))
				|| (Utils.esBoolean(t1) && Utils.esBoolean(t2)) || (Utils.esString(t1) && Utils.esString(t2)))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	@Override
	public void procesa(Comparacion p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2)))
				|| (Utils.esBoolean(t1) && Utils.esBoolean(t2)) || (Utils.esString(t1) && Utils.esString(t2))
				|| (t1 instanceof Tipo_puntero || t1 instanceof Null_t)
						&& (t2 instanceof Tipo_puntero || t2 instanceof Null_t))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	@Override
	public void procesa(Distinto p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2)))
				|| (Utils.esBoolean(t1) && Utils.esBoolean(t2)) || (Utils.esString(t1) && Utils.esString(t2))
				|| (t1 instanceof Tipo_puntero || t1 instanceof Null_t)
						&& (t2 instanceof Tipo_puntero || t2 instanceof Null_t))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	@Override
	public void procesa(Suma p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (Utils.esEntero(t1) && Utils.esEntero(t2))
			p.tipo = new Int_t();
		else if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2))))
			p.tipo = new Real_t();
		else
			errores.nuevoError(); // Tipos no operables
	}

	@Override
	public void procesa(Resta p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (Utils.esEntero(t1) && Utils.esEntero(t2))
			p.tipo = new Int_t();
		else if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2))))
			p.tipo = new Real_t();
		else
			errores.nuevoError(); // Tipos no operables
	}

	@Override
	public void procesa(And p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (Utils.esBoolean(t1) && Utils.esBoolean(t2))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	@Override
	public void procesa(Or p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (Utils.esBoolean(t1) && Utils.esBoolean(t2))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	@Override
	public void procesa(Mult p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (Utils.esEntero(t1) && Utils.esEntero(t2))
			p.tipo = new Int_t();
		else if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2))))
			p.tipo = new Real_t();
		else
			errores.nuevoError(); // Tipos no operables
	}

	@Override
	public void procesa(Div p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (Utils.esEntero(t1) && Utils.esEntero(t2))
			p.tipo = new Int_t();
		else if (((Utils.esEntero(t1) || Utils.esReal(t1)) && (Utils.esEntero(t2) || Utils.esReal(t2))))
			p.tipo = new Real_t();
		else
			errores.nuevoError(); // Tipos no operables
	}

	@Override
	public void procesa(Mod p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (Utils.esEntero(t1) && Utils.esEntero(t2))
			p.tipo = new Int_t();
		else
			errores.nuevoError(); // Tipos no operables
	}

	@Override
	public void procesa(Not_unario p) {
		p.op1().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);

		if (Utils.esBoolean(t1))
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipo no compatible
	}

	@Override
	public void procesa(Resta_unario p) {
		p.op1().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);

		if (Utils.esEntero(t1))
			p.tipo = new Int_t();
		else if (Utils.esReal(t1))
			p.tipo = new Real_t();
		else
			errores.nuevoError(); // Tipo no compatible
	}

	@Override
	public void procesa(Indexacion p) {
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);

		if (t1 instanceof Tipo_array && t2.tipo() instanceof Int_t)
			p.tipo = t1.tipo();
		else
			errores.nuevoError(); // Tipo no compatible
	}

	@Override
	public void procesa(Acc_reg p) {
		p.op1().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);

		if (t1 instanceof Tipo_registro) {
			p.tipo = existe(((Tipo_registro) t1).lista_parametros_registro(), p.cadena());
			if (p.tipo == null)
				errores.nuevoError(); // No existe el campo p.cadena() en el registro t1
		} else if (p.tipo == null)
			errores.nuevoError(); // t1 no es un Tipo_registro
	}

	private Tipo existe(LParamReg l, String c) {
		ParamReg p = l.parametro_registro();

		if (p.cadena().equals(c))
			return p.tipo();

		if (l instanceof Muchas_lista_param_reg)
			return existe(l.lista_parametros_registro(), c);
		else
			return null;
	}

	@Override
	public void procesa(Indireccion p) {
		p.op1().procesa(this);

		Tipo t1 = Utils.ref(p.op1().tipo);

		if (t1 instanceof Tipo_puntero)
			p.tipo = ((Tipo_puntero) t1).tipo();
		else
			errores.nuevoError(); // t1 no e sun Tipo_puntero
	}

	////////////////////////////////////   METODOS  //////////////////////////////////////////////////////
	//////////////////////////////////// ADICIONALES//////////////////////////////////////////////////////
	static public boolean compatibles(Tipo t1, Tipo t2) {
		HashSet<Pair<Tipo, Tipo>> conjunto = new HashSet<>();
		conjunto.add(new Pair<>(t1, t2));
		return unificables(t1, t2, conjunto);
	}

	static public boolean unificables(Tipo t1, Tipo t2, HashSet<Pair<Tipo, Tipo>> c) {
		Tipo T1 = Utils.ref(t1);
		Tipo T2 = Utils.ref(t2);

		if (Utils.esEntero(T1) && Utils.esEntero(T2)) {
			return true;
		} else if (Utils.esReal(T1) && Utils.esReal(T2)) {
			return true;
		} else if (Utils.esReal(T1) && Utils.esEntero(T2)) {
			return true;
		} else if (Utils.esEntero(T1) && Utils.esReal(T2)) {
			return true;
		} else if (Utils.esBoolean(T1) && Utils.esBoolean(T2)) {
			return true;
		} else if (Utils.esString(T1) && Utils.esString(T2)) {
			return true;
		} else if (Utils.esArray(T1) && Utils.esArray(T2)) {
			return son_unificables(T1.tipo(), T2.tipo(), c);
		} else if (Utils.esRegistro(T1) && Utils.esRegistro(T2)
				&& numElems(T1.lista_parametros_registro()) == numElems(T2.lista_parametros_registro())) {
			return compatibles_registro(T1.lista_parametros_registro(), T2.lista_parametros_registro());
		}
		else if(Utils.esPuntero(T1) && Utils.esPuntero(T2)) {
			return son_unificables(T1.tipo(), T2.tipo(), c);
		}
		else if(Utils.esPuntero(T1) && T2 instanceof Null_t 
				|| T1 instanceof Null_t && Utils.esPuntero(T2)) {
			return true;
		}
		return false;
	}

	static boolean son_unificables(Tipo t1, Tipo t2, HashSet<Pair<Tipo, Tipo>> c) {
		Pair<Tipo, Tipo> p = new Pair<>(t1, t2);

		if (!c.contains(p)) {
			c.add(p);
			return unificables(t1, t2, c);
		}
		return true;
	}

	// Comprobado
	static int numElems(LParamReg l) {
		if (l instanceof Muchas_lista_param_reg)
			return numElems(l.lista_parametros_registro()) + 1;
		return 1;
	}

	// compatibles_registro(Muchas_lista_param_reg l1, Muchas_lista_param_reg l2)
	// deberi ser esta pero da error
	public static boolean compatibles_registro(LParamReg l1, LParamReg l2) {
		return compatibles_registro(l1.lista_parametros_registro(), l2.lista_parametros_registro())
				&& compatibles_registro(l1.parametro_registro(), l2.parametro_registro());
	}

	public static boolean compatibles_registro(Una_lista_param_reg l1, Una_lista_param_reg l2) {
		return compatibles_registro(l2.parametro_registro(), l1.parametro_registro());
	}

	public static boolean compatibles_registro(ParamReg l1, ParamReg l2) {
		return compatibles(l1.tipo(), l2.tipo());
	}

	/*
	 * public boolean compatibles_proc(LParam lp, LParamForm lpf) { // TODO
	 * Auto-generated method stub return false; }
	 */
}
