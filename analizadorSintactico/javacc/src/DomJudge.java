import java.io.InputStreamReader;

import asint.AnalizadorSintacticoTinyDJ;
import asint.ParseException;
import asint.TokenMgrError;
public class DomJudge{
   public static void main(String[] args) throws Exception {
     try{  
      AnalizadorSintacticoTinyDJ asint = new AnalizadorSintacticoTinyDJ(new InputStreamReader(System.in));
      asint.programa_tiny();;
     }
     catch(ParseException e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
     catch(TokenMgrError e) {
        System.out.println("ERROR_LEXICO"); 
     }
   }
}