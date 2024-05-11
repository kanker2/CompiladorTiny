package errores;

import asint.SintaxisAbstractaTiny.Nodo;

public abstract class ErroresVinculacion extends Error{
	public ErroresVinculacion(Nodo n) {
		super(n);
		motivo = "Errores_vinculacion fila:"+n.leeFila()+" col:"+n.leeCol();
	}
}
