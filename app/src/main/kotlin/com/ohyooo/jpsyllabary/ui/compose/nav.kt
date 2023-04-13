package com.ohyooo.jpsyllabary.ui.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeHistory
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ohyooo.jpsyllabary.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Main() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(64.dp))
                DrawerNavItem(stringResId = R.string.single, route = Route.SINGLE, icon = Icons.Filled.Home, scope, scaffoldState, navController)
                DrawerNavItem(stringResId = R.string.table, route = Route.TABLE, icon = Icons.Filled.ListAlt, scope, scaffoldState, navController)
                DrawerNavItem(stringResId = R.string.twister, route = Route.Twister, icon = Icons.Filled.ChangeHistory, scope, scaffoldState, navController)
                DrawerNavItem(stringResId = R.string.source_code, route = Route.SOURCE, icon = Icons.Filled.DataObject, scope, scaffoldState, navController)
            }
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background,
        drawerBackgroundColor = Color.Transparent,
    ) { innerPaddingModifier ->
        Nav(navController = navController, modifier = Modifier.padding(innerPaddingModifier)) {
            scope.launch { drawerState.open() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavItem(
    @StringRes stringResId: Int,
    route: Route,
    icon: ImageVector,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {
    val selected = navController.currentBackStackEntryAsState().value?.destination?.route == route.value

    NavigationDrawerItem(
        label = { Text(text = stringResource(id = stringResId), color = MaterialTheme.colorScheme.inverseSurface) },
        icon = { Icon(imageVector = icon, contentDescription = stringResource(id = stringResId), tint = MaterialTheme.colorScheme.inverseSurface) },
        selected = selected,
        onClick = {
            navController.navigate(route.value)
            scope.launch { scaffoldState.drawerState.close() }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}

@Composable
fun Nav(navController: NavHostController, modifier: Modifier, onMenuClick: () -> Unit = {}) {
    NavHost(navController = navController, modifier = modifier, startDestination = Route.SINGLE.value) {
        composable(Route.SINGLE.value) { Single(onMenuClick) }
        composable(Route.TABLE.value) { Fragment(onMenuClick) }
        composable(Route.Twister.value) { Twister(onMenuClick) }
        composable(Route.SOURCE.value) { Github(onMenuClick) }
    }
}

enum class Route(val value: String) {
    SINGLE("Single"),
    TABLE("Table"),
    Twister("Twister"),
    SOURCE("Source"),
}