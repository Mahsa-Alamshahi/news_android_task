package ir.dorsa.news_task.ui.survey

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.dorsa.news_task.common.Resource
import ir.dorsa.news_task.domain.usecase.SurveyUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(private val surveyUseCase: SurveyUseCase): ViewModel() {



    private var _surveyState = mutableStateOf(SurveyState())
    var surveyState: State<SurveyState> = _surveyState



    fun addComment(){

        viewModelScope.launch {
            val surveyResponse = surveyUseCase()
            surveyResponse.collect {result ->
                when(result){
                    is Resource.Loading ->{
                        _surveyState.value = SurveyState(isLoading = true)
                    }
                    is Resource.Success ->{
                        _surveyState.value = SurveyState(surveyList = result.data ?: emptyList())
                    }
                    is Resource.Failed ->{
                        _surveyState.value =
                            SurveyState(error = result.message ?: "An unexpected error occured.")
                    }
                }

            }
        }
    }
}