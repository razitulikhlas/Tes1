package com.proyekakhir.core.domain.repository


import com.proyekakhir.core.data.source.Resource
import com.proyekakhir.core.data.source.local.UserEntity
import com.proyekakhir.core.domain.model.Events
import kotlinx.coroutines.flow.Flow

interface IEventRepository{
    fun getEvents() : Flow<List<Events>>
    fun getUsers():Flow<Resource<List<UserEntity>>>
}