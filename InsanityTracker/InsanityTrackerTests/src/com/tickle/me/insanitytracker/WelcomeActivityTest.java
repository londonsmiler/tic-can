package com.tickle.me.insanitytracker;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Intent;
import android.view.View;

@RunWith(RobolectricTestRunner.class)
public class WelcomeActivityTest {

	WelcomeActivity activity;

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(WelcomeActivity.class).create()
				.get();

	}

	@Test
	public void shouldHaveButtonToOpenCalendar() throws Exception {

		View button = activity.findViewById(R.id.calender_button);

		button.performClick();

		Intent expectedIntent = new Intent(activity, CalendarActivity.class);
		assertThat(Robolectric.shadowOf(activity).getNextStartedActivity(),
				CoreMatchers.equalTo(expectedIntent));

	}

}
