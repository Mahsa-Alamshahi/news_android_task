package ir.dorsa.news_task.data.repository

import ir.dorsa.news_task.data.data_source.remote.ApiService
import ir.dorsa.news_task.data.data_source.remote.dto.NewsResponse
import ir.dorsa.news_task.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiService: ApiService): NewsRepository {

    override suspend fun getNewsList(): NewsResponse = apiService.newsList()

}