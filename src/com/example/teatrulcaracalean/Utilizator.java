package com.example.teatrulcaracalean;

import java.io.Serializable;

public class Utilizator implements Serializable {
	public String nume, prenume, email, parola;
	public int telefon, an_nastere;
	public Utilizator(String nume, String prenume, String email, String parola,
			int telefon, int an_nastere) {
		super();
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.parola = parola;
		this.telefon = telefon;
		this.an_nastere = an_nastere;
	}
	
	public Utilizator(){
		super();
	}
	
}
