USE goal_management;
-- ------------------------------------------------------------------------
-- Table `employee`
-- ------------------------------------------------------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `keycloakId` VARCHAR(36)  NOT NULL,
    `firstName`  VARCHAR(255) NOT NULL,
    `lastName`   VARCHAR(255) NOT NULL,
    `email`      VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);


-- ------------------------------------------------------------------------
-- Table `employer`
-- ------------------------------------------------------------------------
DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `keycloakId` VARCHAR(36)  NOT NULL,
    `firstName`  VARCHAR(255) NOT NULL,
    `lastName`   VARCHAR(255) NOT NULL,
    `email`      VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);



-- ------------------------------------------------------------------------
-- Table `goals`
-- ------------------------------------------------------------------------
DROP TABLE IF EXISTS `goal`;
CREATE TABLE `goal`
(
    `id`           INT                                       NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(128)                              NOT NULL,
    `detail`       VARCHAR(500)                              NOT NULL,
    `employeeId`   INT                                       NOT NULL,
    `creationDate` DATE                                      NOT NULL,
    `status`       enum ('ACCEPTED', 'PENDING', 'REJECTED' ) NOT NULL,
    `employerId`   INT                                       NOT NULL,
    `approveDate`  DATE,
    `rejectDate`   DATE,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_GOAL_employee`
        FOREIGN KEY (`employeeId`)
            REFERENCES `employee` (`id`),
    CONSTRAINT `fk_goal_employer`
        FOREIGN KEY (`employerId`)
            REFERENCES `employer` (`id`)
);