package ir.dorsa.news_task.ui.news

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewsListScreenRoute( onNewsClick: (String) -> Unit){


    val viewModel = hiltViewModel<NewsListViewModel>()


    NewsListScreen(
        viewModel.newsListState.value,
        viewModel::getNews,
        onNewsClick)
}