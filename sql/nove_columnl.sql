create table `novel_column`(
	id int(11) not null auto_increment,
	`column_name` varchar(18),
	column_id int(11) not null UNIQUE,
	parent_id int(11),
	description varchar(255),
	path varchar(255),

	`create_time` timestamp not null default CURRENT_TIMESTAMP,
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	del_tag int(2) not null default '1',
	last_modify_user_id varchar(255),
	create_user_id varchar(255),
	last_modify_user_name varchar(18),
	create_time_str VARCHAR(255),
	update_time_str varchar(255),
	primary key(id)
)