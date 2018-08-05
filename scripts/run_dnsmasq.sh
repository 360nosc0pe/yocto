#!/bin/bash

cd $(dirname $0)

MACHINE=sds1104xe
LAN_INTERFACE=enp0s20f0u2
WAN_INTERFACE=enp0s31f6

ifconfig $LAN_INTERFACE 192.168.0.1 netmask 255.255.255.0 up

/sbin/iptables -t nat -D POSTROUTING -o $WAN_INTERFACE -j MASQUERADE
/sbin/iptables -D FORWARD -i $WAN_INTERFACE -o $LAN_INTERFACE -m state --state RELATED,ESTABLISHED -j ACCEPT
/sbin/iptables -D FORWARD -i $LAN_INTERFACE -o $WAN_INTERFACE -j ACCEPT

echo 1 > /proc/sys/net/ipv4/ip_forward
/sbin/iptables -t nat -A POSTROUTING -o $WAN_INTERFACE -j MASQUERADE
/sbin/iptables -A FORWARD -i $WAN_INTERFACE -o $LAN_INTERFACE -m state --state RELATED,ESTABLISHED -j ACCEPT
/sbin/iptables -A FORWARD -i $LAN_INTERFACE -o $WAN_INTERFACE -j ACCEPT

dnsmasq -d -i $LAN_INTERFACE -C dnsmasq.conf --tftp-root $(realpath $PWD/../build/tmp-glibc/deploy/images/$MACHINE)
