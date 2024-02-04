package com.app.androidtvapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import coil.load
import com.app.androidtvapp.databinding.PosterItemLayoutBinding
import com.app.androidtvapp.util.Common.Companion.getHeightPercent
import com.app.androidtvapp.util.Common.Companion.getWidthPercent
import com.app.domain.entities.MoviesInfo

class PosterPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {

        val binding =
            PosterItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val params = binding.root.layoutParams
        params.width = getWidthPercent(parent.context, 13)
        params.height = getHeightPercent(parent.context, 32)

        return ViewHolder(binding.root)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val binding = PosterItemLayoutBinding.bind(viewHolder.view)

        val movie = item as MoviesInfo
        binding.posterImage.setImageResource(0)
        binding.posterImage.load("https://www.themoviedb.org/t/p/w500" + movie.posterImg)
        binding.title.text = movie.title
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }

}