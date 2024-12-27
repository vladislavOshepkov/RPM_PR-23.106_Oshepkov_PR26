package com.example.prac26

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ChatsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatsScreen()
        }
    }
}

data class Driver(
    val name: String,
    val message: String,
    val notificationCount: Int? = null,
    val profileImage: Int
)

val drivers = listOf(
    Driver("John Joshua", "Thanks for your service", 1, R.drawable.driver1),
    Driver("Chinonso James", "Alright, I wll be waiting", null, R.drawable.driver2),
    Driver("Raph Ron", "Thanks for your service", 5, R.drawable.driver3),
    Driver("Joy Ezekiel", "Thanks for your service", null, R.drawable.driver4),
    Driver("Joy Ezekiel", "Thanks for your service", 1, R.drawable.driver5)
)

@Composable
fun ChatsScreen() {
    var searchText by remember { mutableStateOf("") }
    val filteredDrivers = remember(searchText) {
        drivers.filter { driver ->
            driver.name.contains(searchText, ignoreCase = true)
        }
    }
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=20.dp)
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        val context = LocalContext.current
                        IconButton(
                            onClick = {
                                val intent = Intent(context, MainActivity::class.java)

                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                                context.startActivity(intent)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back",
                                modifier = Modifier.size(30.dp),
                                tint = Color.Unspecified
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        ) {
                            Text(
                                text = "Chats",
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.align(Alignment.Center)
                                    .padding(end = 60.dp)
                            )
                        }
                    }
                }
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            SearchAppBar(searchText = searchText) { text ->
                searchText = text
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                items(filteredDrivers) { driver ->
                    DriverCard(driver = driver)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(searchText: String, onSearchTextChanged: (String) -> Unit) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = {
            Text(text = "Search for a driver", color = Color.Gray)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon", tint = Color.Gray)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    )
}


@Composable
fun DriverCard(driver: Driver) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = driver.profileImage),
                contentDescription = "Driver Profile",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = driver.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = driver.message, color = Color.Black, fontSize = 14.sp)
            }

            driver.notificationCount?.let {
                NotificationBadge(count = it)
            }
        }
    }
}

@Composable
fun NotificationBadge(count: Int) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFF0560FA)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = count.toString(), color = Color.White, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatsScreen()
}