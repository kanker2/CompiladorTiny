Para ejecutarlo correctamente hacer lo siguiente:
1. Añadir al build path el cup.jar
2. Generar clases restantes de asint
	comando: java -jar cup.jar -parser AnalizadorSintacticoTiny -symbols ClaseLexica spec.cup
3. Generar clases restantes de alex
	comando: java -jar jflex.jar spec.jflex