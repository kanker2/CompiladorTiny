package errores;

import asint.SintaxisAbstractaTiny.Nodo;

public class VinculacionIdentificadorNoDefinido extends ErroresVinculacion {
	public VinculacionIdentificadorNoDefinido(Nodo n) {
		super(n);
		motivo += "Identificador no definido\n";
	}
}
