#!/bin/bash
. ~/.bashrc
. ~/.bash_profile

APP_NAME=midc-system
APP_PORT=8866

#JMX监控相关
#IP=$(ip a|grep -w 'inet'|grep 'global'|sed 's/^.*inet //g'|sed 's/\/[0-9][0-9].*$//g')
#IP=172.31.237.52
IP=
JMX_PORT=

CONFIG_SERVER_URL=http://user:iflytek!@172.22.124.181:8863

BASE_PATH=$(cd `dirname $0`;pwd)
cd ${BASE_PATH}

# 内存 OOM快照 JMX GClog，如果没有server_ip参数，不添加jmx
if [ -n "$IP" ]; then
	JAVA_OPTS="-Xms512m -Xmx1024m \
		-XX:+HeapDumpOnOutOfMemoryError \
		-XX:HeapDumpPath=logs/heapdump.hprof \
		-Djava.rmi.server.hostname=$IP \
		-Dcom.sun.management.jmxremote.port=$JMX_PORT \
		-Dcom.sun.management.jmxremote.ssl=false \
		-Dcom.sun.management.jmxremote.authenticate=false \
		-XX:+PrintGCDateStamps \
		-XX:+PrintGCDetails \
		-Xloggc:logs/gc.log \
		-Djava.io.tmpdir=/data/springboot/tmp"
else
	JAVA_OPTS="-Xms512m -Xmx1024m \
		-XX:+HeapDumpOnOutOfMemoryError \
		-XX:HeapDumpPath=logs/heapdump.hprof \
		-XX:+PrintGCDateStamps \
		-XX:+PrintGCDetails \
		-Xloggc:logs/gc.log \
		-Djava.io.tmpdir=/data/springboot/tmp"
fi

# 应用名 端口 配置中心 地址 profile 连不上配置中心不让启动
APP_ARGS="--spring.application.name=$APP_NAME \
    --server.port=$APP_PORT \
    --spring.cloud.config.uri=$CONFIG_SERVER_URL \
    --spring.cloud.config.profile=dev \
    --spring.cloud.config.fail-fast=true"



#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
    exit 1
}

#检查程序是否在运行
is_exist(){
  PIDS=`jps -l | grep ${APP_NAME}.jar |awk '{print $1}' `
  #如果不存在返回1，存在返回0
  if [ -z "${PIDS}" ]; then
   return 1
  else
   return 0
  fi
}

#启动方法
start(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is already running. pid=${PIDS} ."
  else
    nohup java ${JAVA_OPTS} -jar ${APP_NAME}.jar ${APP_ARGS} >/dev/null 2>&1 &
  fi
}

#停止方法
stop(){
  is_exist
  if [ $? -eq "0" ]; then

    echo "kill $PIDS"
    kill ${PIDS}
    echo "进程关闭完成，休眠5秒等待端口释放！"

    sleep 5

    is_exist

    if [ $? -eq "0" ]; then
       echo "kill -9 $PIDS"
       kill -9 ${PIDS}
    fi

    echo "${APP_NAME} stop success"
  else
    echo "${APP_NAME} is not running"
  fi


}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is running. Pid is ${PIDS}"
  else
    echo "${APP_NAME} is NOT running."
  fi
}

#重启
restart(){
  stop
  start
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac
