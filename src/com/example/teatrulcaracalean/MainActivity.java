package com.example.teatrulcaracalean;

import java.io.File;
import java.util.ArrayList;




import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private SQLiteDatabase db;
	//log in si sign up
	private ArrayList<Utilizator> utilizatori = new ArrayList<Utilizator>();
	Integer idUtilizator = 0;
	private Menu menu;
	private final int cod_rezultat = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		File caleDb = this.getDatabasePath(DatabaseController.DATABASE_NAME);
		File dirDb = caleDb.getParentFile();
		if (!dirDb.exists()) {
			dirDb.mkdir();
		}
//		db = SQLiteDatabase.openOrCreateDatabase(caleDb, null);
//				
//		db.close();
	}

	@Override
	protected void onDestroy(){ 
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.menu = menu;
		MenuInflater mif = getMenuInflater();
		mif.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.sign){
			Intent i = new Intent(this, CreeazaCont.class);
			startActivityForResult(i, cod_rezultat);
		}
		
		if(id == R.id.log){
			Intent i = new Intent(this, Conectare.class);
			i.putExtra("utilizatori", utilizatori);
			startActivityForResult(i, 1);
		}
		
		if(id == R.id.exit){
			finish();
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if(requestCode==0 && intent!=null){
			if(resultCode == RESULT_OK){
				Bundle extras = intent.getExtras();
				Toast.makeText(this, ((Utilizator)extras.getSerializable("utilizator")).nume, Toast.LENGTH_LONG).show();
				utilizatori.add((Utilizator)extras.getSerializable("utilizator"));
			}
			if(resultCode == RESULT_CANCELED){
				Bundle extras = intent.getExtras();
				String email = extras.getString("email");
				MenuItem item = menu.findItem(R.id.utilizator);
				item.setTitle(email);
				item.setVisible(true);
			}
		}
		if(requestCode==1 && intent!=null){
			if(resultCode == RESULT_OK){
				Bundle extras = intent.getExtras();
				if(extras.containsKey("utilizator")){
					Utilizator u = (Utilizator)extras.getSerializable("utilizator");
					System.out.println(u.email);
					idUtilizator = Integer.parseInt(extras.getString("id"));
					System.out.println(idUtilizator);
					Toast.makeText(this, u.nume, Toast.LENGTH_LONG).show();
					MenuItem item  = menu.findItem(R.id.utilizator);
					item.setTitle(u.email);
					item.setVisible(true);
				}
			}
		}
	}

	public void exitApp(View v) {
		finish();
	}

	public void despre(View v) {
		Intent i = new Intent(this, Despre.class);
		startActivity(i);
	}
	
	public void biografii(View v){
		Intent i = new Intent(this, Biografii.class);
		startActivity(i);
	}
	
	public void rezervare(View v){
		Intent i = new Intent(this, Rezervare.class);
		startActivity(i);
	}

}
