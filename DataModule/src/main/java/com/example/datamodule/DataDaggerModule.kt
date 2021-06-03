package com.example.datamodule

import android.content.Context
import com.example.datamodule.database.HabitsDB
import com.example.datamodule.database.IHabitsDatabase
import com.example.datamodule.network.HabitsRetrofitClient
import com.example.domainmodule.HabitsUseCases
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataDaggerModule(private val context: Context) {
    private val BASE_URL = "https://droid-test-server.doubletapp.ru/api/"

    @Singleton
    @Provides
    fun provideRepository(db: IHabitsDatabase, rf: Retrofit): Repository {
        return Repository(db.habitsDao(), rf)
    }

    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideDataBase(): IHabitsDatabase {
        return HabitsDB.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return HabitsRetrofitClient().getClient(BASE_URL)
    }

    @Singleton
    @Provides
    fun provideHabitsInteractor(repo: Repository): HabitsUseCases {
        return HabitsUseCases(repo)
    }
}