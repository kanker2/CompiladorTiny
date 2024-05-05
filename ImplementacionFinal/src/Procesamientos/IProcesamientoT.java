package Procesamientos;

import asint.SintaxisAbstractaTiny.*;


public interface IProcesamientoT{
	
	void tipado(Prog_tiny p);
	void tipado(Bloque p);
	void tipado(Si_lista_opt_decs p);
	void tipado(No_lista_opt_decs p);
	void tipado(Muchas_lista_decs p);
	void tipado(Una_lista_dec p);
	void tipado(Dec_var p);
	void tipado(Dec_tipo p);
	void tipado(Dec_proc p);
	//void tipado(Si_lista_opt_param_form p);
	//void tipado(No_lista_opt_param_form p);
	//void tipado(Muchas_lista_param_form p);
	// tipado(Una_lista_param_form p);
	//void tipado(Param_form_ref p);
	//void tipado(Param_form p);
	void tipado(Tipo_array p);
	void tipado(Tipo_puntero p);
	void tipado(Int_t p);
	void tipado(Real_t p);
	void tipado(Bool_t p);
	void tipado(String_t p);
	void tipado(Tipo_registro p);
	void tipado(Tipo_definido p);
	void tipado(Muchas_lista_param_reg p);
	void tipado(Una_lista_param_reg p);
	void tipado(Param_reg p);
	void tipado(Si_lista_opt_inst p);
	void tipado(No_lista_opt_inst p);
	void tipado(Muchas_lista_inst p);
	void tipado(Una_lista_inst p);
	void tipado(Inst_eval p);
	void tipado(Inst_if_else p);
	void tipado(Si_else p);
	void tipado(No_else p);
	void tipado(Inst_while p);
	void tipado(Inst_read p);
	void tipado(Inst_write p);
	void tipado(Inst_nl p);
	void tipado(Inst_new p);
	void tipado(Inst_delete p);
	void tipado(Inst_call p);
	void tipado(Inst_comp p);
	void tipado(Si_lista_opt_param p);
	void tipado(No_lista_opt_param p);
	void tipado(Muchas_lista_param p);
	void tipado(Una_lista_param p);
	void tipado(Asignacion p);
	void tipado(Mayor p);
	void tipado(Mayor_igual p);
	void tipado(Menor p);
	void tipado(Menor_igual p);
	void tipado(Comparacion p);
	void tipado(Distinto p);
	void tipado(Suma p);
	void tipado(Resta p);
	void tipado(And p);
	void tipado(Or p);
	void tipado(Mult p);
	void tipado(Div p);
	void tipado(Mod p);
	void tipado(Not_unario p);
	void tipado(Resta_unario p);
	void tipado(Indexacion p);
	void tipado(Acc_reg p);
	void tipado(Indireccion p);
	void tipado(Lit_ent p);
	void tipado(Lit_real p);
	void tipado(True_e p);
	void tipado(False_e p);
	void tipado(Null_e p);
	void tipado(Cadena p);
	void tipado(Iden p);
	boolean compatibles(Tipo t1,Tipo T2);
	boolean compatibles_registro(LParamReg l1, LParamReg l2);
	boolean compatibles_proc(LParam lp, LParamForm lpf);
	boolean es_designador(Exp e);
	boolean ambos_ok(Tipo T0,Tipo T1);
	
	
}
