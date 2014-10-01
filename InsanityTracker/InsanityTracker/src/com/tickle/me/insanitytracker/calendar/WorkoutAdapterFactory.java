package com.tickle.me.insanitytracker.calendar;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.widget.Toast;

import com.tickle.me.insanitytracker.db.repository.DBLoader;
import com.tickle.me.insanitytracker.db.repository.DBSavedDataRepository;
import com.tickle.me.insanitytracker.db.repository.FileDBLoader;
import com.tickle.me.insanitytracker.db.repository.SavedDataRepository;

public class WorkoutAdapterFactory {

	public WorkoutAdapter getDBWorkoutAdapter(Context context) {

		InputStream inputStream = createInputStreamToReadFile(context);
		DBLoader loader = new FileDBLoader(inputStream);
		SavedDataRepository repository = new DBSavedDataRepository(context,
				loader);
		return new WorkoutAdapter(repository, context);

	}

	private InputStream createInputStreamToReadFile(Context context) {

		InputStream is = null;
		try {
			is = context.getResources().getAssets()
					.open("0001_create_database.sql");
		} catch (IOException e) {

			e.printStackTrace();

			Toast.makeText(context, "Error opening file to create database",
					Toast.LENGTH_LONG).show();

		}

		return is;
	}
}
