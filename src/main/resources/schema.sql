-- MySQL Script generated by MySQL Workbench
-- 12/21/16 13:30:51
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema multi_bank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema multi_bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `multi_bank` DEFAULT CHARACTER SET utf8 ;
USE `multi_bank` ;

-- -----------------------------------------------------
-- Table `multi_bank`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `multi_bank`.`bank` (
  `ID_BANK` INT(11) NOT NULL,
  `NAME` VARCHAR(45) NULL DEFAULT NULL,
  `PREFIX` VARCHAR(4) NULL DEFAULT NULL,
  `SUFFIX` VARCHAR(1) NULL DEFAULT NULL,
  `BIC` VARCHAR(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_BANK`),
  UNIQUE INDEX `idbank_UNIQUE` (`ID_BANK` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `multi_bank`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `multi_bank`.`account` (
  `ID_ACCOUNT_PK` INT(11) NOT NULL,
  `ACCOUNT_NUMBER` INT(16) NULL DEFAULT NULL,
  `BALANCE` FLOAT NULL DEFAULT NULL,
  `ID_BANK_FK` INT(11) NOT NULL,
  PRIMARY KEY (`ID_ACCOUNT_PK`),
  UNIQUE INDEX `idAccount_UNIQUE` (`ID_ACCOUNT_PK` ASC),
  UNIQUE INDEX `ID_BANK_FK_UNIQUE` (`ID_BANK_FK` ASC),
  CONSTRAINT `ID_BANK_FK`
    FOREIGN KEY (`ID_BANK_FK`)
    REFERENCES `multi_bank`.`bank` (`ID_BANK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `multi_bank`.`account_numbers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `multi_bank`.`account_numbers` (
  `ACCOUNT_NUMBER` INT(11) NOT NULL,
  PRIMARY KEY (`ACCOUNT_NUMBER`),
  UNIQUE INDEX `ACCOUNT_NUMBER_UNIQUE` (`ACCOUNT_NUMBER` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `multi_bank`.`credit_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `multi_bank`.`credit_account` (
  `ID_CREDIT_ACCOUNT_PK` INT(11) NOT NULL,
  `CREDITLINE` FLOAT NULL DEFAULT NULL,
  `ID_ACCOUNT_FK` INT(11) NOT NULL,
  PRIMARY KEY (`ID_CREDIT_ACCOUNT_PK`),
  UNIQUE INDEX `ID_CREDIT_ACCOUNT_PK_UNIQUE` (`ID_CREDIT_ACCOUNT_PK` ASC),
  UNIQUE INDEX `ID_ACCOUNT_FK_UNIQUE` (`ID_ACCOUNT_FK` ASC),
  INDEX `ID_ACCOUNT_FK_idx` (`ID_ACCOUNT_FK` ASC),
  CONSTRAINT `ID_ACCOUNT_FK`
    FOREIGN KEY (`ID_ACCOUNT_FK`)
    REFERENCES `multi_bank`.`account` (`ID_ACCOUNT_PK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `multi_bank`.`saving_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `multi_bank`.`saving_account` (
  `ID_SAVING_ACCOUNT_PK` INT(11) NOT NULL,
  `ID_ACC_FK` INT(11) NOT NULL,
  PRIMARY KEY (`ID_SAVING_ACCOUNT_PK`),
  UNIQUE INDEX `ID_SAVING_ACCOUNT_PK_UNIQUE` (`ID_SAVING_ACCOUNT_PK` ASC),
  UNIQUE INDEX `ID_ACCOUNT_FK_UNIQUE` (`ID_ACC_FK` ASC),
  CONSTRAINT `ID_ACC_FK`
    FOREIGN KEY (`ID_ACC_FK`)
    REFERENCES `multi_bank`.`account` (`ID_ACCOUNT_PK`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
