package myapp.hoang.users.repositories

import myapp.hoang.users.datasources.UsersRemoteDataSource
import myapp.hoang.core.models.InstaCloneUser
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersRemoteDataSource: UsersRemoteDataSource
): UsersRepository {
    override suspend fun getUserById(id: String): InstaCloneUser {
        return usersRemoteDataSource.getUserById(id)
    }
}