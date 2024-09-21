package com.example.kotlic.book_module
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookAboutus(nc: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { nc.navigate("main") }) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = { Text("About Us") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // About Us Title
            Text(
                text = "Contact Us",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Phone Number
            ContactItem(
                label = "Phone",
                value = "010-295-242",
                onClick = { phoneNumber ->
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                    //LocalContext.current.startActivity(intent)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email
            ContactItem(
                label = "Email",
                value = "Kosalloy6@gmail.com",
                onClick = { email ->
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
                    //LocalContext.current.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun ContactItem(label: String, value: String, onClick: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp
        )
        Text(
            text = value,
            style = TextStyle(color = Color.Blue, fontSize = 16.sp),
            modifier = Modifier
                .clickable { onClick(value) }
                .padding(vertical = 4.dp)
        )
    }
}

