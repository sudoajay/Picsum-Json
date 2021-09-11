package com.sudoajay.picsum.main.repository.remoteMediator

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.model.local.PersonLocalGson
import com.sudoajay.picsum.main.model.local.PersonLocalMoshi
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.repository.PersonViewHolder


class PersonLocalPagingAdapterMoshi(
    private var mainActivity: MainActivity

) :
    PagingDataAdapter<PersonLocalMoshi, PersonViewHolder>(Person_COMPARATOR) {
    private var TAG= "PersonLocalPagingTAG"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            mainActivity,
            LayoutPersonListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        Log.e(TAG, "bind:  I m here Gson  -- post $position" )

        getItem(position)?.let { holder.bind(it) }
    }



    companion object {
        private val Person_COMPARATOR = object : DiffUtil.ItemCallback<PersonLocalMoshi>() {
            override fun areItemsTheSame(oldItem: PersonLocalMoshi, newItem: PersonLocalMoshi): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: PersonLocalMoshi, newItem: PersonLocalMoshi): Boolean =
                oldItem == newItem
        }
    }


}