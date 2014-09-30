package com.tickle.me.insanitytracker.db.repository;

import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.tickle.me.insanitytracker.db.repository.DBLoader;
import com.tickle.me.insanitytracker.db.repository.FileDBLoader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

@RunWith(RobolectricTestRunner.class)
public class FileDBLoaderTest {

	@Test
	public void shouldExecuteScriptAgainstDatabase() throws Exception {

		SQLiteDatabase database = SQLiteDatabase.create(null);

		InputStream inputStream = openFile("createbob.sql");

		DBLoader loader = new FileDBLoader(inputStream);

		loader.loadDatabase(database);

		Cursor query = database
				.query("bob", null, null, null, null, null, null);

		assertThat(query.getCount(), CoreMatchers.equalTo(1));

	}

	private InputStream openFile(String string) throws FileNotFoundException {
		FileInputStream fileStream = new FileInputStream("resources/" + string);
		return fileStream;

	}

}
