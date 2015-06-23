package sigma.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class Utils {
	public static boolean isEmptyString(String str) {
		return null == str || str.isEmpty();
	}

	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);
	}

	public static boolean isEmptyCollection(Collection<?> collection) {
		return null == collection || collection.isEmpty();
	}

	public static boolean isNotEmptyCollection(Collection<?> collection) {
		return !isEmptyCollection(collection);
	}

	public static Integer convertStringToInteger(String str) {
		try {
			return Integer.valueOf(str);
		} catch (Exception nfexc) {
			return null;
		}
	}

	public static String convertToFechaHora(Date fecha) {
		if (null != fecha) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return sdf.format(fecha);
		}
		return StringUtils.EMPTY;
	}

	public static String convertToFecha(Date fecha) {
		if (null != fecha) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(fecha);
		}
		return StringUtils.EMPTY;
	}

	public static Date convertFechaHoraToDate(String fecha, String hora) {
		if (null != fecha && null != hora) {
			String fechaHora = fecha + " " + hora;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			try {
				return sdf.parse(fechaHora);
			} catch (ParseException e) {
			}
		}
		return null;
	}

	public static Date setMaxSeconds(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static Date setMinSeconds(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static String convertToHora(Date fecha) {
		if (null != fecha) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			return sdf.format(fecha);
		}
		return StringUtils.EMPTY;
	}
}
