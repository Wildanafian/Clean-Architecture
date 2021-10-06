package com.example.cleanarch.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarch.data.network.ApiInterface
import com.example.cleanarch.data.network.model.NewsData
import com.example.cleanarch.data.network.Resource
import com.example.cleanarch.domain.MainActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repo: MainActivityUseCase, apiInterface: ApiInterface) : ViewModel() {

    private var _data : MutableLiveData<List<NewsData>> = MutableLiveData()
    private var data = _data

    private var _errorMessage : MutableLiveData<String> = MutableLiveData()
    private var errorMessage = _errorMessage

    var loading : MutableLiveData<Int> = MutableLiveData()

    init {
        getData()
    }

    private fun getData(){
        viewModelScope.launch {
            repo.getNews().collect {
                when(it.status){
                    Resource.Status.SUCCESS ->{
                        _data.value = it.data
                        loading.value = View.GONE
                    }
                    Resource.Status.ERROR ->{
                        loading.value = View.GONE
                        _errorMessage.value = it.message
                    }
                    Resource.Status.LOADING ->{
                        loading.value = View.VISIBLE
                    }
                    Resource.Status.COMPLETE ->{
                        loading.value = View.GONE
                    }
                }
            }
        }
    }

    fun newsData() = data

    fun errorMessage() = errorMessage
}