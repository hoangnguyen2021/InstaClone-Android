package myapp.hoang.users.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import myapp.hoang.users.datasources.UsersRemoteDataSource
import myapp.hoang.users.repositories.UsersRepository
import myapp.hoang.users.repositories.UsersRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUsersRepository(usersRemoteDataSource: UsersRemoteDataSource): UsersRepository {
        return UsersRepositoryImpl(usersRemoteDataSource)
    }
}