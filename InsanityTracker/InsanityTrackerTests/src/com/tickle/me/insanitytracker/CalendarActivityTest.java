package com.tickle.me.insanitytracker;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.widget.GridView;
import android.widget.ListAdapter;

import com.tickle.me.insanitytracker.calendar.WorkoutDayAdapter;

@RunWith(RobolectricTestRunner.class)
public class CalendarActivityTest {

	CalendarActivity activity;

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(CalendarActivity.class).create()
				.get();
	}

	@Test
	public void shouldUseWorkoutAdapterForGridView() throws Exception {

		GridView calendarGridView = (GridView) activity
				.findViewById(R.id.calendar_grid_view);

		ListAdapter adapter = calendarGridView.getAdapter();

		assertTrue(adapter instanceof WorkoutDayAdapter);

	}
}
