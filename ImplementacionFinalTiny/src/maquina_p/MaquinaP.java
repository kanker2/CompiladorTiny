package maquina_p;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import maquina_p.GestorMemoriaDinamica.NotEnoughMemoryException;
import maquina_p.GestorPilaActivaciones.DesbordamientoPilaException;

public class MaquinaP {
	private List<Instruccion> codigoP; // memoria de programa
	private Stack<Valor> pilaEvaluacion; // pila de evaluacion
	private Valor[] datos; // memoria de datos
	private int pc; // contador del programa
	private GestorMemoriaDinamica gestorMemoriaDinamica;
	private GestorPilaActivaciones gestorPilaActivaciones;
	private int tamEstatico; // Numero de celdas de la memeoria estatica
	private int tamPila; // Numero de celdas para la pila de registro de activacion
	private int tamMemDin; // Numero de celdas para la memoria dinamica
	private int num_display; // Numero de displays

	public static class EAccesoIlegitimo extends RuntimeException {
	}

	public static class EAccesoFueraDeRango extends RuntimeException {
	}

	public static class EAccesoValorNoDefinido extends RuntimeException {
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

	private class IDesapila implements Instruccion {
		public void ejecuta() {
			pilaEvaluacion.pop();
			pc++;
		}

		public String toString() {
			return "desapila";
		};
	}

	private class IDesapilaEscribe implements Instruccion {

		public IDesapilaEscribe() {
		}

		public void ejecuta() {
			Valor e = pilaEvaluacion.pop();
			System.out.println(e);
			pc++;
		}

		public String toString() {
			return "desapilaCima";
		};
	}

	private class IReadInt implements Instruccion {

		public void ejecuta() {
			Scanner s = new Scanner(System.in);
			pilaEvaluacion.push(new ValorInt(s.nextInt()));
			pc++;
		}

		public String toString() {
			return "ReadInt";
		}
	}

	private class IReadReal implements Instruccion {

		public void ejecuta() {
			Scanner s = new Scanner(System.in);
			pilaEvaluacion.push(new ValorReal(s.nextFloat()));
			pc++;
		}

		public String toString() {
			return "ReadReal";
		}
	}

	private class IReadString implements Instruccion {

		public void ejecuta() {
			Scanner s = new Scanner(System.in);
			pilaEvaluacion.push(new ValorString(s.nextLine()));
			pc++;
		}

		public String toString() {
			return "ReadString";
		}
	}

	private class IReadBool implements Instruccion {

		public void ejecuta() {
			Scanner s = new Scanner(System.in);
			pilaEvaluacion.push(new ValorBool(s.nextBoolean()));
			pc++;
		}

		public String toString() {
			return "ReadBool";
		}
	}

