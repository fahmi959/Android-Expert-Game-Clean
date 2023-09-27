package com.fahmi.androidexpertgame.core.di

import androidx.room.Room
import com.fahmi.androidexpertgame.core.data.GameRepository
import com.fahmi.androidexpertgame.core.data.source.local.LocalDataSource
import com.fahmi.androidexpertgame.core.data.source.local.room.GameDatabase
import com.fahmi.androidexpertgame.core.data.source.remote.RemoteDataSource
import com.fahmi.androidexpertgame.core.data.source.remote.network.ApiService
import com.fahmi.androidexpertgame.core.domain.repository.IGameRepository
import com.fahmi.androidexpertgame.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.fahmi.androidexpertgame.core.data.source.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGameRepository> { com.fahmi.androidexpertgame.core.data.GameRepository(get(), get(), get()) }
}