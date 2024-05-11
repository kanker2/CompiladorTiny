package visitante;

import asint.SintaxisAbstractaTiny.*;


public class ComprobacionTipos extends ProcesamientoDef{
	

	public void procesa(Prog_tiny p) {
		p.bloque().procesa(this);
		//p.setTipo(p.bloque().getTipo());  HEMOS COMETADO EL SETTIPO() PORQUE TODAVIA NO TENEMOS CLARO COMO HACERLO
			
	}

	public void procesa(Bloque p) {
		p.lista_opt_declaraciones().procesa(this);
		p.lista_opt_instrucciones().procesa(this);
		//p.setTipo(ambos_ok(p.lista_opt_declaraciones().getTipo(), p.lista_opt_declaraciones().getTipo()));
	}

	public void procesa(Si_lista_opt_decs p) {
		p.lista_declaraciones().procesa(this);
		//p.setTipo(p.lista_declaraciones().getTipo());
	}

	public void procesa(No_lista_opt_decs p) {
		//p.setTipo(Ok_t.class);		
	}

	public void procesa(Muchas_lista_decs p) {
		p.lista_declaraciones().procesa(this);
		p.declaracion().procesa(this);
		//p.setTipo(ambos_ok(p.lista_declaraciones().getTipo(), p.declaracion().getTipo()));

		
	}

	public void procesa(Una_lista_dec p) {
		p.declaracion().procesa(this);
		//p.setTipo(p.declaracion().getTipo());

	}

	public void procesa(Dec_var p) {
		p.tipo().procesa(this);
		//p.setTipo(p.tipo().getTipo());

	}
	

	public void procesa(Dec_tipo p) {
		p.tipo().procesa(this);
		//p.setTipo(p.tipo().getTipo());
		
	}

	public void procesa(Dec_proc p) {
		p.bloque().procesa(this);
		//p.setTipo(p.bloque().getTipo());
		
	}

	public void procesa(Tipo_array p) {
		p.tipo().procesa(this);
		//p.setTipo(p.tipo().getTipo());
		
	}

	public void procesa(Tipo_puntero p) {
		p.tipo().procesa(this);
		//p.setTipo(p.tipo().getTipo());
		
	}

