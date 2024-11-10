CREATE SCHEMA IF NOT EXISTS `fifaranglist`;
USE `fifaranglist`;

CREATE TABLE IF NOT EXISTS `fifaranglist`.`confederation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `strength` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `fifaranglist`.`user_table` (
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `active` TINYINT NULL DEFAULT NULL,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`username`));

CREATE TABLE IF NOT EXISTS `fifaranglist`.`selection` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `confederation` INT NULL DEFAULT NULL,
  `active` TINYINT NULL DEFAULT NULL,
  `points` INT NULL DEFAULT NULL,
  `rang` INT NULL DEFAULT NULL,
  `confederation_id` INT NOT NULL,
  `user_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `selection_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fifaranglist`.`user_table` (`username`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `selection_ibfk_2`
    FOREIGN KEY (`confederation`)
    REFERENCES `fifaranglist`.`confederation` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `fifaranglist`.`match_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `strength` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `fifaranglist`.`match_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `host` INT NULL DEFAULT NULL,
  `away` INT NULL DEFAULT NULL,
  `hostGoals` INT NULL DEFAULT NULL,
  `awayGoals` INT NULL DEFAULT NULL,
  `matchType` INT NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `away_goals` INT NOT NULL,
  `match_date` DATE NOT NULL,
  `host_goals` INT NOT NULL,
  `match_away` INT NOT NULL,
  `match_host` INT NOT NULL,
  `match_type_id` INT NOT NULL,
  `user_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKisnbimw1c4rbv3puoyfapojg6`
    FOREIGN KEY (`match_host`)
    REFERENCES `fifaranglist`.`selection` (`id`),
  CONSTRAINT `FKjmi6vyxmvl0i7kiicho6jkt1r`
    FOREIGN KEY (`user_id`)
    REFERENCES `fifaranglist`.`user_table` (`username`),
  CONSTRAINT `FKnmv5il44sjhv3387nojwmemvm`
    FOREIGN KEY (`match_away`)
    REFERENCES `fifaranglist`.`selection` (`id`),
  CONSTRAINT `match_table_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `fifaranglist`.`user_table` (`username`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `match_table_ibfk_2`
    FOREIGN KEY (`away`)
    REFERENCES `fifaranglist`.`selection` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `match_table_ibfk_3`
    FOREIGN KEY (`host`)
    REFERENCES `fifaranglist`.`selection` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `match_table_ibfk_4`
    FOREIGN KEY (`match_type_id`)
    REFERENCES `fifaranglist`.`match_type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


INSERT INTO `fifaranglist`.`user_table` (`active`,`role`, `password`, `username`) VALUES ( 0, 'admin' , '$2y$10$q8gkCDrKtmlGWU5d8iGSgOCanVjOszjh0/JIbHl8B873Xj8sySrXe', 'user_test');
INSERT INTO `fifaranglist`.`user_table` (`active`,`role`, `password`, `username`) VALUES ('0', 'admin', '$2y$10$mYcgDfks60EaWbIK5u43Nu2yKDj3VSnyJZQvPQZViUkZo/4zQe/yq', 'veljko');
INSERT INTO `fifaranglist`.`user_table` (`active`,`role`, `password`, `username`) VALUES ( 0, 'user', '$2y$10$q8gkCDrKtmlGWU5d8iGSgOCanVjOszjh0/JIbHl8B873Xj8sySrXe', 'update_user');
INSERT INTO `fifaranglist`.`user_table` (`active`,`role`, `password`, `username`) VALUES ( 0, 'user', '$2y$10$q8gkCDrKtmlGWU5d8iGSgOCanVjOszjh0/JIbHl8B873Xj8sySrXe', 'delete_user');



INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('1', 'EUROPE', '1');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('2', 'SOUTHAMERICA', '1');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('3', 'AFRICA', '0.86');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('4', 'ASIA', '0.86');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('5', 'NORTHAMERICA', '0.84');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('6', 'OCEANIA', '0.84');

INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('1', '0', 'Serbia', '100', '1', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('3', '0', 'Austria', '50', '2', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('4', '0', 'update_selection', '0', '0', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('5', '0', 'delete_selection', '0', '0', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('10', '0', 'transaction_selection', '0', '0', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('11', '0', 'transaction_selection2', '0', '0', '1', 'user_test');

INSERT INTO `fifaranglist`.`match_type` (`id`,`name`, `strength`) VALUES ('1','WORLDCUP', '1');
INSERT INTO `fifaranglist`.`match_type` (`id`,`name`, `strength`) VALUES ('2','CONFEDERATIONCUP', '0.8');
INSERT INTO `fifaranglist`.`match_type` (`id`,`name`, `strength`) VALUES ('3','QUALIFIER', '0.6');
INSERT INTO `fifaranglist`.`match_type` (`id`, `name`, `strength`) VALUES ('4', 'FRIENDLY', '0.5');

INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('1', '1', '2024-01-01', '3', '3', '1', '3', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('6', '1', '2024-01-01', '3', '3', '1', '3', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('7', '1', '2024-01-01', '3', '11', '10', '1', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('8', '1', '2024-01-01', '3', '10', '11', '1', 'user_test');