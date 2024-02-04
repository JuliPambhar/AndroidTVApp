package com.app.androidtvapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import coil.load
import coil.transform.CircleCropTransformation
import com.app.androidtvapp.databinding.CastItemLayoutBinding
import com.app.domain.entities.CastInfo

class CastItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val binding =
            CastItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val binding = CastItemLayoutBinding.bind(viewHolder.view)
        val cast = item as CastInfo
        binding.castImg.load("https://www.themoviedb.org/t/p/w500" + cast.castImg) {
            transformations(CircleCropTransformation())
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}