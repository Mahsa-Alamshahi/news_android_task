package ir.dorsa.news_task.ui.news

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ir.dorsa.news_task.data.data_source.remote.dto.News

@Composable
fun NewsListScreenRoute(
                         onSurveyClick: () -> Unit,
                         onNewsClick: (News) -> Unit
){


    val viewModel = hiltViewModel<NewsListViewModel>()


    NewsListScreen(
        viewModel.newsListState.value,
        viewModel::getNews,
        onSurveyClick,
        onNewsClick,
        )
}