package com.igorvd.baseproject.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.igorvd.baseproject.data.network.interceptor.getSimpleLogging
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import com.igorvd.baseproject.domain.BuildConfig

/**
 * Helper class that creates retrofit clients
 * @author Igor Vilela
 * @since 12/10/17
 */
class ApiClientBuilder {

    companion object {

        //  private val HEADER_TIMEZONE = "Timezone"

        private lateinit var sRetrofit: Retrofit
        private lateinit var sHttpClientBuilder: OkHttpClient.Builder

        fun <S> createService(
            serviceClass: Class<S>,
            baseUrl: String,
            gson: Gson = Gson(),
            vararg interceptors: Interceptor
        ): S {

            sHttpClientBuilder = OkHttpClient.Builder()

            interceptors.forEach { interceptor -> sHttpClientBuilder.addInterceptor(interceptor) }

            /*sHttpClientBuilder.addInterceptor ({
                chain : Interceptor.Chain ->

                val original = chain.request()

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                        .header(HEADER_TIMEZONE, "America/Sao_Paulo")
                        .method(original.method(), original.body())

                val request = requestBuilder.build()
                chain.proceed(request)
            })*/

            if (BuildConfig.DEBUG) {

                // Critical part, LogClient must be last one if you have more interceptors
                sHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().getSimpleLogging())

            }

            val client = sHttpClientBuilder
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()
            sRetrofit = getClientBuilder(baseUrl, gson).client(client)
                .build()
            return sRetrofit.create(serviceClass)
        }

        private fun getClientBuilder(baseUrl: String, gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
        }
    }

}