package com.example.stackoverflobiz.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datasource.models.QuestionsListResponse
import com.example.stackoverflobiz.data.QuestionsRepo
import com.example.stackoverflobiz.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionRepo: QuestionsRepo,
) : ViewModel() {

    private val _trendingQuestions = MutableLiveData<Resource<QuestionsListResponse>>()
    val trendingQuestions: LiveData<Resource<QuestionsListResponse>> = _trendingQuestions

    init {
        getTrendingQuestions()
    }



    private val _searchedQuestions = MutableLiveData<Resource<QuestionsListResponse>>()
    val searchedQuestions: LiveData<Resource<QuestionsListResponse>> = _searchedQuestions

    private val _filteredQuestions = MutableLiveData<Resource<QuestionsListResponse>>()
    val filteredQuestions: LiveData<Resource<QuestionsListResponse>> = _filteredQuestions

    private fun getTrendingQuestions() = viewModelScope.launch {
        _trendingQuestions.postValue(Resource.Loading())
        _trendingQuestions.postValue(questionRepo.fetchAllQuestions())
    }

    fun getSearchedQuestions(searchQuery: String) = viewModelScope.launch {
        _searchedQuestions.postValue(Resource.Loading())
        _searchedQuestions.postValue(questionRepo.getSearchedQuestions(searchQuery))
    }

    fun getFilteredQuestions(tags: String) = viewModelScope.launch {
        _filteredQuestions.postValue(Resource.Loading())
        _filteredQuestions.postValue(questionRepo.getFilteredQuestionsByTags(tags = tags))
    }
}