package com.example.prac26

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class WalletActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalletScreen()
        }
    }
}

@Composable
fun WalletScreen() {
    var selectedItem by remember { mutableIntStateOf(1) }
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
                                1 -> {}
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
            WalletContent()
        }
    }
}

@Composable
fun WalletContent() {
    val context = LocalContext.current

    val transactions = listOf(
        Transaction(-3000.00, "Delivery fee", "July 7, 2022"),
        Transaction(2000.00, "Lalalalala", "July 4, 2022"),
        Transaction(-3000.00, "Third Delivery", "July 1, 2022"),
        Transaction(1000.00, "Another One", "June 27, 2022"),
        Transaction(2500.00, "Experts Are The Best", "June 23, 2022"),
        Transaction(-3000.00, "Delivery fee", "June 17, 2022")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Wallet",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(25.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Hello Ken", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = buildAnnotatedString {
                            append("Current balance: ")
                            withStyle(style = SpanStyle(color = Color(0xFF007AFF))) {
                                append("N10,712.00")
                            }
                        },
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Top Up",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TopUpOption(icon = R.drawable.bank, text = "Bank") {}
                        TopUpOption(icon = R.drawable.transfer, text = "Transfer") {}
                        TopUpOption(icon = R.drawable.card, text = "Card") {
                            val intent = Intent(context, AddPaymentMethodActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            context.startActivity(intent)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(35.dp))
            Text("Transaction History", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                transactions.forEach { transaction ->
                    TransactionItem(transaction)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun TopUpOption(icon: Int, text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = Color.Unspecified
        )
        Spacer(Modifier.height(4.dp))
        Text(text, fontSize = 12.sp)
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = formatAmount(transaction.amount),
                fontWeight = FontWeight.Medium,
                color = if (transaction.amount < 0) Color.Red else Color.Green,
                fontSize = 16.sp
            )
            Text(text = transaction.description, fontSize = 14.sp, color = Color.Gray)
        }
        Text(text = transaction.date, fontSize = 14.sp, color = Color.Gray)
    }
}

fun formatAmount(amount: Double): String {
    return String.format("N%,.2f", amount)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WalletScreen()
}

data class Transaction(val amount: Double, val description: String, val date: String)