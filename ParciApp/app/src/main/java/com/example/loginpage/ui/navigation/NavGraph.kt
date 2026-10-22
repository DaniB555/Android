package com.example.loginpage.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.example.loginpage.*
import com.example.loginpage.ui.screens.*

private data class BottomNavItem(val route: Any, val label: String)

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavItem(route = Home, label = "Home"),
        BottomNavItem(route = Profile(), label = "Profile"),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.hierarchy?.any {
        it.hasRoute(MainGraph::class)
    } == true

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { (route, label) ->
                        val selected = currentDestination?.hierarchy?.any {
                            it.hasRoute(route::class)
                        } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(route) {
                                    popUpTo<MainGraph> { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Text(text = label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Login,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Login> {
                LoginScreen {
                    navController.navigate(MainGraph) {
                        popUpTo<Login> { inclusive = true }
                    }
                }
            }
            navigation<MainGraph>(startDestination = Home) {
                composable<Home> {
                    HomeScreen { userId, userName ->
                        navController.navigate(Profile(userId, userName))
                    }
                }
                composable<Profile> { backStackEntry ->
                    val profile: Profile = backStackEntry.toRoute()
                    ProfileScreen(userId = profile.userId, userName = profile.userName)
                }
            }
        }
    }
}
