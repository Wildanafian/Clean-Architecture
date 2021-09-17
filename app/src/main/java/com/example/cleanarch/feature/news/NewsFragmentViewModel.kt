package com.example.cleanarch.feature.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarch.model.NewsData
import com.example.cleanarch.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFragmentViewModel @Inject constructor(private val repo: NewsFragmentRepository) : ViewModel() {

    private var _data : MutableLiveData<ArrayList<NewsData>> = MutableLiveData()
    private var data = _data

    private var _errorMessage : MutableLiveData<String> = MutableLiveData()
    var errorMessage = _errorMessage

    private var _loading : MutableLiveData<Boolean> = MutableLiveData()
    private var loading : LiveData<Boolean> = _loading

    init {
        getData()
    }

    fun getData(){
        viewModelScope.launch {
            repo.getData().collect {
                when(it.status){
                    Resource.Status.SUCCESS ->{
                        _loading.value = false
                        _data.value = it.data
                    }
                    Resource.Status.ERROR ->{
                        _loading.value = false
                        _errorMessage.value = it.message
                    }
                    Resource.Status.LOADING ->{
                        _loading.value = true
                    }
                }
            }
        }
    }

    fun newsData() = data

    fun loading() = loading

    fun errorMessage() = errorMessage
}