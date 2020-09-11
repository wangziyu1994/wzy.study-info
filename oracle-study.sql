spool C:\Users\Administrator\Desktop\env-variable\spoolExport\Export.CSV
set colsep','; 
set echo off; 
set feedback on;
set heading off; 
set pagesize 0;
set termout off;
set trimout on;
set trimspool on;
set feedback off;
set heading off;
set echo off;
set term off;
set trimout on
set trimspool  on
set linesize 120;
select * from students_info fetch next 4 rows only;
spool off;


