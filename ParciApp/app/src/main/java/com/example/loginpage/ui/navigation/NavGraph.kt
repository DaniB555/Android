package com.example.loginpage.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.*
import com.example.loginpage.*
import com.example.loginpage.ui.screens.*
import com.example.loginpage.utils.SessionManager

private data class BottomNavItem(val route: Any, val label: String)

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sessionManager = remember { SessionManager.getInstance(context) }

    val startDestination = remember {
        if (sessionManager.isLoggedIn) MainGraph else Login
    }

    val bottomNavItems = listOf(
        BottomNavItem(route = Home, label = "Home"),
        BottomNavItem(route = Profile, label = "Profile"),
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
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Login> {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(MainGraph) {
                            popUpTo<Login> { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate(Register)
                    }
                )
            }
            composable<Register> {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate(MainGraph) {
                            popUpTo<Login> { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.popBackStack()
                    }
                )
            }
            navigation<MainGraph>(startDestination = Home) {
                composable<Home> {
                    HomeScreen(
                        onLogout = {
                            navController.navigate(Login) {
                                popUpTo<MainGraph> { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }
                composable<Profile> {
                    ProfileScreen()
                }
            }
        }
    }
}
