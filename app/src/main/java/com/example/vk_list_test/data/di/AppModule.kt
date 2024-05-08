package com.example.vk_list_test.data.di

import com.example.vk_list_test.data.api.DummyApi
import com.example.vk_list_test.data.product.repository.ProductRepositoryImpl
import com.example.vk_list_test.domain.product.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(DummyApi.BASE_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): DummyApi = retrofit.create(DummyApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: DummyApi): ProductRepository = ProductRepositoryImpl(api)
}