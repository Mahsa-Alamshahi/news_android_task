package ir.dorsa.news_task.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

class SurveyResponse : ArrayList<SurveyResponseItem>()



data class SurveyResponseItem(
    @SerializedName("survy")
    val survey: String
)