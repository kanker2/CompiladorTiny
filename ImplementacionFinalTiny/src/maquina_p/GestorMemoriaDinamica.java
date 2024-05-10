package maquina_p;

public class GestorMemoriaDinamica {
	
	// Representacion de un bloque de memoria
	private class bloque {
		private int dir;	// Dir. inicio del bloque
		private int celdas; // Cantidad de celdas que ocupa desde dir
		private bloque sig; // Enlace al siguiente bloque reservable
		
		public bloque (int dir, int celdas_ocupadas) {
			this.dir = dir;
			this.celdas = celdas_ocupadas;
			this.sig = null;	
		}
	}
	
	// Excepciones lanzadas por el Gestor de Memoria Dinamica
	public static class NotEnoughMemoryException extends Exception{
		public NotEnoughMemoryException(String message) {
	        super(message);
	    }
	}
	
	// Codigo del Gestor de Memoria Dinamica
	private bloque bloque_inicial; // El primer bloque
	
	public GestorMemoriaDinamica (int dir_ini, int celdas_totales) {
		// En un inicio todo el espacio es un bloque asignable
		this.bloque_inicial = new bloque(dir_ini, celdas_totales);
		
	}
	
	// Reserva memoria y devuelve la direccion inicial del bloque reservado
	public int allocaMemoria(int celdas) throws NotEnoughMemoryException {
		bloque cur = bloque_inicial; 
		bloque prev = null;
		
		// Buscar un bloque libre que contenga la direccion pedidad
		while (cur != null && cur.celdas < celdas) {
			prev = cur;
			cur = cur.sig;
		}
		
		// No hay bloque libre para reservar 
		if(cur == null) {
			throw new NotEnoughMemoryException("No hay Memoria Dinamica suficiente");
		}
		
		int dir_ini = cur.dir;
		cur.dir += celdas;
		cur.celdas -= celdas;
		
		// Si el bloque libre se reserva en su totalidad 
		if(cur.celdas == 0) {
			
			// Si tengo un bloque libre previo lo enlazo
			if(prev != null) {
				prev.sig = cur.sig;
			}
			// Si no tengo un bloque libre previo 
			else {
				this.bloque_inicial = cur.sig;
			}
			
		}
		return dir_ini; // Direccion de comienzo del espacio reservado
	}
	
	
	public void liberaMemoria(int dir, int celdas) {
		// cur sera el bloque libre despues del espacio a liberar
		bloque cur = bloque_inicial;
		// prev sera justamente un bloque libre antes del espacio a liberar
		bloque prev = null; 	
		
		while (cur != null && cur.dir < dir) {
			prev = cur;
			cur = cur.sig;
		}
		
		// Comprobar si el bloque libre antes del espacio a liberar solapan
		if(prev != null && prev.dir + prev.celdas == dir) {
			prev.celdas += celdas;
			// Comprobar si el espacio a liberar solapa con el siguiente bloque libre
			if(dir + celdas == cur.dir) {
				prev.celdas += cur.celdas;
				prev.sig = cur.sig;
			}
		}
		// Caso donde el espacio a liberar solapa con el bloque libre siguiente
		else if (cur != null && dir + celdas == cur.dir) {
			cur.dir = dir;
			cur.celdas += celdas;
		}
		// Ultimo caso donde no solapan con ningun bloque libre
		else {
			bloque nuevo_bloque = new bloque(dir, celdas);
			
			if(prev != null) {
				prev.sig = nuevo_bloque;
				nuevo_bloque.sig = cur;
			}
			else {
				bloque_inicial = nuevo_bloque;
			}	
		}	
	}
	
	

}
