package com.erp.distribution.sfa.data.di


import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.erp.distribution.sfa.data.source.remote.service_api.*
import com.erp.distribution.sfa.utils.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {


    @RequiresPermission(value = Manifest.permission.INTERNET)
    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava3CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                /* If there is Internet, get the cache that was stored 5 seconds ago.
                 * If the cache is older than 5 seconds, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-age' attribute is responsible for this behavior.
                 */
                request = if (true) request.newBuilder() //make default to true till i figure out how to inject network status
                    .header("Cache-Control", "public, max-age=" + 5).build()
                /*If there is no Internet, get the cache that was stored 7 days ago.
                 * If the cache is older than 7 days, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-stale' attribute is responsible for this behavior.
                 * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                 */
                else request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
                chain.proceed(request)
            }
        return client.build()
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        return GsonConverterFactory
                .create(gsonBuilder)
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

//    @Provides
//    @Singleton
//    fun provideIsNetworkAvailable(@ApplicationContext context: Context): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//        return activeNetwork != null && activeNetwork.isConnected
//    }


    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
    @Singleton
    @Provides
    fun provideServiceSecurity(retrofit: Retrofit): RetrofitServiceSecurity {
        return retrofit.create(RetrofitServiceSecurity::class.java)
    }

    @Singleton
    @Provides
    fun provideServiceFArea(retrofit: Retrofit): RetrofitServiceFArea = retrofit.create(
        RetrofitServiceFArea::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFSubArea(retrofit: Retrofit): RetrofitServiceFSubArea = retrofit.create(
        RetrofitServiceFSubArea::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFCompany(retrofit: Retrofit): RetrofitServiceFCompany = retrofit.create(
        RetrofitServiceFCompany::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFDivision(retrofit: Retrofit): RetrofitServiceFDivision = retrofit.create(
        RetrofitServiceFDivision::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFCustomer(retrofit: Retrofit): RetrofitServiceFCustomer = retrofit.create(
        RetrofitServiceFCustomer::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFCustomerGroup(retrofit: Retrofit): RetrofitServiceFCustomerGroup = retrofit.create(
        RetrofitServiceFCustomerGroup::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFCustomerPic(retrofit: Retrofit): RetrofitServiceFCustomerPic = retrofit.create(
        RetrofitServiceFCustomerPic::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFMaterial(retrofit: Retrofit): RetrofitServiceFMaterial = retrofit.create(
        RetrofitServiceFMaterial::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFMaterialGroup1(retrofit: Retrofit): RetrofitServiceFMaterialGroup1 = retrofit.create(
        RetrofitServiceFMaterialGroup1::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFMaterialGroup2(retrofit: Retrofit): RetrofitServiceFMaterialGroup2 = retrofit.create(
        RetrofitServiceFMaterialGroup2::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFMaterialGroup3(retrofit: Retrofit): RetrofitServiceFMaterialGroup3 = retrofit.create(
        RetrofitServiceFMaterialGroup3::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFMaterialPic(retrofit: Retrofit): RetrofitServiceFMaterialPic = retrofit.create(
        RetrofitServiceFMaterialPic::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFMaterialSalesBrand(retrofit: Retrofit): RetrofitServiceFMaterialSalesBrand = retrofit.create(
        RetrofitServiceFMaterialSalesBrand::class.java
    )

    @Singleton
    @Provides
    fun provideServiceFSalesman(retrofit: Retrofit): RetrofitServiceFSalesman = retrofit.create(
        RetrofitServiceFSalesman::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFStock(retrofit: Retrofit): RetrofitServiceFStock = retrofit.create(
        RetrofitServiceFStock::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFTax(retrofit: Retrofit): RetrofitServiceFTax = retrofit.create(
        RetrofitServiceFTax::class.java
    )

    @Singleton
    @Provides
    fun provideServiceFtPriceAltdItems(retrofit: Retrofit): RetrofitServiceFtPriceAltdItems = retrofit.create(
            RetrofitServiceFtPriceAltdItems::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFtPriceAlth(retrofit: Retrofit): RetrofitServiceFtPriceAlth = retrofit.create(
            RetrofitServiceFtPriceAlth::class.java
    )

    @Singleton
    @Provides
    fun provideServiceFtSalesdItems(retrofit: Retrofit): RetrofitServiceFtSalesdItems = retrofit.create(
        RetrofitServiceFtSalesdItems::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFtSalesh(retrofit: Retrofit): RetrofitServiceFtSalesh = retrofit.create(
        RetrofitServiceFtSalesh::class.java
    )

    @Singleton
    @Provides
    fun provideServiceFVendor(retrofit: Retrofit): RetrofitServiceFVendor = retrofit.create(
        RetrofitServiceFVendor::class.java
    )
    @Singleton
    @Provides
    fun provideServiceFWarehouse(retrofit: Retrofit): RetrofitServiceFWarehouse = retrofit.create(
        RetrofitServiceFWarehouse::class.java
    )
    @Singleton
    @Provides
    fun provideServiceSysvar(retrofit: Retrofit): RetrofitServiceSysvar = retrofit.create(
        RetrofitServiceSysvar::class.java
    )

}


