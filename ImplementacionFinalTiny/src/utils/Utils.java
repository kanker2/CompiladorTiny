package utils;

import asint.SintaxisAbstractaTiny.Acc_reg;
import asint.SintaxisAbstractaTiny.Bool_t;
import asint.SintaxisAbstractaTiny.Error_t;
import asint.SintaxisAbstractaTiny.Exp;
import asint.SintaxisAbstractaTiny.Iden;
import asint.SintaxisAbstractaTiny.Indexacion;
import asint.SintaxisAbstractaTiny.Indireccion;
import asint.SintaxisAbstractaTiny.Int_t;
import asint.SintaxisAbstractaTiny.Ok_t;
import asint.SintaxisAbstractaTiny.Real_t;
import asint.SintaxisAbstractaTiny.String_t;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Tipo_array;
import asint.SintaxisAbstractaTiny.Tipo_definido;
import asint.SintaxisAbstractaTiny.Tipo_puntero;
import asint.SintaxisAbstractaTiny.Tipo_registro;

public class Utils {

	
	
	static public Tipo ref(Tipo t) {
		if (t instanceof Tipo_definido)
			return ref(t.vinculo.tipo);
		return t;
	}

	static public boolean es_designador(Exp e) {
		if (e instanceof Iden || e instanceof Indexacion || e instanceof Acc_reg || e instanceof Indireccion)
			return true;
		return false;
	}
	
	static public Tipo ambos_ok(Tipo t1, Tipo t2) {
		if(t1 instanceof Ok_t && t2 instanceof Ok_t) {
			return new Ok_t();
		}
		return new Error_t();
		
	}

	static public boolean esEntero(Tipo t) {
		return t instanceof Int_t;
	}

	static public boolean esReal(Tipo t) {
		return t instanceof Real_t;
	}

	static public boolean esString(Tipo t) {
		return t instanceof String_t;
	}

	static public boolean esBoolean(Tipo t) {
		return t instanceof Bool_t;
	}

	static public boolean esPuntero(Tipo t) {
		return t instanceof Tipo_puntero;
	}

	static public boolean esArray(Tipo t) {
		return t instanceof Tipo_array;
	}

	static public boolean esRegistro(Tipo t) {
		return t instanceof Tipo_registro;
	}
	
	static public boolean esTipoDefinido(Tipo t) {
		return t instanceof Tipo_definido;
	}
	
	static public boolean esTipoError(Tipo t) {
		return t instanceof Error_t;
	}

	static public boolean esTipoBasico(Tipo t) {
		return esEntero(t) || esReal(t) || esString(t) || esBoolean(t);
	}
}
