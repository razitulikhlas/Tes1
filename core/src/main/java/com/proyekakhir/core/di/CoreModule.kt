package com.proyekakhir.core.di

import androidx.room.Room
import com.proyekakhir.core.BuildConfig
import com.proyekakhir.core.data.source.LocalDataSource
import com.proyekakhir.core.data.source.RemoteDataSource
import com.proyekakhir.core.data.source.local.DatabaseSuite
import com.proyekakhir.core.data.source.remote.network.ApiService
import com.proyekakhir.core.domain.repository.EventRepository
import com.proyekakhir.core.domain.usecase.EventIteractor
import com.proyekakhir.core.domain.usecase.UseCaseEvent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val coreModule = module {
    single { EventRepository(get(), get()) }
    factory<UseCaseEvent> { EventIteractor(get()) }
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build().create(ApiService::class.java)
    }
    factory { RemoteDataSource(get()) }
    factory {
        get<DatabaseSuite>().suiteDao
    }
    single {
//        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
//        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), DatabaseSuite::class.java, "db_SUITE")
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { LocalDataSource(get()) }
}