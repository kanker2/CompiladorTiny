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

public interface IProcesamientoV {
	void vincula(Prog_tiny p);
	void vincula(Bloque p);
	void vincula(Si_lista_opt_decs p);
	void vincula(No_lista_opt_decs p);
	//Primera Vuelta
	void vincula1(Muchas_lista_decs p);
	void vincula1(Una_lista_dec p);
	void vincula1(Dec_var p);
	void vincula1(Dec_tipo p);
	void vincula1(Dec_proc p);
	void vincula1(Si_lista_opt_param_form p);
	void vincula1(No_lista_opt_param_form p);
	void vincula1(Muchas_lista_param_form p);
	void vincula1(Una_lista_param_form p);
	void vincula1(Param_form_ref p);
	void vincula1(Param_form p);
	void vincula1(Tipo_array p);
	void vincula1(Tipo_puntero p);
	void vincula1(Int_t p);
	void vincula1(Real_t p);
	void vincula1(Bool_t p);
	void vincula1(String_t p);
	void vincula1(Tipo_registro p);
	void vincula1(Tipo_definido p);
	void vincula1(Muchas_lista_param_reg p);
	void vincula1(Una_lista_param_reg p);
	void vincula1(Param_reg p);
	////Segunda Vuelta
	void vincula2(Muchas_lista_decs p);
	void vincula2(Una_lista_dec p);
	void vincula2(Dec_var p);
	void vincula2(Dec_tipo p);
	void vincula2(Dec_proc p);
	void vincula2(Si_lista_opt_param_form p);
	void vincula2(No_lista_opt_param_form p);
	void vincula2(Muchas_lista_param_form p);
	void vincula2(Una_lista_param_form p);
	void vincula2(Param_form_ref p);
	void vincula2(Param_form p);
	void vincula2(Tipo_array p);
	void vincula2(Tipo_puntero p);
	void vincula2(Int_t p);
	void vincula2(Real_t p);
	void vincula2(Bool_t p);
	void vincula2(String_t p);
	void vincula2(Tipo_registro p);
	void vincula2(Tipo_definido p);
	void vincula2(Muchas_lista_param_reg p);
	void vincula2(Una_lista_param_reg p);
	void vincula2(Param_reg p);
	///
	void vincula(Si_lista_opt_inst p);
	void vincula(No_lista_opt_inst p);
	void vincula(Muchas_lista_inst p);
	void vincula(Una_lista_inst p);
	void vincula(Inst_eval p);
	void vincula(Inst_if_else p);
	void vincula(Si_else p);
	void vincula(No_else p);
	void vincula(Inst_while p);
	void vincula(Inst_read p);
	void vincula(Inst_write p);
	void vincula(Inst_nl p);
	void vincula(Inst_new p);
	void vincula(Inst_delete p);
	void vincula(Inst_call p);
	void vincula(Inst_comp p);
	void vincula(Si_lista_opt_param p);
	void vincula(No_lista_opt_param p);
	void vincula(Muchas_lista_param p);
	void vincula(Una_lista_param p);
	void vincula(Asignacion p);
	void vincula(Mayor p);
	void vincula(Mayor_igual p);
	void vincula(Menor p);
	void vincula(Menor_igual p);
	void vincula(Comparacion p);
	void vincula(Distinto p);
	void vincula(Suma p);
	void vincula(Resta p);
	void vincula(And p);
	void vincula(Or p);
	void vincula(Mult p);
	void vincula(Div p);
	void vincula(Mod p);
	void vincula(Not_unario p);
	void vincula(Resta_unario p);
	void vincula(Indexacion p);
	void vincula(Acc_reg p);
	void vincula(Indireccion p);
	void vincula(Lit_ent p);
	void vincula(Lit_real p);
	void vincula(True_e p);
	void vincula(False_e p);
	void vincula(Null_e p);
	void vincula(Cadena p);
	void vincula(Iden p);
	void abreAmbito(Map ts);//??????
	void cierraAmbito(Map ts);//?????
	void contiene(Map ts, String id);
	void inserta(Map ts, String id, Dec dec);
	Dec vinculo(Map ts, String id);
	

	
	
	
}
