package com.example.stackoverflobiz.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.models.QuestionsListResponse
import com.example.stackoverflobiz.data.QuestionsRepo
import com.example.stackoverflobiz.utils.Resource
import kotlinx.coroutines.launch

class QuestionViewModel constructor(
    private val questionRepo: QuestionsRepo,
) : ViewModel() {

    private val _trendingQuestions = MutableLiveData<Resource<QuestionsListResponse>>()
    val trendingQuestions: LiveData<Resource<QuestionsListResponse>> = _trendingQuestions

    private val _searchedQuestions = MutableLiveData<Resource<QuestionsListResponse>>()
    val searchedQuestions: LiveData<Resource<QuestionsListResponse>> = _searchedQuestions

    fun getTrendingQuestions() = viewModelScope.launch {
        _trendingQuestions.postValue(Resource.Loading())
        _trendingQuestions.postValue(questionRepo.fetchAllQuestions())
    }

    fun getSearchedQuestions(searchQuery: String) = viewModelScope.launch {
        _searchedQuestions.postValue(Resource.Loading())
        _searchedQuestions.postValue(questionRepo.fetchSearchedQuestions(searchQuery))
    }
}