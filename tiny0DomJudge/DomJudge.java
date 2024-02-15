
import alex.ECaracterInesperado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import alex.UnidadLexica;

public class DomJudge {
	

    public static void main(String[] args) throws FileNotFoundException, IOException {
	 Reader input = new InputStreamReader(new FileInputStream("input.txt"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad = null;
     boolean error;
     do {
       error = false;  
       try {  
         unidad = al.sigToken();
	     //imprime(unidad);
         System.out.println(unidad);
       }
       catch(ECaracterInesperado e) {
              error = true;
       }
     }
     while (error || unidad.clase() != ClaseLexica.EOF);
    }        

} 
