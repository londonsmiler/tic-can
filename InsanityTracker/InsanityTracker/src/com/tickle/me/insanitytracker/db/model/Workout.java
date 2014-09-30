package com.tickle.me.insanitytracker.db.model;

import java.util.Date;

import android.provider.BaseColumns;

public class Workout {

	private final String title;
	private boolean completed;
	private final int id;
	private Date completedDateTime;

	public Workout(int id, String title, boolean completed) {
		this.id = id;
		this.title = title;
		this.completed = completed;

	}

	public static abstract class WorkoutColumns implements BaseColumns {
		public static final String TABLE_NAME = "workout";
		public static final String COLUMN_NAME_DAY = "day";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_COMPLETED = "completed";
		public static final String COLUMN_NAME_DATETIME_COMPLETED = "datetime_completed";

	}

	public String getTitle() {

		return title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompletedDateTime(Date date) {
		completedDateTime = date;
	}

	public void setCompleted(boolean b) {
		completed = b;
	}

	public int getId() {
		return id;
	}

}
