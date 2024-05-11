package visitante;

import java.util.Stack;

import asint.SintaxisAbstractaTiny.*;
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

	}

	@Override
	public void procesa(Inst_delete p) {

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

	}

	@Override
	public void procesa(Asignacion e) {

	}

	@Override
	public void procesa(Mayor e) {

	}

	@Override
	public void procesa(Mayor_igual e) {

	}

	@Override
	public void procesa(Menor e) {

	}

	@Override
	public void procesa(Menor_igual e) {

	}

	@Override
	public void procesa(Comparacion e) {

	}

	@Override
	public void procesa(Distinto e) {

	}

	@Override
	public void procesa(Suma e) {

	}

	@Override
	public void procesa(Resta e) {

	}

	@Override
	public void procesa(And e) {

	}

	@Override
	public void procesa(Or e) {

	}

	@Override
	public void procesa(Mult e) {

	}

	@Override
	public void procesa(Div e) {

	}

	@Override
	public void procesa(Not_unario e) {
		e.op1().procesa(this);
		gen_acc_val(e.op1(), m);
		// COMPLETAR

	}

	@Override
	public void procesa(Resta_unario e) {
		e.op1().procesa(this);
		gen_acc_val(e.op1(), m);
		// COMPLETAR
	}

	@Override
	public void procesa(Mod e) {
		gen_cod_op(e.op1(), e.op2());
		m.emit(m.mod());
	}

	@Override
	public void procesa(Indexacion e) {

	}

	@Override
	public void procesa(Acc_reg e) {

	}

	@Override
	public void procesa(Indireccion e) {

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
