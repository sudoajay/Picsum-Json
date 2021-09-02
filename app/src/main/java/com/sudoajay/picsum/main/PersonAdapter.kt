package com.sudoajay.picsum.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sudoajay.picsum.databinding.LayoutPersonBinding
import com.sudoajay.picsum.main.model.Item
import com.sudoajay.picsum.main.model.Person
import javax.inject.Inject


class PersonAdapter @Inject constructor() :
    PagingDataAdapter<Person, PersonAdapter.PersonViewHolder>(Person_COMPARATOR) {


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

        fun bind(person: Person) = with(binding) {

        }
    }

    companion object {
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem.authorName == newItem.authorName

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem == newItem
        }
    }
}