package sigma.utils;

import java.util.Comparator;
import java.util.Date;

import sigma.entities.Atencion;

public class AtencionComparator implements Comparator<Atencion> {

	@Override
	public int compare(Atencion o1, Atencion o2) {

		Date inicioAtencion1 = o1.getInicioAtencion();
		Date inicioAtencion2 = o2.getInicioAtencion();

		Date recepcion1 = o1.getFechaRecepcion();
		Date recepcion2 = o2.getFechaRecepcion();

		int result = 0;

		if (null == inicioAtencion1 && null == inicioAtencion2) {
			result = recepcion1.compareTo(recepcion2);
		} else if (null == inicioAtencion1) {
			result = 1;
		} else if (null == inicioAtencion2) {
			result = -1;
		} else {
			result = inicioAtencion1.compareTo(inicioAtencion2);
		}

		return result;
	}

}
