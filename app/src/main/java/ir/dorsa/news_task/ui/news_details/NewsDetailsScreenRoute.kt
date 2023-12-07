package ir.dorsa.news_task.ui.news_details

import androidx.compose.runtime.Composable
import ir.dorsa.news_task.data.data_source.remote.dto.News

@Composable
fun NewsDetailsScreenRoute(
    news: News
){

    NewsDetailsScreen(news = news)
}