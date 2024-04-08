package visitante.asint;

import visitante.asint.SintaxisAbstractaTiny.*;

public interface Procesamiento {

    void procesa(Prog_tiny prog);
    void procesa (Bloque blq);    
    void procesa(LOptDecs ld);
    void procesa(LDecs ld);
    void procesa(Dec d);
    void procesa(LOptParamForm a);
    void procesa (LParamForm a);    
    void procesa (ParamForm a);  
    void procesa (Tipo a);    
    void procesa (LParamReg a);    
    void procesa (ParamReg a);    
    void procesa (LOptInst a);    
    void procesa (LInst a);    
    void procesa (Inst a);   
    void procesa(Exp a);
    void procesa (ExpBin a);    
    void procesa (LOptParam a);  
    void procesa (LParam a);    
  

    void procesa(Asignacion exp);
    void procesa(Mayor exp);
    void procesa(Mayor_igual exp);
    void procesa(Menor exp);
    void procesa(Menor_igual exp);
    void procesa(Comparacion exp);
    void procesa(Distinto exp);
    void procesa(Suma exp);
    void procesa(Resta exp);
    void procesa(And exp);
    void procesa(Or exp);
    void procesa(Mult exp);
    void procesa(Div exp);
    void procesa(Mod exp);
    void procesa(Not_unario exp);
    void procesa(Resta_unario exp);
    void procesa(Indexacion exp);
    void procesa(Acc_reg exp);
    void procesa(Indireccion exp);

    void procesa(Lit_ent exp);
    void procesa(Lit_real exp);
    void procesa(True_e exp);
    void procesa(False_e exp);
    void procesa(Null_e exp);
    void procesa(Cadena exp);
    void procesa(Iden exp);
}