	public void procesa(Int_t p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void procesa(Real_t p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void procesa(Bool_t p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void procesa(String_t p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void procesa(Tipo_registro p) {
		p.lista_parametros_registro().procesa(this);
		//p.setTipo(p.lista_parametros_registro().getTipo());		
	}

	public void procesa(Tipo_definido p) {
		if (claseDe(p.getTipo(), Dec_tipo.class)) {}
			//p.setTipo(Ok_t.class);		
		else {}
			//p.setTipo(Error_t.class);		

	}

	public void procesa(Muchas_lista_param_reg p) {
		p.lista_parametros_registro().procesa(this);
		p.parametro_registro().procesa(this);
		//p.setTipo(ambos_ok(p.lista_parametros_registro().getTipo(), p.parametro_registro().getTipo()));
	}

	public void procesa(Una_lista_param_reg p) {
		p.parametro_registro().procesa(this);
		//p.setTipo(p.parametro_registro().getTipo());
	}

	
	public void procesa(Param_reg p) {
		p.tipo().procesa(this);
		//p.setTipo(p.tipo().getTipo());
		
	}

	
	public void procesa(Si_lista_opt_inst p) {
		p.lista_instrucciones().procesa(this);
		//p.setTipo(p.lista_instrucciones().getTipo());
		
	}

	
	public void procesa(No_lista_opt_inst p) {
		//p.setTipo(Ok_t.class);		
	}

	public void procesa(Muchas_lista_inst p) {
		p.lista_instrucciones().procesa(this);
		p.instruccion().procesa(this);
		//p.setTipo(ambos_ok(p.lista_instrucciones().getTipo(), p.instruccion().getTipo()));		
	}

	public void procesa(Una_lista_inst p) {
		p.instruccion().procesa(this);
		//p.setTipo(p.instruccion().getTipo());		
	}

	public void procesa(Inst_eval p) {
		p.expresion().procesa(this);
		
		if (claseDistError(p.expresion().getTipo()) ) {}
			//p.setTipo(Ok_t.class);		
		else {}
			//	p.setTipo(Error_t.class);		


			
	}

	public void procesa(Inst_if_else p) {

		p.expresion().procesa(this);
		p.bloque1().procesa(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if (claseDe(p.bloque2(), Si_else.class)) { // si tiene segundo bloque
				p.bloque2().procesa(this);
				
				if (claseDe(t1, Bool_t.class) && claseDe(p.bloque1().getTipo(), Ok_t.class) && claseDe(p.bloque2().getTipo(), Ok_t.class)) {
					//p.setTipo(Ok_t.class);
				}
				else {
					//p.setTipo(Error_t.class);		

				}

			}
			else if (claseDe(t1, Bool_t.class) && claseDe(p.bloque1().getTipo(), Ok_t.class)) {
				//p.setTipo(Ok_t.class);		
			}
			else {
				//p.setTipo(Error_t.class);		
			}
	}

	public void procesa(Si_else p) {
		p.bloque().procesa(this);
		//p.setTipo(p.bloque().getTipo());
		
	}

	public void procesa(No_else p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void procesa(Inst_while p) {
		
		p.expresion().procesa(this);
		p.bloque().procesa(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if (claseDe(t1, Bool_t.class) &&claseDe(p.bloque().getTipo(), Ok_t.class)) {
				//p.setTipo(Ok_t.class); 		
			}
			else {
				//p.setTipo(Error_t.class);		
			}
			
	}

	public void procesa(Inst_read p) {

		p.expresion().procesa(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if ((claseDe(t1, String_t.class) || claseDe(t1, Real_t.class) || claseDe(t1, Int_t.class)
					)&& es_designador(p.expresion())) {
				//p.setTipo(Ok_t.class);		
			}
			else { 
				//p.setTipo(Error_t.class);		
			}
	}

	public void procesa(Inst_write p) {
		
		p.expresion().procesa(this);
		
		Tipo t1 = ref(p.expresion().getTipo());

		
			if (claseDe(t1, String_t.class) || claseDe(t1, Real_t.class) || claseDe(t1, Int_t.class) || claseDe(t1, Bool_t.class)) {
				//p.setTipo(Ok_t.class);		
			}
			else {
				//p.setTipo(Error_t.class);		
			}
	
	
}

	public void procesa(Inst_nl p) {
		//p.setTipo(Ok_t.class);		
	}

	public void procesa(Inst_new p) {

		p.expresion().procesa(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if (claseDe(t1, Tipo_puntero.class)) {
				//p.setTipo(Ok_t.class);		
			}
			else {
				//p.setTipo(Error_t.class);		
	
			}
	}
	public void procesa(Inst_delete p) {
		p.expresion().procesa(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if (claseDe(t1, Tipo_puntero.class)) {
				//p.setTipo(Ok_t.class);		
			}
			else {
				//p.setTipo(Error_t.class);		
	
			}
	}
	
	@Override
	public void procesa(Inst_call p) {
		p.parametro().procesa(this);
		
		//falta terminarla
			
	}

	public void procesa(Inst_comp p) {
		p.bloque().procesa(this);
		//p.setTipo(p.bloque().getTipo());
	}

	public void procesa(Si_lista_opt_param p) {
		p.lista_parametros().procesa(this);
		//p.setTipo(p.lista_parametros().getTipo());	
	}

	public void procesa(No_lista_opt_param p) {
		//p.setTipo(Ok_t.class);		
		
	}

	
	public void procesa(Muchas_lista_param p) {
		p.lista_parametros().procesa(this);
		p.expresion().procesa(this);
		
		if (claseDistError(p.expresion().getTipo()) && claseDe(p.lista_parametros().getTipo(), Ok_t.class)) {
			//p.setTipo(true);
		}

	}

	public void procesa(Una_lista_param p) {
		
		p.expresion().procesa(this);
		
		if (claseDistError(p.expresion().getTipo())) {
		
		//p.setTipo(Ok_t.class);		
	
		}
	}

	public void procesa(Asignacion p) {
		
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		if (es_designador(p.op1())){
			if (compatibles(p.op1().getTipo(), p.op2().getTipo())) {
				//p.setTipo(p.op1().getTipo());
			}
			else {
				//p.setTipo(Error_t.class);
			}
		}

			
	}

	public void procesa(Mayor p) {
		
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if ((claseDe(p.op1(), Int_t.class) || claseDe(p.op1(), Real_t.class)) && 
				(claseDe(p.op2(), Int_t.class) || claseDe(p.op2(), Real_t.class))) {}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)) {}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), String_t.class) && claseDe(p.op2(), String_t.class)) {}
			//p.setTipo(Bool_t.class);
		else {}
			//p.setTipo(Error_t.class);

		
		
	}

	public void procesa(Mayor_igual p) {
		
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if ((claseDe(p.op1(), Int_t.class) || claseDe(p.op1(), Real_t.class)) && 
				(claseDe(p.op2(), Int_t.class) || claseDe(p.op2(), Real_t.class))){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), String_t.class) && claseDe(p.op2(), String_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);		
	}

	public void procesa(Menor p) {
		
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if ((claseDe(p.op1(), Int_t.class) || claseDe(p.op1(), Real_t.class)) && 
				(claseDe(p.op2(), Int_t.class) || claseDe(p.op2(), Real_t.class))){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), String_t.class) && claseDe(p.op2(), String_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);		
	}

	public void procesa(Menor_igual p) {
		
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if ((claseDe(p.op1(), Int_t.class) || claseDe(p.op1(), Real_t.class)) && 
				(claseDe(p.op2(), Int_t.class) || claseDe(p.op2(), Real_t.class))){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), String_t.class) && claseDe(p.op2(), String_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);		
	}

	@Override
	public void procesa(Comparacion p) {
		
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if ((claseDe(p.op1(), Int_t.class) || claseDe(p.op1(), Real_t.class)) && 
				(claseDe(p.op2(), Int_t.class) || claseDe(p.op2(), Real_t.class))){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), String_t.class) && claseDe(p.op2(), String_t.class)){}
			//p.setTipo(Bool_t.class);
		else if ((claseDe(p.op1(), Tipo_puntero.class) || claseDe(p.op1(), Null_t.class)) && 
				(claseDe(p.op2(), Tipo_puntero.class) || claseDe(p.op2(), Null_t.class)) ){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);		
	}

