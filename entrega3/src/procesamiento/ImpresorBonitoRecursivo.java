package procesamiento;

import asint.SintaxisAbstractaTiny.ProgT;

public class ImpresorBonitoRecursivo implements ImpresorBonito {
	public void procesa(ProgT p) {
		System.out.println(p.toString());
	}
}
