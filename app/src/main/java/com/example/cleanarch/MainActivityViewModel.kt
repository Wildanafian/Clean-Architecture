package com.example.cleanarch

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarch.model.NewsData
import com.example.cleanarch.network.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repo : MainActivityRepository) : ViewModel() {

    private var _data : MutableLiveData<ArrayList<NewsData>> = MutableLiveData()
    private var data = _data

    private var _errorMessage : MutableLiveData<String> = MutableLiveData()
    private var errorMessage = _errorMessage

    var loading : MutableLiveData<Int> = MutableLiveData()

    init {
        getData()
    }

    fun getData(){
        viewModelScope.launch {
            repo.getData().collect {
                when(it.status){
                    Resource.Status.SUCCESS ->{
                        loading.value = View.GONE
                        _data.value = it.data
                    }
                    Resource.Status.ERROR ->{
                        loading.value = View.GONE
                        _errorMessage.value = it.message
                    }
                    Resource.Status.LOADING ->{
                        loading.value = View.VISIBLE
                    }
                }
            }
        }
    }

    fun newsData() = data

    fun errorMessage() = errorMessage
}