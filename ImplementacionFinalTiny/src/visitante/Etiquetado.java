package visitante;

import java.util.Stack;

import asint.SintaxisAbstractaTiny.Acc_reg;
import asint.SintaxisAbstractaTiny.And;
import asint.SintaxisAbstractaTiny.Asignacion;
import asint.SintaxisAbstractaTiny.Bloque;
import asint.SintaxisAbstractaTiny.Cadena;
import asint.SintaxisAbstractaTiny.Comparacion;
import asint.SintaxisAbstractaTiny.Dec;
import asint.SintaxisAbstractaTiny.Dec_proc;
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
import asint.SintaxisAbstractaTiny.Inst_if;
import asint.SintaxisAbstractaTiny.Inst_if_else;
import asint.SintaxisAbstractaTiny.Inst_new;
import asint.SintaxisAbstractaTiny.Inst_nl;
import asint.SintaxisAbstractaTiny.Inst_read;
import asint.SintaxisAbstractaTiny.Inst_while;
import asint.SintaxisAbstractaTiny.Inst_write;
import asint.SintaxisAbstractaTiny.LDecs;
import asint.SintaxisAbstractaTiny.LOptDecs;
import asint.SintaxisAbstractaTiny.LOptParam;
import asint.SintaxisAbstractaTiny.LOptParamForm;
import asint.SintaxisAbstractaTiny.LParam;
import asint.SintaxisAbstractaTiny.LParamForm;
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
import asint.SintaxisAbstractaTiny.Mult;
import asint.SintaxisAbstractaTiny.No_lista_opt_inst;
import asint.SintaxisAbstractaTiny.Not_unario;
import asint.SintaxisAbstractaTiny.Null_e;
import asint.SintaxisAbstractaTiny.Or;
import asint.SintaxisAbstractaTiny.ParamForm;
import asint.SintaxisAbstractaTiny.Param_form;
import asint.SintaxisAbstractaTiny.Param_form_ref;
import asint.SintaxisAbstractaTiny.Prog_tiny;
import asint.SintaxisAbstractaTiny.Resta;
import asint.SintaxisAbstractaTiny.Resta_unario;
import asint.SintaxisAbstractaTiny.Si_lista_opt_decs;
import asint.SintaxisAbstractaTiny.Si_lista_opt_inst;
import asint.SintaxisAbstractaTiny.Si_lista_opt_param;
import asint.SintaxisAbstractaTiny.Si_lista_opt_param_form;
import asint.SintaxisAbstractaTiny.Suma;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.True_e;
import asint.SintaxisAbstractaTiny.Una_lista_inst;
import asint.SintaxisAbstractaTiny.Una_lista_param;
import asint.SintaxisAbstractaTiny.Una_lista_param_form;
import utils.Utils;

public class Etiquetado extends ProcesamientoDef {
	private Stack<Dec_proc> proc_pendientes;
	private int etq;

	public Etiquetado() {
		this.etq = 0;
	}

	// Funciones de apoyo que recolecta los procedimientos
	public void recolecta_procs(LOptDecs loptdecs) {
		if (loptdecs instanceof Si_lista_opt_decs) {
			recolecta_procs(loptdecs.lista_declaraciones());
		}
	}

	public void recolecta_procs(LDecs ldecs) {
		if (ldecs instanceof Muchas_lista_decs) {
			recolecta_procs(ldecs.lista_declaraciones());
			recolecta_procs(ldecs.declaracion());
		} else {
			recolecta_procs(ldecs.declaracion());
		}
	}

	public void recolecta_procs(Dec dec) {
		if (dec instanceof Dec_proc) {
			proc_pendientes.push((Dec_proc) dec);
		}
	}

	// Funciones de apoyo para el paso de parametros en funcion
	public void etiquetado_paso_params(LOptParamForm pfs, LOptParam preals) {
		if (pfs instanceof Si_lista_opt_param_form && preals instanceof Si_lista_opt_param) {
			etiquetado_paso_params(pfs.lista_parametros_formales(), preals.lista_parametros());
		}
	}

	public void etiquetado_paso_params(LParamForm pfs, LParam preals) {
		if (pfs instanceof Muchas_lista_param_form && preals instanceof Muchas_lista_param) {
			etiquetado_paso_params(pfs.lista_parametros_formales(), preals.lista_parametros());
			etiquetado_paso_param(pfs.parametro_formal(), preals.expresion());
		}

		if (pfs instanceof Una_lista_param_form && preals instanceof Una_lista_param) {
			etiquetado_paso_param(pfs.parametro_formal(), preals.expresion());
		}
	}

	// Funciones de utilidad
	public boolean es_designador(Exp e) {
		if (e instanceof Iden || e instanceof Indexacion || e instanceof Acc_reg || e instanceof Indireccion) {
			return true;
		}

		return false;
	}

	public void etiquetado_acc_val(Exp e) {
		if (es_designador(e)) {
			etq++;
		}
	}

	// Patron visitante
	@Override
	public void procesa(Prog_tiny p) {
		p.prim = etq;
		p.bloque().procesa(this);
		p.sig = etq;
	}

