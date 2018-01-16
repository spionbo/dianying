create table `permission_user`(
	id int(11) not null auto_increment,
	user_id int(11),
	position_id int(11),
	permission_key varchar(255),
	permission_val varchar(255),

	`create_time` timestamp not null default CURRENT_TIMESTAMP,
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	del_tag int(2) not null default "1",
	last_modify_user_id int(11),
	create_user_id int(11),
	last_modify_user_name varchar(18),
	create_time_str VARCHAR(255),
	update_time_str varchar(255),
	primary key(id),
	foreign key(position_id) references `permission`(id) ON DELETE CASCADE
)