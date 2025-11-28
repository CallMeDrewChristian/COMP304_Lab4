package com.kenneth.lab4_start.data

import com.kenneth.lab4_start.R

//make 16 places
//use png pictures and convert them


val places = listOf(
    //LandMark
    Place(
        name = "Tokyo SkyTree",
        latitude = 35.7100627,
        longitude = 139.8107004,
        address = "1 Chome-1-2 Oshiage, Sumida City, Tokyo 131-0045, Japan",
        //place picture file
        image = R.drawable.tokyoskytree, // min Width / Height > 800 px
        category = Category.Landmark,
    ),

    Place(
        name = "Tokyo Tower",
        latitude = 35.6585805,
        longitude = 139.6692152,
        address = "4 Chome-2-8 Shibakoen, Minato City, Tokyo 105-0011, Japan",
        //place picture file
        image = R.drawable.tokyotower, // min Width / Height > 800 px
        category = Category.Landmark,
),
    Place(
        name = "Shibuya Scramble Crossing",
        latitude = 35.6607397,
        longitude = 139.6373931,
        address = "Shibuya, Tokyo, Japan",
        //place picture file
        image = R.drawable.shibuyascramblecrossing, // min Width / Height > 800 px
        category = Category.Landmark,
    ),

    Place(
        name = "Omoide Yokocho Memory Lane",
        latitude = 35.6607397,
        longitude = 139.6373931,
        address = "1 Chome-2 Nishishinjuku, Shinjuku City, Tokyo 160-0023, Japan",
        //place picture file
        image = R.drawable.memorylane, // min Width / Height > 800 px
        category = Category.Landmark,
    ),

    //Park

    Place(
        name = "Metasequoia Grove",
        latitude = 35.7799012,
        longitude = 139.8447965,
        address = "6-1 Mizumotokoen, Katsushika City, Tokyo 125-0034, Japan",
        //place picture file
        image = R.drawable.grove, // min Width / Height > 800 px
        category = Category.Landmark,
    ),

    Place(
        name = "Akatsuki Port Park",
        latitude = 35.6462354,
        longitude = 139.8102925,
        address = "3 Chome Aomi, Koto City, Tokyo 135-0064, Japan",
        //place picture file
        image = R.drawable.akatsukipark, // min Width / Height > 800 px
        category = Category.Park,
    ),
)