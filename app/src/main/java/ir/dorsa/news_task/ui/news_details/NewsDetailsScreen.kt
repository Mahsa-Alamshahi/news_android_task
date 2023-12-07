package ir.dorsa.news_task.ui.news_details

import android.content.res.ColorStateList
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.util.LinkifyCompat
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ir.dorsa.news_task.R
import ir.dorsa.news_task.data.data_source.remote.dto.News

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailsScreen(news: News) {

    val configuration = LocalConfiguration.current
    val heightInDp = configuration.screenHeightDp.dp

    Card(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(4.dp),
    ) {

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {



                Column(modifier = Modifier.fillMaxSize()) {


                    Card(
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        shape = RoundedCornerShape(4.dp),
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

                    Text(
                        text = news.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray.copy(.5f),
                        ),
                        shape = RoundedCornerShape(4.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 16.dp
                        )
                    ) {

                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {


                            Column {





                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.DarkGray)
                            )

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                        Text(
                                            text = "توضیحات: ",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(2.dp).fillMaxWidth().weight(1f)
                                        )


                                        Text(
                                            text = news.description,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(2.dp).weight(1f)
                                        )

                                }


                              Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                        Text(
                                            text = "تاریخ خبر: ",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(2.dp).fillMaxWidth().weight(1f)
                                        )


                                        Text(
                                            text = news.date.toString(),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(2.dp).weight(1f)
                                        )

                                }


                              Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                        Text(
                                            text = "تعداد بازدید کننده: ",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(2.dp).fillMaxWidth().weight(1f)
                                        )


                                        Text(
                                            text = news.viewCount.toString(),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(2.dp).weight(1f)
                                        )

                                }





                            }

                        }
                    }
                }

        }
    }

}