package com.ohyooo.shared.compose.multifab

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ripple.createRippleModifierNode
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import com.ohyooo.shared.common.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MultiFloatingActionButton(
    fabIcon: ImageVector,
    items: List<MultiFabItem>,
    toState: MultiFabState,
    showLabels: Boolean = true,
    stateChanged: (fabState: MultiFabState) -> Unit,
    modifier: Modifier,
    onFabItemClicked: (item: MultiFabItem) -> Unit,
) {
    val transition: Transition<MultiFabState> = updateTransition(targetState = toState, label = "")
    val scale: Float by transition.animateFloat(label = "") { state ->
        if (state == MultiFabState.EXPANDED) 56f else 0f
    }
    val alpha: Float by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 50)
        }, label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 1f else 0f
    }
    val shadow: Dp by transition.animateDp(
        transitionSpec = {
            tween(durationMillis = 50)
        }, label = ""
    ) { state ->
        if (state == MultiFabState.EXPANDED) 2.dp else 0.dp
    }
    val rotation: Float by transition.animateFloat(label = "") { state ->
        if (state == MultiFabState.EXPANDED) 45f else 0f
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        items.forEach { item ->
            MiniFabItem(item, alpha, shadow, scale, showLabels, onFabItemClicked)
            Spacer(modifier = Modifier.height(8.dp))
        }
        FloatingActionButton(
            onClick = {
                stateChanged(
                    if (transition.currentState == MultiFabState.EXPANDED) {
                        MultiFabState.COLLAPSED
                    } else MultiFabState.EXPANDED
                )
            },
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(50, 50, 50, 50)
        ) {
            Icon(
                imageVector = fabIcon,
                modifier = Modifier.rotate(rotation),
                contentDescription = "fabIcon",
                tint = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Composable
private fun MiniFabItem(
    item: MultiFabItem,
    alpha: Float,
    shadow: Dp,
    ignoredScale: Float,
    showLabel: Boolean,
    onFabItemClicked: (item: MultiFabItem) -> Unit,
) {
    val fabColor = MaterialTheme.colorScheme.secondaryContainer
    val shadowColor = MaterialTheme.colorScheme.tertiaryContainer
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        if (showLabel) {
            Text(
                item.label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .alpha(animateFloatAsState(targetValue = alpha).value)
                    .shadow(animateDpAsState(targetValue = shadow).value)
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .alpha(alpha)
                .background(fabColor)
                .padding(4.dp)
                .clickable(
                    onClick = { onFabItemClicked(item) },
                )
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier
                    .alpha(alpha)
                    .align(Alignment.Center),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
private fun Minimal(
    item: MultiFabItem,
    alpha: Float,
    scale: Float,
    onFabItemClicked: (item: MultiFabItem) -> Unit,
) {
    val fabColor = MaterialTheme.colorScheme.secondary
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .alpha(alpha)
            .background(fabColor)
            .clickable(
                onClick = { onFabItemClicked(item) },
            )
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            modifier = Modifier
                .alpha(alpha)
                .align(Alignment.Center)
        )
    }
}