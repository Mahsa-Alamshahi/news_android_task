package ir.dorsa.news_task.ui.news_list

import ir.dorsa.news_task.data.data_source.remote.dto.News

data class NewsListState(
    val isLoading: Boolean = false,
    val newsList: List<News> = emptyList(),
    val error: String = ""
)
