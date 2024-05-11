package visitante;

import asint.SintaxisAbstractaTiny.*;
import errores.Errores;
import utils.Utils;


public class ComprobacionTipos extends ProcesamientoDef{
	
	private Errores errores;
	
	ComprobacionTipos(Errores e) {
		errores = e;
	}
	
	public void procesa(Prog_tiny p) {
		p.bloque().procesa(this);
		p.tipoCorrecto = p.bloque().tipoCorrecto;
	}

	public void procesa(Bloque p) {
		p.lista_opt_declaraciones().procesa(this);
		p.lista_opt_instrucciones().procesa(this);
		p.tipoCorrecto = ambos_ok(p.lista_opt_declaraciones().tipoCorrecto,
									p.lista_opt_instrucciones().tipoCorrecto);
	}

	public void procesa(Si_lista_opt_decs p) {
		p.lista_declaraciones().procesa(this);
		p.tipoCorrecto = p.lista_declaraciones().tipoCorrecto;
	}

	public void procesa(No_lista_opt_decs p) {
		p.tipoCorrecto = true;
	}

	public void procesa(Muchas_lista_decs p) {
		p.lista_declaraciones().procesa(this);
		p.declaracion().procesa(this);
		p.tipoCorrecto = ambos_ok(p.lista_declaraciones().tipoCorrecto,
				p.declaracion().tipoCorrecto);
	}

	public void procesa(Una_lista_dec p) {
		p.declaracion().procesa(this);
		p.tipoCorrecto = p.declaracion().tipoCorrecto;
	}

	public void procesa(Dec_var p) {
		p.tipo().procesa(this);
		p.tipoCorrecto = p.tipo().tipoCorrecto;
	}
	

	public void procesa(Dec_tipo p) {
		p.tipo().procesa(this);
		p.tipoCorrecto = p.tipo().tipoCorrecto;		
	}

	public void procesa(Dec_proc p) {
		p.lista_opt_parametros_formales().procesa(this);
		p.bloque().procesa(this);
		p.tipoCorrecto = ambos_ok(p.lista_opt_parametros_formales().tipoCorrecto,
								p.bloque().tipoCorrecto);
	}
	
	@Override
	public void procesa(Si_lista_opt_param_form p) {
		p.lista_parametros_formales().procesa(this);
		p.tipoCorrecto = p.lista_parametros_formales().tipoCorrecto;
	}

	@Override
	public void procesa(No_lista_opt_param_form p) {
		p.tipoCorrecto = true;
	}

	@Override
	public void procesa(Muchas_lista_param_form p) {
		p.lista_parametros_formales().procesa(this);
		p.parametro_formal().procesa(this);
		p.tipoCorrecto = ambos_ok(p.parametro_formal().tipoCorrecto,
								p.parametro_formal().tipoCorrecto);
	}

	@Override
	public void procesa(Una_lista_param_form p) {
		p.parametro_formal().procesa(this);
		p.tipoCorrecto = p.parametro_formal().tipoCorrecto;		
	}

	@Override
	public void procesa(Param_form_ref p) {
		p.tipo().procesa(this);
		p.tipoCorrecto = p.tipo().tipoCorrecto;
	}

	@Override
	public void procesa(Param_form p) {
		p.tipo().procesa(this);
		p.tipoCorrecto = p.tipo().tipoCorrecto;
	}

	public void procesa(Tipo_array p) {
		p.tipo().procesa(this);
		p.tipoCorrecto = p.tipo().tipoCorrecto;
	}

	public void procesa(Tipo_puntero p) {
		p.tipo().procesa(this);
		p.tipoCorrecto = p.tipo().tipoCorrecto;	
	}

	public void procesa(Tipo_registro p) {
		p.lista_parametros_registro().procesa(this);
		p.tipoCorrecto = p.lista_parametros_registro().tipoCorrecto;
	}

