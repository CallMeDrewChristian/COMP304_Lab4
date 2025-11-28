package com.kenneth.lab4_start

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kenneth.lab4_start.data.Category
import com.kenneth.lab4_start.data.places
import com.kenneth.lab4_start.ui.theme.Lab4_StartTheme
import com.kenneth.lab4_start.ui.views.MapScreen
import com.kenneth.lab4_start.ui.views.PlacesScreen
import com.kenneth.lab4_start.viewModel.PlacesViewModel
import org.koin.androidx.compose.koinViewModel

enum class AppScreen{
    Home,
    Places,
    Map
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4_StartTheme {
                PlacesApp()
            }
        }
    }
}

// LazyColumn(
// verticalArrangement = Arrangement.spacedBy(space = 8.dp)
// ){
//  item()
// }


@Composable
fun PlacesApp() {
    val navController = rememberNavController()
    val placesViewModel : PlacesViewModel = koinViewModel()
    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.name
    ){
        composable(route = AppScreen.Home.name) {
            MainActivityContent (
                viewModel = placesViewModel,
                onCategorySelected = {
                    navController.navigate(AppScreen.Places.name + "/${it}")
                }
            )
        }

        composable(
            route = AppScreen.Places.name + "/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = ""
                }
            )
        ){
            val category = it.arguments?.getString("category")
            PlacesScreen(
                viewModel = placesViewModel,
                category = Category.valueOf(category!!),
                onPlaceSelected = {index ->
                    navController.navigate(AppScreen.Map.name + "/$index")
                },
                onBackButtonPressed = {
                    navController.navigate(AppScreen.Home.name)
                }

            )
        }

        composable(
            route = AppScreen.Map.name + "/{index}",
            arguments = listOf(
            navArgument("index") {
                type = NavType.IntType
                nullable = false
                defaultValue = 1
            }
            )
        ) {
            val index = it.arguments?.getInt("index")
            val place = placesViewModel.getPlace(index ?: 0)
            MapScreen(
                place = place,
                onBackButtonPressed = {
                    navController.navigate(AppScreen.Places.name + "/${place.category}")
                }
            )
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainActivityContent(
    viewModel: PlacesViewModel,
    onCategorySelected: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
            //need to change
            title = "YOUR PROJECT TITLE",
            showBackButton = false
            )
        }
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean,
    onBackButtonPressed: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },

        navigationIcon = {
            if(showBackButton){
                IconButton(onClick = onBackButtonPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back Button"
                    )
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}