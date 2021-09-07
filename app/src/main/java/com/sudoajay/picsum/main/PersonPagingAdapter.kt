package com.sudoajay.picsum.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sudoajay.picsum.databinding.LayoutPersonBinding
import com.sudoajay.picsum.main.model.PersonJackson
import javax.inject.Inject


class PersonPagingAdapter @Inject constructor() :
    PagingDataAdapter<PersonJackson, PersonPagingAdapter.PersonViewHolder>(Person_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            LayoutPersonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class PersonViewHolder(private val binding: LayoutPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(personJackson: PersonJackson) {

        }
    }

    companion object {
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<PersonJackson>() {
            override fun areItemsTheSame(oldItem: PersonJackson, newItem: PersonJackson): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PersonJackson, newItem: PersonJackson): Boolean =
                oldItem == newItem
        }
    }
}