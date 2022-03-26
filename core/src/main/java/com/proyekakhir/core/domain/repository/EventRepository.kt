package com.proyekakhir.core.domain.repository


import com.proyekakhir.core.data.source.LocalDataSource
import com.proyekakhir.core.data.source.NetworkBoundResource
import com.proyekakhir.core.data.source.RemoteDataSource
import com.proyekakhir.core.data.source.Resource
import com.proyekakhir.core.data.source.local.UserEntity
import com.proyekakhir.core.data.source.remote.network.ApiResponse
import com.proyekakhir.core.data.source.remote.response.DataItem
import com.proyekakhir.core.domain.model.Events
import com.proyekakhir.core.utils.Data
import com.proyekakhir.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IEventRepository {
    override fun getEvents(): Flow<List<Events>> {
        return flow {
            emit(Data.generateDataDummy())
        }
    }

    override fun getUsers(): Flow<Resource<List<UserEntity>>> = object : NetworkBoundResource<List<UserEntity>, List<DataItem>>() {
        override fun loadFromDB(): Flow<List<UserEntity>> {
            return localDataSource.getListUser()
        }

        override fun shouldFetch(data: List<UserEntity>?): Boolean =
            data == null || data.isEmpty()

        override suspend fun createCall(): Flow<ApiResponse<List<DataItem>>> =
            remoteDataSource.getUser()

        override suspend fun saveCallResult(data: List<DataItem>) {
            val listUser= DataMapper.mapResponseToUserEntity(data)
            localDataSource.insertUser(listUser)
        }

    }.asFlow()


}