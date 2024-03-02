package asintTiny0;

import java.io.IOException;
import java.io.Reader;

import alexEval.UnidadLexica;

public class AnalizadorSintacticoTiny0DJ {
	public AnalizadorSintacticoTiny0DJ(Reader input) throws IOException {
        super(input); 
     }
//   protected final void traza_emparejamiento(UnidadLexica unidad) {
//       switch(unidad.clase()) {
//		   case IDEN: case ENT: case REAL: System.out.println(unidad.lexema()); break;
//                 default: System.out.println(unidad.clase().getImage());
//	 }
//   } 
}
