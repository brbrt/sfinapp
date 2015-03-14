DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT,
  
  PRIMARY KEY (`id`),
  CONSTRAINT ACCOUNT_NAME_UC UNIQUE (`name`)
);



DROP TABLE IF EXISTS `tags`;

CREATE TABLE `tags` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT,
  `parent_id` INT,
  
  PRIMARY KEY (`id`),
  CONSTRAINT TAG_NAME_UC UNIQUE (`name`)
);

