package sigma.utils;

import java.util.Calendar;
import java.util.Date;

public class SigmaUtils {

	public static int getEdad(Date fechaNacimiento) {
		Calendar dob = Calendar.getInstance();
		dob.setTime(fechaNacimiento);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
			age--;
		return age;
	}

}
