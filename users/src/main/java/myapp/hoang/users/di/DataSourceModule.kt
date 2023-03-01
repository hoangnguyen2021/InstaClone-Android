package myapp.hoang.users.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.users.datasources.UsersRemoteDataSource
import myapp.hoang.users.datasources.UsersRemoteDataSourceImpl
import myapp.hoang.users.services.UsersService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideUsersRemoteDataSource(
        usersService: UsersService,
        dispatcherProvider: DispatcherProvider
    ): UsersRemoteDataSource {
        return UsersRemoteDataSourceImpl(usersService, dispatcherProvider)
    }
}