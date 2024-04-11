package procesamiento;

import asint.SintaxisAbstractaTiny.ProgT;

public class ImpresorBonitoInterprete implements ImpresorBonito {
	@Override
	public void procesa(ProgT p) {
		p.imprime();
	}

}
