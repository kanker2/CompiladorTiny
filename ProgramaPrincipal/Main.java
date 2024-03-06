import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import asint.AnalizadorSintacticoTinyDJJavaCC;
import cup.asintcup.*;
import errors.GestionErroresTiny.ErrorLexico;
import cup.alex.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args[1].equalsIgnoreCase("desc")) {
			AnalizadorSintacticoTinyDJJavaCC asintJ = new AnalizadorSintacticoTinyDJJavaCC(new FileReader(args[0]));
		      asintJ.disable_tracing();
		      asintJ.programa_tiny();;
		}
		else if (args[1].equalsIgnoreCase("asc")){
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
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
			System.out.println("Error, tipo de analizador sintactico no valido");
		}
	}

}
