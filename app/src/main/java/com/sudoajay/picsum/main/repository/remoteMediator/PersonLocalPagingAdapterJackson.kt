package com.sudoajay.picsum.main.repository.remoteMediator

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.model.local.PersonLocalJackson
import com.sudoajay.picsum.main.repository.PersonViewHolder
import javax.inject.Inject


class PersonLocalPagingAdapterJackson @Inject constructor(
    private var mainActivity: MainActivity

) :
    PagingDataAdapter<PersonLocalJackson, PersonViewHolder>(Person_COMPARATOR) {
    private var TAG= "PersonLocalPagingTAG"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            mainActivity,
            LayoutPersonListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        Log.e(TAG, "bind:  I m here -- post $position" )

        getItem(position)?.let { holder.bind(it) }
    }


    companion object {
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<PersonLocalJackson>() {
            override fun areItemsTheSame(
                oldItem: PersonLocalJackson,
                newItem: PersonLocalJackson
            ): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: PersonLocalJackson,
                newItem: PersonLocalJackson
            ): Boolean =
                oldItem == newItem
        }
    }


}