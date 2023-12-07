package ir.dorsa.news_task.domain.usecase

import ir.dorsa.news_task.common.Resource
import ir.dorsa.news_task.data.data_source.remote.dto.SurveyResponse
import ir.dorsa.news_task.data.data_source.remote.dto.SurveyResponseItem
import ir.dorsa.news_task.domain.repository.SurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SurveyUseCase @Inject constructor(private val surveyRepository: SurveyRepository) {



    suspend operator fun invoke(): Flow<Resource<List<SurveyResponseItem>>> = flow {

        try {
            emit(Resource.Loading())
            val surveyResponse = surveyRepository.addComment().toList()
            emit(Resource.Success(surveyResponse))
        } catch (e: HttpException) {
            emit(Resource.Failed(e.localizedMessage ?: "An unexpected error occured."))
        } catch (e: IOException) {
            emit(Resource.Failed("Couldn't get data."))
        }

    }
}