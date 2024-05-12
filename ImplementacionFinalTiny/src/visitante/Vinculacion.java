package visitante;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import asint.SintaxisAbstractaTiny.*;
import errores.Errores;
import errores.VinculacionIdentificadorDuplicado;
import errores.VinculacionIdentificadorNoDefinido;

public class Vinculacion extends ProcesamientoDef {
	
	private TablaDeSimbolosAnidados ts; 
	private SimbolosRegistroAnidados sr;
	private Errores errores;
	
	public Vinculacion(Errores e) {
		ts = new TablaDeSimbolosAnidados();
		sr = new SimbolosRegistroAnidados();
		errores = e;
	}
	
	//Estas dos clases se usan para la parte del pre tipado encargada de evitar la duplicidad en los campos registro
	public class SimbolosRegistro extends HashSet<String>{}
	
	public class SimbolosRegistroAnidados extends ArrayDeque<SimbolosRegistro> {
		public void abreRegistro() {
			addFirst(new SimbolosRegistro());
		}
		
		public void cierraRegistro() {
			removeFirst();
		}
		
		public boolean duplicadoRegistro(String id) {
			return getFirst().contains(id);
		}
		
		public void apuntaRegistro(String id) {
			getFirst().add(id);
		}
	}
	
	public class TablaDeSimbolos extends HashMap<String, Nodo> {
		private Boolean primeraPasada;
		
		public void iniPrimeraPasada() {
			primeraPasada = new Boolean(true);
		}
		
		public void finPrimeraPasada() {
			primeraPasada = false ;
		}
		
		public boolean necesitaNuevaPasada() {
			return primeraPasada != null;
		}
		
		public boolean primeraPasada() {
			return primeraPasada;
		}
		
	}
	
	public class TablaDeSimbolosAnidados extends ArrayDeque<TablaDeSimbolos> {
		
		public void abreAmbito() {
			addFirst(new TablaDeSimbolos());
		}
		
		public void cierraAmbito() {
			removeFirst();
		}
		
		private Nodo vinculoDe(String id) {
			Iterator<TablaDeSimbolos> i = iterator();
			while (i.hasNext()) {
				TablaDeSimbolos tsCurrent = i.next();
				if (tsCurrent.containsKey(id))
					return tsCurrent.get(id);
			}
			return null;
		}
		
		public void vincular(Nodo p, String id) {
			p.vinculo = vinculoDe(id);
			if (verbose)
				System.out.println("Vinculando '"+p+"' a simbolo "+p.vinculo);
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
				errores.nuevoError(new VinculacionIdentificadorDuplicado(p));
			else {
				inserta(id, p);
				if (verbose)
					System.out.println("Insertando '"+id+"' de:\nf:"+p.leeFila()+";c:"+p.leeCol()+" en la Tabla de Simbolos");
			}
		}
	
		/*
		 * Gestionamos nuevas pasadas anidadas
		 * 
		 * */
		
		public void iniPrimeraPasada() {
			getFirst().iniPrimeraPasada();
		}
		
		public void finPrimeraPasada() {
			getFirst().finPrimeraPasada();
		}
		
		public boolean primeraPasada() {
			Iterator<TablaDeSimbolos> i = iterator();
			while (i.hasNext()) {
				TablaDeSimbolos tsCurrent = i.next();
				if (tsCurrent.necesitaNuevaPasada())
					return tsCurrent.primeraPasada();
			}
			System.err.println("No se han gestionado bien los ámbitos y las pasadas por ellos");
			return false;
		}
	}
	
	// procesa() == metodo vinculacion de la especificación
	
	@Override
	public void procesa(Prog_tiny p) {
		p.bloque().procesa(this);
		if ( errores.hayError() )
			errores.traza();
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
		ts.iniPrimeraPasada();
		p.lista_declaraciones().procesa(this);
		ts.finPrimeraPasada();
		//vincula2
		p.lista_declaraciones().procesa(this);
	}
	
	@Override
	public void procesa(No_lista_opt_decs p) { /* noop */ }

