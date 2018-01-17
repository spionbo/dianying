create table `fhvkxetpgjkm`(
	id int(11) not null auto_increment,
	user_id varchar(255),
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(id),
	foreign key(user_id) references `user`(user_id) ON DELETE CASCADE
)