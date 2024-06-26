package visitante;

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

public interface Procesamiento {
	void procesa(Prog_tiny p);
	void procesa(Bloque p);
	void procesa(Si_lista_opt_decs p);
	void procesa(No_lista_opt_decs p);
	void procesa(Muchas_lista_decs p);
	void procesa(Una_lista_dec p);
	void procesa(Dec_var p);
	void procesa(Dec_tipo p);
	void procesa(Dec_proc p);
	void procesa(Si_lista_opt_param_form p);
	void procesa(No_lista_opt_param_form p);
	void procesa(Muchas_lista_param_form p);
	void procesa(Una_lista_param_form p);
	void procesa(Param_form_ref p);
	void procesa(Param_form p);
	void procesa(Tipo_array p);
	void procesa(Tipo_puntero p);
	void procesa(Int_t p);
	void procesa(Real_t p);
	void procesa(Bool_t p);
	void procesa(String_t p);
	void procesa(Tipo_registro p);
	void procesa(Tipo_definido p);
	void procesa(Muchas_lista_param_reg p);
	void procesa(Una_lista_param_reg p);
	void procesa(Param_reg p);
	void procesa(Si_lista_opt_inst p);
	void procesa(No_lista_opt_inst p);
	void procesa(Muchas_lista_inst p);
	void procesa(Una_lista_inst p);
	void procesa(Inst_eval p);
	void procesa(Inst_if_else p);
	void procesa(Si_else p);
	void procesa(No_else p);
	void procesa(Inst_while p);
	void procesa(Inst_read p);
	void procesa(Inst_write p);
	void procesa(Inst_nl p);
	void procesa(Inst_new p);
	void procesa(Inst_delete p);
	void procesa(Inst_call p);
	void procesa(Inst_comp p);
	void procesa(Asignacion p);
	void procesa(Mayor p);
	void procesa(Mayor_igual p);
	void procesa(Menor p);
	void procesa(Menor_igual p);
	void procesa(Comparacion p);
	void procesa(Distinto p);
	void procesa(Suma p);
	void procesa(Resta p);
	void procesa(And p);
	void procesa(Or p);
	void procesa(Mult p);
	void procesa(Div p);
	void procesa(Mod p);
	void procesa(Not_unario p);
	void procesa(Resta_unario p);
	void procesa(Indexacion p);
	void procesa(Acc_reg p);
	void procesa(Indireccion p);
	void procesa(Lit_ent p);
	void procesa(Lit_real p);
	void procesa(True_e p);
	void procesa(False_e p);
	void procesa(Null_e p);
	void procesa(Cadena p);
	void procesa(Iden p);
	void procesa(Si_lista_opt_param p);
	void procesa(No_lista_opt_param p);
	void procesa(Muchas_lista_param p);
	void procesa(Una_lista_param p);

	
	
	
}
