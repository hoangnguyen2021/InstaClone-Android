package myapp.hoang.instaclone.features.users.datasources

import kotlinx.coroutines.withContext
import myapp.hoang.core.coroutines.DispatcherProvider
import myapp.hoang.instaclone.features.users.services.UsersService
import myapp.hoang.core.models.InstaCloneUser
import javax.inject.Inject

class UsersRemoteDataSourceImpl @Inject constructor(
    private val usersService: UsersService,
    private val dispatcherProvider: DispatcherProvider
): UsersRemoteDataSource {
    override suspend fun getUserById(id: String): InstaCloneUser {
        return withContext(dispatcherProvider.io) {
            usersService.getUserById(id)
        }
    }
}