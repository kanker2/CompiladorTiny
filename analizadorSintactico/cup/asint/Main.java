package asint;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTinyDJ(alex);
	 //asint.setScanner(alex);
	 asint.parse();
	 
	 
	 /*try {
	    	 asint.analiza();
	     } catch(ErrorSintactico e) {
	    	 System.out.println("ERROR_SINTACTICO");
	     } catch(ErrorLexico e) {
	    	 System.out.println("ERROR_LEXICO");
	     }*/
 }
}   
   
