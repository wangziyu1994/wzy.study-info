SHOW VARIABLES LIKE 'max_connections';
EXPLAIN SELECT * FROM wzy.students;
SHOW TABLE STATUS LIKE  'students';
SHOW INDEX FROM students;
USE wzy;
SHOW CREATE TABLE students;
CREATE TABLE `students-copy` (
  `s_id` VARCHAR(20) DEFAULT NULL,
  `s_name` VARCHAR(10) DEFAULT NULL,
  `start_date` DATE NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

SHOW TABLES LIKE `students-copy`;
SELECT * FROM `students-copy` ;


INSERT INTO `students-copy` (s_id,s_name,start_date) 
SELECT s_id,s_name,start_date FROM students;
