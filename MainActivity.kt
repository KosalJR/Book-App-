package com.example.kotlic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddev.book_module.BookDetailModel
import com.example.androiddev.book_module.BookDetailScaffold
import com.example.androiddev.book_module.BookScaffold
import com.example.androiddev.book_module.BookSearchResultScaffold
import com.example.androiddev.book_module.BookSearchScaffold
import com.example.androiddev.book_module.BookViewModel
import com.example.kotlic.async_module.TodoUI
import com.example.kotlic.async_module.TodoViewModel
import com.example.kotlic.book_module.BookAboutus
import com.example.kotlic.food.FoodViewModel
import com.example.kotlic.food.Food_CartPage
import com.example.kotlic.food.Food_CartViewModel
import com.example.kotlic.food.Food_FavoriteViewModel
import com.example.kotlic.food.Food_Firstpage
import com.example.kotlic.food.Food_FovoritePage
import com.example.myapp.sample2.food.Food_Secondpage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//            App()
//            MultiplePageApp()
            //AsyncApp()
            book()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
    fun AsyncApp(){
        val vm = TodoViewModel()
        TodoUI(vm)
    }
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ){
//    Text(
//        text = "Lim $name 😁",
//        modifier = modifier
//    )}
//}
//
//@Preview(showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    MyAppTheme {
//        Greeting("Kemhout")
//    }
//}

@Preview(showSystemUi = true)
@Composable
fun PreviewApp(){
    App()
}

///@Composable
//fun App(){
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
////        composeText()
////        composeIcon()
////        composeImage()
////        composeSurface()
////        Poster()
////        composeFacebook()
//
//    }
//}

@Preview(showSystemUi = true)
@Composable
fun MultiplePageApp(){
//    val nc = rememberNavController()
//    val sf: fontsizemode = viewModel()
//    val cm: colormode = viewModel()
//
//    NavHost(navController = nc, startDestination = "first" ){
//        composable("first"){
//            FirstPage(nc,sf,cm)
//        }
//        composable("second"){
//            SecondPage(nc,sf,cm)
//        }
//    }
    val nc = rememberNavController()
    val mv = FoodViewModel()
    val favVM = Food_FavoriteViewModel()
    val cart = Food_CartViewModel()
    NavHost(navController = nc, startDestination = "first" ){
        composable("first"){
            Food_Firstpage(nc, mv, favVM, cart)
        }
        composable("second"){
            Food_Secondpage(nc, mv)
        }
        composable("favorite"){
            Food_FovoritePage(nc,mv,favVM)
        }
        composable("cart"){
            Food_CartPage(nc, mv, cart)
        }

    }
}

@Composable
fun App(){
//    foodScreen()
//   Grabapp()
//    Column{
//        page1WithoutViewModel()
//        page2WithoutViewModel()
//
}
@Preview(showSystemUi = true)
@Composable
fun book(){
    val nc = rememberNavController()
    val vm = BookViewModel()
    val vmd = BookDetailModel()
    BookScaffold(nc, vm)
    NavHost(navController = nc, startDestination = "main" ) {
        composable("main") {
            BookScaffold(nc, vm)
        }
        composable("book_detail") {
            BookDetailScaffold(nc, vm, vmd)
        }
        composable("search") {
            BookSearchScaffold(nc, vm)
        }
        composable("search_result") {
            BookSearchResultScaffold(nc, vm)
        }
        composable("about_us"){
            BookAboutus(nc)
        }
    }

}