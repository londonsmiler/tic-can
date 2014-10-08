package com.tickle.me.insanitytracker.db.model;

import java.util.List;

import android.provider.BaseColumns;

public class WorkoutDay {

	private final int id;
	private final int day;
	private final String title;
	private final List<Workout> children;

	public WorkoutDay(int id, int day, String title, List<Workout> children) {
		this.id = id;
		this.day = day;
		this.title = title;
		this.children = children;

	}

	public String getTitle() {
		return title;
	}

	public boolean isComplete() {

		for (Workout workout : children) {
			if (!workout.isCompleted()) {
				return false;
			}
		}

		return true;

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

	public int getDay() {
		return day;
	}
}