	@Override
	public void procesa(Muchas_lista_decs p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.lista_declaraciones().procesa(this);
			p.declaracion().procesa(this);
		} else {				// vincula2
			p.lista_declaraciones().procesa(this);
			p.declaracion().procesa(this);
		}
	}

	@Override
	public void procesa(Una_lista_dec p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.declaracion().procesa(this);
		} else {				// vincula2
			p.declaracion().procesa(this);
		}
	}

	@Override
	public void procesa(Dec_var p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.tipo().procesa(this);
			ts.recolecta(p.cadena(), p);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Dec_tipo p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.tipo().procesa(this);
			ts.recolecta(p.cadena(), p);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Dec_proc p) {
		if (ts.primeraPasada()) { 	// vincula1
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
		if (ts.primeraPasada()) { 	// vincula1
			p.lista_parametros_formales().procesa(this);
			p.num_elems = p.lista_parametros_formales().num_elems;
		} else {				// vincula2
			p.lista_parametros_formales().procesa(this);
		}
	}

	@Override
	public void procesa(No_lista_opt_param_form p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.num_elems = 0;
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}

	@Override
	public void procesa(Muchas_lista_param_form p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.lista_parametros_formales().procesa(this);
			p.parametro_formal().procesa(this);
			
			p.num_elems = p.lista_parametros_formales().num_elems + 1;
		} else {				// vincula2
			p.lista_parametros_formales().procesa(this);
			p.parametro_formal().procesa(this);
		}
	}

	@Override
	public void procesa(Una_lista_param_form p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.parametro_formal().procesa(this);
			
			p.num_elems = 1;
		} else {				// vincula2
			p.parametro_formal().procesa(this);
		}
	}

	@Override
	public void procesa(Param_form_ref p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.tipo().procesa(this);
			ts.recolecta(p.cadena(), p);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Param_form p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.tipo().procesa(this);
			ts.recolecta(p.cadena(), p);
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Tipo_array p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.tipo().procesa(this);
//			if (Integer.getInteger(p.cadena()) <= 0)
//				errores.nuevoError(); // Error de pretipado que aprovechamo a comprobar aquí
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Tipo_puntero p) {
		if (ts.primeraPasada()) { 	// vincula1
			if (!p.tipo().tipoDefinido())
				p.tipo().procesa(this);
		} else {				// vincula2
			if (p.tipo().tipoDefinido()) {
				ts.vincular(p.tipo(), p.tipo().cadena());
				if (p.tipo().vinculo == null)
					errores.nuevoError(new VinculacionIdentificadorNoDefinido(p.tipo()));
			} else {
				p.tipo().procesa(this);
			}
		}
	}

	@Override
	public void procesa(Int_t p) {
		if (ts.primeraPasada()) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}

	@Override
	public void procesa(Real_t p) {
		if (ts.primeraPasada()) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}

	@Override
	public void procesa(Bool_t p) {
		if (ts.primeraPasada()) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}
	
	@Override
	public void procesa(String_t p) {
		if (ts.primeraPasada()) { 	// vincula1
			/* noop */
		} else {				// vincula2
			/* noop */
		}
	}

	@Override
	public void procesa(Tipo_registro p) {
		if (ts.primeraPasada()) { 	// vincula1
			sr.abreRegistro();
			p.lista_parametros_registro().procesa(this);
			sr.cierraRegistro();
		} else {				// vincula2
			p.lista_parametros_registro().procesa(this);
		}
	}

	@Override
	public void procesa(Muchas_lista_param_reg p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.lista_parametros_registro().procesa(this);
			p.parametro_registro().procesa(this);
		} else {				// vincula2
			p.lista_parametros_registro().procesa(this);
			p.parametro_registro().procesa(this);
		}
	}

	@Override
	public void procesa(Una_lista_param_reg p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.parametro_registro().procesa(this);
		} else {				// vincula2
			p.parametro_registro().procesa(this);
		}
	}

	@Override
	public void procesa(Param_reg p) {
		if (ts.primeraPasada()) { 	// vincula1
			p.tipo().procesa(this);
			if (sr.duplicadoRegistro(p.cadena()))
				errores.nuevoError(new VinculacionIdentificadorDuplicado(p));
			else
				sr.apuntaRegistro(p.cadena());
		} else {				// vincula2
			p.tipo().procesa(this);
		}
	}

	@Override
	public void procesa(Tipo_definido p) {
		if (ts.primeraPasada()) {
			ts.vincular(p, p.cadena());
			if (p.vinculo == null) {
				errores.nuevoError(new VinculacionIdentificadorNoDefinido(p));
				return;
			}
		}
		else {
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
		p.bloque1().procesa(this);
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
		ts.vincular(p, p.cadena());
		if (p.vinculo == null) {
			errores.nuevoError(new VinculacionIdentificadorNoDefinido(p));
			return;
		}
	}

	@Override
	public void procesa(Inst_comp p) {
		p.bloque().procesa(this);
	}


	@Override
	public void procesa(Si_lista_opt_param p) {
		p.lista_parametros().procesa(this);
		
		p.num_elems = p.lista_parametros().num_elems; 
	}

	@Override
	public void procesa(No_lista_opt_param p) {
		p.num_elems = 0;
	}

	@Override
	public void procesa(Muchas_lista_param p) {
		p.lista_parametros().procesa(this);
		p.expresion().procesa(this);
		
		p.num_elems = p.lista_parametros().num_elems + 1;
	}

	@Override
	public void procesa(Una_lista_param p) {
		p.expresion().procesa(this);
		
		p.num_elems = 1;
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
		ts.vincular(p, p.cadena());
		if (p.vinculo == null) {
			errores.nuevoError(new VinculacionIdentificadorNoDefinido(p));
			return;
		}
	}
}
