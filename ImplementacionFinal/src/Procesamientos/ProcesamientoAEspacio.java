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
		p.bloque().asig_espacio(this);
	}

	@Override
	public void asig_espacio(Bloque p) {
		// TODO Auto-generated method stub
		int dir_ant=dir;
		p.lista_opt_declaraciones().asig_espacio1(this);
		p.lista_opt_declaraciones().asig_espacio2(this);
		p.lista_opt_instrucciones().asig_espacio(this);
		dir=dir_ant;
	}

	@Override
	public void asig_espacio1(Si_lista_opt_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().asig_espacio1(this);
	}

	@Override
	public void asig_espacio1(No_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().asig_espacio1(this);
		p.declaracion().asig_espacio1(this);

	}

	@Override
	public void asig_espacio1(Una_lista_dec p) {
		// TODO Auto-generated method stub
		p.declaracion().asig_espacio1(this);

	}

	@Override
	public void asig_espacio1(Dec_var p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio1(this);
		p.setDir(dir);
		p.setNivel(nivel);
		dir+=p.tipo().getTam();
	}

	@Override
	public void asig_espacio1(Dec_tipo p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio1(this);
	}

	@Override
	public void asig_espacio1(Dec_proc p) {
		// TODO Auto-generated method stub
		int dir_ant=dir;
		nivel++;
		p.setNivel(nivel);
		dir=0;
		p.lista_opt_parametros_formales().asig_espacio1(this);
		p.lista_opt_parametros_formales().asig_espacio1(this);
		p.bloque().asig_espacio(this);
		p.setTam(dir);
		dir=dir_ant;
		nivel--;
	}

	
	public void asig_espacio1(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		p.lista_parametros_formales().asig_espacio1(this);
	}

	@Override
	public void asig_espacio1(No_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio1(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub
		p.lista_parametros_formales().asig_espacio1(this);
		p.parametro_formal().asig_espacio1(this);
	}

	@Override
	public void asig_espacio1(Una_lista_param_form p) {
		// TODO Auto-generated method stub
		p.parametro_formal().asig_espacio1(this);
	}

	@Override
	public void asig_espacio1(Param_form_ref p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio1(this);
		p.setDir(dir);
		p.setNivel(nivel);
		dir++;
	}

	@Override
	public void asig_espacio1(Param_form p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio1(this);
		p.setDir(dir);
		p.setNivel(nivel);
		dir+=p.tipo().getTam();
	}

	@Override
	public void asig_espacio1(Tipo_array p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio1(this);
		p.setTam(p.tipo().getTam()*Integer.parseInt(p.cadena()));
	}

	@Override
	public void asig_espacio1(Tipo_puntero p) {
		// TODO Auto-generated method stub
		/*if(p.tipo()!=Tipo_def(id)) {
			p.tipo().asig_espacio1(this);
		}*/
		p.setTam(1);
	}

	@Override
	public void asig_espacio1(Int_t p) {
		// TODO Auto-generated method stub
		p.setTam(1);
	}

	@Override
	public void asig_espacio1(Real_t p) {
		// TODO Auto-generated method stub
		p.setTam(1);
	}

	@Override
	public void asig_espacio1(Bool_t p) {
		// TODO Auto-generated method stub
		p.setTam(1);
	}

	@Override
	public void asig_espacio1(String_t p) {
		// TODO Auto-generated method stub
		p.setTam(1);
	}
	
	@Override
	public void asig_espacio1(Tipo_definido p) {
		// TODO Auto-generated method stub
		//p.setVinculo(p.tipo().);
		p.setTam(p.tipo().getTam());
	}

	@Override
	public void asig_espacio1(Tipo_registro p) {
		// TODO Auto-generated method stub
		int dir_ant=dir;
		dir =0;
		p.lista_parametros_registro().asig_espacio1(this);
		p.setTam(dir);
		dir=dir_ant;
		
		/* Asi lo teniamos
		p.lista_parametros_registro().asig_espacio1(this);
		p.setTam(p.lista_parametros_registro().getTam()); //Tuve que poner que LParamReg extienede de Nodo
	*/
	}

	

	@Override
	public void asig_espacio1(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		p.lista_parametros_registro().asig_espacio1(this);
		p.parametro_registro().asig_espacio1(this);
		
		
		/* Asi lo teniamos
		int d=p.lista_parametros_registro().asig_espacio1(this);
		p.parametro_registro().asig_espacio1(this);
		p.parametro_registro().setDesp(d);
		return d+p.parametro_registro().getTam();*/
	}

	@Override
	public void asig_espacio1(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		p.parametro_registro().asig_espacio1(this);
		
		/*p.parametro_registro().asig_espacio1(this);
		p.parametro_registro().setDesp(0);
		return p.parametro_registro().getTam();*/
	}

	@Override
	public void asig_espacio1(Param_reg p) {
		// TODO Auto-generated method stub
		p.setDesp(dir);
		p.tipo().asig_espacio1(this);
		dir+=p.tipo().getTam();
		
		/*p.tipo().asig_espacio1(this);
		p.setTam(p.tipo().getTam());*/
	}

	@Override
	public void asig_espacio2(Si_lista_opt_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().asig_espacio2(this);
	}

	@Override
	public void asig_espacio2(No_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void asig_espacio2(Muchas_lista_decs p) {
		// TODO Auto-generated method stub
		p.lista_declaraciones().asig_espacio2(this);
		p.declaracion().asig_espacio2(this);
	}

	@Override
	public void asig_espacio2(Una_lista_dec p) {
		// TODO Auto-generated method stub
		p.declaracion().asig_espacio2(this);

	}

	@Override
	public void asig_espacio2(Dec_var p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio2(this);;

	}

	@Override
	public void asig_espacio2(Dec_tipo p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio2(this);;

	}

	@Override
	public void asig_espacio2(Dec_proc p) {}

	@Override
	public void asig_espacio2(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub
		p.lista_parametros_formales().asig_espacio2(this);	}

	@Override
	public void asig_espacio2(No_lista_opt_param_form p) {}

	@Override
	public void asig_espacio2(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub
		p.lista_parametros_formales().asig_espacio2(this);
		p.parametro_formal().asig_espacio2(this);
	}

	@Override
	public void asig_espacio2(Una_lista_param_form p) {
		// TODO Auto-generated method stub
		p.parametro_formal().asig_espacio2(this);

	}

	@Override
	public void asig_espacio2(Param_form_ref p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio2(this);

	}

	@Override
	public void asig_espacio2(Param_form p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio2(this);

	}

	@Override
	public void asig_espacio2(Tipo_array p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio2(this);

	}

	@Override
	public void asig_espacio2(Tipo_puntero p) {
		// TODO Auto-generated method stub
		/*if(p.tipo()==tipo_definido(id)) {
			p.tipo().getVinculo()=dec_tipo(T�,id);
			p.tipo().setTam(p.tipo().tipo().getTam());???????
		}*/
	}

	@Override
	public void asig_espacio2(Int_t p) {}

	@Override
	public void asig_espacio2(Real_t p) {}

	@Override
	public void asig_espacio2(Bool_t p) {}
	@Override
	public void asig_espacio2(String_t p) {}

	@Override
	public void asig_espacio2(Tipo_registro p) {
		// TODO Auto-generated method stub
		p.lista_parametros_registro().asig_espacio2(this);
	}

	@Override
	public void asig_espacio2(Tipo_definido p) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void asig_espacio2(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		p.lista_parametros_registro().asig_espacio2(this);
		p.parametro_registro().asig_espacio2(this);
	}

	@Override
	public void asig_espacio2(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		p.parametro_registro().asig_espacio2(this);

	}

	@Override
	public void asig_espacio2(Param_reg p) {
		// TODO Auto-generated method stub
		p.tipo().asig_espacio2(this);

	}

	@Override
	public void asig_espacio(Si_lista_opt_inst p) {
		// TODO Auto-generated method stub
		p.lista_instrucciones().asig_espacio(this);
	}

	@Override
	public void asig_espacio(No_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Muchas_lista_inst p) {
		// TODO Auto-generated method stub
		p.lista_instrucciones().asig_espacio(this);
		p.instruccion().asig_espacio(this);

	}

	@Override
	public void asig_espacio(Una_lista_inst p) {
		// TODO Auto-generated method stub
		p.instruccion().asig_espacio(this);

	}

	@Override
	public void asig_espacio(Inst_eval p) {
		// TODO Auto-generated method stub
		//????????????''
	}

	@Override
	public void asig_espacio(Inst_if_else p) {
		// TODO Auto-generated method stub
		if(p.bloque2()!=null) {//???????
			p.bloque1().asig_espacio(this);
			p.bloque2().asig_espacio(this);
		}
		else {
			p.bloque1().asig_espacio(this);
		}
	}

	@Override
	public void asig_espacio(Si_else p) {
		// TODO Auto-generated method stub
		p.bloque().asig_espacio(this);
	}

	@Override
	public void asig_espacio(No_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asig_espacio(Inst_while p) {
		// TODO Auto-generated method stub
		p.bloque().asig_espacio(this);
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
		p.bloque().asig_espacio(this);
	}

	

	

}
