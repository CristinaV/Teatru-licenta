package com.example.teatrulcaracalean;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseController extends SQLiteOpenHelper {
	public final static String DATABASE_NAME = "teatru.db";

	public DatabaseController(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	private static final String TABELA_UTILIZATORI = "utilizatori";
	private static final String TABELA_ACTORI = "actori";
	private static final String TABELA_SCENOGRAFI = "scenografi";
	private static final String TABELA_REGIZORI = "regizori";
	private static final String TABELA_PIESE = "piese";
	private static final String TABELA_SALA = "sala";
	private static final String TABELA_LOCURI_OCUPATE ="locuri_ocupate";
	private static final String TABELA_REZERVARE = "rezervare";
	
	//-----------------------Coloane Utilizator----------------
	private static final String ID_UTILIZATOR = "id_utilizator";
	private static final String EMAIL = "email";
	private static final String NUME_UTILIZATOR = "nume_utilizator";
	private static final String PRENUME_UTILIZATOR ="prenume_utilizator";
	private static final String TELEFON = "telefon";
	private static final String AN_NASTERE = "an_nastere";
	private static final String PAROLA = "parola";
	
	// ------------------------Coloane Actori------------------
	private static final String ID = "id";
	private static final String NUME = "nume";
	private static final String BIOGRAFIE = "biografie";

	// --------------------Coloane Scenografi-----------------
	private static final String ID_SCENOGRAF = "id";
	private static final String NUME_SCENOGRAF = "nume";
	private static final String BIOGRAFIE_SCENOGRAF = "biografie";

	// ---------------------Coloane Regizori------------------
	private static final String ID_REGIZOR = "id";
	private static final String NUME_REGIZOR = "nume";
	private static final String BIOGRAFIE_REGIZOR = "biografie";

	// --------------------Coloane Piese---------------------
	private static final String ID_PIESA = "id_piesa";
	private static final String NUME_PIESA = "nume_piesa";
	private static final String DATA = "data";
	private static final String DURATA = "durata";
	private static final String PRET = "pret";
	private static final String DETALII = "detalii";
	
	//-----------------Coloane Sala-------------
	private static final String ID_SALA = "id";
	private static final String NR_LOC="nr_loc";
	private static final String NR_RAND="nr_rand";
	
	//--------------Coloane Rezervare---------------
	private static final String ID_REZERVARE = "id_rezervare";
	
	//--------------Coloane Locuri_ocupate---------------
	private static final String ID_LOC_OCUPAT = "id_loc";
		
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		String CREATE_UTILIZATORI = "CREATE TABLE " + TABELA_UTILIZATORI + "(" + ID_UTILIZATOR + " INTEGER PRIMARY KEY, " + 
	NUME_UTILIZATOR + " TEXT, " + PRENUME_UTILIZATOR + " TEXT, " + EMAIL + " TEXT, " + TELEFON + " TEXT, " + AN_NASTERE + 
	" TEXT, " + PAROLA + " TEXT" + ")";
		database.execSQL(CREATE_UTILIZATORI);
		
		String CREATE_ACTORI = "CREATE TABLE " + TABELA_ACTORI + "(" + ID
				+ " INTEGER PRIMARY KEY, " + NUME + " TEXT," + BIOGRAFIE
				+ " TEXT" + ")";
		database.execSQL(CREATE_ACTORI);
		
		String CREATE_SCENOGRAFI = "CREATE TABLE " + TABELA_SCENOGRAFI + "("
				+ ID_SCENOGRAF + " INTEGER PRIMARY KEY, " + NUME_SCENOGRAF
				+ " TEXT," + BIOGRAFIE_SCENOGRAF + " TEXT" + ")";
		database.execSQL(CREATE_SCENOGRAFI);
		
		String CREATE_REGIZORI = "CREATE TABLE " + TABELA_REGIZORI + "("
				+ ID_REGIZOR + " INTEGER PRIMARY KEY, " + NUME_REGIZOR
				+ " TEXT," + BIOGRAFIE_REGIZOR + " TEXT" + ")";
		database.execSQL(CREATE_REGIZORI);
		
		String CREATE_PIESA = "CREATE TABLE " + TABELA_PIESE + "(" + 
		ID_PIESA + " INTEGER PRIMARY KEY, " + NUME_PIESA + " TEXT, " + DATA + " TEXT, " + DURATA + " TEXT, " 
				+ PRET + " TEXT," + DETALII + " TEXT " + ")";
		database.execSQL(CREATE_PIESA);
		
		String CREATE_SALA = "CREATE TABLE" + TABELA_SALA + "(" + 
		ID_SALA + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NR_LOC +" INTEGER, " + 
				NR_RAND + " INTEGER, " + ")";
		database.execSQL(CREATE_SALA);
		
		String CREATE_LOCURI_OCUPATE = " CREATE TABLE " + TABELA_LOCURI_OCUPATE + "(" + ID_LOC_OCUPAT + " INTEGER PRIMARY KEY, " + 
				 "FOREIGN KEY " +  "(" + ID_PIESA + ")" + " REFERENCES " + TABELA_PIESE + 
				 "(ID_PIESA), " + "FOREIGN KEY (ID_REZERVARE) REFERENCES " +
			TABELA_REZERVARE + "(ID_REZERVARE) " + "FOREIGN KEY (ID_SALA) REFERENCES " +
			TABELA_SALA + "(ID_SALA) " +  ")";
		database.execSQL(CREATE_LOCURI_OCUPATE);
		
		String CREATE_REZERVARE = "CREATE TABLE " + TABELA_REZERVARE + "(" + ID_REZERVARE + " INTEGER PRIMARY KEY, " + 
				 "FOREIGN KEY " +  "(" + ID_PIESA + ")" + " REFERENCES " + TABELA_PIESE + 
				 "(ID_PIESA), " + "FOREIGN KEY (ID_UTILIZATOR) REFERENCES " +
			TABELA_UTILIZATORI + "(ID_UTILIZATOR) " + ")";
		database.execSQL(CREATE_REZERVARE);
		 
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		String queryActori;
		queryActori = "DROP TABLE IF EXISTS " + TABELA_ACTORI;
		database.execSQL(queryActori);
		String queryScenografi;
		queryScenografi = "DROP TABLE IF EXISTS " + TABELA_SCENOGRAFI;
		database.execSQL(queryScenografi);
		String queryRegizori;
		queryRegizori = "DROP TABLE IF EXISTS " + TABELA_REGIZORI;
		database.execSQL(queryRegizori);
		String queryPiese;
		queryPiese = "DROP TABLE IF EXISTS " + TABELA_PIESE;
		database.execSQL(queryPiese);
		onCreate(database);
		
	}

	public void clear() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TABELA_ACTORI);
		db.execSQL("DROP TABLE IF EXISTS " + TABELA_REGIZORI);
		db.execSQL("DROP TABLE IF EXISTS " + TABELA_SCENOGRAFI);
		db.execSQL("DROP TABLE IF EXISTS " + TABELA_PIESE);
		onCreate(db);
	}

	// ----------------Interogari pentru Actori---------------
	public void inserareActor(Actor actor) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valori = new ContentValues();
		valori.put(ID, actor.getId());
		valori.put(NUME, actor.getNume());
		valori.put(BIOGRAFIE, actor.getBiografie());
		db.insert(TABELA_ACTORI, null, valori);
		db.close();
	}

	public ArrayList<Actor> getActori() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM " + TABELA_ACTORI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Actor> actori = new ArrayList<Actor>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Actor actor = new Actor(
						cursor.getInt(cursor.getColumnIndex(ID)),
						cursor.getString(cursor.getColumnIndex(NUME)),
						cursor.getString(cursor.getColumnIndex(BIOGRAFIE)));
				actori.add(actor);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return actori;
	}

	public ArrayList<Actor> getNumeActor() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT " + NUME + " FROM " + TABELA_ACTORI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Actor> actori = new ArrayList<Actor>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Actor actor = new Actor(cursor.getString(cursor
						.getColumnIndex(NUME)));
				actori.add(actor);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return actori;
	}

	public ArrayList<Actor> getBiografieActor() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT " + BIOGRAFIE + " FROM " + TABELA_ACTORI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Actor> actori = new ArrayList<Actor>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Actor actor = new Actor(cursor.getString(cursor
						.getColumnIndex(BIOGRAFIE)));
				actori.add(actor);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return actori;
	}

	// -----------------Interogari pentru Regizori-------------
	public void inserareRegizor(Regizor regizor) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valori = new ContentValues();
		valori.put(ID_REGIZOR, regizor.getId());
		valori.put(NUME_REGIZOR, regizor.getNume());
		valori.put(BIOGRAFIE_REGIZOR, regizor.getBiografie());
		db.insert(TABELA_REGIZORI, null, valori);
		db.close();
	}

	public ArrayList<Regizor> getRegizori() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM " + TABELA_REGIZORI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Regizor> regizori = new ArrayList<Regizor>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Regizor regizor = new Regizor(cursor.getInt(cursor
						.getColumnIndex(ID_REGIZOR)), cursor.getString(cursor
						.getColumnIndex(NUME_REGIZOR)), cursor.getString(cursor
						.getColumnIndex(BIOGRAFIE_REGIZOR)));
				regizori.add(regizor);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return regizori;
	}

	public ArrayList<Regizor> getNumeRegizor() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT " + NUME_REGIZOR + " FROM " + TABELA_REGIZORI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Regizor> regizori = new ArrayList<Regizor>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Regizor regizor = new Regizor(cursor.getString(cursor
						.getColumnIndex(NUME_REGIZOR)));
				regizori.add(regizor);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return regizori;
	}

	public ArrayList<Regizor> getBiografieRegizor() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT " + BIOGRAFIE_REGIZOR + " FROM " + TABELA_REGIZORI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Regizor> regizori = new ArrayList<Regizor>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Regizor regizor = new Regizor(cursor.getString(cursor
						.getColumnIndex(BIOGRAFIE_REGIZOR)));
				regizori.add(regizor);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return regizori;
	}
	
	
	
	//-------------------------Interogari Scenografi------------------
	
	public void inserareScenograf(Scenograf scenograf) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valori = new ContentValues();
		valori.put(ID_SCENOGRAF, scenograf.getId());
		valori.put(NUME_SCENOGRAF, scenograf.getNume());
		valori.put(BIOGRAFIE_SCENOGRAF, scenograf.getBiografie());
		db.insert(TABELA_SCENOGRAFI, null, valori);
		db.close();
	}

	public ArrayList<Scenograf> getScenograf() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM " + TABELA_SCENOGRAFI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Scenograf> scenografi = new ArrayList<Scenograf>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Scenograf scenograf = new Scenograf(cursor.getInt(cursor
						.getColumnIndex(ID_SCENOGRAF)), cursor.getString(cursor
						.getColumnIndex(NUME_SCENOGRAF)),
						cursor.getString(cursor
								.getColumnIndex(BIOGRAFIE_SCENOGRAF)));
				scenografi.add(scenograf);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return scenografi;
	}

	public ArrayList<Scenograf> getNumeScenograf() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT " + NUME_SCENOGRAF + " FROM " + TABELA_SCENOGRAFI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Scenograf> scenografi = new ArrayList<Scenograf>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Scenograf scenograf = new Scenograf(cursor.getString(cursor
						.getColumnIndex(NUME_SCENOGRAF)));
				scenografi.add(scenograf);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return scenografi;
	}

	public ArrayList<Scenograf> getBiografieScenograf() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT " + BIOGRAFIE_SCENOGRAF + " FROM "
				+ TABELA_SCENOGRAFI;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Scenograf> scenografi = new ArrayList<Scenograf>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Scenograf scenograf = new Scenograf(cursor.getString(cursor
						.getColumnIndex(BIOGRAFIE_SCENOGRAF)));
				scenografi.add(scenograf);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return scenografi;
	}

	// ---------------------Interogari Piesa--------------
	
	public void inserarePiesa(Piesa piesa) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valori = new ContentValues();
		valori.put(ID_PIESA, piesa.getIdPiesa());
		valori.put(NUME_PIESA, piesa.getNume());
		valori.put(DATA, piesa.getData());
		valori.put(DURATA,piesa.getDurata());
		valori.put(PRET, piesa.getPret());
		valori.put(DETALII,piesa.getDetalii());
		db.insert(TABELA_PIESE, null, valori);
		db.close();
	}

	public ArrayList<Piesa> getPiesa() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT * FROM " + TABELA_PIESE;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Piesa> piese = new ArrayList<Piesa>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Piesa piesa= new Piesa(cursor.getInt(cursor.getColumnIndex(ID_PIESA)), 
						cursor.getString(cursor.getColumnIndex(NUME_PIESA)),
						cursor.getString(cursor.getColumnIndex(DATA)), 
						cursor.getString(cursor.getColumnIndex(DURATA)),
						cursor.getString(cursor.getColumnIndex(PRET)),
						cursor.getString(cursor.getColumnIndex(DETALII)));
				piese.add(piesa);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return piese;
	}

	public ArrayList<Piesa> getNumeSiDataPiesa() {
		SQLiteDatabase db = this.getReadableDatabase();
		String sql = "SELECT " + NUME_PIESA + " ," + DATA + " FROM " + TABELA_PIESE;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Piesa> piese = new ArrayList<Piesa>();

		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				Piesa piesa = new Piesa(cursor.getString(cursor.getColumnIndex(NUME_PIESA)), 
						cursor.getString(cursor.getColumnIndex(DATA)));
				piese.add(piesa);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		return piese;
	}
	
	//------------------------Interogari Tabela Sala-------------
//	public ArrayList<Piesa> getPiesa() {
//		SQLiteDatabase db = this.getReadableDatabase();
//		String sql = "SELECT * FROM " + TABELA_PIESE;
//		Cursor cursor = db.rawQuery(sql, null);
//		ArrayList<Piesa> piese = new ArrayList<Piesa>();
//
//		if (cursor.moveToFirst()) {
//			while (cursor.isAfterLast() == false) {
//				Piesa piesa= new Piesa(cursor.getInt(cursor.getColumnIndex(ID_PIESA)), 
//						cursor.getString(cursor.getColumnIndex(NUME_PIESA)),
//						cursor.getString(cursor.getColumnIndex(DATA)), 
//						cursor.getString(cursor.getColumnIndex(DURATA)),
//						cursor.getString(cursor.getColumnIndex(PRET)),
//						cursor.getString(cursor.getColumnIndex(DETALII)));
//				piese.add(piesa);
//				cursor.moveToNext();
//			}
//		}
//		cursor.close();
//		db.close();
//
//		return piese;
//	}

}
