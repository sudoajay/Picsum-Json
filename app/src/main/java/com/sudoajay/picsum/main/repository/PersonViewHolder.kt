package com.sudoajay.picsum.main.repository


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.LayoutPersonListBinding
import com.sudoajay.picsum.main.MainActivity
import com.sudoajay.picsum.main.bottomsheet.LongPressBottomSheet
import com.sudoajay.picsum.main.model.local.PersonLocalGson
import com.sudoajay.picsum.main.model.local.PersonLocalJackson
import com.sudoajay.picsum.main.model.local.PersonLocalMoshi
import com.sudoajay.picsum.main.model.remote.PersonGson
import com.sudoajay.picsum.main.model.remote.PersonJackson
import com.sudoajay.picsum.main.model.remote.PersonMoshi


class PersonViewHolder(
    val mainActivity: MainActivity,
    private val binding: LayoutPersonListBinding
) : RecyclerView.ViewHolder(binding.root) {

    var TAG = "PersonViewHolderTAG"


    fun bind(personJackson: PersonJackson) {

        setImageLoader(personJackson.downloadUrl)


        binding.personNameTextView.text = personJackson.name

        binding.personSizeTextView.text = getSize(personJackson.width, personJackson.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personJackson.openUrl, personJackson.downloadUrl)
            true
        }
    }

    fun bind(personGson: PersonGson) {
        setImageLoader(personGson.downloadUrl)

        binding.personNameTextView.text = personGson.name

        binding.personSizeTextView.text = getSize(personGson.width, personGson.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personGson.openUrl, personGson.downloadUrl)
            true
        }
    }
    fun bind(personMoshi: PersonMoshi) {
        setImageLoader(personMoshi.downloadUrl)

        binding.personNameTextView.text = personMoshi.name

        binding.personSizeTextView.text = getSize(personMoshi.width, personMoshi.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personMoshi.openUrl, personMoshi.downloadUrl)
            true
        }
    }

    fun bind(personLocalJackson: PersonLocalJackson) {


        setImageLoader(personLocalJackson.downloadUrl)


        binding.personNameTextView.text = personLocalJackson.name

        binding.personSizeTextView.text =
            getSize(personLocalJackson.width, personLocalJackson.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personLocalJackson.openUrl, personLocalJackson.downloadUrl)
            true
        }
    }

    fun bind(personLocalGson: PersonLocalGson) {

        setImageLoader(personLocalGson.downloadUrl)

        binding.personNameTextView.text = personLocalGson.name

        binding.personSizeTextView.text = getSize(personLocalGson.width, personLocalGson.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personLocalGson.openUrl, personLocalGson.downloadUrl)
            true
        }
    }

    fun bind(personLocalMoshi: PersonLocalMoshi) {


        setImageLoader(personLocalMoshi.downloadUrl)
        binding.personNameTextView.text = personLocalMoshi.name

        binding.personSizeTextView.text = getSize(personLocalMoshi.width, personLocalMoshi.height)

        binding.boxConstraintLayout.setOnLongClickListener {
            openMoreSetting(personLocalMoshi.openUrl, personLocalMoshi.downloadUrl)
            true
        }
    }


    private fun getSize(width: Int, heigth: Int): String {
        return "($width * $heigth)"
    }

    private fun setImageLoader(url: String) {
        when (mainActivity.viewModel.getImageLoader) {
            mainActivity.getString(R.string.glide_text) -> {
                Log.e(TAG, "setImageLoader: At glide")

                Glide
                    .with(mainActivity)
                    .load(url)
                    .centerCrop()
                    .override(120, 120)
                    .placeholder(R.drawable.ic_me)
                    .error(R.drawable.ic_me)
                    .into(binding.personImageImageView);
            }
            else -> {
                Log.e(TAG, "setImageLoader: At picasso")

                Picasso.get()
                    .load(url)
                    .resize(120, 120)
                    .error(R.drawable.ic_me)
                    .placeholder(R.drawable.ic_me)
                    .centerCrop()
                    .into(binding.personImageImageView)

            }
        }
    }

    private fun openMoreSetting(openUrl: String, downloadUrl: String) {
        val longPressBottomSheet = LongPressBottomSheet(openUrl, downloadUrl)
        longPressBottomSheet.show(
            mainActivity.supportFragmentManager.beginTransaction(),
            longPressBottomSheet.tag
        )
    }
}