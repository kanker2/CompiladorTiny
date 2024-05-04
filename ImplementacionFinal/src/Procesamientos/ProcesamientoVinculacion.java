package Procesamientos;

import java.util.Map;

import asint.SintaxisAbstractaTiny.Acc_reg;
import asint.SintaxisAbstractaTiny.And;
import asint.SintaxisAbstractaTiny.Asignacion;
import asint.SintaxisAbstractaTiny.Bloque;
import asint.SintaxisAbstractaTiny.Bool_t;
import asint.SintaxisAbstractaTiny.Cadena;
import asint.SintaxisAbstractaTiny.Comparacion;
import asint.SintaxisAbstractaTiny.Dec;
import asint.SintaxisAbstractaTiny.Dec_proc;
import asint.SintaxisAbstractaTiny.Dec_tipo;
import asint.SintaxisAbstractaTiny.Dec_var;
import asint.SintaxisAbstractaTiny.Distinto;
import asint.SintaxisAbstractaTiny.Div;
import asint.SintaxisAbstractaTiny.False_e;
import asint.SintaxisAbstractaTiny.Iden;
import asint.SintaxisAbstractaTiny.Indexacion;
import asint.SintaxisAbstractaTiny.Indireccion;
import asint.SintaxisAbstractaTiny.Inst_call;
import asint.SintaxisAbstractaTiny.Inst_comp;
import asint.SintaxisAbstractaTiny.Inst_delete;
import asint.SintaxisAbstractaTiny.Inst_eval;
import asint.SintaxisAbstractaTiny.Inst_if_else;
import asint.SintaxisAbstractaTiny.Inst_new;
import asint.SintaxisAbstractaTiny.Inst_nl;
import asint.SintaxisAbstractaTiny.Inst_read;
import asint.SintaxisAbstractaTiny.Inst_while;
import asint.SintaxisAbstractaTiny.Inst_write;
import asint.SintaxisAbstractaTiny.Int_t;
import asint.SintaxisAbstractaTiny.Lit_ent;
import asint.SintaxisAbstractaTiny.Lit_real;
import asint.SintaxisAbstractaTiny.Mayor;
import asint.SintaxisAbstractaTiny.Mayor_igual;
import asint.SintaxisAbstractaTiny.Menor;
import asint.SintaxisAbstractaTiny.Menor_igual;
import asint.SintaxisAbstractaTiny.Mod;
import asint.SintaxisAbstractaTiny.Muchas_lista_decs;
import asint.SintaxisAbstractaTiny.Muchas_lista_inst;
import asint.SintaxisAbstractaTiny.Muchas_lista_param;
import asint.SintaxisAbstractaTiny.Muchas_lista_param_form;
import asint.SintaxisAbstractaTiny.Muchas_lista_param_reg;
import asint.SintaxisAbstractaTiny.Mult;
import asint.SintaxisAbstractaTiny.No_else;
import asint.SintaxisAbstractaTiny.No_lista_opt_decs;
import asint.SintaxisAbstractaTiny.No_lista_opt_inst;
import asint.SintaxisAbstractaTiny.No_lista_opt_param;
import asint.SintaxisAbstractaTiny.No_lista_opt_param_form;
import asint.SintaxisAbstractaTiny.Not_unario;
import asint.SintaxisAbstractaTiny.Null_e;
import asint.SintaxisAbstractaTiny.Or;
import asint.SintaxisAbstractaTiny.Param_form;
import asint.SintaxisAbstractaTiny.Param_form_ref;
import asint.SintaxisAbstractaTiny.Param_reg;
import asint.SintaxisAbstractaTiny.Prog_tiny;
import asint.SintaxisAbstractaTiny.Real_t;
import asint.SintaxisAbstractaTiny.Resta;
import asint.SintaxisAbstractaTiny.Resta_unario;
import asint.SintaxisAbstractaTiny.Si_else;
import asint.SintaxisAbstractaTiny.Si_lista_opt_decs;
import asint.SintaxisAbstractaTiny.Si_lista_opt_inst;
import asint.SintaxisAbstractaTiny.Si_lista_opt_param;
import asint.SintaxisAbstractaTiny.Si_lista_opt_param_form;
import asint.SintaxisAbstractaTiny.String_t;
import asint.SintaxisAbstractaTiny.Suma;
import asint.SintaxisAbstractaTiny.Tipo_array;
import asint.SintaxisAbstractaTiny.Tipo_definido;
import asint.SintaxisAbstractaTiny.Tipo_puntero;
import asint.SintaxisAbstractaTiny.Tipo_registro;
import asint.SintaxisAbstractaTiny.True_e;
import asint.SintaxisAbstractaTiny.Una_lista_dec;
import asint.SintaxisAbstractaTiny.Una_lista_inst;
import asint.SintaxisAbstractaTiny.Una_lista_param;
import asint.SintaxisAbstractaTiny.Una_lista_param_form;
import asint.SintaxisAbstractaTiny.Una_lista_param_reg;

