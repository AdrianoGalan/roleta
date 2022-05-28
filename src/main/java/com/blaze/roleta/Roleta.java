package com.blaze.roleta;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Roleta extends Thread {

	private TextField tfSequenciaCor;

	private TextField tfNumeroSequenciaSalvaCor;

	private TextField tfNumeroSugestaoCor;

	private TextField tfNumeroAcertoCor;

	private TextField tfApostarCor;

	private String corSorteada = "";
	private String sequenciaCor = "";
	List<String> listSequenciaCor = new ArrayList<String>();

	private TextField tfNumerosSorteados;
	


	public Roleta(TextField tfSequenciaCor, TextField tfNumeroSequenciaSalvaCor, TextField tfNumeroSugestaoCor,
			TextField tfNumeroAcertoCor, TextField tfApostarCor, TextField tfNumerosSorteados) {
	
		this.tfSequenciaCor = tfSequenciaCor;
		this.tfNumeroSequenciaSalvaCor = tfNumeroSequenciaSalvaCor;
		this.tfNumeroSugestaoCor = tfNumeroSugestaoCor;
		this.tfNumeroAcertoCor = tfNumeroAcertoCor;
		this.tfApostarCor = tfApostarCor;
		this.tfNumerosSorteados = tfNumerosSorteados;
	}

	@Override
	public void run() {

		try {

			this.inicio();

		} catch (Exception e) {

		}

	}

	private void inicio() {

		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium()
					.launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
			Page page = browser.newPage();
			page.navigate("https://blaze.com/pt/games/double");

			String saida = "";
			String saidaNumeros = "";
			int numeroSorteado;

			while (true) {

				try {

					saida = page.locator("#roulette-timer > div.time-left").textContent();

				} catch (Exception e) {
					System.out.println("erro");
				}

				if (saida.contains("Girou")) {

					if (saida.length() == 15) {
						saida = saida.substring(12, 14);
					} else {
						saida = saida.substring(12, 13);
					}

					saidaNumeros += saida;
					
					this.escreveTf(this.tfNumerosSorteados, saidaNumeros);

					saidaNumeros += ", ";

					numeroSorteado = Integer.parseInt(saida);

					this.sequenciaCor(numeroSorteado);


					page.waitForTimeout(9000);
				}

				page.waitForTimeout(2000);

			}

		}

	}

	private void sequenciaCor(int numeroSorteado) {
		
		//escreve o numero de seuencias salvas
		this.escreveTf(this.tfNumeroSequenciaSalvaCor, String.valueOf(this.listSequenciaCor.size()));
		
		//retira mensagem de apostar
		this.escreveTf(this.tfApostarCor, "");

		
		if (numeroSorteado == 0) {
			this.corSorteada = "B";
		} else if (numeroSorteado < 8) {

			this.corSorteada = "v";
		} else {
			this.corSorteada = "p";
		}

		if (this.corSorteada.equals("B")) {
			if (this.listSequenciaCor.contains(this.sequenciaCor)) {
				
				// mensagem de apostar
				this.escreveTf(this.tfApostarCor, "Apostar");
			
			} else {
				this.listSequenciaCor.add(this.sequenciaCor);
				this.sequenciaCor = "";
			}

		} else {

			this.sequenciaCor += this.corSorteada;
			this.escreveTf(this.tfSequenciaCor, this.sequenciaCor);

		}

	}
	
	private void escreveTf(TextField tf, String msg) {
		
		tf.setEditable(true);
		tf.setText(msg);
		tf.setEditable(false);
		
	}

}
