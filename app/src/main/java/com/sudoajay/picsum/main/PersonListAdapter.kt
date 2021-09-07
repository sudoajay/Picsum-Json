package com.sudoajay.picsum.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutPersonBinding
import com.sudoajay.picsum.main.model.Person

class PersonListAdapter (var person:List<Person>) : RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            LayoutPersonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    inner class PersonViewHolder(private val binding: LayoutPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bind(person: Person) {
            binding.person = person

            Picasso.get()
                .load(person.downloadUrl)
                .resize(100, 100)
                .error(R.drawable.ic_me)
                .centerCrop()
                .into(binding.personImageImageView)

            binding.personSizeTextView.text = "(${person.width} * ${person.height})"
        }
    }


    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val paymentBean: Person= person[position]
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = person.size


}