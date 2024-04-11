package impresion;

import java.util.HashMap;
import java.util.Map;

import asint.SintaxisAbstractaTiny.*;

public class Impresion  {
	
    
   /* 
    * public class ECteNoDefinida extends RuntimeException {
        public ECteNoDefinida(String msg) {
            super(msg);
        }
    }
    public class ECteDuplicada extends RuntimeException {
        public ECteDuplicada(String msg) {
            super(msg);
        }
    }
    * 
    * 
    * private Map<String,Float> env;
    private Map<String,Boolean> envB; //////////////////////////¿?¿¿¿¿
*/    
    public Impresion() {
    }
    
    /*private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.print("(");};
        opnd.procesa(this);
        if(opnd.prioridad() < np) {System.out.print(")");};        
    }
    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        imprimeOpnd(opnd0,np0);
        System.out.print(" "+op+" ");
        imprimeOpnd(opnd1,np1);
    }*/
   
    //y si la instruccion es de tipo boolean ????????????
    public void imprimeProg(Prog_tiny prog) { //evalua el programa 
    	
        System.out.println("{");
        System.out.println();

        imprimeBloque(prog.bloque);
        
        System.out.println();
        System.out.print("}");

    	
    }
    
    
    public void imprimeBloque(Blq b) { 
    	
    	///////////¿?¿?¿?¿?
    	
    	imprimeListaOptDecs(b.lista_opt_declaraciones);
        System.out.println();
    	imprimeListaOptInst(b.lista_opt_instrucciones);
    }
    
    public void imprimeListaOptDecs(LOptDecs l) {
    	
    	if(claseDe(l,Si_lista_opt_decs.class)) {
    		imprimeMuchasListaDecs(l.lista_declaraciones);
    	}
    	
        System.out.println("&&");

    	
    }
    public void imprimeMuchasListaDecs(LDecs l) {
    	
	   	 if(claseDe(l,Muchas_lista_decs.class)) { 
	   		imprimeMuchasListaDecs(l.lista_declaraciones); //recursion 
	         System.out.println(";");

	     }
	
	   	 imprimeDec(l.declaracion);
    }
    //imprimeUnaListaDec???????
    
    public void imprimeDec(Dec d) {
    	
    	 if(claseDe(d.tipo,Dec_var.class)) { 
    		 imprimeDec_var(d.tipo, d.cadena);
    	 }
    	 else if (claseDe(d.tipo,Dec_proc.class)) {
    		 imprimeDec_proc(d.cadena, d.lista_opt_parametros_formales, d.bloque);
    		 
    	 }
    	 else { //Dec_tipo 
    		 imprimeDec_tipo(d.tipo, d.cadena);
    	  }
    	 	 
    }
    
    public void imprimeDec_var(Tipo t, String s) {
    	imprimeTipo(t); //
    	System.out.println();
        System.out.println(s);
    }
    
    public void imprimeDec_proc(String s, LOptParamForm l, Blq blq) {
    	System.out.println("<proc>");
        //print string - posicion ???????????????????
		 imprimeLOptParamForm(l);
		 System.out.println();
		 imprimeBloque(blq);
    }
    
    public void imprimeDec_tipo(Tipo t, String s) {
    	System.out.println("<type>");
	    imprimeTipo(t);
         System.out.println(s);
    }
    
    public void imprimeTipo(Tipo t) {
    	
	    if(claseDe(t,Tipo_array.class)) { 
	    	imprimeTipoArray(t, t.cadena);
	   	 }
	   	 else if (claseDe(t,Tipo_puntero.class)) {
	   		imprimeTipoPuntero(t);
	   	 }
	   	 else if (claseDe(t,Tipo_registro.class)){  	
	   		imprimeTipoRegistro(t.lista_parametros_registro );
	   	 }
		 else if (claseDe(t,Tipo_definido.class)){ 	
			 imprimeTipoDefinido(t.cadena);
	  	 }
    	
    }
    
    public void imprimeTipoArray (Tipo t, String n) {
    	
    	System.out.println(t.tipo);
		System.out.print("[" + n  + "]");
    }
    
	public void imprimeTipoPuntero (Tipo t) {
		System.out.print("^");
		System.out.print(t.tipo);
	    	
	}
	public void imprimeTipoRegistro(LParamReg lista_parametros_registro ) {
		System.out.println("<struct>");
		System.out.print("{");
		imprimeMuchasListaParamReg(lista_parametros_registro);
		System.out.print("}");
    }
	public void imprimeTipoDefinido (String s) {
		System.out.print(s);
    }
    
	
	
	
	
	
    public void imprimeMuchasListaParamReg(LParamReg l) {
    	
	   	 if(claseDe(l,Muchas_lista_param_reg.class)) { 
	   		imprimeMuchasListaParamReg(l.lista_parametros_registro); //recursion 
	         System.out.println(",");

	     }
	
	   	 imprimeParamReg(l.parametro_registro);
   }
    
    
    
    
    public void imprimeListaOptInst(LOptInst l) {
    	
    	if(claseDe(b.lista_opt_instrucciones,Si_lista_opt_inst.class)) {
        	imprimeListaOptInst(b.lista_opt_instrucciones);
         }
    }
    
