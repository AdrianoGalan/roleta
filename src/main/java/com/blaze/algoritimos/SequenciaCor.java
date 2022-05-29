package com.blaze.algoritimos;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SequenciaCor extends Thread {

	private TextField tfSequenciaCor;

	private TextField tfNumeroSequenciaSalvaCor;

	private TextField tfNumeroSugestaoCor;

	private TextField tfNumeroAcertoCor;

	private TextField tfApostarCor;

	private String corSorteada = "";
	private String sequenciaCor = "";
	List<String> listSequenciaCor = new ArrayList<String>();

	private int numeroSorteado;
	


	public SequenciaCor(TextField tfSequenciaCor, TextField tfNumeroSequenciaSalvaCor, TextField tfNumeroSugestaoCor,
			TextField tfNumeroAcertoCor, TextField tfApostarCor, int numeroSorteado) {
	
		this.tfSequenciaCor = tfSequenciaCor;
		this.tfNumeroSequenciaSalvaCor = tfNumeroSequenciaSalvaCor;
		this.tfNumeroSugestaoCor = tfNumeroSugestaoCor;
		this.tfNumeroAcertoCor = tfNumeroAcertoCor;
		this.tfApostarCor = tfApostarCor;
		this.numeroSorteado = numeroSorteado;
		start();
		
	}

	@Override
	public void run() {

		try {

			this.inicio();

		} catch (Exception e) {

		}

	}

	

	private void inicio() {
		
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
