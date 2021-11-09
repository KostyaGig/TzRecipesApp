package com.zinoview.tzrecipesapp.service_locator.core

import android.content.Context
import com.zinoview.tzrecipesapp.core.ResourceProvider
import com.zinoview.tzrecipesapp.data.ExceptionMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoreModule {

    lateinit var retrofit: Retrofit
    lateinit var client: OkHttpClient
    lateinit var resourceProvider: ResourceProvider
    lateinit var exceptionMapper: ExceptionMapper

    fun init(context: Context) {
        initNetwork()

        resourceProvider = ResourceProvider.Base(context)

        exceptionMapper = ExceptionMapper.Base(resourceProvider)
    }

    private fun initNetwork() {
            client =
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

    fun <T> networkService(clazz: Class<T>) = retrofit.create(clazz)

    private companion object {
        private const val BASE_URL = "https://test.kode-t.ru"
    }
}