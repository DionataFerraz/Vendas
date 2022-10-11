package br.com.dionataferraz.vendas.data.remote

import br.com.dionataferraz.vendas.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val DURATION = 60L

object RetrofitNetworkClient {

    fun createNetworkClient(baseUrl: String = BuildConfig.HTTP_SERVER) =
        retrofitClient(
            baseUrl = baseUrl,
            httpClient = httpClient(),
            moshiConverter = moshi(),
        )

    private fun moshi() = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    private fun httpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .connectTimeout(DURATION, TimeUnit.SECONDS)
            .readTimeout(DURATION, TimeUnit.SECONDS)
            .writeTimeout(DURATION, TimeUnit.SECONDS)
            .build()

    private fun loggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    private fun retrofitClient(
        baseUrl: String,
        httpClient: OkHttpClient,
        moshiConverter: MoshiConverterFactory
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(moshiConverter)
        .build()
}