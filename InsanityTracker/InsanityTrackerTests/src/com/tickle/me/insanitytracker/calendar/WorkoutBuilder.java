package com.tickle.me.insanitytracker.calendar;

import com.tickle.me.insanitytracker.db.model.Workout;

public class WorkoutBuilder {

	private boolean completed = false;
	private String title = "a workout";
	private int id = 0;

	public Workout build() {
		return new Workout(id, title, completed);
	}

	public WorkoutBuilder title(String string) {
		this.title = string;
		return this;
	}

	public WorkoutBuilder id(int i) {
		this.id = i;
		return this;
	}

	public WorkoutBuilder complete(boolean b) {
		completed = b;
		return this;
	}

}
