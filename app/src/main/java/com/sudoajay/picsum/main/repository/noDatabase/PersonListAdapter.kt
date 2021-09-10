package com.sudoajay.picsum.main.repository.noDatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.model.remote.PersonJackson
import com.sudoajay.picsum.main.repository.PersonViewHolder


class PersonListAdapter(
    private var mainActivity: MainActivity,
    var personJacksons: List<PersonJackson>,
    var personGson: List<PersonGson>
) :
    RecyclerView.Adapter<PersonViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder( mainActivity,
            LayoutPersonListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )



    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        if (personJacksons.isNotEmpty()) {
            val paymentBean: PersonJackson = personJacksons[position]
            holder.bind(paymentBean)
        } else {
            val paymentBean: PersonGson = personGson[position]
            holder.bind(paymentBean)
        }
    }

    override fun getItemCount(): Int =
        if (personJacksons.isNotEmpty()) personJacksons.size else personGson.size



}