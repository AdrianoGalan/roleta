package com.blaze.comum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Relogio extends Thread{
	
	@Override
	public void run() {

		try {

			this.inicio();

		} catch (Exception e) {

		}

	}
	
	private void inicio() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Calendar data = new GregorianCalendar(2022, 1, 1, 00, 00, 00);
		
		System.out.println(sdf.format(data.getTime()));
		
	}

}
