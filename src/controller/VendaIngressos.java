package controller;

import java.util.concurrent.Semaphore;

public class VendaIngressos extends Thread{
		private int pessoas;
		private static int ingressos = 100;
		private Semaphore farol;
		boolean status = false;
		
	public VendaIngressos(int pessoas, Semaphore farol) {
		this.pessoas = pessoas;
		this.farol = farol;
	}
	
	public void run() {
		login();
		if(status == true){
			ProcessoCompra();
			if(status == true) {
				try {
					farol.acquire();
					ValidaCompra();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					farol.release();
				}
			}
		}
	}
	
	

	//Faz o login do sistema e conta o tempo	
	private void login() {
		int tempo = 0;
		tempo = (int) (Math.random() * 1951) + 50;
		if(tempo < 1000) {
			status = true;
		}else {
			System.out.println("ERRO DE TIMEOUT - " + "A pessoa " + pessoas + " estorou o tempo e nao podera efetuar a compra\n");
		}
	}
	
	//Contabiliza o tempo da fila 
	private void ProcessoCompra() {
		status = false;
		int tempo = 0;
		tempo = (int) (Math.random() * 2001) + 1000;
		if(tempo < 2500) {
			status = true;
		}else{
			System.out.println("ERRO FINAL DE TEMPO DE SESSÃO - " + " A pessoa " + pessoas + " estorou o tempo e nao podera efetuar a compra\n");
		}
	}
	
	//Efetua compra do ingresso	
	private void ValidaCompra() {
		int quantidades = 0;
		quantidades = (int) (Math.random() * 4) + 1;
		ingressos = ingressos - quantidades;
		if(ingressos >= 0) {	
			System.out.println("Cliente Nº " + pessoas + " SUA COMPRA FOI APROVADA - Quantidade de ingressos - " + quantidades + "\nIngressos disponiveis - " + ingressos + "\n");
		}else {
			if(ingressos < 0) {
				ingressos = ingressos + quantidades;
				System.out.println("CLIENTE Nº "+ pessoas + " COMPRA NAO EFETUADA, Os ingressos estao esgotados\n" );
			}
		}
	}
}