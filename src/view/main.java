package view;

import java.util.concurrent.Semaphore;

import controller.VendaIngressos;

public class main {

	public static void main(String[] args) {
		
		Semaphore farol = new Semaphore(1);
		
		for (int i = 0; i <= 300; i++) {
			Thread VendaIngressos = new VendaIngressos(i, farol);
			VendaIngressos.start();
		}
	}
}
