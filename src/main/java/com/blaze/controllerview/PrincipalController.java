package com.blaze.controllerview;

import javafx.fxml.FXML;

import com.blaze.roleta.Roleta;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrincipalController {

	private Roleta roleta;

	@FXML
	private TextField tfSequenciaCor;
	@FXML
	private TextField tfNumeroSequenciaSalvaCor;
	@FXML
	private TextField tfNumeroSugestaoCor;
	@FXML
	private TextField tfNumeroAcertoCor;
	@FXML
	private TextField tfApostarCor;
	@FXML
	private TextField tfNumerosSorteados;
	@FXML
	private Button btnIniciar;

	public PrincipalController() {

	}

	// Event Listener on Button.onAction
	@FXML
	public void onBtnIniciar(ActionEvent event) {

		roleta = new Roleta(tfSequenciaCor, tfNumeroSequenciaSalvaCor, tfNumeroSugestaoCor, tfNumeroAcertoCor,
				tfApostarCor, tfNumerosSorteados);

		roleta.start();

		this.btnIniciar.setDisable(true);

	}

	// Event Listener on Button.onAction
	@FXML
	public void btnRest(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on Button.onAction
	@FXML
	public void btnSair(ActionEvent event) {
		System.exit(0);
	}
}