//Se pretende seguir el patron visitante
public class ProcesamientoVinculacion implements IProcesamientoV{
	
	//Map<String,Nodo> ts;???????????
	@Override
	public void vincula(Prog_tiny p) {
		// TODO Auto-generated method stub
		//ts=new Map();//?????'
		p.bloque().vincula(this);
	}

	@Override
	public void vincula(Bloque p) {
		// TODO Auto-generated method stub
		//abreAmbito(ts);
		p.lista_opt_declaraciones().vincula(this);
		p.lista_opt_instrucciones().vincula(this);
		//cierraAmbito(ts);
	}

	@Override
	public void vincula(Si_lista_opt_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().vincula1(this);
		p.lista_declaraciones().vincula2(this);
	}

	@Override
	public void vincula(No_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().vincula1(this);
		p.declaracion().vincula1(this);
	}

	@Override
	public void vincula1(Una_lista_dec p) {
		// TODO Auto-generated method stub
		p.declaracion().vincula1(this);
	}

	@Override
	public void vincula1(Dec_var p) {
		// TODO Auto-generated method stub
		/*p.tipo().vincula1(this);
		if(contiene(ts,p.cadena())) {
			error;
		}
		else inserta(ts, p.cadena(),p.tipo()??);*/
	}

	@Override
	public void vincula1(Dec_tipo p) {
		// TODO Auto-generated method stub
		/*p.tipo().vincula1(this);
		if(contiene(ts,p.cadena())) {
			error;
		}
		else inserta(ts, p.cadena(),p.tipo()??)*/
	}

	@Override
	public void vincula1(Dec_proc p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(No_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Una_lista_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Param_form_ref p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Tipo_array p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Tipo_puntero p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Int_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Real_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Bool_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(String_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Tipo_registro p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Tipo_definido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula1(Param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Una_lista_dec p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Dec_var p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Dec_tipo p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Dec_proc p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(No_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Una_lista_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Param_form_ref p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Tipo_array p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Tipo_puntero p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Int_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Real_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Bool_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(String_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Tipo_registro p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Tipo_definido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula2(Param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Si_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(No_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Muchas_lista_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Una_lista_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_eval p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_if_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Si_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(No_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_while p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_read p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_write p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_nl p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_new p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_delete p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_call p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Inst_comp p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Si_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(No_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Muchas_lista_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Una_lista_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Asignacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Mayor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Mayor_igual p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Menor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Menor_igual p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Comparacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Distinto p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Suma p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Resta p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(And p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Or p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Mult p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Div p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Mod p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Not_unario p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Resta_unario p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Indexacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Acc_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Indireccion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Lit_ent p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Lit_real p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(True_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(False_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Null_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Cadena p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vincula(Iden p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void abreAmbito(Map ts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cierraAmbito(Map ts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contiene(Map ts, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserta(Map ts, String id, Dec dec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dec vinculo(Map ts, String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
