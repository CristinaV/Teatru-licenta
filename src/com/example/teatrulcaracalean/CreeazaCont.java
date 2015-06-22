package com.example.teatrulcaracalean;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class CreeazaCont extends MainActivity{
	ArrayList<Utilizator> res = new ArrayList<Utilizator>();
    private static String text_spinner;  
    SQLiteDatabase db;
    File caleDb;
	private Button b;
	private Boolean enable1=false,enable2=false,enable3=false,enable4=false,enable5=false,enable6=false,enable7=false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		
		caleDb=CreeazaCont.this.getDatabasePath(DatabaseController.DATABASE_NAME);
		
		db=SQLiteDatabase.openOrCreateDatabase(caleDb, null);
		LoadUtilizatoriAsync loader=new LoadUtilizatoriAsync();
		loader.execute();
		
		String[] ani=new String[81];
		for(int i=1917;i<1997;i++){
			ani[i-1917+1]=new Integer(1997-i+1917).toString();
		}
		ani[0]="Alege anul nasterii";
		final Spinner spinner=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ani);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		b=(Button)findViewById(R.id.button1);
		
		final EditText et=(EditText)findViewById(R.id.email);
		final EditText et5=(EditText)findViewById(R.id.prenume);
		final EditText et4=(EditText)findViewById(R.id.nume);
		final EditText et2=(EditText)findViewById(R.id.parola);
		final EditText et3=(EditText)findViewById(R.id.confirma_parola);
		final EditText et6=(EditText)findViewById(R.id.telefon);
		
		final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		
		et6.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {	
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {	
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(s.length()==10){
					et6.setBackgroundColor(getResources().getColor(R.color.green));enable6=true;
				}
				else{
					et6.setBackgroundColor(getResources().getColor(R.color.red));enable6=false;
				}

				b.setEnabled(enable1 && enable2 && enable3 && enable4 && enable5 && enable6 && enable7);
			}
		});
		
		et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				final String email=et.getText().toString();
				if(email.matches(emailPattern)){
					et.setBackgroundColor(getResources().getColor(R.color.green));enable1=true;
				}
				else{
					et.setBackgroundColor(getResources().getColor(R.color.red));enable1=false;
				}

				b.setEnabled(enable1 && enable2 && enable3 && enable4 && enable5 && enable6 && enable7);
			}
		});
		final String PASSWORD_PATTERN = 
	              "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
		final Pattern pattern=Pattern.compile(PASSWORD_PATTERN);
        et2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {		
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				Matcher matcher=pattern.matcher(s);
				if(matcher.matches()){
					et2.setBackgroundColor(getResources().getColor(R.color.green));
					enable2=true;
				}
				else{
					et2.setBackgroundColor(getResources().getColor(R.color.red));
					enable2=false;
				}

				b.setEnabled(enable1 && enable2 && enable3 && enable4 && enable5 && enable6 && enable7);
			}
		});
		et3.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {	
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				final String parola1=et2.getText().toString();
				final String parola2=et3.getText().toString();
				if(parola2.equals(parola1)){
					et3.setBackgroundColor(getResources().getColor(R.color.green));enable3=true;
				}
				else{
					et3.setBackgroundColor(getResources().getColor(R.color.red));enable3=false;
				}
				
				b.setEnabled(enable1 && enable2 && enable3 && enable4 && enable5 && enable6 && enable7);
			}
		});
		
		
		et4.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {	
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				int ok=0;
				final String nume=et4.getText().toString();
				for(int i=0;i<nume.length();i++){
			    	   if(Character.isLetter(nume.charAt(i)) || nume.charAt(i)=='-' || nume.charAt(i)==' '){
			    		   ok=1;
			    	   }
			    	   else{
			    		   ok=0;break;
			    	   }
			       }
				if(ok==1){
					et4.setBackgroundColor(getResources().getColor(R.color.green));enable4=true;
				}
				else{
					et4.setError("Numele poate contine doar litere, - si spatiu");
					et4.setBackgroundColor(getResources().getColor(R.color.red));enable4=false;
				}
			
				b.setEnabled(enable1 && enable2 && enable3 && enable4 && enable5 && enable6 && enable7);
			}
		});
		
		et5.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {	
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				int ok=0;
				final String prenume=et5.getText().toString();
				for(int i=0;i<prenume.length();i++){
			    	   if(Character.isLetter(prenume.charAt(i)) || prenume.charAt(i)=='-' || prenume.charAt(i)==' '){
			    		   ok=1;
			    	   }
			    	   else{
			    		   ok=0;break;
			    	   }
			       }
				if(ok==1){
					et5.setBackgroundColor(getResources().getColor(R.color.green));enable5=true;
				}
				else{
					et5.setError("Prenumele poate contine doar litere, - si spatiu");
					et5.setBackgroundColor(getResources().getColor(R.color.red));enable5=false;
				}
				
			/*	if( s.length()>0 && !et6.getText().toString().equals("") && !et2.getText().toString().equals("") && !et3.getText().toString().equals("") && !et.getText().toString().equals("") && !et4.getText().toString().equals("")){	       
			       if(ok==1)
					//b.setEnabled(true);	
			    	   enable5=true;
			       else
			    	//   b.setEnabled(false);
			    	   enable5=false;
				}
				else{
					//b.setEnabled(false);
					enable5=false;
				}*/
				b.setEnabled(enable1 && enable2 && enable3 && enable4 && enable5 && enable6 && enable7);
			}
		});

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				text_spinner=spinner.getItemAtPosition(arg2).toString();
				if(!text_spinner.equalsIgnoreCase("Alege anul nasterii"))
				     enable7=true;
				b.setEnabled(enable1 && enable2 && enable3 && enable4 && enable5 && enable6 && enable7);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void creeazaCont(View v){
		Intent i=new Intent();
		
		EditText et=(EditText)findViewById(R.id.nume);
		String nume=et.getText().toString();
		et=(EditText)findViewById(R.id.prenume);
		String prenume=et.getText().toString();
		et=(EditText)findViewById(R.id.parola);
		String parola=et.getText().toString();
		et=(EditText)findViewById(R.id.email);
		String email=et.getText().toString();
		Spinner sp=(Spinner)findViewById(R.id.spinner1);
		int an_nastere=Integer.parseInt(sp.getSelectedItem().toString());
		et=(EditText)findViewById(R.id.telefon);
		int telefon=Integer.parseInt(text_spinner);
		
		Utilizator u=new Utilizator(nume, prenume, email, parola, telefon, an_nastere);
		//verific daca exista deja cont
		

		int ok=1;
		if(res!=null){
			for(Utilizator util:res){
				if(util.email.equalsIgnoreCase(u.email)){
					Toast.makeText(this, "exista deja un cont cu acest email", Toast.LENGTH_LONG).show();
					setResult(RESULT_CANCELED,i);	
					ok=0;
					break;
				}
			}
			if(ok==1){
				db.execSQL("INSERT INTO utilizatori(nume,telefon,email,parola,anNastere) VALUES('" + u.nume.concat(" ").concat(u.prenume) + "'," + u.telefon + ",'" + u.email + "','"+u.parola+"',"+u.an_nastere+")");
				i.putExtra("utilizator", u);
		    	setResult(RESULT_OK,i);					
				
			}
		}
		else{
			db.execSQL("INSERT INTO utilizatori(nume,telefon,email,parola,anNastere) VALUES('" + u.nume.concat(" ").concat(u.prenume) + "'," + u.telefon + ",'" + u.email + "','"+u.parola+"',"+u.an_nastere+")");
			i.putExtra("utilizator", u);
	    	setResult(RESULT_OK,i);	
		}
		db.close();
		finish();
	/*	Cursor c=db.rawQuery("SELECT * FROM utilizatori", null);
		if(c!=null){
			if(c.moveToFirst()){
				boolean gasit=false;
				do {
		            String Cursoremail = c.getString(c.getColumnIndex("email"));
		            if(Cursoremail.equals(u.email)){
		            	gasit=true;
		            	break;
		            }
		        }while (c.moveToNext());
				if(!gasit){
					db.execSQL("INSERT INTO utilizatori(nume,telefon,email,parola,anNastere) VALUES('" + u.nume.concat(" ").concat(u.prenume) + "'," + u.telefon + ",'" + u.email + "','"+u.parola+"',"+u.an_nastere+")");
					Toast.makeText(this, "inregistrare adaugata", Toast.LENGTH_LONG).show();
			    	
			    	i.putExtra("utilizator", u);
			    	setResult(RESULT_OK,i);					
					finish();
				}
				else{
					Toast.makeText(this, "exista deja un cont cu acest email", Toast.LENGTH_LONG).show();
				}
			}
		}

		c.close();*/
		
		
	}

	public void dejaMembru(View v){
		Intent i=new Intent(this,Conectare.class);
		startActivityForResult(i, 2); 
	}
	
	 public void onActivityResult(int requestCode, int resultCode, Intent intent){
	    	super.onActivityResult(requestCode, resultCode, intent);
	    	if(requestCode==2 && intent!=null)
	    	{
	    		if(resultCode==RESULT_OK)
	    		{
	    			Bundle extras=intent.getExtras();
	    			Integer id=extras.getInt("id");
	    			String email=extras.getString("email");
	    			Intent i=new Intent();
	    			System.out.println(id+"    "+email);
	    			i.putExtra("email", email);
	    			setResult(RESULT_CANCELED, i);
	    			finish();
	    		}
	    	}
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
