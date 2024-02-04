package com.app.androidtvapp.ui.home

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.app.androidtvapp.ui.detail.CastItemPresenter
import com.app.domain.entities.CastInfo
import com.app.domain.entities.MoviesInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : RowsSupportFragment() {

    private val viewModel: ListFragmentViewModel by activityViewModels()

    private val listRowPresenter = object : ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM) {
        override fun isUsingDefaultListSelectEffect(): Boolean {
            return false
        }
    }.apply {
        shadowEnabled = false
    }

    private val rootAdapter by lazy { ArrayObjectAdapter(listRowPresenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
//            prepareEntranceTransition()
        }
        this.adapter = rootAdapter

        setOnItemViewSelectedListener { _, item, _, _ ->
            if (item is MoviesInfo) {
                viewModel.onItemSelected(item)
            }
        }

        setOnItemViewClickedListener { _, item, _, _ ->
            if (item is MoviesInfo) {
                viewModel.onItemClicked(item)
            }
        }
    }

    fun bindCastData(list: List<CastInfo>) {
        val arrayObjectAdapter = ArrayObjectAdapter(CastItemPresenter())
        list.forEach { cast ->
            arrayObjectAdapter.add(cast)
        }
        val headerItem = HeaderItem("Cast & Crew")
        val listRow = ListRow(headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)
    }

    fun displayData(movies: List<MoviesInfo>, title: String) {
        val rowAdapter = ArrayObjectAdapter(PosterPresenter())
        movies.forEach { movie ->
            rowAdapter.add(movie)


        }
        val headerItem = HeaderItem(title)
        val row = ListRow(headerItem, rowAdapter)
        rootAdapter.add(row)
    }
}