	@Override
	public void procesa(Bloque p) {
		p.prim = etq;
		recolecta_procs(p.lista_opt_declaraciones());
		p.lista_opt_instrucciones().procesa(this);
		etq++;
		while (!proc_pendientes.isEmpty()) {
			Dec_proc proc = proc_pendientes.pop();
			proc.prim = etq;
			etq++;
			recolecta_procs(p.lista_opt_declaraciones());
			etq += 2;
			proc.sig = etq;
		}
		p.sig = etq;
	}

	@Override
	public void procesa(Si_lista_opt_inst p) {
		p.prim = etq;
		p.lista_instrucciones().procesa(this);
		p.sig = etq;
	}

	@Override
	public void procesa(No_lista_opt_inst p) {
		// Noop
	}

	@Override
	public void procesa(Muchas_lista_inst p) {
		p.prim = etq;
		p.lista_instrucciones().procesa(this);
		p.instruccion().procesa(this);
		p.sig = etq;
	}

	@Override
	public void procesa(Una_lista_inst p) {
		p.prim = etq;
		p.instruccion().procesa(this);
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_eval p) {
		p.prim = etq;
		p.expresion().procesa(this);
		etq++;
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_if p) {
		p.prim = etq;
		p.expresion().procesa(this);
		etiquetado_acc_val(p.expresion());
		etq++;
		p.bloque1().procesa(this);
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_if_else p) {
		p.prim = etq;
		p.expresion().procesa(this);
		etiquetado_acc_val(p.expresion());
		etq++;
		p.bloque1().procesa(this);
		etq++;
		p.bloque2().procesa(this);
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_while p) {
		p.prim = etq;
		p.expresion().procesa(this);
		etiquetado_acc_val(p.expresion());
		etq++;
		p.bloque1().procesa(this);
		etq++;
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_read p) {
		p.prim = etq;
		p.expresion().procesa(this);

		if (Utils.esEntero(p.tipo) || Utils.esReal(p.tipo) || Utils.esBoolean(p.tipo) || Utils.esString(p.tipo)) {
			etq++;
		}
		etq++;
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_write p) {
		p.prim = etq;
		p.expresion().procesa(this);
		etiquetado_acc_val(p.expresion());
		etq++;
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_nl p) {
		p.prim = etq;
		etq++;
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_new p) {
		p.prim = etq;

		Tipo T = Utils.ref(p.expresion().tipo);
		if (Utils.es_designador(p.expresion()) && Utils.esPuntero(T)) {
			p.expresion().procesa(this);
			etq += 2;
		}
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_delete p) {
		p.prim = etq;
		p.expresion().procesa(this);
		Tipo T = Utils.ref(p.expresion().tipo);
		if (Utils.es_designador(p.expresion()) && Utils.esPuntero(T)) {
			etq++;
		}

		p.sig = etq;
	}

	@Override
	public void procesa(Inst_call p) {
		p.prim = etq;
		etq++;
		Dec_proc decProc = (Dec_proc) p.vinculo;
		etiquetado_paso_params(decProc.lista_opt_parametros_formales(), p.lista_opt_parametros());
		etq++;
		p.sig = etq;
	}

	@Override
	public void procesa(Inst_comp p) {
		p.prim = etq;
		etq++;
		p.sig = etq;
	}

	public void etiquetado_paso_param(ParamForm pf, Exp e) {
		etq+=3;

		if (pf instanceof Param_form) {
			etq+=3;
			e.procesa(this);

			Tipo pft = Utils.ref(pf.tipo());

			if (Utils.esReal(pft) && Utils.esEntero(e.tipo)) {
				etiquetado_acc_val(e);
				etq+=2;
			} else {
				if (Utils.es_designador(e)) {
					etq++;
				} else {
					etq++;
				}

			}

		} else if (pf instanceof Param_form_ref) {
			etq+=3;
			e.procesa(this);
			etq++;

		}
	}

