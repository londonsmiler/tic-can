package com.tickle.me.insanitytracker.calendar;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tickle.me.insanitytracker.db.model.WorkoutDay;
import com.tickle.me.insanitytracker.db.repository.SavedDataRepository;

public class WorkoutDayAdapter extends BaseAdapter {

	public static final String TEXT_VIEW_TAG = "WorkoutDayAdapter.TextView";
	static final Object CHECKBOX_TAG = "WorkoutDayAdapter.CheckBox";
	private final SavedDataRepository repository;
	private final Context context;

	public WorkoutDayAdapter(SavedDataRepository repository, Context context) {
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

		LinearLayout view;
		TextView textView;

		CheckBox checkBox;
		if (existingView == null) {
			view = createLayout();

			textView = createTextView();
			view.addView(textView);

			checkBox = createCheckBox();
			view.addView(checkBox);

		} else {
			view = (LinearLayout) existingView;
			textView = (TextView) existingView.findViewWithTag(TEXT_VIEW_TAG);
			checkBox = (CheckBox) existingView.findViewWithTag(CHECKBOX_TAG);
		}

		StringBuilder detailString = new StringBuilder();

		detailString.append("<H2>Day ").append(workoutDay.getDay())
				.append("</H2><P>").append(workoutDay.getTitle());

		textView.setText(Html.fromHtml(detailString.toString()));

		checkBox.setChecked(workoutDay.isComplete());

		return view;

	}

	private LinearLayout createLayout() {
		LinearLayout view;
		view = new LinearLayout(context);
		view.setOrientation(LinearLayout.VERTICAL);
		view.setGravity(Gravity.FILL);
		return view;
	}

	private CheckBox createCheckBox() {
		CheckBox checkBox;
		checkBox = new CheckBox(context);
		checkBox.setTextColor(Color.YELLOW);
		checkBox.setBackgroundColor(Color.DKGRAY);
		checkBox.setTag(CHECKBOX_TAG);
		checkBox.setClickable(false);
		checkBox.setFocusable(false); // not tested
		checkBox.setFocusableInTouchMode(false); // not tested
		return checkBox;
	}

	private TextView createTextView() {
		TextView textView;
		textView = new TextView(context);
		textView.setTag(TEXT_VIEW_TAG);
		textView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 110));
		textView.setBackgroundColor(Color.DKGRAY);
		textView.setTextColor(Color.YELLOW);
		return textView;
	}
}
