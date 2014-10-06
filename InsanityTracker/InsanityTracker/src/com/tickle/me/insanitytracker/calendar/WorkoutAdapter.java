package com.tickle.me.insanitytracker.calendar;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tickle.me.insanitytracker.db.model.Workout;
import com.tickle.me.insanitytracker.db.repository.SavedDataRepository;

public class WorkoutAdapter extends BaseAdapter {

	public static final String TITLE_TEXTVIEW_TAG = "WorkoutAdapter.Title.TextView";
	public static final String COMPLETED_CHECKBOX_TAG = "WorkoutAdapter.Completed.CheckBox";
	private final SavedDataRepository repository;
	private final int day;
	private final Context context;

	public WorkoutAdapter(SavedDataRepository repository, Context context,
			int day) {
		this.repository = repository;
		this.context = context;
		this.day = day;
	}

	@Override
	public int getCount() {
		return repository.loadAllWorkouts(day).size();
	}

	@Override
	public Object getItem(int arg0) {
		return repository.loadAllWorkouts(day).get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LinearLayout layout = new LinearLayout(context);

		Workout workout = repository.loadAllWorkouts(day).get(arg0);

		TextView titleTextView = new TextView(context);
		titleTextView.setText(Html.fromHtml("<H2>" + workout.getTitle()
				+ "</H2"));
		titleTextView.setTag(TITLE_TEXTVIEW_TAG);
		layout.addView(titleTextView);

		CheckBox completedCheckBox = new CheckBox(context);
		completedCheckBox.setChecked(workout.isCompleted());
		completedCheckBox.setTag(COMPLETED_CHECKBOX_TAG);
		layout.addView(completedCheckBox);

		return layout;

	}
}
