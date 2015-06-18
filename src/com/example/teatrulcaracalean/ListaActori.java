package com.example.teatrulcaracalean;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaActori extends MainActivity{
	private List<String> actori;
	private ListView listView;
	private ArrayAdapter<String> adaptor;
	private List<String> listAdaptor;
	private HelperBD db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_actori);
		
		db = new HelperBD(this);
		actori = db.getActori();
		if(actori!=null){
			listAdaptor = new ArrayList<String>();
			adaptor = new ArrayAdapter<String>(ListaActori.this, android.R.layout.simple_list_item_1,listAdaptor);
			for(String actor: actori){
				listAdaptor.add(actor);
				adaptor.notifyDataSetChanged();
			}
			listView = (ListView) findViewById(R.id.lista_actori);
			listView.setAdapter(adaptor);
		}
		
	}
	
	
}
