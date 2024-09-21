package com.example.androiddev.book_module

import com.google.gson.annotations.SerializedName

data class BookDetailModel (

    @SerializedName("description") var description : String = "",
    @SerializedName("links") var links : List<Links> = emptyList(),
    @SerializedName("title") var title : String = "",
    @SerializedName("covers") var covers : List<Int> = emptyList(),
    @SerializedName("subject_places") var subjectPlaces : List<String> = emptyList(),
    @SerializedName("first_publish_date") var firstPublishDate : String = "",
    @SerializedName("subject_people") var subjectPeople : List<String> = emptyList(),
    @SerializedName("key") var key : String = "",
    @SerializedName("authors") var authors : List<Authors> = emptyList(),
    @SerializedName("excerpts") var excerpts : List<Excerpts> = emptyList(),
    @SerializedName("subjects") var subjects : List<String> = emptyList(),
//    @SerializedName("type") var type : Type,
    @SerializedName("subject_times") var subjectTimes : List<String> = emptyList(),
//    @SerializedName("cover_edition") var coverEdition : CoverEdition,
//    @SerializedName("latest_revision") var latestRevision : Int,
//    @SerializedName("revision") var revision : Int,
//    @SerializedName("created") var created : Created,
//    @SerializedName("last_modified") var lastModified : LastModified

)

data class Type (

    @SerializedName("key") var key : String

)

data class Links (

    @SerializedName("title") var title : String,
    @SerializedName("url") var url : String,
    @SerializedName("type") var type : Type

)

data class Author (

    @SerializedName("key") var key : String

)

data class Authors (

    @SerializedName("author") var author : Author,
    @SerializedName("type") var type : Type

)

data class Excerpts (

    @SerializedName("pages") var pages : String,
    @SerializedName("excerpt") var excerpt : String,
    @SerializedName("author") var author : Author

)

data class CoverEdition (

    @SerializedName("key") var key : String

)

data class Created (

    @SerializedName("type") var type : String,
    @SerializedName("value") var value : String

)

data class LastModified (

    @SerializedName("type") var type : String,
    @SerializedName("value") var value : String

)