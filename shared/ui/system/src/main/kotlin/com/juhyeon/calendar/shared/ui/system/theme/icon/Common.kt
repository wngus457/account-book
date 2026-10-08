package com.juhyeon.calendar.shared.ui.system.theme.icon

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.juhyeon.calendar.shared.ui.system.R

val CommonArrowBack = Icons.AutoMirrored.Filled.KeyboardArrowLeft
val CommonArrowForward = Icons.AutoMirrored.Filled.KeyboardArrowRight
val CommonClose = Icons.Filled.Close
val CommonBack = Icons.Filled.ArrowBackIosNew

val BottomNavSelectedHome = Icons.Filled.Home
val BottomNavUnSelectedHome = Icons.Outlined.Home
val BottomNavSelectedSetting = Icons.Filled.Settings
val BottomNavUnSelectedSetting = Icons.Outlined.Settings
val BottomNavSelectedHistory = Icons.Filled.Timer
val BottomNavUnSelectedHistory = Icons.Outlined.Timer

// icons
val IconAirplane = R.drawable.ic_airplane
val IconApartment = R.drawable.ic_apartment
val IconBaby = R.drawable.ic_baby
val IconBook = R.drawable.ic_book
val IconCar = R.drawable.ic_car
val IconCart = R.drawable.ic_cart
val IconExercise = R.drawable.ic_exercise
val IconGame = R.drawable.ic_game
val IconMeal = R.drawable.ic_meal
val IconMedical = R.drawable.ic_medical
val IconMoney = R.drawable.ic_money
val IconPizza = R.drawable.ic_pizza
val IconSavings = R.drawable.ic_savings
val IconStock = R.drawable.ic_stock
val IconTrophy = R.drawable.ic_trophy

// Category.icon 키 → 아이콘
fun categoryIcon(key: String): Int = when (key) {
    "airplane" -> IconAirplane
    "apartment" -> IconApartment
    "baby" -> IconBaby
    "book" -> IconBook
    "car" -> IconCar
    "cart" -> IconCart
    "exercise" -> IconExercise
    "game" -> IconGame
    "meal" -> IconMeal
    "medical" -> IconMedical
    "money" -> IconMoney
    "pizza" -> IconPizza
    "savings" -> IconSavings
    "stock" -> IconStock
    "trophy" -> IconTrophy
    else -> IconMoney
}

@Preview(showBackground = true)
@Composable
private fun CommonIconsPreview() {
    Column {
        Icon(imageVector = CommonArrowBack, contentDescription = "CommonArrowBack")
        Icon(imageVector = CommonArrowForward, contentDescription = "CommonArrowForward")
    }
}