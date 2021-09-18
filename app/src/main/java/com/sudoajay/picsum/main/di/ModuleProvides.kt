package com.sudoajay.picsum.main.di

import com.sudoajay.picsum.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleProvides {

    @Singleton
    @Provides
    fun providesMainActivity():MainActivity = MainActivity()



}

