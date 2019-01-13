#!/usr/bin/env bash

startup() {
    su
    setprop service.adb.tcp.port 5555
    start adbd
    echo `ifconfig wlan0`
}

shutdown() {
    stop adbd
}

if [ "$1" = "run" ] ; then
    startup
else
    shutdown
fi

