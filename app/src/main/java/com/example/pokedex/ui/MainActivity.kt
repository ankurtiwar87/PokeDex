package com.example.pokedex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType // For NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.PokemonList.PokemonListScreen
import com.example.pokedex.ui.theme.PokeDexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDexTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "pokemon_list_screen") {

                    // First Screen: Pokemon List Screen
                    composable(route = "pokemon_list_screen") {
                        PokemonListScreen(navController = navController)
                    }

                    // Second Screen: Pokemon Detail Screen (with arguments)
                    composable(
                        route = "pokemon_detail_screen/{DominantColor}/{pokemonName}",
                        arguments = listOf(
                            navArgument("DominantColor") {
                                type = NavType.IntType
                            },
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            }
                        )
                    ) { backStackEntry ->
                        // Extract arguments
                        val dominantColor = remember {
                            val color = backStackEntry.arguments?.getInt("DominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }

                        val pokemonName = remember {
                            backStackEntry.arguments?.getString("pokemonName")
                        }

                        // Pass arguments to the detail screen
                        // PokemonDetailScreen(dominantColor, pokemonName)
                    }
                }
            }
        }
    }
}