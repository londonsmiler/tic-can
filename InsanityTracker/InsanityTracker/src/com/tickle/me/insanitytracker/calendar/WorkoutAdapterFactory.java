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

	private static volatile WorkoutDayAdapter INSTANCE;
	private static SavedDataRepository REPOSITORY_INSTANCE;

	private WorkoutAdapterFactory() {

	}

	public static SavedDataRepository getDBRepository(Context context) {
		createInstancesIfRequired(context);

		return REPOSITORY_INSTANCE;
	}

	public static WorkoutDayAdapter getDBWorkoutAdapter(Context context) {

		createInstancesIfRequired(context);

		return INSTANCE;

	}

	private static void createInstancesIfRequired(Context context) {
		if (INSTANCE == null) {
			synchronized (WorkoutAdapterFactory.class) {
				if (INSTANCE == null) {
					InputStream inputStream = createInputStreamToReadFile(context);
					DBLoader loader = new FileDBLoader(inputStream);
					REPOSITORY_INSTANCE = new DBSavedDataRepository(context,
							loader);
					INSTANCE = new WorkoutDayAdapter(REPOSITORY_INSTANCE, context);

				}

			}
		}
	}

	private static InputStream createInputStreamToReadFile(Context context) {

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
