package sigma.common;

import java.text.SimpleDateFormat;
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
}
