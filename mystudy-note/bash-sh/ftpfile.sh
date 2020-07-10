user=ftpadmin1
password=wangziyu
port=22
dir1=/home/ftpuser
dir2=/d/info
ip=192.168.30.140
user1=root
log=ftp.log
filename=$1

lftp -u ${user},${password}  sftp://${ip}:${port} <<EOF
cd ${dir1};
bin;
prompt;
get ${filename};
bye;
EOF


cd ${dir2};
if test -e ${filename} 
then
echo "end=== `date "+%Y-%m-%d  %H:%M:%S"` not found" >>ftp.log
else
echo "end=== `date "+%Y-%m-%d  %H:%M:%S"` found" >>ftp.log
fi
exit 0;
