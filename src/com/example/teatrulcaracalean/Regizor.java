package com.example.teatrulcaracalean;

import java.io.Serializable;

public class Regizor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id; 
	private String nume;
	private String biografie;
	
	public Regizor() {
	}
	
	public Regizor(String nume){
		this.nume = nume;
	}

	public Regizor(int id, String nume, String biografie) {
		this.id = id;
		this.nume = nume;
		this.biografie = biografie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getBiografie() {
		return biografie;
	}

	public void setBiografie(String biografie) {
		this.biografie = biografie;
	}

	@Override
	public String toString() {
		return  nume;
	}

}
