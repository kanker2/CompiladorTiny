package Procesamientos;

import asint.SintaxisAbstractaTiny.*;


public class ProcesamientoCTipos implements IProcesamientoT{
	

	public void tipado(Prog_tiny p) {
		p.bloque().tipado(this);
		//p.setTipo(p.bloque().getTipo());  HEMOS COMETADO EL SETTIPO() PORQUE TODAVIA NO TENEMOS CLARO COMO HACERLO
			
	}

	public void tipado(Bloque p) {
		p.lista_opt_declaraciones().tipado(this);
		p.lista_opt_instrucciones().tipado(this);
		//p.setTipo(ambos_ok(p.lista_opt_declaraciones().getTipo(), p.lista_opt_declaraciones().getTipo()));
	}

	public void tipado(Si_lista_opt_decs p) {
		p.lista_declaraciones().tipado(this);
		//p.setTipo(p.lista_declaraciones().getTipo());
	}

	public void tipado(No_lista_opt_decs p) {
		//p.setTipo(Ok_t.class);		
	}

	public void tipado(Muchas_lista_decs p) {
		p.lista_declaraciones().tipado(this);
		p.declaracion().tipado(this);
		//p.setTipo(ambos_ok(p.lista_declaraciones().getTipo(), p.declaracion().getTipo()));

		
	}

	public void tipado(Una_lista_dec p) {
		p.declaracion().tipado(this);
		//p.setTipo(p.declaracion().getTipo());

	}

	public void tipado(Dec_var p) {
		p.tipo().tipado(this);
		//p.setTipo(p.tipo().getTipo());

	}
	

	public void tipado(Dec_tipo p) {
		p.tipo().tipado(this);
		//p.setTipo(p.tipo().getTipo());
		
	}

	public void tipado(Dec_proc p) {
		p.bloque().tipado(this);
		//p.setTipo(p.bloque().getTipo());
		
	}

	public void tipado(Tipo_array p) {
		p.tipo().tipado(this);
		//p.setTipo(p.tipo().getTipo());
		
	}

	public void tipado(Tipo_puntero p) {
		p.tipo().tipado(this);
		//p.setTipo(p.tipo().getTipo());
		
	}

