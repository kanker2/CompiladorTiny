package Procesamientos;

import asint.SintaxisAbstractaTiny.ProgT;

public class VinculacionVisitante {
	public void vincula(ProgT p) {
		System.out.println("VINCULACION VISITANTE");
		p.vincula(new ProcesamientoVinculacion());
	}
}
