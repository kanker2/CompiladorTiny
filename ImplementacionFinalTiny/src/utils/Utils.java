package utils;

import asint.SintaxisAbstractaTiny.Acc_reg;
import asint.SintaxisAbstractaTiny.Bool_t;
import asint.SintaxisAbstractaTiny.Cadena;
import asint.SintaxisAbstractaTiny.Exp;
import asint.SintaxisAbstractaTiny.Iden;
import asint.SintaxisAbstractaTiny.Indexacion;
import asint.SintaxisAbstractaTiny.Indireccion;
import asint.SintaxisAbstractaTiny.Int_t;
import asint.SintaxisAbstractaTiny.Real_t;
import asint.SintaxisAbstractaTiny.String_t;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Tipo_definido;

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
	
	static public boolean esTipoBasico(Tipo t) {
		return esEntero(t) || esReal(t) || esString(t) || esBoolean(t);
	}
}
