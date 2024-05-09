package procesamiento;

import asint.SintaxisAbstractaTiny.ProgT;

public class ImpresorBonitoInterprete implements ImpresorBonito {
	@Override
	public void procesa(ProgT p) {
		System.out.println("IMPRESION INTERPRETE");
		p.imprime();
		System.out.println();
	}

}
