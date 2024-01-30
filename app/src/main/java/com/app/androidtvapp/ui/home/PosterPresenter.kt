package com.app.androidtvapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import coil.load
import com.app.androidtvapp.data.remote.MovieItem
import com.app.androidtvapp.databinding.ItemLayoutBinding

class PosterPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {

        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val params = binding.root.layoutParams
        params.width = getWidthPercent(parent.context, 13)
        params.height = getHeightPercent(parent.context, 32)

//        val imageCardView = ImageCardView(parent.context).apply {
//            isFocusable = true
//            isFocusableInTouchMode = true
//            cardType = BaseCardView.CARD_TYPE_MAIN_ONLY
//            with(mainImageView) {
//                val posterWidth = parent.resources.getDimension(R.dimen.poster_width).toInt()
//                val posterHeight = parent.resources.getDimension(R.dimen.Poster_height).toInt()
//                layoutParams = BaseCardView.LayoutParams(posterWidth, posterHeight)
//            }
//        }
        return ViewHolder(binding.root)
    }

    private fun getWidthPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    private fun getHeightPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.heightPixels ?: 0
        return (width * percent) / 100
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val binding = ItemLayoutBinding.bind(viewHolder.view)

        val movie = item as MovieItem
        binding.posterImage.setImageResource(0)
        binding.posterImage.load(movie.image_url)
        binding.title.text = movie.name

//        with(viewHolder.view as ImageCardView) {
//            val posterWidth = resources.getDimension(R.dimen.poster_width).toInt()
//            val posterHeight = resources.getDimension(R.dimen.Poster_height).toInt()
//
//            mainImageView.load(
//                data = movie.image_url,
//                builder = {
//                    scale(Scale.FIT)
//                    size(posterWidth, posterHeight)
//                    //placeholder(R.mipmap.ic_launcher)
//                })
//            titleText = movie.name
//        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }

}