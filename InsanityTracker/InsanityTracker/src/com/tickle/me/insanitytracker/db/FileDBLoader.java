package com.tickle.me.insanitytracker.db;

import java.io.IOException;
import java.io.InputStream;

import android.database.sqlite.SQLiteDatabase;

public class FileDBLoader implements DBLoader {

	private InputStream inputStream;

	public FileDBLoader(InputStream inputStream) {
		this.inputStream = inputStream;
		
	}

	@Override
	public void loadDatabase(SQLiteDatabase database) {
		try {
			String[] parseSqlFile = FileHelper.parseSqlFile(inputStream);
			
			for (String string : parseSqlFile) {
				database.execSQL(string);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
