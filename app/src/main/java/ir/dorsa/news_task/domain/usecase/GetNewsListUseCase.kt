package ir.dorsa.news_task.domain.usecase

import ir.dorsa.news_task.common.Resource
import ir.dorsa.news_task.data.data_source.remote.dto.NewsResponseItem
import ir.dorsa.news_task.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(private val newsRepository: NewsRepository) {


    suspend operator fun invoke(): Flow<Resource<List<NewsResponseItem>>> = flow {
        try {
            emit(Resource.Loading())
            val newsList = newsRepository.getNewsList().toList()
            emit(Resource.Success(newsList))
        } catch (e: HttpException) {
            emit(Resource.Failed(e.localizedMessage ?: "An unexpected error occured."))
        } catch (e: IOException) {
            emit(Resource.Failed("Couldn't get data."))
        }


    }
}