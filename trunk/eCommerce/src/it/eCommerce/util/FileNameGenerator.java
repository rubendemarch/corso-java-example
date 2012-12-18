package it.eCommerce.util;

import java.util.Calendar;
import java.util.Random;

public class FileNameGenerator {
		
	
	public static String fileNameGen(String fileExt){
		long now = Calendar.getInstance().getTimeInMillis();
	return now + "" + new Random(now).nextInt(1000) + fileExt;
	}

}
