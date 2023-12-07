package ir.dorsa.news_task.ui.news

import ir.dorsa.news_task.data.data_source.remote.dto.NewsResponseItem

data class NewsListState(val isLoading: Boolean = true,
    val newsList: List<NewsResponseItem> = emptyList(),
    val error: String = ""
)
