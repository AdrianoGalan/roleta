package com.blaze.controller;


import com.blaze.algoritmos.SequenciaCor;
import com.blaze.algoritmos.Soma;
import com.blaze.controllerview.PrincipalController;;

public class NumerosSorteados {

	private String saidaTelaNumeroSoreados = "";
	private PrincipalController pc;

	//algoritmos
	private SequenciaCor sCor;
	private Soma soma;

	public NumerosSorteados(PrincipalController pc) {

		this.pc = pc;
		this.sCor = new SequenciaCor(this.pc);
		this.soma = new Soma(this.pc);
	}

	public boolean primeiraBranca(int numeroSorteado) {
		return numeroSorteado == 0;
	}

	// funcao para monitorar os sorteios
	public void monitoraSorteios(int numeroSorteado) {

		//saida tela
		this.saidaTelaNumeroSoreados += String.valueOf(numeroSorteado);
		this.pc.numerosSorteados(this.saidaTelaNumeroSoreados);
		this.saidaTelaNumeroSoreados += ", ";

		try {
			
			//chama algoritimos passando o numero
			this.sCor.verificaCor(numeroSorteado);
			this.soma.verificaSoma(numeroSorteado);

			if (numeroSorteado == 0) {
			
				this.saidaTelaNumeroSoreados = "";
			} 

				
			

		} catch (Exception e) {
			System.out.println("Erro monitoraSorteios");
		}

	}

}
