-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shopgiay` DEFAULT CHARACTER SET utf8 ;
USE `shopgiay` ;

-- -----------------------------------------------------
-- Table `shopgiay`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `created` DATETIME NOT NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT '0',
  `phone` VARCHAR(10) not NULL,
  `address` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `email` VARCHAR(32) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 628
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `thumbnail` VARCHAR(100) NOT NULL,
  `price` INT(11) NOT NULL,
  `sale_price` INT(11) NOT NULL DEFAULT '0',
  `created` DATETIME NOT NULL,
  `description` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`comment`
-- -----------------------------------------------------
use shopgiay;

CREATE TABLE IF NOT EXISTS `shopgiay`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_parent_id` int not null default 0,
  `content` NVARCHAR(255) NOT NULL,
  `created` DATETIME NOT NULL,
  `account_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_comment_account_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_comment_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `shopgiay`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shopgiay`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`rate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` INT(1) NOT NULL,
  `account_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_rate_account1_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_rate_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_rate_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `shopgiay`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rate_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shopgiay`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `shopgiay` ;

-- -----------------------------------------------------
-- Table `shopgiay`.`checkout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`checkout` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL,
  `confirm` TINYINT(1) NOT NULL DEFAULT '0',
  `status` INT(11) NOT NULL DEFAULT '0',
  `account_id` INT(11) NOT NULL,
  `type` INT(11) NOT NULL,
  `deny` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_checkout_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_checkout_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `shopgiay`.`account` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`color` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `color` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `color_UNIQUE` (`color` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 43
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`size`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`size` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `size` VARCHAR(30) NOT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `size_UNIQUE` (`size` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`product_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`product_detail` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `amount` INT(11) NOT NULL DEFAULT '0',
  `color_id` INT(11) NOT NULL,
  `size_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `color_id`, `size_id`, `product_id`),
  INDEX `fk_product_detail_color1_idx` (`color_id` ASC) VISIBLE,
  INDEX `fk_product_detail_size1_idx` (`size_id` ASC) VISIBLE,
  INDEX `fk_product_detail_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_detail_color1`
    FOREIGN KEY (`color_id`)
    REFERENCES `shopgiay`.`color` (`id`),
  CONSTRAINT `fk_product_detail_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shopgiay`.`product` (`id`),
  CONSTRAINT `fk_product_detail_size1`
    FOREIGN KEY (`size_id`)
    REFERENCES `shopgiay`.`size` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`cart_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`cart_item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_detail_id` INT(11) NOT NULL,
  `account_id` INT(11) NOT NULL,
  `amount` INT(11) NOT NULL DEFAULT '1',
  `show` TINYINT(1) NOT NULL DEFAULT '1',
  `checkout_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `product_detail_id`, `account_id`),
  INDEX `fk_cart_item_product_detail1_idx` (`product_detail_id` ASC) VISIBLE,
  INDEX `fk_cart_item_account1_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_cart_item_checkout1_idx` (`checkout_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_item_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `shopgiay`.`account` (`id`),
  CONSTRAINT `fk_cart_item_checkout1`
    FOREIGN KEY (`checkout_id`)
    REFERENCES `shopgiay`.`checkout` (`id`),
  CONSTRAINT `fk_cart_item_product_detail1`
    FOREIGN KEY (`product_detail_id`)
    REFERENCES `shopgiay`.`product_detail` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `created` DATETIME NOT NULL,
  `image` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`product_has_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`product_has_category` (
  `product_id` INT(11) NOT NULL,
  `category_id` INT(11) NOT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  INDEX `fk_product_has_category_category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_product_has_category_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `shopgiay`.`category` (`id`),
  CONSTRAINT `fk_product_has_category_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shopgiay`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`product_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`product_image` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `image` VARCHAR(100) NOT NULL,
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_image_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_image_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shopgiay`.`product` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shopgiay`.`wish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shopgiay`.`wish` (
  `account_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`account_id`, `product_id`),
  INDEX `fk_account_has_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_account_has_product_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_has_product_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `shopgiay`.`account` (`id`),
  CONSTRAINT `fk_account_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `shopgiay`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
