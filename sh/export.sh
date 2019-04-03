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
username="midcuser"
password="iflytek!D123"
dbname="cloud"
dbhost="172.17.28.75"
dbport=4001
echo "请输入sql:"
read sql
echo "输入的sql为：$sql"
function query(){
    mysql -h${dbhost} -P${dbport} -u${username} -p${password} -D${dbname} -e"${sql}"
}

date=`date +%Y-%m-%d`

mkdir -p ${date}

for line in `query | grep external`
do
    src="/data/saas/$line"
    echo "导出目录文件:$src"

    tmp=${line#/*/*/*/*/*/}
    businessid=${tmp%/*/*/*}
#    echo "businessid:$businessid"

    target=./${date}/${businessid}

    if [ ! -d ${target} ]; then
        mkdir ./${date}/${businessid}
    fi
    cp -r ${src} ${target}
done

echo "************开始压缩数据**************"
#zip -rq ${date}.zip ${date}
