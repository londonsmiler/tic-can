package com.tickle.me.insanitytracker.db;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

@RunWith(RobolectricTestRunner.class)
public class SavedDataRepositoryTest {
	
	DBSavedDataRepository repository;
	DBLoader dataLoader;
	
	@Before
	public void setUp() {
		Context context = Robolectric.application;
		dataLoader = new TestLoader();
		
		repository = new DBSavedDataRepository(context, dataLoader);
		
	}
	
	@Test
	public void shouldPassWantedParametersToSuperClass() throws Exception {
		assertThat(repository.getDatabaseName(), CoreMatchers.equalTo(DBSavedDataRepository.DATABASE_NAME));

		
	}
	
	@Test
	public void shouldCreateNewDBIfNotExist() throws Exception {
		
		SQLiteDatabase readableDatabase = repository.getReadableDatabase();	
		assertNotNull(readableDatabase);

	}
	

	@Test
	public void shouldInitiliseDBOnceOnly() throws Exception {
		
		repository.initialise();
		repository.initialise();
		
		SQLiteDatabase readableDatabase = repository.getReadableDatabase();	
		assertNotNull(readableDatabase);
		
		Cursor query = readableDatabase.query("bob", null, null, null, null, null, null);

		int count = query.getCount();
		
		assertThat(count, CoreMatchers.equalTo(1));
		
	}
	
	class TestLoader implements DBLoader {

		@Override
		public void loadDatabase(SQLiteDatabase database) {
			
		
			database.execSQL("create table bob (sue TEXT) ");
			database.execSQL("insert into bob values (\'one\') ");

			
		}
		
		
	}

	
}
