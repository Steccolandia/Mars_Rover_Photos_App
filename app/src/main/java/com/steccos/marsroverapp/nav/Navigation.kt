package com.steccos.marsroverapp.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.steccos.marsroverapp.R
import com.steccos.marsroverapp.nav.Navigation.Home

object Navigation {
    const val Home = "home"
    const val Manifest = "manifest/{roverName}"
    const val Photo = "photo/{roverName}?sol={sol}"
    const val Saved = "saved"
}

class Action(navController: NavController) {
    val home: () -> Unit = { navController.navigate(Home)}
    val manifest: (roverName: String) -> Unit =
        { roverName ->
            navController.navigate("manifest/${roverName}")
        }
    val photo: (roverName: String, sol: String) -> Unit =
        { roverName, sol ->
            navController.navigate("photo/${roverName}?sol=${sol}")
        }
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val drawableId: Int
) {
    object Home: Screen("home", R.string.rover, R.drawable.ic_mars_rover)
    object Saved: Screen("saved", R.string.saved, R.drawable.baseline_save_24)
}