package com.sudoajay.picsum.main.repository.noDatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.model.remote.PersonJackson
import com.sudoajay.picsum.main.model.remote.PersonMoshi
import com.sudoajay.picsum.main.repository.PersonViewHolder
import javax.inject.Inject


class PersonListAdapter @Inject constructor(
    private var mainActivity: MainActivity
) :
    RecyclerView.Adapter<PersonViewHolder>() {
    var personJackson: List<PersonJackson> = listOf()
    var personGson: List<PersonGson> = listOf()
    var personMoshi: List<PersonMoshi> =  listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            mainActivity,
            LayoutPersonListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )



    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        when {
            personJackson.isNotEmpty() -> {
                val paymentBean: PersonJackson = personJackson[position]
                holder.bind(paymentBean)
            }
            personGson.isNotEmpty() -> {
                val paymentBean: PersonGson = personGson[position]
                holder.bind(paymentBean)
            }
            else -> {
                val paymentBean: PersonMoshi = personMoshi[position]
                holder.bind(paymentBean)
            }
        }

    }

    override fun getItemCount(): Int =
        when {
            personJackson.isNotEmpty() -> personJackson.size
            personGson.isNotEmpty() -> personGson.size
            else -> personMoshi.size
        }



}