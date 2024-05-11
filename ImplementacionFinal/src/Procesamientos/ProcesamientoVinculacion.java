package Procesamientos;

import java.util.Map;
import java.util.HashMap;

import asint.SintaxisAbstractaTiny.*;


//Se pretende seguir el patron visitante
public class ProcesamientoVinculacion implements IProcesamientoV{
	
	Map<String,Dec> ts;
	
	public void vincula(Prog_tiny p) {
		// TODO Auto-generated method stub
		ts=new HashMap<String,Dec>();//?????
		p.bloque().vincula(this);
	}

	
	public void vincula(Bloque p) {
		// TODO Auto-generated method stub
		//abreAmbito(ts);
		p.lista_opt_declaraciones().vincula(this);
		p.lista_opt_instrucciones().vincula(this);
		//cierraAmbito(ts);
	}

	
	public void vincula(Si_lista_opt_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().vincula1(this);
		p.lista_declaraciones().vincula2(this);
	}

	
	public void vincula(No_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}

	
	public void vincula1(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().vincula1(this);
		p.declaracion().vincula1(this);
	}

	
	public void vincula1(Una_lista_dec p) {
		// TODO Auto-generated method stub
		p.declaracion().vincula1(this);
	}

	
	public void vincula1(Dec_var p) {
		// TODO Auto-generated method stub
		/*p.tipo().vincula1(this);
		if(contiene(ts,p.cadena())) {
			error;
		}
		else inserta(ts, p.cadena(),p.tipo()??);*/
	}

	
	public void vincula1(Dec_tipo p) {
		// TODO Auto-generated method stub
		p.tipo().vincula1(this);
		/*if(contiene(ts,p.cadena())) {
			error;
		}
		else inserta(ts, p.cadena(),Dec);*/
	}

	
	public void vincula1(Dec_proc p) {
		// TODO Auto-generated method stub
		/*if contiene(ts, Id) {
		error
		}
		else
		inserta(ts, Id)
		end if
			abreAmbito(ts)*/
		p.lista_opt_parametros_formales().vincula1(this);
		p.bloque().vincula(this);	
			//cierraAmbito(ts)
	}

	
	public void vincula1(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		p.lista_parametros_formales().vincula1(this);
	}

	
	public void vincula1(No_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	
	public void vincula1(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub
		p.lista_parametros_formales().vincula1(this);
		p.parametro_formal().vincula1(this);
	}

	
	public void vincula1(Una_lista_param_form p) {
		// TODO Auto-generated method stub
		p.parametro_formal().vincula1(this);
	}

	
	public void vincula1(Param_form_ref p) {
		// TODO Auto-generated method stub
		p.tipo().vincula1(this);
		/*
		 if contiene(ts, Id) then 
			error
		else
			inserta(ts, Id, $)
		end if

		 * */
	}

	
	public void vincula1(Param_form p) {
		// TODO Auto-generated method stub
		p.tipo().vincula1(this);
		/*
		 if contiene(ts, Id) then 
			error
		else
			inserta(ts, Id, $)
		end if

		 * */
	}

	
	public void vincula1(Tipo_array p) {
		// TODO Auto-generated method stub
		p.tipo().vincula1(this);
	}

	
	public void vincula1(Tipo_puntero p) {
		// TODO Auto-generated method stub
		/*if (p.tipo() != Dec_tipo.class) {
				p.tipo().vincula1(this); 
		} */

	}

	
	public void vincula1(Int_t p) {}

	
	public void vincula1(Real_t p) {}

	
	public void vincula1(Bool_t p) {}

	
	public void vincula1(String_t p) {}

	public void vincula1(Tipo_registro p) {
		// TODO Auto-generated method stub
		p.lista_parametros_registro().vincula1(this);
	}

	
	public void vincula1(Tipo_definido p) {
		// TODO Auto-generated method stub
		p.setVinculo(vinculoDe(ts,p.cadena()));
		/*if(p.getVinculo()!=Dec_tipo) {
			error
		}*/
	}

	
	public void vincula1(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		p.lista_parametros_registro().vincula1(this);
		p.parametro_registro().vincula1(this);
	}

	
	public void vincula1(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		p.parametro_registro().vincula1(this);
	}

	
	public void vincula1(Param_reg p) {
		// TODO Auto-generated method stub
		p.tipo().vincula1(this);
	}

	
	public void vincula2(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().vincula2(this);
		p.declaracion().vincula2(this);
	}

	
	public void vincula2(Una_lista_dec p) {
		// TODO Auto-generated method stub
		p.declaracion().vincula2(this);
	}

	
	public void vincula2(Dec_var p) {
		// TODO Auto-generated method stub
		p.tipo().vincula2(this);

	}

	
	public void vincula2(Dec_tipo p) {
		// TODO Auto-generated method stub
		p.tipo().vincula2(this);

	}

	
	public void vincula2(Dec_proc p) {
		// TODO Auto-generated method stub
		p.lista_opt_parametros_formales().vincula2(this);

	}

	
	public void vincula2(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		p.lista_parametros_formales().vincula2(this);

	}

	
	public void vincula2(No_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	
	public void vincula2(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub
		p.lista_parametros_formales().vincula2(this);
		p.parametro_formal().vincula2(this);

	}

	
	public void vincula2(Una_lista_param_form p) {
		// TODO Auto-generated method stub
		p.parametro_formal().vincula2(this);

	}

	
	public void vincula2(Param_form_ref p) {
		// TODO Auto-generated method stub
		p.tipo().vincula2(this);

	}

	
	public void vincula2(Param_form p) {
		// TODO Auto-generated method stub
		p.tipo().vincula2(this);
	}

	
	public void vincula2(Tipo_array p) {
		// TODO Auto-generated method stub
		p.tipo().vincula2(this);

	}

	
	public void vincula2(Tipo_puntero p) {
		// TODO Auto-generated method stub
		/*if(p.tipo()==p.) {??????????
			p.tipo().setVinculo(vinculoDe(ts,p.tipo().cadena()))
		}*/
	}

	
	public void vincula2(Int_t p) {}

	
	public void vincula2(Real_t p) {}

	
	public void vincula2(Bool_t p) {}

	
	public void vincula2(String_t p) {}

	
	public void vincula2(Tipo_registro p) {
		// TODO Auto-generated method stub
		p.lista_parametros_registro().vincula2(this);
	}

	
	public void vincula2(Tipo_definido p) {}

	
	public void vincula2(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		p.lista_parametros_registro().vincula2(this);
		p.parametro_registro().vincula2(this);

	}

	
	public void vincula2(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		p.parametro_registro().vincula2(this);
	}

	
	public void vincula2(Param_reg p) {
		// TODO Auto-generated method stub
		p.tipo().vincula2(this);
	}

	
	public void vincula(Si_lista_opt_inst p) {
		// TODO Auto-generated method stub
		p.lista_instrucciones().vincula(this);
	}

	
	public void vincula(No_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	
	public void vincula(Muchas_lista_inst p) {
		// TODO Auto-generated method stub
		p.lista_instrucciones().vincula(this);
		p.instruccion().vincula(this);
	}

	
	public void vincula(Una_lista_inst p) {
		// TODO Auto-generated method stub
		p.instruccion().vincula(this);
	}

	
	public void vincula(Inst_eval p) {
		// TODO Auto-generated method stub
		p.expresion().vincula(this);
	}

	
	public void vincula(Inst_if_else p) {
		// TODO Auto-generated method stub
		p.expresion().vincula(this);
		p.bloque1().vincula(this);
		p.bloque2().vincula(this);
	}

	
	public void vincula(Si_else p) {
		// TODO Auto-generated method stub
		p.bloque().vincula(this);
	}

	
	public void vincula(No_else p) {
		// TODO Auto-generated method stub
		
	}

	
	public void vincula(Inst_while p) {
		// TODO Auto-generated method stub
		//ambitos?????????????
		p.expresion().vincula(this);
		p.bloque().vincula(this);
	}

	
	public void vincula(Inst_read p) {
		// TODO Auto-generated method stub
		p.expresion().vincula(this);

	}

	
	public void vincula(Inst_write p) {
		// TODO Auto-generated method stub
		p.expresion().vincula(this);

	}

	
	public void vincula(Inst_nl p) {}

	
	public void vincula(Inst_new p) {
		// TODO Auto-generated method stub
		p.expresion().vincula(this);
	}

	
	public void vincula(Inst_delete p) {
		// TODO Auto-generated method stub
		p.expresion().vincula(this);
	}

	
	public void vincula(Inst_call p) {
		// TODO Auto-generated method stub
		if(contiene(ts,p.cadena())) {
			p.setVinculo(vinculoDe(ts,p.cadena()));
			p.lista_opt_parametros().vincula(this);
		}
		else {}//error}
	}

	
	public void vincula(Inst_comp p) {
		// TODO Auto-generated method stub
		p.bloque().vincula(this);
	}

	
	public void vincula(Si_lista_opt_param p) {
		// TODO Auto-generated method stub
		p.lista_parametros().vincula(this);
	}

	
	public void vincula(No_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	
	public void vincula(Muchas_lista_param p) {
		// TODO Auto-generated method stub
		p.lista_parametros().vincula(this);
		p.expresion().vincula(this);
	}

	
	public void vincula(Una_lista_param p) {
		// TODO Auto-generated method stub
		p.expresion().vincula(this);

	}

	
	public void vincula(Asignacion p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Mayor p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Mayor_igual p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Menor p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Menor_igual p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Comparacion p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Distinto p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Suma p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Resta p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(And p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Or p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Mult p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Div p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Mod p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Not_unario p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Resta_unario p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);
		p.op2().vincula(this);
	}

	
	public void vincula(Indexacion p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);//??????????
	}

	
	public void vincula(Acc_reg p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);//????????
	}

	
	public void vincula(Indireccion p) {
		// TODO Auto-generated method stub
		p.op1().vincula(this);//???????????

	}

	
	public void vincula(Lit_ent p) {}

	
	public void vincula(Lit_real p) {}

	
	public void vincula(True_e p) {}

	
	public void vincula(False_e p) {}

	
	public void vincula(Null_e p) {}

	
	public void vincula(Cadena p) {}

	
	public void vincula(Iden p) {
		// TODO Auto-generated method stub
		p.setVinculo(vinculoDe(ts, p.cadena()));
		if(p.getVinculo()==null) {
			//errorrrrrr
		}
	}

	
	public void abreAmbito(Map<String, Dec> ts) { //TODAVIA NO TENEMOS CLARO COMO HACERLA
		// TODO Auto-generated method stub
		
	}

	
	public void cierraAmbito(Map<String, Dec> ts) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean contiene(Map<String, Dec> ts, String id) {
		// TODO Auto-generated method stub
		if(ts.get(id)==null)return false;
		return true;
	}

	
	public void inserta(Map<String, Dec> ts, String id, Dec dec) {
		// TODO Auto-generated method stub
		ts.put(id, dec);
	}

	
	public Dec vinculoDe(Map<String, Dec> ts, String id) {
		// TODO Auto-generated method stub
		return ts.get(id);
	}

}
