package com.ohyooo.common

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity

actual fun getPlatformName() = "Android"

actual fun openGitHub() {
    val url = "https://github.com/ohyooo/JPSyllabary-KMM"
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(App.context, intent, null)
}