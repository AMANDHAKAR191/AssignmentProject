package com.aman.assignmentproject.screens

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object PermissionScreen: Screen("permission_screen")
    object AboutScreen: Screen("about_screen")
    object NextScreen: Screen("next_screen")
    object DetailsScreen: Screen("details_screen")
}