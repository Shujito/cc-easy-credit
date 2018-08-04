create table if not exists payment_type (
	_id integer primary key on conflict fail,
	months integer not null,
	interest real not null,
	unique (months, interest) on conflict fail
)
