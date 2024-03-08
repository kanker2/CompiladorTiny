import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javaccAsc.AnalizadorSintacticoTinyDJ;
import javaccAsc.ParseException;
import javaccAsc.TokenMgrError;
import cupAlex.AnalizadorLexicoTiny;
import cupAsint.*;
import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;


public class Main {

	public static void main(String[] args) throws Exception {
		 /*if (args.length < 2) {
	            System.out.println("Error: Debes proporcionar el archivo y la opción (desc/asc).");
	            return;
	        }*/

	     //String archivo = args[0];
	     String archivo = "C:/Users/juand/eclipse-workspacep/royluna/src/in_tiny0_2.txt";
	     String opcion = args[0];

	     //"C://Users/Luna Santos/eclipse-workspace/ProgramaPrincipal/src/in_tiny0_1.txt"
		Reader input = new InputStreamReader(new FileInputStream(archivo));
			
		
		if(opcion.equalsIgnoreCase("desc")) {
			
			  AnalizadorSintacticoTinyDJ asint = new AnalizadorSintacticoTinyDJ(input);
		      asint.disable_tracing();
		       
		      try {
		    	  asint.programa_tiny();
		      }
		      catch(ParseException e) {
		         System.out.println("ERROR_SINTACTICO"); 
		      }
		      catch(TokenMgrError e) {
		         System.out.println("ERROR_LEXICO"); 
		      }
		    
		}
		else if (opcion.equalsIgnoreCase("asc")){
			
			AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			AnalizadorSintacticoTinyDJCup asint = new AnalizadorSintacticoTinyDJCup(alex);
			//asint.setScanner(alex);

			try {
				asint.debug_parse();
			} catch (ErrorLexico e) {
				System.out.println("ERROR_LEXICO");
			} catch (ErrorSintactico e) {
				System.out.println("ERROR_SINTACTICO");
			}
		}
		else {
			System.out.println(opcion);
			System.out.println("Error, tipo de analizador sintactico no valido");
		}
	}

}
