package com.ohyooo.jpsyllabary.ui.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeHistory
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ohyooo.jpsyllabary.R
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
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.single), color = MaterialTheme.colorScheme.inverseSurface) },
                    icon = { Icon(Icons.Filled.Home, stringResource(R.string.single), tint = MaterialTheme.colorScheme.inverseSurface) },
                    selected = navController.currentBackStackEntryAsState().value?.destination?.route == Route.SINGLE.value,
                    onClick = {
                        navController.navigate(Route.SINGLE.value)
                        scope.launch { scaffoldState.drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.table), color = MaterialTheme.colorScheme.inverseSurface) },
                    icon = { Icon(Icons.Filled.ListAlt, stringResource(R.string.table), tint = MaterialTheme.colorScheme.inverseSurface) },
                    selected = navController.currentBackStackEntryAsState().value?.destination?.route == Route.TABLE.value,
                    onClick = {
                        navController.navigate(Route.TABLE.value)
                        scope.launch { scaffoldState.drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.twister), color = MaterialTheme.colorScheme.inverseSurface) },
                    icon = { Icon(Icons.Filled.ChangeHistory, stringResource(R.string.twister), tint = MaterialTheme.colorScheme.inverseSurface) },
                    selected = navController.currentBackStackEntryAsState().value?.destination?.route == Route.Twister.value,
                    onClick = {
                        navController.navigate(Route.Twister.value)
                        scope.launch { scaffoldState.drawerState.close() }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
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

@Composable
fun Nav(navController: NavHostController, modifier: Modifier, onMenuClick: () -> Unit = {}) {
    NavHost(navController = navController, modifier = modifier, startDestination = Route.SINGLE.value) {
        composable(Route.SINGLE.value) { Single(onMenuClick) }
        composable(Route.TABLE.value) { Fragment(onMenuClick) }
        composable(Route.Twister.value) { Twister(onMenuClick) }
    }
}

enum class Route(val value: String) {
    SINGLE("Single"),
    TABLE("Table"),
    Twister("Twister"),
}