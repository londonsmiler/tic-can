package com.tickle.me.insanitytracker.db.model;

import java.util.ArrayList;
import java.util.List;

import com.tickle.me.insanitytracker.calendar.WorkoutBuilder;

public class WorkoutDayBuilder {

	private int id = 0;
	private int day = 1;
	private final String title = "a title";
	private final List<Workout> children = new ArrayList<Workout>();

	public WorkoutDay build() {
		return new WorkoutDay(id, day, title, children);
	}

	public WorkoutDayBuilder dayAndId(int i) {
		day = i;
		id = day - 1;
		return this;
	}

	public WorkoutDayBuilder withCompleteChild() {
		children.add(new WorkoutBuilder().complete(true).build());
		return this;
	}

	public WorkoutDayBuilder withIncompleteChild() {
		children.add(new WorkoutBuilder().complete(false).build());
		return this;
	}

}
