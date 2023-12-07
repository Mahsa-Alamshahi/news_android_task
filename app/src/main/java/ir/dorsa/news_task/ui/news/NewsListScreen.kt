package ir.dorsa.news_task.ui.news

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.dorsa.news_task.R
import ir.dorsa.news_task.common.isConnectionOn
import ir.dorsa.news_task.data.data_source.remote.dto.News

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    newsListState: NewsListState,
    getNewsList: () -> Unit,
    onSurveyClick: () -> Unit,
    onNewsClick: (News) -> Unit
    ) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        if (context.isConnectionOn()) {
        getNewsList()
        } else {
            Toast.makeText(context, "Please check your internet connection.", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("اخبار فوری") },
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            painterResource(id = R.drawable.baseline_filter_alt_24),
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = {
                        onSurveyClick()
                    }) {
                        Icon(
                            painterResource(id = R.drawable.baseline_comment_24),
                            contentDescription = "Localized description"
                        )
                    }


                })
        },
    ) { contentPadding ->


        if (newsListState.isLoading) {
            CircularProgressIndicator()
        } else if (newsListState.error.isNotBlank()) {
            Text(newsListState.error)

            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        } else if (newsListState.newsList.isEmpty()) {
            Text("Empty List")
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
    }
}
