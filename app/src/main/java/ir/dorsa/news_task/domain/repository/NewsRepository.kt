package ir.dorsa.news_task.domain.repository

interface NewsRepository {

    suspend fun getNewsList()
}