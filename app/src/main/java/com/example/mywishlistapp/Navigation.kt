package com.example.mywishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable

fun Navigation (viewModel: WishViewModel = viewModel(),
                navController: NavHostController = rememberNavController()){

  NavHost(
     navController = navController,
     startDestination = Screen.HomeScreen.route
 ){
    composable(route = Screen.HomeScreen.route){
       HomeView(navController,viewModel)
    }
      composable(route = Screen.AddScreen.route){
//        HomeView()
        AddEditDetailView(id = 0L, viewModel = viewModel, navController =navController )
    }
      
 }
}