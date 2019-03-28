package misc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static String YYMMDD ="yyyy-MM-dd";	
	
	public static Date String2Date(String sd,String pattern)
	{
		Date date1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date1 = sdf.parse(sd);
 		} catch (Exception e) {
 			date1 = null;
 		} 
		return date1;

	}
}
