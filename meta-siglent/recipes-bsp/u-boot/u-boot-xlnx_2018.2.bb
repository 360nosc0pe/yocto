include recipes-bsp/u-boot/u-boot-xlnx.inc
include recipes-bsp/u-boot/u-boot-spl-zynq-init.inc

XILINX_RELEASE_VERSION = "v2018.2"
SRCREV = "21812b5fd359d8756d619a15b49b6079ae3f9f36"
PV = "v2018.02-xilinx-${XILINX_RELEASE_VERSION}+git${SRCPV}"

SRC_URI_append = " \
        file://0001-add-sds1202xe-support.patch \
        file://0002-add-sds1104xe-support.patch \
        "

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=6;md5=157ab8408beab40cd8ce1dc69f702a6c"
