package ir.dorsa.news_task.ui.news_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ir.dorsa.news_task.R
import ir.dorsa.news_task.common.datepicker.PersianCalendar
import ir.dorsa.news_task.data.data_source.remote.dto.News

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsListItem(news: News, onNewsClick: (News) -> Unit) {


    val configuration = LocalConfiguration.current
    val heightInDp = configuration.screenHeightDp.dp


    val persianCalender = PersianCalendar()
    persianCalender.timeInMillis = news.date.toLong()


    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onNewsClick(news)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(.5.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Row(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {


            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                Column(
                    modifier = Modifier
                        .weight(.6f)
                        .padding(start = 8.dp, top = 2.dp, bottom = 6.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {


                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp, top = 2.dp, bottom = 6.dp, end = 8.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {


                        Text(
                            text = news.title,
                            modifier = Modifier.padding(bottom = 4.dp),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )

                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(.5.dp)
                                .background(Color.Gray)
                                .padding(top = 4.dp, bottom = 16.dp)
                        )


                        Text(
                            text = stringResource(R.string.news_date) + persianCalender.persianLongDateAndTime,
                            modifier = Modifier.padding(bottom = 4.dp, top = 4.dp),
                            maxLines = 1,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )


                        Text(
                            text = stringResource(R.string.view_count) + news.viewCount.toString(),
                            modifier = Modifier.padding(bottom = 4.dp),
                            maxLines = 1,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )

                    }
                }

                Card(
                    modifier = Modifier
                        .weight(.4f)
                        .padding(0.dp)
                        .clickable { },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    shape = RoundedCornerShape(0.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
                    )
                ) {

                    GlideImage(
                        model = news.poster,
                        contentDescription = "Poster",
                        modifier = Modifier
                            .background(color = Color.Gray)
                            .fillMaxWidth()
                            .height(heightInDp / 6)
                            .clickable {
                                onNewsClick(news)
                            },
                    ) {
                        it.error(R.drawable.news_placeholder).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    }
                }

            }
        }
    }

}