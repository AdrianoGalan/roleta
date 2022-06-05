package com.blaze.algoritmos;

import java.util.ArrayList;
import java.util.List;

import com.blaze.controllerview.PrincipalController;

public class SequenciaCor {

	private int totalBranco = 0;
	private int totalPreto = 0;
	private int totalVermelho = 0;
	private int numeroSugestao = 0;
	private int numeroAcerto = 0;
	private String corSorteada = "";
	private boolean apostar = false;

	private PrincipalController pc;
	private List<String> listSequenciaCor;
	private String sequenciaCor = "";

	public SequenciaCor(PrincipalController pc) {
		this.pc = pc;
		this.listSequenciaCor = new ArrayList<String>();
	}

	public void verificaCor(int numeroSorteado) {

		// verifica a cor da bola
		if (numeroSorteado == 0) {
			this.corSorteada = "B";

			this.totalBranco += 1;
			this.pc.totalBrancas(this.totalBranco);

		} else if (numeroSorteado < 8) {
			this.corSorteada = "v";

			this.totalVermelho += 1;
			this.pc.totalVermelhas(this.totalVermelho);
		} else {
			this.corSorteada = "p";

			this.totalPreto += 1;
			this.pc.totalPretas(this.totalPreto);
		}

		// Sorteado bola branca
		if (numeroSorteado == 0) {

			if (this.apostar) {
				// soma acertos de cor
				this.numeroAcerto += 1;
				// escreve na tela nomero de acerto de cor
				this.pc.numeroAcertoCor(this.numeroAcerto);

			}

			if (this.sequenciaCor.length() > 3 && !this.listSequenciaCor.contains(sequenciaCor)) {

				// add sequencia na lista
				this.listSequenciaCor.add(this.sequenciaCor);

				// escreve o numero de sequencias salvas
				this.pc.numeroSequenciaSalvaCor(this.listSequenciaCor.size());
				// limpa sequencia de cor
				this.sequenciaCor = "";
				this.pc.sequenciaCor(this.sequenciaCor);

			} else {
				// limpa sequencia de cor
				this.sequenciaCor = "";
				this.pc.sequenciaCor(this.sequenciaCor);

			}
		} else {

			// escreve a sequencia na tela
			this.sequenciaCor += this.corSorteada;
			this.pc.sequenciaCor(this.sequenciaCor);
		}

		// verifica sequencia
		if (this.listSequenciaCor.contains(sequenciaCor)) {

			// mensagem de apostar
			this.pc.apostarCor("APOSTAR");

			// soma numero de avisos
			this.numeroSugestao += 1;

			// escreve numero de avisos na tela
			this.pc.numeroSugestaoCor(this.numeroSugestao);

			this.apostar = true;

		} else {

			// retira mensagem de apostar
			this.pc.apostarCor("");
			this.apostar = false;

		}

		// escreve o numeros na tela
		this.pc.numeroSequenciaSalvaCor(this.listSequenciaCor.size());
		this.pc.numeroAcertoCor(this.numeroAcerto);
		this.pc.numeroSugestaoCor(this.numeroSugestao);

	}

}
