package com.blaze.algoritmos;

import java.util.ArrayList;
import java.util.List;

import com.blaze.controllerview.PrincipalController;

public class SomaPretaVermelha {

	private PrincipalController pc;

	private int numeroSugestao = 0;
	private int numeroAcerto = 0;
	private int somaP = 0;
	private int somaV = 0;
	private String somaPV = "";

	private boolean apostar = false;

	private List<String> listSequenciaSoma;
	private int totalNumerosSomados = 0;

	public SomaPretaVermelha(PrincipalController pc) {
		this.pc = pc;
		this.listSequenciaSoma = new ArrayList<String>();

	}

	public void verificaSomaPretoVermelha(int numeroSorteado) {

		this.totalNumerosSomados += 1;

		// Sorteado bola branca
		if (numeroSorteado == 0) {

			if (this.apostar) {
				// soma acertos de soma
				this.numeroAcerto += 1;
				// escreve na tela nomero de acerto de soma
				this.pc.numeroAcertoSomaPV(this.numeroAcerto);

			}

			if (this.totalNumerosSomados > 3 && !this.listSequenciaSoma.contains(this.somaPV)) {

				// add sequencia na lista
				this.somaPV = String.valueOf(this.somaP) + String.valueOf(this.somaV);
				this.listSequenciaSoma.add(this.somaPV);

				// escreve o numero de sequencias salvas
				this.pc.numeroSequenciaSalvaSomaPV(this.listSequenciaSoma.size());

				// limpa
				this.somaP = 0;
				this.somaV = 0;
				this.somaPV = "";
				this.totalNumerosSomados = 0;
				this.pc.sequenciaSomaPV(this.somaPV);

			} else {
				// limpa
				this.somaP = 0;
				this.somaV = 0;
				this.somaPV = "";
				this.totalNumerosSomados = 0;
				this.pc.sequenciaSomaPV(this.somaPV);

			}
		} else {

			if (numeroSorteado < 8) {
				this.somaV += 1;
			} else {
				this.somaP += 1;
			}

			// escreve a sequencia na tela
			this.somaPV = String.valueOf(this.somaP) + String.valueOf(this.somaV);
			this.pc.sequenciaSomaPV(somaPV);
		}

		// verifica sequencia
		if (this.listSequenciaSoma.contains(this.somaPV)) {

			// mensagem de apostar
			this.pc.apostarSomaPV("APOSTAR");

			// soma numero de avisos
			this.numeroSugestao += 1;

			// escreve numero de avisos na tela
			this.pc.numeroSugestaoSomaPV(this.numeroSugestao);
			;

			this.apostar = true;

		} else {

			// retira mensagem de apostar
			this.pc.apostarSomaPV("");
			this.apostar = false;

		}
		// escreve o numeros na tela
		this.pc.numeroSequenciaSalvaSomaPV(this.listSequenciaSoma.size());
		this.pc.numeroAcertoSomaPV(this.numeroAcerto);
		this.pc.numeroSugestaoSomaPV(this.numeroSugestao);

	}

}
