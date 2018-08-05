create table if not exists user (
	_id integer primary key on conflict fail,
	username text,
	age integer,
	unique (username collate nocase),
	constraint 'age range not valid' check (age >= 20)
)
