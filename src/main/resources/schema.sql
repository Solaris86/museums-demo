DROP TABLE IF EXISTS works;
DROP TABLE IF EXISTS museums;

CREATE TABLE museums (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    founded VARCHAR
 );

 CREATE TABLE works (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    artist VARCHAR(100),
    period VARCHAR(50),
    medium VARCHAR(50),
    museum_id INT NOT NULL,
    FOREIGN KEY (museum_id) REFERENCES museums(id)
 );