	private class ISumaInt implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() + opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "SumaInt";
		}
	}

	private class ISumaReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() + opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "SumaReal";
		}
	}

	private class IRestaInt implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() - opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "RestaInt";
		}
	}

	private class IRestaReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() - opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "RestaReal";
		}
	}

	private class IMultInt implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() * opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "multInt";
		};
	}

	private class IMultReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() * opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "multReal";
		};
	}

	private class IDivInt implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() / opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "divInt";
		}
	}

	private class IDivReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() / opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "divReal";
		}
	}

	private class IAnd implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorBool() && opnd2.valorBool()));
			pc++;
		}

		public String toString() {
			return "and";
		};
	}

	private class IOr implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorBool() || opnd2.valorBool()));
			pc++;
		}

		public String toString() {
			return "or";
		};
	}

	private class IMod implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() % opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "mod";
		}
	}

	private class IMenosUnarioInt implements Instruccion {

		public void ejecuta() {
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt((-1) * opnd1.valorInt()));
			pc++;

		}

		public String toString() {
			return "MenosUnarioInt";
		}
	}

	private class IMenosUnarioReal implements Instruccion {

		public void ejecuta() {
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal((-1) * opnd1.valorReal()));
			pc++;

		}

		public String toString() {
			return "MenosUnarioReal";
		}
	}

	private class IMayorInt implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorInt() > opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "mayorInt";
		}
	}

	private class IMayorReal implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() > opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "mayorReal";
		}
	}

	private class IMayorBool implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			int var = opnd1.valorBool() ? 1 : 0;
			int var2 = opnd2.valorBool() ? 1 : 0;
			pilaEvaluacion.push(new ValorBool(var > var2));
			pc++;
		}

		public String toString() {
			return "mayorBool";
		}
	}

	private class IMayorString implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();

			if (opnd1.valorStr().compareTo(opnd2.valorStr()) > 0) {
				pilaEvaluacion.push(new ValorBool(true));
			} else {
				pilaEvaluacion.push(new ValorBool(false));
			}
			pc++;
		}

		public String toString() {
			return "mayorString";
		}
	}

	private class IMayorIgualInt implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorInt() >= opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "mayorIgualInt";
		}
	}

	private class IMayorIgualReal implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() >= opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "mayorIgualReal";
		}
	}

	private class IMayorIgualBool implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			int var = opnd1.valorBool() ? 1 : 0;
			int var2 = opnd2.valorBool() ? 1 : 0;
			pilaEvaluacion.push(new ValorBool(var >= var2));
			pc++;
		}

		public String toString() {
			return "mayorIgualBool";
		}
	}

	private class IMayorIgualString implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();

			if (opnd1.valorStr().compareTo(opnd2.valorStr()) >= 0) {
				pilaEvaluacion.push(new ValorBool(true));
			} else {
				pilaEvaluacion.push(new ValorBool(false));
			}
			pc++;
		}

		public String toString() {
			return "mayorIgualString";
		}
	}

	private class IMenorInt implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorInt() < opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "menorInt";
		}
	}

	private class IMenorReal implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() < opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "menorReal";
		}
	}

	private class IMenorBool implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			int var = opnd1.valorBool() ? 1 : 0;
			int var2 = opnd2.valorBool() ? 1 : 0;
			pilaEvaluacion.push(new ValorBool(var < var2));
			pc++;
		}

		public String toString() {
			return "menorBool";
		}
	}

	private class IMenorString implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();

			if (opnd1.valorStr().compareTo(opnd2.valorStr()) < 0) {
				pilaEvaluacion.push(new ValorBool(true));
			} else {
				pilaEvaluacion.push(new ValorBool(false));
			}
			pc++;
		}

		public String toString() {
			return "menorString";
		}
	}

	private class IMenorIgualInt implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorInt() <= opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "menorIgualInt";
		}
	}

	private class IMenorIgualReal implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() <= opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "menorIgualReal";
		}
	}

	private class IMenorIgualBool implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			int var = opnd1.valorBool() ? 1 : 0;
			int var2 = opnd2.valorBool() ? 1 : 0;
			pilaEvaluacion.push(new ValorBool(var <= var2));
			pc++;
		}

		public String toString() {
			return "menorIgualBool";
		}
	}

	private class IMenorIgualString implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();

			if (opnd1.valorStr().compareTo(opnd2.valorStr()) <= 0) {
				pilaEvaluacion.push(new ValorBool(true));
			} else {
				pilaEvaluacion.push(new ValorBool(false));
			}
			pc++;
		}

		public String toString() {
			return "menorIgualString";
		}
	}

	private class IComparacionInt implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorInt() == opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "comparacionInt";
		}
	}

	private class IComparacionReal implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() == opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "comparacionReal";
		}
	}

	private class IComparacionBool implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			int var = opnd1.valorBool() ? 1 : 0;
			int var2 = opnd2.valorBool() ? 1 : 0;
			pilaEvaluacion.push(new ValorBool(var < var2));
			pc++;
		}

		public String toString() {
			return "comparacionBool";
		}
	}

	private class IComparacionString implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();

			if (opnd1.valorStr().equals(opnd2.valorStr())) {
				pilaEvaluacion.push(new ValorBool(true));
			} else {
				pilaEvaluacion.push(new ValorBool(false));
			}
			pc++;
		}

		public String toString() {
			return "comparacionString";
		}
	}

	private class IDistintoInt implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorInt() != opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "distintoInt";
		}
	}

	private class IDistintoReal implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() != opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "distintoReal";
		}
	}

	private class IDistintoBool implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			int var = opnd1.valorBool() ? 1 : 0;
			int var2 = opnd2.valorBool() ? 1 : 0;
			pilaEvaluacion.push(new ValorBool(var != var2));
			pc++;
		}

		public String toString() {
			return "distintoBool";
		}
	}

	private class IDistintoString implements Instruccion {

		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(!opnd1.valorStr().equals(opnd2.valorStr())));
		}

		public String toString() {
			return "distintoString";
		}
	}

	private class IInt2Real implements Instruccion {
		public void ejecuta() {
			Valor val = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal((float) val.valorInt()));
			pc++;
		}

		public String toString() {
			return "Int2Real";
		}
	}

	private class IDup implements Instruccion {
		public void ejecuta() {
			pilaEvaluacion.push(pilaEvaluacion.peek());
			pc++;
		}

		public String toString() {
			return "dup";
		}
	}

	private class ICopia implements Instruccion {
		private int n;

		public ICopia(int n) {
			this.n = n;
		}

		public void ejecuta() {
			int dir0 = pilaEvaluacion.pop().valorInt();
			int dir1 = pilaEvaluacion.pop().valorInt();
			for (int i = 0; i < n; ++i) {
				datos[dir1 + i] = datos[dir0 + i];
			}
			pc++;
		}

		public String toString() {
			return "copia";
		}

	}

	private class IAlloc implements Instruccion {
		private int tam;

		public IAlloc(int tam) {
			this.tam = tam;
		}

		public void ejecuta() {
			int dir_ini;
			try {
				dir_ini = gestorMemoriaDinamica.allocaMemoria(tam);
				pilaEvaluacion.push(new ValorInt(dir_ini));
			} catch (NotEnoughMemoryException e) {
				e.printStackTrace();
			}

			pc++;
		}

		public String toString() {
			return "alloc(" + tam + ")";
		};
	}

	private class IDealloc implements Instruccion {
		private int tam;

		public IDealloc(int tam) {
			this.tam = tam;
		}

		public void ejecuta() {
			int dir_ini = pilaEvaluacion.pop().valorInt();
			gestorMemoriaDinamica.liberaMemoria(dir_ini, tam);
			pc++;
		}

		public String toString() {
			return "dealloc(" + tam + ")";
		};
	}

	private class IApilaInd implements Instruccion {
		public void ejecuta() {
			int dir = pilaEvaluacion.pop().valorInt();
			pilaEvaluacion.push(datos[dir]);
			pc++;
		}

		public String toString() {
			return "apilaInd";
		};
	}

	private class IDesapilaInd implements Instruccion {
		public void ejecuta() {
			Valor v = pilaEvaluacion.pop();
			int d = pilaEvaluacion.pop().valorInt();
			datos[d] = v;
			pc++;
		}

		public String toString() {
			return "desapilaind";
		};
	}

	private class IActiva implements Instruccion {
		private int nivel;
		private int tam_datos;
		private int dir_ret;

		public IActiva(int nivel, int tam_datos, int dir_ret) {
			this.nivel = nivel;
			this.tam_datos = tam_datos;
			this.dir_ret = dir_ret;
		}

		public void ejecuta() {

			int dir_base;
			try {
				// Reserva espacio para los parametros
				dir_base = gestorPilaActivaciones.reservaRegistroActivacion(tam_datos);
				// Guarda direccion de retorno
				datos[dir_base] = new ValorInt(dir_ret);

				// Salvaguarda el valor del display
				datos[dir_base + 1] = new ValorInt(gestorPilaActivaciones.valorDisplay(nivel));
				pilaEvaluacion.push(new ValorInt(dir_base + 2));
				pc++;
			} catch (DesbordamientoPilaException e) {
				e.printStackTrace();
			}

		}

		public String toString() {
			return "activa(" + nivel + "," + tam_datos + "," + dir_ret + ")";
		}
	}

	private class IDesactiva implements Instruccion {
		private int nivel;
		private int tam_datos;

		public IDesactiva(int nivel, int tam_datos) {
			this.nivel = nivel;
			this.tam_datos = tam_datos;
		}

		public void ejecuta() {
			// Libera el espacio ocupado
			int dir_base = gestorPilaActivaciones.liberaRegistroActivacion(tam_datos);

			// Restauramos el antiguo valor del display
			gestorPilaActivaciones.setValorDisplay(nivel, datos[dir_base + 1].valorInt());

			// Apilamos la direccion de retorno
			pilaEvaluacion.push(datos[dir_base]);
			pc++;
		}

		public String toString() {
			return "desactiva(" + nivel + "," + tam_datos + ")";
		}

	}

	private class IApilad implements Instruccion {
		private int nivel;

		public IApilad(int nivel) {
			this.nivel = nivel;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorInt(gestorPilaActivaciones.valorDisplay(nivel)));
			pc++;
		}

		public String toString() {
			return "apilad(" + nivel + ")";
		}

	}

	private class IDesapilad implements Instruccion {
		private int nivel;

		public IDesapilad(int nivel) {
			this.nivel = nivel;
		}

		public void ejecuta() {
			gestorPilaActivaciones.setValorDisplay(nivel, pilaEvaluacion.pop().valorInt());
			pc++;
		}

		public String toString() {
			return "desapilad(" + nivel + ")";
		}
	}

	private class IIrInd implements Instruccion {
		public void ejecuta() {
			pc = pilaEvaluacion.pop().valorInt();
		}

		public String toString() {
			return "irInd";
		}
	}

	private class IrV implements Instruccion {
		private int d;

		public IrV(int d) {
			this.d = d;
		}

		public void ejecuta() {
			Valor v = pilaEvaluacion.pop();
			if (v.valorBool() == true) {
				pc = d;
			} else {
				pc++;
			}
		}

		public String toString() {
			return "irV";
		}
	}

	private class IrF implements Instruccion {
		private int d;

		public IrF(int d) {
			this.d = d;
		}

		public void ejecuta() {
			Valor v = pilaEvaluacion.pop();
			if (v.valorBool() == false) {
				pc = d;
			} else {
				pc++;
			}
		}

		public String toString() {
			return "irF";
		}
	}

	private class IrA implements Instruccion {
		private int d;

		public IrA(int d) {
			this.d = d;
		}

		public void ejecuta() {
			pc = d;
		}

		public String toString() {
			return "irV";
		}
	}

	private class IrInd implements Instruccion {

		public void ejecuta() {
			int d = pilaEvaluacion.pop().valorInt();
			pc = d;
		}

		public String toString() {
			return "ir-ind()";
		}
	}

	private class IStop implements Instruccion {

		public void ejecuta() {
			pc = codigoP.size();
		}

		public String toString() {
			return "Stop";
		}
	}

	private IApilaInt IAPILAINT;
	private IApilaReal IAPILAREAL;
	private IApilaBool IAPILABOOL;
	private IApilaString IAPILASTRING;
	private IDesapila IDESAPILA;
	private IDesapilaEscribe IDESAPILAE;
	private IReadInt IREADINT;
	private IReadReal IREADREAL;
	private IReadBool IREADBOOL;
	private IReadString IREADSTRING;
	private ISumaInt ISUMAINT;
	private ISumaReal ISUMAREAL;
	private IRestaInt IRESTAINT;
	private IRestaReal IRESTAREAL;
	private IMultInt IMULTINT;
	private IMultReal IMULTREAL;
	private IDivInt IDIVINT;
	private IDivReal IDIVREAL;
	private IAnd IAND;
	private IOr IOR;
	private IMod IMOD;
	private IMenosUnarioInt IMENOSUNARIOINT;
	private IMenosUnarioReal IMENOSUNARIOREAL;
	private IMayorInt IMAYORINT;
	private IMayorReal IMAYORREAL;
	private IMayorBool IMAYORBOOL;
	private IMayorString IMAYORSTRING;
	private IMayorIgualInt IMAYORIGUALINT;
	private IMayorIgualReal IMAYORIGUALREAL;
	private IMayorIgualBool IMAYORIGUALBOOL;
	private IMayorIgualString IMAYORIGUALSTRING;
	private IMenorInt IMENORINT;
	private IMenorReal IMENORREAL;
	private IMenorBool IMENORBOOL;
	private IMenorString IMENORSTRING;
	private IMenorIgualInt IMENORIGUALINT;
	private IMenorIgualReal IMENORIGUALREAL;
	private IMenorIgualBool IMENORIGUALBOOL;
	private IMenorIgualString IMENORIGUALSTRING;
	private IComparacionInt ICOMPARACIONINT;
	private IComparacionReal ICOMPARACIONREAL;
	private IComparacionBool ICOMPARACIONBOOL;
	private IComparacionString ICOMPARACIONSTRING;
	private IDistintoInt IDISTINTOINT;
	private IDistintoReal IDISTINTOREAL;
	private IDistintoBool IDISTINTOBOOL;
	private IDistintoString IDISTINTOSTRING;
	private IInt2Real IINT2REAL;
	private IDup IDUP;
	private ICopia ICOPIA;
	private IAlloc IALLOC;
	private IDealloc IDEALLOC;
	private IApilaInd IAPILAIND;
	private IDesapilaInd IDESAPILAIND;
	private IActiva IACTIVA;
	private IDesactiva IDESACTIVA;
	private IApilad IAPILAD;
	private IDesapilad IDESAPILAD;
	private IrV IRV;
	private IrF IRF;
	private IrA IRA;
	private IrInd IRIND;
	private IStop ISTOP;

	public MaquinaP(int tamEstatico, int tamPila, int tamMemDin, int num_display) {
		this.tamEstatico = tamEstatico;
		this.tamPila = tamPila;
		this.tamMemDin = tamMemDin;
		this.num_display = num_display;
		this.codigoP = new ArrayList<>();
		this.pilaEvaluacion = new Stack<>();
		this.datos = new Valor[tamEstatico + tamPila + tamMemDin];
		this.pc = 0;
		this.gestorPilaActivaciones = new GestorPilaActivaciones(tamEstatico, tamEstatico + tamPila - 1, num_display);
		this.gestorMemoriaDinamica = new GestorMemoriaDinamica(tamEstatico + tamPila,
				tamEstatico + tamPila + tamMemDin - 1);

		IDESAPILA = new IDesapila();
		IDESAPILAE = new IDesapilaEscribe();
		IREADINT = new IReadInt();
		IREADREAL = new IReadReal();
		IREADBOOL = new IReadBool();
		IREADSTRING = new IReadString();
		ISUMAINT = new ISumaInt();
		ISUMAREAL = new ISumaReal();
		IRESTAINT = new IRestaInt();
		IRESTAREAL = new IRestaReal();
		IMULTINT = new IMultInt();
		IMULTREAL = new IMultReal();
		IDIVINT = new IDivInt();
		IDIVREAL = new IDivReal();
		IAND = new IAnd();
		IOR = new IOr();
		IMOD = new IMod();
		IMENOSUNARIOINT = new IMenosUnarioInt();
		IMENOSUNARIOREAL = new IMenosUnarioReal();
		IMAYORINT = new IMayorInt();
		IMAYORREAL = new IMayorReal();
		IMAYORBOOL = new IMayorBool();
		IMAYORSTRING = new IMayorString();
		IMAYORIGUALINT = new IMayorIgualInt();
		IMAYORIGUALREAL = new IMayorIgualReal();
		IMAYORIGUALBOOL = new IMayorIgualBool();
		IMAYORIGUALSTRING = new IMayorIgualString();
		IMENORINT = new IMenorInt();
		IMENORREAL = new IMenorReal();
		IMENORBOOL = new IMenorBool();
		IMENORSTRING = new IMenorString();
		IMENORIGUALINT = new IMenorIgualInt();
		IMENORIGUALREAL = new IMenorIgualReal();
		IMENORIGUALBOOL = new IMenorIgualBool();
		IMENORIGUALSTRING = new IMenorIgualString();
		ICOMPARACIONINT = new IComparacionInt();
		ICOMPARACIONREAL = new IComparacionReal();
		ICOMPARACIONBOOL = new IComparacionBool();
		ICOMPARACIONSTRING = new IComparacionString();
		IDISTINTOINT = new IDistintoInt();
		IDISTINTOREAL = new IDistintoReal();
		IDISTINTOBOOL = new IDistintoBool();
		IDISTINTOSTRING = new IDistintoString();
		IINT2REAL = new IInt2Real();
		IDUP = new IDup();
		IAPILAIND = new IApilaInd();
		IDESAPILAIND = new IDesapilaInd();
		IRIND = new IrInd();
		ISTOP = new IStop();
	}

	public void emit(Instruccion inst) {
		this.codigoP.add(inst);
	}

	public void ejecuta() {
		while (pc != codigoP.size()) {
			codigoP.get(pc).ejecuta();
			mostrarEstadoMaquinaP();
		}
	}

	public void mostrarInstrucciones() {
		System.out.println("CodigoP:");
		for (int i = 0; i < codigoP.size(); i++) {
			System.out.println("<" + i + ">" + ":" + codigoP.get(i));
		}
	}

	public void mostrarEstadoMaquinaP() {
		System.out.println("PC:" + pc);
		
		System.out.println("Memoria de Datos:");
		for (int i = 0; i < datos.length; i++) {
			System.out.println("<" + i + ">" + ":" + datos[i]);
		}

		System.out.println("Pila de evaluacion:");
		for (int i = 0; i < pilaEvaluacion.size(); i++) {
			System.out.println("<" + i + ">" + ":" + pilaEvaluacion.get(i));
		}
		
		System.out.println("Displays:");
		for (int i = 1; i <= num_display; i++)
			System.out.println("<" + i+ ">" + ":" + gestorPilaActivaciones.valorDisplay(i));
		System.out.println("-----------------");
	}

	public static void main(String[] args) {
	       MaquinaP m = new MaquinaP(5,10,10,2);
	        
//	       int x;
//	       proc store(int v) {
//	       x = v
//	       }
//	       store(5)
	       
	       m.emit(m.activa(1,1,8));
	       m.emit(m.dup());
	       m.emit(m.apila_int(0));
	       m.emit(m.suma_int());
	       m.emit(m.apila_int(5));
	       m.emit(m.desapila_ind());
	       m.emit(m.desapilad(1));
	       m.emit(m.ir_a(9));
	       m.emit(m.stop());
	       m.emit(m.apila_int(0));
	       m.emit(m.apilad(1));
	       m.emit(m.apila_int(0));
	       m.emit(m.suma_int());
	       m.emit(m.copia(1));
	       m.emit(m.desactiva(1,1));
			m.emit(m.ir_ind());
	       m.ejecuta();

	   }

	public Instruccion apila_int(int n) {
		return new IApilaInt(n);
	}

	public Instruccion apila_real(float n) {
		return new IApilaReal(n);
	}

	public Instruccion apila_bool(boolean n) {
		return new IApilaBool(n);
	}

	public Instruccion apila_String(String n) {
		return new IApilaString(n);
	}

	public Instruccion desapila() {
		return IDESAPILA;
	}

	public Instruccion desapila_escribe() {
		return IDESAPILAE;
	}

	public Instruccion read_int() {
		return IREADINT;
	}

	public Instruccion read_real() {
		return IREADREAL;
	}

	public Instruccion read_bool() {
		return IREADBOOL;
	}

	public Instruccion read_string() {
		return IREADSTRING;
	}

	public Instruccion suma_int() {
		return ISUMAINT;
	}

	public Instruccion suma_real() {
		return ISUMAREAL;
	}

	public Instruccion resta_int() {
		return IRESTAINT;
	}

	public Instruccion resta_real() {
		return IRESTAREAL;
	}

	public Instruccion mult_int() {
		return IMULTINT;
	}

	public Instruccion mult_real() {
		return IMULTREAL;
	}

	public Instruccion div_int() {
		return IDIVINT;
	}

	public Instruccion div_real() {
		return IDIVREAL;
	}

	public Instruccion and() {
		return IAND;
	}

	public Instruccion or() {
		return IOR;
	}

	public Instruccion mod() {
		return IMOD;
	}

	public Instruccion menosUnario_int() {
		return IMENOSUNARIOINT;
	}

	public Instruccion menosUnario_real() {
		return IMENOSUNARIOREAL;
	}

	public Instruccion mayor_int() {
		return IMAYORINT;
	}

	public Instruccion mayor_real() {
		return IMAYORREAL;
	}

	public Instruccion mayor_bool() {
		return IMAYORBOOL;
	}

	public Instruccion mayor_string() {
		return IMAYORSTRING;
	}

	public Instruccion mayorIgual_int() {
		return IMAYORIGUALINT;
	}

	public Instruccion mayorIgual_real() {
		return IMAYORIGUALREAL;
	}

	public Instruccion mayorIgual_bool() {
		return IMAYORIGUALBOOL;
	}

	public Instruccion mayorIgual_string() {
		return IMAYORIGUALSTRING;
	}

	public Instruccion menor_int() {
		return IMENORINT;
	}

	public Instruccion menor_real() {
		return IMENORREAL;
	}

	public Instruccion menor_bool() {
		return IMENORBOOL;
	}

	public Instruccion menor_string() {
		return IMENORSTRING;
	}

	public Instruccion menorIgual_int() {
		return IMENORIGUALINT;
	}

	public Instruccion menorIgual_real() {
		return IMENORIGUALREAL;
	}

	public Instruccion menorIgual_bool() {
		return IMENORIGUALBOOL;
	}

	public Instruccion menorIgual_string() {
		return IMENORIGUALSTRING;
	}

	public Instruccion comparacion_int() {
		return ICOMPARACIONINT;
	}

	public Instruccion comparacion_real() {
		return ICOMPARACIONREAL;
	}

	public Instruccion comparacion_bool() {
		return ICOMPARACIONBOOL;
	}

	public Instruccion comparacion_string() {
		return ICOMPARACIONSTRING;
	}

	public Instruccion distinto_int() {
		return IDISTINTOINT;
	}

	public Instruccion distinto_real() {
		return IDISTINTOREAL;
	}

	public Instruccion distinto_bool() {
		return IDISTINTOBOOL;
	}

	public Instruccion distinto_string() {
		return IDISTINTOSTRING;
	}

	public Instruccion int2real() {
		return IINT2REAL;
	}

	public Instruccion dup() {
		return IDUP;
	}

	public Instruccion apila_ind() {
		return IAPILAIND;
	}

	public Instruccion desapila_ind() {
		return IDESAPILAIND;
	}

	public Instruccion stop() {
		return ISTOP;
	}

	public Instruccion copia(int n) {
		return new ICopia(n);
	}

	public Instruccion alloc(int n) {
		return new IAlloc(n);
	}

	public Instruccion dealloc(int n) {
		return new IDealloc(n);
	}

	public Instruccion activa(int nivel, int tamDatos, int dir_ret) {
		return new IActiva(nivel, tamDatos, dir_ret);
	}

	public Instruccion desactiva(int nivel, int tamDatos) {
		return new IDesactiva(nivel, tamDatos);
	}

	public Instruccion apilad(int nivel) {
		return new IApilad(nivel);
	}

	public Instruccion desapilad(int nivel) {
		return new IDesapilad(nivel);
	}

	public Instruccion ir_v(int d) {
		return new IrV(d);
	}

	public Instruccion ir_f(int d) {
		return new IrF(d);
	}

	public Instruccion ir_a(int d) {
		return new IrA(d);
	}

	public Instruccion ir_ind() {
		return IRIND;
	}

}
