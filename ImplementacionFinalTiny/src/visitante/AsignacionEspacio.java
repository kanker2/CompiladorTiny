package visitante;

import asint.SintaxisAbstractaTiny.Bloque;
import asint.SintaxisAbstractaTiny.Bool_t;
import asint.SintaxisAbstractaTiny.Dec_proc;
import asint.SintaxisAbstractaTiny.Dec_tipo;
import asint.SintaxisAbstractaTiny.Dec_var;
import asint.SintaxisAbstractaTiny.Inst_call;
import asint.SintaxisAbstractaTiny.Inst_comp;
import asint.SintaxisAbstractaTiny.Inst_delete;
import asint.SintaxisAbstractaTiny.Inst_eval;
import asint.SintaxisAbstractaTiny.Inst_if;
import asint.SintaxisAbstractaTiny.Inst_if_else;
import asint.SintaxisAbstractaTiny.Inst_new;
import asint.SintaxisAbstractaTiny.Inst_nl;
import asint.SintaxisAbstractaTiny.Inst_read;
import asint.SintaxisAbstractaTiny.Inst_while;
import asint.SintaxisAbstractaTiny.Inst_write;
import asint.SintaxisAbstractaTiny.Int_t;
import asint.SintaxisAbstractaTiny.Muchas_lista_decs;
import asint.SintaxisAbstractaTiny.Muchas_lista_inst;
import asint.SintaxisAbstractaTiny.Muchas_lista_param_form;
import asint.SintaxisAbstractaTiny.Muchas_lista_param_reg;
import asint.SintaxisAbstractaTiny.No_lista_opt_decs;
import asint.SintaxisAbstractaTiny.No_lista_opt_inst;
import asint.SintaxisAbstractaTiny.No_lista_opt_param_form;
import asint.SintaxisAbstractaTiny.Param_form;
import asint.SintaxisAbstractaTiny.Param_form_ref;
import asint.SintaxisAbstractaTiny.Param_reg;
import asint.SintaxisAbstractaTiny.Prog_tiny;
import asint.SintaxisAbstractaTiny.Real_t;
import asint.SintaxisAbstractaTiny.Si_lista_opt_decs;
import asint.SintaxisAbstractaTiny.Si_lista_opt_inst;
import asint.SintaxisAbstractaTiny.Si_lista_opt_param_form;
import asint.SintaxisAbstractaTiny.String_t;
import asint.SintaxisAbstractaTiny.Tipo_array;
import asint.SintaxisAbstractaTiny.Tipo_definido;
import asint.SintaxisAbstractaTiny.Tipo_puntero;
import asint.SintaxisAbstractaTiny.Tipo_registro;
import asint.SintaxisAbstractaTiny.Una_lista_dec;
import asint.SintaxisAbstractaTiny.Una_lista_inst;
import asint.SintaxisAbstractaTiny.Una_lista_param_form;
import asint.SintaxisAbstractaTiny.Una_lista_param_reg;
import utils.Utils;

public class AsignacionEspacio extends ProcesamientoDef {

	int dir;
	int nivel;
	int max_dir;
	boolean primera;
	boolean primeraR;

	public AsignacionEspacio() {
		dir = 0;
		nivel = 0;
		max_dir = 0;
		primera = false;
		primeraR = false;
	}

	@Override
	public void procesa(Prog_tiny p) {
		p.bloque().procesa(this);
	}

	@Override
	public void procesa(Bloque p) {
		int dir_ant = dir;
		primera = true;
		p.lista_opt_declaraciones().procesa(this); // 1º Pasada
		primera = false;
		p.lista_opt_declaraciones().procesa(this); // 2º Pasada
		p.lista_opt_instrucciones().procesa(this);
		dir = dir_ant;
	}

	@Override
	public void procesa(Si_lista_opt_decs p) {
		p.lista_declaraciones().procesa(this);
	}

	@Override
	public void procesa(No_lista_opt_decs p) {
		//Noop
	}

	@Override
	public void procesa(Muchas_lista_decs p) {
		p.lista_declaraciones().procesa(this);
		p.declaracion().procesa(this);

	}

	@Override
	public void procesa(Una_lista_dec p) {
		p.declaracion().procesa(this);

	}

