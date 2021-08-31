ALTER TABLE question CHANGE `title` `title` VARCHAR(50) CHARSET utf8 COLLATE utf8_general_ci NULL;
ALTER TABLE question CHANGE `tag` `tag` VARCHAR(256) CHARSET utf8 COLLATE utf8_general_ci NULL;
ALTER TABLE comment CHANGE `content` `content` VARCHAR(1024) CHARSET utf8 COLLATE utf8_general_ci NULL;
ALTER TABLE notification CHANGE `notifier_name` `notifier_name` VARCHAR(100) CHARSET utf8 COLLATE utf8_general_ci NULL;
ALTER TABLE notification CHANGE `outer_title` `outer_title` VARCHAR(256) CHARSET utf8 COLLATE utf8_general_ci NULL;

