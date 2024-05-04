package Maquina;

import java.util.List;
import java.util.Stack;

public class MaquinaP {
	private List<Instruccion> codigoP; // memoria de programa
	private Stack<Valor> pilaEvaluacion; // pila de evaluación
	private Valor[] datos; // memoria de datos
	private int pc; // contador de programa
	private GestorMemoriaDinamica gestorMemoriaDinamica;
	private GestorPilaActivaciones gestorPilaActivaciones;
	private ISuma ISUMA; 
	
	public MaquinaP(int tamdatos, int tampila, int tamheap, int ndisplays) {
		
	}
	
	public void ejecuta() {
		while(pc != codigoP.size()) {
		codigoP.get(pc).ejecuta();
		}
	}
	
	//funciones factoria de instrucciones
	public Instruccion suma() {return ISUMA;}
	public Instruccion apilaInt(int val) {return new IApilaInt(val);}
	public Instruccion apilaStr(String val) {return new IApilaStr(val);}
	
	/////////////
	public class ValorInt extends Valor{

		private int valor;
		public ValorInt(int valor) {
		this.valor = valor;
		}
		public int valorInt() {return valor;}
		public String toString() {
		return String.valueOf(valor);
		}
	}
	
	public class ValorString extends Valor{

		private String valor;
		public ValorString(String valor) {
		this.valor = valor;
		}
		public String valorString() {return valor;}
		public String toString() {
		return String.valueOf(valor);
		}
	}
	
	public class ValorBool extends Valor{

		private boolean valor;
		public ValorBool(boolean valor) {
		this.valor = valor;
		}
		public boolean valorBool() {return valor;}
		public String toString() {
		return String.valueOf(valor);
		}
	}
	
	public class ValorReal extends Valor{

		private float valor;
		public ValorReal(float valor) {
		this.valor = valor;
		}
		public float valorReal() {return valor;}
		public String toString() {
		return String.valueOf(valor);
		}
	}

	// todas las insrucciones Suma son iguales:
	// únicamente es necesario instanciarlas una vez
	private class ISuma implements Instruccion {
	public void ejecuta() {
	Valor opnd2 = pilaEvaluacion.pop();
	Valor opnd1 = pilaEvaluacion.pop();
	try {
		pilaEvaluacion.push(new ValorInt(opnd1.valorInt()+opnd2.valorInt()));
	} catch (EAccesoIlegitimo e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pc++;
	}
	public String toString() {return "suma";};
	}
	
	private class IApilaInt implements Instruccion {
		private int valor;
		public IApilaInt(int valor) {
		this.valor = valor;
		}
		public void ejecuta() {
		pilaEvaluacion.push(new ValorInt(valor));
		pc++;
		}
		public String toString() {return "apilaInt("+valor+")";};
	}
	
	private class IApilaStr implements Instruccion {
		private String valor;
		public IApilaStr(String valor) {
		this.valor = valor;
		}
		public void ejecuta() {
		pilaEvaluacion.push(new ValorString(valor));
		pc++;
		}
		public String toString() {return "apila-str("+valor+")";};
	}

}
