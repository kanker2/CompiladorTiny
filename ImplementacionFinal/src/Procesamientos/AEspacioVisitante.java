package Procesamientos;

import asint.SintaxisAbstractaTiny.ProgT;

public class AEspacioVisitante {

	public void asig_espacio(ProgT p) {
		System.out.println("ASIGESPACIO VISITANTE");
		p.asig_espacio(new ProcesamientoAEspacio());
	}
}
