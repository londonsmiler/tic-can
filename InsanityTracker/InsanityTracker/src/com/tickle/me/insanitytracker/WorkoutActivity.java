package com.tickle.me.insanitytracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.tickle.me.insanitytracker.calendar.WorkoutAdapter;
import com.tickle.me.insanitytracker.calendar.WorkoutAdapterFactory;

public class WorkoutActivity extends Activity {

	protected static final String DAY_PARAMETER = "day to display";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);

		ListView listView = (ListView) findViewById(R.id.dailyWorkoutList);

		int day = 1;
		listView.setAdapter(new WorkoutAdapter(WorkoutAdapterFactory
				.getDBRepository(this), this, day));

		Toast.makeText(this, "got day " + day, Toast.LENGTH_LONG).show();

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
