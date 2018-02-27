create table `user`(
	id int(11) not null auto_increment,
	user_name varchar(255) not null,
	head_image VARCHAR(255),
	real_name VARCHAR(10) not null,
	pwd varchar(255) not null,
	user_id varchar(255) not null UNIQUE,
	idfa varchar(255),

	`create_time` timestamp not null default CURRENT_TIMESTAMP,
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	del_tag int(2) not null default 1,
	last_modify_user_id varchar(255),
	create_user_id varchar(255),
	last_modify_user_name varchar(18),
	create_time_str VARCHAR(255),
	update_time_str varchar(255),
	primary key(id)
)