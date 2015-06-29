CREATE TABLE `tags` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT,
  `parent_id` INT,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY  `tags_name_uc` (`name`)
);
