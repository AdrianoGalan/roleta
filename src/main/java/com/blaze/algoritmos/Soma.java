package com.blaze.algoritmos;

import java.util.ArrayList;
import java.util.List;

import com.blaze.controllerview.PrincipalController;

public class Soma {

	private PrincipalController pc;

	private int numeroSugestao = 0;
	private int numeroAcerto = 0;
	private String corSorteada = "";
	private boolean apostar = false;
	
	private List<String> listSequenciaSoma;
	private String sequenciaSoma = "";

	public Soma(PrincipalController pc) {
		this.pc = pc;
		this.listSequenciaSoma = new ArrayList<String>();
		
	}

	public void verificaSoma(int numeroSorteado) {

		// escreve o numeros na tela
		this.pc.numeroSequenciaSalvaSoma(numeroSorteado);
		this.pc.numeroAcertoSoma(this.numeroAcerto);
		this.pc.numeroSugestaoSoma(this.numeroSugestao);
		
		// Sorteado bola branca
				if (numeroSorteado == 0) {

					if (this.apostar) {
						// soma acertos de cor
						this.numeroAcerto += 1;
						// escreve na tela nomero de acerto de cor
						this.pc.numeroAcertoSoma(this.numeroAcerto);

					}

					if (this.sequenciaSoma.length() > 3 && !this.listSequenciaSoma.contains(sequenciaSoma)) {

						// add sequencia na lista
						this.listSequenciaSoma.add(this.sequenciaSoma);

						// escreve o numero de sequencias salvas
						this.pc.numeroSequenciaSalvaCor(this.listSequenciaSoma.size());
						// limpa sequencia de cor
						this.sequenciaSoma = "";
						this.pc.sequenciaCor(this.sequenciaSoma);

					} else {
						// limpa sequencia de cor
						this.sequenciaSoma = "";
						this.pc.sequenciaCor(this.sequenciaSoma);

					}
				} else {

					// escreve a sequencia na tela
					this.sequenciaSoma += this.corSorteada;
					this.pc.sequenciaCor(this.sequenciaSoma);
				}

				// verifica sequencia
				if (this.listSequenciaSoma.contains(sequenciaSoma)) {

					// mensagem de apostar
					this.pc.apostarCor("APOSTAR");

					// soma numero de avisos
					this.numeroSugestao += 1;

					// escreve numero de avisos na tela
					this.pc.numeroSugestaoCor(this.numeroSugestao);
					;

					this.apostar = true;

				} else {

					// retira mensagem de apostar
					this.pc.apostarCor("");
					this.apostar = false;

				}

	}

}
