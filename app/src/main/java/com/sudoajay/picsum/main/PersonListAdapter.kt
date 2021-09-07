package com.sudoajay.picsum.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutPersonBinding
import com.sudoajay.picsum.main.model.PersonGson
import com.sudoajay.picsum.main.model.PersonJackson

class PersonListAdapter(var personJacksons: List<PersonJackson>, var personGson: List<PersonGson>) :
    RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            LayoutPersonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    inner class PersonViewHolder(private val binding: LayoutPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bind(personJackson: PersonJackson) {
            Picasso.get()
                .load(personJackson.downloadUrl)
                .resize(100, 100)
                .error(R.drawable.ic_me)
                .centerCrop()
                .into(binding.personImageImageView)

            binding.personNameTextView.text = personJackson.name

            binding.personSizeTextView.text = "(${personJackson.width} * ${personJackson.height})"
        }

        @SuppressLint("SetTextI18n")
        fun bind(personGson: PersonGson) {
            Picasso.get()
                .load(personGson.downloadUrl)
                .resize(100, 100)
                .error(R.drawable.ic_me)
                .centerCrop()
                .into(binding.personImageImageView)
            binding.personNameTextView.text = personGson.name

            binding.personSizeTextView.text = "(${personGson.width} * ${personGson.height})"
        }
    }


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