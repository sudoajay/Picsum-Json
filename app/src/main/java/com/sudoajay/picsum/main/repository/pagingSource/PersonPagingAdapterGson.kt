package com.sudoajay.picsum.main.repository.pagingSource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.repository.PersonViewHolder


class PersonPagingAdapterGson(
    private var mainActivity: MainActivity

) :
    PagingDataAdapter<PersonGson, PersonViewHolder>(Person_COMPARATOR) {


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
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<PersonGson>() {
            override fun areItemsTheSame(oldItem: PersonGson, newItem: PersonGson): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PersonGson, newItem: PersonGson): Boolean =
                oldItem == newItem
        }
    }


}