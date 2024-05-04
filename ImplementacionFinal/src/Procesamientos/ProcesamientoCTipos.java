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
import asint.SintaxisAbstractaTiny.Exp;
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
import asint.SintaxisAbstractaTiny.LParam;
import asint.SintaxisAbstractaTiny.LParamForm;
import asint.SintaxisAbstractaTiny.LParamReg;
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
import asint.SintaxisAbstractaTiny.Muchas_lista_param_reg;
import asint.SintaxisAbstractaTiny.Mult;
import asint.SintaxisAbstractaTiny.No_else;
import asint.SintaxisAbstractaTiny.No_lista_opt_decs;
import asint.SintaxisAbstractaTiny.No_lista_opt_inst;
import asint.SintaxisAbstractaTiny.No_lista_opt_param;
import asint.SintaxisAbstractaTiny.Not_unario;
import asint.SintaxisAbstractaTiny.Null_e;
import asint.SintaxisAbstractaTiny.Or;
import asint.SintaxisAbstractaTiny.Param_reg;
import asint.SintaxisAbstractaTiny.Prog_tiny;
import asint.SintaxisAbstractaTiny.Real_t;
import asint.SintaxisAbstractaTiny.Resta;
import asint.SintaxisAbstractaTiny.Resta_unario;
import asint.SintaxisAbstractaTiny.Si_else;
import asint.SintaxisAbstractaTiny.Si_lista_opt_decs;
import asint.SintaxisAbstractaTiny.Si_lista_opt_inst;
import asint.SintaxisAbstractaTiny.Si_lista_opt_param;
import asint.SintaxisAbstractaTiny.String_t;
import asint.SintaxisAbstractaTiny.Suma;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Tipo_array;
import asint.SintaxisAbstractaTiny.Tipo_definido;
import asint.SintaxisAbstractaTiny.Tipo_puntero;
import asint.SintaxisAbstractaTiny.Tipo_registro;
import asint.SintaxisAbstractaTiny.True_e;
import asint.SintaxisAbstractaTiny.Una_lista_dec;
import asint.SintaxisAbstractaTiny.Una_lista_inst;
import asint.SintaxisAbstractaTiny.Una_lista_param;
import asint.SintaxisAbstractaTiny.Una_lista_param_reg;

public class ProcesamientoCTipos implements IProcesamientoT{

	@Override
	public void tipado(Prog_tiny p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Bloque p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Si_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(No_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Una_lista_dec p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Dec_var p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Dec_tipo p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Dec_proc p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Tipo_array p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Tipo_puntero p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Int_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Real_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Bool_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(String_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Tipo_registro p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Tipo_definido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Si_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(No_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Muchas_lista_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Una_lista_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_eval p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_if_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Si_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(No_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_while p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_read p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_write p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_nl p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_new p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_delete p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_call p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Inst_comp p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Si_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(No_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Muchas_lista_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Una_lista_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Asignacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Mayor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Mayor_igual p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Menor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Menor_igual p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Comparacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Distinto p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Suma p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Resta p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(And p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Or p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Mult p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Div p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Mod p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Not_unario p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Resta_unario p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Indexacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Acc_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Indireccion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Lit_ent p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Lit_real p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(True_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(False_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Null_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Cadena p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipado(Iden p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean compatibles(Tipo t1, Tipo T2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean compatibles_registro(LParamReg l1, LParamReg l2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean compatibles_proc(LParam lp, LParamForm lpf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean es_designador(Exp e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ambos_ok(Tipo T0, Tipo T1) {
		// TODO Auto-generated method stub
		return false;
	}

}
