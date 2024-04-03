package asint;

public class ClaseSemanticaTiny extends SintaxisAbstractaTiny{
	public ClaseSemanticaTiny() {
		super();
	}
	
	public Exp mkop1(String op, Exp opnd1, Exp opnd2) {
		switch(op) {
		case ">" : return mayor(opnd1,opnd2);
		case ">=": return mayor_igual(opnd1,opnd2);
		case "<" : return menor(opnd1,opnd2);
		case "<=": return menor_igual(opnd1,opnd2);
		case "==": return comparacion(opnd1, opnd2);
		case "!=": return distinto(opnd1, opnd2);
		default: throw new UnsupportedOperationException("Bad op");
		}
	}

	public Exp mkop4(String op, Exp opnd1, Exp opnd2) {
		switch(op) {
		case "*": return mult(opnd1, opnd2);
		case "/": return div(opnd1, opnd2);
		case "%": return mod(opnd1, opnd2);
		default: throw new UnsupportedOperationException("Bad op");
		}
	}
	
	public Exp mkop5(String op, Exp opnd1) {
		switch(op) {
		case "not": return not_unario(opnd1);
		case  "-" : return resta_unario(opnd1);
		default: throw new UnsupportedOperationException("Bad op");
		}
	}
	
}
