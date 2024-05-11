package Procesamientos;

import asint.SintaxisAbstractaTiny.*;


public interface IProcesamientoE {

	void etiquetado(Prog_tiny p);
	void etiquetado(Bloque p);
	
	void etiquetado(Tipo_array p);
	void etiquetado(Tipo_puntero p);
	void etiquetado(Int_t p);
	void etiquetado(Real_t p);
	void etiquetado(Bool_t p);
	void etiquetado(String_t p);
	void etiquetado(Tipo_registro p);
	void etiquetado(Tipo_definido p);
	void etiquetado(Muchas_lista_param_reg p);
	void etiquetado(Una_lista_param_reg p);
	void etiquetado(Param_reg p);
	
	void etiquetado(Si_lista_opt_inst p);
	void etiquetado(No_lista_opt_inst p);
	void etiquetado(Muchas_lista_inst p);
	void etiquetado(Una_lista_inst p);
	void etiquetado(Inst_eval p);
	void etiquetado(Inst_if_else p);
	void etiquetado(Si_else p);
	void etiquetado(No_else p);
	void etiquetado(Inst_while p);
	void etiquetado(Inst_read p);
	void etiquetado(Inst_write p);
	void etiquetado(Inst_nl p);
	void etiquetado(Inst_new p);
	void etiquetado(Inst_delete p);
	void etiquetado(Inst_call p);
	void etiquetado(Inst_comp p);
	void etiquetado(Si_lista_opt_param p);
	void etiquetado(No_lista_opt_param p);
	void etiquetado(Muchas_lista_param p);
	void etiquetado(Una_lista_param p);
	void etiquetado(Asignacion p);
	void etiquetado(Mayor p);
	void etiquetado(Mayor_igual p);
	void etiquetado(Menor p);
	void etiquetado(Menor_igual p);
	void etiquetado(Comparacion p);
	void etiquetado(Distinto p);
	void etiquetado(Suma p);
	void etiquetado(Resta p);
	void etiquetado(And p);
	void etiquetado(Or p);
	void etiquetado(Mult p);
	void etiquetado(Div p);
	void etiquetado(Mod p);
	void etiquetado(Not_unario p);
	void etiquetado(Resta_unario p);
	void etiquetado(Indexacion p);
	void etiquetado(Acc_reg p);
	void etiquetado(Indireccion p);
	void etiquetado(Lit_ent p);
	void etiquetado(Lit_real p);
	void etiquetado(True_e p);
	void etiquetado(False_e p);
	void etiquetado(Null_e p);
	void etiquetado(Cadena p);
	void etiquetado(Iden p);
		
}
