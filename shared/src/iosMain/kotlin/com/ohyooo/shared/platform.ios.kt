package com.ohyooo.shared

actual fun getPlatformName() = "iOS Main"

actual fun openGitHub() {
    println(getPlatformName())
}
