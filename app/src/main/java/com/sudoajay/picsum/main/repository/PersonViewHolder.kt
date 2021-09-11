package com.sudoajay.picsum.main.repository

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.bottomsheet.LongPressBottomSheet
import com.sudoajay.picsum.main.model.local.PersonLocalGson
import com.sudoajay.picsum.main.model.local.PersonLocalJackson
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.model.remote.PersonJackson
import com.sudoajay.picsum.main.model.remote.PersonMoshi

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
    fun bind(personMoshi: PersonMoshi) {
        Picasso.get()
            .load(personMoshi.downloadUrl)
            .resize(120, 120)
            .error(R.drawable.ic_me)
            .centerCrop()
            .into(binding.personImageImageView)
        binding.personNameTextView.text = personMoshi.name

        binding.personSizeTextView.text = getSize(personMoshi.width, personMoshi.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personMoshi.openUrl, personMoshi.downloadUrl)
            true
        }
    }

    fun bind(personLocalJackson: PersonLocalJackson) {

        Picasso.get()
            .load(personLocalJackson.downloadUrl)
            .resize(120, 120)
            .error(R.drawable.ic_me)
            .centerCrop()
            .into(binding.personImageImageView)

        binding.personNameTextView.text = personLocalJackson.name

        binding.personSizeTextView.text = getSize(personLocalJackson.width, personLocalJackson.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personLocalJackson.openUrl, personLocalJackson.downloadUrl)
            true
        }
    }

    fun bind(personLocalGson: PersonLocalGson) {
        Picasso.get()
            .load(personLocalGson.downloadUrl)
            .resize(120, 120)
            .error(R.drawable.ic_me)
            .centerCrop()
            .into(binding.personImageImageView)
        binding.personNameTextView.text = personLocalGson.name

        binding.personSizeTextView.text = getSize(personLocalGson.width, personLocalGson.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personLocalGson.openUrl, personLocalGson.downloadUrl)
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