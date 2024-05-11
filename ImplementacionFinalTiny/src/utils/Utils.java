package utils;

import asint.SintaxisAbstractaTiny.Acc_reg;
import asint.SintaxisAbstractaTiny.Exp;
import asint.SintaxisAbstractaTiny.Iden;
import asint.SintaxisAbstractaTiny.Indexacion;
import asint.SintaxisAbstractaTiny.Indireccion;
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

}
