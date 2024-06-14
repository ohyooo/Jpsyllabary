package com.ohyooo.shared

import com.ohyooo.shared.generated.resources.Res
import com.ohyooo.shared.generated.resources.source_han_sans_sc
import kotlinx.browser.window
import org.jetbrains.compose.resources.FontResource

actual fun getPlatformName() = "WASM"

actual fun openGitHub() {
    window.open(GITHUB_LINK)
}

actual fun getFont(): FontResource? = Res.font.source_han_sans_sc
