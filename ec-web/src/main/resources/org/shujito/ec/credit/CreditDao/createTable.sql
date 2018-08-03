create table if not exists credit (
	_id integer primary key on conflict fail,
	user_id integer not null,
	amount real not null,
	approved integer not null,
	payment_type_id integer not null,
	foreign key (user_id) references user(_id),
	foreign key (payment_type_id) references payment_type(_id)
);
