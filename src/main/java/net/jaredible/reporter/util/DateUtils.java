package net.jaredible.reporter.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtils {

	public static String prettify(Timestamp timestamp) {
		return new SimpleDateFormat("MM/dd/yyy HH:mm").format(timestamp);
	}

}
