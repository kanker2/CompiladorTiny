package asintTiny0;

import java.io.IOException;
import java.io.Reader;

import alexTiny0.UnidadLexica;

public class AnalizadorSintacticoTiny0DJ extends AnalizadorSintacticoTiny0{
	public AnalizadorSintacticoTiny0DJ(Reader input) throws IOException {
        super(input); 
     }
	@Override
	protected final void traza_emparejamiento(UnidadLexica unidad) {
       switch(unidad.clase()) {
		   case Identificador: case Entero: case Real: System.out.println(unidad.lexema()); break;
                 default: System.out.println(unidad.clase().getImage());
	 }
   } 
}
