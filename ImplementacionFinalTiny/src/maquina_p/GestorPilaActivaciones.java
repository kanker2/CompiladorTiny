package maquina_p;

import java.util.ArrayList;

public class GestorPilaActivaciones {
	private int dir_ini; 
	private int dir_fin;
	private ArrayList<Integer> Displays; 
	
	public static class DesbordamientoPilaException extends Exception{
		
		public DesbordamientoPilaException(String msg) {
			super(msg);
		}
	}
	
	public GestorPilaActivaciones(int ini, int fin, int num_displays) {
		this.dir_ini = ini;
		this.dir_fin = fin;
		this.Displays = new ArrayList<>(num_displays);
	}
			
	// Operaciones sobre Displays
	public void setValorDisplay(int nivel, int val) {
		Displays.set(nivel-1, val);
	}
	
	public int valorDisplay(int nivel) {
		return Displays.get(nivel-1);
	}
	
	// Operaciones sobre Registros de Activacion
	public int reservaRegistroActivacion(int tamDatos) throws DesbordamientoPilaException {
		int dir_comienzo_dato = dir_ini;
		
		if((this.dir_fin - this.dir_ini + 1) - (tamDatos + 2) < 0) {
			throw new DesbordamientoPilaException("Memoria insuficiente en la Pila de Activaciones");
		}
		
		this.dir_ini = this.dir_ini + tamDatos + 2;
		return dir_comienzo_dato;
	}
	
	public int liberaRegistroActivacion(int tamDatos) {
		this.dir_ini = this.dir_ini - tamDatos - 2;
		return this.dir_ini;
	}

	
}
