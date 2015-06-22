package com.example.teatrulcaracalean;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListaActori extends MainActivity {

	private DatabaseController dbc;
	ListView lv;
	ArrayList<Actor> actori;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_actori);
		dbc = new DatabaseController(getApplicationContext());
	
		actori = new ArrayList<Actor>();
		
		dbc.inserareActor(new Actor(1,"Mihai Bendeac","Aici este biografia lui Mihai Bendeac"));
		dbc.inserareActor(new Actor(2, "Stefan Banica Jr","Aici este o biografie"));
		actori = dbc.getNumeActor();
		String[] act = new String[actori.size()];
		for(int i=0; i<actori.size();i++){
			act[i] = actori.get(i).toString();
		}

		lv = (ListView)findViewById(R.id.lista_actori);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListaActori.this, android.R.layout.simple_list_item_1,android.R.id.text1, act);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				actori = dbc.getBiografieActor();

				String[] biografie = new String[actori.size()];
				for(int i=0; i<actori.size();i++){
					biografie[i] = actori.get(i).toString();
				}
				final String biografieActor = biografie[position];
				
				Intent i = new Intent(getApplicationContext(), BiografiiListaActori.class);
				i.putExtra("biografieActor", biografieActor);
				startActivity(i);
			}
			
		});
	}

	
}
