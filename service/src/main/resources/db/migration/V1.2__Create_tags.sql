CREATE TABLE `tags` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT,
  `parent_id` INT,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `tags_name_uc` UNIQUE (`name`)
);
