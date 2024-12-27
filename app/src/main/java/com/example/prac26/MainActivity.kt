package com.example.prac26

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar (containerColor = Color.White, modifier = Modifier.shadow(45.dp)) {
                val items = listOf("Home", "Wallet", "Track", "Profile")
                val icons = listOf(
                    R.drawable.ic_home,
                    R.drawable.ic_wallet,
                    R.drawable.ic_track,
                    R.drawable.ic_profile
                )

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(icons[index]),
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            when (index) {
                                0 -> {}
                                1 -> {
                                    val intent = Intent(context, WalletActivity::class.java)

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                                    context.startActivity(intent)
                                }
                                2 -> {
                                    val intent = Intent(context, TrackingPackageActivity::class.java)

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                                    context.startActivity(intent)
                                }
                                3 -> {}
                            }
                        },
                        alwaysShowLabel = true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF007AFF),
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = Color(0xFF007AFF),
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            HomeContent()
        }
    }
}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        HeaderSection()

        Spacer(modifier = Modifier.height(32.dp))

        SpecialForYouSection()

        Spacer(modifier = Modifier.height(32.dp))

        WhatWouldYouLikeToDoSection()
    }
}

@Composable
fun HeaderSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF0560FA))
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Hello Ken!",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "We trust you are having a great time",
                        style = TextStyle(color = Color.White, fontSize = 14.sp)
                    )
                }

                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )

            }
        }
    }

}

@Composable
fun SpecialForYouSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Special for you",
            style = TextStyle(
                color = Color(0xFFEC8000),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow {
            item{
                CardItem(R.drawable.card1)
            }
            item {
                CardItem(R.drawable.card2)
            }
            item {
                CardItem(R.drawable.card3)
            }
            item {
                CardItem(R.drawable.card4)
            }
        }

    }
}


@Composable
fun CardItem(imageRes :Int){
    Box( modifier = Modifier.padding(end = 8.dp)){
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(166.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Card Background",
                modifier = Modifier
                    .height(64.dp)
                    .width(166.dp),
                contentScale = ContentScale.Crop,
            )

        }
    }
}

@Composable
fun WhatWouldYouLikeToDoSection() {
    val context = LocalContext.current
    var selectedCardIndex by remember { mutableIntStateOf(-1) }
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "What would you like to do",
            style = TextStyle(
                color = Color(0xFF0560FA),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ButtonWithIcon(
                iconRes = R.drawable.ic_customer_care,
                title = "Customer Care",
                description = "Our customer care service line is available from 8-9pm week days and 9 - 5 weekends-tap to call us today",
                modifier = Modifier.weight(1f),
                isSelected = selectedCardIndex == 0,
                onCardClick = { selectedCardIndex = if (selectedCardIndex == 0) -1 else 0 }
            )
            ButtonWithIcon(
                iconRes = R.drawable.ic_send_package,
                title = "Send a package",
                description = "Request for a driver to pick up or deliver your package for you",
                modifier = Modifier.weight(1f),
                isSelected = selectedCardIndex == 1,
                onCardClick = { selectedCardIndex = if (selectedCardIndex == 1) -1 else 1 }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ButtonWithIcon(
                iconRes = R.drawable.ic_fund_wallet,
                title = "Fund your wallet",
                description = "To fund your wallet is as easy as ABC, make use of our fast technology and top-up your wallet today",
                modifier = Modifier.weight(1f),
                isSelected = selectedCardIndex == 2,
                onCardClick = { selectedCardIndex = if (selectedCardIndex == 2) -1 else 2 }
            )
            ButtonWithIcon(
                iconRes = R.drawable.ic_chats,
                title = "Chats",
                description = "Search for available rider within your area",
                modifier = Modifier.weight(1f),
                isSelected = selectedCardIndex == 3,
                onCardClick = {
                    if (selectedCardIndex != 3) {
                        selectedCardIndex = 3
                        val intent = Intent(context, ChatsActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        context.startActivity(intent)
                    } else{
                        selectedCardIndex = -1
                    }
                }

            )
        }
    }
}


@Composable
fun ButtonWithIcon(
    iconRes: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onCardClick: () -> Unit

) {

    val backgroundColor = if(isSelected) Color(0xFF0560FA) else Color(0xFFF0F0F0)
    val textColor = if(isSelected) Color.White else Color(0xFF000000)
    val iconColor = if(isSelected) Color.White else Color(0xFF0560FA)


    Card(
        modifier = modifier
            .height(150.dp)
            .clickable {
                onCardClick()
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )

    ) {

        Box(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    style = TextStyle(
                        color = iconColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = description,
                    style = TextStyle(
                        color = textColor,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}