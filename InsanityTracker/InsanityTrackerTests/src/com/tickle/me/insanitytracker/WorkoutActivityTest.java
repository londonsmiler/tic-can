package com.tickle.me.insanitytracker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class WorkoutActivityTest {

	WorkoutActivity activity;

	@Before
	public void setUp() {

		activity = Robolectric.buildActivity(WorkoutActivity.class).create()
				.get();
	}

	@Test
	public void shouldUpdateDatabaseStateToCompleted() throws Exception {

	}
}
