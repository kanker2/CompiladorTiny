package procesamiento;

import asint.SintaxisAbstractaTiny.ProgT;
import visitante.Impresion;

public class ImpresorBonitoVisitante implements ImpresorBonito {

	@Override
	public void procesa(ProgT p) {
		System.out.println("IMPRESION VISITANTE");
		p.procesa(new Impresion());
	}

}
