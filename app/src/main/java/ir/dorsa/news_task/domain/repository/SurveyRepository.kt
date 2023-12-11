package ir.dorsa.news_task.domain.repository

import ir.dorsa.news_task.data.data_source.remote.dto.SurveyResponse

interface SurveyRepository {

    suspend fun addComment(): SurveyResponse
}