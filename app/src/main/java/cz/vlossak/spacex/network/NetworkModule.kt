package cz.vlossak.spacex.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideSpacexApi(retrofit: Retrofit): Api = retrofit.create()

}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val REQUEST_TIMEOUT_SEC = 30L
    private const val MAX_PARALLEL_REQUESTS = 5
    private const val BASE_URL: String = "https://api.spacexdata.com/"

    @Provides
    @Singleton
    fun provideRetrofitOkHttpClient(

    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            // Setup Timeouts
            connectTimeout(REQUEST_TIMEOUT_SEC, TimeUnit.SECONDS)
            readTimeout(REQUEST_TIMEOUT_SEC, TimeUnit.SECONDS)
            writeTimeout(REQUEST_TIMEOUT_SEC, TimeUnit.SECONDS)

            // Setup Max Requests
            dispatcher(Dispatcher().apply { maxRequests = MAX_PARALLEL_REQUESTS })
            retryOnConnectionFailure(true)

            // Setup Interceptors
            addInterceptor(getQueryInterceptor())
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getQueryInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        chain.proceed(request)
    }

}