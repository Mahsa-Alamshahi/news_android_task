package ir.dorsa.news_task.ui.navigation

import ir.dorsa.news_task.common.AppConstants.NEWS_DETAILS_ARGUMENT_KEY

sealed class Screen(val route: String){

    object NewsList: Screen("news_list")
    object NewsDetails: Screen("news_details?$NEWS_DETAILS_ARGUMENT_KEY={$NEWS_DETAILS_ARGUMENT_KEY}") {
        fun passNews(news: String?) =
            "news_details?$NEWS_DETAILS_ARGUMENT_KEY=$news"
    }


    object Survey: Screen("survey")

}
