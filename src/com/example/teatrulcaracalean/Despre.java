package com.example.teatrulcaracalean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class Despre extends MainActivity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expandableListView;
	List<String> listaParinti;
	HashMap<String, List<String>> listaCopii;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_despre);
		
		//luam lista
		expandableListView = (ExpandableListView) findViewById(R.id.expandableLV);
		
		//pregatirea datelor
		prepareListData();
		
		listAdapter = new ExpandableListAdapter(this, listaParinti, listaCopii);
		
		//setare listAdapter
		expandableListView.setAdapter(listAdapter);
		
		//cand dam click pe un subitem
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				if(listaParinti.get(groupPosition).equalsIgnoreCase("contact")){
					listaCopii.values();
				}
				
				if(listaParinti.get(groupPosition).equalsIgnoreCase("istoric")){
					listaCopii.values();
				}
				return false;
			}
			
		});

		
		
	
	}
		private void prepareListData(){
			listaParinti = new ArrayList<String>();
			listaCopii = new HashMap<String, List<String>>();
		
			listaParinti.add("Contact");
			listaParinti.add("Istoric Teatru");
			
			List<String> contact = new ArrayList<String>();
			contact.add("Adresa: Str. Cuza Voda, nr. 10, Caracal, jud. Olt " + "/n" +
					"Telefon 0249.512.067" + "/n" + 
					" Fax: 0249.512.067");
			
			List<String> istoric = new ArrayList<String>();
			istoric.add("Aici voi scrie cateva randuri din istoricul teatrului");
		
			listaCopii.put(listaParinti.get(0), contact);
			listaCopii.put(listaParinti.get(1), istoric);
		
		}
	
}
