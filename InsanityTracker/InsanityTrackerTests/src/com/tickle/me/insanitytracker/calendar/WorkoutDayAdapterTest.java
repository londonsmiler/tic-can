package com.tickle.me.insanitytracker.calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
import android.text.Html;
import android.text.SpannedString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tickle.me.insanitytracker.CalendarActivity;
import com.tickle.me.insanitytracker.db.model.Workout;
import com.tickle.me.insanitytracker.db.model.WorkoutDay;
import com.tickle.me.insanitytracker.db.model.WorkoutDayBuilder;
import com.tickle.me.insanitytracker.db.repository.SavedDataRepository;

@RunWith(RobolectricTestRunner.class)
public class WorkoutDayAdapterTest {

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

		SavedDataRepository repository = new TestWorkoutDayRepository(days);

		WorkoutDayAdapter adapter = new WorkoutDayAdapter(repository, context);

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

		SavedDataRepository repository = new TestWorkoutDayRepository(days);

		WorkoutDayAdapter adapter = new WorkoutDayAdapter(repository, context);

		ViewGroup viewGroup = new GridLayout(context);
		View view = adapter.getView(0, null, viewGroup);

		LinearLayout day = (LinearLayout) view;

		TextView textView = (TextView) day
				.findViewWithTag(WorkoutDayAdapter.TEXT_VIEW_TAG);

		CharSequence day0Text = new SpannedString(
				Html.fromHtml("<H2>Day 1</H2><P>a title"));
		assertThat(textView.getText().toString(),
				CoreMatchers.equalTo(day0Text.toString()));

	}

	@Test
	public void shouldReuseViewIfSupplied() throws Exception {

		WorkoutDay day1 = new WorkoutDayBuilder().build();
		WorkoutDay day2 = new WorkoutDayBuilder().dayAndId(2).build();

		List<WorkoutDay> days = new ArrayList<WorkoutDay>();
		days.add(day1);
		days.add(day2);

		SavedDataRepository repository = new TestWorkoutDayRepository(days);

		WorkoutDayAdapter adapter = new WorkoutDayAdapter(repository, context);

		TextView oldTextView = new TextView(context);
		oldTextView.setTag(WorkoutDayAdapter.TEXT_VIEW_TAG);

		CheckBox oldCheckBox = new CheckBox(context);
		oldCheckBox.setTag(WorkoutDayAdapter.CHECKBOX_TAG);

		LinearLayout oldLayout = new LinearLayout(context);
		oldLayout.addView(oldTextView);
		oldLayout.addView(oldCheckBox);

		ViewGroup viewGroup = new GridLayout(context);
		View view = adapter.getView(0, oldLayout, viewGroup);

		LinearLayout layout = (LinearLayout) view;

		TextView textView = (TextView) layout
				.findViewWithTag(WorkoutDayAdapter.TEXT_VIEW_TAG);

		CheckBox checkBox = (CheckBox) layout
				.findViewWithTag(WorkoutDayAdapter.CHECKBOX_TAG);

		assertTrue(layout == oldLayout);
		assertTrue(textView == oldTextView);
		assertTrue(checkBox == oldCheckBox);

	}

	@Test
	public void shouldReturnWorkoutForSpecifiedIndex() throws Exception {
		WorkoutDay day1 = new WorkoutDayBuilder().build();
		WorkoutDay day2 = new WorkoutDayBuilder().dayAndId(2).build();

		List<WorkoutDay> days = new ArrayList<WorkoutDay>();
		days.add(day1);
		days.add(day2);

		SavedDataRepository repository = new TestWorkoutDayRepository(days);

		WorkoutDayAdapter adapter = new WorkoutDayAdapter(repository, context);

		Object item = adapter.getItem(1);

		assertEquals(day2, item);

	}

	@Test
	public void shouldSetCheckBoxAndNotBeModifiable() throws Exception {
		WorkoutDay completedDay = new WorkoutDayBuilder().withCompleteChild()
				.build();
		WorkoutDay uncompltedDay = new WorkoutDayBuilder()
				.withIncompleteChild().dayAndId(3).build();

		List<WorkoutDay> days = Arrays.asList(completedDay, uncompltedDay);

		WorkoutDayAdapter adapter = new WorkoutDayAdapter(
				new TestWorkoutDayRepository(days), context);

		CheckBox c0 = getCheckBoxForIndex(adapter, 0);
		assertThat(c0.isChecked(), CoreMatchers.equalTo(true));
		assertThat(c0.isClickable(), CoreMatchers.equalTo(false));

		CheckBox c1 = getCheckBoxForIndex(adapter, 1);
		assertThat(c1.isChecked(), CoreMatchers.equalTo(false));

	}

	private CheckBox getCheckBoxForIndex(WorkoutDayAdapter adapter, int index) {
		ViewGroup viewGroup = new GridLayout(context);

		View view = adapter.getView(index, null, viewGroup);

		CheckBox c = (CheckBox) view
				.findViewWithTag(WorkoutDayAdapter.CHECKBOX_TAG);
		return c;
	}

	class TestWorkoutDayRepository implements SavedDataRepository {

		private final List<WorkoutDay> days;

		public TestWorkoutDayRepository(List<WorkoutDay> days) {
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

		@Override
		public void saveWorkout(Workout workout) {
			// TODO Auto-generated method stub

		}
	}

}
