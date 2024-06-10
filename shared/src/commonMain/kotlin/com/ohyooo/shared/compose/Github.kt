package com.ohyooo.shared.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import com.ohyooo.shared.common.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ohyooo.shared.openGitHub

@Composable
fun Github(onMenuClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "menu",
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
                .clickable {
                    onMenuClick()
                },
            tint = MaterialTheme.colorScheme.inverseSurface
        )

        // val context = LocalContext.current
        Column(modifier = Modifier
            .padding(start = 8.dp)
            .clickable {
                openGitHub()
            }
        ) {
            Text(
                text = "GitHub",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.inverseSurface,
                textAlign = TextAlign.Center
            )

            HorizontalDivider()

            Text(
                text = "ohyooo/JPSyllabary",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.inverseSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}
