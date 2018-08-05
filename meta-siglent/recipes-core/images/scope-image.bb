DESCRIPTION = "Siglent SDS1202X-E oscilloscope image"

IMAGE_FEATURES += "package-management"

CORE_IMAGE_EXTRA_INSTALL += "\
    kernel-dev \
    "

CORE_IMAGE_EXTRA_INSTALL += "screen"
CORE_IMAGE_EXTRA_INSTALL += "libstdc++-dev"
CORE_IMAGE_EXTRA_INSTALL += "libc6-dev"
CORE_IMAGE_EXTRA_INSTALL += "i2c-tools"

inherit core-image
