CREATE TABLE `user_details` (
	`user_id` varchar(6) NOT NULL DEFAULT '000000',
	`user_name` varchar(20) NOT NULL,
	`role` varchar(1) NOT NULL DEFAULT 'U',
	`age` int(3) NOT NULL,
	`gender` varchar(1) NOT NULL,
	`languages` varchar(100) NOT NULL,
	`preferred_notify` varchar(5) NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `library_items` (
	`item_id` varchar(8) NOT NULL,
	`item_name` varchar(50) NOT NULL,
	`year` int(4),
	`price` int(7) NOT NULL,
	`description` varchar(200) NOT NULL,
	`item_type` varchar(10) NOT NULL,
	`date_added` DATE NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`item_id`)
);

CREATE TABLE `books` (
	`book_id` varchar(8) NOT NULL DEFAULT 'BO000000',
	`author` varchar(50) NOT NULL,
	`publishers` varchar(100) NOT NULL,
	`edition_no` DECIMAL(5,2) NOT NULL,
	`release_date` DATE NOT NULL,
	`genre` varchar(20),
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`book_id`)
);

CREATE TABLE `item_format` (
	`item_id` varchar(8) NOT NULL,
	`item_type` varchar(6) NOT NULL,
	`available` varchar(150) NOT NULL,
	PRIMARY KEY (`item_id`,`item_type`)
);

CREATE TABLE `movies` (
	`movie_id` varchar(8) NOT NULL DEFAULT 'MO000000',
	`productions` varchar(100) NOT NULL,
	`writers` varchar(100) NOT NULL,
	`singers` varchar(100) NOT NULL,
	`directors` varchar(100) NOT NULL,
	`casts` varchar(100) NOT NULL,
	`release_date` DATE NOT NULL,
	`genre` varchar(20),
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`movie_id`)
);

CREATE TABLE `music` (
	`music_id` varchar(8) NOT NULL DEFAULT 'MU000000',
	`productions` varchar(100) NOT NULL,
	`writers` varchar(100) NOT NULL,
	`singers` varchar(100) NOT NULL,
	`release_date` DATE NOT NULL,
	`genre` varchar(20),
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`music_id`)
);

CREATE TABLE `user_contact_details` (
	`user_id` varchar(6) NOT NULL,
	`email_id` varchar(35) NOT NULL UNIQUE,
	`mobile_no` int(12) NOT NULL UNIQUE,
	`alternate_contact_no` int(12),
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `member_details` (
	`user_id` varchar(6) NOT NULL,
	`member_id` int(6) NOT NULL AUTO_INCREMENT,
	`membership_type_id` int(6) NOT NULL,
	`enrolled_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`renewal_date` DATE NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`member_id`)
);

CREATE TABLE `wish_list` (
	`member_id` int(6) NOT NULL,
	`item_id` varchar(8) NOT NULL,
	`wish_status` int(1) NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`member_id`,`item_id`)
);

CREATE TABLE `login_details` (
	`user_id` varchar(6) NOT NULL,
	`password` varchar(16) NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `login_audit` (
	`user_id` varchar(6) NOT NULL,
	`last_login_time` DATETIME NOT NULL,
	`login_status` char(1) NOT NULL,
	PRIMARY KEY (`user_id`,`last_login_time`)
);

CREATE TABLE `subscribed_list` (
	`member_id` int(6) NOT NULL,
	`item_id` varchar(8) NOT NULL,
	`start_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`return_status` varchar(10) NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`member_id`,`item_id`)
);

CREATE TABLE `liked_list` (
	`user_id` varchar(6) NOT NULL,
	`item_id` varchar(8) NOT NULL,
	`like_status` int(1) NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`user_id`,`item_id`)
);

CREATE TABLE `rating_table` (
	`user_id` varchar(6) NOT NULL,
	`item_id` varchar(8) NOT NULL,
	`rating` int(2) NOT NULL,
	`review` varchar(200) NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`user_id`,`item_id`)
);

