package com.example.teatrulcaracalean;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperBD extends SQLiteOpenHelper {

	public HelperBD(Context context){
		super(context,DespreDB.DATABASE_NAME, null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DespreDB.CREATE_ACTORI);
		db.execSQL(DespreDB.CREATE_REGIZORI);
		db.execSQL(DespreDB.CREATE_SCENOGRAFI);
		inserareDate(db);
	}
	
	private void inserareDate(SQLiteDatabase db){
		db.execSQL(DespreDB.INSERARE_ACTORI);
		db.execSQL(DespreDB.INSERARE_REGIZORI);
		db.execSQL(DespreDB.INSERARE_SCENOGRAFI);
	}
	
	public boolean inserareActor(Actor actor){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valori = new ContentValues();
		valori.put(DespreDB.NUME_ACTOR, actor.getNume());
		valori.put(DespreDB.BIOGRAFIE_ACTOR, actor.getBiografie());
		db.insert(DespreDB.ACTORI, null, valori);
		return true;
	}
	
	public boolean inserareRegizor(Regizor regizor){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valori = new ContentValues();
		valori.put(DespreDB.NUME_REGIZOR, regizor.getNume());
		valori.put(DespreDB.BIOGRAFIE_REGIZOR, regizor.getBiografie());
		db.insert(DespreDB.REGIZORI, null, valori);
		return true;
	}
	
	public boolean inserareScenograf(Scenograf scenograf){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valori = new ContentValues();
		valori.put(DespreDB.NUME_SCENOGRAFI, scenograf.getNume());
		valori.put(DespreDB.BIOGRAFIE_SCENOGRAFI, scenograf.getBiografie());
		db.insert(DespreDB.SCENOGRAFI, null, valori);
		return true;
	}

	public Actor getActoriByID(int id){
		List<Actor> actori = new ArrayList<Actor>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = DespreDB.SELECT_ACTORI + "WHERE " + DespreDB.ID_ACTOR + " = " + id;
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Actor actor = new Actor();
			actor.setId(cursor.getInt(cursor.getColumnIndex(DespreDB.ID_ACTOR)));
			actor.setNume(cursor.getString(cursor.getColumnIndex(DespreDB.NUME_ACTOR)));
			actor.setBiografie(cursor.getString(cursor.getColumnIndex(DespreDB.BIOGRAFIE_ACTOR)));
			
			actori.add(actor);
			cursor.moveToNext();
		}
		return null;
 	}
	
//	public List<Actor> getActori(){
//		List<Actor> actori = new ArrayList<Actor>();
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(DespreDB.SELECT_ACTORI, null);
//		cursor.moveToFirst();
//		while(!cursor.isAfterLast()){
//			Actor actor = new Actor();
//			actor.setId(cursor.getInt(cursor.getColumnIndex(DespreDB.ID_ACTOR)));
//			actor.setNume(cursor.getString(cursor.getColumnIndex(DespreDB.NUME_ACTOR)));
//			actor.setBiografie(cursor.getString(cursor.getColumnIndex(DespreDB.BIOGRAFIE_ACTOR)));
//			
//			actori.add(actor);
//			cursor.moveToNext();
//		}
//		return actori;
// 	}
	
	public List<String> getActori(){
		List<String> actori = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(DespreDB.SELECT_ACTORI, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			String actor = "";
			actor += Integer.parseInt(cursor.getString(cursor.getColumnIndex(DespreDB.ID_ACTOR)));
			actor += " "  + cursor.getString(cursor.getColumnIndex(DespreDB.NUME_ACTOR));
			actor += " " + cursor.getString(cursor.getColumnIndex(DespreDB.BIOGRAFIE_ACTOR));
			actori.add(actor);
			cursor.moveToNext();
		}
		return actori;
 	}
	
	public Regizor getRegizoriByID(int id){
		List<Regizor> regizori = new ArrayList<Regizor>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = DespreDB.SELECT_REGIZORI + "WHERE " + DespreDB.ID_REGIZOR + " = " + id;
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Regizor regizor = new Regizor();
			regizor.setId(cursor.getInt(cursor.getColumnIndex(DespreDB.ID_REGIZOR)));
			regizor.setNume(cursor.getString(cursor.getColumnIndex(DespreDB.NUME_REGIZOR)));
			regizor.setBiografie(cursor.getString(cursor.getColumnIndex(DespreDB.BIOGRAFIE_REGIZOR)));
			
			regizori.add(regizor);
			cursor.moveToNext();
		}
		return null;
 	}
	
	public List<Regizor> getRegizori(){
		List<Regizor> regizori = new ArrayList<Regizor>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(DespreDB.SELECT_REGIZORI, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Regizor regizor = new Regizor();
			regizor.setId(cursor.getInt(cursor.getColumnIndex(DespreDB.ID_REGIZOR)));
			regizor.setNume(cursor.getString(cursor.getColumnIndex(DespreDB.NUME_REGIZOR)));
			regizor.setBiografie(cursor.getString(cursor.getColumnIndex(DespreDB.BIOGRAFIE_REGIZOR)));
			
			regizori.add(regizor);
			cursor.moveToNext();
		}
		return regizori;
 	}
	
	public Scenograf getScenografiById(int id){
		List<Scenograf> scenografi = new ArrayList<Scenograf>();
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = DespreDB.SELECT_SCENOGRAFI + "WHERE " + DespreDB.ID_SCENOGRAFI + " = " + id;
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Scenograf scenograf = new Scenograf();
			scenograf.setId(cursor.getInt(cursor.getColumnIndex(DespreDB.ID_SCENOGRAFI)));
			scenograf.setNume(cursor.getString(cursor.getColumnIndex(DespreDB.NUME_SCENOGRAFI)));
			scenograf.setBiografie(cursor.getString(cursor.getColumnIndex(DespreDB.BIOGRAFIE_SCENOGRAFI)));
			
			scenografi.add(scenograf);
			cursor.moveToNext();
		}
		return null;
 	}
	
	public List<Scenograf> getScenografi(){
		List<Scenograf> scenografi = new ArrayList<Scenograf>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(DespreDB.SELECT_SCENOGRAFI, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Scenograf scenograf = new Scenograf();
			scenograf.setId(cursor.getInt(cursor.getColumnIndex(DespreDB.ID_SCENOGRAFI)));
			scenograf.setNume(cursor.getString(cursor.getColumnIndex(DespreDB.NUME_SCENOGRAFI)));
			scenograf.setBiografie(cursor.getString(cursor.getColumnIndex(DespreDB.BIOGRAFIE_SCENOGRAFI)));
			
			scenografi.add(scenograf);
			cursor.moveToNext();
		}
		return scenografi;
 	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DespreDB.STERGERE_ACTORI);
		db.execSQL(DespreDB.STERGERE_REGIZORI);
		db.execSQL(DespreDB.STERGERE_SCENOGRAFI);
		onCreate(db);
	}
	
	public void inchide(){
		SQLiteDatabase db = this.getReadableDatabase();
		if(db!=null && db.isOpen()){
			db.close();
		}
	}

}
