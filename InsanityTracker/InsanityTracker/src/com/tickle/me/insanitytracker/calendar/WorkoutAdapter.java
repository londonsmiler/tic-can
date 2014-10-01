package com.tickle.me.insanitytracker.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tickle.me.insanitytracker.db.model.WorkoutDay;
import com.tickle.me.insanitytracker.db.repository.SavedDataRepository;

public class WorkoutAdapter extends BaseAdapter {

	private final SavedDataRepository repository;
	private final Context context;

	public WorkoutAdapter(SavedDataRepository repository, Context context) {
		this.repository = repository;
		this.context = context;
	}

	@Override
	public int getCount() {
		return repository.loadAllDays().size();
	}

	@Override
	public Object getItem(int arg0) {
		return repository.loadAllDays().get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View existingView, ViewGroup arg2) {
		WorkoutDay workoutDay = repository.loadAllDays().get(arg0);

		TextView view;

		if (existingView == null) {
			view = new TextView(context);
		} else {
			view = (TextView) existingView;
		}

		view.setText("day " + workoutDay.getDay() + "\n\n"
				+ workoutDay.getTitle());

		return view;

	}

}
