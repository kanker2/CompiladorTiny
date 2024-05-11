package errores;

import asint.SintaxisAbstractaTiny.Nodo;

public class VinculacionIdentificadorDuplicado extends ErroresVinculacion {
	public VinculacionIdentificadorDuplicado(Nodo n) {
		super(n);
		// motivo = "Error Vinculacion: Identificador f:"+getFila()+";c:"+getColumna()+" duplicado\n";
	}
}
