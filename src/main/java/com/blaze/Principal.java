package com.blaze;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {

	public static void main(String[] args) {

		launch();

	}

	@Override
	public void start(Stage stage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/com/blaze/view/Principal.fxml"));

		Scene scene = new Scene(root);
		stage.setTitle("Ficaremos ricos afinal");

		stage.setScene(scene);

//          inicia maiximizado
//          stage.setMaximized(true);
//          retira botão de fechar
		// stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

	}

}