	@Override
	public void procesa(Asignacion e) {
		e.op1().procesa(this);
		e.op2().procesa(this);

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.op1().tipo)) {
			etiquetado_acc_val(e.op2());
			etq++;
		} else {
			if (Utils.es_designador(e.op2())) {
				etq++;
			} else {
				etq++;
			}
		}
	}

	@Override
	public void procesa(Mayor e) {
		etiquetado_op(e.op1(), e.op2());
		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		} else if (Utils.esBoolean(e.tipo)) {
			etq++;
		} else if (Utils.esString(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Mayor_igual e) {
		etiquetado_op(e.op1(), e.op2());
		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		} else if (Utils.esBoolean(e.tipo)) {
			etq++;
		} else if (Utils.esString(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Menor e) {
		etiquetado_op(e.op1(), e.op2());
		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		} else if (Utils.esBoolean(e.tipo)) {
			etq++;
		} else if (Utils.esString(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Menor_igual e) {
		etiquetado_op(e.op1(), e.op2());
		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		} else if (Utils.esBoolean(e.tipo)) {
			etq++;
		} else if (Utils.esString(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Comparacion e) {
		etiquetado_op(e.op1(), e.op2());
		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		} else if (Utils.esBoolean(e.tipo)) {
			etq++;
		} else if (Utils.esString(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Distinto e) {
		etiquetado_op(e.op1(), e.op2());
		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		} else if (Utils.esBoolean(e.tipo)) {
			etq++;
		} else if (Utils.esString(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Suma e) {
		e.op1().procesa(this);
		etiquetado_acc_val(e.op1());

		if (Utils.esEntero(e.op1().tipo) && Utils.esReal(e.tipo)) {
			etq++;
		}

		e.op2().procesa(this);
		etiquetado_acc_val(e.op2());

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.tipo)) {
			etq++;
		}

		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Resta e) {
		e.op1().procesa(this);
		etiquetado_acc_val(e.op1());

		if (Utils.esEntero(e.op1().tipo) && Utils.esReal(e.tipo)) {
			etq++;
		}

		e.op2().procesa(this);
		etiquetado_acc_val(e.op2());

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.tipo)) {
			etq++;
		}

		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(And e) {
		etiquetado_op(e.op1(), e.op2());
		etq++;
	}

	@Override
	public void procesa(Or e) {
		etiquetado_op(e.op1(), e.op2());
		etq++;
	}

	@Override
	public void procesa(Mult e) {
		e.op1().procesa(this);
		etiquetado_acc_val(e.op1());

		if (Utils.esEntero(e.op1().tipo) && Utils.esReal(e.tipo)) {
			etq++;
		}

		e.op2().procesa(this);
		etiquetado_acc_val(e.op2());

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.tipo)) {
			etq++;
		}

		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Div e) {
		e.op1().procesa(this);
		etiquetado_acc_val(e.op1());

		if (Utils.esEntero(e.op1().tipo) && Utils.esReal(e.tipo)) {
			etq++;
		}

		e.op2().procesa(this);
		etiquetado_acc_val(e.op2());

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.tipo)) {
			etq++;
		}

		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Not_unario e) {
		e.op1().procesa(this);
		etiquetado_acc_val(e.op1());
		etq++;

	}

	@Override
	public void procesa(Resta_unario e) {
		e.op1().procesa(this);
		etiquetado_acc_val(e.op1());
		if (Utils.esEntero(e.tipo)) {
			etq++;
		} else if (Utils.esReal(e.tipo)) {
			etq++;
		}
	}

	@Override
	public void procesa(Mod e) {
		etiquetado_op(e.op1(), e.op2());
		etq++;
	}

	@Override
	public void procesa(Indexacion e) {
		Tipo T = Utils.ref(e.op1().tipo);
		if (Utils.esArray(T)) {
			e.op1().procesa(this);
			e.op2().procesa(this);
			etiquetado_acc_val(e.op2());
			etq += 3;
		}
	}

	@Override
	public void procesa(Acc_reg e) {
		Tipo T = Utils.ref(e.op1().tipo);

		if (Utils.esRegistro(T)) {
			e.op1().procesa(this);
			etq += 2;
		}
	}

	@Override
	public void procesa(Indireccion e) {
		Tipo T = Utils.ref(e.op1().tipo);

		if (Utils.esPuntero(T)) {
			e.op1().procesa(this);
			etq++;
		}
	}

	@Override
	public void procesa(Lit_ent n) {
		n.prim = etq;
		etq++;
		n.sig = etq;
	}

	@Override
	public void procesa(Lit_real n) {
		n.prim = etq;
		etq++;
		n.sig = etq;
	}

	@Override
	public void procesa(True_e e) {
		e.prim = etq;
		etq++;
		e.sig = etq;
	}

	@Override
	public void procesa(False_e e) {
		e.prim = etq;
		etq++;
		e.sig = etq;
	}

	@Override
	public void procesa(Null_e e) {
		e.prim = etq;
		etq++;
		e.sig = etq;
	}

	@Override
	public void procesa(Cadena e) {
		e.prim = etq;
		etq++;
		e.sig = etq;
	}

	@Override
	public void procesa(Iden id) {
		id.prim = etq;
		if (id.vinculo instanceof Dec_var) {
			etiquetado_acc_id((Dec_var) id.vinculo);
		} else if (id.vinculo instanceof Param_form_ref) {
			etiquetado_acc_id((Param_form_ref) id.vinculo);
		} else if (id.vinculo instanceof Param_form) {
			etiquetado_acc_id((Param_form) id.vinculo);
		}
		id.sig = etq;
	}

	public void etiquetado_acc_id(Dec_var dec) {
		if (dec.nivel == 0) {
			etq++;
		} else {
			etq += 3;
		}
	}

	public void etiquetado_acc_id(Param_form_ref pf) {
		etq += 4;
	}

	public void etiquetado_acc_id(Param_form pf) {
		etq += 3;
	}

	public void etiquetado_op(Exp Opnd0, Exp Opnd1) {
		Opnd0.procesa(this);
		etiquetado_acc_val(Opnd0);
		Opnd1.procesa(this);
		etiquetado_acc_val(Opnd1);
	}
}
