package ir.dorsa.news_task.ui.news

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.dorsa.news_task.common.Resource
import ir.dorsa.news_task.domain.usecase.GetNewsListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {


    private var _newsListState = mutableStateOf(NewsListState())
    var newsListState: State<NewsListState> = _newsListState


    fun getNews() {
        viewModelScope.launch {
            var newsList = getNewsListUseCase()
            newsList.collect {result ->
                when(result){
                    is Resource.Loading ->{
                        _newsListState.value = NewsListState(isLoading = true)
                    }
                    is Resource.Success ->{
                        _newsListState.value = NewsListState(newsList = result.data ?: emptyList())
                    }
                    is Resource.Failed ->{
                        _newsListState.value =
                            NewsListState(error = result.message ?: "An unexpected error occured.")
                    }
                }

            }
        }
    }

}