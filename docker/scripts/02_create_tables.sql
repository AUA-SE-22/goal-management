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
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(128) NOT NULL,
    `detail`        VARCHAR(500) NOT NULL,
    `employee_id`   INT          NOT NULL,
    `creation_date` DATE         NOT NULL,
    `status`        enum('ACCEPTED', 'PENDING', 'REJECTED' ) NOT NULL,
    `employer_id`   INT          NOT NULL,
    `approve_date`  DATE         NOT NULL,
    `reject_date`   DATE         NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_GOAL_employee`
        FOREIGN KEY (`employee_id`)
            REFERENCES `employee` (`id`),
    CONSTRAINT `fk_goal_employer`
        FOREIGN KEY (`employer_id`)
            REFERENCES `employer` (`id`)
);
CREATE INDEX fk_GOAL_employee_idx ON goal (employee_id ASC );
CREATE INDEX fk_GOAL_employer_idx ON goal (employer_id ASC );