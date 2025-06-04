-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Project
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Project`;

-- -----------------------------------------------------
-- Schema Project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Project` DEFAULT CHARACTER SET utf8;
SHOW WARNINGS;
USE `Project`;

-- -----------------------------------------------------
-- Table `Project`.`Student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Student`;
SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `Project`.`Student` (
  `Sid` INT NOT NULL,
  `Sfname` VARCHAR(45) NOT NULL,
  `Slname` VARCHAR(45) NOT NULL,
  `Semail` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`Sid`)
)
ENGINE = InnoDB;
SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Project`.`Subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Subject`;
SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `Project`.`Subject` (
  `SubjectId` INT NOT NULL,
  `SubjectName` VARCHAR(45) NOT NULL,
  `SubjectCode` VARCHAR(45) NOT NULL,
  `credithour` INT NOT NULL,
  `Subjectcol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SubjectId`)
)
ENGINE = InnoDB;
SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Project`.`Section`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Section`;
SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `Project`.`Section` (
  `Cid` INT NOT NULL,
  `Cdate` DATE NOT NULL,
  `Cstime` TIME NOT NULL,
  `Cetime` TIME NOT NULL,
  `Subject_SubjectId` INT NOT NULL,
  PRIMARY KEY (`Cid`),
  INDEX `fk_Class_Subject1_idx` (`Subject_SubjectId` ASC),
  CONSTRAINT `fk_Class_Subject1`
    FOREIGN KEY (`Subject_SubjectId`)
    REFERENCES `Project`.`Subject` (`SubjectId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;
SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Project`.`Attendance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Attendance`;
SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `Project`.`Attendance` (
  `Student_Sid` INT NOT NULL,
  `Class_Cid` INT NOT NULL,
  `Aid` INT NOT NULL,
  `statuse` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Aid`, `Student_Sid`, `Class_Cid`),
  INDEX `fk_Student_has_Class_Class1_idx` (`Class_Cid` ASC),
  INDEX `fk_Student_has_Class_Student_idx` (`Student_Sid` ASC),
  CONSTRAINT `fk_Student_has_Class_Student`
    FOREIGN KEY (`Student_Sid`)
    REFERENCES `Project`.`Student` (`Sid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Student_has_Class_Class1`
    FOREIGN KEY (`Class_Cid`)
    REFERENCES `Project`.`Section` (`Cid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;
SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Project`.`Student_Subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Project`.`Student_Subject`;
SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `Project`.`Student_Subject` (
  `Student_Sid` INT NOT NULL,
  `Subject_SubjectId` INT NOT NULL,
  `id` INT NOT NULL,
  `degree` DOUBLE NOT NULL,
  PRIMARY KEY (`Student_Sid`, `Subject_SubjectId`, `id`),
  INDEX `fk_Student_has_Subject_Subject1_idx` (`Subject_SubjectId` ASC),
  INDEX `fk_Student_has_Subject_Student1_idx` (`Student_Sid` ASC),
  CONSTRAINT `fk_Student_has_Subject_Student1`
    FOREIGN KEY (`Student_Sid`)
    REFERENCES `Project`.`Student` (`Sid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Student_has_Subject_Subject1`
    FOREIGN KEY (`Subject_SubjectId`)
    REFERENCES `Project`.`Subject` (`SubjectId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB;
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
