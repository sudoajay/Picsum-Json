package com.sudoajay.picsum.main.repository.pagingSource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.model.remote.PersonMoshi
import com.sudoajay.picsum.main.repository.PersonViewHolder
import javax.inject.Inject


class PersonPagingAdapterMoshi @Inject constructor(
) :
    PagingDataAdapter<PersonMoshi, PersonViewHolder>(Person_COMPARATOR) {

    lateinit var mainActivity:MainActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            mainActivity,
            LayoutPersonListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }



    companion object {
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<PersonMoshi>() {
            override fun areItemsTheSame(oldItem: PersonMoshi, newItem: PersonMoshi): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PersonMoshi, newItem: PersonMoshi): Boolean =
                oldItem == newItem
        }
    }


}