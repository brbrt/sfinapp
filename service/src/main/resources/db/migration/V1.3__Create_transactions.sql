CREATE TABLE `transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `amount` DOUBLE NOT NULL,  
  `description` VARCHAR(200) NOT NULL,  
  `account_id` INT NOT NULL,
  `to_account_id` INT,
  `comment` TEXT,
  
  PRIMARY KEY (`id`),
  FOREIGN KEY (`account_id`) REFERENCES accounts(`id`),
  FOREIGN KEY (`to_account_id`) REFERENCES accounts(`id`),
  UNIQUE KEY `transactions_uc` (`date`, `amount`, `description`, `account_id`)
);


CREATE TABLE `transaction_tags` (
  `transaction_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  
  PRIMARY KEY (`transaction_id`, `tag_id`),
  FOREIGN KEY (`transaction_id`) REFERENCES transactions(`id`),
  FOREIGN KEY (`tag_id`) REFERENCES tags(`id`)
);
