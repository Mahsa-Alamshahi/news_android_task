package ir.dorsa.news_task.data.data_source.remote

import ir.dorsa.news_task.data.data_source.remote.dto.NewsResponse
import ir.dorsa.news_task.data.data_source.remote.dto.SurveyResponse
import retrofit2.http.GET

interface ApiService {


    @GET("news")
    suspend fun newsList(
    ): NewsResponse



  @GET("survey")
    suspend fun survey(
    ): SurveyResponse


}