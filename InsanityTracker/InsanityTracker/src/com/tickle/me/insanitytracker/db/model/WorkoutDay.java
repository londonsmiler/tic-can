package com.tickle.me.insanitytracker.db.model;

import android.provider.BaseColumns;

public class WorkoutDay {

	private final int id;
	private final int day;
	private final String title;
	private boolean complete;

	public WorkoutDay(int id, int day, String title, boolean complete) {
		this.id = id;
		this.day = day;
		this.title = title;
		this.complete = complete;

	}

	public String getTitle() {
		return title;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean b) {
		complete = b;
	}

	public String getIdAsString() {
		return "" + id;
	}

	public static abstract class WorkoutDayColumns implements BaseColumns {
		public static final String TABLE_NAME = "workout_day";
		public static final String COLUMN_NAME_DAY = "day";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_COMPLETED = "complete";

	}
}
