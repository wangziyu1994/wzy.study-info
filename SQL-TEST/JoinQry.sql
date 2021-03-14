-- 一对一 内连接
SELECT
	A.s_id,
	A.s_name,
	B.s_id,
	B.s_name
FROM
	A_CLASS AS A
inner JOIN B_CLASS AS B ON A.s_id = B.s_id;

-- 一对多 内连接
SELECT
	A.s_id,
	A.s_name,
	C.s_id,
	C.s_name
FROM
	A_CLASS AS A
inner JOIN C_CLASS AS C ON A.s_id = C.s_id order by A.s_id,C.s_id asc;


-- 多对多 内连接
SELECT
	C.s_id,
	C.s_name,
	D.s_id,
	D.s_name
FROM
	C_CLASS AS C
inner JOIN D_CLASS AS D ON C.s_id = D.s_id order by C.s_id,C.s_name,D.s_id,D.s_name asc;