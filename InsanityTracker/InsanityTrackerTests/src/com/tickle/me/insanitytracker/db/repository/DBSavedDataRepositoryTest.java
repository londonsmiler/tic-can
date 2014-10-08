package com.tickle.me.insanitytracker.db.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tickle.me.insanitytracker.db.model.Workout;
import com.tickle.me.insanitytracker.db.model.Workout.WorkoutColumns;
import com.tickle.me.insanitytracker.db.model.WorkoutDay;
import com.tickle.me.insanitytracker.db.model.WorkoutDay.WorkoutDayColumns;

@RunWith(RobolectricTestRunner.class)
public class DBSavedDataRepositoryTest {

	private static final String TABLE_NAME = WorkoutDayColumns.TABLE_NAME;
	private static final String WORKOUT_TABLENAME = WorkoutColumns.TABLE_NAME;

	private static final Object DAY_TITLE = "day 1 title";
	private static final Object WORKOUT_TITLE = "really hard workout";

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
		assertThat(repository.getDatabaseName(),
				CoreMatchers.equalTo(DBSavedDataRepository.DATABASE_NAME));

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

		Cursor query = readableDatabase.query(TABLE_NAME, null, null, null,
				null, null, null);

		int count = query.getCount();

		assertThat(count, CoreMatchers.equalTo(1));

	}

	@Test
	public void shouldLoadActivityDayFromDatabase() throws Exception {

		List<WorkoutDay> loadedDay = repository.loadAllDays();

		assertThat(loadedDay.size(), CoreMatchers.equalTo(1));
		assertThat(loadedDay.get(0).getTitle(), CoreMatchers.equalTo(DAY_TITLE));

	}

	@Test
	public void shouldLoadDaysActivitiesFromDatabase() throws Exception {
		List<Workout> loadedWorkouts = repository.loadAllWorkouts(1);

		assertThat(loadedWorkouts.size(), CoreMatchers.equalTo(1));
		assertThat(loadedWorkouts.get(0).getTitle(),
				CoreMatchers.equalTo(WORKOUT_TITLE));

	}

	@Test
	public void shouldUpdateDaysActivitiesInDatabase() throws Exception {
		List<Workout> loadedWorkouts = repository.loadAllWorkouts(1);
		assertFalse(loadedWorkouts.get(0).isCompleted());

		loadedWorkouts.get(0).setCompleted(true);
		loadedWorkouts.get(0).setCompletedDateTime(new Date());

		repository.saveWorkout(loadedWorkouts.get(0));

		List<Workout> updatedWorkouts = repository.loadAllWorkouts(1);
		assertThat(updatedWorkouts.get(0).isCompleted(),
				CoreMatchers.equalTo(true));

	}

	@Test
	public void shouldLoadMultipleWorkoutsForOneDay() throws Exception {
		List<Workout> day3Workouts = repository.loadAllWorkouts(3);

		assertThat(day3Workouts.size(), CoreMatchers.equalTo(2));

		int completedCount = 0;

		for (Workout workout : day3Workouts) {
			if (workout.isCompleted()) {
				completedCount++;
			}
		}

		assertThat(completedCount, CoreMatchers.equalTo(1));

	}

	class TestLoader implements DBLoader {

		@Override
		public void loadDatabase(SQLiteDatabase database) {

			database.execSQL("create table " + TABLE_NAME + " ("
					+ WorkoutDayColumns._ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ WorkoutDayColumns.COLUMN_NAME_DAY + " INTEGER,"
					+ WorkoutDayColumns.COLUMN_NAME_TITLE + " TEXT,"
					+ WorkoutDayColumns.COLUMN_NAME_COMPLETED + " INTEGER) ");

			database.execSQL("insert into " + TABLE_NAME + "("
					+ WorkoutDayColumns.COLUMN_NAME_TITLE + ","
					+ WorkoutDayColumns.COLUMN_NAME_DAY + ","
					+ WorkoutDayColumns.COLUMN_NAME_COMPLETED + " ) values (\'"
					+ DAY_TITLE + "\',1,0) ");

			database.execSQL("create table " + WORKOUT_TABLENAME + " ("
					+ WorkoutColumns._ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ WorkoutColumns.COLUMN_NAME_DAY + " INTEGER,"
					+ WorkoutColumns.COLUMN_NAME_TITLE + " TEXT, "
					+ WorkoutColumns.COLUMN_NAME_COMPLETED + " INTEGER,"
					+ WorkoutColumns.COLUMN_NAME_DATETIME_COMPLETED + " TEXT) ");

			database.execSQL("insert into " + WORKOUT_TABLENAME + "("
					+ WorkoutColumns.COLUMN_NAME_TITLE + ", "
					+ WorkoutColumns.COLUMN_NAME_DAY + ", "
					+ WorkoutColumns.COLUMN_NAME_COMPLETED + ") values ('"
					+ WORKOUT_TITLE + "', 1, 0) ");

			insertTwoWorkoutsOnDayThreeOneCompleted(database);

		}

		private void insertTwoWorkoutsOnDayThreeOneCompleted(
				SQLiteDatabase database) {
			database.execSQL("insert into " + WORKOUT_TABLENAME + "("
					+ WorkoutColumns.COLUMN_NAME_TITLE + ", "
					+ WorkoutColumns.COLUMN_NAME_DAY + ", "
					+ WorkoutColumns.COLUMN_NAME_COMPLETED
					+ ") values ('activity 1', 3, 0) ");

			database.execSQL("insert into " + WORKOUT_TABLENAME + "("
					+ WorkoutColumns.COLUMN_NAME_TITLE + ", "
					+ WorkoutColumns.COLUMN_NAME_DAY + ", "
					+ WorkoutColumns.COLUMN_NAME_COMPLETED
					+ ") values ('activity 2', 3, 1) ");

		}
	}

}
