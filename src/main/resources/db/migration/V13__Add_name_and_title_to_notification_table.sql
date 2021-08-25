ALTER TABLE `community`.`notification`
  ADD COLUMN `notifier_name` VARCHAR(100) DEFAULT 'NULL'  NULL AFTER `status`,
  ADD COLUMN `outer_title` VARCHAR(256) DEFAULT 'NUll'  NULL AFTER `notifier_name`;