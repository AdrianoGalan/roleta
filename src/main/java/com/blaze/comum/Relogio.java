package com.blaze.comum;

import javafx.scene.control.TextField;

public class Relogio extends Thread {

	private TextField tfTempo;
	private int hora = 0;
	private int minuto = 0;
	private int segundo = 0;

	public Relogio(TextField tfTempo) {


		this.tfTempo = tfTempo;
	}

	@Override
	public void run() {

		try {

			this.inicio();

		} catch (Exception e) {

		}

	}

	private void inicio() {

		while (true) {
			
			segundo += 1;
			
			if(segundo == 60) {
				segundo = 0;
				minuto += 1;
				if(minuto == 60) {
					minuto = 0;
					hora += 1;
				}
			}
			
			this.tfTempo.setText(String.valueOf(hora) + ":" + String.valueOf(minuto) + ":" + String.valueOf(segundo));
		
			try {

				sleep(1000);

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

}
