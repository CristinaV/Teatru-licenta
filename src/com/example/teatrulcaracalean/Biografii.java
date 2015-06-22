package com.example.teatrulcaracalean;

import com.example.teatrulcaracalean.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Biografii extends MainActivity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_biografii);
	}
	
	public void goToActori(View v){
		Intent i = new Intent(this, ListaActori.class);
		startActivity(i);
	}
	
	public void goToScenografi(View v){
		Intent i = new Intent(this, Scenograf.class);
		startActivity(i);
	}
	
	public void goToRegizori(View v){
		Intent i = new Intent(this, Actor.class);
		startActivity(i);
	}
	
	
}
