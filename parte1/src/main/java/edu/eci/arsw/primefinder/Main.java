package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;

import java.util.Timer;

public class Main {

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> primesFinded = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < 3; i++){
			primesFinded.add(new ArrayList<Integer>());
		}
		
		ArrayList<PrimeFinderThread> threads = new ArrayList<>();
		
		threads.add(new PrimeFinderThread(0,0, 10000000, primesFinded));
		threads.add(new PrimeFinderThread(1, 10000001, 20000000, primesFinded));
		threads.add(new PrimeFinderThread(2, 20000001, 30000000, primesFinded));
		
		for(PrimeFinderThread thread : threads){
			thread.start();
		}

		Timer timer = new Timer();

		timer.schedule(new TimerTask() {
			@Override
			public void run(){
				for(PrimeFinderThread thread : threads){
					thread.setStop(true);
				}
                for(PrimeFinderThread thread : threads){
					System.out.println("El hilo: " + (thread.getNumber()+1) + " Encontro los siguientes numeros: " + primesFinded.get(thread.getNumber()).size());
				}
				System.out.println("Presione enter para continuar. ");
				String read;
				Scanner scanner = new Scanner(System.in);
				read = scanner.nextLine();
				if(read != null){
					scanner.close();
					System.out.println("Continuando Busqueda...");
					for(PrimeFinderThread thread : threads){
						synchronized (thread) {
							thread.resumeSearch();
						} 
					}
				}
	
			}
		}, 5000);			
	}
	
}