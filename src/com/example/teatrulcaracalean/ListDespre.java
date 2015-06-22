package com.example.teatrulcaracalean;

import com.example.teatrulcaracalean.R;
import com.example.teatrulcaracalean.R.id;
import com.example.teatrulcaracalean.R.layout;

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
			descriere = extras.getString("descriereLabel");
			tvDescriere.setText(descriere);
		}
	}

}
