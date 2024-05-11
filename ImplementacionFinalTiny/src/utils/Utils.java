package utils;

import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Tipo_definido;

public class Utils {
	static public Tipo ref(Tipo t) {
    	if (t instanceof Tipo_definido)
    		return ref(t.vinculo.tipo);
    	return t;
	}
}
