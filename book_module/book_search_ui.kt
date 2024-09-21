package com.example.androiddev.book_module

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchScaffold(nc: NavController, vm: BookViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search Book") },
                actions = {
                    IconButton(onClick = {
                        nc.navigate("main")
                    }) {
                        Icon(Icons.Rounded.Close,
                            contentDescription = null)
                    }
                },
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            BookSearchBody(nc, vm)
        }
    }
}

@Composable
fun BookSearchBody(nc: NavController, vm: BookViewModel) {

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            placeholder = { Text("Enter Title") },
            leadingIcon = {
                Icon(
                    Icons.Default.TextFields,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            placeholder = { Text("Enter Author") },
            leadingIcon = {
                Icon(
                    Icons.Default.PersonSearch,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            )
        )

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
            onClick = {
//            val item = Records(
//                pid = "0",
//                title = title,
//                body = body,
//                price = price,
//                qty = qty,
//                image = image,
//            )
                if (title == "") title = "+"
                if (author == "") author = "+"
            vm.searchBook(title, author)
                nc.navigate("search_result")
        }) {
            Text("Search")
        }

//        if (vm.isLoading) {
//            CircularProgressIndicator()
//        }
//
//        if (vm.successMessage.isNotEmpty()) {
//            Text(vm.successMessage, color = Color.Blue)
//        }
//
//        if (vm.errorMessage.isNotEmpty()) {
//            Text(vm.errorMessage, color = Color.Red)
//        }
    }
}