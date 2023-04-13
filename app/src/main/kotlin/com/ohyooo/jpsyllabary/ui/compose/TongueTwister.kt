package com.ohyooo.jpsyllabary.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.PagerScope
import com.ohyooo.jpsyllabary.model.twisterList
import kotlinx.coroutines.launch

@Preview
@Composable
fun Twister(onMenuClick: () -> Unit = {}) {
    Column {
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

        var reset by remember { mutableStateOf(0L) }

        Divider()

        val scope = rememberCoroutineScope()

        repeat(twisterList.size) { column ->
            val state = androidx.compose.foundation.pager.rememberPagerState()
            if (reset > 0L) {
                scope.launch { state.animateScrollToPage(0) }
            }
            Modifier
                .weight(1F)
            PaddingValues(0.dp)
            PagerDefaults.flingBehavior(
                state = state,
                endContentPadding = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
            )
            fun PagerScope.(row: Int) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .fillMaxWidth()
                ) {
                    val s = twisterList[column][row].joinToString("")
                    Box(
                        modifier = Modifier.weight(1F),
                        contentAlignment = Alignment.Center
                    ) {
                        AutoResizeText(
                            text = s,
                            fontSizeRange = FontSizeRange(8.sp, 24.sp),
                            color = MaterialTheme.colorScheme.inverseSurface,
                            textAlign = TextAlign.Justify,
                            maxLines = 2,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }

            Divider()
        }
        ClickButton(modifier = Modifier.weight(4F)) {
            reset++
        }
    }
}