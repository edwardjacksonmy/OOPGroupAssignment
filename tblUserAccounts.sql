CREATE TABLE `java2`.`useraccount` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    UNIQUE INDEX `id_UNIQUE` (`id`) ,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username`) );