package ir.dorsa.news_task.ui.survey

import ir.dorsa.news_task.data.data_source.remote.dto.SurveyResponseItem

data class SurveyState(
    val isLoading: Boolean = false,
    val surveyList: List<SurveyResponseItem> = emptyList(),
    val error: String = ""
)
