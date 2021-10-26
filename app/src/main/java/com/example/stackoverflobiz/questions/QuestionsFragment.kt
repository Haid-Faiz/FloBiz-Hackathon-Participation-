package com.example.stackoverflobiz.questions

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.datasource.models.Question
import com.example.stackoverflobiz.R
import com.example.stackoverflobiz.databinding.FragmentQuestionsBinding
import com.example.stackoverflobiz.utils.Resource
import com.example.stackoverflobiz.utils.openBrowser
import com.example.stackoverflobiz.utils.safeFragmentNavigation
import com.example.stackoverflobiz.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuestionViewModel by viewModels()
    private var job: Job? = null
    private lateinit var trendingAdapter: QuestionsAdapter
    private lateinit var searchedAdapter: QuestionsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpClickListeners()

        viewModel.trendingQuestions.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> showSnackBar(it.message!!)
                is Resource.Loading -> binding.apply {
//                    // Stop Shimmer
//                    shimmerProgress.isGone = false
//                    shimmerProgress.stopShimmer()
                }
                is Resource.Success -> binding.apply {
                    // Stop Shimmer
                    shimmerProgress.isGone = true
                    shimmerProgress.stopShimmer()
                    trendingRv.isGone = false
                    trendingAdapter.submitList(it.data!!.questions)
                }
            }
        }


        binding.searchQueryEt.doOnTextChanged { text, _, _, _ ->
            binding.apply {
                text?.let {
                    if (it.trim().isNotEmpty() && it.trim().isNotBlank()) {
                        eraseQueryBtn.isGone = false
                        querySearchIcon.isVisible = false
                        trendingRv.isGone = true
                        // Show searching progress bar
                        searchText.text = "Searched Results"
                        searchingProgressBar.isGone = false
                        emptySearch.isGone = true
                        errorLayout.isGone = true
                        searchedResultRv.isGone = true // Make it visible on getting results
                        performSearch(it.trim().toString())
                    } else {
                        eraseQueryBtn.isGone = true
                        querySearchIcon.isVisible = true
                        searchText.text = "Trending Questions"
                        trendingRv.isGone = false
                        searchedResultRv.isGone = true
                    }
                }
            }
        }

        viewModel.searchedQuestions.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> binding.apply {
                    searchingProgressBar.isGone = true
                    errorLayout.isGone = false
                    showSnackBar(it.message!!)
                }
                is Resource.Loading -> binding.apply {}
                is Resource.Success -> binding.apply {
                    searchingProgressBar.isGone = true
                    if (it.data!!.questions.isNotEmpty()) {
                        searchedAdapter.submitList(it.data.questions)
                        searchedResultRv.isGone = false
                        emptySearch.isGone = true
                    } else {
                        emptySearch.isGone = false
                        searchedResultRv.isGone = true
                    }
                }
            }
        }
    }


    private fun performSearch(searchQuery: String) {
        job?.cancel()
        job = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            // first let's wait for change in input search query
            delay(800)
            viewModel.getSearchedQuestions(searchQuery = searchQuery)
        }
    }

    private fun setUpClickListeners() = binding.apply {
        eraseQueryBtn.setOnClickListener {
            binding.searchQueryEt.text.clear()
            hideKeyboard()
        }

        filterIcon.setOnClickListener {
            safeFragmentNavigation(
                navController = findNavController(),
                currentFragmentId = R.id.questionsFragment,
                actionId = R.id.action_questionsFragment_to_tagsDialogFragment
            )
        }
    }

    private fun setUpRecyclerView() = binding.apply {
        // Setting up All Questions Results RecyclerView
        trendingRv.setHasFixedSize(true)
        trendingAdapter = QuestionsAdapter {
            openBrowser(it, requireContext())
        }
        trendingRv.adapter = trendingAdapter
        // Setting up searched Results RecyclerView
        searchedResultRv.setHasFixedSize(true)
        searchedAdapter = QuestionsAdapter {
            openBrowser(it, requireContext())
        }
        searchedResultRv.adapter = searchedAdapter
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}