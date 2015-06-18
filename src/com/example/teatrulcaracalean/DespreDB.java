package com.example.teatrulcaracalean;

public class DespreDB {
	
	public final static String DATABASE_NAME = "teatru.db";
	//------------------------ACTORI --------------------------//
	public static final String ACTORI = "actori";
	public static final String ID_ACTOR = "id";
	public static final String NUME_ACTOR = "nume"; 
	public static final String BIOGRAFIE_ACTOR = "biografie";
	
	public static final String CREATE_ACTORI = "Create table if not exists " + ACTORI + " ( "  +
	ID_ACTOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUME_ACTOR + " VARCHAR(40) NOT NULL, " + BIOGRAFIE_ACTOR + 
	" VARCHAR(350) NOT NULL" + " ) ";

	public static final String SELECT_ACTORI = "Select * from " + ACTORI;
	
	public static final String STERGERE_ACTORI = "Drop table if exists" + ACTORI;
	
	public static final String INSERARE_ACTORI = "Insert into " + ACTORI + "(" + ID_ACTOR + ", " + NUME_ACTOR + ", " 
			+ BIOGRAFIE_ACTOR + ")" + "values (" + "1, 'Mihai Bendeac', 'Acesta este un test'" + ")";
	
	//-----------------------REGIZORI----------------------//
	public static final String REGIZORI = "regizori";
	public static final String ID_REGIZOR = "id";
	public static final String NUME_REGIZOR = "nume"; 
	public static final String BIOGRAFIE_REGIZOR = "biografie";
	
	public static final String CREATE_REGIZORI = "Create table if not exists " + REGIZORI + "("  +
	ID_REGIZOR + "INTEGER PRIMARY KEY AUTOINCREMENT, " + NUME_REGIZOR + "VARCHAR(40) NOT NULL, " + BIOGRAFIE_REGIZOR + 
	"VARCHAR(350) NOT NULL" + ")";
	
	public static final String SELECT_REGIZORI = "Select * from " + REGIZORI;
	
	public static final String STERGERE_REGIZORI = "Drop table if exists" + REGIZORI;
	
	public static final String INSERARE_REGIZORI = "Insert into " + REGIZORI + "(" + ID_REGIZOR + ", " + NUME_REGIZOR + ", " 
			+ BIOGRAFIE_REGIZOR + ")" + "values (" + "1, 'Orice Regizor', 'Acesta este un test'" + ")";
	
	//----------------------SCENOGRAFI--------------------//
	public static final String SCENOGRAFI = "scenografi";
	public static final String ID_SCENOGRAFI = "id";
	public static final String NUME_SCENOGRAFI = "nume"; 
	public static final String BIOGRAFIE_SCENOGRAFI = "biografie";
	
	public static final String CREATE_SCENOGRAFI = "Create table if not exists " + SCENOGRAFI + "("  +
	ID_SCENOGRAFI + "INTEGER PRIMARY KEY AUTOINCREMENT, " + NUME_SCENOGRAFI + "VARCHAR(40) NOT NULL, " + BIOGRAFIE_SCENOGRAFI + 
	"VARCHAR(350) NOT NULL" + ")";
	
	public static final String SELECT_SCENOGRAFI = "Select * from " + SCENOGRAFI;
	
	public static final String STERGERE_SCENOGRAFI = "Drop table if exists" + SCENOGRAFI;
	
	public static final String INSERARE_SCENOGRAFI = "Insert into " + SCENOGRAFI + "(" + ID_SCENOGRAFI + ", " + NUME_SCENOGRAFI + ", " 
			+ BIOGRAFIE_SCENOGRAFI + ")" + "values (" + "1, 'Orice scenograf', 'Acesta este un test'" + ")";
	
	
}
