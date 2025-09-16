-- Database and table for Trip Management
CREATE DATABASE IF NOT EXISTS tripdb;
USE tripdb;

CREATE TABLE IF NOT EXISTS trips (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    price DOUBLE NOT NULL,
    status VARCHAR(20) NOT NULL
);

