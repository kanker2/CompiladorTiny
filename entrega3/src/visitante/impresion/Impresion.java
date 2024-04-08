package visitante.impresion;

import visitante.asint.ProcesamientoDef;
import visitante.asint.SintaxisAbstractaTiny.*;


public class Impresion extends ProcesamientoDef {

	  private void imprimeOpnd(Exp opnd, int np) {
	        if(opnd.prioridad() < np) {System.out.print("(");};
	        opnd.procesa(this);
	        if(opnd.prioridad() < np) {System.out.print(")");};        
	    }
	    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
	        imprimeOpnd(opnd0,np0);
	        System.out.print(" "+op+" ");
	        imprimeOpnd(opnd1,np1);
	    }
	   /* public void procesa(Prog prog) {
	         System.out.println("evalua");
	         System.out.print("  ");
	         prog.exp().procesa(this);
	         System.out.println();
	         prog.decs().procesa(this);
	         System.out.println();
	    }  */
	
	    public void procesa(Prog_tiny prog) {
	    
	        System.out.println("{");
	    	prog.bloque.procesa(this);
	        System.out.println();
	        System.out.print("}");

	    }  
	    
	    public void procesa(Bloque blq) {
		    
	        blq.lista_opt_declaraciones.procesa(this);
	        System.out.println();
	        blq.lista_opt_instrucciones.procesa(this);

	        
	    }  
	    
	    public void procesa(Si_lista_opt_decs loi) {
		    
	        loi.lista_declaraciones.procesa(this);
	        System.out.print("&&");
	        
	    } 
	    public void procesa(No_lista_opt_decs loi) {}
	    
	    public void procesa(Muchas_lista_decs loi) {
		    
	        loi.lista_declaraciones.procesa(this);
	        System.out.print(";");
	        loi.declaracion.procesa(this);

	        
	    } 
	    
	    public void procesa(Una_lista_decs loi) {
	        loi.declaracion.procesa(this);
	    } 
	    
   
	    public void procesa(Dec_var dec) { //TODO

	    	
	    	//imprime(TpArray)
	    	//imprime(Id)

	    	dec.tipo.procesa(this);
	    	
	       /* System.out.println();
	         System.out.print("  "+dec.iden()+"=");
	         dec.exp().procesa(this);*/
	    }
	    public void procesa(Dec_tipo dec) {//TODO
	    	
	    	//print “<type>”
	    	//imprime(TpArray)
	    	//imprime(Id)

	        System.out.print("<type>");
	    	dec.tipo.procesa(this);


	       
	    }
	    public void procesa(Dec_proc dec) {//TODO

	    	/*imprime(CabProc)
			imprime(Blq)
	    	 */
	    	
	    	dec.b.procesa(this);
	    	
	    }
	    
	    /*imprime(cabecera_proc(Id,LOptParamForm)):
				print “<proc>”
				imprime(Id)
				imprime(LOptParamForm)
			*/
	    
	    public void procesa(Si_lista_opt_param_form lopf) {
	    	lopf.lpf.procesa(this);
	    }
	    
	    public void procesa(No_lista_opt_param_form lopf) {
	    }
	
	    public void procesa(Muchas_lista_param_form lpf) {
	    	lpf.lpf.procesa(this);
	        System.out.print(",");
	    	lpf.pf.procesa(this);

	    }
	    
	    public void procesa(Una_lista_param_form lpf) {
	    	lpf.pf.procesa(this);
	    }
	    
	    public void procesa(Param_form_ref pf) {
	    	/*imprime(TpArray)
				print “ &”
				imprime(Id)
			*/
	    	
	    	pf.t.procesa(this);
	        System.out.print("&");


	    }
	    
	    public void procesa(Param_form pf) {
	    	/*imprime(TpArray)
			imprime(Id)
		*/
	    	
	    	pf.t.procesa(this);
	    	
	    }
	    public void procesa(Tipo_array ta) {
	    	
	    	ta.t.procesa(this);
	        System.out.print("[" + ta.cad + "]");

	    }
	    
	    public void procesa(Tipo_puntero tp) {
	    	
	        System.out.print("^");
	    	tp.t.procesa(this);

	        //imprime(TpPunt)
	    }
	    
	    public void procesa(Tipo_registro tr) {
	    	
	        System.out.print("<struct>{");
	    	tr.lpr.procesa(this);
	        System.out.print("}");

	    }
	    
	    public void procesa(Tipo_definido tr) {
	    	//imprime(Id)
	        System.out.print(tr.cad); ////////¿?¿?¿?¿¿?¿?

	    }
	    
	    public void procesa(Muchas_lista_param_reg lpr) {
	    	lpr.lpr.procesa(this);
	        System.out.print(","); 
	        lpr.pr.procesa(this);

	    	

	    }
		 public void procesa(Una_lista_param_reg lpr) {
			 lpr.pr.procesa(this);
		
		 }
		 public void procesa(Param_reg pr) {
		 	pr.t.procesa(this);
	        System.out.print(pr.cad); ////////¿?¿?¿?¿¿?¿?
		
		 }
	    
		 public void procesa(Si_lista_opt_inst loi) {
			 loi.li.procesa(this);
		
		 }
		 public void procesa(No_lista_opt_inst loi) {
		 }
		 public void procesa(Muchas_lista_inst li) {
			 li.li.procesa(this);
		     System.out.print(";"); 
		     li.i.procesa(this);
		
		 }
		 public void procesa(Una_lista_inst li) {
			 li.i.procesa(this);
		
		 }
		 public void procesa(Inst_eval i) {
			 System.out.print("@"); 
			 i.e.procesa(this);
		 }
		 
		 public void procesa(Inst_if i) {
			 System.out.print("<if>"); 
			 i.e.procesa(this);
			 i.b.procesa(this);
			 

		 }
		 public void procesa(Inst_if_else i) {
			 System.out.print("<if>"); 
			 i.e.procesa(this);
			 i.b1.procesa(this);
			 System.out.print("<else>"); 
			 i.b2.procesa(this);
			 

		 }
		 public void procesa(Inst_while i) {
			 System.out.print("<while>"); 
			 i.e.procesa(this);
			 i.b.procesa(this);

		 }
		 public void procesa(Inst_read i) {
			 System.out.print("<read>"); 

			 i.e.procesa(this);

		 }
		 public void procesa(Inst_write i) {
			 System.out.print("<write>"); 

			 i.e.procesa(this);

		 }
		 public void procesa(Inst_nl i) {
			 System.out.print("<nl>"); 
		 }
		 public void procesa(Inst_new i) {
			 System.out.print("<new>"); 
			 i.e.procesa(this);

		 }
		 public void procesa(Inst_delete i) {
			 System.out.print("<delete>"); 

			 i.e.procesa(this);

		 }
		 public void procesa(Inst_call i) {
			 System.out.print("<call>"); 
			 System.out.print(i.cad); 
			 System.out.print("("); 
			 i.lop.procesa(this);
			 System.out.print(")"); 


		 }
		 public void procesa(Inst_comp i) {
			 i.b.procesa(this);

		 }
		 
		 public void procesa(Si_lista_opt_param i) {
			 i.lp.procesa(this);

		 }
		 public void procesa(No_lista_opt_param i) {
		 }
		 public void procesa(Muchas_lista_param i) {
			 i.lp.procesa(this);
			 System.out.print(","); 
			 i.e.procesa(this); 

		 }
		 public void procesa(Una_lista_param i) {
			 i.e.procesa(this); 
		 }
		 
		 
		 public void procesa(Asignacion e) {
			   imprimeExpBin(e.op1, "=", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Mayor e) {
			   imprimeExpBin(e.op1, ">", e.op2,e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Mayor_igual e) {
			   imprimeExpBin(e.op1, ">=", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Menor e) {
			   imprimeExpBin(e.op1, "<", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Menor_igual e) {
			   imprimeExpBin(e.op1, "<=", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Comparacion e) {
			   imprimeExpBin(e.op1, "==", e.op2,e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Distinto e) {
			   imprimeExpBin(e.op1, "!=", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Suma e) {
			   imprimeExpBin(e.op1, "+", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Resta e) {
			   imprimeExpBin(e.op1, "-", e.op2,e.prioridad(),  e.prioridad() +1);
		 }
		 public void procesa(And e) {
			   imprimeExpBin(e.op1, "and", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Or e) {
			   imprimeExpBin(e.op1, "or", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Mult e) {
			   imprimeExpBin(e.op1, "*", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Div e) {
			   imprimeExpBin(e.op1, "/", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Mod e) {
			   imprimeExpBin(e.op1, "%", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Resta_unario e) {
			   //imprimeExpBin(e.op1, "=", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
	    
		 public void procesa(Not_unario e) {
			  // imprimeExpBin(e.op1, "=", e.op2, e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Indexacion e) {
			   imprimeExpBin(e.op1, "=", e.op2,e.prioridad(),  e.prioridad() +1);

		 }
		 public void procesa(Acc_reg e) {
			  // imprimeExpBin(e.op1, "=", e.op2, 0,  1);

		 }
		 public void procesa(Indireccion e) {
			  // imprimeExpBin(e.op1, "=", e.op2, 0,  1);

		 }
		 
		 
		 //falta poner en la sintaxis, la prioridad de los operandos TODO
		 public void procesa(Lit_ent l) {
			 System.out.print(l.valor()); 

		 }
		 public void procesa(Lit_real l) {
			 System.out.print(l.valor()); 

		 }
		 public void procesa(True_e l) {
			 System.out.print("<true>"); 

		 }
		 public void procesa(False_e l) {
			 System.out.print("<false>"); 

		 }
		 public void procesa(Null_e l) {
			 System.out.print("<null>"); 

		 }
		 public void procesa(Cadena l) {
			 System.out.print(l.valor()); 

		 }
		 public void procesa(Iden l) {
			 System.out.print(l.iden()); 

		 }
		
		 
}