	public void procesa(Tipo_definido p) {
		// Esto es parte del pre-tipado
		p.tipoCorrecto = p.vinculo instanceof Dec_tipo;
		if (!p.tipoCorrecto)
			errores.nuevoError();
	}

	public void procesa(Int_t p) {
		p.tipoCorrecto = true;
	}

	public void procesa(Real_t p) {
		p.tipoCorrecto = true;		
	}

	public void procesa(Bool_t p) {
		p.tipoCorrecto = true;		
	}

	public void procesa(String_t p) {
		p.tipoCorrecto = true;		
	}

	public void procesa(Muchas_lista_param_reg p) {
		p.lista_parametros_registro().procesa(this);
		p.parametro_registro().procesa(this);
		p.tipoCorrecto = ambos_ok(p.lista_parametros_registro().tipoCorrecto,
								p.parametro_registro().tipoCorrecto);
	}

	public void procesa(Una_lista_param_reg p) {
		p.parametro_registro().procesa(this);
		p.tipoCorrecto = p.parametro_registro().tipoCorrecto; 
	}

	
	public void procesa(Param_reg p) {
		p.tipo().procesa(this);
		p.tipoCorrecto = p.tipo().tipoCorrecto;
	}

	
	public void procesa(Si_lista_opt_inst p) {
		p.lista_instrucciones().procesa(this);
		p.tipoCorrecto = p.lista_instrucciones().tipoCorrecto;
	}

	
	public void procesa(No_lista_opt_inst p) {
		p.tipoCorrecto = true;
	}

	public void procesa(Muchas_lista_inst p) {
		p.lista_instrucciones().procesa(this);
		p.instruccion().procesa(this);
		p.tipoCorrecto = ambos_ok(p.lista_instrucciones().tipoCorrecto,
								p.instruccion().tipoCorrecto);
	}

	public void procesa(Una_lista_inst p) {
		p.instruccion().procesa(this);
		p.tipoCorrecto = p.instruccion().tipoCorrecto;
	}

	public void procesa(Inst_eval p) {
		p.expresion().procesa(this);
		
		p.tipoCorrecto = p.expresion().tipoCorrecto; 
		if (!p.tipoCorrecto)
			errores.nuevoError();
	}
	
	@Override
	public void procesa(Inst_if p) {
		p.expresion().procesa(this);
		p.bloque1().procesa(this);
		p.tipoCorrecto = Utils.ref(p.expresion().tipo) instanceof Bool_t &&
									p.bloque1().tipoCorrecto;
		if (!p.tipoCorrecto)
			errores.nuevoError();
	}

	@Override
	public void procesa(Inst_if_else p) {
		p.expresion().procesa(this);
		p.bloque1().procesa(this);
		p.bloque2().procesa(this);
		p.tipoCorrecto = Utils.ref(p.expresion().tipo) instanceof Bool_t &&
									p.bloque1().tipoCorrecto &&
									p.bloque2().tipoCorrecto;
		if (!p.tipoCorrecto)
			errores.nuevoError();
	}

	public void procesa(Inst_while p) {
		
		p.expresion().procesa(this);
		p.bloque().procesa(this);
		p.tipoCorrecto = Utils.ref(p.expresion().tipo) instanceof Bool_t &&
									p.bloque().tipoCorrecto;
		if (!p.tipoCorrecto)
			errores.nuevoError();
	}

	public void procesa(Inst_read p) {
		p.expresion().procesa(this);
		Tipo tipo_ref = Utils.ref(p.expresion().tipo);
		p.tipoCorrecto = Utils.es_designador(p.expresion()) &&
						(Utils.esEntero(tipo_ref) ||
						Utils.esReal(tipo_ref) ||
						Utils.esString(tipo_ref)); 
		if (!p.tipoCorrecto)
			errores.nuevoError();
	}

