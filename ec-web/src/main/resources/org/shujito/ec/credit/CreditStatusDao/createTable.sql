create table if not exists credit_status (
	_id integer primary key on conflict fail,
	credit_id integer not null,
	credit_status_id integer not null,
	foreign key (credit_id) references credit(_id),
	foreign key (credit_status_id) references credit_status(_id)
)
