CREATE TABLE `accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT,

  PRIMARY KEY (`id`),
  CONSTRAINT `accounts_name_uc` UNIQUE (`name`)
);