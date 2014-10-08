package com.tickle.me.insanitytracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.tickle.me.insanitytracker.calendar.WorkoutAdapter;
import com.tickle.me.insanitytracker.calendar.WorkoutAdapterFactory;
import com.tickle.me.insanitytracker.db.repository.SavedDataRepository;

public class WorkoutActivity extends Activity {

	protected static final String DAY_PARAMETER = "day to display";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);

		ListView listView = (ListView) findViewById(R.id.dailyWorkoutList);

		int day = getIntent().getExtras().getInt(DAY_PARAMETER) + 1;

		// listView.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, android.R.id.text1,
		// new String[] { "one", "two" }));

		final SavedDataRepository dbRepository = WorkoutAdapterFactory
				.getDBRepository(this);

		Toast.makeText(this,
				"Found workouts: " + dbRepository.loadAllWorkouts(1).size(),
				Toast.LENGTH_LONG).show();

		listView.setAdapter(new WorkoutAdapter(dbRepository, this, day));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.workout, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
