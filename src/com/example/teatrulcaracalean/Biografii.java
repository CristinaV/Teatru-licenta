package com.example.teatrulcaracalean;

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
		Intent i = new Intent(this, Actori.class);
		startActivity(i);
	}
	
	
}
