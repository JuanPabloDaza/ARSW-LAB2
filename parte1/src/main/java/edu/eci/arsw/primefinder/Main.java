package edu.eci.arsw.primefinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;

public class Main {

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> primesFinded = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < 3; i++){
			primesFinded.add(new ArrayList<Integer>());
			System.out.println(primesFinded.get(i).toString());
		}
		
		ArrayList<PrimeFinderThread> threads = new ArrayList<>();
		
		threads.add(new PrimeFinderThread(0,0, 10000000, primesFinded));
		threads.add(new PrimeFinderThread(1, 10000001, 20000000, primesFinded));
		threads.add(new PrimeFinderThread(2, 20000001, 30000000, primesFinded));
		
		for(PrimeFinderThread thread : threads){
			thread.start();
		}

		primesFinded.get(0);

		while(threads.get(0).isAlive() || threads.get(1).isAlive() || threads.get(2).isAlive()){

			Timer timer = new Timer(5000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
					synchronized (primesFinded){
						try{
							primesFinded.wait();
						}catch(InterruptedException ex){
							System.out.println(ex);
						}
					}
                    for(PrimeFinderThread thread : threads){
						System.out.println("El hilo: " + (thread.getNumber()+1) + " Encontro los siguientes numeros: " + primesFinded.get(thread.getNumber()));
					}
					System.out.println("Presione enter para continuar. ");
					String read;
					Scanner scanner = new Scanner(System.in);
					read = scanner.nextLine();
					if(read != null){
						primesFinded.notifyAll();
					}
                }
            });
	
		}		
	}
	
}
