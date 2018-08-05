SUMMARY = "The Siglent scope hardware and fpga definitions"
DESCRIPTION = "Contains the Hardware Design Files and hardware software \
hand-off file. The HDF provides bitstream and Xilinx ps7_init_gpl.c/h \
platform headers."
SECTION = "bsp"

DEPENDS += "unzip-native"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

COMPATIBLE_MACHINE_sds1202xe = ".*"
COMPATIBLE_MACHINE_sds1104xe = ".*"
#COMPATIBLE_MACHINE = "sds1104xe"

SRC_URI = "${HDF_BASE}${HDF_PATH}"
S = "${WORKDIR}"

HDF = "${HDF_PATH}"

PROVIDES = " \
    virtual/bitstream \
    virtual/xilinx-platform-init \
    virtual/ps7-tcl-init \
    "

FILES_${PN}-platform-init += "${PLATFORM_INIT_DIR}/*"
FILES_${PN}-bitstream += "download.bit"
FILES_${PN}-ps7-tcl-init += "ps7_init.tcl"

PACKAGES = " \
    ${PN}-platform-init \
    ${PN}-bitstream \
    ${PN}-ps7-tcl-init \
    "

BITSTREAM ?= "bitstream-${PV}-${PR}.bit"
PS7_INIT ?= "ps7_init-${PV}-${PR}.tcl"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit xilinx-platform-init
inherit deploy

SYSROOT_DIRS += "${PLATFORM_INIT_DIR}"

do_install() {
    fn=$(unzip -l ${S}/${HDF} | awk '{print $NF}' | grep ".bit$")
    unzip -o ${S}/${HDF} ${fn} -d ${D}
    [ "${fn}" == "download.bit" ] || mv ${D}/${fn} ${D}/download.bit

    fn=$(unzip -l ${S}/${HDF} | awk '{print $NF}' | grep "ps7_init.tcl$")
    unzip -o ${S}/${HDF} ${fn} -d ${D}
    [ "${fn}" == "ps7_init.tcl" ] || mv ${D}/${fn} ${D}/ps7_init.tcl

    install -d ${D}${PLATFORM_INIT_DIR}
    for fn in ${PLATFORM_INIT_FILES}; do
        unzip -o ${S}/${HDF} ${fn} -d ${D}${PLATFORM_INIT_DIR}
    done
}

do_deploy () {
    if [ -e ${D}/download.bit ]; then
        install -d ${DEPLOYDIR}
        install -m 0644 ${D}/download.bit ${DEPLOYDIR}/${BITSTREAM}
        install -m 0644 ${D}/ps7_init.tcl ${DEPLOYDIR}/${PS7_INIT}
        ln -sf ${BITSTREAM} ${DEPLOYDIR}/download.bit
        ln -sf ${PS7_INIT} ${DEPLOYDIR}/ps7_init.tcl

        # for u-boot 2016.3 with spl load bitstream patch
        ln -sf ${BITSTREAM} ${DEPLOYDIR}/bitstream
    fi
}
addtask deploy before do_build after do_install
