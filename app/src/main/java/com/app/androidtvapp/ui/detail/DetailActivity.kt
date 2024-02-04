package com.app.androidtvapp.ui.detail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import coil.load
import com.app.androidtvapp.R
import com.app.androidtvapp.databinding.FragmentDetailBinding
import com.app.androidtvapp.ui.home.ListFragment
import com.app.domain.entities.MovieDetailInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : FragmentActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding
    private val castFragment = ListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.cast_fragment, castFragment)
        transaction.commit()

        val movieId = intent.getStringExtra("id").orEmpty()
        viewModel.getMovies(movieId)
        viewModel.getMoviesCast(movieId)
        viewModel.movieResponse.observe(this) { resource ->
            setData(resource)
        }

        viewModel.castResponse.observe(this) { response ->
            castFragment.bindCastData(response)
        }
    }

    private fun setData(data: MovieDetailInfo) {
        binding.title.text = data.title
        binding.subtitle.text = getSubtitle(data)
        binding.description.text = data.description
        binding.imgBanner.load("https://www.themoviedb.org/t/p/w780" + data.imgBanner)

        binding.description.isEllipsized { isEllipsized ->
            binding.showMore.visibility = if (isEllipsized) View.VISIBLE else View.GONE

            binding.showMore.setOnClickListener {

                descriptionDialog(
                    this,
                    data.title,
                    getSubtitle(data),
                    data.description
                )

            }
        }
    }

    private fun getSubtitle(response: MovieDetailInfo): String {
        val rating = if (response.rating) {
            "18+"
        } else {
            "13+"
        }

        val genres = response.genreName.joinToString(
            prefix = " ",
            postfix = " • ",
            separator = " • "
        )

        val hours: Int = response.runtime.toInt() / 60
        val min: Int = response.runtime.toInt() % 60

        return rating + genres + hours + "h " + min + "m"

    }

    private fun TextView.isEllipsized(ellipsize: (isEllipsized: Boolean) -> Unit) {
        val lineCount = layout?.lineCount ?: 0
        if (lineCount > 0) {
            val ellipseCount = layout?.getEllipsisCount(lineCount - 1) ?: 0
            ellipsize.invoke(ellipseCount > 0)
        }
    }

    private fun descriptionDialog(
        context: Context,
        title: String?,
        subtext: String,
        description: String
    ) {
        val dialog = Dialog(context, R.style.Theme_AndroidTVApp)
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_description)

        dialog.findViewById<TextView>(R.id.tvTitle).text = title
        dialog.findViewById<TextView>(R.id.tvSubTitle).text = subtext
        dialog.findViewById<TextView>(R.id.description).text = description

        dialog.findViewById<TextView>(R.id.closeBtn).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}