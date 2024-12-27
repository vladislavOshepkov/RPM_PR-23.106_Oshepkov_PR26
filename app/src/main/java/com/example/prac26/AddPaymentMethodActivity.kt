package com.example.prac26


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class AddPaymentMethodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddPaymentMethodScreen()
        }
    }
}

@Composable
fun AddPaymentMethodScreen() {
    var selectedOption by remember { mutableStateOf("wallet") }
    var selectedCard by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
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
                                val intent = Intent(context, WalletActivity::class.java)

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
                                text = "Wallet",
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
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    PaymentOption(
                        title = "Pay with wallet",
                        description = "Complete the payment using your e-wallet",
                        isSelected = selectedOption == "wallet",
                        onClick = { selectedOption = "wallet" }
                    )
                }
                item {
                    PaymentOption(
                        title = "Credit / debit card",
                        description = "Complete the payment using your debit card",
                        isSelected = selectedOption == "card",
                        onClick = { selectedOption = "card" }
                    )
                }
                if (selectedOption == "card") {
                    item {
                        CreditCardList(
                            selectedCard = selectedCard,
                            onCardSelected = { card ->
                                selectedCard = card
                            }
                        )
                    }
                }
                item {
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0560FA)
                        )
                    ) {
                        Text("Proceed to pay", fontSize = 16.sp)
                    }
                }
            }
        }
    )
}

@Composable
fun PaymentOption(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            Text(description, style = MaterialTheme.typography.bodySmall)
        }
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF0560FA),
                unselectedColor = Color.LightGray
            )
        )
    }
}

@Composable
fun CreditCardList(
    selectedCard: String,
    onCardSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CreditCardItem(
            cardNumber = "**** **** **** 3323",
            isSelected = selectedCard == "3323",
            onSelect = { onCardSelected("3323") }
        )
        CreditCardItem(
            cardNumber = "**** **** **** 1547",
            isSelected = selectedCard == "1547",
            onSelect = { onCardSelected("1547") }
        )
    }
}

@Composable
fun CreditCardItem(
    cardNumber: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF0560FA),
                    unselectedColor = Color.LightGray
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(cardNumber, style = MaterialTheme.typography.bodyLarge)
        }
        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_trash),
                contentDescription = "Delete card",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPaymentMethodScreenPreview() {
    AddPaymentMethodScreen()
}