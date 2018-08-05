#set machine "sds1202xe"
set machine "sds1104xe"

connect
targets

targets -set -filter {name =~ "ARM*#0"}
rst -system

#configure fpga
fpga -f /project/yocto/build/tmp-glibc/deploy/images/$machine/download.bit

#run ps7_init
source /project/yocto/build/tmp-glibc/deploy/images/$machine/ps7_init.tcl
ps7_init
ps7_post_config

#download u-boot
dow /project/yocto/build/tmp-glibc/deploy/images/$machine/u-boot.elf
con
