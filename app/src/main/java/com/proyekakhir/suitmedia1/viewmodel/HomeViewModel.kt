package com.proyekakhir.suitmedia1.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.proyekakhir.core.data.source.Resource
import com.proyekakhir.core.data.source.local.UserEntity
import com.proyekakhir.core.domain.usecase.UseCaseEvent

class HomeViewModel(private val useCase: UseCaseEvent):ViewModel() {
    val event = useCase.getEvents().asLiveData()

    val users = MediatorLiveData<Resource<List<UserEntity>>>()

    fun getUsers() {
        users.addSource(useCase.getUsers().asLiveData()) {
            users.value = it
        }
    }
}