	public void tipado(Int_t p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void tipado(Real_t p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void tipado(Bool_t p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void tipado(String_t p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void tipado(Tipo_registro p) {
		p.lista_parametros_registro().tipado(this);
		//p.setTipo(p.lista_parametros_registro().getTipo());		
	}

	public void tipado(Tipo_definido p) {
		if (claseDe(p.getTipo(), Dec_tipo.class)) {}
			//p.setTipo(Ok_t.class);		
		else {}
			//p.setTipo(Error_t.class);		

	}

	public void tipado(Muchas_lista_param_reg p) {
		p.lista_parametros_registro().tipado(this);
		p.parametro_registro().tipado(this);
		//p.setTipo(ambos_ok(p.lista_parametros_registro().getTipo(), p.parametro_registro().getTipo()));
	}

	public void tipado(Una_lista_param_reg p) {
		p.parametro_registro().tipado(this);
		//p.setTipo(p.parametro_registro().getTipo());
	}

	
	public void tipado(Param_reg p) {
		p.tipo().tipado(this);
		//p.setTipo(p.tipo().getTipo());
		
	}

	
	public void tipado(Si_lista_opt_inst p) {
		p.lista_instrucciones().tipado(this);
		//p.setTipo(p.lista_instrucciones().getTipo());
		
	}

	
	public void tipado(No_lista_opt_inst p) {
		//p.setTipo(Ok_t.class);		
	}

	public void tipado(Muchas_lista_inst p) {
		p.lista_instrucciones().tipado(this);
		p.instruccion().tipado(this);
		//p.setTipo(ambos_ok(p.lista_instrucciones().getTipo(), p.instruccion().getTipo()));		
	}

	public void tipado(Una_lista_inst p) {
		p.instruccion().tipado(this);
		//p.setTipo(p.instruccion().getTipo());		
	}

	public void tipado(Inst_eval p) {
		p.expresion().tipado(this);
		
		if (claseDistError(p.expresion().getTipo()) ) {}
			//p.setTipo(Ok_t.class);		
		else {}
			//	p.setTipo(Error_t.class);		


			
	}

	public void tipado(Inst_if_else p) {

		p.expresion().tipado(this);
		p.bloque1().tipado(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if (claseDe(p.bloque2(), Si_else.class)) { // si tiene segundo bloque
				p.bloque2().tipado(this);
				
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

	public void tipado(Si_else p) {
		p.bloque().tipado(this);
		//p.setTipo(p.bloque().getTipo());
		
	}

	public void tipado(No_else p) {
		//p.setTipo(Ok_t.class);		
		
	}

	public void tipado(Inst_while p) {
		
		p.expresion().tipado(this);
		p.bloque().tipado(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if (claseDe(t1, Bool_t.class) &&claseDe(p.bloque().getTipo(), Ok_t.class)) {
				//p.setTipo(Ok_t.class); 		
			}
			else {
				//p.setTipo(Error_t.class);		
			}
			
	}

	public void tipado(Inst_read p) {

		p.expresion().tipado(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if ((claseDe(t1, String_t.class) || claseDe(t1, Real_t.class) || claseDe(t1, Int_t.class)
					)&& es_designador(p.expresion())) {
				//p.setTipo(Ok_t.class);		
			}
			else { 
				//p.setTipo(Error_t.class);		
			}
	}

	public void tipado(Inst_write p) {
		
		p.expresion().tipado(this);
		
		Tipo t1 = ref(p.expresion().getTipo());

		
			if (claseDe(t1, String_t.class) || claseDe(t1, Real_t.class) || claseDe(t1, Int_t.class) || claseDe(t1, Bool_t.class)) {
				//p.setTipo(Ok_t.class);		
			}
			else {
				//p.setTipo(Error_t.class);		
			}
	
	
}

	public void tipado(Inst_nl p) {
		//p.setTipo(Ok_t.class);		
	}

	public void tipado(Inst_new p) {

		p.expresion().tipado(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if (claseDe(t1, Tipo_puntero.class)) {
				//p.setTipo(Ok_t.class);		
			}
			else {
				//p.setTipo(Error_t.class);		
	
			}
	}
	public void tipado(Inst_delete p) {
		p.expresion().tipado(this);
		
		Tipo t1 = ref(p.expresion().getTipo());
		
			if (claseDe(t1, Tipo_puntero.class)) {
				//p.setTipo(Ok_t.class);		
			}
			else {
				//p.setTipo(Error_t.class);		
	
			}
	}
	
	@Override
	public void tipado(Inst_call p) {
		p.parametro().tipado(this);
		
		//falta terminarla
			
	}

	public void tipado(Inst_comp p) {
		p.bloque().tipado(this);
		//p.setTipo(p.bloque().getTipo());
	}

	public void tipado(Si_lista_opt_param p) {
		p.lista_parametros().tipado(this);
		//p.setTipo(p.lista_parametros().getTipo());	
	}

	public void tipado(No_lista_opt_param p) {
		//p.setTipo(Ok_t.class);		
		
	}

	
	public void tipado(Muchas_lista_param p) {
		p.lista_parametros().tipado(this);
		p.expresion().tipado(this);
		
		if (claseDistError(p.expresion().getTipo()) && claseDe(p.lista_parametros().getTipo(), Ok_t.class)) {
			//p.setTipo(true);
		}

	}

	public void tipado(Una_lista_param p) {
		
		p.expresion().tipado(this);
		
		if (claseDistError(p.expresion().getTipo())) {
		
		//p.setTipo(Ok_t.class);		
	
		}
	}

	public void tipado(Asignacion p) {
		
		p.op1().tipado(this);
		p.op2().tipado(this);
		
		if (es_designador(p.op1())){
			if (compatibles(p.op1().getTipo(), p.op2().getTipo())) {
				//p.setTipo(p.op1().getTipo());
			}
			else {
				//p.setTipo(Error_t.class);
			}
		}

			
	}

	public void tipado(Mayor p) {
		
		p.op1().tipado(this);
		p.op2().tipado(this);
		
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

	public void tipado(Mayor_igual p) {
		
		p.op1().tipado(this);
		p.op2().tipado(this);
		
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

	public void tipado(Menor p) {
		
		p.op1().tipado(this);
		p.op2().tipado(this);
		
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

	public void tipado(Menor_igual p) {
		
		p.op1().tipado(this);
		p.op2().tipado(this);
		
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
	public void tipado(Comparacion p) {
		
		p.op1().tipado(this);
		p.op2().tipado(this);
		
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
	public void tipado(Distinto p) {

		p.op1().tipado(this);
		p.op2().tipado(this);
		
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
	public void tipado(Suma p) {

		p.op1().tipado(this);
		p.op2().tipado(this);
		
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
	public void tipado(Resta p) {
		
		p.op1().tipado(this);
		p.op2().tipado(this);
		
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
	public void tipado(And p) {
		
		p.op1().tipado(this);
		p.op2().tipado(this);

		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		
		if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)) {}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void tipado(Or p) {

		p.op1().tipado(this);
		p.op2().tipado(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Bool_t.class) && claseDe(p.op2(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void tipado(Mult p) {

		p.op1().tipado(this);
		p.op2().tipado(this);
		
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
	public void tipado(Div p) {

		p.op1().tipado(this);
		p.op2().tipado(this);
		
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
	public void tipado(Mod p) {

		p.op1().tipado(this);
		p.op2().tipado(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Int_t.class) && claseDe(p.op2(), Int_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void tipado(Not_unario p) {

		p.op1().tipado(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		
		if (claseDe(p.op1(), Int_t.class)){}
			//p.setTipo(Int_t.class);
		else if (claseDe(p.op1(), Real_t.class)){}
			//p.setTipo(Real_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void tipado(Resta_unario p) {

		
		p.op1().tipado(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		
		if (claseDe(p.op1(), Bool_t.class)){}
			//p.setTipo(Bool_t.class);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void tipado(Indexacion p) {

		p.op1().tipado(this);
		p.op2().tipado(this);
		
		Tipo t1 = ref(p.op1().getTipo());
		Tipo t2 = ref(p.op2().getTipo());
		
		if (claseDe(p.op1(), Tipo_array.class) && claseDe(p.op2(), Int_t.class)){}
			//p.setTipo(/*T*/);
		else{}
			//p.setTipo(Error_t.class);
	}

	@Override
	public void tipado(Acc_reg p) {

		p.op1().tipado(this);
		
		Tipo t = ref(p.op1().getTipo());
		
		if (claseDe(p.op1(), Tipo_registro.class) /*&& existe(LParamReg, c)*/){}
			//p.setTipo(/*(LParamReg, c)*/);
		else{}
			//p.setTipo(Error_t.class);
	}

	public void tipado(Indireccion p) {

		p.op1().tipado(this);
		
		Tipo t = ref(p.op1().getTipo());
		
		if (claseDe(p.op1(), Tipo_puntero.class)){}
			//p.setTipo(/*T*/);
		else{}
			//p.setTipo(Error_t.class);
	}

	public void tipado(Lit_ent p) {
		//p.setTipo(Int_t.class);		
		
	}

	public void tipado(Lit_real p) {
		//p.setTipo(Real_t.class);		
		
	}

	public void tipado(True_e p) {
		//p.setTipo(Bool_t.class);		
		
	}

	public void tipado(False_e p) {
		//p.setTipo(Bool_t.class);		
		
	}

	public void tipado(Null_e p) {
		//p.setTipo(Null_t.class);		
		
	}

	public void tipado(Cadena p) {
		//p.setTipo(String_t.class);		
		
	}

	
	public void tipado(Iden p) {
		
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
