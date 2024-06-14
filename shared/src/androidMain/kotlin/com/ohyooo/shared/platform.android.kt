package com.ohyooo.shared

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import org.jetbrains.compose.resources.FontResource

actual fun getPlatformName() = "Android"

actual fun openGitHub() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(GITHUB_LINK)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(App.context, intent, null)
}

actual fun getFont(): FontResource? = null
