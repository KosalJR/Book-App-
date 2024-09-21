package com.example.androiddev.book_module

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
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
fun BookSearchResultScaffold(nc: NavController, vm: BookViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        vm.stateBookList.value.clear()
                        nc.navigate("main")
                    }) {
                        Icon(Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null)
                    }
                },
                title = { Text("Search Result") },
                actions = {
                    IconButton(onClick = {
                        vm.toggleSortSearch()
                    }) {
                        Icon(
                            Icons.Default.ArrowDownward,
                            contentDescription = "Down",
                        )
                    }
                    IconButton(onClick = {
                        nc.navigate("search_result")
                        vm.refreshSearch()
                    }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                        )
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            BookSearchResultBody(nc, vm)
        }
    }
}

@Composable
fun BookSearchResultBody(nc: NavController, vm: BookViewModel) {
    LaunchedEffect(Unit) {
        if (vm.stateSearchBookList.value.isEmpty()) {
            vm.getSearchResultList()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (vm.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (vm.errorMessage.isNotEmpty()) {
            Text("Error: ${vm.errorMessage}")
        } else {
            Column () {
                Box (
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    if (vm.successMessage.isNotEmpty()) {
                        Text(vm.successMessage, color = Color.Blue)
                    }

                    if (vm.errorMessage.isNotEmpty()) {
                        Text(vm.errorMessage, color = Color.Red)
                    }
                }

                LazyColumn() {
                    items(vm.results.size) {

                        val element = vm.stateSearchBookList.value[it]

//                        Row(
//                            modifier = Modifier.padding(10.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                        ) {
//
//                            Surface(
//                                modifier = Modifier.size(120.dp),
//                            ) {
//                                AsyncImage(
//                                    model = "https://covers.openlibrary.org/b/olid/${element.coverEditionKey}.jpg?default=false",
//                                    contentDescription = "https://covers.openlibrary.org/b/olid/${element.coverEditionKey}.jpg?default=false",
//                                )
//                            }
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(10.dp)
//                            ) {
//                                Column() {
//                                    Text(element.title)
//                                    Text("by ${element.authorName}")
//                                    Text("release: ${element.firstPublishYear}")
//                                }
//                            }
//                        }

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
}