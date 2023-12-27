package com.ohyooo.shared

import kotlinx.browser.window

actual fun getPlatformName() = "WASM"

actual fun openGitHub() {
    window.open(GITHUB_LINK)
}