package visitante;

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
import asint.SintaxisAbstractaTiny.Exp;
import asint.SintaxisAbstractaTiny.False_e;
import asint.SintaxisAbstractaTiny.Iden;
import asint.SintaxisAbstractaTiny.Indexacion;
import asint.SintaxisAbstractaTiny.Indireccion;
import asint.SintaxisAbstractaTiny.Inst_call;
import asint.SintaxisAbstractaTiny.Inst_comp;
import asint.SintaxisAbstractaTiny.Inst_delete;
import asint.SintaxisAbstractaTiny.Inst_eval;
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
import asint.SintaxisAbstractaTiny.Inst_if;
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

public class Impresion extends ProcesamientoDef{
	
	private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.println("(");};
        opnd.procesa(this);
        if(opnd.prioridad() < np) {System.out.println();
		System.out.print(")");};        
    }
    private void imprimeExpBin(Exp padre, Exp opnd0, String op, Exp opnd1, int np0, int np1) {
        imprimeOpnd(opnd0,np0);
        System.out.println();
		System.out.println(op + "$f:" + padre.leeFila() + ",c:" + padre.leeCol() + "$");
        imprimeOpnd(opnd1,np1);
    }

	@Override
	public void procesa(Prog_tiny p) {
		p.bloque().procesa(this);
	}

	@Override
	public void procesa(Bloque p) {
		System.out.print("{");
		p.lista_opt_declaraciones().procesa(this);
		p.lista_opt_instrucciones().procesa(this);
		System.out.println();
		System.out.print("}");		
	}

	@Override
	public void procesa(Si_lista_opt_decs p) {
		System.out.println();
		p.lista_declaraciones().procesa(this);
		System.out.println();
		System.out.print("&&");
		
	}

	@Override
	public void procesa(No_lista_opt_decs p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Muchas_lista_decs p) {
		p.lista_declaraciones().procesa(this);
		System.out.println();
		System.out.print(";");
		System.out.println();
		p.declaracion().procesa(this);
		
	}

	@Override
	public void procesa(Una_lista_dec p) {
		p.declaracion().procesa(this);
		
	}

	@Override
	public void procesa(Dec_var p) {
		p.tipo().procesa(this);
		System.out.println();
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");

	}

	@Override
	public void procesa(Dec_tipo p) {
		System.out.println("<type>");
		p.tipo().procesa(this);
		System.out.println();
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Dec_proc p) {
		System.out.println("<proc>");
		System.out.println(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		p.lista_opt_parametros_formales().procesa(this);
		System.out.println();
		p.bloque().procesa(this);
		
	}

	@Override
	public void procesa(Si_lista_opt_param_form p) {
		System.out.println("(");
		p.lista_parametros_formales().procesa(this);
		System.out.println();
		System.out.print(")");		
	}

	@Override
	public void procesa(No_lista_opt_param_form p) {
		System.out.println("(");
		System.out.print(")");		
	}

	@Override
	public void procesa(Muchas_lista_param_form p) {
		p.lista_parametros_formales().procesa(this);
		System.out.println();
		System.out.println(",");
		p.parametro_formal().procesa(this);	
	}

	@Override
	public void procesa(Una_lista_param_form p) {
		p.parametro_formal().procesa(this);
		
	}

	@Override
	public void procesa(Param_form_ref p) {
		p.tipo().procesa(this);
		System.out.println();
		System.out.println("&");
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Param_form p) {
		p.tipo().procesa(this);
		System.out.println();
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Tipo_array p) {
		p.tipo().procesa(this);
		System.out.println();
		System.out.println("[");
		System.out.println(p.cadena());
		System.out.print("]" + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Tipo_puntero p) {
		System.out.println("^");
		p.tipo().procesa(this);
		
	}

	@Override
	public void procesa(Int_t p) {
		System.out.print("<int>");
		
	}

	@Override
	public void procesa(Real_t p) {
		System.out.print("<real>");
		
	}

	@Override
	public void procesa(Bool_t p) {
		System.out.print("<bool>");
		
	}

	@Override
	public void procesa(String_t p) {
		System.out.print("<string>");
		
	}

	@Override
	public void procesa(Tipo_registro p) {
		System.out.println("<struct>");
		System.out.println("{");
		p.lista_parametros_registro().procesa(this);
		System.out.println();
		System.out.print("}");
		
	}

	@Override
	public void procesa(Tipo_definido p) {
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Muchas_lista_param_reg p) {
		p.lista_parametros_registro().procesa(this);
		System.out.println();
		System.out.println(",");
		p.parametro_registro().procesa(this);
		
	}

	@Override
	public void procesa(Una_lista_param_reg p) {
		p.parametro_registro().procesa(this);
		
	}

	@Override
	public void procesa(Param_reg p) {
		p.tipo().procesa(this);
		System.out.println();
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Si_lista_opt_inst p) {
		System.out.println();
		p.lista_instrucciones().procesa(this);
		
	}

	@Override
	public void procesa(No_lista_opt_inst p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Muchas_lista_inst p) {
		p.lista_instrucciones().procesa(this);
		System.out.println();
		System.out.println(";");
		p.instruccion().procesa(this);
		
	}

	@Override
	public void procesa(Una_lista_inst p) {
		p.instruccion().procesa(this);
	}

	@Override
	public void procesa(Inst_eval p) {
		System.out.println("@");
		p.expresion().procesa(this);
		
	}

	@Override
	public void procesa(Inst_if_else p) {
		System.out.println("<if>");
		p.expresion().procesa(this);
		System.out.println();
		p.bloque().procesa(this);
		System.out.println();
		System.out.println("<else>");
		p.bloque2().procesa(this);
		
	}
	
	@Override
	public void procesa(Inst_if p) {
		System.out.println("<if>");
		p.expresion().procesa(this);
		System.out.println();
		p.bloque().procesa(this);
		
	}

	@Override
	public void procesa(Inst_while p) {
		System.out.println("<while>");
		p.expresion().procesa(this);
		System.out.println();
		p.bloque().procesa(this);
		
	}

	@Override
	public void procesa(Inst_read p) {
		System.out.println("<read>");
		p.expresion().procesa(this);
		
	}

	@Override
	public void procesa(Inst_write p) {
		System.out.println("<write>");
		p.expresion().procesa(this);
		
	}

	@Override
	public void procesa(Inst_nl p) {
		System.out.print("<nl>");
		
	}

	@Override
	public void procesa(Inst_new p) {
		System.out.println("<new>");
		p.expresion().procesa(this);
		
	}

	@Override
	public void procesa(Inst_delete p) {
		System.out.println("<delete>");
		p.expresion().procesa(this);
		
	}

	@Override
	public void procesa(Inst_call p) {
		System.out.println("<call>");
		System.out.println(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		p.lista_opt_parametros().procesa(this);
		
	}

	@Override
	public void procesa(Inst_comp p) {
		p.bloque().procesa(this);
		
	}

	@Override
	public void procesa(Asignacion p) {
		imprimeExpBin(p, p.op1(), "=", p.op2(), 1, 0);
		
	}

	@Override
	public void procesa(Mayor p) {
		imprimeExpBin(p, p.op1(), ">", p.op2(), 1, 2);
		
	}

	@Override
	public void procesa(Mayor_igual p) {
		imprimeExpBin(p, p.op1(), ">=", p.op2(), 1, 2);
		
	}

	@Override
	public void procesa(Menor p) {
		imprimeExpBin(p, p.op1(), "<", p.op2(), 1, 2);
		
	}

	@Override
	public void procesa(Menor_igual p) {
		imprimeExpBin(p, p.op1(), "<=", p.op2(), 1, 2);
		
	}

	@Override
	public void procesa(Comparacion p) {
		imprimeExpBin(p, p.op1(), "==", p.op2(), 1, 2);
		
	}

	@Override
	public void procesa(Distinto p) {
		imprimeExpBin(p, p.op1(), "!=", p.op2(), 1, 2);
		
	}

	@Override
	public void procesa(Suma p) {
		imprimeExpBin(p, p.op1(), "+", p.op2(), 2, 3);
		
	}

	@Override
	public void procesa(Resta p) {
		imprimeExpBin(p, p.op1(), "-", p.op2(), 3, 3);
		
	}

	@Override
	public void procesa(And p) {
		imprimeExpBin(p, p.op1(), "<and>", p.op2(), 4, 3);
		
	}

	@Override
	public void procesa(Or p) {
		imprimeExpBin(p, p.op1(), "<or>", p.op2(), 4, 4);
		
	}

	@Override
	public void procesa(Mult p) {
		imprimeExpBin(p, p.op1(), "*", p.op2(), 4, 5);
		
	}

	@Override
	public void procesa(Div p) {
		imprimeExpBin(p, p.op1(), "/", p.op2(), 4, 5);
		
	}

	@Override
	public void procesa(Mod p) {
		imprimeExpBin(p, p.op1(), "%", p.op2(), 4, 5);
		
	}

	@Override
	public void procesa(Not_unario p) {
		System.out.println("<not>" + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		imprimeOpnd(p.op1(), 5);		
	}

	@Override
	public void procesa(Resta_unario p) {
		System.out.println("-" + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		imprimeOpnd(p.op1(), 5);
		
	}

	@Override
	public void procesa(Indexacion p) {
		imprimeOpnd(p.op1(), 6);
		System.out.println();
		System.out.println("[" + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		imprimeOpnd(p.op2(), 0);
		System.out.println();
		System.out.print("]");
		
	}

	@Override
	public void procesa(Acc_reg p) {
		imprimeOpnd(p.op1(), 6);
		System.out.println();
		System.out.println(".");
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Indireccion p) {
		imprimeOpnd(p.op1(), 6);
		System.out.println();
		System.out.print("^" + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Lit_ent p) {
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Lit_real p) {
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(True_e p) {
		System.out.print("<true>" + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(False_e p) {
		System.out.print("<false>"+ "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Null_e p) {
		System.out.print("<null>" + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Cadena p) {
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Iden p) {
		System.out.print(p.cadena() + "$f:" + p.leeFila() + ",c:" + p.leeCol() + "$");
		
	}

	@Override
	public void procesa(Si_lista_opt_param p) {
		System.out.println("(");
		p.lista_parametros().procesa(this);
		System.out.println();
		System.out.print(")");
		
	}

	@Override
	public void procesa(No_lista_opt_param p) {
		System.out.println("(");
		System.out.print(")");
		
	}

	@Override
	public void procesa(Muchas_lista_param p) {
		p.lista_parametros().procesa(this);
		System.out.println();
		System.out.println(",");
		p.expresion().procesa(this);
		
	}

	@Override
	public void procesa(Una_lista_param p) {
		p.expresion().procesa(this);
		
	}
	
}

