-- Database schema for job-app-tutorial (MySQL).
-- Run against the jobs_app_database created by the Docker container.

USE jobs_app_database;

-- Parent table: other tables reference it, so it is created first
CREATE TABLE company (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE jobs_table (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    title       VARCHAR(255),
    description VARCHAR(255),
    min_salary  VARCHAR(255),
    max_salary  VARCHAR(255),
    location    VARCHAR(255),
    company_id  BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_jobs_company FOREIGN KEY (company_id)
        REFERENCES company (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

CREATE TABLE review (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    title       VARCHAR(255),
    description VARCHAR(255),
    rating      DOUBLE       NOT NULL,
    company_id  BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_review_company FOREIGN KEY (company_id)
        REFERENCES company (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);
