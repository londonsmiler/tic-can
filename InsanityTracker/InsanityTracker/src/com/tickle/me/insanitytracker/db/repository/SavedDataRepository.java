package com.tickle.me.insanitytracker.db.repository;

import java.util.List;

import com.tickle.me.insanitytracker.db.model.Workout;
import com.tickle.me.insanitytracker.db.model.WorkoutDay;

public interface SavedDataRepository {

	public abstract void initialise();

	void updateDay(WorkoutDay day);

	List<Workout> loadAllWorkouts(int dayToLoad);

	List<WorkoutDay> loadAllDays();

}