package alexTiny0;

public enum ClaseLexica {
	Identificador, Entero, Real,
	INT("<int>"), REAL("<real>"), BOOL("<bool>"),
	NOT("<not>"), TRUE("true"), FALSE("false"),
	POR("*"), ENTRE("/"), AND("<and>"), OR("<or>"), 
	MAS("+"), MENOS("-"),
	MENOR("<"), MAYOR(">"), MENORIGUAL("<="), MAYORIGUAL(">="), 
	COMPARACION("=="), DISTINTO("!="), ASIGNACION("="), 
	INIPAR("("), FINPAR(")"), 
	INIBLOQUE("{"), FINBLOQUE("}"), 
	ARROBA("@"), FINDECLARACIONES("&&"), PUNTOYCOMA(";"), EOF("<EOF>");
	private String image;
	public String getImage() {return image;};
	private ClaseLexica() {image = toString();}
	private ClaseLexica(String image) {this.image = image;}
}
