Para ejecutarlo correctamente hacer lo siguiente:
1. Añadir al build path el cup.jar
2. Generar clases restantes de asint
	comando: java -jar cup.jar -parser ConstructorASTTiny -symbols ClaseLexica spec.cup -nopositions //-nopositions no he probado si funciona
3. Generar clases restantes de alex
	comando: java -jar jflex.jar spec.jflex
