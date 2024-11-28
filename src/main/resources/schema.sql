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
  `active` TINYINT(1) NULL DEFAULT NULL,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`username`));

CREATE TABLE IF NOT EXISTS `fifaranglist`.`selection` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `confederation` INT NULL DEFAULT NULL,
  `active` TINYINT(1) NULL DEFAULT NULL,
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


INSERT INTO `fifaranglist`.`user_table` (`active`,`role`, `password`, `username`) VALUES (1, 'admin' , '$2y$10$q8gkCDrKtmlGWU5d8iGSgOCanVjOszjh0/JIbHl8B873Xj8sySrXe', 'user_test');
INSERT INTO `fifaranglist`.`user_table` (`active`,`role`, `password`, `username`) VALUES (1, 'admin', '$2y$10$mYcgDfks60EaWbIK5u43Nu2yKDj3VSnyJZQvPQZViUkZo/4zQe/yq', 'veljko');
INSERT INTO `fifaranglist`.`user_table` (`active`,`role`, `password`, `username`) VALUES (1, 'user', '$2y$10$q8gkCDrKtmlGWU5d8iGSgOCanVjOszjh0/JIbHl8B873Xj8sySrXe', 'update_user');
INSERT INTO `fifaranglist`.`user_table` (`active`,`role`, `password`, `username`) VALUES (1, 'user', '$2y$10$q8gkCDrKtmlGWU5d8iGSgOCanVjOszjh0/JIbHl8B873Xj8sySrXe', 'delete_user');


INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('1', 'EUROPE', '1');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('2', 'SOUTHAMERICA', '1');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('3', 'AFRICA', '0.86');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('4', 'ASIA', '0.86');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('5', 'NORTHAMERICA', '0.84');
INSERT INTO `fifaranglist`.`confederation` (`id`, `name`, `strength`) VALUES ('6', 'OCEANIA', '0.84');

INSERT INTO `fifaranglist`.`match_type` (`id`,`name`, `strength`) VALUES ('1','WORLDCUP', '1');
INSERT INTO `fifaranglist`.`match_type` (`id`,`name`, `strength`) VALUES ('2','CONFEDERATIONCUP', '0.8');
INSERT INTO `fifaranglist`.`match_type` (`id`,`name`, `strength`) VALUES ('3','QUALIFIER', '0.6');
INSERT INTO `fifaranglist`.`match_type` (`id`, `name`, `strength`) VALUES ('4', 'FRIENDLY', '0.5');

INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('1', '0', 'Serbia', '100', '1', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('3', '0', 'Austria', '50', '2', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('1', '0', 'Serbia', '100', '1', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('2', '0', 'Brazil', '120', '1', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('3', '0', 'Austria', '50', '2', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('4', '0', 'Germany', '110', '1', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('5', '0', 'Argentina', '90', '2', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('6', '0', 'France', '95', '2', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('7', '0', 'Italy', '85', '3', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('8', '0', 'Spain', '110', '1', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('9', '0', 'Netherlands', '80', '3', '1', 'user_test');
INSERT INTO `fifaranglist`.`selection` (`id`, `active`, `name`, `points`, `rang`, `confederation_id`, `user_id`) VALUES ('10', '0', 'England', '105', '2', '1', 'user_test');

INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('1', '1', '2024-01-01', '3', '3', '1', '3', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('6', '1', '2024-01-01', '3', '3', '1', '3', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('7', '1', '2024-01-01', '3', '11', '10', '1', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('8', '1', '2024-01-01', '3', '10', '11', '1', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('1', '1', '2024-01-01', '3', '3', '1', '1', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('2', '2', '2024-01-02', '2', '2', '4', '2', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('3', '1', '2024-01-03', '1', '1', '5', '3', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('4', '2', '2024-01-04', '2', '6', '4', '4', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('5', '3', '2024-01-05', '1', '2', '7', '1', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('6', '2', '2024-01-06', '4', '3', '8', '2', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('7', '1', '2024-01-07', '3', '10', '6', '3', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('8', '2', '2024-01-08', '2', '11', '5', '4', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('9', '3', '2024-01-09', '1', '6', '7', '2', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('10', '2', '2024-01-10', '3', '5', '8', '1', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('11', '3', '2024-01-11', '1', '2', '9', '3', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('12', '1', '2024-01-12', '3', '11', '7', '4', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('13', '0', '2024-01-13', '4', '10', '8', '1', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('14', '2', '2024-01-14', '1', '9', '3', '2', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('15', '1', '2024-01-15', '4', '8', '6', '4', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('16', '0', '2024-01-16', '2', '9', '10', '3', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('17', '2', '2024-01-17', '1', '3', '5', '4', 'user_test');
INSERT INTO `fifaranglist`.`match_table` (`id`, `away_goals`, `match_date`, `host_goals`, `match_away`, `match_host`, `match_type_id`, `user_id`) VALUES ('18', '3', '2024-01-18', '2', '4', '6', '1', 'user_test');