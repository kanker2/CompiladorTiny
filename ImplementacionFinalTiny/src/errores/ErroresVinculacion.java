package errores;

import asint.SintaxisAbstractaTiny.Nodo;

public abstract class ErroresVinculacion extends Error{
	public ErroresVinculacion(Nodo n) {
		super(n);
		motivo += "Error de Vinculacion: ";
	}
}
