package com.proyekakhir.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SuiteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<UserEntity>)

    @Query("SELECT * FROM TBL_USER")
    fun getListUser(): Flow<List<UserEntity>>
}