package com.ohyooo.jpsyllabary.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ohyooo.jpsyllabary.ui.compose.Main
import com.ohyooo.jpsyllabary.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Main()
            }
        }
    }

}
