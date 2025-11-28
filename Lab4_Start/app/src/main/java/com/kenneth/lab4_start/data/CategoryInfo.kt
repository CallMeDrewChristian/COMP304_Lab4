package com.kenneth.lab4_start.data

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.Fort
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryInfo (
    val title: String,
    val subtitle: String,
    val icon: ImageVector
    )

object CategoryInfoProvider {
    private val categoryInfoMap = mapOf(
        "Landmark" to CategoryInfo (
            "Landmark Sites",
            "Explore Japans Rich History",
            Icons.Default.Home
        ),

        "Park and Nature" to CategoryInfo (
            "Park and Nature",
            "Discover Nature and Beauty",
            Icons.Default.Forest
        ),

        "Museum and Culture" to CategoryInfo (
            "Museum and Culture",
            "Experience Japans Culture",
            Icons.Default.Fort
        ),

        "Shopping" to CategoryInfo (
            "Shopping District",
            "Shop in Style",
            Icons.Default.ShoppingBag
        ),
    )

    fun getCategoryInfo(categoryName: String): CategoryInfo{

        return categoryInfoMap[categoryName] ?: CategoryInfo (
            categoryName, "Please Add Category Detail", Icons.Default.Error
        )

    }
}
