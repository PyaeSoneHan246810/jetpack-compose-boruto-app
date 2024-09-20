package com.example.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.example.borutoapp.data.local.dao.HeroDao
import com.example.borutoapp.data.local.dao.HeroRemoteKeyDao
import com.example.borutoapp.data.local.database.HeroesDatabase
import com.example.borutoapp.data.local.typeConverter.HeroTypeConverter
import com.example.borutoapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideHeroesDatabase(
        @ApplicationContext context: Context
    ): HeroesDatabase {
        return Room
            .databaseBuilder(
                context = context,
                klass = HeroesDatabase::class.java,
                name = Constants.DATABASE_HEROES
            )
            .addTypeConverter(HeroTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideHeroDao(
        heroesDatabase: HeroesDatabase
    ): HeroDao {
        return heroesDatabase.heroDao
    }

    @Provides
    @Singleton
    fun provideHeroRemoteKeyDao(
        heroesDatabase: HeroesDatabase
    ): HeroRemoteKeyDao {
        return heroesDatabase.heroRemoteKeyDao
    }
}