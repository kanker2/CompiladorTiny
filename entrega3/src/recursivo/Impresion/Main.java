package impresion;

import asint.SintaxisAbstracta.Prog;
import c_ast_ascendente.AnalizadorLexicoEval;
import c_ast_descendente.ConstructorASTsEval;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
   public static void main(String[] args) throws Exception {
         if(args[0].equals("asc")) {
           Reader input = new InputStreamReader(new FileInputStream(args[1]));
  	   AnalizadorLexicoEval alex = new AnalizadorLexicoEval(input);
	   c_ast_ascendente.ConstructorASTEval asint = new c_ast_ascendente.ConstructorASTEval(alex);
           Prog prog = (Prog)asint.parse().value;
  	   System.out.println(new Impresion().imprime(prog));
         }
         else {
             ConstructorASTsEval asint = new ConstructorASTsEval(new FileReader(args[1]));
             asint.disable_tracing();
             System.out.println(new Impresion().evalua(asint.analiza()));
         }
    }
}   
