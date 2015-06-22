package com.example.teatrulcaracalean;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Conectare extends MainActivity{
	ArrayList<Utilizator> res = new ArrayList<Utilizator>();
	ArrayList<Utilizator> lista=new ArrayList<Utilizator>();
	SQLiteDatabase db;
	File caleDb;
	Cursor c;
	Button b;
	Boolean enable1=false,enable2=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conectare);
		
		File caleDb=this.getDatabasePath(DatabaseController.DATABASE_NAME);
        caleDb=Conectare.this.getDatabasePath(DatabaseController.DATABASE_NAME);
        db=SQLiteDatabase.openOrCreateDatabase(caleDb, null);
		
      //  LoadUtilizatoriAsync loader=new LoadUtilizatoriAsync();
	//	loader.execute();
		
		Intent i=getIntent();
		lista=(ArrayList<Utilizator>)i.getSerializableExtra("utilizatori");
		final EditText et=(EditText)findViewById(R.id.etemail);
		final EditText et1=(EditText)findViewById(R.id.etparola);
		b=(Button)findViewById(R.id.btn_conectare);
		final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		final Pattern pattern=Pattern.compile(emailPattern);
		et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {	
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Matcher matcher=pattern.matcher(s.toString());
				//final String email=et.getText().toString();
				if(s.length()!=0){
                	//b.setEnabled(true);
                	enable1=true;
                }
                else{
                	//b.setEnabled(false);
                	enable1=false;
                }
				
				b.setEnabled(enable1 && enable2);
			}
			
		});
		
		
		et1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
                if(s.length()!=0){
                	//b.setEnabled(true);
                	enable2=true;
                }
                else{
                	//b.setEnabled(false);
                	enable2=false;
                }
               
                b.setEnabled(enable1 && enable2);
			}
		});
		
	//	System.out.println("conectare  "+res.size());
	}
	
	public void conectare(View v){
		EditText et=(EditText)findViewById(R.id.etemail);
		String email=et.getText().toString();
		et=(EditText)findViewById(R.id.etparola);
		String parola=et.getText().toString();
		boolean gasit=false;
		Intent i=new Intent();
		c=db.rawQuery("SELECT * FROM utilizatori", null);
		System.out.println("cursor conectare "+c.getCount());
		if(c!=null){
			if(c.moveToFirst()){
				do{
					String CursorNume=c.getString(c.getColumnIndex("nume"));
					String CursorTelefon=c.getString(c.getColumnIndex("telefon"));
					String CursorAnNastere=c.getString(c.getColumnIndex("anNastere"));
					String CursorEmail=c.getString(c.getColumnIndex("email"));
					String CursorParola=c.getString(c.getColumnIndex("parola"));
					String CursorIdUtil=c.getString(c.getColumnIndex("idUtil"));
					if(email.equals(CursorEmail) && parola.equals(CursorParola)){
						Toast.makeText(Conectare.this, "Te-ai conectat cu succes", Toast.LENGTH_LONG).show();
						
						Utilizator util=new Utilizator(CursorNume, CursorNume.substring(CursorNume.indexOf(" ")), CursorEmail, CursorParola,Integer.parseInt(CursorTelefon), Integer.parseInt(CursorAnNastere));
						i.putExtra("utilizator", util);
						i.putExtra("email", util.email);
						i.putExtra("id", CursorIdUtil);
						setResult(RESULT_OK,i);
						
						gasit=true;
						break;
					}
				}while(c.moveToNext());
				if(!gasit){
					Toast.makeText(Conectare.this, "Creeaza un cont mai intai!", Toast.LENGTH_LONG).show();
					setResult(RESULT_CANCELED,i);
				}
			}
		}
	    db.close();
		finish();
		/*
		if(res!=null){
			for(Utilizator util:res){
				if(util.email.equalsIgnoreCase(email) && util.parola.equalsIgnoreCase(parola) ){
					Toast.makeText(Conectare.this, "Te-ai conectat cu succes", Toast.LENGTH_LONG).show();
					i.putExtra("utilizator", util);
					setResult(RESULT_OK,i);
					gasit=true;
					break;
				}
			}
			if(!gasit){
				Toast.makeText(Conectare.this, "Creeaza un cont mai intai!", Toast.LENGTH_LONG).show();
				setResult(RESULT_CANCELED,i);
			}
		}
		else{
			Toast.makeText(Conectare.this, "Creeaza un cont mai intai!", Toast.LENGTH_LONG).show();
			setResult(RESULT_CANCELED,i);	
		}
		db.close();
		finish();*/
		
		
	}
public class LoadUtilizatoriAsync extends AsyncTask<String, Integer, ArrayList<Utilizator>>{
		
		@Override
		protected ArrayList<Utilizator> doInBackground(String... params) {
			Cursor c=db.rawQuery("SELECT * FROM utilizatori", null);
			try {
				if(c!=null){
					if(c.moveToFirst()){
						boolean gasit=false;
						do {
							String CursorNume=c.getString(c.getColumnIndex("nume"));
							String CursorTelefon=c.getString(c.getColumnIndex("telefon"));
							String CursorAnNastere=c.getString(c.getColumnIndex("anNastere"));
							String CursorEmail=c.getString(c.getColumnIndex("email"));
							String CursorParola=c.getString(c.getColumnIndex("parola"));
							Utilizator util=new Utilizator(CursorNume, CursorNume.substring(CursorNume.indexOf(" ")), CursorEmail, CursorParola,Integer.parseInt(CursorTelefon), Integer.parseInt(CursorAnNastere));
                            res.add(util);
				            
				        }while (c.moveToNext());
					}
				}
				c.close();
				return res;
			} catch (Exception e) {
				e.printStackTrace();
				c.close();
				return null;
			}
			
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
		}

		@Override
		protected void onPostExecute(ArrayList<Utilizator> result) {
			super.onPostExecute(result);
		
		}
}
}
