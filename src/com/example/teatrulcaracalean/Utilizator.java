package com.example.teatrulcaracalean;

import java.io.Serializable;

public class Utilizator implements Serializable {
	public String nume, prenume, email, parola;
	public int id_utilizator, telefon, an_nastere;
	
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

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public int getAn_nastere() {
		return an_nastere;
	}

	public void setAn_nastere(int an_nastere) {
		this.an_nastere = an_nastere;
	}
	
}
