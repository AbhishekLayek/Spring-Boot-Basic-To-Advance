CREATE TABLE departments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE employees
ADD COLUMN department_id BIGINT NOT NULL,
ADD CONSTRAINT fk_employee_department
    FOREIGN KEY (department_id)
    REFERENCES departments(id);