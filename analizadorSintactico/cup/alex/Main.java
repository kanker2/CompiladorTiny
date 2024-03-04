package alex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.ALexOperations.ECaracterInesperado;
import asint.ClaseLexica;

public class Main {
   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input  = new InputStreamReader(new FileInputStream(args[0]));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad = null;
     do {
       try {  
         unidad = al.yylex();
         System.out.println(unidad);  
       }
       catch(ECaracterInesperado e) {
               System.out.println(e.getMessage());
               System.exit(1);
       }
     }
     while (unidad.clase() != ClaseLexica.EOF);
    }        
} 