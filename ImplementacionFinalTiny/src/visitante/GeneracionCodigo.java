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
import maquina_p.MaquinaP;
import utils.Utils;

public class GeneracionCodigo extends ProcesamientoDef {
	private MaquinaP m;
	private Stack<Dec_proc> proc_pendientes;

	public GeneracionCodigo(MaquinaP maquinaP) {
		this.m = maquinaP;
		this.proc_pendientes = new Stack<>();
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
	public void gen_paso_params(LOptParamForm pfs, LOptParam preals) {
		if (pfs instanceof Si_lista_opt_param_form && preals instanceof Si_lista_opt_param) {
			gen_paso_params(pfs.lista_parametros_formales(), preals.lista_parametros());
		}
	}

	public void gen_paso_params(LParamForm pfs, LParam preals) {
		if (pfs instanceof Muchas_lista_param_form && preals instanceof Muchas_lista_param) {
			gen_paso_params(pfs.lista_parametros_formales(), preals.lista_parametros());
			gen_paso_param(pfs.parametro_formal(), preals.expresion());
		}

		if (pfs instanceof Una_lista_param_form && preals instanceof Una_lista_param) {
			gen_paso_param(pfs.parametro_formal(), preals.expresion());
		}
	}

	// Funciones de utilidad

	public void gen_acc_val(Exp e, MaquinaP m) {
		if (Utils.es_designador(e)) {
			m.emit(m.apila_ind());
		}
	}

	// Patron visitante
	@Override
	public void procesa(Prog_tiny p) {
		p.bloque().procesa(this);
	}

	@Override
	public void procesa(Bloque p) {
		recolecta_procs(p.lista_opt_declaraciones());
		p.lista_opt_instrucciones().procesa(this);
		m.emit(m.stop());
		while (!proc_pendientes.isEmpty()) {
			Dec_proc proc = proc_pendientes.pop();
			m.emit(m.desapilad(proc.nivel));
			recolecta_procs(p.lista_opt_declaraciones());
			m.emit(m.desactiva(proc.nivel, proc.tam));
			m.emit(m.ir_ind());
		}
	}

	@Override
	public void procesa(Si_lista_opt_inst p) {
		p.lista_instrucciones().procesa(this);
	}

	@Override
	public void procesa(No_lista_opt_inst p) {
		// Noop
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
		p.expresion().procesa(this);
		m.emit(m.desapila());
	}

	@Override
	public void procesa(Inst_if p) {
		p.expresion().procesa(this);
		gen_acc_val(p.expresion(), m);
		m.emit(m.ir_f(p.sig));
		p.bloque1().procesa(this);
	}

	@Override
	public void procesa(Inst_if_else p) {
		p.expresion().procesa(this);
		gen_acc_val(p.expresion(), m);
		m.emit(m.ir_f(p.bloque2().prim));
		p.bloque1().procesa(this);
		m.emit(m.ir_a(p.sig));
		p.bloque2().procesa(this);
	}

	@Override
	public void procesa(Inst_while p) {
		p.expresion().procesa(this);
		gen_acc_val(p.expresion(), m);
		m.emit(m.ir_f(p.sig));
		p.bloque1().procesa(this);
		m.emit(m.ir_a(p.prim));
	}

	@Override
	public void procesa(Inst_read p) {
		p.expresion().procesa(this);

		if (Utils.esEntero(p.expresion().tipo)) {
			m.emit(m.read_int());
		} else if (Utils.esReal(p.expresion().tipo)) {
			m.emit(m.read_real());
		} else if (Utils.esString(p.expresion().tipo)) {
			m.emit(m.read_string());
		} else if (Utils.esBoolean(p.expresion().tipo)) {
			m.emit(m.read_bool());
		}
		m.emit(m.desapila_ind());
	}

	@Override
	public void procesa(Inst_write p) {
		p.expresion().procesa(this);
		gen_acc_val(p.expresion(), m);
		m.emit(m.desapila_escribe());
	}

	@Override
	public void procesa(Inst_nl p) {
		m.emit(m.apila_String("\n"));
	}

	@Override
	public void procesa(Inst_new p) {
		if (Utils.es_designador(p.expresion()) && Utils.esPuntero(Utils.ref(p.expresion().tipo))) {
			p.expresion().procesa(this);
			Tipo t = p.expresion().tipo;
			m.emit(m.alloc(t.tam));
			m.emit(m.desapila_ind());
		}
	}

	@Override
	public void procesa(Inst_delete p) {
		p.expresion().procesa(this);
		Tipo T = Utils.ref(p.expresion().tipo);

		if (Utils.es_designador(p.expresion()) && Utils.esPuntero(T)) {
			m.emit(m.dealloc(T.tipo().tam));
		}

	}

	@Override
	public void procesa(Inst_call p) {
		m.emit(m.activa(p.vinculo.nivel, p.vinculo.tam, p.sig));
		Dec_proc decProc = (Dec_proc) p.vinculo;
		gen_paso_params(decProc.lista_opt_parametros_formales(), p.lista_opt_parametros());
		m.emit(m.ir_a(p.vinculo.prim));
	}

	@Override
	public void procesa(Inst_comp p) {
		p.bloque1().procesa(this);
	}

	public void gen_paso_param(ParamForm pf, Exp e) {
		m.emit(m.dup());
		m.emit(m.apila_int(pf.dir));
		m.emit(m.suma_int());

		if (pf instanceof Param_form) {
			m.emit(m.dup());
			m.emit(m.alloc(pf.tipo().tam));
			m.emit(m.desapila_ind());
			e.procesa(this);

			Tipo pft = Utils.ref(pf.tipo());

			if (Utils.esReal(pft) && Utils.esEntero(e.tipo)) {
				gen_acc_val(e, m);
				m.emit(m.int2real());
				m.emit(m.desapila_ind());
			} else {
				if (Utils.es_designador(e)) {
					m.emit(m.copia(pf.tipo().tam));
				} else {
					m.emit(m.desapila_ind());
				}

			}

		} else if (pf instanceof Param_form_ref) {
			m.emit(m.dup());
			m.emit(m.alloc(1));
			m.emit(m.desapila_ind());
			e.procesa(this);
			m.emit(m.desapila_ind());

		}

	}

	@Override
	public void procesa(Asignacion e) {
		e.op1().procesa(this);
		e.op2().procesa(this);

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.op1().tipo)) {
			gen_acc_val(e.op2(), m);
			m.emit(m.int2real());
			m.emit(m.desapila_ind());
		} else {
			if (Utils.es_designador(e.op2())) {
				m.emit(m.copia(e.op1().tam));
			} else {
				m.emit(m.desapila_ind());
			}
		}

	}

	@Override
	public void procesa(Mayor e) {
		gen_cod_op(e.op1(), e.op2());

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.mayor_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.mayor_real());
		} else if (Utils.esBoolean(e.tipo)) {
			m.emit(m.mayor_bool());
		} else if (Utils.esString(e.tipo)) {
			m.emit(m.mayor_string());
		}
	}

	@Override
	public void procesa(Mayor_igual e) {
		gen_cod_op(e.op1(), e.op2());

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.mayorIgual_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.mayorIgual_real());
		} else if (Utils.esBoolean(e.tipo)) {
			m.emit(m.mayorIgual_bool());
		} else if (Utils.esString(e.tipo)) {
			m.emit(m.mayorIgual_string());
		}
	}

	@Override
	public void procesa(Menor e) {
		gen_cod_op(e.op1(), e.op2());

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.menor_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.menor_real());
		} else if (Utils.esBoolean(e.tipo)) {
			m.emit(m.menor_bool());
		} else if (Utils.esString(e.tipo)) {
			m.emit(m.menor_string());
		}
	}

	@Override
	public void procesa(Menor_igual e) {
		gen_cod_op(e.op1(), e.op2());

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.menorIgual_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.menorIgual_real());
		} else if (Utils.esBoolean(e.tipo)) {
			m.emit(m.menorIgual_bool());
		} else if (Utils.esString(e.tipo)) {
			m.emit(m.menorIgual_string());
		}
	}

	@Override
	public void procesa(Comparacion e) {
		gen_cod_op(e.op1(), e.op2());

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.comparacion_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.comparacion_real());
		} else if (Utils.esBoolean(e.tipo)) {
			m.emit(m.comparacion_bool());
		} else if (Utils.esString(e.tipo)) {
			m.emit(m.comparacion_string());
		}
	}

	@Override
	public void procesa(Distinto e) {
		gen_cod_op(e.op1(), e.op2());
		if (Utils.esEntero(e.tipo)) {
			m.emit(m.distinto_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.distinto_real());
		} else if (Utils.esBoolean(e.tipo)) {
			m.emit(m.distinto_bool());
		} else if (Utils.esString(e.tipo)) {
			m.emit(m.distinto_string());
		}
	}

	@Override
	public void procesa(Suma e) {
		e.op1().procesa(this);
		gen_acc_val(e.op1(), m);

		if (Utils.esEntero(e.op1().tipo) && Utils.esReal(e.tipo)) {
			m.emit(m.int2real());
		}

		e.op2().procesa(this);
		gen_acc_val(e.op2(), m);

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.tipo)) {
			m.emit(m.int2real());
		}

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.suma_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.suma_real());
		}

	}

	@Override
	public void procesa(Resta e) {
		e.op1().procesa(this);
		gen_acc_val(e.op1(), m);

		if (Utils.esEntero(e.op1().tipo) && Utils.esReal(e.tipo)) {
			m.emit(m.int2real());
		}

		e.op2().procesa(this);
		gen_acc_val(e.op2(), m);

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.tipo)) {
			m.emit(m.int2real());
		}

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.resta_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.resta_real());
		}
	}

	@Override
	public void procesa(And e) {
		gen_cod_op(e.op1(), e.op2());
		m.emit(m.and());
	}

	@Override
	public void procesa(Or e) {
		gen_cod_op(e.op1(), e.op2());
		m.emit(m.or());
	}

	@Override
	public void procesa(Mult e) {
		e.op1().procesa(this);
		gen_acc_val(e.op1(), m);

		if (Utils.esEntero(e.op1().tipo) && Utils.esReal(e.tipo)) {
			m.emit(m.int2real());
		}

		e.op2().procesa(this);
		gen_acc_val(e.op2(), m);

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.tipo)) {
			m.emit(m.int2real());
		}

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.mult_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.mult_real());
		}
	}

	@Override
	public void procesa(Div e) {
		e.op1().procesa(this);
		gen_acc_val(e.op1(), m);

		if (Utils.esEntero(e.op1().tipo) && Utils.esReal(e.tipo)) {
			m.emit(m.int2real());
		}

		e.op2().procesa(this);
		gen_acc_val(e.op2(), m);

		if (Utils.esEntero(e.op2().tipo) && Utils.esReal(e.tipo)) {
			m.emit(m.int2real());
		}

		if (Utils.esEntero(e.tipo)) {
			m.emit(m.div_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.div_real());
		}
	}

	@Override
	public void procesa(Not_unario e) {
		e.op1().procesa(this);
		gen_acc_val(e.op1(), m);
		m.emit(m.notUnario());

	}

	@Override
	public void procesa(Resta_unario e) {
		e.op1().procesa(this);
		gen_acc_val(e.op1(), m);
		if (Utils.esEntero(e.tipo)) {
			m.emit(m.menosUnario_int());
		} else if (Utils.esReal(e.tipo)) {
			m.emit(m.menosUnario_real());
		}
	}

	@Override
	public void procesa(Mod e) {
		gen_cod_op(e.op1(), e.op2());
		m.emit(m.mod());
	}

	@Override
	public void procesa(Indexacion e) {
		Tipo T = Utils.ref(e.op1().tipo);
		if(Utils.esArray(T)) {
			e.op1().procesa(this);
			e.op2().procesa(this);
			gen_acc_val(e.op2(), m);
			m.emit(m.apila_int(T.tipo().tam));
			m.emit(m.mult_int());
			m.emit(m.suma_int());
		}
	}

	@Override
	public void procesa(Acc_reg e) {
		Tipo T = Utils.ref(e.op1().tipo);
		
		if(Utils.esRegistro(T)) {
			e.op1().procesa(this);
			m.emit(); //Hallar el desplazamiento
			m.emit(m.suma_int());
		}
	}

	@Override
	public void procesa(Indireccion e) {
		Tipo T = Utils.ref(e.op1().tipo);
		
		if(Utils.esPuntero(T)) {
			e.op1().procesa(this);
			m.emit(m.apila_ind());
		}
	}

	@Override
	public void procesa(Lit_ent n) {
		m.emit(m.apila_int(Integer.parseInt(n.cadena())));
	}

	@Override
	public void procesa(Lit_real n) {
		m.emit(m.apila_real(Float.parseFloat(n.cadena())));
	}

	@Override
	public void procesa(True_e e) {
		m.emit(m.apila_bool(true));
	}

	@Override
	public void procesa(False_e e) {
		m.emit(m.apila_bool(false));
	}

	@Override
	public void procesa(Null_e e) {
		m.emit(m.apila_int(-1));
	}

	@Override
	public void procesa(Cadena c) {
		m.emit(m.apila_String(c.cadena()));
	}

	@Override
	public void procesa(Iden id) {
		if (id.vinculo instanceof Dec_var) {
			gen_acc_id((Dec_var) id.vinculo);
		} else if (id.vinculo instanceof Param_form_ref) {
			gen_acc_id((Param_form_ref) id.vinculo);
		} else if (id.vinculo instanceof Param_form) {
			gen_acc_id((Param_form) id.vinculo);
		}
	}

	public void gen_acc_id(Dec_var dec) {
		if (dec.nivel == 0) {
			m.emit(m.apila_int(dec.dir));
		}
		else {
			m.emit(m.apilad(dec.nivel));
			m.emit(m.apila_int(dec.dir));
			m.emit(m.suma_int());
		}
	}

	public void gen_acc_id(Param_form_ref pf) {
		m.emit(m.apilad(pf.nivel));
		m.emit(m.apila_int(pf.dir));
		m.emit(m.suma_int());
		m.emit(m.apila_ind());
	}

	public void gen_acc_id(Param_form pf) {
		m.emit(m.apilad(pf.nivel));
		m.emit(m.apila_int(pf.dir));
		m.emit(m.suma_int());
	}

	public void gen_cod_op(Exp Opnd0, Exp Opnd1) {
		Opnd0.procesa(this);
		gen_acc_val(Opnd0, m);
		Opnd1.procesa(this);
		gen_acc_val(Opnd1, m);
	}

}
