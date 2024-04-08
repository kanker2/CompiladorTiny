package asint;

import java.util.HashMap;
import java.util.Map;



public class SintaxisAbstractaEval {
      
    private static void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.print("(");};
        opnd.imprime();
        if(opnd.prioridad() < np) {System.out.print(")");};        
    }
    private static void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        imprimeOpnd(opnd0,np0);
        System.out.print(" "+op+" ");
        imprimeOpnd(opnd1,np1);
    }
    
    public static abstract class Nodo  {
       public Nodo() {
		   fila=col=-1;
       }   
	   private int fila;
	   private int col;
	   public Nodo ponFila(int fila) {
		    this.fila = fila;
            return this;			
	   }
	   public Nodo ponCol(int col) {
		    this.col = col;
            return this;			
	   }
	   public int leeFila() {
		  return fila; 
	   }
	   public int leeCol() {
		  return col; 
	   }
           public abstract void imprime();
    }
    

    public static abstract class Exp  extends  Nodo {
       public Exp() {
          super();
       }   
       public abstract int prioridad();
    }
   
    
    private static abstract class ExpBin extends Exp {
        protected Exp opnd0;
        protected Exp opnd1;
        public ExpBin(Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }
    }
            
    public static class Suma extends ExpBin {
        public Suma(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"+",opnd1, 2, 3);
        }
        public int prioridad() {return 2;}
        
        public String toString() {
            return "suma("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class Mayor extends ExpBin {
        public Mayor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,">",opnd1, 1, 2);
        }
        public int prioridad() {return 1;}
        
        public String toString() {
            return "Mayor("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class Mayor_igual extends ExpBin {
        public Mayor_igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,">=",opnd1, 1, 2);
        }
        public int prioridad() {return 1;}
        
        public String toString() {
            return "Mayor_igual("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class Menor extends ExpBin {
        public Menor(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"<",opnd1, 1, 2);
        }
        public int prioridad() {return 1;}
        
        public String toString() {
            return "Menor("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class Menor_igual extends ExpBin {
        public Menor_igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"<=",opnd1, 1, 2);
        }
        public int prioridad() {return 1;}
        
        public String toString() {
            return "Menor_igual("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class Distinto extends ExpBin {
        public Distinto(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"!=",opnd1,1 , 2);
        }
        public int prioridad() {return 1;}
        
        public String toString() {
            return "Distinto("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class AND extends ExpBin {
        public AND(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"and",opnd1, 3,4 );
        }
        public int prioridad() {return 3;}
        
        public String toString() {
            return "AND("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class OR extends ExpBin {
        public OR(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"OR",opnd1, 3, 4);
        }
        public int prioridad() {return 3;}
        
        public String toString() {
            return "OR("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class MOD extends ExpBin {
        public MOD(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"%",opnd1, 4, 5);
        }
        public int prioridad() {return 4;}
        
        public String toString() {
            return "MOD("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class Asignacion extends ExpBin {
        public Asignacion(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"=",opnd1, 0, 1);
        }
        public int prioridad() {return 0;}
        
        public String toString() {
            return "Asignacion("+opnd0+","+opnd1+")";
        }
    }
    
    public static class Igual extends ExpBin {
        public Igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"==",opnd1, 1, 2);
        }
        public int prioridad() {return 1;}
        
        public String toString() {
            return "Igual("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class Resta extends ExpBin {
        public Resta(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"-",opnd1,2,3);
        }
       public int prioridad() {return 0;}
       public String toString() {
            return "resta("+opnd0+","+opnd1+")";
        } 
    }
    public static class Mul extends ExpBin {
        public Mul(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"*",opnd1,4,5);
        }
        public int prioridad() {return 1;}
        public String toString() {
            return "mul("+opnd0+","+opnd1+")";
        } 
    }
    public static class Div extends ExpBin {
        public Div(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public void imprime() {
            imprimeExpBin(opnd0,"/",opnd1,4,5);
        }
        public int prioridad() {return 1;}
        public String toString() {
            return "div("+opnd0+","+opnd1+")";
        } 
    }
    
    public static class Lit_ent extends Exp {
        private String num;
        public Lit_ent(String num) {
            super();
            this.num = num;
        }
        public void imprime() {
            System.out.print(num);
        }
        public int prioridad() {return 2;}
        public String toString() {
            return "lit_ent("+num+"["+leeFila()+","+leeCol()+"])";
        } 
    }

    public static class Lit_real extends Exp {
        private String num;
        public Lit_real(String num) {
            super();
            this.num = num;
        }
        public void imprime() {
            System.out.print(num);
        }
        public int prioridad() {return 2;}
        public String toString() {
            return "lit_real("+num+"["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class True extends Exp {
        
        public True() {
            super();
            ;
        }
        public void imprime() {
            System.out.print("<true>");
        }
        public int prioridad() {return 2;}
        public String toString() {
            return "True(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class False extends Exp {
        
        public False() {
            super();
            ;
        }
        public void imprime() {
            System.out.print("<false>");
        }
        public int prioridad() {return 2;}
        public String toString() {
            return "False(["+leeFila()+","+leeCol()+"])";
        } 
    }
	
    public static class Iden extends Exp {
        private String id;
        public Iden(String id) {
            super();
            this.id = id;
        }
        public void imprime() {
            System.out.print(id);
        }
        public int prioridad() {return 2;}
        public String toString() {
            return "iden("+id+"["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class Dec extends Nodo {
        private String id;
        private Exp exp;
        public Dec(String id, Exp exp) {
            this.id = id;
            this.exp = exp;
        }
        public void imprime() {
            System.out.println();
            System.out.print("  "+id+"=");
            exp.imprime();
        }
        public String toString() {
            return "dec("+id+"["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class Dec_var extends Dec {
        private TpArray tp;
        private Iden id;
        public Dec_var(TpArray tp, Iden id) {
            super();
            this.id = id;
            this.tp = tp;
        }
        public void imprime() {
            tp.imprime();
            id.imprime();
        }
        public String toString() {
            return "Dec_var("+id+"["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class Dec_tipo extends Dec {
        private TpArray tp;
        private Iden id;
        public Dec_tipo(TpArray tp, Iden id) {
            super();
            this.id = id;
            this.tp = tp;
        }
        public void imprime() {
        	System.out.println("<type>");
        	tp.imprime();
            id.imprime();
        }
        public String toString() {
            return "Dec_tipo("+id+"["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class Dec_proc extends Dec {
        
        private Blq blq;
        private Iden id;
        private LOptParamForm lopf;
        
        public Dec_proc(Blq blq, Iden id, LOptParamForm lop) {
            this.blq = blq;
            this.id=id;
            this.lopf=lop;
        }
        
        public void imprime() {
        	System.out.println("<proc>");
        	id.imprime();
        	lopf.imprime();        	
            blq.imprime();
        }
        public String toString() {
            return "Dec_proc("+id+"["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static abstract class ParamForms extends Nodo {
        public ParamForms() {
        }
        public LParamForm lparamform() {throw new UnsupportedOperationException();}

     }
    
    
     public static class LOptParamForm extends ParamForms {
        private LParamForm paramsf; 
        public LOptParamForm(LParamForm paramsf) {
           super();
           this.paramsf = paramsf;
        }   
         public void imprime() {
             paramsf.imprime();
         }
         public String toString() {
             return "LOptParamForm("+paramsf+")";
         } 
  
     }
     
     public static class no_lista_opt_param_form extends Params {
        public no_lista_opt_param_form() {
           super();
        }   
        public String toString() {
             return "no_lista_opt_param_form()";
         } 
         public void imprime() {}
     }

     public static abstract class LParamForm extends Nodo {
        public LParamForm() {
 		   super();
        }
        public ParamForm paramform() {throw new UnsupportedOperationException();}
        public LParamForm lparamform() {throw new UnsupportedOperationException();}
     }
     	
     public static class si_lista_param_form extends LParamForm {
        private LParamForm paramsf;
        private ParamForm p;
        public si_lista_param_form(LParamForm paramsf, ParamForm p) {
           super();
           this.paramsf = paramsf;
           this.p = p;
        }
        public void imprime() {
            paramsf.imprime();
            System.out.print(",");
            p.imprime();
        }
        public String toString() {
             return "si_lista_param_form("+paramsf+","+p+")";
         } 
     }

     public static class una_lista_param_form extends LParamForm {
        private ParamForm p;
        public una_lista_param_form(ParamForm p) {
           super();
           this.p = p;
        }
        public void imprime() {
            p.imprime();
        }
        public String toString() {
             return "una_lista_param_form("+p+")";
         } 
     }
    
    public static class ParamForm extends Nodo {
    	private TpArray tipo;
    	private Iden id;
        public ParamForm(Iden id, TpArray tipo) {
            this.id = id;
            this.tipo = tipo;
        }
        public void imprime() {
            tipo.imprime();
            id.imprime();
        }
        public String toString() {
            return "ParamForm("+id+"["+leeFila()+","+leeCol()+"],"+tipo+")";
        } 
    }
    
    public static class ParamFormRef extends Nodo {
    	private TpArray tipo;
    	private Iden id;
        public ParamFormRef(Iden id, TpArray tipo) {
            this.id = id;
            this.tipo = tipo;
        }
        public void imprime() {
            tipo.imprime();
            System.out.print(" &");
            id.imprime();
        }
        public String toString() {
            return "ParamFormRef("+tipo+id+"["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class TpArray extends Nodo {
    	private TpArray tipo;
    	private Lit_ent e;
        public TpArray(TpArray tipo, Lit_ent e) {
            
            this.tipo = tipo;
            this.e = e;
        }
        public void imprime() {
            tipo.imprime();
            System.out.print("[");
            e.imprime();
            System.out.println("]");
        }
        public String toString() {
            return "TpArray("+tipo+"["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class tipo_puntero extends Nodo {
    	private T2 tipo;
    	
        public tipo_puntero(T2 tipo) {
            
            this.tipo = tipo;
            
        }
        public void imprime() {
            System.out.print("^");
            tipo.imprime();   
        }
        public String toString() {
            return "tipo_puntero(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class T2 extends Nodo {
    	
    	
        public T2() {
                       
        }
        public void imprime() {
               
        }
        public String toString() {
            return "T2(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class Tipo_basico extends T2 {
    	
    	
        public Tipo_basico() {
                       
        }
        public void imprime() {
               
        }
        public String toString() {
            return "Tipo_basico(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class int_t extends Tipo_basico {
    	
    	
        public int_t() {
                       
        }
        public void imprime() {
               
        }
        public String toString() {
            return "int_t(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class real_t extends Tipo_basico {
    	
    	
        public real_t() {
                       
        }
        public void imprime() {
               
        }
        public String toString() {
            return "real_t(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class bool_t extends Tipo_basico {
    	
    	
        public bool_t() {
                       
        }
        public void imprime() {
               
        }
        public String toString() {
            return "bool_t(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class string_t extends Tipo_basico {
    	
    	
        public string_t() {
                       
        }
        public void imprime() {
               
        }
        public String toString() {
            return "string_t(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class Tipo_registro extends T2 {
    	
    	LParamReg l;
        public Tipo_registro(LParamReg l) {
			this.l=l;   
        }
        public void imprime() {
               System.out.println("<struct>");
               System.out.print("{");
               l.imprime();
               System.out.println("}");
        }
        public String toString() {
            return "Tipo_registro(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class Tipo_definido extends T2 {
    	
    	Iden id;
        public Tipo_definido(Iden id) {
                       this.id=id;
        }
        public void imprime() {
               id.imprime();
        }
        public String toString() {
            return "Tipo_definido(["+leeFila()+","+leeCol()+"])";
        } 
    }
    public static class ParamReg extends Nodo {
    	private TpArray tipo;
    	private Iden id;
        public ParamReg(Iden id, TpArray tipo) {
            this.id = id;
            this.tipo = tipo;
        }
        public void imprime() {
            tipo.imprime();
            id.imprime();
        }
        public String toString() {
            return "Param("+id+"["+leeFila()+","+leeCol()+"])";
        } 
    }
    public static abstract class ParamsReg extends Nodo {
        public ParamsReg() {
        }
        public LParamReg lparamreg() {throw new UnsupportedOperationException();}

     }
    
 

     public static abstract class LParamReg extends Nodo {
        public LParamReg() {
 		   super();
        }
        public ParamReg paramReg() {throw new UnsupportedOperationException();}
        public LParamReg lparamReg() {throw new UnsupportedOperationException();}
     }
     	
     public static class si_lista_param_reg extends LParamReg {
        private LParamReg paramsr;
        private ParamReg p;
        
        public si_lista_param_reg(LParamReg paramsr, ParamReg p) {
           super();
           this.paramsr = paramsr;
           this.p = p;
        }
        public void imprime() {
            paramsr.imprime();
            System.out.print(",");
            p.imprime();
        }
        public String toString() {
             return "si_lista_param_reg("+paramsr+","+p+")";
         } 
     }

     public static class una_lista_param_reg extends LParamReg {
        private ParamReg p;
        public una_lista_param_reg(ParamReg p) {
           super();
           this.p = p;
        }
        public void imprime() {
            p.imprime();
        }
        public String toString() {
             return "una_lista_param_reg("+p+")";
         } 
     }
    
    public static class inst extends Nodo {
        
        public inst() {
            
        }
        public void imprime() {
            
        }
        public String toString() {
            return "inst(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static abstract class Insts extends Nodo {
        public Insts() {
        }
        public LInst linst() {throw new UnsupportedOperationException();}

     }
    
    
     public static class LOptInst extends inst {
        private LInst l; 
        public LOptInst(LInst l) {
           super();
           this.l = l;
        }   
         public void imprime() {
             l.imprime();
         }
         public String toString() {
             return "LOptInst("+l+")";
         } 
  
     }
     
     public static class no_lista_opt_inst extends Params {
        public no_lista_opt_inst() {
           super();
        }   
        public String toString() {
             return "no_lista_opt_inst()";
         } 
         public void imprime() {}
     }

     public static abstract class LInst extends Nodo {
        public LInst() {
 		   super();
        }
        public inst nst() {throw new UnsupportedOperationException();}
        public LInst linst() {throw new UnsupportedOperationException();}
     }
     	
     public static class si_lista_inst extends LInst {
        private LInst l;
        private inst i;
        public si_lista_inst(LInst l, inst i) {
           super();
           this.l = l;
           this.i = i;
        }
        public void imprime() {
            l.imprime();
            System.out.print(";");
            i.imprime();
        }
        public String toString() {
             return "si_lista_inst("+l+","+i+")";
         } 
     }

     public static class una_lista_inst extends LInst {
        private inst i;
        public una_lista_inst(inst i) {
           super();
           this.i = i;
        }
        public void imprime() {
            i.imprime();
        }
        public String toString() {
             return "una_lista_inst("+i+")";
         } 
     }
     
    public static class inst_eval extends inst {
        private Exp exp;
        public inst_eval(Exp e) {
            this.exp=e;
        }
        public void imprime() {
        	System.out.print("@ ");
        	exp.imprime();
        	System.out.println();
        	
        }
        public String toString() {
            return "inst_eval(["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class inst_if extends inst {
        private Exp exp;
        private Blq blq;
        private inst_else el;
      
        public inst_if(Exp e, Blq b, inst_else ie) {
            this.exp=e;
            this.blq=b;
            this.el=ie;
        }
        public void imprime() {
        	System.out.println("<if>");
        	exp.imprime();
        	blq.imprime();
        	el.imprime();	
        }
        public String toString() {
            return "inst_if("+exp+"["+leeFila()+","+leeCol()+"],"+blq+")";
        } 
    }
    
    public static class inst_else extends inst {
        private Blq blq;
        public inst_else(Blq blq) {
            this.blq=blq;
        }
        public void imprime() {
        	System.out.println("<else>");
        	blq.imprime();
        	
        	
        }
        public String toString() {
            return "inst_else("+blq+"["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class no_inst_else extends inst {
        public no_inst_else() {
            
        }
        public void imprime() {
        	
        }
        public String toString() {
            return "no_inst_else(["+leeFila()+","+leeCol()+"])";
        } 
    }
    
    public static class inst_while extends inst {
    	 private Exp exp;
         private Blq blq;
         public inst_while(Exp e, Blq blq) {
            this.exp=e;
            this.blq=blq;
        }
        public void imprime() {
        	System.out.println("<else>");
        	exp.imprime();
        	blq.imprime();
        	
        	
        }
        public String toString() {
            return "inst_while(["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class inst_read extends inst {
        private Exp exp;
        public inst_read(Exp e) {
            this.exp=e;
        }
        public void imprime() {
        	System.out.println("<read>");
        	exp.imprime();
        	
        	
        }
        public String toString() {
            return "inst_read(["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class inst_write extends inst {
        private Exp exp;
        public inst_write(Exp e) {
            this.exp=e;
        }
        public void imprime() {
        	System.out.println("<write>");
        	exp.imprime();
        	
        	
        }
        public String toString() {
            return "inst_write(["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class inst_nl extends inst {
        public inst_nl() {
            
        }
        public void imprime() {
        	System.out.println("<nl>");
        	
        	
        	
        }
        public String toString() {
            return "inst_nl(["+leeFila()+","+leeCol()+"]")";
        } 
    }
    
    public static class inst_new extends inst {
        private Exp exp;
        public inst_new(Exp e) {
            this.exp=e;
        }
        public void imprime() {
        	System.out.println("<new>");
        	exp.imprime();
        	
        	
        }
        public String toString() {
            return "inst_new(["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class inst_delete extends inst {
        private Exp exp;
        public inst_delete(Exp e) {
            this.exp=e;
        }
        public void imprime() {
        	System.out.println("<delete>");
        	exp.imprime();
        	
        	
        }
        public String toString() {
            return "inst_delete(["+leeFila()+","+leeCol()+"],"+exp+")";
        } 
    }
    
    public static class inst_call extends inst {
        private Iden id;
        private LOptParam param;
        public inst_call(Iden id,LOptParam param) {
            this.id=id;
            this.param=param;
        }
        public void imprime() {
        	System.out.println("<call>");
        	id.imprime();
        	System.out.print("(");
        	param.imprime();
        	System.out.println(")");
        	
        	
        }
        public String toString() {
            return "inst_call("+id+"["+leeFila()+","+leeCol()+"],"+param+")";
        } 
    }
    
    public static class inst_comp extends inst {
        private Blq blq;
        public inst_comp(Blq blq) {
            this.blq=blq;
        }
        public void imprime() {
        	blq.imprime();
        	
        	
        }
        public String toString() {
            return "inst_comp(["+leeFila()+","+leeCol()+"],"+blq+")";
        } 
    }
    
    
    
    public static abstract class Decs extends Nodo {
       public Decs() {
       }
       public LDecs ldecs() {throw new UnsupportedOperationException();}

    }
    public static class LOptDecs extends Decs {
       private LDecs decs; 
       public LOptDecs(LDecs decs) {
          super();
          this.decs = decs;
       }   
        public void imprime() {
            decs.imprime();
            System.out.print("&&");
        }
        public String toString() {
            return "LOptDecs("+decs+")";
        } 
 
    }
    
    public static class no_lista_opt_decs extends Decs {
       public no_lista_opt_decs() {
          super();
       }   
       public String toString() {
            return "no_lista_opt_decs()";
        } 
        public void imprime() {}
    }

    public static abstract class LDecs extends Nodo {
       public LDecs() {
		   super();
       }
       public Dec dec() {throw new UnsupportedOperationException();}
       public LDecs ldecs() {throw new UnsupportedOperationException();}
    }
    	
    public static class si_lista_decs extends LDecs {
       private LDecs decs;
       private Dec dec;
       public si_lista_decs(LDecs decs, Dec dec) {
          super();
          this.dec = dec;
          this.decs = decs;
       }
       public void imprime() {
           decs.imprime();
           System.out.print(";");
           dec.imprime();
       }
       public String toString() {
            return "si_lista_decs("+decs+","+dec+")";
        } 
    }

    public static class una_lista_dec extends LDecs {
       private Dec dec;
       public una_lista_dec(Dec dec) {
          super();
          this.dec = dec;
       }
       public void imprime() {
           dec.imprime();
       }
       public String toString() {
            return "una_lista_dec("+dec+")";
        } 
    }
    
    
    public static abstract class Params extends Nodo {
        public Params() {
        }
        public LParam lparam() {throw new UnsupportedOperationException();}

     }
    
    
     public static class LOptParam extends Params {
        private LParam params; 
        public LOptParam(LParam params) {
           super();
           this.params = params;
        }   
         public void imprime() {
             params.imprime();
         }
         public String toString() {
             return "LOptParam("+params+")";
         } 
  
     }
     
     public static class no_lista_opt_param extends Params {
        public no_lista_opt_param() {
           super();
        }   
        public String toString() {
             return "no_lista_opt_param()";
         } 
         public void imprime() {}
     }

     public static abstract class LParam extends Nodo {
        public LParam() {
 		   super();
        }
        public LParam lparam() {throw new UnsupportedOperationException();}
     }
     	
     public static class si_lista_param extends LParam {
        private LParam params;
        private Exp exp;
        public si_lista_param(LParam params, Exp exp) {
           super();
           this.params = params;
           this.exp = exp;
        }
        public void imprime() {
            params.imprime();
            System.out.print(",");
            exp.imprime();
        }
        public String toString() {
             return "si_lista_param("+decs+","+dec+")";
         } 
     }

     public static class una_lista_param extends LParam {
        private Exp exp;
        public una_lista_param(Exp exp) {
           super();
           this.exp = exp;
        }
        public void imprime() {
            exp.imprime();
        }
        public String toString() {
             return "una_lista_param("+exp+")";
         } 
     }

    public static class Blq extends Nodo {
        private LDecs LOptDecs;
        private LInst LOptIsnt;
        public Blq(LDecs LOptDecs, LInst LOptIsnt) {
           super();
           this.LOptDecs=LOptDecs;
           this.LOptIsnt=LOptIsnt;
        }
        public void imprime() {
        	LOptDecs.imprime();
        	LOptIsnt.imprime();
        }
        public String toString() {
             return "bloque("+LOptDecs+","+LOptIsnt+ ")";
         } 
     }
    
    
    public static class Prog extends Nodo {
	   private Blq blq;
	   
       public Prog(Blq blq) {
		   super();
		   this.blq = blq;
       }   
       public void imprime() {
           System.out.println("{");     
           blq.imprime();
           System.out.println("}");
           
       }
       public String toString() {
            return "prog("+blq+")";
        } 
    }

     // Constructoras    
    public Prog prog(Blq blq) {
        return new Prog(blq);
    }
    
    public Exp bloque(LDecs LOptDecs, LInst LOptInst) {
    	return new Blq(LOptDecs,LOptInst);
    public Exp suma(Exp opnd0, Exp opnd1) {
        return new Suma(opnd0,opnd1);
    }
    public Exp resta(Exp opnd0, Exp opnd1) {
        return new Resta(opnd0,opnd1);
    }
    public Exp mul(Exp opnd0, Exp opnd1) {
        return new Mul(opnd0,opnd1);
    }
    public Exp div(Exp opnd0, Exp opnd1) {
        return new Div(opnd0,opnd1);
    }
    
    public Exp mayor(Exp opnd0, Exp opnd1) {
        return new Mayor(opnd0,opnd1);
    }
    
    public Exp asignacion(Exp opnd0, Exp opnd1) {
        return new Asignacion(opnd0,opnd1);
    }
    
    public Exp mayorIg(Exp opnd0, Exp opnd1) {
        return new Mayor_igual(opnd0,opnd1);
    }
    
    public Exp menor(Exp opnd0, Exp opnd1) {
        return new Menor(opnd0,opnd1);
    }
    
    public Exp menor_igual(Exp opnd0, Exp opnd1) {
        return new Menor_igual(opnd0,opnd1);
    }
    
    public Exp igual(Exp opnd0, Exp opnd1) {
        return new Igual(opnd0,opnd1);
    }
    
    public Exp distinto(Exp opnd0, Exp opnd1) {
        return new Distinto(opnd0,opnd1);
    }
    
    public Exp and(Exp opnd0, Exp opnd1) {
        return new AND(opnd0,opnd1);
    }
    
    public Exp or(Exp opnd0, Exp opnd1) {
        return new OR(opnd0,opnd1);
    }
    
    public Exp mod(Exp opnd0, Exp opnd1) {
        return new MOD(opnd0,opnd1);
    }
    
    public Exp lit_ent(String num) {
        return new Lit_ent(num);
    }
    public Exp lit_real(String num) {
        return new Lit_real(num);
    }
    public Exp iden(String num) {
        return new Iden(num);
    }
    public Dec dec(String id, Exp exp) {
        return new Dec(id,exp);
    }
    public Decs si_decs(LDecs decs) {
        return new LOptDecs(decs);
    }
    public Decs no_decs() {
        return new no_lista_opt_decs();
    }
    public LDecs muchas_decs(LDecs decs, Dec dec) {
        return new si_lista_decs(decs,dec);
    }
    public LDecs una_dec(Dec dec) {
        return new una_lista_dec(dec);
    }
    
    public Dec decvar(TpArray t, Iden id) {
        return new Dec_var(t,id);
    }
}
