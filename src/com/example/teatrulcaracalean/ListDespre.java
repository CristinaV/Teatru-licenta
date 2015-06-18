package com.example.teatrulcaracalean;

import android.os.Bundle;
import android.widget.TextView;

public class ListDespre extends MainActivity{

	String descriere;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_despre);
		
		TextView tvDescriere = (TextView)findViewById(R.id.tvDescriere);
		
		Bundle extras = getIntent().getExtras();
		
		if(extras!=null){
			descriere = extras.getString("descrierelabel");
			tvDescriere.setText(descriere);
		}
	}

}