	public void procesa(Inst_write p) {
		p.expresion().procesa(this);
		Tipo tipo_ref = Utils.ref(p.expresion().tipo);
		
		p.tipoCorrecto = Utils.es_designador(p.expresion()) &&
				(Utils.esEntero(tipo_ref) ||
				Utils.esReal(tipo_ref) ||
				Utils.esString(tipo_ref) ||
				Utils.esBoolean(tipo_ref));
		if (!p.tipoCorrecto)
			errores.nuevoError();
}

	public void procesa(Inst_nl p) {
		p.tipoCorrecto = true;
	}

	public void procesa(Inst_new p) {
		p.expresion().procesa(this);
		Tipo tipo_ref = Utils.ref(p.expresion().tipo);
		p.tipoCorrecto = tipo_ref instanceof Tipo_puntero;
		if (!p.tipoCorrecto)
			errores.nuevoError();
	}
	public void procesa(Inst_delete p) {
		p.expresion().procesa(this);
		Tipo tipo_ref = Utils.ref(p.expresion().tipo);
		p.tipoCorrecto = tipo_ref instanceof Tipo_puntero;
		if (!p.tipoCorrecto)
			errores.nuevoError();
	}
	
	@Override
	public void procesa(Inst_call p) {
		if (!(p.vinculo instanceof Dec_proc))
			errores.nuevoError(); // Esto nunca se debería dar por que asumimos una vinculacion sin errores
		else {
			LOptParamForm loptpf = ((Dec_proc)p.vinculo).lista_opt_parametros_formales();
			if (loptpf.num_elems != p.lista_opt_parametros().num_elems)
				errores.nuevoError(); // Se esperaban loptpf.num_elems parametros y se recibieron p.lista_opt_parametros.num_elems
			else
				comparar_listas(loptpf.lista_parametros_formales(), p.lista_opt_parametros().lista_parametros());
		}
	}
	
