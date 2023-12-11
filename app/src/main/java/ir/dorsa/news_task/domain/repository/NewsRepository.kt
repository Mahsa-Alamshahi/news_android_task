package ir.dorsa.news_task.domain.repository

import ir.dorsa.news_task.data.data_source.remote.dto.NewsResponse

interface NewsRepository {

    suspend fun getNewsList(): NewsResponse
}