package com.ohyooo.common.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ohyooo.common.res.R
import com.ohyooo.common.viewmodel.SingleViewModel

@Composable
fun Single(onMenuClick: () -> Unit = {}) {
    Icon(imageVector = Icons.Default.Menu, tint = MaterialTheme.colorScheme.inverseSurface, contentDescription = "menu", modifier = Modifier
        .padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
        .clickable {
            onMenuClick()
        })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var type by rememberSaveable { mutableStateOf(R.strings.katakanaWithVoiceless) }
        var character by rememberSaveable { mutableStateOf("あ") }
        var hint by rememberSaveable { mutableStateOf("a") }
        var hintVisible by rememberSaveable { mutableStateOf(false) }

        val onClick: () -> Unit = {
            SingleViewModel.get().apply {
                type = title
                character = kana
                hint = pron
                hintVisible = false
            }
        }

        val onHintClick: () -> Unit = { hintVisible = !hintVisible }

        Type(modifier = Modifier.weight(1F), type)

        Divider(color = Color.Gray, thickness = 1.dp)

        Character(modifier = Modifier.weight(2F), character)

        Divider(color = Color.Gray, thickness = 1.dp)

        Hint(modifier = Modifier.weight(2F), hint, hintVisible, onHintClick)

        Divider(color = Color.Gray, thickness = 1.dp)

        ClickButton(modifier = Modifier.weight(2F), onClick)
    }
}

@Composable
fun Type(modifier: Modifier, text: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = MaterialTheme.colorScheme.inverseSurface,
            fontSize = 18.sp,
            textAlign = TextAlign.End,
        )
    }
}

@Composable
fun Character(modifier: Modifier, text: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 100.sp, color = MaterialTheme.colorScheme.inverseSurface, modifier = modifier)
    }
}

@Composable
fun Hint(modifier: Modifier, text: String, visible: Boolean, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (visible) {
            Text(text = text, fontSize = 80.sp, color = MaterialTheme.colorScheme.inverseSurface, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClickButton(modifier: Modifier, onClick: () -> Unit) {
    Image(
        painter = rememberVectorPainter(Icons.Default.Circle),
        contentDescription = "Next",
        contentScale = ContentScale.Inside,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    )
}

