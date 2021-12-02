package com.example.cleanarch.presentation

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarch.data.model.NewsData
import com.example.cleanarch.data.network.Resource
import com.example.cleanarch.domain.MainActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val useCase: MainActivityUseCase) :
    ViewModel() {

    private var _data: MutableLiveData<List<NewsData>> = MutableLiveData()
    private var data = _data

    private var _errorMessage: MutableLiveData<String> = MutableLiveData()
    private var errorMessage = _errorMessage

    var loading: MutableLiveData<Int> = MutableLiveData()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            useCase.getNews().collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        _data.value = it.data
                        loading.value = View.GONE
                    }
                    Resource.Status.ERROR -> {
                        loading.value = View.GONE
                        _errorMessage.value = it.message
                    }
                    Resource.Status.LOADING -> {
                        loading.value = View.VISIBLE
                    }
                    Resource.Status.COMPLETE -> {
                        loading.value = View.GONE
                    }
                }
            }
        }
    }

    fun newsData() = data

    fun errorMessage() = errorMessage
}