    /* consEnv(prog.bloque.lista_opt_declaraciones); //evaluar las decl
        return evaluarInst(prog.bloque.lista_opt_instrucciones); //evaluar las inst
    }*/
    private void consEnv(LOptDecs optDecs) { // evalua las dec
    	if(claseDe(optDecs,Si_lista_opt_decs.class)) {
            consEnv(optDecs.lista_declaraciones);
        }
    }
    

    /*private void consEnv(LDecs decs) {
        if(claseDe(decs,Muchas_decs.class)) {
            consEnv(decs.ldecs());
        }
        consEnv(decs.dec());
    }*/
    
    @SuppressWarnings("unused")
	private void consEnv(LDecs decs) {
    	if(claseDe(decs, Muchas_lista_decs.class)) {//evaluar las dec
            consEnv(decs.lista_declaraciones);
        } else if(claseDe(decs, Una_lista_decs.class)) {//evalua 1 dec
            consEnv(decs.declaracion);
        }
    }
    
    
    /*private void consEnv(Dec dec) {
        if(env.containsKey(dec.iden())) {
            throw new ECteDuplicada("Cte duplicada: "+dec.iden()+
                                     " fila:"+dec.leeFila()+" col:"+dec.leeCol()); 
        }
        else {
            env.put(dec.iden(),eval(dec.exp()));
        }
    }*/
    private void consEnv(Dec dec) { //evalua 1 dec
    	if(claseDe(dec, Dec_var.class)) {
    		Dec_var decVar = (Dec_var) dec;
    		 if(env.containsKey(decVar.cadena) || envB.containsKey(decVar.cadena))  {
    			 throw new ECteDuplicada("Cte duplicada: "+ decVar.cadena); 
    		 } else {
    			//hay que ver de que tipo es, numerica o booleana
    			 env.put(decVar.cadena, eval(dec.evaluarExpresion(dec.exp()))); //dec deberia tener un parametro de expresion
    			 envB.put(decVar.cadena, eval(dec.evaluarCondicion(dec.exp()))); 
    			 
            }
        }
    	else if (claseDe(dec, Dec_tipo.class)) {
    		
    	}
    	else if (claseDe(dec, Dec_proc.class)) {
    		
    		//evaluar  lista_opt_parametros_formales bloque rec

    	}
    }
 
    
    private float evaluarInst(LOptInst optInst) { //evalua las inst
    	if(claseDe(optInst, Si_lista_opt_inst.class)) {
            return evaluarInst(optInst.lista_instrucciones);
        }
    	
    	return 0;
    }
    
    private float evaluarInst(LInst inst) {//evaluar rec las instr
    	if(claseDe(inst, Muchas_lista_inst.class)) {
            return evaluarInst(inst.lista_instrucciones);
        } else if (claseDe(inst, Una_lista_inst.class)) {
            return evaluarInst(inst.instruccion);
        }
    	
    	return 0;
    }
    
