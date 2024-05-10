package maquina_p;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class MaquinaP {
	private List<Instruccion> codigoP; // memoria de programa
	private Stack<Valor> pilaEvaluacion; // pila de evaluacion
	private Valor[] datos; // memoria de datos
	private int pc; // contador del programa
	private GestorMemoriaDinamica gestorMemoriaDinamica;
	private GestorPilaActivaciones gestorPilaActivaciones;

	public static class EAccesoIlegitimo extends RuntimeException {
	}

	// valores basicos de la maquina-p
	private class Valor {
		public int valorInt() {
			throw new EAccesoIlegitimo();
		}

		public float valorReal() {
			throw new EAccesoIlegitimo();
		}

		public boolean valorBool() {
			throw new EAccesoIlegitimo();
		}

		public String valorStr() {
			throw new EAccesoIlegitimo();
		}
	}

	private class ValorInt extends Valor {
		private int valor;

		public ValorInt(int valor) {
			this.valor = valor;
		}

		public int valorInt() {
			return valor;
		}

		public String toString() {
			return String.valueOf(valor);
		}
	}

	private class ValorReal extends Valor {
		private float valor;

		public ValorReal(float valor) {
			this.valor = valor;
		}

		public float valorReal() {
			return this.valor;
		}

		public String toString() {
			return String.valueOf(valor);
		}
	}

	private class ValorBool extends Valor {
		private boolean valor;

		public ValorBool(boolean valor) {
			this.valor = valor;
		}

		public boolean valorBool() {
			return valor;
		}

		public String toString() {
			return String.valueOf(valor);
		}
	}

	private class ValorString extends Valor {
		private String valor;

		public ValorString(String valor) {
			this.valor = valor;
		}

		public String valorString() {
			return valor;
		}

		public String toString() {
			return valor;
		}
	}

	// instrucciones de la maquina-p
	private class IApilaInt implements Instruccion {
		private int valor;

		public IApilaInt(int valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorInt(valor));
			pc++;
		}

		public String toString() {
			return "apilaInt(" + valor + ")";
		};
	}

	private class IApilaReal implements Instruccion {
		private float valor;

		public IApilaReal(float valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorReal(valor));
			pc++;
		}

		public String toString() {
			return "apilaReal(" + valor + ")";
		}
	}

	private class IApilaBool implements Instruccion {
		private boolean valor;

		public IApilaBool(boolean valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorBool(valor));
			pc++;
		}

		public String toString() {
			return "apilaBool(" + valor + ")";
		};
	}

	private class IApilaString implements Instruccion {
		private String valor;

		public IApilaString(String valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorString(valor));
			pc++;
		}

		public String toString() {
			return "apilaString(" + valor + ")";
		};
	}
	
	   private  Instruccion IReadInt;
	   private  class  IReadInt implements  Instruccion{
		   
	       public void ejecuta(){
	           Scanner s = new Scanner(System.in);
	           pilaEvaluacion.push(new ValorInt(s.nextInt()));
	           pc++;
	       }
	       public String toString() {
	           return "Read";
	       }
	   }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
