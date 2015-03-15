DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT,
  
  PRIMARY KEY (`id`),
  CONSTRAINT ACCOUNT_NAME_UC UNIQUE (`name`)
);

----------------------------------------------------

DROP TABLE IF EXISTS `tags`;

CREATE TABLE `tags` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT,
  `parent_id` INT,
  
  PRIMARY KEY (`id`),
  CONSTRAINT TAG_NAME_UC UNIQUE (`name`)
);

----------------------------------------------------

DROP TABLE IF EXISTS `transactions`;

CREATE TABLE `transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `amount` DOUBLE NOT NULL,  
  `description` VARCHAR(200) NOT NULL,  
  `account_id` INT NOT NULL,
  `comment` TEXT,
  
  PRIMARY KEY (`id`),
  
  FOREIGN KEY (`account_id`)
  REFERENCES accounts(`id`)
);

----------------------------------------------------

DROP TABLE IF EXISTS `transaction_tags`;

CREATE TABLE `transaction_tags` (
  `transaction_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  
  PRIMARY KEY (`transaction_id`, `tag_id`),
  
  FOREIGN KEY (`transaction_id`)
  REFERENCES transactions(`id`),
  
  FOREIGN KEY (`tag_id`)
  REFERENCES tags(`id`)
);
