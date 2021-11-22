package com.qa.api.challenge.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {
	
	
	public static String getDateFromCurrentDate(int noOfDays) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, noOfDays);  // number of days to add or remove
		return sdf.format(c.getTime());
	}

}
