FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

COMPATIBLE_MACHINE_sds1202xe = ".*"
SRC_URI_append_sds1202xe = " \
        file://devicetree.dts \
        "

COMPATIBLE_MACHINE_sds1104xe = ".*"
SRC_URI_append_sds1104xe = " \
        file://devicetree.dts \
        "
