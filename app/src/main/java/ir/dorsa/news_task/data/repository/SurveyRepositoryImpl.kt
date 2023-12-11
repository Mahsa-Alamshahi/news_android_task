package ir.dorsa.news_task.data.repository

import ir.dorsa.news_task.data.data_source.remote.ApiService
import ir.dorsa.news_task.data.data_source.remote.dto.SurveyResponse
import ir.dorsa.news_task.domain.repository.SurveyRepository
import javax.inject.Inject

class SurveyRepositoryImpl @Inject constructor(private val apiService: ApiService): SurveyRepository {


    override suspend fun addComment(): SurveyResponse =
        apiService.survey()

}