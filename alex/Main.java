package alex;

//import alex.ALexOperations.ECaracterInesperado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTinyFlex;
import alex.ClaseLexica;
import alex.UnidadLexica;

public class Main {
   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input  = new InputStreamReader(new FileInputStream(args[0]));
     AnalizadorLexicoTinyFlex al = new AnalizadorLexicoTinyFlex(input);
     UnidadLexica unidad = null;
     do {
    	 unidad = al.yylex();
         System.out.println(unidad);
       /*try {  
         unidad = al.yylex();
         System.out.println(unidad);  
       }
       catch(ECaracterInesperado e) {
               System.out.println(e.getMessage());
               System.exit(1);
       }*/
     }
     while (unidad.clase() != ClaseLexica.EOF);
    }        
} 
