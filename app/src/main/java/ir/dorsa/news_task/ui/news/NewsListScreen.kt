package ir.dorsa.news_task.ui.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NewsListScreen(){



    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "News List")
    }

}




@Composable
@Preview
fun PreviewNewsList(){
    NewsListScreen()
}