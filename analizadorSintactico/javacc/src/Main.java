
import java.io.FileReader;

import asint.AnalizadorSintacticoTiny;
public class Main{
   public static void main(String[] args) throws Exception {
      AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(new FileReader(args[0]));
      asint.disable_tracing();
      asint.programa_tiny();;
   }
}