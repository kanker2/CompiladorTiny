package Procesamientos;

import asint.SintaxisAbstractaTiny.Acc_reg;
import asint.SintaxisAbstractaTiny.And;
import asint.SintaxisAbstractaTiny.Asignacion;
import asint.SintaxisAbstractaTiny.Bloque;
import asint.SintaxisAbstractaTiny.Bool_t;
import asint.SintaxisAbstractaTiny.Cadena;
import asint.SintaxisAbstractaTiny.Comparacion;
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

public class ProcesamientoAEspacio implements IProcesamientoAM{

	int dir = 0;
	int nivel=0;
	@Override
	public void asig_espacio(Prog_tiny p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Bloque p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Si_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(No_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Una_lista_dec p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Dec_var p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Dec_tipo p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Dec_proc p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(No_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Una_lista_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Param_form_ref p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Tipo_array p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Tipo_puntero p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Int_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Real_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Bool_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(String_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Tipo_registro p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Tipo_definido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Una_lista_dec p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Dec_var p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Dec_tipo p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Dec_proc p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(No_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Una_lista_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Param_form_ref p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Tipo_array p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Tipo_puntero p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Int_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Real_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Bool_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(String_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Tipo_registro p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Tipo_definido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio2(Param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Si_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(No_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Muchas_lista_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Una_lista_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_eval p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_if_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Si_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(No_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_while p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_read p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_write p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_nl p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_new p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_delete p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_call p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_comp p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Si_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(No_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Muchas_lista_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Una_lista_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Asignacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Mayor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Mayor_igual p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Menor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Menor_igual p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Comparacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Distinto p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Suma p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Resta p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(And p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Or p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Mult p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Div p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Mod p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Not_unario p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Resta_unario p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Indexacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Acc_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Indireccion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Lit_ent p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Lit_real p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(True_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(False_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Null_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Cadena p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Iden p) {
		// TODO Auto-generated method stub
		
	}

}
