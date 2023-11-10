package com.steccos.marsroverapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.steccos.marsroverapp.nav.Action
import com.steccos.marsroverapp.nav.Navigation.Home
import com.steccos.marsroverapp.nav.Navigation.Manifest
import com.steccos.marsroverapp.nav.Navigation.Photo
import com.steccos.marsroverapp.nav.Navigation.Saved
import com.steccos.marsroverapp.nav.Screen
import com.steccos.marsroverapp.ui.theme.MarsRoverAppTheme
import com.steccos.marsroverapp.ui.view.ManifestScreen
import com.steccos.marsroverapp.ui.view.PhotoListSavedScreen
import com.steccos.marsroverapp.ui.view.PhotoScreen
import com.steccos.marsroverapp.ui.view.RoverList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NAvCompose() {
    val navController = rememberNavController()
    val actions = remember(navController) {Action(navController)}

    val items = listOf(
        Screen.Home,
        Screen.Saved
    )

    MarsRoverAppTheme {
        Scaffold(bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach{ screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(id = screen.drawableId),
                                contentDescription = stringResource(screen.resourceId)
                    ) },
                        label = {
                            Text(text = stringResource(id = screen.resourceId))
                        },
                        selected = currentDestination?.hierarchy?.any {
                            if (it.route?.contains("manifest") == true || it.route?.contains("photo") == true) {
                                screen.route == "home"
                            } else {
                                it.route == screen.route
                            }
                            } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                    )
                }
            }
        }




        ) { innerPadding ->

            val modifier = Modifier.padding(innerPadding)
        NavHost(navController = navController, startDestination = Home) {
            composable(Home) {
                RoverList(
                    modifier = modifier
                ) { roverName ->
                    actions.manifest(roverName)

                }
            }
            composable(Manifest) { backStackEntry ->
                ManifestScreen(
                    modifier = modifier,
                    roverName = backStackEntry.arguments?.getString("roverName"),
                    marsRoverManifestViewModel = hiltViewModel(),
                    onClick = { roverName, sol ->
                        actions.photo(roverName, sol)
                    }
                )
            }
            composable(Photo) { backStackEntry ->
                PhotoScreen(
                    modifier = modifier,
                    roverName = backStackEntry.arguments?.getString("roverName"),
                    sol = backStackEntry.arguments?.getString("sol"),
                    marsRoverPhotoViewModel = hiltViewModel()
                )
            }
            composable(Saved) {
                PhotoListSavedScreen(
                    modifier = modifier
                )
            }
        }
    }
    }
}