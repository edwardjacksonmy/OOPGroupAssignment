CREATE TABLE `java2`.`fruits` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `keycode` VARCHAR(45) NOT NULL,
  `region` VARCHAR(45) NOT NULL,
  `stock` VARCHAR(45) NOT NULL,
  `rotten` INT(11) DEFAULT NULL,
  `supplier` VARCHAR(100) NOT NULL,
  `costs` DOUBLE NOT NULL,
  `sold` INT(11) DEFAULT NULL,
  `purchased` INT(11) NOT NULL,
  `arrival_date` DATE DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idnew_table_UNIQUE` (`id`) );