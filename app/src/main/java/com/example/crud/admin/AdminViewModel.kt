package com.example.crud.admin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminViewModel(application: Application) : AndroidViewModel(application){
    val readAdmin : LiveData<List<Superadmin>>
    private val repository:AdminRepository

    init {
        val adminDao = AdminDatabase.getDatabase(application).AdminUserDao()
        repository = AdminRepository(adminDao)
        readAdmin = repository.adminData
    }
    fun addAdmin(admin:Superadmin)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.addAdmin(admin)
        }
    }

}