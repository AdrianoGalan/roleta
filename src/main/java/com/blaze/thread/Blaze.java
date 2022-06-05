package com.blaze.thread;

import java.util.Scanner;

import com.blaze.controller.NumerosSorteados;
import com.blaze.controllerview.PrincipalController;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import javafx.scene.control.CheckBox;

public class Blaze extends Thread {

	Scanner ler = new Scanner(System.in);

	private PrincipalController pc;
	private NumerosSorteados ns;
	private boolean primeiraBolaBranca = false;
	private CheckBox checkEntradamanial;

	public Blaze(PrincipalController pc, CheckBox checkEntradamanial) {
		super();
		this.pc = pc;
		this.ns = new NumerosSorteados(this.pc);
		this.checkEntradamanial = checkEntradamanial;

	}

	@Override
	public void run() {

		try {

			if (checkEntradamanial.isSelected()) {

				this.entradaManual();

			} else {
				this.checkEntradamanial.setDisable(true);
				this.monitoraNumeros();
			}
		} catch (Exception e) {

		}

	}

	private void entradaManual() {

		int numeroSorteado = 0;

		while (true) {

			System.out.println("Digite um numero");

			numeroSorteado = ler.nextInt();
			this.ns.monitoraSorteios(numeroSorteado);
		}

	}

	private void monitoraNumeros() {

		try {

			try (Playwright playwright = Playwright.create()) {
				Browser browser = playwright.chromium()
						.launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
				Page page = browser.newPage();

				this.pc.numerosSorteados("Conectando ao site ...");

				page.navigate("https://blaze.com/pt/games/double");

				String saida = "";
				int numeroSorteado;

				this.pc.numerosSorteados("Esperando primeira bola ");

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

						numeroSorteado = Integer.parseInt(saida);

						if (this.primeiraBolaBranca) {

							this.ns.monitoraSorteios(numeroSorteado);

						} else {

							this.primeiraBolaBranca = this.ns.primeiraBranca(numeroSorteado);

							this.pc.numerosSorteados("Esperando primeira bola branca ...Numero sorteado " + numeroSorteado);
						}

						page.waitForTimeout(9000);
					}

					page.waitForTimeout(2000);

				}

			}

		} catch (Exception e) {
			System.out.println("Erro moniorar numero " + e.getMessage());
		}

	}

}
