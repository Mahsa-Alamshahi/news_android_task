package ir.dorsa.news_task.ui.survey

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SurveyScreenRoute(){

    val viewModel: SurveyViewModel = hiltViewModel()

    SurveyScreen(
        viewModel.surveyState.value,
        viewModel::addComment
    )
}