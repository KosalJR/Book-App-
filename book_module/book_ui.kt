package com.example.androiddev.book_module

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScaffold(nc: NavController, vm: BookViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Book") },
                actions = {
                    Box(modifier = Modifier.padding(1.dp)) {
                        IconButton(onClick = {
                            nc.navigate("search")
                        }) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                            )
                        }
                    }

//                    IconButton(onClick = {
//                        vm.toggleSort(vm.stateBookList)
//                    }) {
//                        Icon(
//                            Icons.Default.ArrowDownward,
//                            contentDescription = "Down",
//                        )
//                    }
                    IconButton(onClick = {
                        vm.getResultList()
                    }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                        )
                    }
                    IconButton(onClick = {
                        nc.navigate("about_us")
                    }) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "about us",
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            BookBody(nc, vm)
        }
    }
}

@Composable
fun BookBody(nc: NavController, vm: BookViewModel) {
    LaunchedEffect(Unit) {
//        vm.getResultList()
        if (vm.stateBookList.value.isEmpty()) {
            vm.getResultList()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (vm.isLoading) {
            CircularProgressIndicator()
        } else if (vm.errorMessage.isNotEmpty()) {
            Text("Error: ${vm.errorMessage}")
        } else {
            LazyColumn() {
                items(vm.results.size) {

                    val element = vm.stateBookList.value[it]

//                    Row(
//                        modifier = Modifier.padding(10.dp)
//                            .clickable{
//                                nc.navigate("book_detail")
//                        },
//                        verticalAlignment = Alignment.CenterVertically,
//                    ) {
//
//                        Surface(
//                            modifier = Modifier.size(120.dp),
//                        ) {
//                            AsyncImage(
//                                model = "https://covers.openlibrary.org/b/olid/${element.coverEditionKey}.jpg?default=false",
//                                contentDescription = "https://covers.openlibrary.org/b/olid/${element.coverEditionKey}.jpg?default=false",
//                            )
//                        }
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(10.dp)
//                        ) {
//                            Column() {
//                                Text(element.title)
//                                Text("by ${element.authorName}")
//                                Text("release: ${element.firstPublishYear}")
//                            }
//                        }
//                    }

                    Row(
                        modifier = Modifier
                            .padding(0.dp)
                            .border(BorderStroke(1.dp, Color.LightGray))
                            .clickable {
                                vm.setBook(element)
                                nc.navigate("book_detail")
                            },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Surface(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .size(135.dp),
                        ) {
                            AsyncImage(modifier = Modifier.fillMaxWidth(),
                                model = "https://covers.openlibrary.org/b/olid/${element.coverEditionKey}.jpg?default=false",
                                contentDescription = "https://covers.openlibrary.org/b/olid/${element.coverEditionKey}.jpg?default=false",
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Column() {
                                Text(element.title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                                Spacer(modifier = Modifier.height(7.dp))
                                Text("By :  ${element.authorName.joinToString(", ")}")
                                Text("Release   :  ${element.firstPublishYear}")
                            }
                        }
                    }
                }
            }
        }
    }
}