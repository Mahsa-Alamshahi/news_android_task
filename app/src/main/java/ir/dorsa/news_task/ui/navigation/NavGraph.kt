package ir.dorsa.news_task.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ir.dorsa.news_task.common.AppConstants.NEWS_DETAILS_ARGUMENT_KEY
import ir.dorsa.news_task.data.data_source.remote.dto.News
import ir.dorsa.news_task.ui.news_details.NewsDetailsScreenRoute
import ir.dorsa.news_task.ui.news_list.NewsListScreenRoute
import ir.dorsa.news_task.ui.survey.SurveyScreenRoute

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.NewsList.route) {

        newsListRoute(navController)
        newsDetailsRoute()
        surveyRoute()
    }
}


fun NavGraphBuilder.newsListRoute(navController: NavController) {
    composable(
        route = Screen.NewsList.route
    ) {

        NewsListScreenRoute(onSurveyClick = {
            navController.navigate(Screen.Survey.route)
        }) { news ->

            val newsJsonString = news.toJson()
            navController.navigate(Screen.NewsDetails.passNews(newsJsonString))

        }

    }
}


fun NavGraphBuilder.newsDetailsRoute() {
    composable(
        route = Screen.NewsDetails.route,
        arguments = listOf(navArgument(NEWS_DETAILS_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        navBackStackEntry.arguments?.getString(NEWS_DETAILS_ARGUMENT_KEY)
            ?.let { jsonString ->
                val news = jsonString.fromJson(News::class.java)
                NewsDetailsScreenRoute(news = news)
            }
    }
}



fun NavGraphBuilder.surveyRoute() {
    composable(
        route = Screen.Survey.route
    ) {
        SurveyScreenRoute()
    }
}

