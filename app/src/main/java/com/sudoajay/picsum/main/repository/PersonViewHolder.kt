package com.sudoajay.picsum.main.repository

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.bottomsheet.LongPressBottomSheet
import com.sudoajay.picsum.main.model.PersonGson
import com.sudoajay.picsum.main.model.PersonJackson

class PersonViewHolder(
    val mainActivity: MainActivity,
    private val binding: LayoutPersonListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(personJackson: PersonJackson) {
        Picasso.get()
            .load(personJackson.downloadUrl)
            .resize(120, 120)
            .error(R.drawable.ic_me)
            .centerCrop()
            .into(binding.personImageImageView)

        binding.personNameTextView.text = personJackson.name

        binding.personSizeTextView.text = getSize(personJackson.width, personJackson.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personJackson.openUrl, personJackson.downloadUrl)
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
            openMoreSetting(personGson.openUrl, personGson.downloadUrl)
            true
        }
    }


    private fun getSize(width: Int, heigth: Int): String {
        return "($width * $heigth)"
    }

    private fun openMoreSetting(openUrl: String, downloadUrl: String) {
        val longPressBottomSheet = LongPressBottomSheet(openUrl, downloadUrl)
        longPressBottomSheet.show(
            mainActivity.supportFragmentManager.beginTransaction(),
            longPressBottomSheet.tag
        )
    }
}