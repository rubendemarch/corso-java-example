package it.eCommerce.util;

import java.util.Calendar;
import java.util.Random;

public class FileNameGenerator {
		
	public static String fileGen(String fileExt){
		//definisco il seme x generazione random
		long now = Calendar.getInstance().getTimeInMillis();
		
		return now+""+new Random(now).nextInt(1000)+fileExt;
	}

}
