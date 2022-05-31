package com.blaze.controllerview;

import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.blaze.comum.Relogio;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrincipalController extends Thread {

	// variaveis funcao cor
	private String corSorteada = "";
	private String sequenciaCor = "";
	private boolean avidoCor = false;
	private int numeroAvisosCor = 0;
	private int acertosCor = 0;
	private List<String> listSequenciaCor = new ArrayList<String>();
	Scanner ler = new Scanner(System.in);

	// Variaveis uso comum
	private boolean primeiraBolaBranca = false;
	private int totalBranco = 0;
	private int totalPreto = 0;
	private int totalVermelho = 0;
	private Relogio relogio;
	private int bolasRodada = 0;

	// Uso Comum
	@FXML
	private TextField tfNumerosSorteados;
	@FXML
	private Button btnIniciar;
	@FXML
	private Label lbAviso;
	@FXML
	private TextField tfTotalBrancas;
	@FXML
	private TextField tfTotalPretas;
	@FXML
	private TextField tfTotalVermelhas;
	@FXML
	private TextField tfTempo;

	// sequencia cor
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

	// Soma
	@FXML
	private TextField tfSequenciaSoma;
	@FXML
	private TextField tfNumeroSequenciaSalvaSoma;
	@FXML
	private TextField tfNumeroSugestaoSoma;
	@FXML
	private TextField tfNumeroAcertoSoma;
	@FXML
	private TextField tfApostarSoma;
	private List<Integer> listSoma = new ArrayList<Integer>();
	private int soma = 0;
	private int numeroAvisosSoma = 0;
	private int acertosSoma = 0;
	private boolean avidoSoma = false;

	public PrincipalController() {

	}

	@Override
	public void run() {

		try {

			this.monitoraNumeros();

		} catch (Exception e) {

		}

	}

	// Event Listener on Button.onAction
	@FXML
	public void onBtnIniciar(ActionEvent event) {

		start();
		this.btnIniciar.setDisable(true);

		this.relogio = new Relogio(this.tfTempo);
		this.relogio.start();

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

	private void monitoraNumeros() {
		try {

			this.escreveTf(this.tfNumerosSorteados, "Conectando ao site ...");

			try (Playwright playwright = Playwright.create()) {
				Browser browser = playwright.chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
				Page page = browser.newPage();
				page.navigate("https://blaze.com/pt/games/double");

				String saida = "";
				String saidaNumeros = "";
				int numeroSorteado;

				this.escreveTf(this.tfNumerosSorteados, "Esperando primeira bola Branca");

				while (true) {

					try {

						saida = page.locator("#roulette-timer > div.time-left").textContent();

					} catch (Exception e) {
						System.out.println("erro");
					}
					// entrada manual para testes
					// saida = "Girou";

					if (saida.contains("Girou")) {

						if (saida.length() == 15) {
							saida = saida.substring(12, 14);
						} else {
							saida = saida.substring(12, 13);
						}

						numeroSorteado = Integer.parseInt(saida);

						// entrada manual para testes
//					System.out.println("digite o numero");
//					numeroSorteado = ler.nextInt();

						saida = String.valueOf(numeroSorteado);

						// verifica se saiu a primeia bola branca
						if (this.primeiraBolaBranca) {

							this.bolasRodada += 1;

							saidaNumeros += saida;

							this.escreveTf(this.tfNumerosSorteados, saidaNumeros);

							saidaNumeros += ", ";

							// algoritimos
							this.sequenciaCor(numeroSorteado);
							this.soma(numeroSorteado);

							if (numeroSorteado == 0) {
								saidaNumeros = "";
								this.bolasRodada = 0;
							}

						} else {

							this.escreveTf(this.tfNumerosSorteados,
									"Esperando primeira bola Branca, Numero sorteado: " + numeroSorteado);

							if (numeroSorteado == 0) {
								// set valor depois da primeira bola branca
								this.escreveTf(this.tfNumerosSorteados, "");
								this.primeiraBolaBranca = true;

							}
						}

						page.waitForTimeout(9000);
					}

					page.waitForTimeout(2000);

				}

			}

		} catch (Exception e) {
			System.out.println("Erro moniorar numero");
		}

	}

	// funcao para sequencia de cor
	private void sequenciaCor(int numeroSorteado) {

		try {

			// escreve o numeros na tela
			this.escreveTf(this.tfNumeroSequenciaSalvaCor, String.valueOf(this.listSequenciaCor.size()));
			this.escreveTf(this.tfNumeroAcertoCor, String.valueOf(this.acertosCor));
			this.escreveTf(this.tfNumeroSugestaoCor, String.valueOf(this.numeroAvisosCor));

			// verifica a cor da bola
			if (numeroSorteado == 0) {
				this.corSorteada = "B";

				this.totalBranco += 1;
				this.escreveTf(tfTotalBrancas, String.valueOf(this.totalBranco));

			} else if (numeroSorteado < 8) {
				this.corSorteada = "v";

				this.totalVermelho += 1;
				this.escreveTf(tfTotalVermelhas, String.valueOf(this.totalVermelho));
			} else {
				this.corSorteada = "p";

				this.totalPreto += 1;
				this.escreveTf(tfTotalPretas, String.valueOf(this.totalPreto));
			}

			// Sorteado bola branca
			if (numeroSorteado == 0) {

				if (this.avidoCor) {
					// soma acertos de cor
					this.acertosCor += 1;
					// escreve na tela nomero de acerto de cor
					this.escreveTf(this.tfNumeroAcertoCor, String.valueOf(this.acertosCor));

				}

				if (this.bolasRodada > 3 && !this.listSequenciaCor.contains(sequenciaCor)) {

					// add sequencia na lista
					this.listSequenciaCor.add(this.sequenciaCor);

					// escreve o numero de sequencias salvas
					this.escreveTf(this.tfNumeroSequenciaSalvaCor, String.valueOf(this.listSequenciaCor.size()));
					// limpa sequencia de cor
					this.sequenciaCor = "";
					this.escreveTf(this.tfSequenciaCor, this.sequenciaCor);

				} else {
					// limpa sequencia de cor
					this.sequenciaCor = "";
					this.escreveTf(this.tfSequenciaCor, this.sequenciaCor);

				}
			} else {

				// escreve a sequencia na tela
				this.sequenciaCor += this.corSorteada;
				this.escreveTf(this.tfSequenciaCor, this.sequenciaCor);
			}

			// verifica sequencia
			if (this.listSequenciaCor.contains(sequenciaCor)) {

				// mensagem de apostar
				this.escreveTf(this.tfApostarCor, "Apostar");

				// soma numero de avisos
				this.numeroAvisosCor += 1;

				// escreve numero de avisos na tela
				this.escreveTf(this.tfNumeroSugestaoCor, String.valueOf(this.numeroAvisosCor));

				this.avidoCor = true;

			} else {

				// retira mensagem de apostar
				this.escreveTf(this.tfApostarCor, "");
				this.avidoCor = false;

			}

		} catch (Exception e) {
			System.out.println("Erro sequencia cor");
		}

	}

	private void soma(int numeroSorteado) {

		try {

			// escreve o numeros na tela
			this.escreveTf(this.tfNumeroSequenciaSalvaSoma, String.valueOf(this.listSoma.size()));
			this.escreveTf(this.tfNumeroAcertoSoma, String.valueOf(this.acertosSoma));
			this.escreveTf(this.tfNumeroSugestaoSoma, String.valueOf(this.numeroAvisosSoma));

			this.soma += numeroSorteado;

			if (numeroSorteado == 0) {

				if (this.avidoSoma) {
					// soma acertos
					this.acertosSoma += 1;
					// escreve na tela nomero de acerto
					this.escreveTf(this.tfNumeroAcertoSoma, String.valueOf(this.acertosSoma));

				}

				if (this.bolasRodada > 3 && !this.listSoma.contains(this.soma)) {

					// add sequencia na lista
					this.listSoma.add(this.soma);

					// escreve o numero de sequencias salvas
					this.escreveTf(this.tfNumeroSequenciaSalvaSoma, String.valueOf(this.listSoma.size()));

					// sera soma
					this.soma = 0;
					this.escreveTf(this.tfSequenciaSoma, "");

				} else {

					// zera soma
					this.soma = 0;
					this.escreveTf(this.tfSequenciaCor, "");

				}
			} else {

				// escreve a sequencia na tela
				this.escreveTf(this.tfSequenciaSoma, String.valueOf(this.soma));
			}

			// verifica sequencia
			if (this.listSoma.contains(this.soma)) {

				// mensagem de apostar
				this.escreveTf(this.tfApostarSoma, "Apostar");

				// soma numero de avisos
				this.numeroAvisosSoma += 1;

				// escreve numero de avisos na tela
				this.escreveTf(this.tfNumeroSugestaoSoma, String.valueOf(this.numeroAvisosSoma));

				this.avidoSoma = true;

			} else {

				// retira mensagem de apostar
				this.escreveTf(this.tfApostarSoma, "");
				this.avidoSoma = false;

			}

		} catch (Exception e) {
			System.out.println("Erro soma");
		}

	}

	private void escreveTf(TextField tf, String msg) {

		try {

			tf.setEditable(true);
			tf.setText(msg);
			tf.setEditable(false);

		} catch (Exception e) {
			System.out.println("Erro escrever");
		}

	}
}
