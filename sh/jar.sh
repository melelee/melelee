#! /bin/bash
jar_root=`pwd`
case "$1" in
start)
    echo "启动程序："$2
    num=`jps -l | grep $2 | wc -l`
    if [ $num -gt 0 ]; then
        echo "程序已启动"
    else
        nohup java -jar $2 >/dev/null 2>&1 &
    fi
    if [ ! -d $jar_root"/logs" ]; then
        mkdir $jar_root"/logs"
    fi
    if [ ! -f $jar_root"/logs/spring.log" ]; then
        touch $jar_root"/logs/spring.log"
    fi
    tail -f $jar_root"/logs/spring.log"
    ;;
stop)
    echo "停止程序："$2
    #kill `jps -v | grep $jar_root|awk '{print $2}'`
    echo `jps -l | grep $2`
    num=`jps -l | grep $2 | wc -l`
    count=1
    while [ $num -gt 0 ]; do
        kill `jps -l | grep $2 | awk '{print $1}'`
        echo "等待中... ... "$count
        sleep 1
        num=`jps -l | grep $2 | wc -l`
        let "count++"
    done
    echo "程序退出完成"
    ;;
restart)
    $0 stop $2
    $0 start $2
    ;;
status)
    echo `ps aux | grep $2 | grep -v $0 | grep -v "grep"`
    ;;
*)
    echo "不能识别的命令："$1
    echo "Usage: "$0" {start|stop|status|restart} jarfilename"
    exit 2
    ;;
esac

