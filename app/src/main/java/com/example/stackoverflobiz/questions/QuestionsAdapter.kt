package com.example.stackoverflobiz.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.datasource.models.Question
import com.example.stackoverflobiz.databinding.ItemAdvertiseBinding
import com.example.stackoverflobiz.databinding.ItemQuestionBinding
import com.example.stackoverflobiz.tags.TagsAdapter
import com.example.stackoverflobiz.utils.formatTime

class QuestionsAdapter(
    private var onItemClicked: ((question: Question) -> Unit)
) : ListAdapter<Question, QuestionsAdapter.ViewHolder>(DiffUtilCallback()) {

    private val ADVERTISE_TYPE = 1
    private val QUESTION_TYPE = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return if (viewType == ADVERTISE_TYPE) {
            ViewHolder(
                ItemAdvertiseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else
            ViewHolder(
                ItemQuestionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            when (binding) {
                is ItemQuestionBinding -> binding.apply {
                    questionText.text = question.title
                    authorImage.load(question.owner.profileImage)
                    author.text = question.owner.displayName
                    question.lastEditDate?.let {
                        timestamp.formatTime(it.toLong())
                    }
                    // Setting up Tags Adapter
                    val tagAdapter = TagsAdapter()
                    rvTags.setHasFixedSize(true)
                    rvTags.adapter = tagAdapter
                    tagAdapter.submitList(question.tags)
                    root.setOnClickListener {
                        onItemClicked(question)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 5 == 0) ADVERTISE_TYPE else QUESTION_TYPE
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Question>() {

        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean =
            oldItem.equals(newItem)
    }
}


//class HorizontalPagerAdapter(
//    private var onPosterClick: ((movieResult: MovieResult) -> Unit)? = null,
//) : PagingDataAdapter<MovieResult, HorizontalPagerAdapter.ViewHolder>(DiffUtilCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
//        ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//    )
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getItem(position)!!)
//    }
//
//    inner class ViewHolder(
//        private val binding: ItemPosterBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(movieResult: MovieResult) = binding.apply {
//            posterImage.load(TMDB_IMAGE_BASE_URL_W500.plus(movieResult.posterPath))
//            ratingText.text = String.format("%.1f", movieResult.voteAverage)
//            posterImage.setOnClickListener {
//                onPosterClick?.invoke(movieResult)
//            }
//        }
//    }
//
//    class DiffUtilCallback : DiffUtil.ItemCallback<MovieResult>() {
//
//        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean =
//            oldItem == newItem
//
//        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean =
//            oldItem.equals(newItem)
//    }
//}
