CREATE TABLE `employee-tracker`.employee
(
    id          bigint      NOT NULL AUTO_INCREMENT,
    personal_id bigint      NOT NULL,
    name        VARCHAR(25) NOT NULL,
    team        VARCHAR(25) NOT NULL,
    team_lead   VARCHAR(25) NOT NULL,

    CONSTRAINT employee_id_pk PRIMARY KEY (id),
    UNIQUE (personal_id)
);