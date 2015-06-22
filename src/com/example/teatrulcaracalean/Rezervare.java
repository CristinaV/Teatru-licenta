package com.example.teatrulcaracalean;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Rezervare extends MainActivity{

	private DatabaseController dbc;
	ListView lv;
	ArrayList<Piesa> piese;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_piese);
		dbc = new DatabaseController(getApplicationContext());
	
		piese = new ArrayList<Piesa>();
		
		dbc.inserarePiesa(new Piesa(1,"Napoleon era fata","Luni 20/07/2015"," 1h 30 minute", " 15 lei","Aici este sunt detalii despre piesa"));
		dbc.inserarePiesa(new Piesa(2, "Sotul Pacalit","Marti 21/07/2015"," 1h ", " 10 lei","Aici este sunt detalii despre piesa Sotul Pacalit"));
		piese = dbc.getNumeSiDataPiesa();
		String[] spectacol = new String[piese.size()];
		for(int i=0; i<piese.size();i++){
			spectacol[i] = piese.get(i).toString();
		}

		lv = (ListView)findViewById(R.id.lvPiese);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Rezervare.this, android.R.layout.simple_list_item_1,android.R.id.text1, spectacol);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				piese = dbc.getPiesa();

				String[] spect = new String[piese.size()];
				for(int i=0; i<piese.size();i++){
					spect[i] = piese.get(i).toString();
				}
				final String detaliiSpect = spect[position].toString();
				
				Intent i = new Intent(getApplicationContext(), PiesaDetalii.class);
				i.putExtra("detaliiSpect", detaliiSpect);
				startActivity(i);
			}
			
		});
	}

}