	@Override
	public void procesa(Distinto p) {

		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if ((claseDe(p.op1(), Int_t.class) || claseDe(p.op1(), Real_t.class)) && 
				(claseDe(p.op2(), Int_t.class) || claseDe(p.op2(), Real_t.class))){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else if (claseDe(p.op1(), String_t.class) && claseDe(p.op2(), String_t.class)){}
			//p.setTipo(Bool_t.class);
		else if ((claseDe(p.op1(), Tipo_puntero.class) || claseDe(p.op1(), Null_t.class)) && 
				(claseDe(p.op2(), Tipo_puntero.class) || claseDe(p.op2(), Null_t.class)) ){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Suma p) {

		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Int_t.class) && claseDe(p.op2(), Int_t.class)){}
			//p.setTipo(Int_t.class);
		else if (claseDe(p.op1(), Real_t.class) && claseDe(p.op2(), Real_t.class)){}
			//p.setTipo(Real_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Resta p) {
		
		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Int_t.class) && claseDe(p.op2(), Int_t.class)){}
			//p.setTipo(Int_t.class);
		else if (claseDe(p.op1(), Real_t.class) && claseDe(p.op2(), Real_t.class)){}
			//p.setTipo(Real_t.class);
		else{}
			//p.setTipo(Error_t.class);		
	}

	@Override
	public void procesa(And p) {
		
		p.op1().procesa(this);
		p.op2().procesa(this);

		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		
		if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)) {}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Or p) {

		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Mult p) {

		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Int_t.class) && claseDe(p.op2(), Int_t.class)){}
			//p.setTipo(Int_t.class);
		else if ((claseDe(p.op1(), Real_t.class) && claseDe(p.op2(), Int_t.class))
				|| (claseDe(p.op1(), Int_t.class) && claseDe(p.op2(), Real_t.class))){}
			//p.setTipo(Real_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Div p) {

		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Int_t.class) && claseDe(p.op2(), Int_t.class)){}
			//p.setTipo(Int_t.class);
		else if ((claseDe(p.op1(), Real_t.class) && claseDe(p.op2(), Int_t.class))
				|| (claseDe(p.op1(), Int_t.class) && claseDe(p.op2(), Real_t.class))){}
			//p.setTipo(Real_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Mod p) {

		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Int_t.class) && claseDe(p.op2(), Int_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Not_unario p) {

		p.op1().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		
		if (claseDe(p.op1(), Int_t.class)){}
			//p.setTipo(Int_t.class);
		else if (claseDe(p.op1(), Real_t.class)){}
			//p.setTipo(Real_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Resta_unario p) {

		
		p.op1().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		
		if (claseDe(p.op1(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Indexacion p) {

		p.op1().procesa(this);
		p.op2().procesa(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Tipo_array.class) && claseDe(p.op2(), Int_t.class)){}
			//p.setTipo(/*T*/);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void procesa(Acc_reg p) {

		p.op1().procesa(this);
		
		Tipo t = ref(p.op1().getTipo());
		
		if (claseDe(p.op1(), Tipo_registro.class) /*&& existe(LParamReg, c)*/){}
			//p.setTipo(/*(LParamReg, c)*/);
		else{}
			//p.setTipo(Error_t.class);
	}

	public void procesa(Indireccion p) {

		p.op1().procesa(this);
		
		Tipo t = ref(p.op1().getTipo());
		
		if (claseDe(p.op1(), Tipo_puntero.class)){}
			//p.setTipo(/*T*/);
		else{}
			//p.setTipo(Error_t.class);
	}

	public void procesa(Lit_ent p) {
		//p.setTipo(Int_t.class);		
		
	}

	public void procesa(Lit_real p) {
		//p.setTipo(Real_t.class);		
		
	}

	public void procesa(True_e p) {
		//p.setTipo(Bool_t.class);		
		
	}

	public void procesa(False_e p) {
		//p.setTipo(Bool_t.class);		
		
	}

	public void procesa(Null_e p) {
		//p.setTipo(Null_t.class);		
		
	}

	public void procesa(Cadena p) {
		//p.setTipo(String_t.class);		
		
	}

	
	public void procesa(Iden p) {
		
		if (claseDe(p.getTipo(), Dec_var.class)) {
			//p.setTipo(Tipo.class);		

		}
		else if (claseDe(p.getTipo(), Param_form_ref.class)) {
			//p.setTipo(Tipo.class);		

		}
		else if  (claseDe(p.getTipo(), Param_form.class)) {
			//p.setTipo(Tipo.class);		

		}
		else {}
			//p.setTipo(Error_t.class);		

	}

	 ////////////////////////////////////METODOS ADICIONALES//////////////////////////////////////////////////////
	
    public boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 
    
    public boolean claseDistError(Object o) {
        return o.getClass() != Error.class;
    } 
        
	
	public boolean compatibles(Tipo t1, Tipo T2) { //TERMINAR; NO SÉ o?
		
		return false;
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
    
    
    public Tipo ref(Tipo t) {
		
		if (claseDe(t, Tipo_definido.class)) {
			//t.setVinculo(Dec_tipo.class);
			return ref(t);
		}
		else return t;
	}
    
	public Tipo ambos_ok(Tipo t1, Tipo t2) {
			
			if (claseDe(t1, Ok_t.class) && claseDe(t2, Ok_t.class)) {}
				//return Ok.class;
			else {}
				//return Error.class; ///error
			
			//NO, es para q no de error
			Tipo ret = null;
			return ret;
			
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
