package com.tickle.me.insanitytracker.db.repository;

import java.util.List;

import com.tickle.me.insanitytracker.db.model.Workout;
import com.tickle.me.insanitytracker.db.model.WorkoutDay;

public interface SavedDataRepository {

	public abstract void initialise();

	List<Workout> loadAllWorkouts(int dayToLoad);

	List<WorkoutDay> loadAllDays();

	void updateDay(WorkoutDay day);

	void saveWorkout(Workout workout);

}