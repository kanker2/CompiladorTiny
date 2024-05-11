package errores;

import asint.SintaxisAbstractaTiny.Nodo;

public abstract class Error {
	String motivo = null;
	Integer fila = null;
	Integer columna = null;
	Nodo p = null;
	
	Error(Nodo n) {
		p = n;
		fila = n.leeFila();
		columna = n.leeCol();
	}
	
	public String getMotivo() {
		return motivo;
	}
	
	public Integer getFila() {
		return fila;
	}
	
	public Integer getColumna() {
		return columna;
	}
	
	public Nodo getNodo() {
		return p;
	}
}
