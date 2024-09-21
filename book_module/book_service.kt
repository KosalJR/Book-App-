package com.example.androiddev.book_module

//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://openlibrary.org"

interface RndAPIService{
    @GET("search.json")
    suspend fun getBooks(
        @Query("q") q: String,
        @Query("page") page: String = "1",
        @Query("sort") sort: String = "rating",
//        @Query("api_key") api_key: String = "yourapikey"
    ): BookModel

    companion object {
        private var randomservice : RndAPIService? = null
        fun getInstance(): RndAPIService{
            if(randomservice == null){
                randomservice = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RndAPIService::class.java)
            }
            return randomservice!!
        }
    }
}

interface SearchService{
    @GET("search.json")
    suspend fun getsearchBooks(
        @Query("q") q: String,
        @Query("author") author: String,
        @Query("page") page: String = "1",
        @Query("sort") sort: String = "rating",
//        @Query("api_key") api_key: String = "yourapikey"
    ): BookModel

    companion object {
        private var searchservice : SearchService? = null
        fun getInstance(): SearchService{
            if(searchservice == null){
                searchservice = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SearchService::class.java)
            }
            return searchservice!!
        }
    }
}

interface DescService {
    @GET("works/{cover_edition_key}.json")
    suspend fun getBookDescription(
        @Path("cover_edition_key") coverEditionKey: String
    ): List<BookDetailModel>

    companion object {
        private var desc_service : DescService? = null
        fun getInstance(): DescService{
            if(desc_service == null){
                desc_service = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DescService::class.java)
            }
            return desc_service!!
        }
    }
}