
import java.io.FileReader;

import asint.AnalizadorSintacticoTinyDJ;
public class Main{
   public static void main(String[] args) throws Exception {
      AnalizadorSintacticoTinyDJ asint = new AnalizadorSintacticoTinyDJ(new FileReader(args[0]));
      asint.disable_tracing();
      asint.programa_tiny();;
   }
}