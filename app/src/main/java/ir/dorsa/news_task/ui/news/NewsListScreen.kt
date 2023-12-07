package ir.dorsa.news_task.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
inline fun NewsListScreen(
    newsListState: NewsListState,
    crossinline getNewsList: () -> Unit,
    onNewsClick: (String) -> Unit
){



    LaunchedEffect(key1 = Unit) {
        getNewsList()
    }



    if (newsListState.isLoading) {
      CircularProgressIndicator()
    } else if (newsListState.error.isNotBlank()) {
        Text(newsListState.error)
    } else if (newsListState.newsList.isEmpty()) {
        Text("Empty List")
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {


            items(newsListState.newsList){ news ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                        },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = news.title, fontSize = 18.sp)
                }

            }
        }

    }

}
