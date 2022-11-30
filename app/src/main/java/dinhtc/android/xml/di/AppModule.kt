package dinhtc.android.xml.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dinhtc.android.xml.BuildConfig.DEBUG
import dinhtc.android.xml.repository.categoryImpl.CategoryServiceImpl
import dinhtc.android.xml.repository.productImpl.ProductServiceImpl
import dinhtc.android.xml.service.ApiService
import dinhtc.android.xml.ultils.Constants.BASE_URL
import dinhtc.android.xml.ultils.Constants.CONNECT_TIMEOUT
import dinhtc.android.xml.ultils.Constants.READ_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providerApiInterface(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)

        val logging = HttpLoggingInterceptor()
        if (DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }

        okHttpClient.addInterceptor(logging)
        return okHttpClient.build()
    }

    /*
     context in @Module :
        (@ApplicationContext context: Context)
     */

    @Provides
    @Singleton
    fun categoryServicesImpl(@ApplicationContext context: Context) = CategoryServiceImpl(context)

    @Provides
    @Singleton
    fun productServiceImpl(@ApplicationContext context: Context) = ProductServiceImpl(context)
}