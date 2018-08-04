create table if not exists credit_status_type (
	_id integer primary key on conflict fail,
	description text not null,
	unique (description collate nocase)
)
