package visitante.asint;

import visitante.asint.SintaxisAbstractaTiny.*;


public class ProcesamientoDef implements Procesamiento {

	
	public void procesa(Prog_tiny prog) {}
	public void procesa (Bloque blq){}   
	public void procesa(LOptDecs ld){}
	public void procesa(LDecs ld){}
	public void procesa(Dec d){}
	public void procesa(LOptParamForm a){}
	public  void procesa (LParamForm a){}  
	public void procesa (ParamForm a){}
	public void procesa (Tipo a){}
	public void procesa (LParamReg a){}    
	public void procesa (ParamReg a){}  
	public void procesa (LOptInst a){}   
	public void procesa (LInst a){}
	public void procesa (Inst a){} 
    public void procesa(Exp a){}
    public void procesa (ExpBin a){}    
    public void procesa (LOptParam a){}  
    public void procesa (LParam a){}
	  

    public void procesa(Asignacion exp) {}
    public void procesa(Mayor exp) {}
    public void procesa(Mayor_igual exp) {}
    public void procesa(Menor exp) {}
    public void procesa(Menor_igual exp) {}
    public void procesa(Comparacion exp) {}
    public void procesa(Distinto exp) {}
    public void procesa(Suma exp) {}
    public void procesa(Resta exp) {}
    public void procesa(And exp) {}
    public void procesa(Or exp) {}
    public void procesa(Mult exp) {}
    public void procesa(Div exp) {}
    public void procesa(Mod exp) {}
    public void procesa(Not_unario exp) {}
    public void procesa(Resta_unario exp) {}
    public void procesa(Indexacion exp) {}
    public void procesa(Acc_reg exp) {}
    public void procesa(Indireccion exp) {}

    public void procesa(Lit_ent exp) {}
    public void procesa(Lit_real exp) {}
    public void procesa(True_e exp) {}
    public void procesa(False_e exp) {}
    public void procesa(Null_e exp) {}
    public void procesa(Cadena exp) {}
    public void procesa(Iden exp) {}
}
