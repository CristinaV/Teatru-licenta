package com.example.teatrulcaracalean;

import com.example.teatrulcaracalean.R;
import com.example.teatrulcaracalean.R.array;
import com.example.teatrulcaracalean.R.id;
import com.example.teatrulcaracalean.R.layout;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Despre extends MainActivity {

	String[] valori = new String[]{"Contact", 
						"Istoricul teatrului"};
	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_despre);
		
		listView = (ListView)findViewById(R.id.lvDespre);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valori);
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String[] descriere = getResources().getStringArray(R.array.descriere);
				final String descriereLabel = descriere[position];
				
				Intent i = new Intent(getApplicationContext(), ListDespre.class);
				i.putExtra("descriereLabel", descriereLabel);
				startActivity(i);
			}
			
		});
		
	}

}