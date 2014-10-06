package com.tickle.me.insanitytracker.calendar;

import static org.junit.Assert.assertThat;

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

import com.tickle.me.insanitytracker.WorkoutActivity;
import com.tickle.me.insanitytracker.db.model.Workout;
import com.tickle.me.insanitytracker.db.model.WorkoutDay;
import com.tickle.me.insanitytracker.db.repository.SavedDataRepository;

@RunWith(RobolectricTestRunner.class)
public class WorkoutAdaptorTest {

	Context context;

	@Before
	public void setUp() {
		context = Robolectric.buildActivity(WorkoutActivity.class).create()
				.get();
	}

	@Test
	public void shouldcountNumberOfWorkoutsForADay() throws Exception {

		Workout workout1 = new WorkoutBuilder().build();
		Workout workout2 = new WorkoutBuilder().build();

		List<Workout> workouts = Arrays.asList(workout1, workout2);

		SavedDataRepository repository = new TestWorkoutRepository(workouts);

		WorkoutAdapter adaptor = new WorkoutAdapter(repository, context, 0);

		assertThat(adaptor.getCount(), CoreMatchers.equalTo(workouts.size()));

		assertThat((Workout) adaptor.getItem(1), CoreMatchers.equalTo(workout2));

	}

	@Test
	public void shouldRenderView() throws Exception {

		int day = 0;

		String title = "Title for the day";
		Workout workout1 = new WorkoutBuilder().title("one").title(title).id(0)
				.build();
		Workout workout2 = new WorkoutBuilder().title("two").id(1).build();

		List<Workout> workouts = Arrays.asList(workout1, workout2);

		SavedDataRepository repository = new TestWorkoutRepository(workouts);

		WorkoutAdapter adapter = new WorkoutAdapter(repository, context, day);

		ViewGroup viewGroup = new GridLayout(context);
		View view = adapter.getView(0, null, viewGroup);

		LinearLayout workoutView = (LinearLayout) view;

		TextView textView = (TextView) workoutView
				.findViewWithTag(WorkoutAdapter.TITLE_TEXTVIEW_TAG);

		CheckBox checkBox = (CheckBox) workoutView
				.findViewWithTag(WorkoutAdapter.COMPLETED_CHECKBOX_TAG);

		CharSequence day0Text = new SpannedString(Html.fromHtml("<H2>" + title
				+ "</H2>"));
		assertThat(textView.getText().toString(),
				CoreMatchers.equalTo(day0Text.toString()));

		assertThat(checkBox.isChecked(),
				CoreMatchers.equalTo(workouts.get(0).isCompleted()));

	}

	class TestWorkoutRepository implements SavedDataRepository {

		private final List<Workout> workouts;

		public TestWorkoutRepository(List<Workout> workouts) {
			this.workouts = workouts;

		}

		@Override
		public void initialise() {
			// TODO Auto-generated method stub

		}

		@Override
		public void updateDay(WorkoutDay day) {
			// TODO Auto-generated method stub

		}

		@Override
		public List<Workout> loadAllWorkouts(int dayToLoad) {
			return workouts;
		}

		@Override
		public List<WorkoutDay> loadAllDays() {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
