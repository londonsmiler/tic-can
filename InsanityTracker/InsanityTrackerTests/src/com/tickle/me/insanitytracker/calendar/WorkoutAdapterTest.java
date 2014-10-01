package com.tickle.me.insanitytracker.calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.tickle.me.insanitytracker.CalendarActivity;
import com.tickle.me.insanitytracker.db.model.Workout;
import com.tickle.me.insanitytracker.db.model.WorkoutDay;
import com.tickle.me.insanitytracker.db.model.WorkoutDayBuilder;
import com.tickle.me.insanitytracker.db.repository.SavedDataRepository;

@RunWith(RobolectricTestRunner.class)
public class WorkoutAdapterTest {

	Context context;

	@Before
	public void setUp() {
		context = Robolectric.buildActivity(CalendarActivity.class).create()
				.get();

	}

	@Test
	public void shouldCountNumberOfWorkoutDaysInDatabase() throws Exception {
		WorkoutDay day1 = new WorkoutDayBuilder().build();
		WorkoutDay day2 = new WorkoutDayBuilder().dayAndId(2).build();

		List<WorkoutDay> days = new ArrayList<WorkoutDay>();
		days.add(day1);
		days.add(day2);

		SavedDataRepository repository = new TestRepository(days);

		WorkoutAdapter adapter = new WorkoutAdapter(repository, context);

		assertThat(adapter.getCount(), CoreMatchers.equalTo(days.size()));

	}

	@Test
	public void shouldGetItemsAtAppropriatePositionInViewWithNullView()
			throws Exception {

		WorkoutDay day1 = new WorkoutDayBuilder().build();
		WorkoutDay day2 = new WorkoutDayBuilder().dayAndId(2).build();

		List<WorkoutDay> days = new ArrayList<WorkoutDay>();
		days.add(day1);
		days.add(day2);

		SavedDataRepository repository = new TestRepository(days);

		WorkoutAdapter adapter = new WorkoutAdapter(repository, context);

		ViewGroup viewGroup = new GridLayout(context);
		View view = adapter.getView(0, null, viewGroup);

		TextView textView = (TextView) view;

		CharSequence day0Text = "day 1\n\na title";
		assertThat(textView.getText(), CoreMatchers.equalTo(day0Text));

	}

	@Test
	public void shouldReuseViewIfSupplied() throws Exception {

		WorkoutDay day1 = new WorkoutDayBuilder().build();
		WorkoutDay day2 = new WorkoutDayBuilder().dayAndId(2).build();

		List<WorkoutDay> days = new ArrayList<WorkoutDay>();
		days.add(day1);
		days.add(day2);

		SavedDataRepository repository = new TestRepository(days);

		WorkoutAdapter adapter = new WorkoutAdapter(repository, context);

		TextView oldView = new TextView(context);
		oldView.setText("old text");

		ViewGroup viewGroup = new GridLayout(context);
		View view = adapter.getView(0, oldView, viewGroup);

		TextView textView = (TextView) view;

		CharSequence day0Text = "day 1\n\na title";
		assertThat(textView.getText(), CoreMatchers.equalTo(day0Text));
		assertTrue(textView == oldView);

	}

	@Test
	public void shouldReturnWorkoutForSpecifiedIndex() throws Exception {
		WorkoutDay day1 = new WorkoutDayBuilder().build();
		WorkoutDay day2 = new WorkoutDayBuilder().dayAndId(2).build();

		List<WorkoutDay> days = new ArrayList<WorkoutDay>();
		days.add(day1);
		days.add(day2);

		SavedDataRepository repository = new TestRepository(days);

		WorkoutAdapter adapter = new WorkoutAdapter(repository, context);

		Object item = adapter.getItem(1);

		assertEquals(day2, item);

	}

	class TestRepository implements SavedDataRepository {

		private final List<WorkoutDay> days;

		public TestRepository(List<WorkoutDay> days) {
			this.days = days;

		}

		@Override
		public void initialise() {

		}

		@Override
		public void updateDay(WorkoutDay day) {

		}

		@Override
		public List<Workout> loadAllWorkouts(int dayToLoad) {
			return null;
		}

		@Override
		public List<WorkoutDay> loadAllDays() {
			return days;
		}
	}

}
