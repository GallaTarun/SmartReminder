package com.example.smartreminder.ui.home

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object AddEditScreen: Screen("add_edit")
    object SettingsScreen: Screen("settings")
}