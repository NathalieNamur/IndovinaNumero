package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private int numeroSegreto; 
	private final int NMAX = 100; 
	
	private int tentativiEffettuati;
	private final int TMAX = 8;
	
	private boolean partitaInCorso = false;
	
	//NB: 
	//Le costanti sono dichiarate come "final" e scritte in maiuscolo.
	
	
	//ESTENSIONE ES: 
	//il giocatore non deve poter inserire più di una volta lo stesso numero:
	
	private Set<Integer> tentativi;
	
	
	
	public int getNumeroSegreto() {
		return numeroSegreto;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTentativiEffettuati() {
		return tentativiEffettuati;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	public void nuovaPartita() {
	
		numeroSegreto = (int)((Math.random()*NMAX) +1); //*
	
		//*
		//Math.random : fornisce un numero casuale tra 0.0 e 0.9
		//Math.random()*NMAX) +1 : fornisce un numero casuale tra 1 e NMAX
		//(int)((Math.random()*NMAX) +1) : cast double->int
		
		tentativiEffettuati = 0;
		
		partitaInCorso = true;
	
		
		tentativi = new HashSet<Integer>(); //ESTENSIONE ES.
	}

	
	public int provaTentativo(int t) {
	
		if(!partitaInCorso)
			throw new IllegalStateException("Hai perso. La partita è terminata.");
		
    	if(!tentativoValido(t))
    		throw new InvalidParameterException("Devi inserire un numero tra 1 e "+NMAX+"\nche non hai ancora utilizzato.");
    	
    	
		tentativiEffettuati++;
		
		
		tentativi.add(t); //ESTENSIONE ES.
		
		
		if(tentativiEffettuati == TMAX)
			partitaInCorso = false;
		
		//Definiamo una convenzione:
		//0  -> tentativo = numeroSegreto
		//-1 -> tentativo troppo basso
		//+1 -> tentativo troppo alto
		
		if(t == numeroSegreto) {
			partitaInCorso = false;
			return 0; 
		}
		
		else if(t < numeroSegreto)
			return -1;
		
		else
			return 1; 
			
	}

	
	private boolean tentativoValido(int t) {
		
		if( (t<1 || t>NMAX) || tentativi.contains(t))
    		return false;
		
		return true; 
    	
	}
	
}

