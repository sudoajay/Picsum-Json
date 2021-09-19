package com.sudoajay.picsum.main.di

import android.content.Context
import com.sudoajay.picsum.main.proto.ProtoManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleProvides {

    @Singleton
    @Provides
    fun providesProtoManger( @ApplicationContext appContext: Context):ProtoManager = ProtoManager(appContext)



}

