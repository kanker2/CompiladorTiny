package errores;

import java.util.ArrayList;

public class Errores {
	ArrayList<Error> errs;
	
	public Errores() {
		errs = new ArrayList<Error>();
	}
	
	public void nuevoError(Error e) {
		errs.add(e);
	}
	
	public void traza() {
		errs.forEach(x -> { System.out.println(x.getMotivo()); });
	}
	
	public boolean hayError() {
		return !errs.isEmpty();
	}
}
