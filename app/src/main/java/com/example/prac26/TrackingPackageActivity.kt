package com.example.prac26

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight

class TrackingPackageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackingApp()
        }
    }
}

@Composable
fun TrackingApp() {
    var selectedItem by remember { mutableIntStateOf(2) }
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
                                0 -> {
                                    val intent = Intent(context, MainActivity::class.java)

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                                    context.startActivity(intent)
                                }
                                1 -> {
                                    val intent = Intent(context, WalletActivity::class.java)

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                                    context.startActivity(intent)
                                }
                                2 -> {}
                                3 -> {}
                            }
                        },
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
        TrackingPackageScreen(
            modifier = Modifier.padding(innerPadding).statusBarsPadding()
        )
    }
}

@Composable
fun TrackingPackageScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        MapSection(modifier = Modifier.fillMaxWidth())

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Tracking Number",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Icon(
                    painter = painterResource(id = R.drawable.tracking_number),
                    contentDescription = "Track number",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "R-7458-4567-4434-5854",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = Color(0xFF007AFF)
                )
            }

            Text(
                text = "Package Status",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Gray
            )

            val statuses = listOf(
                "Courier requested - July 7 2022, 08:00am",
                "Package ready for delivery - July 7 2022, 08:30am",
                "Package in transit - July 7 2022, 10:30am",
                "Package delivered - July 7 2022, 03:30am"
            )

            statuses.forEach { status ->
                val parts = status.split(" - ")
                val action = parts[0]
                val timestamp = parts[1]

                Row(modifier = Modifier.padding(4.dp)) {
                    Checkbox(
                        checked = true,
                        onCheckedChange = {},
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFF007AFF))
                    )

                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = action,
                            fontSize = 14.sp,
                            color = Color(0xFF007AFF)
                        )
                        Text(
                            text = timestamp,
                            fontSize = 14.sp,
                            color = Color(0xFFEC8000)
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    val intent = Intent(context, DeliverySuccessfulActivity::class.java)

                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007AFF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = "View Package Info")
            }
        }
    }
}

@Composable
fun MapSection(modifier: Modifier = Modifier) {
    val mapHtml = """
        <iframe 
            width="100%" 
            height="300"
            style="border:0"
            loading="lazy"
            allowfullscreen
            src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d3132.684363888948!2d82.92001090072053!3d55.042430811363644!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sus!4v1734277270736!5m2!1sen!2sus"
        ></iframe>
    """.trimIndent()

    AndroidView(
        factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadDataWithBaseURL(null, mapHtml, "text/html", "UTF-8", null)
            }
        },
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun TrackingPackageScreenPreview() {
    TrackingApp()
}