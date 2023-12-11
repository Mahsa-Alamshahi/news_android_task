package ir.dorsa.news_task.ui.news_details

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.dorsa.news_task.R
import ir.dorsa.news_task.common.datepicker.PersianCalendar
import ir.dorsa.news_task.data.data_source.remote.dto.News


@Composable
fun NewsDetailsScreenRoute(
    news: News
) {
    NewsDetailsScreen(news = news)
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailsScreen(news: News) {


    val activity = LocalContext.current as Activity
    val configuration = LocalConfiguration.current
    val heightInDp = configuration.screenHeightDp.dp


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {


        val persianCalender = PersianCalendar()
        persianCalender.timeInMillis = news.date.toLong()


        Column(modifier = Modifier.fillMaxSize()) {

            Card(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(0.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                )
            ) {


                GlideImage(
                    model = news.poster,
                    contentDescription = "Poster",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightInDp / 4),
                    contentScale = ContentScale.Crop,

                    ) {
                    it
                        .error(R.drawable.news_placeholder)

                }
            }

            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {


                Text(
                    text = "تعداد بازدید: ${news.viewCount}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )


                VerticalDivider(modifier = Modifier.height(32.dp).padding(start = 8.dp, end = 8.dp))

                Icon(imageVector = Icons.Default.Share, contentDescription = "Share",
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .size(24.dp)
                        .clickable {
                            shareNews(activity, "${news.title} \n\n${news.description}")
                        })


            }
            Text(
                text = news.title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            HorizontalDivider(modifier = Modifier.height(.5.dp))


            Text(
                text = news.description,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(8.dp)
            )



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {


            }
        }
    }
}


fun shareNews(activity: Activity, newsText: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, newsText)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    activity.startActivity(Intent.createChooser(intent, "Share News"))
}


