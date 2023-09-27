package com.fahmi.androidexpertgame.core.di

import androidx.room.Room
import com.fahmi.androidexpertgame.core.data.GameRepository
import com.fahmi.androidexpertgame.core.data.source.local.LocalDataSource
import com.fahmi.androidexpertgame.core.data.source.local.room.GameDatabase
import com.fahmi.androidexpertgame.core.data.source.remote.RemoteDataSource
import com.fahmi.androidexpertgame.core.data.source.remote.network.ApiService
import com.fahmi.androidexpertgame.core.domain.repository.IGameRepository
import com.fahmi.androidexpertgame.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("fahmi".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
//            .add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=") // Untuk Mengetahui kunci kunci sha dibawah pada logcat
            .add(hostname, "sha256/lwtzFdQjeD+EVzKCXKhXN6jZ1kiSkDrwxDsujuYErho=")
            .add(hostname, "sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
            .add(hostname, "sha256/K87oWBWM9UZfyddvDfoxL+8lpNyoUB2ptGtn0fv6G2Q=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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