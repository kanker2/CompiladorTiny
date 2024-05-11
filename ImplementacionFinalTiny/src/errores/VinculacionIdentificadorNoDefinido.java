package errores;

import asint.SintaxisAbstractaTiny.Nodo;

public class VinculacionIdentificadorNoDefinido extends ErroresVinculacion {
	public VinculacionIdentificadorNoDefinido(Nodo n) {
		super(n);
		motivo = "Error Vinculacion: Identificador "+n.toString()+" no definido\n";
	}
}
