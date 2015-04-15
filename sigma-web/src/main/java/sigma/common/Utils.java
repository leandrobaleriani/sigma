package sigma.common;

import java.util.Collection;

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
}
