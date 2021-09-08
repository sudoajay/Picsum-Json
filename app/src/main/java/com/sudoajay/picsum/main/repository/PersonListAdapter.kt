package com.sudoajay.picsum.main.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutPersonBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.bottomsheet.LongPressBottomSheet
import com.sudoajay.picsum.main.model.PersonGson
import com.sudoajay.picsum.main.model.PersonJackson


class PersonListAdapter(
    private var mainActivity: MainActivity,
    var personJacksons: List<PersonJackson>,
    var personGson: List<PersonGson>
) :
    RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonViewHolder(
            LayoutPersonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    inner class PersonViewHolder(private val binding: LayoutPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(personJackson: PersonJackson) {
            Picasso.get()
                .load(personJackson.downloadUrl)
                .resize(119, 119)
                .error(R.drawable.ic_me)
                .centerCrop()
                .into(binding.personImageImageView)

            binding.personNameTextView.text = personJackson.name

            binding.personSizeTextView.text = getSize(personJackson.width, personJackson.height)

            binding.boxConstraintLayout.setOnLongClickListener {
                openMoreSetting(personJackson.openUrl,personJackson.downloadUrl)
                true
            }

        }

        fun bind(personGson: PersonGson) {
            Picasso.get()
                .load(personGson.downloadUrl)
                .resize(120, 120)
                .error(R.drawable.ic_me)
                .centerCrop()
                .into(binding.personImageImageView)
            binding.personNameTextView.text = personGson.name

            binding.personSizeTextView.text = getSize(personGson.width, personGson.height)

            binding.boxConstraintLayout.setOnLongClickListener {
                openMoreSetting(personGson.openUrl,personGson.downloadUrl)
                true
            }
        }

        private fun getSize(width: Int, heigth: Int): String {
            return "($width * $heigth)"
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

    fun openMoreSetting(openUrl :String,downloadUrl :String ) {
        val longPressBottomSheet = LongPressBottomSheet(openUrl,downloadUrl )
        longPressBottomSheet.show(
            mainActivity.supportFragmentManager.beginTransaction(),
            longPressBottomSheet.tag
        )
    }

}