package ir.dorsa.news_task.ui.news_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import ir.dorsa.news_task.R
import ir.dorsa.news_task.common.isConnectionOn
import ir.dorsa.news_task.ui.components.theme.Red40
import ir.dorsa.news_task.data.data_source.remote.dto.News
import ir.dorsa.news_task.ui.components.ErrorView
import ir.dorsa.news_task.ui.components.Loading



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
    ) { filterBy ->

        viewModel.filterList(filterBy)
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    newsListState: NewsListState,
    getNewsList: () -> Unit,
    onSurveyClick: () -> Unit,
    onNewsClick: (News) -> Unit,
    onFilterClick: (String) -> Unit
) {


    val context = LocalContext.current


    val sortDialogState = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        if (context.isConnectionOn()) {
            getNewsList()
        } else {
            Toast.makeText(context, "Please check your internet connection.", Toast.LENGTH_SHORT)
                .show()
        }
    }


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Scaffold(
            topBar = {

                TopAppBar(
                    title = {
                        Text(stringResource(R.string.news_title), fontWeight = FontWeight.SemiBold, color = Color.White)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Red40),
                    actions = {
                        IconButton(onClick = {
                            sortDialogState.value = true
                        }) {
                            Icon(
                                painterResource(id = R.drawable.baseline_sort_24),
                                contentDescription = "Filter",
                                tint= Color.White
                            )
                        }
                        IconButton(onClick = {
                            onSurveyClick()
                        }) {
                            Icon(
                                painterResource(id = R.drawable.baseline_comment_24),
                                contentDescription = "Filter",
                                tint= Color.White
                            )
                        }


                    })
            },
        ) { contentPadding ->


            if (newsListState.isLoading) {
                Loading()
            } else if (newsListState.error.isNotBlank()) {
                ErrorView(message = newsListState.error)
            } else if (newsListState.newsList.isEmpty()) {
                ErrorView("Empty List")
            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(contentPadding)
                ) {

                    items(newsListState.newsList) { news ->
                        NewsListItem(news = news, onNewsClick)
                    }
                }

            }

            when {
                sortDialogState.value -> {
                    FilterDialog(onFilterClick = onFilterClick) {
                        sortDialogState.value = false
                    }
                }
            }
        }

    }
}
