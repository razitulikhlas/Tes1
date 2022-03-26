package com.proyekakhir.core.data.source

import com.proyekakhir.core.data.source.local.SuiteDao
import com.proyekakhir.core.data.source.local.UserEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val suiteDao: SuiteDao) {
    fun getListUser(): Flow<List<UserEntity>> = suiteDao.getListUser()
    suspend fun insertUser(list: List<UserEntity>) = suiteDao.insertAll(list)
}