CREATE TABLE `membership_details` (
	`member_type_id` int(6) NOT NULL AUTO_INCREMENT,
	`membership_type` varchar(6) NOT NULL,
	`max_price_limit` int(4) NOT NULL,
	`validity_days` int(3) NOT NULL,
	`membership_cost` int(5) NOT NULL,
	`c_time` DATETIME NOT NULL,
	`m_time` DATETIME NOT NULL,
	PRIMARY KEY (`member_type_id`,`membership_type`)
);

CREATE TABLE `address_details` (
	`user_id` varchar(6) NOT NULL,
	`doorno` int(2) NOT NULL,
	`street_name` varchar(50),
	`pin_code` int(6) NOT NULL,
	`city` varchar(20) NOT NULL,
	`state` varchar(20) NOT NULL,
	`country` varchar(20) NOT NULL,
	PRIMARY KEY (`user_id`,`doorno`)
);

ALTER TABLE `books` ADD CONSTRAINT `books_fk0` FOREIGN KEY (`book_id`) REFERENCES `library_items`(`item_id`) ON UPDATE CASCADE;

ALTER TABLE `item_format` ADD CONSTRAINT `item_format_fk0` FOREIGN KEY (`item_id`) REFERENCES `library_items`(`item_id`);

ALTER TABLE `movies` ADD CONSTRAINT `movies_fk0` FOREIGN KEY (`movie_id`) REFERENCES `library_items`(`item_id`) ON UPDATE CASCADE;

ALTER TABLE `music` ADD CONSTRAINT `music_fk0` FOREIGN KEY (`music_id`) REFERENCES `library_items`(`item_id`) ON UPDATE CASCADE;

ALTER TABLE `user_contact_details` ADD CONSTRAINT `user_contact_details_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details`(`user_id`) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `member_details` ADD CONSTRAINT `member_details_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details`(`user_id`) ON UPDATE CASCADE;

ALTER TABLE `member_details` ADD CONSTRAINT `member_details_fk1` FOREIGN KEY (`membership_type_id`) REFERENCES `membership_details`(`member_type_id`) ON UPDATE CASCADE;

ALTER TABLE `wish_list` ADD CONSTRAINT `wish_list_fk0` FOREIGN KEY (`member_id`) REFERENCES `member_details`(`member_id`) ON UPDATE CASCADE;

ALTER TABLE `wish_list` ADD CONSTRAINT `wish_list_fk1` FOREIGN KEY (`item_id`) REFERENCES `library_items`(`item_id`) ON UPDATE CASCADE;

ALTER TABLE `login_details` ADD CONSTRAINT `login_details_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details`(`user_id`) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `subscribed_list` ADD CONSTRAINT `subscribed_list_fk0` FOREIGN KEY (`member_id`) REFERENCES `member_details`(`member_id`) ON UPDATE CASCADE;

ALTER TABLE `subscribed_list` ADD CONSTRAINT `subscribed_list_fk1` FOREIGN KEY (`item_id`) REFERENCES `library_items`(`item_id`) ON UPDATE CASCADE;

ALTER TABLE `liked_list` ADD CONSTRAINT `liked_list_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details`(`user_id`) ON UPDATE CASCADE;

ALTER TABLE `liked_list` ADD CONSTRAINT `liked_list_fk1` FOREIGN KEY (`item_id`) REFERENCES `library_items`(`item_id`) ON UPDATE CASCADE;

ALTER TABLE `rating_table` ADD CONSTRAINT `rating_table_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details`(`user_id`) ON UPDATE CASCADE;

ALTER TABLE `rating_table` ADD CONSTRAINT `rating_table_fk1` FOREIGN KEY (`item_id`) REFERENCES `library_items`(`item_id`) ON UPDATE CASCADE;

ALTER TABLE `address_details` ADD CONSTRAINT `address_details_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_contact_details`(`user_id`) ON UPDATE CASCADE ON DELETE CASCADE;