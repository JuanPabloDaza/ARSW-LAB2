package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b, number;
	ArrayList<ArrayList<Integer>> primesFinded;

	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public PrimeFinderThread(int number, int a, int b, ArrayList<ArrayList<Integer>> primesFinded) {
		super();
		this.number = number;
		this.a = a;
		this.b = b;
	}

	public void run(){
		for (int i=a;i<=b;i++){						
			if (isPrime(i)){
				primesFinded.get(number).add(i);
			}
		}
		
		
	}

	public int getNumber(){
		return number;
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	
	
}
