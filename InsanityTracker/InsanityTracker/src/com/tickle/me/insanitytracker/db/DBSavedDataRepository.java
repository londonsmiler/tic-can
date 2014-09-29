package com.tickle.me.insanitytracker.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSavedDataRepository extends SQLiteOpenHelper implements SavedDataRepository {

	public static final String DATABASE_NAME = "DailyTasks";
	public static final int VERSION = 1;
	public static final SQLiteDatabase.CursorFactory DEFAULT_FACTORY = null;
	private DBLoader loader;
	
	public DBSavedDataRepository(Context context, DBLoader loader) {
		super(context, DATABASE_NAME, DEFAULT_FACTORY, VERSION);
		this.loader = loader;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		loader.loadDatabase(database);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialise() {
		getWritableDatabase();
		
	}


}
