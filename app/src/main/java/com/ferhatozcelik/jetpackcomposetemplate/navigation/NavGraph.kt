package com.ferhatozcelik.jetpackcomposetemplate.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ferhatozcelik.jetpackcomposetemplate.ui.create.CreateScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.HomeViewModel
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                navController = navController,
                viewModel = HomeViewModel()
            )
        }
        composable(Screen.Create.route) {
            CreateScreen(navController = navController)
        }
    }
}
