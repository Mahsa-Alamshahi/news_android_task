package ir.dorsa.news_task.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.dorsa.news_task.data.data_source.remote.ApiService
import ir.dorsa.news_task.data.repository.NewsRepositoryImpl
import ir.dorsa.news_task.domain.repository.NewsRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideNewsRepository(apiService: ApiService): NewsRepository = NewsRepositoryImpl(apiService)
}