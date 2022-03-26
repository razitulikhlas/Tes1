package com.proyekakhir.core.domain.usecase

import com.proyekakhir.core.data.source.Resource
import com.proyekakhir.core.data.source.local.UserEntity
import com.proyekakhir.core.domain.model.Events
import kotlinx.coroutines.flow.Flow

interface UseCaseEvent {
    fun getEvents() : Flow<List<Events>>
    fun getUsers():Flow<Resource<List<UserEntity>>>
}