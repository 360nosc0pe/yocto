LINUX_VERSION = "4.9"
XILINX_RELEASE_VERSION = "v2017.3"
#NOTE: that's newer than 2017.3, but we just treat it as a 2017.3 release
SRCREV ?= "bd8f87ca6a415c7d90615ddaa683bdbf781114bc"

include recipes-kernel/linux/linux-xlnx.inc

FILESEXTRAPATHS_append := "${THISDIR}/linux-xlnx/4.9:${THISDIR}/linux-xlnx/config:"
SRC_URI_append += " \
        file://scope-features;type=kmeta;destsuffix=scope-features \
        file://0001-xilinx-parallel-display-drm-driver.patch \
        file://0002-revert-xilinx-gpio-keys-changed.patch \
        file://0003-add-sds1202xe-poweroff-driver.patch \
        file://0004-add-sds1000xe-frontpanel-led-driver.patch \
        file://0005-add-sds1104xe-poweroff-driver.patch \
        "

KERNEL_FEATURES_append_zynq += " \
    display.scc \
    misc.scc \
    "
