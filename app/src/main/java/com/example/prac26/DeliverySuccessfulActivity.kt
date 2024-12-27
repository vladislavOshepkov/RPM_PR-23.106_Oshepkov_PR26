package com.example.prac26

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext

class DeliverySuccessfulActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeliverySuccessfulScreen()
        }
    }
}

@Composable
fun DeliverySuccessfulScreen() {
    var isLoading by remember { mutableStateOf(true) }
    val rotation = remember { Animatable(0f) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
        )
        isLoading = false
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                Image(
                    painter = painterResource(id = R.drawable.loading),
                    contentDescription = "Loading",
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            rotationZ = -rotation.value
                        }
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.done),
                    contentDescription = "Done",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "Delivery Successful",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = "Your Item has been delivered successfully",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .animateContentSize()
            ) {
                if (!isLoading) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Rate Rider", color = Color(0xFF007AFF))
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            repeat(5) {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = "star",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(end = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Add feedback") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.feedback),
                        contentDescription = "Feedback Icon",
                        tint = Color.Unspecified
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .animateContentSize()
            )
            Button(
                onClick = {
                    val intent = Intent(context, TrackingPackageActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .animateContentSize(),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
            ) {
                Text("Done", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    DeliverySuccessfulScreen()
}