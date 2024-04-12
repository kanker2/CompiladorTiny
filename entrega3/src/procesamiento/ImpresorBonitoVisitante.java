package procesamiento;

import asint.SintaxisAbstractaTiny.ProgT;
import visitante.Impresion;

public class ImpresorBonitoVisitante implements ImpresorBonito {

	@Override
	public void procesa(ProgT p) {
		p.procesa(new Impresion());
	}

}
