create table if not exists credit_status (
	credit_id integer not null,
	credit_status_type_id integer not null,
	foreign key (credit_id) references credit(_id),
	foreign key (credit_status_type_id) references credit_status_type(_id),
	primary key (credit_id) on conflict replace
)
