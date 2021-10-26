package com.example.stackoverflobiz.tags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.stackoverflobiz.databinding.FragmentTagsDialogBinding
import com.example.stackoverflobiz.questions.QuestionViewModel
import com.example.stackoverflobiz.questions.QuestionsAdapter
import com.example.stackoverflobiz.utils.Resource
import com.example.stackoverflobiz.utils.openBrowser
import com.example.stackoverflobiz.utils.showSnackBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TagsDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTagsDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionViewModel by viewModels()
    private lateinit var filterAdapter: QuestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTagsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        binding.apply {

            closeDetailBtn.setOnClickListener {
                dismiss()
            }

            btnApplyFilter.setOnClickListener {
                tagsInput.editText?.text?.toString()?.trim()?.let { tags ->
                    tagsInput.error = null
                    viewModel.getFilteredQuestions(tags)
                } ?: run { tagsInput.error = "Required" }
            }
        }

        viewModel.filteredQuestions.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> binding.apply {
                    searchingProgressBar.isGone = true
                    errorLayout.isGone = false
                    btnApplyFilter.text = "Apply Filter"
                    showSnackBar(it.message!!)
                }
                is Resource.Loading -> binding.apply {
                    searchingProgressBar.isGone = false
                    errorLayout.isGone = true
                    btnApplyFilter.text = "Fetching..."
                    emptySearch.isGone = true
                    filteredResultRv.isGone = true
                }
                is Resource.Success -> binding.apply {
                    btnApplyFilter.text = "Apply Filter"
                    searchingProgressBar.isGone = true

                    if (it.data!!.questions.isNotEmpty()) {
                        filterAdapter.submitList(it.data.questions)
                        filteredResultRv.isGone = false
                        emptySearch.isGone = true
                    } else {
                        emptySearch.isGone = false
                        filteredResultRv.isGone = true
                    }
                }
            }
        }

    }

    private fun setUpRecyclerView() = binding.apply {

        filteredResultRv.setHasFixedSize(true)
        filterAdapter = QuestionsAdapter {
            openBrowser(it, requireContext())
        }
        filteredResultRv.adapter = filterAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}