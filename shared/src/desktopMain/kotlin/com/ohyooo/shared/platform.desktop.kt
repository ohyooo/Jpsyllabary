package com.ohyooo.shared

import java.awt.Desktop
import java.net.URI
import java.util.Locale

actual fun getPlatformName() = "Desktop"

actual fun openGitHub() {
    val osName by lazy(LazyThreadSafetyMode.NONE) { System.getProperty("os.name").lowercase(Locale.getDefault()) }
    val desktop = Desktop.getDesktop()
    when {
        Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE) -> desktop.browse(URI(GITHUB_LINK))
        "mac" in osName -> Runtime.getRuntime().exec(arrayOf("open", GITHUB_LINK), null, null)
        "nix" in osName || "nux" in osName -> Runtime.getRuntime().exec(arrayOf("xdg-open", GITHUB_LINK), null, null)
        else -> throw RuntimeException("cannot open $GITHUB_LINK")
    }
}