	//Con las dos funciones siguientes pasamos de un procesamiento recursivo a uno secuencial
	//Con esto terminamos el procesamiento de comprobacion de tipos por completo de una instruccion call
	//Y la comprobacion de los tipos entre los argumentos y los parametros del procedimiento
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
				if (! (Utils.es_designador(e) && Utils.esReal(e.tipo)) )
					errores.nuevoError(); // No se cumple la segunda condicion del ultimo parrafo de la pagina 5, interpretalo tu, majo
			else
				if (! (Utils.es_designador(e) && compatibles(p.tipo(), e.tipo)) )
					errores.nuevoError(); // Tipos no compatibles
		}
	}

	public void procesa(Inst_comp p) {
		p.bloque().procesa(this);
		p.tipoCorrecto = p.bloque().tipoCorrecto;
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
			p.tipo = ((Dec_var)p.vinculo).tipo();
		else if(p.vinculo instanceof Param_form)
			p.tipo = ((Param_form)p.vinculo).tipo();
		else if (p.vinculo instanceof Param_form_ref)
			p.tipo = ((Param_form_ref)p.vinculo).tipo();
		else
			errores.nuevoError(); // El vinculo no está bien vinculado, no se debería dar
	}
	
	public void procesa(Asignacion p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		if (!es_designador(p.op1()))
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
		
		if ( ((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2)))
				||
				(Utils.esBoolean(t1) && Utils.esBoolean(t2)) ||
				(Utils.esString(t1) && Utils.esString(t2)) )
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	public void procesa(Mayor_igual p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);
		
		if ( ((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2)))
				||
				(Utils.esBoolean(t1) && Utils.esBoolean(t2)) ||
				(Utils.esString(t1) && Utils.esString(t2)) )
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	public void procesa(Menor p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);
		
		if ( ((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2)))
				||
				(Utils.esBoolean(t1) && Utils.esBoolean(t2)) ||
				(Utils.esString(t1) && Utils.esString(t2)) )
			p.tipo = new Bool_t();
		else
			errores.nuevoError(); // Tipos no comparables
	}

	public void procesa(Menor_igual p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = Utils.ref(p.op1().tipo);
		Tipo t2 = Utils.ref(p.op2().tipo);
		
		if ( ((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2)))
				||
				(Utils.esBoolean(t1) && Utils.esBoolean(t2)) ||
				(Utils.esString(t1) && Utils.esString(t2)) )
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
		
		if ( ((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2)))
				||
				(Utils.esBoolean(t1) && Utils.esBoolean(t2)) ||
				(Utils.esString(t1) && Utils.esString(t2)) ||
				(t1 instanceof Tipo_puntero || t1 instanceof Null_t) && (t2 instanceof Tipo_puntero || t2 instanceof Null_t))
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
		
		if ( ((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2)))
				||
				(Utils.esBoolean(t1) && Utils.esBoolean(t2)) ||
				(Utils.esString(t1) && Utils.esString(t2)) ||
				(t1 instanceof Tipo_puntero || t1 instanceof Null_t) && (t2 instanceof Tipo_puntero || t2 instanceof Null_t))
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
		else if (((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2))))
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
		else if (((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2))))
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
		else if (((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2))))
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
		else if (((Utils.esEntero(t1) || Utils.esReal(t1)) &&
			(Utils.esEntero(t2) || Utils.esReal(t2))))
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
		
		if (t1 instanceof Tipo_array &&
			t2.tipo() instanceof Int_t)
			p.tipo = t1.tipo();
		else
			errores.nuevoError(); // Tipo no compatible
	}

	@Override
	public void procesa(Acc_reg p) {
		p.op1().procesa(this);
		
		Tipo t1 = Utils.ref(p.op1().tipo);
		
		if (t1 instanceof Tipo_registro) {
			p.tipo = existe(((Tipo_registro)t1).lista_parametros_registro(), p.cadena());
			if (p.tipo == null)
				errores.nuevoError(); // No existe el campo p.cadena() en el registro t1
		}
		else
			if (p.tipo == null)
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
			p.tipo = ((Tipo_puntero)t1).tipo();
		else
			errores.nuevoError(); // t1 no e sun Tipo_puntero
	}

	 ////////////////////////////////////METODOS ADICIONALES//////////////////////////////////////////////////////
	
    public boolean claseDe(Object o, Class c) {
        return o ;
    } 
    
    public boolean claseDistError(Object o) {
        return o.getClass() != Error.class;
    }
	
	public boolean unificables(Tipo t1, Tipo T2) {//TERMINAR; NO SÉ o?
			
			return false;
	}
	
	public boolean son_unificables(Tipo t1, Tipo T2) {//TERMINAR; NO SÉ o?
		
		return false;
	}

    public boolean es_designador(Exp e) {
    	return claseDe(e, Iden.class) ||  claseDe(e, Indexacion.class) ||  claseDe(e, Acc_reg.class) ||  claseDe(e, Indireccion.class);
    }
    
	public Boolean ambos_ok(Boolean t1, Boolean t2) {
		if (!t1 || !t2)
			errores.nuevoError();
		return t1 && t2;
	}
	

	//compatibles_registro(Muchas_lista_param_reg l1, Muchas_lista_param_reg l2) deberi ser esta pero da error
	public boolean compatibles_registro(LParamReg l1, LParamReg l2) {
		return compatibles_registro(l1.lista_parametros_registro(), l2.lista_parametros_registro())
				&& compatibles_registro(l1.parametro_registro(), l2.parametro_registro());
	}
	
	public boolean compatibles_registro(Una_lista_param_reg l1, Una_lista_param_reg l2) {
		return compatibles_registro(l2.parametro_registro(), l1.parametro_registro());
	}
	
	public boolean compatibles_registro(ParamReg l1, ParamReg l2) {
		return compatibles(l1.getTipo(), l2.getTipo());
	}


	/*	public boolean compatibles_proc(LParam lp, LParamForm lpf) {
		// TODO Auto-generated method stub
		return false;
	}*/	
}
