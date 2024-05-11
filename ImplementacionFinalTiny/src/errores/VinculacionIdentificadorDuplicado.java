package errores;

import asint.SintaxisAbstractaTiny.Nodo;

public class VinculacionIdentificadorDuplicado extends ErroresVinculacion {
	public VinculacionIdentificadorDuplicado(Nodo n) {
		super(n);
		motivo += "Identificador duplicado\n";
	}
}
