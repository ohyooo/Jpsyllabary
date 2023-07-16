package com.ohyooo.common

import java.awt.Desktop
import java.net.URI
import java.util.Locale

actual fun getPlatformName() = "Desktop"

actual fun openGitHub() {
    val uri = URI("https://github.com/ohyooo/JPSyllabary-KMM")
    val osName by lazy(LazyThreadSafetyMode.NONE) { System.getProperty("os.name").lowercase(Locale.getDefault()) }
    val desktop = Desktop.getDesktop()
    when {
        Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE) -> desktop.browse(uri)
        "mac" in osName -> Runtime.getRuntime().exec("open $uri")
        "nix" in osName || "nux" in osName -> Runtime.getRuntime().exec("xdg-open $uri")
        else -> throw RuntimeException("cannot open $uri")
    }
}

