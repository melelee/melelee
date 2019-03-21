#!/bin/bash
# author:melelee
#######################################
# 影像文件导出sh
# 全局变量:
#   None
# 参数:
#   None
# 返回值:
#   None
#######################################
username=""
password=""
dbname=""
sql=""

function query(){
    mysql -u${username} -p${password} -D${dbname} -e"${sql}"
}

date=`date +%Y-%m-%d`

mkdir ${date}

for line in `query | grep external`
do
    dir="/data/saas/$line"
    scp -r midcuser@127.0.0.1:${dir%/*/*/*} ./${date}/
done

echo "************开始压缩数据**************"
zip -r ${date}.zip ${date}