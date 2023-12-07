package ir.dorsa.news_task.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.dorsa.news_task.ui.news.NewsListScreenRoute

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.NewsList.route) {

        newsListRoute(navController)
    }
}


fun NavGraphBuilder.newsListRoute(navController: NavController) {
    composable(
        route = Screen.NewsList.route
    ) {
        NewsListScreenRoute(){

        }
    }
}