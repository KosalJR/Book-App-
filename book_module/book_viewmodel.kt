package com.example.androiddev.book_module

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {

    private val _results = mutableStateListOf<Docs>()
    var errorMessage: String by mutableStateOf("")
    var successMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val results: List<Docs>
        get() = _results

    var selectedBook by mutableStateOf(Docs())
        private set

    fun setBook(book: Docs){
        selectedBook = book
    }

    var stateBookList : MutableState<MutableList<Docs>> = mutableStateOf(_results)
    var stateSearchBookList : MutableState<MutableList<Docs>> = mutableStateOf(_results)

    fun searchBook(title: String, author: String) {
        _results.clear()
//        stateBookList.value.clear()

        viewModelScope.launch {
            isLoading = true
            val service = SearchService.getInstance()
            try {
                val response: MutableState<BookModel> = mutableStateOf(service.getsearchBooks(title, author))

                if (response.value.numFound != "0") {
//                    successMessage = "\" Result Found ${response.value.numFound} \""
                    successMessage = "\"${title} ${author}\" returns ${response.value.numFound} results"
                    getSearchResultList(title, author) // Refresh the product list after insertion
                } else {
                    successMessage = "\"${title} ${author}\" returns ${response.value.numFound} results"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${ e.message.toString()}"
            } finally {
                isLoading = false
            }
        }
    }

    fun getResultList(title: String = "think+like+a+success") {
//        stateBookList.value = _results
        viewModelScope.launch {
            isLoading = true
            val apiService = RndAPIService.getInstance()
            try {
                _results.clear()
                _results.addAll(apiService.getBooks(title).docs)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    fun getSearchResultList(title: String = "+", author: String = "+") {
//        stateSearchBookList.value = _results
        viewModelScope.launch {
            isLoading = true
            val apiSearchService = SearchService.getInstance()
            try {
                _results.clear()
                _results.addAll(apiSearchService.getsearchBooks(title, author).docs)

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    private val _resultsDesc = mutableStateListOf<BookDetailModel>()
    val descResults: List<BookDetailModel>
        get() = _resultsDesc

    fun getDescriptionList(coverEditionKey: String = "0") {
        viewModelScope.launch {
            isLoading = true
            val descSearchService = DescService.getInstance()
            try {
                _resultsDesc.clear()
                _resultsDesc.addAll(descSearchService.getBookDescription(coverEditionKey))

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    fun fetchBookDetail(coverEditionKey: String) {
        _resultsDesc.clear()
//        stateBookList.value.clear()

        viewModelScope.launch {
            isLoading = true
            val service = DescService.getInstance()
            try {
                val response: MutableState<List<BookDetailModel>> = mutableStateOf(service.getBookDescription(coverEditionKey))

                if (response.value.toString().contains("error")) {
                    successMessage = "Not Found"
                    getDescriptionList(coverEditionKey) // Refresh the product list after insertion
                } else {
                    successMessage = "Success"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${ e.message.toString()}"
            } finally {
                isLoading = false
            }
        }
    }

    fun refreshSearch() {
        stateSearchBookList.value = _results
    }

    var isAscendingOrder = true

    fun toggleSortSearch() {
        var newList = stateSearchBookList.value.toMutableList()

        if (isAscendingOrder){
            newList.sortBy {it.firstPublishYear}
        }
        else{
            newList.sortByDescending {it.firstPublishYear}
        }

        stateSearchBookList.value = newList
        isAscendingOrder = !isAscendingOrder
    }

//    fun toggleSort(){
//        var newList = stateBookList.value.toMutableList()
//
//        if (isAscendingOrder){
//            newList.sortBy {it.firstPublishYear}
//        }
//        else{
//            newList.sortByDescending {it.firstPublishYear}
//        }
//
//        stateBookList.value = newList
//        isAscendingOrder = !isAscendingOrder
//    }

}