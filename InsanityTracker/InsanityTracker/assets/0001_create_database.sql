create table workout_day (_id INTEGER PRIMARY KEY AUTOINCREMENT, day INTEGER, title TEXT, completed INTEGER);
create table workout (_id INTEGER PRIMARY KEY AUTOINCREMENT, day INTEGER, title TEXT, completed INTEGER, datetime_completed TEXT);

insert into workout_day (day, title, completed) values (1,"Fit Test",0);
insert into workout_day (day, title, completed) values (2,"Plyometric Cardio Circuit",0);
insert into workout_day (day, title, completed) values (3,"Cardio Power & Resistance",0);
insert into workout_day (day, title, completed) values (4,"Cardio Recovery",0);
insert into workout_day (day, title, completed) values (5,"Pure Cardio",0);
insert into workout_day (day, title, completed) values (6,"Plyometric Cardio Circuit",0);
insert into workout_day (day, title, completed) values (7,"Rest",0);
insert into workout_day (day, title, completed) values (8,"Cardio Power & Resistance",0);
insert into workout_day (day, title, completed) values (9,"Pure Cardio",0);
insert into workout_day (day, title, completed) values (10,"Plyometric Cardio Circuit",0);
insert into workout_day (day, title, completed) values (11,"Cardio Recovery",0);
insert into workout_day (day, title, completed) values (12,"Cardio Power & Resistance",0);
insert into workout_day (day, title, completed) values (13,"Pure Cardio & Cardio Abs",0);
insert into workout_day (day, title, completed) values (14,"Rest",0);
insert into workout_day (day, title, completed) values (15,"Fit Test",0);
insert into workout_day (day, title, completed) values (16,"Plyometric Cardio Circuit",0);
insert into workout_day (day, title, completed) values (17,"Pure Cardio & Cardio Abs",0);
insert into workout_day (day, title, completed) values (18,"Cardio Recovery",0);
insert into workout_day (day, title, completed) values (19,"Cardio Power & Resistance",0);
insert into workout_day (day, title, completed) values (20,"Plyometric Cardio Circuit",0);

insert into workout (day, title, completed) values (1,"Fit Test",0);
insert into workout (day, title, completed) values (2,"Plyometric Cardio Circuit",0);
insert into workout (day, title, completed) values (3,"Cardio Power",0);
insert into workout (day, title, completed) values (3,"Resistance",0);
insert into workout (day, title, completed) values (4,"Cardio Recovery",0);
insert into workout (day, title, completed) values (5,"Pure Cardio",0);
insert into workout (day, title, completed) values (6,"Plyometric Cardio Circuit",0);
insert into workout (day, title, completed) values (7,"Rest",0);
insert into workout (day, title, completed) values (8,"Cardio Power",0);
insert into workout (day, title, completed) values (8,"Resistance",0);
insert into workout (day, title, completed) values (9,"Pure Cardio",0);
insert into workout (day, title, completed) values (10,"Plyometric Cardio Circuit",0);
insert into workout (day, title, completed) values (11,"Cardio Recovery",0);
insert into workout (day, title, completed) values (12,"Cardio Power",0);
insert into workout (day, title, completed) values (12,"Resistance",0);
insert into workout (day, title, completed) values (13,"Pure Cardio",0);
insert into workout (day, title, completed) values (13,"Cardio Abs",0);
insert into workout (day, title, completed) values (14,"Rest",0);
insert into workout (day, title, completed) values (15,"Fit Test",0);
insert into workout (day, title, completed) values (16,"Plyometric Cardio Circuit",0);
insert into workout (day, title, completed) values (17,"Pure Cardio",0);
insert into workout (day, title, completed) values (17,"Cardio Abs",0);
insert into workout (day, title, completed) values (18,"Cardio Recovery",0);
insert into workout (day, title, completed) values (19,"Cardio Power",0);
insert into workout (day, title, completed) values (19,"Resistance",0);
insert into workout (day, title, completed) values (20,"Plyometric Cardio Circuit",0);
