package com.example.androiddev.book_module

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScaffold(nc: NavController, vm: BookViewModel, vmd: BookDetailModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        nc.popBackStack()
                    }) {
                        Icon(Icons.Rounded.ArrowBackIosNew,
                            contentDescription = null)
                    }
                },
                title = { Text(text = "Book Detail") }
            )
        }
    ) { it ->
        Box(
            modifier = Modifier
                .padding(it),
        ) {
            val doc = vm.selectedBook
            val description = vm.descResults.firstOrNull()

//            vm.fetchBookDetail(doc.coverEditionKey)

//            description?.let {
//                Text(text = vmd.description)
//            } ?: Text(text = "No description available")
//            Text(text = vmd.description)

            doc?.let {
//                Column(
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
//                        .padding(10.dp),
//                ) {
//                    Text(it.title, modifier = Modifier.padding(10.dp))
//                    Text(text = it.coverEditionKey)
//                    AsyncImage(
//                        model = "https://covers.openlibrary.org/b/olid/${it.coverEditionKey}.jpg?default=false",
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                    )
//                }

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(10.dp),
                ) {
                    Text( modifier = Modifier.padding(bottom = 6.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        text = it.title
                    )
                    Text(text = "by ${it.authorName}")
                    AsyncImage(modifier = Modifier.fillMaxWidth()
                        .padding(top = 10.dp),
                        model = "https://covers.openlibrary.org/b/olid/${it.coverEditionKey}.jpg?default=false",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .background(color = Color.LightGray)
                        .clip(MaterialTheme.shapes.medium)) {
                        Surface(
                            modifier = Modifier
                                .padding(15.dp)
                                .align(Alignment.CenterHorizontally),
                        ){
                            Row (modifier = Modifier.padding(horizontal = 6.dp)
                                .align(Alignment.CenterHorizontally)) {
                                Column (modifier = Modifier.border(BorderStroke(3.dp, Color.LightGray))
                                    .padding(top = 15.dp, start = 10.dp, end = 10.dp, bottom = 15.dp)
                                    .width(100.dp).height(60.dp), ){
                                    Column (modifier = Modifier
                                        .align(Alignment.CenterHorizontally),
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                            Text("Publish Date", fontSize = 13.sp, color = Color.DarkGray)
                                            Text("${it.publishDate.firstOrNull()}", fontSize = 15.sp, textAlign = TextAlign.Center)
                                    }

                                }
//                                Spacer(modifier = Modifier.height(9.dp))
                                Column (modifier = Modifier
                                    .padding(top = 15.dp, start = 5.dp, end = 5.dp, bottom = 15.dp)
                                    .width(135.dp), ){
                                    Column (modifier = Modifier
                                        .align(Alignment.CenterHorizontally),
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text("Publisher", fontSize = 13.sp, color = Color.DarkGray)
                                        Text("${it.publisher.firstOrNull()}", fontSize = 15.sp, textAlign = TextAlign.Center)
                                    }
                                }
                                Column (modifier = Modifier.border(BorderStroke(3.dp, Color.LightGray))
                                    .padding(top = 15.dp, start = 10.dp, end = 10.dp, bottom = 15.dp)
                                    .width(100.dp).height(60.dp), ){
                                    Column (modifier = Modifier
                                        .align(Alignment.CenterHorizontally),
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text("Language", fontSize = 13.sp, color = Color.DarkGray)
                                        Text("${it.language.firstOrNull()}", fontSize = 15.sp, textAlign = TextAlign.Center)
                                    }
                                }
                            }

                        }
                    }
                }
            } ?: Text(text = "No data available")
        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SecondPage(nc: NavController, fs: FontSizeViewModel, dl: ThemeViewModel) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                navigationIcon = {
//                    IconButton(onClick = {
//                        nc.popBackStack()
//                    }) {
//                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
//                    }
//                },
//                title = { Text(text = "Second Page") },
//                actions = {
//                    IconButton(onClick = { fs.decrease() }) {
//                        Icon(Icons.Rounded.Remove, contentDescription = null)
//                    }
//                    IconButton(onClick = { fs.increase() }) {
//                        Icon(Icons.Rounded.Add, contentDescription = null)
//                    }
//                    IconButton(onClick = { dl.lighttheme() }) {
//                        Icon(Icons.Rounded.DarkMode, contentDescription = null)
//                    }
//                }
//            )
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .padding(it),
//        ) {
//            Column(
//                modifier = Modifier.verticalScroll(rememberScrollState()).padding(10.dp),
//            ) {
//                Text(fontSize = fs.size.sp, text = "Kotlin is a cross-platform, statically typed, general-purpose high-level programming language with type inference. Kotlin is designed to interoperate fully with Java, and the JVM version of Kotlin's standard library depends on the Java Class Library, but type inference allows its syntax to be more concise.")
//            }
//        }
//    }
//}