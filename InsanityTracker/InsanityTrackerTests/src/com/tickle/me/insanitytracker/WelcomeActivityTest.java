package com.tickle.me.insanitytracker;

import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;
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
	public void setUp( ) {
		activity = Robolectric.buildActivity(WelcomeActivity.class).create().get();
		
	}
	
	@Test
	public void shouldHaveTextToMarkComplete() throws Exception {
		
		String s = activity.getString(R.string.done);
		
		assertThat(s, CoreMatchers.equalTo("Done!"));
		
	}

	@Test
	public void shouldHaveButtonToMarkComplete() throws Exception {
		
		View button = activity.findViewById(R.id.imageView1);
		
		button.performClick();
		
		Intent expectedIntent = new Intent(activity, WelcomeActivity.class);
		assertThat(Robolectric.shadowOf(activity).getNextStartedActivity(), CoreMatchers.equalTo(expectedIntent));

		
	}
	
	
	
}