    private float evaluarInst(Inst inst) { //evalua 1 inst
    
    		//tener en cuenta que 3+4+6
    	
    	if (claseDe(inst, Inst_eval.class)) {
            return evaluarExpresion(inst.expresion);
                        
        } else if (claseDe(inst, Inst_if.class)) {
            if( evaluarCondicion(inst.expresion)) {
            	 
            	 consEnv(inst.bloque1.lista_opt_declaraciones); //evaluar las decl
                return evaluarInst(inst.bloque1.lista_opt_instrucciones); //evaluar las inst
            }
            
        } else if (claseDe(inst, Inst_if_else.class)) {
            if (evaluarCondicion(inst.expresion)) {
            	 consEnv(inst.bloque1.lista_opt_declaraciones); //evaluar las decl
                return evaluarInst(inst.bloque1.lista_opt_instrucciones); //evaluar las inst
            }
            else {
            	 consEnv(inst.bloque2.lista_opt_declaraciones); //evaluar las decl
                return evaluarInst(inst.bloque2.lista_opt_instrucciones); //evaluar las inst
            }
            
        } else if (claseDe(inst, Inst_while.class)) {
            
            while (evaluarCondicion(inst.expresion)) { // va camiando ????
            	 consEnv(inst.bloque1.lista_opt_declaraciones); //evaluar las decl
               return evaluarInst(inst.bloque1.lista_opt_instrucciones); //evaluar las inst
            }
            
        } else if (claseDe(inst, Inst_read.class)) {
            Expresion expresion = ((Inst_read) instruccion).expresion;
            // Lógica para leer un valor y asignarlo a la expresión
            
        } else if (claseDe(inst, Inst_write.class)) {
            Expresion expresion = ((Inst_write) instruccion).expresion;
            // Lógica para escribir el valor de la expresión
            
        } else if (claseDe(inst, Inst_nl.class)) {
            // Salto de línea
            System.out.println();
            
        } else if (claseDe(inst, Inst_new.class)) {
            Expresion expresion = ((Inst_new) instruccion).expresion;
            // Lógica para crear un nuevo objeto
            
        } else if claseDe(inst, Inst_delete.class)) {
            Expresion expresion = ((Inst_delete) instruccion).expresion;
            // Lógica para eliminar un objeto
            
            
        } else if (claseDe(inst, Inst_call.class)) {
            String identificador = ((Inst_call) instruccion).identificador;
            ListaOptParametros parametros = ((Inst_call) instruccion).parametros;
            
            // Lógica para llamar a un procedimiento
        } else if (claseDe(inst, Inst_bloque.class)) {
            Bloque bloque = ((Inst_bloque) instruccion).bloque;
            evaluarBloque(bloque);
            
            
        } else {
            throw new IllegalArgumentException("Instrucción no válida: " + inst);
        }
    	
    }
    
    //env.put(dec.iden(),eval(dec.exp()));
    /*private float eval(Exp exp) {
    	//bool string ????????????????????????????????????????????????????????????????????
        if(claseDe(exp,Lit_ent.class) || claseDe(exp,Lit_real.class)) {
            return Float.valueOf(exp.valor()).floatValue();
        }
        else if(claseDe(exp,Iden.class)) {
            if(! env.containsKey(exp.iden())) {
                throw new ECteNoDefinida("Cte indefinida: "+exp.iden()+
                                        " fila:"+exp.leeFila()+" col:"+exp.leeCol()); 
            }
            else {
                return env.get(exp.iden());
            }
        }
        else {
            float v1 = eval(exp.opnd0());
            float v2 = eval(exp.opnd1());
            if(claseDe(exp,Suma.class)) {
                return v1+v2;
            }
            else if(claseDe(exp,Resta.class)) {
                return v1-v2;
            }
            else if(claseDe(exp,Mult.class)) {
                return v1*v2;
            }
            else {
                return v1/v2;
            }
        }
    } */
    
  

	private float evaluarExpresion(Exp exp) {
        if(claseDe(exp,Lit_ent.class) || claseDe(exp,Lit_real.class)) {
            return Float.valueOf(exp.valor()).floatValue();
        }
        /*else if (claseDe(exp,True_e.class) || claseDe(exp,False_e.class) || claseDe(exp,Null_e.class)) { //NULL?????????????
        	return Boolean.valueOf(exp.valor());
        }
        
        else if (claseDe(exp,Cadena.class)) { //string
        	return String.valueOf(exp.valor());

        }*/
        else if(claseDe(exp,Iden.class)) {
            if(! env.containsKey(exp.iden())) { //falta con envB
                throw new ECteNoDefinida("Cte indefinida: "+exp.iden()+
                                        " fila:"+exp.leeFila()+" col:"+exp.leeCol()); 
            }
            else {
                return env.get(exp.iden());
            }
        }
        else {
            float v1 = evaluarExpresion(exp.opnd0());
            float v2 = evaluarExpresion(exp.opnd1());
            
            if(claseDe(exp,Suma.class)) {
                return v1+v2;
            }
            else if(claseDe(exp,Resta.class)) {
                return v1-v2;
            }
            else if(claseDe(exp,Mult.class)) {
                return v1*v2;
            }
            else {
                return v1/v2;
            }
        }
    } 
    
	private boolean evaluarCondicion(Exp exp) {
		
		if (claseDe(exp,True_e.class) || claseDe(exp,False_e.class)) { 
        	return Boolean.valueOf(exp.valor());
        }
		else if(claseDe(exp,Iden.class)) {
	            if(! envB.containsKey(exp.iden())) {
	                throw new ECteNoDefinida("Cte indefinida: "+exp.iden()+
	                                        " fila:"+exp.leeFila()+" col:"+exp.leeCol()); 
	            }
	            else {
	                return envB.get(exp.iden());
	            }
	    }
		 else {
	            float v1 = evaluarExpresion(exp.opnd0());
	            float v2 = evaluarExpresion(exp.opnd1());
	            
	            if(claseDe(exp,Mayor.class)) {
	                return v1 > v2;
	            }
	            else if(claseDe(exp,Mayor_igual.class)) {
	                return v1 >= v2;
	            }
	            else if(claseDe(exp,Menor.class)) {
	                return v1 < v2;
	            }
	            else if(claseDe(exp,Menor_igual.class)) {
	                return v1 <= v2;
	            }
	            else if(claseDe(exp,Comparacion.class)) {
	                return v1 == v2;
	            }
	            else if(claseDe(exp, Distinto.class)) {
	                return v1 != v2;
	            }
	      }

		
		return false;
	}
	
	
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    }    
}