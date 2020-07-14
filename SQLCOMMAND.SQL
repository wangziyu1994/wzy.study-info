#MYSQL 查看SQL语句执行计划
EXPLAIN SELECT * FROM wzy.students;

#MYSQL 连接
#内连接，左外连接，全连接，交叉连接（笛卡尔积）


#MYSQL 分组使用`employee_tbl`
-- WITH ROLLUP 可以实现在分组统计数据基础上再进行相同的统计（SUM,AVG,COUNT…）。
-- 我们可以使用 coalesce 来设置一个可以取代 NUll 的名称，coalesce 语法：
-- 参数说明：如果a==null,则选择b；如果b==null,则选择c；如果a!=null,则选择a；如果a b c 都为null ，则返回为null（没意义）。
 SELECT NAME, COUNT(*) FROM   employee_tbl GROUP BY NAME;
 SELECT NAME, SUM(singin) FROM   employee_tbl GROUP BY NAME WITH ROLLUP;
#MYSQL 查看表状态
SHOW TABLE STATUS LIKE  'students';
#MYSQL 查看表索引状况
SHOW INDEX FROM students;
SELECT * FROM students WHERE s_name=NULL;

#下表中的正则模式可应用于 REGEXP 操作符中。

/**模式	描述
^	匹配输入字符串的开始位置。如果设置了 RegExp 对象的 Multiline 属性，^ 也匹配 '\n' 或 '\r' 之后的位置。
$	匹配输入字符串的结束位置。如果设置了RegExp 对象的 Multiline 属性，$ 也匹配 '\n' 或 '\r' 之前的位置。
.	匹配除 "\n" 之外的任何单个字符。要匹配包括 '\n' 在内的任何字符，请使用象 '[.\n]' 的模式。
[...]	字符集合。匹配所包含的任意一个字符。例如， '[abc]' 可以匹配 "plain" 中的 'a'。
[^...]	负值字符集合。匹配未包含的任意字符。例如， '[^abc]' 可以匹配 "plain" 中的'p'。
p1|p2|p3	匹配 p1 或 p2 或 p3。例如，'z|food' 能匹配 "z" 或 "food"。'(z|f)ood' 则匹配 "zood" 或 "food"。
*	匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。* 等价于{0,}。
+	匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,}。
{n}	n 是一个非负整数。匹配确定的 n 次。例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o。
{n,m}	m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。
*/




#MYSQL 复制表结构以及表数据
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

#Mysql 获取服务器元数据
SELECT VERSION();
SELECT DATABASE();
SELECT USER();
SHOW STATUS; -- 获取MYSQL服务器状态
SHOW VARIABLES;

#MYSQL事务
/**
MySQL 事务主要用于处理操作量大，复杂度高的数据。比如说，在人员管理系统中，你删除一个人员，你即需要删除人员的基本资料，也要删除和该人员相关的信息，如信箱，文章等等，这样，这些数据库操作语句就构成一个事务！

在 MySQL 中只有使用了 Innodb 数据库引擎的数据库或表才支持事务。
事务处理可以用来维护数据库的完整性，保证成批的 SQL 语句要么全部执行，要么全部不执行。
事务用来管理 insert,update,delete 语句
一般来说，事务是必须满足4个条件（ACID）：：原子性（Atomicity，或称不可分割性）、一致性（Consistency）、隔离性（Isolation，又称独立性）、持久性（Durability）。

原子性：一个事务（transaction）中的所有操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被回滚（Rollback）到事务开始前的状态，就像这个事务从来没有执行过一样。
一致性：在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这表示写入的资料必须完全符合所有的预设规则，这包含资料的精确度、串联性以及后续数据库可以自发性地完成预定的工作。
隔离性：数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable read）和串行化（Serializable）。
持久性：事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。
在 MySQL 命令行的默认设置下，事务都是自动提交的，即执行 SQL 语句后就会马上执行 COMMIT 操作。因此要显式地开启一个事务务须使用命令 BEGIN 或 START TRANSACTION，或者执行命令 SET AUTOCOMMIT=0，用来禁止使用当前会话的自动提交。

事务控制语句：
BEGIN 或 START TRANSACTION 显式地开启一个事务；
COMMIT 也可以使用 COMMIT WORK，不过二者是等价的。COMMIT 会提交事务，并使已对数据库进行的所有修改成为永久性的；
ROLLBACK 也可以使用 ROLLBACK WORK，不过二者是等价的。回滚会结束用户的事务，并撤销正在进行的所有未提交的修改；
SAVEPOINT identifier，SAVEPOINT 允许在事务中创建一个保存点，一个事务中可以有多个 SAVEPOINT；
RELEASE SAVEPOINT identifier 删除一个事务的保存点，当没有指定的保存点时，执行该语句会抛出一个异常；
ROLLBACK TO identifier 把事务回滚到标记点；
SET TRANSACTION 用来设置事务的隔离级别。InnoDB 存储引擎提供事务的隔离级别有READ UNCOMMITTED、READ COMMITTED、REPEATABLE READ 和 SERIALIZABLE。

*/
SET autocommit=0;
INSERT INTO students VALUES(7,"wzytest1",DATE_FORMAT(NOW(),"%Y-%m-%d"));
BEGIN;
INSERT INTO students VALUES(7,"wzytest1",DATE_FORMAT(NOW(),"%Y-%m-%d"));
SELECT * FROM students WHERE s_name LIKE CONCAT('%',CONCAT('wzy','%'));

COMMIT;
END;
SELECT DATE_FORMAT(CURRENT_DATE(),'%Y-%m-%d') FROM DUAL;
SELECT CURRENT_DATE() FROM DUAL;

#MySQL 序列使用`students`

#MYSQL重复数据
-- INSERT IGNORE INTO 当插入数据时，在设置了记录的唯一性后，如果插入重复数据，将不返回错误，只以警告形式返回。 
-- 而 REPLACE INTO 如果存在 primary 或 unique 相同的记录，则先删除掉。再插入新记录。

#MYSQL 重命名表
ALTER TABLE `students-copy` RENAME TO  `students_copy`;



