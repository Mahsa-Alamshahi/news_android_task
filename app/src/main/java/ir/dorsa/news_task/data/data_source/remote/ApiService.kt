package ir.dorsa.news_task.data.data_source.remote

import ir.dorsa.news_task.data.data_source.remote.dto.NewsResponse
import retrofit2.http.GET

interface ApiService {


    @GET("news")
    suspend fun newsList(
    ): NewsResponse


}