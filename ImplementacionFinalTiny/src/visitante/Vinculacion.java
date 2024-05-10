package visitante;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
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
	private boolean primeraPasada;
	private SimbolosRegistro sr;
	
	public Vinculacion() {
		ts = new TablaDeSimbolosAnidados();
		primeraPasada = true;
	}
	
	public class SimbolosRegistro {
		private HashSet<String> ambito;
		public void abreAmbitoRegistro() {
			ambito = new HashSet<String>();
		}
		public void cierraAmbitoRegistro() {
			ambito = null;
		}
		
		public boolean duplicadoRegistro(String id) {
			return ambito.contains(id);
		}
		
		public void apuntaRegistro(String id) {
			ambito.add(id);
		}
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
		
		public void inserta(String id, Nodo p) {
			getFirst().put(id,  p);
		}
		
		/* Añade en el ámbito más reciente
		 * Excepción en caso de que ya esté declarado en ese ámbito */
		public void recolecta(String id, Nodo p) {
			TablaDeSimbolos tsCurrent = getFirst();
			if (contiene(id))
				throw ;// Identificador duplicado
			else
				inserta(id, p);
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
		//vincula1
		p.lista_declaraciones().procesa(this);
		primeraPasada = false;
		//vincula2
		p.lista_declaraciones().procesa(this);
	}
	
	@Override
	public void procesa(No_lista_opt_decs p) { /* noop */ }

	@Override
	public void procesa(Muchas_lista_decs p) {
		if (primeraPasada) { 	// vincula1
			p.lista_declaraciones().procesa(this);
			p.declaracion().procesa(this);
		} else {				// vincula2
			p.lista_declaraciones().procesa(this);
			p.declaracion().procesa(this);
		}
	}

	@Override
	public void procesa(Una_lista_dec p) {
		if (primeraPasada) { 	// vincula1
			p.declaracion().procesa(this);
		} else {				// vincula2
			p.declaracion().procesa(this);
		}
	}

	@Override
	public void procesa(Dec_var p) {
		if (primeraPasada) { 	// vincula1
			p.tipo().procesa(this);
			ts.recolecta(p.cadena(), p);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Dec_tipo p) {
		if (primeraPasada) { 	// vincula1
			p.tipo().procesa(this);
			ts.recolecta(p.cadena(), p);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Dec_proc p) {
		if (primeraPasada) { 	// vincula1
			ts.recolecta(p.cadena(), p);
			ts.abreAmbito();
			p.lista_opt_parametros_formales().procesa(this);
			p.bloque().procesa(this);
			ts.cierraAmbito();
		} else {				// vincula2
			p.lista_opt_parametros_formales().procesa(this);
		}
	}

	@Override
	public void procesa(Si_lista_opt_param_form p) {
		if (primeraPasada) { 	// vincula1
			p.lista_parametros_formales().procesa(this);
		} else {				// vincula2
			p.lista_parametros_formales().procesa(this);
		}
	}

	@Override
	public void procesa(No_lista_opt_param_form p) {
		if (primeraPasada) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}

	@Override
	public void procesa(Muchas_lista_param_form p) {
		if (primeraPasada) { 	// vincula1
			p.lista_parametros_formales().procesa(this);
			p.parametro_formal().procesa(this);
		} else {				// vincula2
			p.lista_parametros_formales().procesa(this);
			p.parametro_formal().procesa(this);
		}
	}

	@Override
	public void procesa(Una_lista_param_form p) {
		if (primeraPasada) { 	// vincula1
			p.parametro_formal().procesa(this);
		} else {				// vincula2
			p.parametro_formal().procesa(this);
		}
	}

	@Override
	public void procesa(Param_form_ref p) {
		if (primeraPasada) { 	// vincula1
			p.tipo().procesa(this);
			ts.recolecta(p.cadena(), p);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Param_form p) {
		if (primeraPasada) { 	// vincula1
			p.tipo().procesa(this);
			ts.recolecta(p.cadena(), p);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Tipo_array p) {
		if (primeraPasada) { 	// vincula1
			p.tipo().procesa(this);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Tipo_puntero p) {
		if (primeraPasada) { 	// vincula1
			if (!p.tipo().tipoDefinido())
				p.tipo().procesa(this);
		} else {				// vincula2
			if (p.tipo().tipoDefinido()) {
				p.tipo().vinculo = ts.vinculoDe(p.tipo().cadena());
				if (p.tipo().vinculo == null)
					throw ;// Identificador de Tipo no definido
			} else {
				p.tipo().procesa(this);
			}
		}
	}

	@Override
	public void procesa(Int_t p) {
		if (primeraPasada) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}

	@Override
	public void procesa(Real_t p) {
		if (primeraPasada) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}

	@Override
	public void procesa(Bool_t p) {
		if (primeraPasada) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}
	
	@Override
	public void procesa(String_t p) {
		if (primeraPasada) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}

	@Override
	public void procesa(Tipo_registro p) {
		if (primeraPasada) { 	// vincula1
			sr.abreAmbitoRegistro();
			p.lista_parametros_registro().procesa(this);
			sr.cierraAmbitoRegistro();
		} else {				// vincula2
			p.lista_parametros_registro().procesa(this);
		}
	}

	@Override
	public void procesa(Muchas_lista_param_reg p) {
		if (primeraPasada) { 	// vincula1
			p.lista_parametros_registro().procesa(this);
			p.parametro_registro().procesa(this);
		} else {				// vincula2
			p.lista_parametros_registro().procesa(this);
			p.parametro_registro().procesa(this);
		}
	}

	@Override
	public void procesa(Una_lista_param_reg p) {
		if (primeraPasada) { 	// vincula1
			p.parametro_registro().procesa(this);
		} else {				// vincula2
			p.parametro_registro().procesa(this);
		}
	}

	@Override
	public void procesa(Param_reg p) {
		if (primeraPasada) { 	// vincula1
			p.tipo().procesa(this);
			if (sr.duplicadoRegistro(p.cadena()))
				throw ;// Identificador registro duplicado
			else
				sr.apuntaRegistro(p.cadena());
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Tipo_definido p) {
		if (primeraPasada) { 	// vincula1
		} else {				// vincula2
			/* noop */
		}
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
			throw ;//Identificador no declarado
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
			throw ;//Identificador no declarado 
	}
}
