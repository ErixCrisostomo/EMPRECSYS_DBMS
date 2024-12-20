CREATE DATABASE IF NOT EXISTS emprecsys;
USE emprecsys;

CREATE TABLE PersonalInfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    contactNumber VARCHAR(15) NOT NULL,
    hireDate DATE NOT NULL
);

-- Table for Full-Time Employee specific information
CREATE TABLE FullTimeEmployees (
    id INT PRIMARY KEY,
    position VARCHAR(50) NOT NULL,
    monthlySalary DOUBLE NOT NULL,
    FOREIGN KEY (id) REFERENCES PersonalInfo(id) ON DELETE CASCADE
);

-- Table for Part-Time Employee specific information
CREATE TABLE PartTimeEmployees (
    id INT PRIMARY KEY,
    position VARCHAR(50) NOT NULL,
    hourlyWage DOUBLE NOT NULL,
    hoursWorked DOUBLE NOT NULL,
    FOREIGN KEY (id) REFERENCES PersonalInfo(id) ON DELETE CASCADE
);

UPDATE PersonalInfo
SET name = ?, email = ?, contactNumber = ?, hireDate = ?
WHERE id = ?;

UPDATE FullTimeEmployees
SET position = ?, monthlySalary = ?
WHERE id = ?;

UPDATE PartTimeEmployees
SET position = ?, hourlyWage = ?, hoursWorked = ?
WHERE id = ?;