package Procesamientos;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import asint.SintaxisAbstractaTiny.*;


public class ProcesamientoEtiquetado implements IProcesamientoE {
	
	private Stack<Dec> proc_pendientes = new Stack<>();
	private int etq = 0;

	@Override
	public void etiquetado(Prog_tiny p) {
		p.setPrim(etq);
		p.bloque().etiquetado(this);
		p.setSig(etq);
			
	}

	@Override
	public void etiquetado(Bloque p) {
		p.setPrim(etq);
		//recolecta_procs(p.lista_opt_declaraciones());
		p.lista_opt_instrucciones().etiquetado(this);
		etq++;
		while(!proc_pendientes.isEmpty()){
			
			Dec cima = proc_pendientes.pop(); //cogemos la cima y la desapilamos
			// let cima = dec_proc(...) in
			cima.setPrim(etq);
			etq++;
			//recolecta_procs(blq);
			etq+=2;
			cima.setSig(etq);

		}

		p.setSig(etq);
	}


	@Override
	public void etiquetado(Tipo_array p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Tipo_puntero p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Int_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Real_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Bool_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(String_t p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Tipo_registro p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Tipo_definido p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Muchas_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Una_lista_param_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Param_reg p) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void etiquetado(Si_lista_opt_inst p) {
		p.setPrim(etq);
		p.lista_instrucciones().etiquetado(this);
		p.setSig(etq);
	}

	@Override
	public void etiquetado(No_lista_opt_inst p) {}

	@Override
	public void etiquetado(Muchas_lista_inst p) {
		p.setPrim(etq);	
		p.lista_instrucciones().etiquetado(this);
		p.instruccion().etiquetado(this);
		p.setSig(etq);
	}

	@Override
	public void etiquetado(Una_lista_inst p) {
		p.setPrim(etq);
		p.instruccion().etiquetado(this);
		p.setSig(etq);
	}

	@Override
	public void etiquetado(Inst_eval p) {
		p.setPrim(etq);
		p.expresion().etiquetado(this);
		etq++;
		p.setSig(etq);
	}

	/*@Override
	public void etiquetado(Inst_if) {
		p.setPrim(etq);
		p.expresion().etiquetado(this);
		p.expresion().etiquetado_acc_val(this);
		etq++;
		p.bloque().etiquetado(this);
		p.setSig(etq);
	}*/
	
	@Override
	public void etiquetado(Inst_if_else p) {
		p.setPrim(etq);
		p.expresion().etiquetado(this);
		//p.expresion().etiquetado_acc_val(this);
		etq++;
		p.bloque().etiquetado(this);
		p.setSig(etq);
	}

	@Override
	public void etiquetado(Si_else p) {
		
	}

	@Override
	public void etiquetado(No_else p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Inst_while p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Inst_read p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Inst_write p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Inst_nl p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Inst_new p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Inst_delete p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Inst_call p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Inst_comp p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Si_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(No_lista_opt_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Muchas_lista_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Una_lista_param p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Asignacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Mayor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Mayor_igual p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Menor p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Menor_igual p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Comparacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Distinto p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Suma p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Resta p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(And p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Or p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Mult p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Div p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Mod p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Not_unario p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Resta_unario p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Indexacion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Acc_reg p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Indireccion p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Lit_ent p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Lit_real p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(True_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(False_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Null_e p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Cadena p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void etiquetado(Iden p) {
		// TODO Auto-generated method stub
		
	}

}
