package ir.dorsa.news_task.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("date")
    val date: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("viewCount")
    val viewCount: Int
)