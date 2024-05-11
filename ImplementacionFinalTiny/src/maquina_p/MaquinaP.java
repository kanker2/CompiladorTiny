package maquina_p;

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

	private class IDesapilaCima implements Instruccion {

		public IDesapilaCima() {
		}

		public void ejecuta() {
			pilaEvaluacion.pop();
			pc++;
		}

		public String toString() {
			return "desapilaCima";
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
	
	private class IStop implements Instruccion {

		public void ejecuta() {
			pc = codigoP.size();
		}

		public String toString() {
			return "Stop";
		}
	}

}
