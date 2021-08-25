CREATE TABLE notification(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `notifier` BIGINT NOT NULL,
  `receiver` BIGINT NOT NULL,
  `outerId` BIGINT NOT NULL,
  `type` INT NOT NULL,
  `gmt_create` BIGINT NOT NULL,
  `status` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);
