package ru.gb.android.homework3.data.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.android.homework3.data.product.ProductApiService


@Module
object NetworkModule {

    private const val ENDPOINT = "https://makzimi.github.io/"

    @Provides
    fun providesConverterFactory(): Converter.Factory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
        val interceptor: HttpLoggingInterceptor;
        interceptor = HttpLoggingInterceptor();
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor;
    }


    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient:OkHttpClient,
        factory:Converter.Factory,
    ): Retrofit {

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ENDPOINT)
                .addConverterFactory(factory)
                .build()

    }


    fun provideProductApiService(retrofit: Retrofit): ProductApiService {
         return retrofit.create(ProductApiService::class.java)
   }

}