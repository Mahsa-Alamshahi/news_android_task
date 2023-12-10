package ir.dorsa.news_task.ui.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.dorsa.news_task.common.Resource
import ir.dorsa.news_task.domain.usecase.GetNewsListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {


    private var _newsListState = mutableStateOf(NewsListState())
    var newsListState: State<NewsListState> = _newsListState


    fun getNews() {
        viewModelScope.launch {
            val newsList = getNewsListUseCase()
            newsList.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _newsListState.value = NewsListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _newsListState.value = NewsListState(newsList = result.data ?: emptyList())
                    }

                    is Resource.Failed -> {
                        _newsListState.value =
                            NewsListState(error = result.message ?: "An unexpected error occured.")
                    }
                }
            }
        }
    }


    fun filterList(filterBy: String) {
        when (filterBy) {
            SortType.values()[0].sortBy -> {
                sortByViewCount()
            }
            SortType.values()[1].sortBy  -> {
                sortByDate()
            }
        }
    }



   private fun sortByViewCount(){
           _newsListState.value = NewsListState(newsList = _newsListState.value.newsList.sortedBy {
               it.viewCount
           })
    }


   private fun sortByDate(){
           _newsListState.value = NewsListState(newsList = _newsListState.value.newsList.sortedBy {
               it.date
           })
    }


}