	@Override
	public void procesa(Dec_var p) {
		if (primera) {
			p.tipo().procesa(this);
			p.dir = dir;
			p.nivel = nivel;
			inc_dir(p.tipo().tam);
		} else {
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Dec_tipo p) {
		p.tipo().procesa(this);
	}

	@Override
	public void procesa(Dec_proc p) {
		if (primera) {
			int dir_ant = dir;
			int max_dir_ant = max_dir;
			nivel++;
			p.nivel = nivel;
			dir = 0;
			max_dir = 0;
			primeraR = true;
			p.lista_opt_parametros_formales().procesa(this);// 1º pasada
			primeraR = false;
			p.lista_opt_parametros_formales().procesa(this);// 2º pasada
			p.bloque().procesa(this);
			primera = true; // la inst de arriba hace otras dos pasadas y deja primera en false
			p.tam = max_dir;
			dir = dir_ant;
			max_dir = max_dir_ant;
			nivel--;
		}
	}

	@Override
	public void procesa(Si_lista_opt_param_form p) {
		p.lista_parametros_formales().procesa(this);
	}

	@Override
	public void procesa(No_lista_opt_param_form p) {
		//Noop
	}

	@Override
	public void procesa(Muchas_lista_param_form p) {
		p.lista_parametros_formales().procesa(this);
		p.parametro_formal().procesa(this);
	}

	@Override
	public void procesa(Una_lista_param_form p) {
		p.parametro_formal().procesa(this);
	}

	@Override
	public void procesa(Param_form_ref p) {
		if (primeraR) {
			p.tipo().procesa(this);
			p.dir = dir;
			p.nivel = nivel;
			inc_dir(dir);
		} else {
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Param_form p) {
		if (primeraR) {
			p.tipo().procesa(this);
			p.dir = dir;
			p.nivel = nivel;
			inc_dir(p.tipo().tam);
		} else {
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Tipo_array p) {
		if (primera) {
			p.tipo().procesa(this);
			p.tam = p.tipo().tam * Integer.parseInt(p.cadena());
		} else {
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Tipo_puntero p) {
		
		if(primera) {
			if(!Utils.esTipoDefinido(p.tipo())) {
				p.tipo().procesa(this);
			}
			p.tam = 1;
		}
		else {
			if(Utils.esTipoDefinido(p.tipo())) {
				Dec_tipo d = (Dec_tipo) p.vinculo;
				p.tipo().tam = d.tipo().tam;
			}
			else {
				p.tipo().procesa(this);
			}
		}

	}

	@Override
	public void procesa(Int_t p) {
		if (primera)
			p.tam = 1;
	}

	@Override
	public void procesa(Real_t p) {
		if (primera)
			p.tam = 1;
	}

	@Override
	public void procesa(Bool_t p) {
		if (primera)
			p.tam = 1;
	}

	@Override
	public void procesa(String_t p) {
		if (primera)
			p.tam = 1;
	}

	@Override
	public void procesa(Tipo_registro p) {
		if(primera) {
			int dir_ant = dir;
			dir = 0;
			p.lista_parametros_registro().procesa(this);
			p.tam = dir;
			dir = dir_ant;
		}
		else {
			p.lista_parametros_registro().procesa(this);
		}
		
	}

	@Override
	public void procesa(Muchas_lista_param_reg p) {
		p.lista_parametros_registro().procesa(this);
		p.parametro_registro().procesa(this);
	}

	@Override
	public void procesa(Una_lista_param_reg p) {
		p.parametro_registro().procesa(this);
	}

	@Override
	public void procesa(Param_reg p) {
		if(primera) {
			p.despl = dir;
			p.tipo().procesa(this);
			p.tam = p.tipo().tam;
		}
		else {
			p.tipo().procesa(this);
		}

	}

	@Override
	public void procesa(Tipo_definido p) {
		if(primera) {
			Dec_tipo d = (Dec_tipo) p.vinculo;
			p.tam = d.tipo().tam;
		}
	}

	@Override
	public void procesa(Si_lista_opt_inst p) {
		p.lista_instrucciones().procesa(this);
	}

	@Override
	public void procesa(No_lista_opt_inst p) {
		//Noop

	}

	@Override
	public void procesa(Muchas_lista_inst p) {
		p.lista_instrucciones().procesa(this);
		p.instruccion().procesa(this);

	}

	@Override
	public void procesa(Una_lista_inst p) {
		p.instruccion().procesa(this);

	}

	@Override
	public void procesa(Inst_eval p) {
	}

	@Override
	public void procesa(Inst_if p) {
		p.bloque1().procesa(this);
	}

	@Override
	public void procesa(Inst_if_else p) {
		p.bloque1().procesa(this);
		p.bloque2().procesa(this);
	}

	@Override
	public void procesa(Inst_while p) {
		p.bloque1().procesa(this);
	}

	@Override
	public void procesa(Inst_read p) {
	}

	@Override
	public void procesa(Inst_write p) {
	}

	@Override
	public void procesa(Inst_nl p) {
	}

	@Override
	public void procesa(Inst_new p) {
	}

	@Override
	public void procesa(Inst_delete p) {
	}

	@Override
	public void procesa(Inst_call p) {
	}

	@Override
	public void procesa(Inst_comp p) {
		p.bloque1().procesa(this);
	}

	public void inc_dir(int inc) {
		dir += inc;
		if (dir > max_dir) {
			max_dir = dir;
		}
	}
}
