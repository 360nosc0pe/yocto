DESCRIPTION = "Siglent SDS1202X-E oscilloscope image with development features enabled"

require recipes-core/images/scope-image.bb

IMAGE_FEATURES += "ssh-server-dropbear tools-sdk tools-debug debug-tweaks dev-pkgs"
