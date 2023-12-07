package ir.dorsa.news_task.ui.navigation

sealed class Screen(val route: String){

    object NewsList: Screen("news_list")
    object NewsDetails: Screen("news_details")
}
