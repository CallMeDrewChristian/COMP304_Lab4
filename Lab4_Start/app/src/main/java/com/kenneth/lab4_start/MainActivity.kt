package com.kenneth.lab4_start

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kenneth.lab4_start.data.Category
import com.kenneth.lab4_start.data.CategoryInfoProvider
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

@Composable
fun CategoryButton(
    name: String,
    onCategorySelected: (String) -> Unit
){
    val categoryInfo = CategoryInfoProvider
        .getCategoryInfo(name)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(Color(0x80DDDDDD)),
        onClick = {onCategorySelected(name)}
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier.weight(1f)
            ){

                Text(
                    text = categoryInfo.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = categoryInfo.subtitle ,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }

            Icon(
                imageVector = categoryInfo.icon,
                contentDescription = name,
                modifier = Modifier
                    .size(48.dp)
                    .padding(16.dp),
                MaterialTheme.colorScheme.primary
            )
        }
    }
}
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
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.grove),
                contentDescription = "Background Image",
                modifier = Modifier
                    //fillMaxWidth() to be in the bottom
                    .fillMaxHeight()
                    .align(Alignment.BottomCenter),
                //fillMaxWidth() to be in the bottom
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "YOUR PROJECT HEADLINE",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "YOUR PROJECT CAPTION",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.getCategories()) { category ->
                        CategoryButton(
                            name = category,
                            onCategorySelected = onCategorySelected
                        )
                    }
                }
            }

        }

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