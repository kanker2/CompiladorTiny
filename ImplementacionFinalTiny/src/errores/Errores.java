package errores;

import java.util.Stack;

public class Errores {
	Stack<Error> errs;
	
	public Errores() {
		errs = new Stack<Error>();
	}
	
	public void nuevoError(Error e) {
		errs.push(e);
	}
}
