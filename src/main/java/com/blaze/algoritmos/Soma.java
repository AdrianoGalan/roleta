package com.blaze.algoritmos;

import java.util.ArrayList;
import java.util.List;

import com.blaze.controllerview.PrincipalController;

public class Soma {

	private PrincipalController pc;

	private int numeroSugestao = 0;
	private int numeroAcerto = 0;
	private int soma = 0;
	private boolean apostar = false;

	private List<Integer> listSequenciaSoma;
	private int totalNumerosSomados = 0;

	public Soma(PrincipalController pc) {
		this.pc = pc;
		this.listSequenciaSoma = new ArrayList<Integer>();

	}

	public void verificaSoma(int numeroSorteado) {

		this.totalNumerosSomados += 1;

		// Sorteado bola branca
		if (numeroSorteado == 0) {

			if (this.apostar) {
				// soma acertos de soma
				this.numeroAcerto += 1;
				// escreve na tela nomero de acerto de soma
				this.pc.numeroAcertoSoma(this.numeroAcerto);

			}

			if (this.totalNumerosSomados > 3 && !this.listSequenciaSoma.contains(soma)) {

				// add sequencia na lista
				this.listSequenciaSoma.add(this.soma);

				// escreve o numero de sequencias salvas
				this.pc.numeroSequenciaSalvaSoma(this.listSequenciaSoma.size());

				// limpa
				this.soma = 0;
				this.totalNumerosSomados = 0;
				this.pc.sequenciaSoma(this.soma);

			} else {
				// limpa
				this.soma = 0;
				this.totalNumerosSomados = 0;
				this.pc.sequenciaSoma(this.soma);

			}
		} else {

			// escreve a sequencia na tela
			this.soma += numeroSorteado;
			this.pc.sequenciaSoma(this.soma);
		}

		// verifica sequencia
		if (this.listSequenciaSoma.contains(this.soma)) {

			// mensagem de apostar
			this.pc.apostarSoma("APOSTAR");

			// soma numero de avisos
			this.numeroSugestao += 1;

			// escreve numero de avisos na tela
			this.pc.numeroSugestaoSoma(this.numeroSugestao);
			;

			this.apostar = true;

		} else {

			// retira mensagem de apostar
			this.pc.apostarSoma("");
			this.apostar = false;

		}
		// escreve o numeros na tela
		this.pc.numeroSequenciaSalvaSoma(this.listSequenciaSoma.size());
		this.pc.numeroAcertoSoma(this.numeroAcerto);
		this.pc.numeroSugestaoSoma(this.numeroSugestao);

	}

}
