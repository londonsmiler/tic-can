package com.tickle.me.insanitytracker.db.model;

public class WorkoutDayBuilder {

	private int id = 0;
	private int day = 1;
	private final String title = "a title";
	private boolean complete = false;

	public WorkoutDay build() {
		return new WorkoutDay(id, day, title, complete);
	}

	public WorkoutDayBuilder dayAndId(int i) {
		day = i;
		id = day - 1;
		return this;
	}

	public WorkoutDayBuilder complete(boolean b) {
		this.complete = b;
		return this;
	}

}
