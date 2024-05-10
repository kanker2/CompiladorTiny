package visitante;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import asint.SintaxisAbstractaTiny.Nodo;
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
import asint.SintaxisAbstractaTiny.Inst_if;
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

public class Vinculacion extends ProcesamientoDef {
	
	private TablaDeSimbolosAnidados ts; 
	
	public Vinculacion() {
		ts = new TablaDeSimbolosAnidados();
	}
	
	public class TablaDeSimbolos extends HashMap<String, Nodo> {}
	
	public class TablaDeSimbolosAnidados extends ArrayDeque<TablaDeSimbolos> {
		public void abreAmbito() {
			addFirst(new TablaDeSimbolos());
		}
		
		public void cierraAmbito() {
			removeFirst();
		}
		
		public Nodo vinculoDe(String id) {
			Iterator<TablaDeSimbolos> i = iterator();
			while (i.hasNext()) {
				TablaDeSimbolos tsCurrent = i.next();
				if (tsCurrent.containsKey(id))
					return tsCurrent.get(id);
			}
			return null;
		}
		
		public boolean contiene(String id) {
			return getFirst().containsKey(id);
		}
	}
	
	// procesa() == metodo vinculacion de la especificación
	
	@Override
	public void procesa(Prog_tiny p) {
		p.bloque().procesa(this);
	}

	@Override
	public void procesa(Bloque p) {
		ts.abreAmbito();
		p.lista_opt_declaraciones().procesa(this);
		p.lista_opt_instrucciones().procesa(this);
		ts.cierraAmbito();
	}

	@Override
	public void procesa(Si_lista_opt_decs p) {
		vincula1(p.lista_declaraciones());
		vincula2(p.lista_declaraciones());
	}
	
	@Override
	public void procesa(No_lista_opt_decs p) { /* noop */ }

	@Override
	public void procesa(Muchas_lista_decs p) {
	}

	@Override
	public void procesa(Una_lista_dec p) {
	}

	@Override
	public void procesa(Dec_var p) {
	}

	@Override
	public void procesa(Dec_tipo p) {
	}

	@Override
	public void procesa(Dec_proc p) {
		
	}

	@Override
	public void procesa(Si_lista_opt_param_form p) {
		// TODO Auto-generated method stub

	}

	@Overridep.expresion().procesa(this);
	public void procesa(No_lista_opt_param_form p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Muchas_lista_param_form p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Una_lista_param_form p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_form_ref p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_form p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_array p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_puntero p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Int_t p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Real_t p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Bool_t p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(String_t p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_registro p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_definido p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Una_lista_param_reg p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_reg p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Si_lista_opt_inst p) {
		p.lista_instrucciones().procesa(this);
	}

	@Override
	public void procesa(No_lista_opt_inst p) { /* noop */ }

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
	}

	@Override
	public void procesa(Inst_if_else p) {
		p.expresion().procesa(this);
		p.bloque1().procesa(this);
		p.bloque2().procesa(this);
	}

	@Override
	public void procesa(Inst_if p) {
		p.expresion().procesa(this);
		p.bloque().procesa(this);
	}

	@Override
	public void procesa(Inst_while p) {
		p.expresion().procesa(this);
		p.bloque().procesa(this);
	}

	@Override
	public void procesa(Inst_read p) {
		p.expresion().procesa(this);
	}

	@Override
	public void procesa(Inst_write p) {
		p.expresion().procesa(this);
	}

	@Override
	public void procesa(Inst_nl p) { /* noop */ }

	@Override
	public void procesa(Inst_new p) {
		p.expresion().procesa(this);
	}

	@Override
	public void procesa(Inst_delete p) {
		p.expresion().procesa(this);
	}

	@Override
	public void procesa(Inst_call p) {
		if(ts.contiene(p.cadena())) {
			p.vinculo = ts.vinculoDe(p.cadena());
			p.lista_opt_parametros().procesa(this);
		}
		else
			throw
	}

	@Override
	public void procesa(Inst_comp p) {
		p.bloque().procesa(this);
	}


	@Override
	public void procesa(Si_lista_opt_param p) {
		p.lista_parametros().procesa(this);
	}

	@Override
	public void procesa(No_lista_opt_param p) { /* noop */ }

	@Override
	public void procesa(Muchas_lista_param p) {
		p.lista_parametros().procesa(this);
		p.expresion().procesa(this);
	}

	@Override
	public void procesa(Una_lista_param p) {
		p.expresion().procesa(this);
	}
	
	@Override
	public void procesa(Asignacion p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Mayor p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Mayor_igual p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Menor p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Menor_igual p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Comparacion p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Distinto p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Suma p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Resta p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(And p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Or p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Mult p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Div p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Mod p) {
		p.op1().procesa(this);
		p.op2().procesa(this);
	}

	@Override
	public void procesa(Not_unario p) {
		p.op1().procesa(this);
	}

	@Override
	public void procesa(Resta_unario p) {
		p.op1().procesa(this);
	}

	@Override
	public void procesa(Indexacion p) {
		p.op1().procesa(this);
	}

	@Override
	public void procesa(Acc_reg p) {
		p.op1().procesa(this);
	}

	@Override
	public void procesa(Indireccion p) {
		p.op1().procesa(this);
	}

	@Override
	public void procesa(Lit_ent p) { /* noop */ }

	@Override
	public void procesa(Lit_real p) { /* noop */ }
	
	@Override
	public void procesa(True_e p) { /* noop */ }

	@Override
	public void procesa(False_e p) { /* noop */ }

	@Override
	public void procesa(Null_e p) { /* noop */ }

	@Override
	public void procesa(Cadena p) { /* noop */ }

	@Override
	public void procesa(Iden p) {
		p.vinculo = ts.vinculoDe(p.cadena());
		if (p.vinculo == null)
			throw 
	}
}
