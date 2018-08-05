LINUX_VERSION = "4.14"
XILINX_RELEASE_VERSION = "v2018.2"
SRCREV ?= "c2ba891326bb472da59b6a2da29aca218d337687"

require recipes-kernel/linux/linux-xlnx.inc

FILESEXTRAPATHS_append := "${THISDIR}/linux-xlnx/4.14:${THISDIR}/linux-xlnx/config:"
SRC_URI_append += " \
        file://scope-features;type=kmeta;destsuffix=scope-features \
        file://0001-xilinx-parallel-display-drm-driver.patch \
        file://0002-add-sds1202xe-poweroff-driver.patch \
        file://0003-add-sds1000xe-frontpanel-led-driver.patch \
        file://0004-add-sds1104xe-poweroff-driver.patch \
        "

KERNEL_FEATURES_append_zynq += " \
    display.scc \
    misc.scc \
    "
