package com.tickle.me.insanitytracker.db.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tickle.me.insanitytracker.db.model.Workout;
import com.tickle.me.insanitytracker.db.model.Workout.WorkoutColumns;
import com.tickle.me.insanitytracker.db.model.WorkoutDay;
import com.tickle.me.insanitytracker.db.model.WorkoutDay.WorkoutDayColumns;

public class DBSavedDataRepository extends SQLiteOpenHelper implements
		SavedDataRepository {

	public static final String DATABASE_NAME = "DailyTasks";
	public static final int VERSION = 1;
	public static final SQLiteDatabase.CursorFactory DEFAULT_FACTORY = null;
	private static final String TABLE_NAME_DAY = WorkoutDayColumns.TABLE_NAME;
	private final DBLoader loader;

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

	@Override
	public void updateDay(WorkoutDay day) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues updatedColumns = new ContentValues();
		updatedColumns.put(WorkoutDayColumns.COLUMN_NAME_COMPLETED,
				day.isComplete());

		db.update(TABLE_NAME_DAY, updatedColumns, WorkoutDayColumns._ID
				+ " = ?", new String[] { day.getIdAsString() });
	}

	@Override
	public List<WorkoutDay> loadAllDays() {

		List<WorkoutDay> daysList = new ArrayList<WorkoutDay>();

		Cursor query = getReadableDatabase().query(TABLE_NAME_DAY, null, null,
				null, null, null, null);

		if (query.moveToFirst()) {
			do {
				WorkoutDay aDay = new WorkoutDay(query.getInt(0),
						query.getInt(1), query.getString(2),
						parseBoolean(query.getInt(3)));
				daysList.add(aDay);

			} while (query.moveToNext());

		}

		return daysList;

	}

	private boolean parseBoolean(int int1) {
		return (int1 == 0 ? false : true);
	}

	@Override
	public List<Workout> loadAllWorkouts(int dayToLoad) {
		List<Workout> workoutList = new ArrayList<Workout>();

		Cursor query = getReadableDatabase().query(WorkoutColumns.TABLE_NAME,
				null, WorkoutColumns.COLUMN_NAME_DAY + " = ? ",
				new String[] { "" + dayToLoad }, null, null, null, null);

		if (query.moveToFirst()) {
			do {
				Workout aWorkout = new Workout(query.getInt(0),
						query.getString(2), parseBoolean(query.getInt(3)));
				workoutList.add(aWorkout);

			} while (query.moveToNext());
		}

		return workoutList;
	}

	public void saveWorkout(Workout workout) {

		ContentValues valuesToUpdate = new ContentValues();

		valuesToUpdate.put(WorkoutColumns.COLUMN_NAME_COMPLETED,
				workout.isCompleted());

		getWritableDatabase().update(WorkoutColumns.TABLE_NAME, valuesToUpdate,
				WorkoutColumns._ID + " = ?",
				new String[] { "" + workout.getId() });

	}

}
